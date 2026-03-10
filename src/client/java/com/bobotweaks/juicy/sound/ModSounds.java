package com.bobotweaks.juicy.sound;

import com.bobotweaks.juicy.JuicyClient;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    // Inventory Sounds
    public static final SoundEvent ITEM_PICKUP = register("item_pickup");

    // Hotbar Switch Sounds
    public static final SoundEvent SWITCH_GENERAL = register("switch_general");
    public static final SoundEvent SWITCH_FIRE = register("switch_fire");
    public static final SoundEvent SWITCH_SWORD = register("switch_sword");

    // Item Use Sounds
    public static final SoundEvent SWORD_SWING = register("sword_swing");

    // Equipping Sounds
    public static final SoundEvent EQUIP_LEATHER = register("equip_leather");

    private static SoundEvent register(String name) {
        Identifier id = new Identifier(JuicyClient.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        
    }
}
