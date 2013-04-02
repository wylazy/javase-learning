package com.ipjmc.demo.nio;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 * BufferIO
 * @author wylazy
 *
 */
public class BufferIODemo {

	private static final int SIZE = 128;
	
	public static void main(String [] args) throws Exception {
		
		Path path = Paths.get("/home/wylazy/data");
		FileChannel channel = FileChannel.open(path, EnumSet.of(StandardOpenOption.READ));

		ByteBuffer directBuffer = ByteBuffer.allocateDirect(SIZE);
		process2(channel, directBuffer);
		
		channel.close();
	}
	
	/**
	 * 无解码，适合处理英文文章
	 * @param channel
	 * @param directBuffer
	 * @throws IOException
	 */
	public static void process1(FileChannel channel, ByteBuffer directBuffer) throws IOException {
		byte [] dst = new byte[SIZE];
		while (channel.read(directBuffer) > 0) {
			directBuffer.flip();
			while (directBuffer.hasRemaining()) {
				int len = Math.min(directBuffer.remaining(), dst.length);
				directBuffer.get(dst, 0, len);
				System.out.print(new String(dst, 0, len));
			}
			directBuffer.clear();
		}
	}
	
	/**
	 * 通过utf-8解码
	 * @param channel
	 * @param directBuffer
	 * @throws IOException
	 */
	public static void process2(FileChannel channel, ByteBuffer directBuffer) throws IOException {
		Charset charset = Charset.forName("utf-8");
		CharsetDecoder decoder = charset.newDecoder();
		decoder.onMalformedInput(CodingErrorAction.REPORT);
		decoder.onUnmappableCharacter(CodingErrorAction.REPORT);
		
		CharBuffer buffer = CharBuffer.allocate(SIZE/2);
		boolean eof = false;
		CoderResult coderResult = CoderResult.UNDERFLOW;
		int c = 0;
		while (!eof) {

			//directBuffer 已经没有更多的数据
			if (coderResult == CoderResult.UNDERFLOW) {
				directBuffer.compact(); //directbuffer可能在utf-8的某个字符中间断开
				eof = (channel.read(directBuffer) == -1);
				directBuffer.flip();
			}

			coderResult = decoder.decode(directBuffer, buffer, eof);
			
			//buffer已满
			if (coderResult == CoderResult.OVERFLOW) {
				drainBuffer(buffer);
			}

		}
		
		while (decoder.flush(buffer) == CoderResult.OVERFLOW) {
			drainBuffer(buffer);
		}
		
		drainBuffer(buffer);
	}
	
	public static void drainBuffer(CharBuffer buffer) {
		buffer.flip();
		if (buffer.hasRemaining()) {
			System.out.print(buffer.toString());
		}
		buffer.clear();
	}
}
