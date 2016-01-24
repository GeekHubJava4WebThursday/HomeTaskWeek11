package com.main;

import com.enums.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Dictionary {

    @Autowired
    ResourceLoader resourceLoader;

    private Map<Language, Map<String, String>> dictionaries = new HashMap<Language, Map<String, String>>();

    public String translate(String word, Language language) {
        Map<String, String> dictionary = getDictionary(language);
        String translation = dictionary.get(word);
        return dictionary.get(word) != null ? translation : word;
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
        List<String> stringList = resourceLoader.load("dict/" + language.toString().toLowerCase() + ".dict");
        for (String line : stringList) {
            String[] split = line.split("=");
            dictionary.put(split[0].replace("\uFEFF", ""), split[1]);
        }
        return dictionary;
    }
}
