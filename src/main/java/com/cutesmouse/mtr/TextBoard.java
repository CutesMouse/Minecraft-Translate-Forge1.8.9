package com.cutesmouse.mtr;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.util.*;

public class TextBoard extends Gui {
    public static int MAX_LENGTH = 140;
    public static int MAX_LINE = 10;
    public static int PER_LINE = 10;
    public static void reInitSize() {
        MAX_LINE = Config.Instance.getInt("option","textboard_maxLine",DefaultValues.MAX_LINE);
        PER_LINE = Config.Instance.getInt("option","textboard_perLine",DefaultValues.PER_LINE);
        MAX_LENGTH = Config.Instance.getInt("option","textboard_maxLength",DefaultValues.MAX_LENGTH);
    }
    private static int X;
    private static int Y;
    private static LinkedList<MSGData> board;
    public TextBoard() {
        init();
    }
    private synchronized void init() {
        if (board == null) return;
        X = Config.Instance.getInt("layout","translateTextboard_x",367);
        Y = Config.Instance.getInt("layout","translateTextboard_z",15);
        try {
            while (board.size() > 0 && System.currentTimeMillis() - board.getFirst().time > 7000) {
                board.removeFirst();
            }
            int line = 0;
            for (MSGData text : board) {
                drawString(Minecraft.getMinecraft().fontRendererObj, text.msg, X, Y + (line * PER_LINE), new Color(255, 157, 225, 203).hashCode());
                line++;
            }
        } catch (ConcurrentModificationException e) {

        }
    }
    public static void appendText(String t) {
        if (board == null) board = new LinkedList<MSGData>();
        StringBuilder builder = new StringBuilder();
        reInitSize();
        for (char c : t.toCharArray()) {
            if (Minecraft.getMinecraft().fontRendererObj.getStringWidth(builder.toString()+c) >= MAX_LENGTH) {
                board.add(new MSGData(builder.toString(),System.currentTimeMillis()));
                builder = new StringBuilder();
            }
            builder.append(c);
        }
        board.add(new MSGData(builder.toString(),System.currentTimeMillis()));
        while (board.size() > MAX_LINE) {
            board.removeFirst();
        }
    }
    private static class MSGData {
        private String msg;
        private Long time;
        public MSGData(String msg, Long time) {
            this.msg = msg;
            this.time = time;
        }
    }
}
