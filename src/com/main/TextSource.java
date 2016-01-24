package com.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TextSource {

    @Autowired
    ResourceLoader resourceLoader;

    public String getText(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : resourceLoader.load(path)) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }

}
