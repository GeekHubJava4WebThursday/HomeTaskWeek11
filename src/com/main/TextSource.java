package com.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TextSource {

	@Autowired
	ResourceLoader resourceLoader;

	public String getText(String path) {
		StringBuilder sb = new StringBuilder();

		for (String line : resourceLoader.load(path)) {
			sb.append(line);
		}

		return sb.toString();
	}
}
