package com.main;

import com.lang.Language;
import com.lang.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Translator {

    @Autowired
    TextSource textSource;

    @Autowired
    LanguageDetector languageDetector;

    @Autowired
    Dictionary dictionary;

    public String translate(String source) {
        String text = textSource.getText(source);
        Language language = languageDetector.detectLanguage(text);
        StringBuilder stringBuilder = new StringBuilder();
        for(String s:text.split(" ")){
           stringBuilder.append(dictionary.translate(s,language)+" ");
        }
        return stringBuilder.toString();
    }
}
