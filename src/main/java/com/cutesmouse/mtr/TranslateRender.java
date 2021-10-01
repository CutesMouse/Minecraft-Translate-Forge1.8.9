package com.cutesmouse.mtr;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class TranslateRender extends FontRenderer {
    public TranslateRender() {
        super(Minecraft.getMinecraft().gameSettings,
                new ResourceLocation("textures/font/ascii.png"),
                Minecraft.getMinecraft().renderEngine, false);
    }

    @Override
    public int drawString(String text, float x, float y, int color, boolean dropShadow) {
        if (!MTranslate.OPEN) return super.drawString(text, x, y, color, dropShadow);
        return super.drawString(Translater.translateOrReturn(text), x, y, color, dropShadow);
    }

    @Override
    public int getStringWidth(String text) {
        if (!MTranslate.OPEN) return super.getStringWidth(text);
        return super.getStringWidth(Translater.translateOrReturn(text));
    }
}
