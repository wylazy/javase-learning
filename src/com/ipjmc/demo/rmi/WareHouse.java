package com.ipjmc.demo.rmi;

import java.rmi.*;

/**
 * 客户端和服务器共享的远程对象接口
 * @author wylazy
 *
 */
public interface WareHouse extends Remote {

	public double getPrice(String name) throws RemoteException;
}
