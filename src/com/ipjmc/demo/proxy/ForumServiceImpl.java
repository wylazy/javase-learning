package com.ipjmc.demo.proxy;

public class ForumServiceImpl implements ForumService {

	@Override
	public void removeTopic() {
		System.out.println("Removing topic ...");
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
