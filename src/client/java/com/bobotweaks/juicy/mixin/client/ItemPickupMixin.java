package com.bobotweaks.juicy.mixin.client;

import com.bobotweaks.juicy.JuicyClient;
import com.bobotweaks.juicy.sound.ModSounds;
import com.bobotweaks.juicy.util.ModUtilities;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.ItemPickupAnimationS2CPacket;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ItemPickupMixin {

    @Inject(method = "onItemPickupAnimation", at = @At("TAIL"))
    private void sas$onItemPickup(ItemPickupAnimationS2CPacket packet, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null || client.player == null) return;

        if (packet.getCollectorEntityId() == client.player.getId()) {
            ModUtilities.playConfigSound(ModSounds.ITEM_PICKUP, 0.4f, 0.8f, 0.8f, 1.2f, JuicyClient.SOUNDS_CONFIG.itemPickup);
        }
    }
}
