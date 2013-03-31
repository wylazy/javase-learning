package com.ipjmc.demo.io;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class DirDemo {

	public static void main(String [] args) throws IOException {
		File file = new File("/home/");
		System.out.println(file.getAbsolutePath() + File.separatorChar);
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
	}
}
