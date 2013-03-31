package com.ipjmc.demo.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import com.ipjmc.demo.rmi.WareHouse;

/**
 * 远程方法的实现，需要继承UnicastRemoteObject
 * @author wylazy
 *
 */
public class WareHouseImpl extends UnicastRemoteObject implements WareHouse {

	private Map<String, Double> products;
	
	public WareHouseImpl() throws RemoteException {
		products = new HashMap<String, Double>();
		products.put("A", 1.1);
		products.put("B", 2.2);
		products.put("C", 3.3);
	}
	
	@Override
	public double getPrice(String name) throws RemoteException {
		Double price = products.get(name);
		return price == null ? 0 : price;
	}

}
