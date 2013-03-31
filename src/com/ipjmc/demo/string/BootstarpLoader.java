package com.ipjmc.demo.string;

public class BootstarpLoader {

	public static void main(String [] args) {
		ClassLoader loader = String.class.getClassLoader();
		if (loader == null) {
			System.out.println("String.class.getClassLoader() == null");
		} else {
			System.out.println(loader);
		}
	}
}
