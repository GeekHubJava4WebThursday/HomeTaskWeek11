package com.main;

import com.lang.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class Dictionary {

	@Autowired
	private ResourceLoader resourceLoader;
	private Map<Language, Map<String, String>> dictionaries = new HashMap<>();

	public String translate(String word, Language language) {
		return getDictionary(language).get(word);
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
		List<String> wordList = resourceLoader.load("lang/" + language + ".dict");
		Map<String, String> dictionary = new HashMap<>();
		wordList.forEach(w -> dictionary.put(w.split("=")[0], w.split("=")[1]));
        return dictionary;
	}
}
