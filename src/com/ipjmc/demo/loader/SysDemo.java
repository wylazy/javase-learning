package com.ipjmc.demo.loader;

public class SysDemo {

	public static void main(String [] args) {
		String dir = System.getProperty("java.ext.dirs");
		System.out.println("java.exe.dirs : " + dir);
		
		String path = System.getProperty("java.class.path");
		System.out.println("java.class.path : " + path);
		
		System.out.println("loader = " + SysDemo.class.getClassLoader().getResource(""));
		try {
			Class.forName("com.ipjmc.class.Custom");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.load("No");
		
	}
}
