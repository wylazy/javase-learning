package com.ipjmc.demo.proxy;

import java.lang.reflect.Proxy;

import com.ipjmc.demo.proxy.cglib.CglibMethodInterceptor;
import com.ipjmc.demo.proxy.dynamic.PerformanceHandler;


public class Main {

	public static void main(String [] args) throws InterruptedException {
		testDynamicProxy();
		testCglibProxy();
	}
	
	private static void testDynamicProxy() {
		ForumService service = new ForumServiceImpl();
		
		PerformanceHandler handler = new PerformanceHandler(service);
		
		ForumService proxy = (ForumService) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), handler);
	
		proxy.removeTopic();
	}
	
	private static void testCglibProxy() {
		CglibMethodInterceptor proxy = new CglibMethodInterceptor();
		ForumServiceImpl service = (ForumServiceImpl) proxy.getProxy(ForumServiceImpl.class);
		service.removeTopic();
	}
}
