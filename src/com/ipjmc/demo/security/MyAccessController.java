package com.ipjmc.demo.security;

import java.security.AccessControlException;
import java.security.Permission;

public class MyAccessController {

	public static void checkPermission(Permission perm) throws AccessControlException {  
		throw new SecurityException("你没有的权限");
	}  
}
