package com.ipjmc.demo.security;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.AccessController;

import com.ipjmc.demo.http.SimpleHttpClient;

public class SecurityTest {

	static {
		System.setProperty("java.security.policy", "/home/wylazy/workspace/javaweb/JavaSE/resources/mySecurity.policy");
	}
	
	public static void test() throws IOException {
		
		SecurityManager securityManager = new SecurityManager();
		System.setSecurityManager(securityManager);
		
		//final String path = "/home/wylazy/data";
		final String path = "/home/wylazy/workspace/javaweb/j2ee/yunhai/5d4f7693-077a-4bab-a580-111111111112/webapp/WEB-INF/lib/struts2-core-2.1.8.1.jar";
		File file = new File(path);
		FileReader reader = new FileReader(file);
		char [] buf = new char[64];
		int len = reader.read(buf);
		System.out.println(new String(buf, 0, len));
		reader.close();
	}
	
	public static void customTest() throws IOException {
		SecurityManager securityManager = new MySecurityManager();
		System.setSecurityManager(securityManager);
		FileReader reader = new FileReader("/home/wylazy/xmpp.txt");
		char [] buf = new char[64];
		int len = reader.read(buf);
		System.out.println(new String(buf, 0, len));
		reader.close();
	}
	
	public static void httpTest() throws Exception {
		SecurityManager securityManager = new SecurityManager();
		System.setSecurityManager(securityManager);
		SimpleHttpClient client = new SimpleHttpClient();
		System.out.println(client.get("http://front.free4lab.com/", null));
	}
	
	public static void runCmd(String cmd) {
		
		SecurityManager securityManager = new MySecurityManager();
		System.setSecurityManager(securityManager);
		
		Runtime run = Runtime.getRuntime();//返回与当前 Java 应用程序相关的运行时对象  
        try {  
            Process p = run.exec(cmd);// 启动另一个进程来执行命令  
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());  
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
            String lineStr;  
            while ((lineStr = inBr.readLine()) != null) {
                //获得命令执行后在控制台的输出信息  
                System.out.println(lineStr);// 打印输出信息  
            }
            
            //检查命令是否执行失败。  
            if (p.waitFor() != 0) {  
                if (p.exitValue() == 1)//p.exitValue()==0表示正常结束，1：非正常结束  
                    System.err.println("命令执行失败!");  
            }  
            inBr.close();  
            in.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
	}
	public static void main(String [] args) throws Exception {
		test();
		//customTest();
		//runCmd("ifconfig");
	//	httpTest();
		

		//System.out.println(System.getProperty("os.name") + System.getProperty("os.version"));

	}
}
