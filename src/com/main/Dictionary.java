package com.main;

import com.lang.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Dictionary {

	private static final String DICT = "E:\\HomeTaskWeek11\\dict\\english.dict";

	@Autowired
	ResourceLoader resourceLoader;

	private Map<Language, Map<String, String>> dictionaries = new HashMap<Language, Map<String, String>>();

	public String translate(String word, Language language) {
        String translateWord = word;
		if (getDictionary(language).get(word) != null) {
			translateWord = getDictionary(language).get(word);
		}
		return translateWord;
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
        List<String> words = resourceLoader.load(DICT);
		Map<String, String> dictionary = new HashMap<String, String>();
		for(String word : words){
			int index = word.indexOf("=");
			if(language == Language.ENGLISH){
				dictionary.put(word.substring(0, index), word.substring(index+1, word.length()));
			}else if(language == Language.UKRAINIAN){
				dictionary.put(word.substring(index+1, word.length()), word.substring(0, index));
			}
		}
        return dictionary;
	}


}
