package com.bobotweaks.sas;

import com.bobotweaks.sas.config.GeneralConfig;
import com.bobotweaks.sas.config.SoundsConfig;
import com.bobotweaks.sas.event.ModEvents;
import com.bobotweaks.sas.sound.ModSounds;

import net.fabricmc.api.ClientModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoundsAndStuffClient implements ClientModInitializer {
	public static final String MOD_ID = "sas";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	public static GeneralConfig GENERAL_CONFIG;
	public static SoundsConfig SOUNDS_CONFIG;

	@Override
	public void onInitializeClient() {
		LOGGER.info("Initializing Sounds & Stuff Client");

		GENERAL_CONFIG = GeneralConfig.load();
		SOUNDS_CONFIG = SoundsConfig.load();

		ModSounds.registerSounds();
		ModEvents.registerEvents();
	}
}