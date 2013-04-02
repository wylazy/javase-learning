package com.ipjmc.demo.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirDemo {

	public static void main(String [] args) throws IOException {
		File file = new File("/home");
		System.out.println(file.getAbsolutePath() + File.separatorChar);
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());

		
		System.out.println("Path:");
		Path dir = Paths.get("/tmp");
		System.out.println(Files.isRegularFile(dir));
		System.out.println(Files.isDirectory(dir));
		
		DirectoryStream<Path> paths = Files.newDirectoryStream(dir);
		
		for (Path path : paths) {
			System.out.println(path);
		}
	}
}
