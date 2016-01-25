package com.main;

import com.lang.Language;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class Dictionary {

    private Map<Language, Map<String, String>> dictionaries = new HashMap<>();

    public static final Pattern wordPattern = Pattern.compile("[^a-zA-Z]+", Pattern.DOTALL);

    public String translate(String text, Language language) {
        Map<String, String> dictionary = getDictionary(language);
        Set<String> words = Arrays
                .stream(wordPattern.split(text))
                .collect(Collectors.toSet());
        for (String word: words) {
            String translation = dictionary.get(word.toLowerCase());
            if (translation != null) {
                text = text.replaceAll("\\b" + word + "\\b", toCapital(translation, word));
            }
        }
        return text;
	}

    private String toCapital(String translation, String origin) {
        if (origin.length() < 1 || translation.length() < 1) {
            return origin;
        }
        if (translation.length() < 1 || !Character.isUpperCase(origin.charAt(0))) {
            return translation;
        }
        if (translation.length() == 1) {
            return translation.toUpperCase();
        }
        return translation.substring(0, 1).toUpperCase() + translation.substring(1);
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
        try (InputStreamReader inputStream = new InputStreamReader(
                new FileInputStream(propertyFile), Charset.defaultCharset())) {
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
