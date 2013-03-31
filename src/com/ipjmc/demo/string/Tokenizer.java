package com.ipjmc.demo.string;

import java.util.StringTokenizer;

public class Tokenizer {

	private static final String TIME = "10:03:57";
	
	public static void main(String [] args) {
		StringTokenizer tokenizer = new StringTokenizer(TIME, ":");
		while (tokenizer.hasMoreTokens()) {
			System.out.println(tokenizer.nextToken());
		}
		//System.out.println(tokenizer.nextToken());
		
		tokenizer = new StringTokenizer(TIME, ":");
		while (tokenizer.hasMoreElements()) {
			System.out.println(tokenizer.nextElement());
		}
	}
}
