package com.ipjmc.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;

/**
 * AIO 异步IO
 * @author wylazy
 *
 */
public class HandlerServer {

	private static final int SIZE = 1024;
	private static final byte CTRL_D = 4;
	
	private AsynchronousChannelGroup chanelGroup;
	private AsynchronousServerSocketChannel serverChannel;
	
	private AcceptHandler acceptHandler;
	
	public void start() throws IOException {
		chanelGroup = AsynchronousChannelGroup.withFixedThreadPool(
				Runtime.getRuntime().availableProcessors() * 2, //2倍CPU核心数的线程 
				Executors.defaultThreadFactory());
		
		serverChannel = AsynchronousServerSocketChannel.open(chanelGroup);
		if (serverChannel.isOpen()) {
			
			serverChannel.bind(new InetSocketAddress(10086));
			acceptHandler = new AcceptHandler();
			serverChannel.accept(null, acceptHandler);
			
			System.in.read();
		}
		
	}

	/**
	 * 处理Accept的Handler
	 * @author wylazy
	 *
	 */
	private class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Void> {

		@Override
		public void completed(AsynchronousSocketChannel channel, Void attachment) {

			//接收下一个客户端的connection
			serverChannel.accept(null, this);
			
			ReadHandler readHandler = new ReadHandler(channel);
			WriteHandler writeHandler = new WriteHandler(channel);
			
			readHandler.setWriteHandler(writeHandler);
			writeHandler.setReadHandler(readHandler);

			//异步接收
			ByteBuffer dst = ByteBuffer.allocate(SIZE);
			channel.read(dst, dst, readHandler);
			
			//异步发送
			ByteBuffer src = ByteBuffer.wrap("Welcome\n".getBytes());
			channel.write(src, src, writeHandler);
		}

		@Override
		public void failed(Throwable exc, Void attachment) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * 接收数据的Handler
	 * @author wylazy
	 *
	 */
	private class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {

		private WriteHandler writeHandler;
		private AsynchronousSocketChannel channel;
		
		public ReadHandler(AsynchronousSocketChannel channel) {
			this.channel = channel;
		}
		
		public void setWriteHandler(WriteHandler writeHandler) {
			this.writeHandler = writeHandler;
		}
		
		@Override
		public void completed(Integer result, ByteBuffer buffer) {
			if (result > 0) {
				buffer.flip();
				
				if (buffer.get(0) == CTRL_D) {
					try {
						channel.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				System.out.print(new String(buffer.array()));
				
				//将数据echo回客户端
				byte [] data = new byte[result];
				buffer.get(data);
				ByteBuffer src = ByteBuffer.wrap(data);
				channel.write(src, src, writeHandler);
				
				//继续接收客户端的数据
				buffer.clear();
				channel.read(buffer, buffer, this);
				
			} else {
				try {
					channel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

		@Override
		public void failed(Throwable exc, ByteBuffer buffer) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * 发送数据的Handler
	 * @author wylazy
	 *
	 */
	private class WriteHandler implements CompletionHandler<Integer, ByteBuffer> {

		private ReadHandler readHandler;
		private AsynchronousSocketChannel channel;
		
		public WriteHandler(AsynchronousSocketChannel channel) {
			this.channel = channel;
		}
		
		public void setReadHandler(ReadHandler readHandler) {
			this.readHandler = readHandler;
		}
		
		@Override
		public void completed(Integer result, ByteBuffer buffer) {
			
			//如果发送数据失败，则关闭连接
			if (result <= 0) {
				try {
					channel.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		@Override
		public void failed(Throwable exc, ByteBuffer buffer) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main(String [] args) throws Exception {
		new HandlerServer().start();
	}
}
