package com.bobotweaks.juicy.config;

import com.bobotweaks.juicy.JuicyClient;

import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class SoundsConfig {
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("sas-sounds.cfg").toFile();

    public boolean itemPickup = true;
    public boolean inventoryOpen = true;
    public boolean inventoryClose = true;
    public boolean slotClick = true;
    public boolean equipLeather = true;
    public boolean switchFire = true;
    public boolean switchSword = true;
    public boolean swordSwing = true;

    public static SoundsConfig load() {
        SoundsConfig config = new SoundsConfig();
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
                            case "itemPickup" -> config.itemPickup = Boolean.parseBoolean(value);
                            case "inventoryOpen" -> config.inventoryOpen = Boolean.parseBoolean(value);
                            case "inventoryClose" -> config.inventoryClose = Boolean.parseBoolean(value);
                            case "slotClick" -> config.slotClick = Boolean.parseBoolean(value);
                            case "equipLeather" -> config.equipLeather = Boolean.parseBoolean(value);
                            case "switchFire" -> config.switchFire = Boolean.parseBoolean(value);
                            case "switchSword" -> config.switchSword = Boolean.parseBoolean(value);
                            case "swordSwing" -> config.swordSwing = Boolean.parseBoolean(value);
                        }
                    }
                }
            } catch (Exception e) {
                JuicyClient.LOGGER.error("Failed to load sounds config, using defaults", e);
            }
        } else {
            config.save();
        }
        return config;
    }

    public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONFIG_FILE))) {
            writer.println("# --- Basic Inventory Sounds ---");
            writer.println("itemPickup=" + itemPickup);
            writer.println("inventoryOpen=" + inventoryOpen);
            writer.println("inventoryClose=" + inventoryClose);
            writer.println("slotClick=" + slotClick);
            writer.println("");
            writer.println("# --- Equipping Sounds ---");
            writer.println("equipLeather=" + equipLeather);
            writer.println("");
            writer.println("# --- Item Switch Sounds ---");
            writer.println("switchFire=" + switchFire);
            writer.println("switchSword=" + switchSword);
            writer.println("");
            writer.println("# --- Item Use Sounds ---");
            writer.println("swordSwing=" + swordSwing);
        } catch (IOException e) {
            JuicyClient.LOGGER.error("Failed to save sounds config", e);
        }
    }
}
