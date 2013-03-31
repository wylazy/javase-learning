package com.ipjmc.demo.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceHandler implements InvocationHandler {

	public Object target;
	
	public PerformanceHandler(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		long begin = System.currentTimeMillis();
		method.invoke(target, args);
		long end = System.currentTimeMillis();
		
		System.out.println("DynamicProxy 执行花费时间：" + (end - begin) + "ms");
		return target;
	}

}
