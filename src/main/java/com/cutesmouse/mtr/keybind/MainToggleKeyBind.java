package com.cutesmouse.mtr.keybind;

import com.cutesmouse.mtr.settings.MTRSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class MainToggleKeyBind {

    // 宣告按鍵變數
    public static KeyBinding openSettingsKey;

    // 初始化並註冊按鍵 (這個方法要在主類別呼叫)
    public static void init() {
        // 參數說明:
        // 1. 語言檔的 key (用於顯示名稱)
        // 2. 預設按鍵 (來自 org.lwjgl.input.Keyboard)
        // 3. 設定選單中的分類名稱
        openSettingsKey = new KeyBinding("key.mtr.main", 0, "key.categories.mtr");

        // 向 Forge 註冊這個按鍵
        ClientRegistry.registerKeyBinding(openSettingsKey);
    }

    // 監聽按鍵輸入事件
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent e) {
        if (!openSettingsKey.isPressed()) return;
        MTRSettings.setActive(!MTRSettings.isActive());
        if (MTRSettings.isActive()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(
                    new ChatComponentText("§6[MTR] §f" + StatCollector.translateToLocal("mtr.text.keybinding.toggle.on")));
        } else {
            Minecraft.getMinecraft().thePlayer.addChatMessage(
                    new ChatComponentText("§6[MTR] §f" + StatCollector.translateToLocal("mtr.text.keybinding.toggle.off")));
        }
    }
}
