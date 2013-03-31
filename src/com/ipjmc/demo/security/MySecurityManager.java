package com.ipjmc.demo.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Permission;

public class MySecurityManager extends SecurityManager {
	
	@Override
	public void checkRead(String file) {
		
		if (!file.endsWith(".txt")) {
			throw new SecurityException("您没有权限读取该文件: " + file);
		}
		super.checkRead(file);
	}

	@Override
	public void checkPropertyAccess(String key) {
		super.checkPropertyAccess(key);
		if (key.startsWith("java")) {
			throw new SecurityException("Can't access property " + key);
		}
	}
	
}
