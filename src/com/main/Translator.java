package com.main;

import com.lang.Language;
import com.lang.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Translator {

	TextSource textSource;
	LanguageDetector languageDetector;
	Dictionary dictionary;

	@Autowired
	public Translator(TextSource textSource, LanguageDetector languageDetector, Dictionary dictionary) {
		this.textSource = textSource;
		this.languageDetector = languageDetector;
		this.dictionary = dictionary;
	}

	public String translate(String source) {
		StringBuilder translatedText = new StringBuilder();
		String text = textSource.getText(source);
		Language language = languageDetector.detectLanguage(text);

		for (String word : text.split("\\s+")) {
			translatedText
					.append(dictionary.translate(word.trim(), language))
					.append(" ");
		}

		return translatedText.toString();
	}

}
