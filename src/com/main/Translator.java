package com.main;

import com.lang.Language;
import com.lang.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class Translator {

	@Autowired
	private LanguageDetector languageDetector;

	@Autowired
	private TextSource textSource;

	@Autowired
	private Dictionary dictionary;

	public static final Pattern wordSplitPattern = Pattern.compile("[^a-zA-Z]+", Pattern.DOTALL);

	public String translate(String source) {
		String text = textSource.getText(source);
		Language language = languageDetector.detectLanguage(text);
		Set<String> words = Arrays
				.stream(wordSplitPattern.split(text))
				.collect(Collectors.toSet());
		for (String word: words) {
			String translation = dictionary.translate(word, language);
			if (translation != null) {
				text = text.replaceAll("\\b" + word + "\\b", translation);
			}
		}
		return text;
	}
}
