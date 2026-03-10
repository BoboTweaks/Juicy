package com.bobotweaks.juicy.event;

import com.bobotweaks.juicy.JuicyClient;
import com.bobotweaks.juicy.sound.ModSounds;
import com.bobotweaks.juicy.util.ModUtilities;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModEvents {

    private static final ItemStack[] lastArmor = new ItemStack[4];
    private static int lastSelectedSlot = -1;
    private static int tickDelay = 0;

    public static void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            if (!isReady(client)) return;

            onHotbarSwitch(client);
            onArmorEquip(client);
        });
    }
    
    private static boolean isReady(MinecraftClient client) {
        if (tickDelay < 20) {
            tickDelay++;
            for (int i = 0; i < 4; i++) {
                lastArmor[i] = client.player.getInventory().armor.get(i).copy();
            }
            lastSelectedSlot = client.player.getInventory().selectedSlot;
            return false;
        }
        return true;
    }
    
    private static void onHotbarSwitch(MinecraftClient client) {
        int current = client.player.getInventory().selectedSlot;
        if (current == lastSelectedSlot) return;

        ItemStack prev = client.player.getInventory().getStack(lastSelectedSlot);
        ItemStack held = client.player.getInventory().getStack(current);
        
        if (prev.isEmpty() && held.isEmpty()) {
            lastSelectedSlot = current;
            return;
        }
        
        ModUtilities.playConfigSound(ModSounds.SWITCH_GENERAL, 0.2f, 0.5f, 0.7f, 1.3f, JuicyClient.GENERAL_CONFIG.itemSwitchSounds);

        if (isFireSound(held)) {
            ModUtilities.playConfigSound(ModSounds.SWITCH_FIRE, 0.225f, 0.5f, 0.75f, 1.25f, JuicyClient.SOUNDS_CONFIG.switchFire);
        } else if (isSword(held)) {
            ModUtilities.playConfigSound(ModSounds.SWITCH_SWORD, 0.4f, 0.7f, 0.9f, 1.1f, JuicyClient.SOUNDS_CONFIG.switchSword);
        }

        lastSelectedSlot = current;
    }
    
    private static void onArmorEquip(MinecraftClient client) {
        for (int i = 0; i < 4; i++) {
            ItemStack current = client.player.getInventory().armor.get(i);
            ItemStack previous = lastArmor[i];

            boolean wasEmpty = previous == null || previous.isEmpty();
            boolean isNowArmor = !current.isEmpty() && current.getItem() instanceof ArmorItem;

            if (wasEmpty && isNowArmor) {
                ArmorItem armor = (ArmorItem) current.getItem();

                if (armor.getMaterial() == ArmorMaterials.LEATHER) {
                    ModUtilities.playConfigSound(ModSounds.EQUIP_LEATHER, 0.6f, 0.9f, 0.9f, 1.1f, JuicyClient.SOUNDS_CONFIG.equipLeather);
                }
            }

            lastArmor[i] = current.copy();
        }
    }

    private static boolean isFireSound(ItemStack stack) {
        return stack.isOf(Items.TORCH)
            || stack.isOf(Items.SOUL_TORCH)
            || stack.isOf(Items.CAMPFIRE)
            || stack.isOf(Items.SOUL_CAMPFIRE);
    }

    private static boolean isSword(ItemStack stack) {
        return stack.getItem() instanceof net.minecraft.item.SwordItem;
    }
}
