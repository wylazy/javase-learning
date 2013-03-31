package com.ipjmc.demo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shared {
	private int count;
	private boolean finished;
	
	private Lock lock = new ReentrantLock(true);
	private Condition condition = lock.newCondition();
	
	public int getCount() {
		lock.lock();
		try {
			while (!finished) {
				condition.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return this.count;
	}

	public void working(int size) {
		
		lock.lock();
		try {
			for (int i = 0; i < size; i++) {
				this.count++;
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			finished = true;
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}
