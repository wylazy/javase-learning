package com.ipjmc.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FutureServer {

	private AsynchronousServerSocketChannel asynChannel;
	public void start() throws Exception {
		
		asynChannel = AsynchronousServerSocketChannel.open();
		if (asynChannel.isOpen()) {
			
			asynChannel.bind(new InetSocketAddress(10086));
			
			//接收客户端的连接
			Future<AsynchronousSocketChannel> future = asynChannel.accept();
			AsynchronousSocketChannel channel = future.get();
			
			//接收请求
			ByteBuffer buffer = ByteBuffer.allocate(8);
			channel.read(buffer).get();
			
			//echo
			buffer.flip();
			channel.write(buffer).get();
			channel.close();
		}
	}
	
	public static void main(String [] args) throws Exception {
		new FutureServer().start();
	}
}
