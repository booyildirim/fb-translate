package com.booyildirim.core;


import java.util.HashMap;
import java.util.Map;

public final class AppConstants {

    private AppConstants() {
    }

    private static final Map<String, String> CODE_LANG_MAP = new HashMap<String, String>();

    static {
        // TODO: 14/10/2016 add config-style language-code spec
        // german, english, turkish for demo only
        CODE_LANG_MAP.put("english", "en");
        CODE_LANG_MAP.put("turkish", "tr");
        CODE_LANG_MAP.put("german", "de");
    }


    public static Map<String, String> getMap() {
        return CODE_LANG_MAP;
    }

}
