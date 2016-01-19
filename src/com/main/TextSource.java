package com.main;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class TextSource {

	public String getText(String path) {
        String text = "";
		if (Files.exists(Paths.get(path))) {
			try {
                text = new String(Files.readAllBytes(Paths.get(path)));
            } catch (IOException e) {
                e.printStackTrace();
            }
		}else {
			System.err.println("File not found!");
		}
		return text;
	}
}
