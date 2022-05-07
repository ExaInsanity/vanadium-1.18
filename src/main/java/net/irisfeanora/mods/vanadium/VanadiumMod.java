package net.irisfeanora.mods.vanadium;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VanadiumMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("vanadium-quilt");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Loading vanadium. Your villagers are now less painful.");
	}
}
