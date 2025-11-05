package io.github.benjamin3033.neoforge;

import net.neoforged.fml.common.Mod;

import io.github.benjamin3033.WebsocketCommandsMod;

@Mod(WebsocketCommandsMod.MOD_ID)
public final class WeboscketCommandsModNeoForge {
    public WeboscketCommandsModNeoForge() {
        // Run our common setup.
        WebsocketCommandsMod.init();
    }
}
