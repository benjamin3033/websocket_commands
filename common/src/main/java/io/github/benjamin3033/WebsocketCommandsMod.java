package io.github.benjamin3033;

import dev.architectury.event.events.common.CommandRegistrationEvent;
import dev.architectury.event.events.common.LifecycleEvent;

import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;


public final class WebsocketCommandsMod {
    public static final String MOD_ID = "websocket_commands";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static ModConfig config;

    private MinecraftServer minecraftServer;
    private WebsocketServer websocketServer;
    private static WebsocketCommandsMod INSTANCE;



    public static void init() {


        LOGGER.info("Initializing Websocket Commands");

        INSTANCE = new WebsocketCommandsMod();
        INSTANCE.initialize();
    }

    private void initialize(){
        config = ModConfig.load();

        LifecycleEvent.SERVER_STARTED.register(server -> {
            INSTANCE.minecraftServer = server;
            INSTANCE.startWebsocketServer();
        });

        CommandRegistrationEvent.EVENT.register((commandDispatcher, commandBuildContext, commandSelection) -> {
            commandDispatcher.register(
                    literal("wscmessage")
                            .requires(source -> source.hasPermission(2))
                            .then(argument("message", greedyString())
                                    .executes(context -> {
                                        String message = getString(context, "message");
                                        websocketServer.broadcastMessage(message);
                                        return 1;
                                    })
                            )
            );
        });
    }

    private void startWebsocketServer()
    {
        websocketServer = new WebsocketServer(config.websocketPort);
        websocketServer.server = minecraftServer;
        websocketServer.start();
    }

    public static WebsocketCommandsMod getInstance(){
        return INSTANCE;
    }
}
