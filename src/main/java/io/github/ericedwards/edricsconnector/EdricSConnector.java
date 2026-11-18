package io.github.ericedwards.edricsconnector;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EdricSConnector implements ModInitializer {

    public static final String MOD_ID = "edrics-connector";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        LOGGER.info("onInitialize() called");

        ModConfig modConfig;
        try {
            modConfig = ModConfigLoader.load();
        } catch (IOException e) {
            LOGGER.error("cannot create/load mod configuration");
        }

        CommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess, environment) -> {
                    dispatcher.register(
                            Commands.literal("edrics-connector")
                                    .executes(context -> {
                                        context.getSource().sendSuccess(() -> Component.literal("got em"), false);
                                        return 1;
                                    })
                    );
                });

        ServerPlayConnectionEvents.JOIN.register(
                (handler, sender, server) -> {
                    LOGGER.info("Player joined: {}", handler.player.getName().getString());
                });

        ServerPlayConnectionEvents.DISCONNECT.register(
                (handler, server) -> {
                    LOGGER.info("Player left: {}", handler.player.getName().getString());
                });
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
