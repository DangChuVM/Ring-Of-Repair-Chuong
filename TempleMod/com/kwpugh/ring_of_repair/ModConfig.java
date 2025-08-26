/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  blue.endless.jankson.Jankson
 *  blue.endless.jankson.JsonElement
 *  blue.endless.jankson.JsonObject
 *  blue.endless.jankson.api.DeserializationException
 *  blue.endless.jankson.api.SyntaxError
 *  net.fabricmc.loader.api.FabricLoader
 */
package com.kwpugh.ring_of_repair;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.DeserializationException;
import blue.endless.jankson.api.SyntaxError;
import com.kwpugh.ring_of_repair.ModConfigSettings;
import com.kwpugh.ring_of_repair.RingOfRepair;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Map;
import net.fabricmc.loader.api.FabricLoader;

public class ModConfig {
    private static String MOD_ID = "ring_of_repair";
    private static String CONFIG_FILENAME = "/ring_repair_config_settings.json5";
    private static ModConfigSettings SETTINGS = new ModConfigSettings();

    public static void setup() {
        Path configPath = ModConfig.getModConfigPath();
        try {
            Files.createDirectories(configPath, new FileAttribute[0]);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        File configFile = new File(String.valueOf(configPath) + CONFIG_FILENAME);
        if (configFile.exists()) {
            RingOfRepair.LOGGER.always().log("{} config settings json5 found", (Object)MOD_ID);
            ModConfig.validateConfigFile();
        } else {
            RingOfRepair.LOGGER.always().log("{} config settings json5 not found, creating default", (Object)MOD_ID);
            ModConfig.createDefaultConfig();
        }
        SETTINGS = ModConfig.getConfigFile();
    }

    public static ModConfigSettings getConfig() {
        return SETTINGS;
    }

    private static void validateConfigFile() {
        Jankson jankson = Jankson.builder().build();
        Path configPath = ModConfig.getModConfigPath();
        File configFile = new File(String.valueOf(configPath) + CONFIG_FILENAME);
        if (configFile.exists()) {
            JsonObject configFileData;
            try {
                configFileData = jankson.load(configFile);
            }
            catch (SyntaxError | IOException e) {
                throw new RuntimeException(e);
            }
            JsonElement jsonElement = jankson.toJson((Object)new ModConfigSettings());
            if (jsonElement instanceof JsonObject) {
                JsonObject configDefault = (JsonObject)jsonElement;
                for (Map.Entry entry : configDefault.entrySet()) {
                    if (configFileData.containsKey(entry.getKey())) continue;
                    configFileData.putDefault((String)entry.getKey(), (JsonElement)entry.getValue(), null);
                    RingOfRepair.LOGGER.always().log("config key added: " + (String)entry.getKey());
                }
                for (Map.Entry entry : configFileData.entrySet()) {
                    if (configDefault.containsKey(entry.getKey())) continue;
                    configFileData.remove(entry.getKey(), entry.getValue());
                    RingOfRepair.LOGGER.always().log("config key removed: " + (String)entry.getKey());
                }
                try {
                    SETTINGS = (ModConfigSettings)jankson.fromJsonCarefully(configFileData, ModConfigSettings.class);
                }
                catch (DeserializationException e) {
                    throw new RuntimeException(e);
                }
                ModConfig.refreshConfigFile(configFile, SETTINGS);
            }
        }
    }

    private static void refreshConfigFile(File configFile, ModConfigSettings settings) {
        Jankson jankson = Jankson.builder().build();
        String result = jankson.toJson((Object)settings).toJson(true, true);
        try {
            FileOutputStream out = new FileOutputStream(configFile, false);
            out.write(result.getBytes());
            out.flush();
            out.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void createDefaultConfig() {
        Path configPath = ModConfig.getModConfigPath();
        File configFile = new File(String.valueOf(configPath) + CONFIG_FILENAME);
        try {
            Files.createDirectories(configPath, new FileAttribute[0]);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        RingOfRepair.LOGGER.always().log("{} default config created at: " + String.valueOf(configFile), (Object)MOD_ID);
        Jankson jankson = Jankson.builder().build();
        String result = jankson.toJson((Object)new ModConfigSettings()).toJson(true, true);
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(configFile, false);
            out.write(result.getBytes());
            out.flush();
            out.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static ModConfigSettings getConfigFile() {
        Path configPath = ModConfig.getModConfigPath();
        Jankson jankson = Jankson.builder().build();
        File configFile = new File(String.valueOf(configPath) + CONFIG_FILENAME);
        try {
            JsonObject configJson = jankson.load(configFile);
            SETTINGS = (ModConfigSettings)jankson.fromJson(configJson, ModConfigSettings.class);
        }
        catch (SyntaxError | IOException ex) {
            throw new RuntimeException(ex);
        }
        return SETTINGS;
    }

    private static Path getModConfigPath() {
        return FabricLoader.getInstance().getConfigDir().resolve(MOD_ID);
    }
}

