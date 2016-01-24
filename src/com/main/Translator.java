package com.main;

import com.enums.Language;
import com.lang.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Translator {

    @Autowired
    LanguageDetector languageDetector;
    @Autowired
    Dictionary dictionary;
    @Autowired
    TextSource textSource;

    public String translate(String source) {
        String text = textSource.getText(source);
        Language language = languageDetector.detectLanguage(text);
        StringBuilder translation = new StringBuilder();
        for (String word : text.split("\\W")) {
            translation.append(dictionary.translate(word, language)).append(" ");
        }
        return translation.toString();
    }

}
