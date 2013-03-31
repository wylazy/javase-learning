package com.ipjmc.demo.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ipjmc.demo.rmi.WareHouse;

public class WareHouseServer {

	public static void main(String [] args) throws RemoteException, NamingException {
		System.out.println("Constructing server implentation ...");
		WareHouse house = new WareHouseImpl();
		
		LocateRegistry.createRegistry(1099);
		System.out.println("Binding server implentation to registery ...");
		Context context = new InitialContext();
		context.bind("rmi:ware_house", house);
		
		System.out.println("Waiting for invocations from clients ...");
	}
}
