package com.ipjmc.demo.nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 使用Java NIO Select模型
 * @author wylazy
 *
 */
public class SelectServer {

	/**
	 * 发送缓冲区大小
	 */
	private static int BUFFER_SIZE = 64;
	
	
	/**
	 * Channel的多路复用器
	 */
	private Selector selector;
	
	
	/**
	 * Server用于accept的socket
	 */
	private ServerSocketChannel serverChannel;
	
	
	/**
	 * 如果server想发送数据，先将数据放到这里，然后设置key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
	 * 才能让selector监控发送缓冲区，key是相应的Channel
	 */
	private Map<SocketChannel, List<ByteBuffer> > toWriteData;
	
	
	public void start(String[] args) throws IOException {

		selector = Selector.open();
		serverChannel = ServerSocketChannel.open();
		toWriteData = new HashMap<>();

		if (selector.isOpen() && serverChannel.isOpen()) {
			serverChannel.configureBlocking(false);
			serverChannel.bind(new InetSocketAddress(10020));

			//先用selector监控Accept事件
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);

			System.out.println(serverChannel.getLocalAddress() + " waiting for connections ...");
			while (true) {
				selector.select(); //可能会阻塞
				processEvent();
			}
		}

	}

	/**
	 * 处理相应的IO事件
	 * @throws IOException
	 */
	public void processEvent() throws IOException {
		Set<SelectionKey> selectionKeySet = selector.selectedKeys();
		Iterator<SelectionKey> itr = selectionKeySet.iterator();
		while (itr.hasNext()) {
			SelectionKey key = itr.next();
			itr.remove();
			if (!key.isValid()) {
				continue;
			}

			if (key.isAcceptable()) {
				acceptOp();
			} else if (key.isReadable()) {
				readOp(key);
			} else if (key.isWritable()) {
				writeOp(key);
			}
		}
	}
	
	/**
	 * 有客户端连接到来
	 * @throws IOException
	 */
	private void acceptOp() throws IOException {
		SocketChannel socketChannel = serverChannel.accept();
		socketChannel.configureBlocking(false);
		System.out.println("Connected from " + socketChannel.getRemoteAddress());
		
		SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);
		List<ByteBuffer> list = new LinkedList<ByteBuffer>();
		toWriteData.put(socketChannel, list);
		send(key, ByteBuffer.wrap("Hello\n".getBytes("utf-8")));
	}
	
	/**
	 * 有客户端发送数据到来
	 * @param key
	 * @throws IOException
	 */
	private void readOp(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		ByteBuffer buf = ByteBuffer.allocate(BUFFER_SIZE);
		int len = socketChannel.read(buf);
		if (len < 0) {
			System.out.println("Connection was closed by " + socketChannel.getRemoteAddress());
			socketChannel.close();
			key.cancel();
			return;
		}
		
		byte [] data = new byte[len];
		System.arraycopy(buf.array(), 0, data, 0, len);
		
		System.out.print(new String(data, Charset.forName("utf-8")));

		send(key, ByteBuffer.wrap(data));
		
		//必须重新设置SelectionKey
		key.interestOps(key.interestOps() | SelectionKey.OP_READ);
	}
	
	/**
	 * 发送缓冲区可用
	 * @param key
	 * @throws IOException
	 */
	private void writeOp(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		List<ByteBuffer> list = toWriteData.get(socketChannel);
		Iterator<ByteBuffer> itr = list.iterator();
		while (itr.hasNext()) {
			ByteBuffer buf = itr.next();
			socketChannel.write(buf);
			
			//数据没有发送完，说明缓冲区已经满了，停止发送
			if (buf.hasRemaining()) {
				break;
			}
			
			itr.remove();
		}
	}
	
	/**
	 * 要发送数据时，先将数据放入toWriteData，然后让selector监控发送缓冲区，等发送缓冲区可用时再完成实际的发送
	 * @param key
	 * @param buffer
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	private void send(SelectionKey key, ByteBuffer buffer) throws UnsupportedEncodingException, IOException {
		toWriteData.get(key.channel()).add(buffer);
		key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
	}
	
	public static void main(String [] args) throws Exception {
		new SelectServer().start(args);
	}
}
