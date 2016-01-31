package com.main;

import com.lang.Language;
import com.lang.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.StringJoiner;

@Component
public class Translator {

    private static String WORD_DELIMITER = " ";

    @Autowired
    private TextSource textSource;

    @Autowired
    private Dictionary dictionary;

    @Autowired
    private LanguageDetector languageDetector;

    public String translate(String source) {
        String text = textSource.getText(source);
        Language fromLanguage = languageDetector.detectLanguage(text);
        String[] words = text.split(WORD_DELIMITER);
        StringJoiner translatedText = new StringJoiner(WORD_DELIMITER);
        for (String word:words){
            String punctuation = word.replaceAll("[^\\W]|_", "");
            String translatedWord = dictionary.translate(word.replaceAll("[\\W]|_", ""),fromLanguage);
            translatedText.add(translatedWord+punctuation);
        }
        return translatedText.toString();
	}

}
