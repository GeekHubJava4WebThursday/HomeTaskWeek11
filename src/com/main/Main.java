package com.main;

import com.lang.Language;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Map;
import java.util.StringTokenizer;

public class Main {


	public static void main(String[] args) throws BeansException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        Translator translator = context.getBean(Translator.class);

		String translation = translator.translate("E:/some_text.txt");
		System.out.println(translation);

	}
}
