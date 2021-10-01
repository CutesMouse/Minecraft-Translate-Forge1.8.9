package com.cutesmouse.mtr;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ChatComponentText;

import java.awt.*;
import java.io.IOException;

public class OptionScreen extends GuiScreen {
    private GuiTextField URL;
    private GuiTextField PER_LINE;
    private GuiTextField MAX_LINE;
    private GuiTextField MAX_LENGTH;
    public OptionScreen() {

    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        if (URL != null) URL.drawTextBox();
        //if (PER_LINE != null) PER_LINE.drawTextBox();
        //if (MAX_LINE != null) MAX_LINE.drawTextBox();
        //if (MAX_LENGTH != null) MAX_LENGTH.drawTextBox();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int mid_x = sr.getScaledWidth()/2;
        int mid_y = (sr.getScaledHeight() / 2);
        drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "Google Script\u9023\u7D50\u7DB2\u5740",
                mid_x, mid_y - 25,new Color(255, 221, 61).hashCode());
        /*drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "\u804A\u5929\u5BA4\u7FFB\u8B6F\u6700\u5927\u5BEC\u5EA6",
                mid_x, mid_y - 50,new Color(255, 221, 61).hashCode());
        drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "\u804A\u5929\u5BA4\u7FFB\u8B6F\u6700\u5927\u884C\u6578",
                mid_x, mid_y,new Color(255, 221, 61).hashCode());
        drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "\u804A\u5929\u5BA4\u7FFB\u8B6F\u884C\u8DDD",
                mid_x, mid_y + 50,new Color(255, 221, 61).hashCode());*/
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int size_x = 200;
        int size_y = 20;
        int mid_x = (sr.getScaledWidth()/2);
        int mid_y = (sr.getScaledHeight() / 2);
        {
            URL = new GuiTextField(10007,Minecraft.getMinecraft().fontRendererObj,mid_x - (size_x / 2),
                    mid_y - (size_y / 2),size_x,size_y);
            URL.setMaxStringLength(1000);
            String text = MTranslate.getKeyURL();
            if (!text.equals("0")) URL.setText(text);
        }
        /*{
            MAX_LENGTH = new GuiTextField(10008,Minecraft.getMinecraft().fontRendererObj,mid_x - 20,
                    mid_y- (size_y/2) - 25,40,size_y);
            String text = Config.Instance.getInt("option","textboard_maxLength",DefaultValues.MAX_LENGTH)+"";
            MAX_LENGTH.setText(text);
        }
        {
            MAX_LINE = new GuiTextField(10009,Minecraft.getMinecraft().fontRendererObj,mid_x - 20,
                    mid_y- (size_y/2) + 25,40,size_y);
            String text = Config.Instance.getInt("option","textboard_maxLine",DefaultValues.MAX_LINE)+"";
            MAX_LINE.setText(text);
        }
        {
            PER_LINE = new GuiTextField(10010,Minecraft.getMinecraft().fontRendererObj,mid_x - 20,
                    mid_y- (size_y/2) + 75,40,size_y);
            String text = Config.Instance.getInt("option","textboard_perLine",DefaultValues.PER_LINE)+"";
            PER_LINE.setText(text);
        }*/
        super.initGui();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        URL.mouseClicked(mouseX, mouseY, mouseButton);
        /*PER_LINE.mouseClicked(mouseX, mouseY, mouseButton);
        MAX_LINE.mouseClicked(mouseX, mouseY, mouseButton);
        MAX_LENGTH.mouseClicked(mouseX, mouseY, mouseButton);*/
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        URL.textboxKeyTyped(typedChar, keyCode);
        /*PER_LINE.textboxKeyTyped(typedChar, keyCode);
        MAX_LENGTH.textboxKeyTyped(typedChar, keyCode);
        MAX_LINE.textboxKeyTyped(typedChar, keyCode);*/
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void onGuiClosed() {
        String text = URL.getText();
        String origin = MTranslate.getKeyURL();
        boolean save = false;
        if (!text.isEmpty() && text.contains("https") && !origin.equals(text)) {
            Config.Instance.setString("option", "keyURL", text);
            save = true;
        }
        /*text = MAX_LINE.getText();
        if (!text.isEmpty()) {
            if (Config.Instance.setInt("option", "textboard_maxLine", Integer.parseInt(text))) save = true;
        }
        text = MAX_LENGTH.getText();
        if (!text.isEmpty()) {
            if (Config.Instance.setInt("option", "textboard_maxLength", Integer.parseInt(text))) save = true;
        }
        text = PER_LINE.getText();
        if (!text.isEmpty()) {
            if (Config.Instance.setInt("option", "textboard_perLine", Integer.parseInt(text))) save = true;
        }*/
        if (save) Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00A7a\u6210\u529F\u8A2D\u5B9A!"));
        super.onGuiClosed();
    }
}
