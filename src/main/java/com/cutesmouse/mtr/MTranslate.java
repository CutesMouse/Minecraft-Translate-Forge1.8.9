package com.cutesmouse.mtr;

import com.cutesmouse.mtr.commands.SettingCommand;
import com.cutesmouse.mtr.keybind.MainToggleKeyBind;
import com.cutesmouse.mtr.mixin.TranslateRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MTranslate.MOD_ID, version = MTranslate.VERSION)
public class MTranslate {
    public static final String MOD_ID = "mtr";
    public static final String VERSION = "1.3";
    public static MTranslate instance;

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        ClientCommandHandler.instance.registerCommand(new SettingCommand());
    }

    @Mod.EventHandler
    public void preInt(FMLPreInitializationEvent e) {
        instance = this;
        MinecraftForge.EVENT_BUS.register(new MainToggleKeyBind());
        MainToggleKeyBind.init();
    }

    @Mod.EventHandler
    public void completeLoading(FMLLoadCompleteEvent e) {
        TranslateRender after = new TranslateRender();
        Minecraft.getMinecraft().fontRendererObj = after;
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager())
                .registerReloadListener(after);
    }
}
