package com.cutesmouse.mtr.settings;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.io.IOException;

public class MTRConfig {
    private Configuration config;

    public static MTRConfig load() {
        try {
            return new MTRConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private MTRConfig() throws IOException {
        File configFile = new File(Loader.instance().getConfigDir(),"MTranslate.yml");
        if (!configFile.exists()) {
            configFile.createNewFile();
        }
        config = new Configuration(configFile);
        config.load();
    }
    public String getString(String type, String key, String defaultValue) {
        return config.get(type, key, defaultValue).getString();
    }
    public int getInt(String type, String key, int def) {
        return config.get(type,key,def).getInt();
    }
    public void setString(String type, String key, String value) {
        config.get(type,key,"0").set(value);
        if (config.hasChanged()) config.save();
    }
    public boolean setInt(String type, String key, int value) {
        config.get(type,key,0).set(value);
        if (config.hasChanged()) {
            config.save();
            return true;
        }
        return false;
    }
}
