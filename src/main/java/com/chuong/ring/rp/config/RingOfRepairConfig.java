package com.chuong.ring.rp.config;

import com.chuong.ring.rp.RingOfRepair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RingOfRepairConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "ring_of_repair.json");

    public double repairIntervalSeconds = 1.0;

    public static RingOfRepairConfig load() {
        RingOfRepairConfig config = new RingOfRepairConfig();

        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                config = GSON.fromJson(reader, RingOfRepairConfig.class);
            } catch (IOException e) {
                RingOfRepair.LOGGER.error("Could not read config file", e);
            }
        }

        // Clamp values to prevent invalid settings
        if (config.repairIntervalSeconds < 0.1) {
            config.repairIntervalSeconds = 0.1;
        }
        if (config.repairIntervalSeconds > 5.0) {
            config.repairIntervalSeconds = 5.0;
        }

        config.save();
        return config;
    }

    public void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            RingOfRepair.LOGGER.error("Could not write config file", e);
        }
    }
}
