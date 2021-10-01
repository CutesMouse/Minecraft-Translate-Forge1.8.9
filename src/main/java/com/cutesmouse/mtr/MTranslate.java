package com.cutesmouse.mtr;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Mod(modid = MTranslate.MOD_ID,version = MTranslate.VERSION)
public class MTranslate {
    public static final String MOD_ID = "mtr";
    public static final String VERSION = "1.0";
    public static MTranslate instance;

    @Mod.EventHandler
    public void completeLoading(FMLLoadCompleteEvent e) {
        TranslateRender obj = new TranslateRender();
        Minecraft.getMinecraft().fontRendererObj = obj;
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager())
                .registerReloadListener(obj);
    }
    @Mod.EventHandler
    public void preInt(FMLPreInitializationEvent e) {
        instance = this;
        try {
            Config.Instance = new Config();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        ClientCommandHandler.instance.registerCommand(new SettingCommand());
        MinecraftForge.EVENT_BUS.register(this);
    }
    public static boolean OPEN = false;
    public static boolean OPEN_TEXT = false;
    public static boolean COLOR_CODE = true;
    /*@SubscribeEvent
    public void onRenderItem(ItemTooltipEvent e) {
        if (OPEN) Translater.GetTranslate(e.toolTip);
    }*/
    private final static Color G = new Color(175, 252, 89, 255);
    private final static Color R = new Color(255, 116, 116, 255);
    @SubscribeEvent
    public void showingGUI(GuiScreenEvent.InitGuiEvent.Post e) {
        //Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(e.gui.getClass().getName()));
        //350, 195
        int b1_x = Config.Instance.getInt("layout","toggle_x",350);
        int b1_z = Config.Instance.getInt("layout","toggle_z",218);
        int b2_x = Config.Instance.getInt("layout","color_x",350);
        int b2_z = Config.Instance.getInt("layout","color_z",195);
        int b3_x = Config.Instance.getInt("layout","refresh_x",350);
        int b3_z = Config.Instance.getInt("layout","refresh_z",172);
        /*int b5_x = Config.Instance.getInt("layout","textboard_toggle_x",350);
        int b5_z = Config.Instance.getInt("layout","textboard_toggle_z",149);*/
        if (!(e.gui instanceof GuiChat)) return;
        final GuiButton button = new GuiButton(10001,b1_x, b1_z, 80, 20, "\u7FFB\u8B6F\u6A21\u5F0F: " +(OPEN ? "\u958B" : "\u95DC")) {
            @Override
            public void mouseReleased(int mouseX, int mouseY) {
                questIfKeyNull();
                OPEN = !OPEN;
                this.displayString = "\u7FFB\u8B6F\u6A21\u5F0F: " +(OPEN ? "\u958B" : "\u95DC");
                this.packedFGColour = (OPEN ? G : R).hashCode();
            }
        };
        button.packedFGColour = (OPEN ? G : R).hashCode();
        e.buttonList.add(button);
        final GuiButton button2 = new GuiButton(10002,b2_x, b2_z, 80, 20, "\u984F\u8272\u4EE3\u78BC: " +(COLOR_CODE ? "\u958B" : "\u95DC")) {
            @Override
            public void mouseReleased(int mouseX, int mouseY) {
                questIfKeyNull();
                COLOR_CODE = !COLOR_CODE;
                this.displayString = "\u984F\u8272\u4EE3\u78BC: " +(COLOR_CODE ? "\u958B" : "\u95DC");
                this.packedFGColour = (COLOR_CODE ? G : R).hashCode();
            }
        };
        button2.packedFGColour = (COLOR_CODE ? G : R).hashCode();
        e.buttonList.add(button2);
        final GuiButton button3 = new GuiButton(10003,b3_x, b3_z, 80, 20, "\u5237\u65B0\u7FFB\u8B6F") {
            @Override
            public void mouseReleased(int mouseX, int mouseY) {
                questIfKeyNull();
                Translater.TRANSLATE_TABLE = new HashMap<>();
                Translater.recentTasks = new ArrayList<>();
            }
        };
        button3.packedFGColour = new Color(255, 223, 142).hashCode();
        e.buttonList.add(button3);

        /*final GuiButton button5 = new GuiButton(10005,b5_x, b5_z, 80, 20, "\u804A\u5929\u5BA4\u7FFB\u8B6F: " +(OPEN_TEXT ? "\u958B" : "\u95DC")) {
            @Override
            public void mouseReleased(int mouseX, int mouseY) {
                questIfKeyNull();
                OPEN_TEXT = !OPEN_TEXT;
                this.displayString = "\u804A\u5929\u5BA4\u7FFB\u8B6F: " +(OPEN_TEXT ? "\u958B" : "\u95DC");
                this.packedFGColour = (OPEN_TEXT ? G : R).hashCode();
            }
        };
        button5.packedFGColour = (OPEN_TEXT ? G : R).hashCode();
        e.buttonList.add(button5);*/
        //
    }
    private void questIfKeyNull() {
        if (!getKeyURL().equals("0")) return;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00A7c\u7CFB\u7D71\u5075\u6E2C\u5230\u4F60\u9084\u6C92\u6709\u8A2D\u5B9AAPI URL"));
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00A7c\u8ACB\u8F38\u5165 /tr link \u4F86\u67E5\u8A62\u5982\u4F55\u8A2D\u5B9A!"));
    }
    public static String getKeyURL() {
        return Config.Instance.getString("option","keyURL");
    }
    /*@SubscribeEvent
    public void receiveMSG(ClientChatReceivedEvent e) {
        if (!OPEN_TEXT) return;
        if (getKeyURL().equals("0")) return;
        if (e.type == 2) return;
        final String t = e.message.getFormattedText();
        new Thread(new Runnable() {
            @Override
            public void run() {
                TextBoard.appendText(Translater.SingleTranslate(t));
            }
        }).start();
    }*/
    /*@SubscribeEvent
    public void onRenderTextBoard(RenderGameOverlayEvent.Post e) {
        if (!OPEN_TEXT) return;
        if (!e.type.equals(RenderGameOverlayEvent.ElementType.EXPERIENCE)) return;
        new TextBoard();
    }*/
}
