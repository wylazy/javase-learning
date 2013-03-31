package com.ipjmc.demo.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapDemo {

	private HashMap<Integer, String> map;
	
	public MapDemo() {
		map = new HashMap<Integer, String>();
	}
	
	public void test() {
		map.put(10086, "中国移动");
		map.put(1024, "二进制");
		map.put(65535, "端口");
		
		print();
		System.out.println("-------------");
		Iterator<Entry<Integer, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> entry = iterator.next();
			if(entry.getKey() == 65535) {
				iterator.remove();
			}
		}
		print();
		
	}
	
	public void print() {
		Iterator<Entry<Integer, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> entry = iterator.next();
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
	}
	
	public static void main(String [] args) {
		MapDemo demo = new MapDemo();
		demo.test();
	}
}
