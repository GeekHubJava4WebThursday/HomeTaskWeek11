package com.main;

import com.lang.Language;
import com.lang.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Translator {

	@Autowired
	private LanguageDetector languageDetector;
	@Autowired
	private TextSource textSource;
	@Autowired
	private Dictionary dictionary;

    public String translate(String source) {
		String text = textSource.getText(source);
		Language language = languageDetector.detectLanguage(text);
		StringBuilder translatedText = new StringBuilder();
		Arrays.stream(text.split(" "))
				.forEach(word -> translatedText.append(dictionary.translate(word, language)));
		return translatedText.toString();
	}

}
