package com.ipjmc.demo.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibMethodInterceptor implements MethodInterceptor {

	private Enhancer enhancer = new Enhancer();
	
	public Object getProxy(Class clz) {
		enhancer.setSuperclass(clz);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		long begin = System.currentTimeMillis();
		Object result = proxy.invokeSuper(obj, args);
		long end = System.currentTimeMillis();
		System.out.println("CglibMethodInterceptor 执行花费时间：" + (end - begin) + "ms");
		return result;
	}

}
