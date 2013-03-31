package com.ipjmc.demo.string;

import java.util.LinkedList;

public class ListString {

	public static void main(String [] args) {
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		
		System.out.println(list.toString());
	}
}
