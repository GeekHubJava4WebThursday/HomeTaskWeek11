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
    ResourceLoader resourceLoader;

    private Map<Language, Map<String, String>> dictionaries = new HashMap<>();


    public String translate(String word, Language language) {
        String translated = getDictionary(language).get(word);
        if (translated == null) {
            return word;
        }
        return translated;
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
        List<String> load = resourceLoader.load("/home/sergei/GeekHub/HomeTaskWeek11/dict/english.dict");
        Map<String, String> dictionary = new HashMap<>();

        if (language.equals(Language.ENGLISH)) {
            for (String s : load) {
                String[] arr = s.split("=");
                dictionary.put(arr[0], arr[1]);
            }
        } else if (language.equals(Language.RUSSIAN)) {
            for (String s : load) {
                String[] arr = s.split("=");
                dictionary.put(arr[1], arr[0]);
            }
        } else
            throw new UnsupportedOperationException();

        return dictionary;
    }
}
