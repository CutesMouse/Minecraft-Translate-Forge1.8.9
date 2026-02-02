package com.cutesmouse.mtr.api;

import com.cutesmouse.mtr.settings.MTRSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class Translator {
    private static HashMap<String, String> TRANSLATE_TABLE = new HashMap<String, String>();
    private final static ArrayList<String> CURRENT_TASKS = new ArrayList<String>();
    private static ArrayList<TranslateTask> recentTasks = new ArrayList<TranslateTask>();

    public static String translateOrReturn(String s) {
        // KeyURL is not correctly set
        if (!MTRSettings.getKeyURL().startsWith("https://script.google.com/macros/")) return s;

        if (!MTRSettings.isColorCodeEnabled()) s = s.replaceAll("\\u00a7(.)", "");

        if (TRANSLATE_TABLE.containsKey(s)) {
            return TRANSLATE_TABLE.get(s);
        }
        if (CURRENT_TASKS.contains(s)) {
            return s;
        }
        CURRENT_TASKS.add(s);
        final String text = s;
        new Thread(() -> {
            String result = new TranslateTask(text).getResult();
            CURRENT_TASKS.remove(result);
            System.out.println(result);
            TRANSLATE_TABLE.put(text, result);
        }).start();
        return s;
    }

    public static void refresh() {
        TRANSLATE_TABLE = new HashMap<>();
        recentTasks = new ArrayList<>();
        CURRENT_TASKS.clear();
    }

    public static void queue(TranslateTask task) {
        new ArrayList<>(recentTasks).stream().filter(p -> p.isSimiliar(task)).findFirst().ifPresent(tasks -> {
            task.similarTranslate(tasks);
            TRANSLATE_TABLE.put(task.getSource(), task.getTranslatedText());
        });
        if (task.getTranslatedText() != null) return;
        recentTasks.add(task);
        task.formalTranslate(translate(getFromLanguage(), getToLanguage(), task.getSource()));
    }

    public static String getFromLanguage() {
        String from_c = MTRSettings.getSourceLanguage();
        if (from_c.equalsIgnoreCase("auto") || from_c.equals("0")) from_c = "";
        return from_c;
    }

    public static String getToLanguage() {
        String to_c = MTRSettings.getTargetLanguage();
        if (to_c.equals("0")) to_c = "zh-TW";
        return to_c;
    }

    private static String translate(String langFrom, String langTo, String sent) {
        try {
            String urlStr = MTRSettings.getKeyURL() +
                    "?q=" + URLEncoder.encode(sent, "UTF-8") +
                    "&target=" + langTo +
                    "&source=" + langFrom;
            URL url = new URL(urlStr);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String result = response.toString().replace("\uFF08", "(")
                    .replace("\uFF03", "#")
                    .replace("\uFF05", "%")
                    .replace("\uFF09", ")")
                    .replace("&lt;", "\u003c")
                    .replace("&#39;", "\u0027")
                    .replace("&gt;", "\u003e")
                    .replace("&amp;", "\u0026");
            return result;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return "";
    }
}