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
    public static HashMap<String,String> TRANSLATE_TABLE = new HashMap<String, String>();
    private static ArrayList<String> CURRENT_TASKS = new ArrayList<String>();
    public static void GetTranslate(List<String> lores) {
        if (MTranslate.getKeyURL().equals("0")) return;
        ArrayList<String> newList = new ArrayList<String>(lores);
        lores.clear();
        for (String s : newList) {
            if (TRANSLATE_TABLE.containsKey(s)) {
                lores.add(TRANSLATE_TABLE.get(s));
                continue;
            }
            lores.add(s);
            if (CURRENT_TASKS.contains(s)) {
                continue;
            }
            CURRENT_TASKS.add(s);
            final String text = s;
            String from_c = Config.Instance.getString("source","lang");
            if (from_c.equalsIgnoreCase("auto") || from_c.equals("0")) from_c = "";
            String to_c = Config.Instance.getString("target","lang");
            if (to_c.equals("0")) to_c = "zh-TW";
            final String from = from_c;
            final String to = to_c;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        translate(from,to,text);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    public static String SingleTranslate(String s) {
        if (TRANSLATE_TABLE.containsKey(s)) {
            return TRANSLATE_TABLE.get(s);
        }
        String from_c = Config.Instance.getString("source", "lang");
        if (from_c.equalsIgnoreCase("auto") || from_c.equals("0")) from_c = "";
        String to_c = Config.Instance.getString("target", "lang");
        if (to_c.equals("0")) to_c = "zh-TW";
        try {
            String r = translate(from_c, to_c, s);
            TRANSLATE_TABLE.put(s, r);
            return r;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "\u00A7c\u7FFB\u8B6F\u932F\u8AA4!";
    }
    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        if (text.contains("minecraft:")) {
            CURRENT_TASKS.remove(text);
            TRANSLATE_TABLE.put(text,text);
            return text;
        }
        String sent = text;
        if (!MTranslate.COLOR_CODE) sent = text.replaceAll("\\u00a7(.)","");
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
        String result = new String(response.toString().replace("\uFF08","(")
                .replace("\uFF03","#")
                .replace("\uFF05","%")
                .replace("\uFF09",")")
                .replace("&lt;","\u003c")
                .replace("&#39;","\u0027")
                .replace("&gt;","\u003e")
                .replace("&amp;","\u0026")
                .getBytes(code),code);
        CURRENT_TASKS.remove(text);
        TRANSLATE_TABLE.put(text,result);
        return result;
    }
}
