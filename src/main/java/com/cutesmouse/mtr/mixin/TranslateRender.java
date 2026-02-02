package com.cutesmouse.mtr.mixin;

import com.cutesmouse.mtr.MTranslate;
import com.cutesmouse.mtr.api.Translator;
import com.cutesmouse.mtr.settings.MTRSettings;
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
        if (!MTRSettings.isActive()) return super.drawString(text, x, y, color, dropShadow);
        return super.drawString(Translator.translateOrReturn(text), x, y, color, dropShadow);
    }

    @Override
    public int getStringWidth(String text) {
        if (!MTRSettings.isActive()) return super.getStringWidth(text);
        return super.getStringWidth(Translator.translateOrReturn(text));
    }
}
