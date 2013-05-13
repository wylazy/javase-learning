package com.ipjmc.demo.obj;

abstract public class PrivateBase {

	private void printName() {
		System.out.println("Base");
	}
	
	public void print() {
		printName();
	}
}
