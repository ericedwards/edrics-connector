package io.github.ericedwards.edricsconnector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfigLoader {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static ModConfig load() throws IOException {

        Path file = FabricLoader.getInstance()
                .getConfigDir()
                .resolve("edrics-connector.json");

        if (Files.notExists(file)) {
            ModConfig config = new ModConfig();
            Files.writeString(file, GSON.toJson(config));
            return config;
        }

        return GSON.fromJson(Files.readString(file), ModConfig.class);
    }

}
