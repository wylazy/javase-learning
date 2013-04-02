package com.ipjmc.demo.nio;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class WatcherDemo {

	public static void main(String [] args) throws IOException, InterruptedException {
		WatchService watcher = FileSystems.getDefault().newWatchService();
		Path path = Paths.get("/home/wylazy/");
		
		path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, 
							   StandardWatchEventKinds.ENTRY_MODIFY, 
							   StandardWatchEventKinds.ENTRY_DELETE);
		
		while (true) {
			WatchKey key = watcher.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				Kind<?> kind = event.kind();
				WatchEvent<Path> watchEventPath = (WatchEvent<Path>) event;
				System.out.println(kind + " => " + watchEventPath.context());
			}
			
			if (!key.reset()) {
				break;
			}
		}
	}
}
