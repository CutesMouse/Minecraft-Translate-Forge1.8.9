package com.cutesmouse.mtr;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.io.IOException;

public class Config {
    public static Config Instance;
    private Configuration config;
    public Config() throws IOException {
        File configFile = new File(Loader.instance().getConfigDir(),"MTranslate.yml");
        if (!configFile.exists()) {
            configFile.createNewFile();
        }
        config = new Configuration(configFile);
        config.load();
    }
    public String getString(String type, String key) {
        return config.get(type, key, "0").getString();
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
