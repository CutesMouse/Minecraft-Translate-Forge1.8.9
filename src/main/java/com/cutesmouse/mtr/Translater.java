package com.cutesmouse.mtr;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Translater {
    public static HashMap<String, String> TRANSLATE_TABLE = new HashMap<String, String>();
    private final static ArrayList<String> CURRENT_TASKS = new ArrayList<String>();
    public static ArrayList<TranslateTask> recentTasks = new ArrayList<TranslateTask>();

    public static String translateOrReturn(String s) {
        if (MTranslate.getKeyURL().equals("0")) return s;

        if (!MTranslate.COLOR_CODE) s = s.replaceAll("\\u00a7(.)", "");

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
            TRANSLATE_TABLE.put(text, result);
        }).start();
        return s;
    }

    public static void queue(TranslateTask task) {
        //recentTasks.removeIf(p -> current - task.getTiming() > 3000);
        new ArrayList<>(recentTasks).stream().filter(p -> p.isSimiliar(task)).findFirst().ifPresent(tasks -> {
            task.similarTranslate(tasks);
            TRANSLATE_TABLE.put(task.getSource(), task.getTranslatedText());
        });
        if (task.getTranslatedText() != null) return;
        recentTasks.add(task);
        task.formalTranslate(translate(getFromLanguage(), getToLanguage(), task.getSource()));
    }

    public static String getFromLanguage() {
        String from_c = Config.Instance.getString("source", "lang");
        if (from_c.equalsIgnoreCase("auto") || from_c.equals("0")) from_c = "";
        return from_c;
    }

    public static String getToLanguage() {
        String to_c = Config.Instance.getString("target", "lang");
        if (to_c.equals("0")) to_c = "zh-TW";
        return to_c;
    }

    private static String translate(String langFrom, String langTo, String sent) {
        System.out.println("DOING new translate -> " + sent);
        try {
            String urlStr = MTranslate.getKeyURL() +
                    "?q=" + URLEncoder.encode(sent, "UTF-8") +
                    "&target=" + langTo +
                    "&source=" + langFrom;
            URL url = new URL(urlStr);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String code = "UTF-8";
            String result = new String(response.toString().replace("\uFF08", "(")
                    .replace("\uFF03", "#")
                    .replace("\uFF05", "%")
                    .replace("\uFF09", ")")
                    .replace("&lt;", "\u003c")
                    .replace("&#39;", "\u0027")
                    .replace("&gt;", "\u003e")
                    .replace("&amp;", "\u0026")
                    .getBytes(code), code);
            System.out.println(result);
            return result;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return "";
    }
}
