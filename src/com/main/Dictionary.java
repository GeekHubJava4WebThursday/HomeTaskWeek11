package com.main;

import com.lang.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Dictionary {

	@Autowired
	ResourceLoader resourceLoader;

	private Map<Language, Map<String, String>> dictionaries = new HashMap<>();

	public String translate(String word, Language language) {
		Map<String, String> dictionary = getDictionary(language);
		word = word.replaceAll("\\p{Punct}", "");
		String translation = dictionary.get(word);
		return translation == null ? word : translation;

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
		Map<String, String> dictionary = new HashMap<>();
		List<String> lines = resourceLoader.load("dict/" + language.name().toLowerCase() + ".dict");
		for (String line : lines) {
			String[] words = line.split("=");
			dictionary.put(words[0], words[1]);
		}

		return dictionary;
	}
}
