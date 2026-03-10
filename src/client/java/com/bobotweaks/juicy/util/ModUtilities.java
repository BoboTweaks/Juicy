package com.bobotweaks.juicy.util;

import com.bobotweaks.juicy.JuicyClient;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;

public class ModUtilities {

    /**
     * Generates a random float between min and max.
     */
    public static float randomRange(Random random, float min, float max) {
        return min + random.nextFloat() * (max - min);
    }

    /**
     * Generates a random pitch centered around 1.0f.
     */
    public static float randomPitch(Random random, float range) {
        return 1.0f + (random.nextFloat() * 2 - 1) * range;
    }

    /**
     * Consolidated check for global, category, and specific sound toggles.
     */
    public static boolean shouldPlay(boolean specificToggle) {
        return JuicyClient.GENERAL_CONFIG.inventorySounds && specificToggle;
    }

    /**
     * Plays a sound with randomized volume/pitch if all config conditions are met.
     */
    public static void playConfigSound(SoundEvent sound, float minVol, float maxVol, float minPitch, float maxPitch, boolean specificToggle) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;
        
        if (shouldPlay(specificToggle)) {
            float volume = randomRange(client.world.random, minVol, maxVol) * JuicyClient.GENERAL_CONFIG.masterVolume;
            float pitch = randomRange(client.world.random, minPitch, maxPitch);
            client.player.playSound(sound, volume, pitch);
        }
    }
}
