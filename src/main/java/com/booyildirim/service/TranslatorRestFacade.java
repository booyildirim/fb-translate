package com.booyildirim.service;

import com.booyildirim.artifact.TranslateApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class TranslatorRestFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(TranslatorRestFacade.class);

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_KEY =
            "trnsl.1.1.20161011T110811Z.25bb80f4c2c7b9c0.f8028a1479cb0ed860680371b7dae7b4f7268d65";
    //System.getProperty("TRANSLATION_API_KEY");
    private static final String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    // System.getProperty("TRANSLATION_API_URL");
    private static String API_URL = BASE_URL + "?key=" + API_KEY;

    public TranslateApiResponse tryTranslation(String sourceCode, String destCode, String text) {
        LOGGER.info("action through: " + API_URL);
        final String langparam = sourceCode + "-" + destCode;
        API_URL = API_URL + "&lang=" + langparam;
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("text", text);

        TranslateApiResponse translateApiResponse;

        try {
            translateApiResponse = restTemplate.postForObject(API_URL, map, TranslateApiResponse.class);
        } catch (Exception e) {
            translateApiResponse = new TranslateApiResponse();
        }
        return translateApiResponse;
    }

}
