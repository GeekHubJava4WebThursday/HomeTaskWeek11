package com.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TextSource {

	@Autowired
	private ResourceLoader resourceLoader;

    public String getText(String path) {
        return resourceLoader.load(path)
                .stream()
				.collect(Collectors.joining(" "));
	}
}