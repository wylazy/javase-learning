package com.ipjmc.demo.loader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class PathClassLoader extends ClassLoader {

	private String classPath;
	
	public PathClassLoader(String classPath) {
		this.classPath = classPath;
	}
	
	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		byte [] data = getData(className);
		if (data == null) {
			throw new ClassNotFoundException();
		} else {
			return defineClass(className, data, 0, data.length);
		}
	}
	
	private byte [] getData(String className) {
		String fullPath = classPath + File.separatorChar + className.replace('.', File.separatorChar);
		
		File file = new File(fullPath);
		int size = 0;
		if (file.isFile()) {
			if (file.length() < 256*1024) {
				size = (int) file.length();
			}
		}
		
		if (size == 0) {
			return null;
		}
		
		BufferedInputStream is = null;
		try {
			
			is = new BufferedInputStream(new FileInputStream(file));
			byte [] data = new byte[size];
			int offset = 0;
			while (offset < size) {
				offset += is.read(data, offset, size);
			}
			return data;	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String [] args) throws Exception {
		final String path = "/home/wylazy/workspace/javaweb/JavaSE/target/classes/";
		ClassLoader loader = new PathClassLoader(path);
		Class clazz = loader.loadClass("com.ipjmc.demo.string.ListString");
		Method method = clazz.getDeclaredMethod("main", new Class[]{String[].class});
		method.invoke(clazz, new Object [] {null});
	}
}
