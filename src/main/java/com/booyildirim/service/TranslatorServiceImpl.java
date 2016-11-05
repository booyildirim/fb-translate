package com.booyildirim.service;

import com.booyildirim.artifact.TranslateApiResponse;
import com.booyildirim.core.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("translatorService")
public class TranslatorServiceImpl implements TranslatorService {

    @Autowired
    private TranslatorRestFacade translatorRestFacade;


    @Override
    public String usage() {
        String description = "Example usage: english turkish Hello, how are you? We have support for languages: ";

        String supportedLangs = "";
        Map<String, String> codeLangMap = AppConstants.getMap();

        for (Map.Entry<String, String> stringStringEntry : codeLangMap.entrySet()) {
            supportedLangs += stringStringEntry.getKey() + " ";
        }

        return description + supportedLangs;
    }

    @Override
    public String translate(String sourceLanguage, String destinationLanguage, String text) {

        final Map<String, String> langCodesMap = AppConstants.getMap();
        final boolean srcExists = langCodesMap.containsKey(sourceLanguage.toLowerCase());
        final boolean destExists = langCodesMap.containsKey(destinationLanguage.toLowerCase());

        if (srcExists && destExists) {
            final TranslateApiResponse apiResponse =
                    translatorRestFacade.tryTranslation(langCodesMap.get(sourceLanguage),
                            langCodesMap.get(destinationLanguage),
                            text);

            return apiResponse.getText().get(0);
        }

        return "-1";
    }

}
