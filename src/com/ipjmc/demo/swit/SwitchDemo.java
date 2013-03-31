package com.ipjmc.demo.swit;

public class SwitchDemo {

	public static void main(String [] args) {
		Integer k = null;
		switch (k) {
		case 0:
			System.out.println("k == 0");
			break;
		default:
			System.out.println("k != 0");
			break;
		}
	}
}
