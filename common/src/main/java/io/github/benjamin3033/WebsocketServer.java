package io.github.benjamin3033;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class WebsocketServer extends WebSocketServer {

    WebSocket connection;
    public MinecraftServer server;

    public WebsocketServer(int port) {super (new InetSocketAddress(port));}

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        connection = webSocket;
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        server.execute(() -> {
            CommandSourceStack commandSource = server.createCommandSourceStack().withPermission(4).withSuppressedOutput();
            server.getCommands().performPrefixedCommand(commandSource, s);
        });
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
    }

    @Override
    public void onStart() {
    }

    public void broadcastMessage(String message){
        connection.send(message);
    }
}
