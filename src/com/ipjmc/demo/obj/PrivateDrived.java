package com.ipjmc.demo.obj;

public class PrivateDrived extends PrivateBase {

	
	public void printName() {
		System.out.println("Drived");
	}
	
	public static void main(String [] args) {
		PrivateBase demo = new PrivateDrived();
		demo.print();
	}
}
