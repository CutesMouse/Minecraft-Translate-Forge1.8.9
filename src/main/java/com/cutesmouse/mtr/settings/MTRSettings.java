package com.cutesmouse.mtr.settings;

public class MTRSettings {

    private static MTRConfig config;

    public static String getKeyURL() {
        if (config == null) config = MTRConfig.load();
        return config.getString("option", "keyURL", "");
    }

    public static boolean isColorCodeEnabled() {
        if (config == null) config = MTRConfig.load();
        return config.getString("option", "color_code", "true").equals("true");
    }

    public static String getSourceLanguage() {
        if (config == null) config = MTRConfig.load();
        return config.getString("source", "lang", "auto");
    }

    public static String getTargetLanguage() {
        if (config == null) config = MTRConfig.load();
        return config.getString("target", "lang", "zh-TW");
    }

    public static boolean isActive() {
        if (config == null) config = MTRConfig.load();
        return config.getString("option", "active", "false").equals("true");
    }

    public static void setActive(boolean active) {
        if (config == null) config = MTRConfig.load();
        config.setString("option", "active", Boolean.toString(active));
    }

    public static void setColorCode(boolean color_code) {
        if (config == null) config = MTRConfig.load();
        config.setString("option", "color_code", Boolean.toString(color_code));
    }

    public static void setKeyUrl(String key_url) {
        if (config == null) config = MTRConfig.load();
        config.setString("option", "keyURL", key_url);
    }

    public static void setSourceLang(String source_lang) {
        if (config == null) config = MTRConfig.load();
        config.setString("source", "lang", source_lang);
    }

    public static void setTargetLang(String target_lang) {
        if (config == null) config = MTRConfig.load();
        config.setString("target", "lang", target_lang);
    }
}