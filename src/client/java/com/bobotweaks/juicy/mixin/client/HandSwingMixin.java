package com.bobotweaks.juicy.mixin.client;

import com.bobotweaks.juicy.JuicyClient;
import com.bobotweaks.juicy.sound.ModSounds;
import com.bobotweaks.juicy.util.ModUtilities;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class HandSwingMixin {

    @Shadow public boolean handSwinging;
    @Shadow public int handSwingTicks;
    @Shadow protected abstract int getHandSwingDuration();

    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;)V", at = @At("HEAD"))
    private void sas$onSwingHand(Hand hand, CallbackInfo ci) {
        if (!((Object)this instanceof ClientPlayerEntity player)) return;
        
        if (hand == Hand.MAIN_HAND && player.getMainHandStack().getItem() instanceof SwordItem) {
            
            if (!this.handSwinging || this.handSwingTicks >= this.getHandSwingDuration() / 2 || this.handSwingTicks < 0) {
                ModUtilities.playConfigSound(ModSounds.SWORD_SWING, 0.4f, 0.7f, 0.9f, 1.1f, JuicyClient.SOUNDS_CONFIG.swordSwing);
            }
        }
    }
}
