package com.cutesmouse.mtr.screen;

import com.cutesmouse.mtr.api.Translator;
import com.cutesmouse.mtr.settings.MTRSettings;
import com.cutesmouse.mtr.utils.DelayedTask;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class MTRSettingScreen extends GuiScreen {

    private GuiTextField apiKeyField;
    private static final int ROW_SPACING = 24;
    private static final ResourceLocation LOGO_TEXTURE = new ResourceLocation("mtr", "textures/gui/banner.png");

    // 定義按鈕 ID (1.8.9 需要用 ID 來判斷點擊事件)
    private static final int BUTTON_MAIN_SWITCH = 0;
    private static final int BUTTON_REFRESH = 1;
    private static final int BUTTON_COLOR_SWITCH = 2;
    private static final int BUTTON_SOURCE_LANG = 3;
    private static final int BUTTON_TARGET_LANG = 4;
    private static final int BUTTON_DONE = 5;

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();

        int centerX = this.width / 2;
        int startY = 110;

        // 總開關按鈕
        String mainSwitchText = DisplayUtils.getMainSwitchDisplay();
        this.buttonList.add(new GuiButton(BUTTON_MAIN_SWITCH, centerX - 98 - 2, startY, 98, 20, mainSwitchText));

        // 刷新翻譯
        this.buttonList.add(new GuiButton(BUTTON_REFRESH, centerX + 2, startY, 98, 20, I18n.format("mtr.text.option.refresh")));

        // API Key 輸入框位置計算
        startY += ROW_SPACING;

        // API Key 輸入框
        int labelHeight = this.fontRendererObj.FONT_HEIGHT;
        this.apiKeyField = new GuiTextField(6, this.fontRendererObj, centerX - 100, startY + labelHeight + 2, 200, 20);
        this.apiKeyField.setMaxStringLength(128);
        this.apiKeyField.setText(MTRSettings.getKeyURL());

        // 格式開關
        startY += ROW_SPACING + labelHeight + 2;
        String colorSwitchText = DisplayUtils.getColorSwitchDisplay();
        this.buttonList.add(new GuiButton(BUTTON_COLOR_SWITCH, centerX - 100, startY, 200, 20, colorSwitchText));

        // 語言設定按鈕
        startY += ROW_SPACING;

        // 來源語言
        String sourceLangText = DisplayUtils.getSourceLangDisplay();
        this.buttonList.add(new GuiButton(BUTTON_SOURCE_LANG, centerX - 98 - 2, startY, 98, 20, sourceLangText));

        // 目標語言
        String targetLangText = DisplayUtils.getTargetLangDisplay();
        this.buttonList.add(new GuiButton(BUTTON_TARGET_LANG, centerX + 2, startY, 98, 20, targetLangText));

        // 底部關閉按鈕
        this.buttonList.add(new GuiButton(BUTTON_DONE, centerX - 100, this.height - 32, 200, 20, I18n.format("gui.done")));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        // 繪製 Logo
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(LOGO_TEXTURE);
        drawModalRectWithCustomSizedTexture(this.width / 2 - 45, 13, 0, 0, 90, 90, 90, 90);

        // 繪製 API Key 的標籤文字
        int centerX = this.width / 2;
        int startY = 110 + ROW_SPACING;
        this.drawString(this.fontRendererObj, I18n.format("mtr.text.option.keyurl"), centerX - this.apiKeyField.getWidth() / 2, startY, 0x9A9A9A);

        // 繪製輸入框
        this.apiKeyField.drawTextBox();

        // 繪製按鈕 (super.drawScreen 會遍歷 buttonList 並繪製)
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case BUTTON_MAIN_SWITCH:
                MTRSettings.setActive(!MTRSettings.isActive());
                button.displayString = DisplayUtils.getMainSwitchDisplay();
                break;

            case BUTTON_REFRESH:
                Translator.refresh();
                break;

            case BUTTON_COLOR_SWITCH:
                MTRSettings.setColorCode(!MTRSettings.isColorCodeEnabled());
                button.displayString = DisplayUtils.getColorSwitchDisplay();
                break;

            case BUTTON_SOURCE_LANG:
                this.mc.displayGuiScreen(
                        new LanguageSelectScreen(this,
                                StatCollector.translateToLocal("mtr.text.option.language.set.source"),
                                () -> LanguageManager.getSelected(true),
                                lang -> MTRSettings.setSourceLang(lang.code()), true));
                break;

            case BUTTON_TARGET_LANG:
                this.mc.displayGuiScreen(
                        new LanguageSelectScreen(this,
                                StatCollector.translateToLocal("mtr.text.option.language.set.target"),
                                () -> LanguageManager.getSelected(false),
                                lang -> MTRSettings.setTargetLang(lang.code()), false));
                break;

            case BUTTON_DONE:
                if (!MTRSettings.getKeyURL().equals(this.apiKeyField.getText())) {
                    MTRSettings.setKeyUrl(this.apiKeyField.getText());
                }
                this.mc.displayGuiScreen(null);
                break;
        }
    }

    // 處理鍵盤
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (this.apiKeyField.isFocused()) {
            this.apiKeyField.textboxKeyTyped(typedChar, keyCode);
        }
        // 按下 ESC 關閉介面
        super.keyTyped(typedChar, keyCode);
    }

    // 處理滑鼠點擊 (選中輸入框)
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.apiKeyField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    // 每一幀更新 (讓輸入框的光標閃爍)
    @Override
    public void updateScreen() {
        super.updateScreen();
        this.apiKeyField.updateCursorCounter();
    }

    @Override
    public void onGuiClosed() {
        // 介面關閉時觸發
        Keyboard.enableRepeatEvents(false);
    }
}