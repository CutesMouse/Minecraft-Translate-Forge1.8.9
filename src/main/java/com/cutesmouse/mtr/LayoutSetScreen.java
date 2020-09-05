package com.cutesmouse.mtr;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.io.IOException;

import static com.cutesmouse.mtr.MTranslate.*;

public class LayoutSetScreen extends GuiScreen {
    public LayoutSetScreen() {

    }
    private final static Color G = new Color(175, 252, 89, 255);
    private final static Color R = new Color(255, 116, 116, 255);
    private DetectObject TextboardPosition;
    private int mouseX;
    private int mouseY;
    private int dx;
    private int dy;
    private Object SELECTED;
    //private ArrayList<GuiTextField> texts = new ArrayList<GuiTextField>();
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        if (SELECTED != null) {
            if (SELECTED instanceof GuiButton) {
                for (GuiButton b : buttonList) {
                    if (((GuiButton) SELECTED).id == b.id) {
                        b.xPosition = mouseX - dx;
                        b.yPosition = mouseY - dy;
                        break;
                    }
                }
            }
            if (SELECTED instanceof DetectObject) {
                TextboardPosition.x = mouseX - dx;
                TextboardPosition.y = mouseY - dy;
            }
        }
        if (TextboardPosition != null) {
            drawRect(TextboardPosition.x, TextboardPosition.y,
                    TextboardPosition.right(), TextboardPosition.bottom(), new Color(142, 251, 255, 149).hashCode());
            {
                GlStateManager.pushMatrix();
                String print = "\u804A\u5929\u5BA4\u7FFB\u8B6F\u6587\u5B57";
                double printSize = fontRendererObj.getStringWidth(print) * 1.5 * 0.5;
                GlStateManager.translate(TextboardPosition.x + (TextboardPosition.width/2F) - printSize, TextboardPosition.y+TextBoard.MAX_LINE*TextBoard.PER_LINE*0.5-5,0);
                GlStateManager.scale(1.5,1.5,1.5);
                Minecraft.getMinecraft().fontRendererObj.drawString(print,
                        0,0, new Color(0, 74, 255).hashCode(), false);
                GlStateManager.popMatrix();
            }
        }
        /*for (GuiTextField text : texts) {
            text.drawTextBox();
        }*/
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        super.initGui();
        /*{
            GuiTextField text = new GuiTextField(10005,Minecraft.getMinecraft().fontRendererObj,8,186,30,20);
            text.setText();
        }*/
        TextBoard.reInitSize();
        int b1_x = Config.Instance.getInt("layout","toggle_x",350);
        int b1_z = Config.Instance.getInt("layout","toggle_z",218);
        int b2_x = Config.Instance.getInt("layout","color_x",350);
        int b2_z = Config.Instance.getInt("layout","color_z",195);
        int b3_x = Config.Instance.getInt("layout","refresh_x",350);
        int b3_z = Config.Instance.getInt("layout","refresh_z",172);
        int b4_x = Config.Instance.getInt("layout","translateTextboard_x",350);
        int b4_z = Config.Instance.getInt("layout","translateTextboard_z",172);
        int b5_x = Config.Instance.getInt("layout","textboard_toggle_x",350);
        int b5_z = Config.Instance.getInt("layout","textboard_toggle_z",149);
        {
            this.TextboardPosition = new DetectObject(b4_x,b4_z, TextBoard.MAX_LENGTH,TextBoard.MAX_LINE*TextBoard.PER_LINE);
        }
        {
            final GuiButton button = new GuiButton(10001,b1_x, b1_z, 80, 20, "\u7FFB\u8B6F\u6A21\u5F0F: " +(OPEN ? "\u958B" : "\u95DC"));
            button.packedFGColour = (OPEN ? G : R).hashCode();
            buttonList.add(button);
        }
        {
            final GuiButton button = new GuiButton(10002,b2_x, b2_z, 80, 20, "\u984F\u8272\u4EE3\u78BC: " +(COLOR_CODE ? "\u958B" : "\u95DC"));
            button.packedFGColour = (COLOR_CODE ? G : R).hashCode();
            buttonList.add(button);
        }
        {
            final GuiButton button = new GuiButton(10003,b3_x, b3_z, 80, 20, "\u5237\u65B0\u7FFB\u8B6F");
            button.packedFGColour = new Color(255, 223, 142).hashCode();
            buttonList.add(button);
        }
        {
            final GuiButton button = new GuiButton(10005,b5_x, b5_z, 80, 20, "\u804A\u5929\u5BA4\u7FFB\u8B6F: " +(OPEN_TEXT ? "\u958B" : "\u95DC"));
            button.packedFGColour = (OPEN_TEXT ? G : R).hashCode();
            buttonList.add(button);
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        dx = mouseX - button.xPosition;
        dy = mouseY - button.yPosition;
        SELECTED = button;
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        SELECTED = null;
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (TextboardPosition != null && TextboardPosition.isHover(mouseX,mouseY)) {
            dx = mouseX - TextboardPosition.x;
            dy = mouseY - TextboardPosition.y;
            SELECTED = TextboardPosition;
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onGuiClosed() {
        for (GuiButton b : buttonList) {
            switch (b.id) {
                case 10001:
                    Config.Instance.setInt("layout","toggle_x",b.xPosition);
                    Config.Instance.setInt("layout","toggle_z",b.yPosition);
                    break;
                case 10002:
                    Config.Instance.setInt("layout","color_x",b.xPosition);
                    Config.Instance.setInt("layout","color_z",b.yPosition);
                    break;
                case 10003:
                    Config.Instance.setInt("layout","refresh_x",b.xPosition);
                    Config.Instance.setInt("layout","refresh_z",b.yPosition);
                    break;
                case 10005:
                    Config.Instance.setInt("layout","textboard_toggle_x",b.xPosition);
                    Config.Instance.setInt("layout","textboard_toggle_z",b.yPosition);
                    break;
            }
        }
        Config.Instance.setInt("layout","translateTextboard_x",TextboardPosition.x);
        Config.Instance.setInt("layout","translateTextboard_z",TextboardPosition.y);
    }


}
