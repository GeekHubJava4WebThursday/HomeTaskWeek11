package com.main;

import com.lang.Language;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Component
public class Dictionary {

    private Map<Language, Map<String, String>> dictionaries = new HashMap<>();

    public static final String delimiterPattern = "[^a-zA-Z]";

    public String translate(String text, Language language) {
        Map<String, String> dictionary = getDictionary(language);
        return Arrays
                .stream(text.split(delimiterPattern))
                .map(word -> {
                    String value = dictionary.get(word.toLowerCase());
                    return (value == null) ? word : value;
                })
                .collect(Collectors.joining(" "));
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
//        List<String> dictionary = resourceLoader.load(System.getProperty("user.dir") + "\\dict\\english.dict");
        Properties properties = new Properties();
        String propertyFile = System.getProperty("user.dir") + "\\dict\\" + language.toString().toLowerCase() + ".dict";
        try (InputStreamReader inputStream = new InputStreamReader(new FileInputStream(propertyFile))) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> String.valueOf(entry.getKey()),
                        entry -> String.valueOf(entry.getValue())
                ));
	}
}
