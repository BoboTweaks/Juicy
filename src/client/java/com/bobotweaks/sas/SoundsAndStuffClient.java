package com.bobotweaks.sas;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoundsAndStuffClient implements ClientModInitializer {
	public static final String MOD_ID = "sas";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("Initializing Sounds & Stuff Client");
	}
}