package com.booyildirim.service;

public interface TranslatorService {
    String usage();
    String translate(String sourceLanguage, String destinationLanguage, String text);
}
