package com.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextSource {

	@Autowired
	private ResourceLoader resourceLoader;

	public String getText(String path) {
		List<String> text = resourceLoader.load(path);
		StringBuilder sb = new StringBuilder();
		text.forEach(sb::append);
        return sb.toString();
	}
}
