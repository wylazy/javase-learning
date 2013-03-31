package com.ipjmc.demo.rmi.client;

import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

import com.ipjmc.demo.rmi.WareHouse;

public class WareHouseClient {

	public static void main(String [] args) throws NamingException, RemoteException {
		Context context = new InitialContext();
		System.out.println("RMI Registery binds:");
		
		Enumeration<NameClassPair> e = context.list("rmi://localhost:1099/");
		while (e.hasMoreElements()) {
			NameClassPair pair = (NameClassPair) e.nextElement();
			System.out.println(pair.getName());
		}
		
		final String uri = "rmi://localhost:1099/ware_house";
		WareHouse house = (WareHouse) context.lookup(uri);
		
		String name = "A";
		System.out.println(name + " => " + house.getPrice(name));
	}
}
