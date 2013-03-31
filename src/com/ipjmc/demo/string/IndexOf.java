package com.ipjmc.demo.string;

public class IndexOf {

	public static void main(String [] args) {
		String response = "<?xml version='1.0' encoding='UTF-8'?>\n<taskProcessingNum>0</taskProcessingNum>";
		int index = response.indexOf("</taskProcessingNum>");
		System.out.println(response.substring(index-1, index));
	}
}
