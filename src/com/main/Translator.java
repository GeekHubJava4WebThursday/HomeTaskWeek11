package com.main;

import com.lang.Language;
import com.lang.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Translator {

	@Autowired
	TextSource textSource;

	@Autowired
	Dictionary dictionary;

	@Autowired
	LanguageDetector languageDetector;

    public String translate(String source) {
		String text = textSource.getText(source);
		Language language = languageDetector.detectLanguage(text);
		String textMas[] = text.split("\\s+");
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < textMas.length; i++){
			sb.append(dictionary.translate(textMas[i], language) + " ");
		}
		return sb.toString();
	}

}
