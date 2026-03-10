package com.bobotweaks.sas.sound;

import com.bobotweaks.sas.SoundsAndStuffClient;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    // Inventory Sounds
    public static final SoundEvent ITEM_PICKUP = register("item_pickup");

    private static SoundEvent register(String name) {
        Identifier id = new Identifier(SoundsAndStuffClient.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        
    }
}
