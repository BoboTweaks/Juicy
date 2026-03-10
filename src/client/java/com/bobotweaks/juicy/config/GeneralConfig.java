package com.bobotweaks.juicy.config;

import com.bobotweaks.juicy.JuicyClient;

import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GeneralConfig {
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("sas-general.cfg").toFile();

    public float masterVolume = 1.0f;
    public boolean inventorySounds = true;
    public boolean itemSwitchSounds = true;

    public static GeneralConfig load() {
        GeneralConfig config = new GeneralConfig();
        if (CONFIG_FILE.exists()) {
            try {
                List<String> lines = Files.readAllLines(CONFIG_FILE.toPath());
                for (String line : lines) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#") || line.startsWith("//")) continue;
                    
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        
                        switch (key) {
                            case "masterVolume" -> config.masterVolume = Float.parseFloat(value);
                            case "inventorySounds" -> config.inventorySounds = Boolean.parseBoolean(value);
                            case "itemSwitchSounds" -> config.itemSwitchSounds = Boolean.parseBoolean(value);
                        }
                    }
                }
            } catch (Exception e) {
                JuicyClient.LOGGER.error("Failed to load general config, using defaults", e);
            }
        } else {
            config.save();
        }
        return config;
    }

    public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONFIG_FILE))) {
            writer.println("masterVolume=" + masterVolume);
            writer.println("inventorySounds=" + inventorySounds);
            writer.println("itemSwitchSounds=" + itemSwitchSounds);
        } catch (IOException e) {
            JuicyClient.LOGGER.error("Failed to save general config", e);
        }
    }
}
