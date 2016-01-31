package com.main;

import com.lang.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Dictionary {

    @Autowired
    private ResourceLoader resourceLoader;

	private Map<Language, Map<String, String>> dictionaries = new HashMap<Language, Map<String, String>>();

	public String translate(String word, Language language) {
        Map<String, String> dictionary = getDictionary(language);

		return dictionary.get(word.toLowerCase())!=null?
                dictionary.get(word.toLowerCase()):
                word;
	}

	private Map<String, String> getDictionary(Language language) {
		Map<String, String> dictionary = dictionaries.get(language);
		if (null == dictionary) {
			dictionary = loadDictionary(language);
			dictionaries.put(language, dictionary);
		}
		return dictionary;
	}

	private Map<String, String> loadDictionary(Language language) {
        Path dictionaryPath = Paths.get("dict/" + language.name().toLowerCase() + ".dict");
        List<String> words = new ArrayList<>();
        try {
            words = Files.readAllLines(dictionaryPath, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,String> dictionary = new HashMap<>();
        words.stream()
                .forEach(word -> {
                    String[] dictWords = word.split("=",2);
                    dictionary.put(dictWords[0],dictWords[1]);
                });
        return dictionary;
	}
}
