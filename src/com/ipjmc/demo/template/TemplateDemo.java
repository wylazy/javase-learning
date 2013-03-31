package com.ipjmc.demo.template;

import java.util.List;

public class TemplateDemo<T> {

	private T entity;
	
	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	public T getEntity() {
		return this.entity;
	}
}
