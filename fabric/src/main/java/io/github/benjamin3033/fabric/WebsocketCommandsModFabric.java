package io.github.benjamin3033.fabric;

import net.fabricmc.api.ModInitializer;

import io.github.benjamin3033.WebsocketCommandsMod;

public final class WebsocketCommandsModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        WebsocketCommandsMod.init();
    }
}
