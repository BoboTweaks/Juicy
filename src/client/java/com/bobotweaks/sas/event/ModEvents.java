package com.bobotweaks.sas.event;

import com.bobotweaks.sas.SoundsAndStuffClient;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ModEvents {

    public static void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            // Event logic will go here
        });
    }
}
