package io.github.ericedwards.edricsconnector;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EdricSConnector implements ModInitializer {

	public static final String MOD_ID = "edrics-connector";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");

		ServerPlayConnectionEvents.JOIN.register(
				(handler, sender, server) -> {
					LOGGER.info("Player joined: {}", handler.player.getName().getString());
				});

		ServerPlayConnectionEvents.DISCONNECT.register(
				( handler, server) -> {
					LOGGER.info("Player left: {}", handler.player.getName().getString());
				});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
