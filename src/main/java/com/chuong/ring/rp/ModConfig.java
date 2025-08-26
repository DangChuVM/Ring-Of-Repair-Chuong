package com.chuong.ring.rp;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.DeserializationException;
import blue.endless.jankson.api.SyntaxError;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ModConfig {
    // Use the MOD_ID from the main class for consistency
    private static final String MOD_ID = RingOfRepair.MOD_ID;
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID);
    private static final File CONFIG_FILE = new File(CONFIG_PATH.toFile(), "ring_repair_config_settings.json5");
    private static ModConfigSettings SETTINGS = new ModConfigSettings();
    private static final Jankson JANKSON = Jankson.builder().build();

    public static void setup() {
        try {
            Files.createDirectories(CONFIG_PATH);
            if (CONFIG_FILE.exists()) {
                RingOfRepair.LOGGER.info("Config file found, validating...");
                validateConfigFile();
            } else {
                RingOfRepair.LOGGER.info("Config file not found, creating default.");
                createDefaultConfig();
            }
            SETTINGS = loadConfigFile();
        } catch (Exception e) {
            // Use the main class's logger
            RingOfRepair.LOGGER.error("Error setting up config for " + MOD_ID, e);
        }
    }

    public static ModConfigSettings getConfig() {
        return SETTINGS;
    }

    private static void validateConfigFile() throws IOException, SyntaxError, DeserializationException {
        JsonObject configFileData = JANKSON.load(CONFIG_FILE);
        JsonObject defaultConfigData = (JsonObject) JANKSON.toJson(new ModConfigSettings());

        boolean needsRefresh = false;

        for (Map.Entry<String, JsonElement> entry : defaultConfigData.entrySet()) {
            if (!configFileData.containsKey(entry.getKey())) {
                configFileData.putDefault(entry.getKey(), entry.getValue(), null);
                RingOfRepair.LOGGER.info("Added missing config option: " + entry.getKey());
                needsRefresh = true;
            }
        }

        if (configFileData.entrySet().removeIf(entry -> !defaultConfigData.containsKey(entry.getKey()))) {
            RingOfRepair.LOGGER.info("Removed old config options.");
            needsRefresh = true;
        }

        if (needsRefresh) {
            SETTINGS = JANKSON.fromJsonCarefully(configFileData, ModConfigSettings.class);
            saveConfigFile();
        }
    }

    private static void saveConfigFile() throws IOException {
        String result = JANKSON.toJson(SETTINGS).toJson(true, true);
        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE, false)) {
            out.write(result.getBytes());
        }
    }

    private static void createDefaultConfig() throws IOException {
        RingOfRepair.LOGGER.info("Creating default config at: {}", CONFIG_FILE.getAbsolutePath());
        SETTINGS = new ModConfigSettings();
        saveConfigFile();
    }

    private static ModConfigSettings loadConfigFile() throws IOException, SyntaxError {
        JsonObject configJson = JANKSON.load(CONFIG_FILE);
        return JANKSON.fromJson(configJson, ModConfigSettings.class);
    }
}
