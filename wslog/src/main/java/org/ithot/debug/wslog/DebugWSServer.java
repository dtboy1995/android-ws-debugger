package org.ithot.debug.wslog;

import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class DebugWSServer extends WebSocketServer {

    public static final String TAG = "[DebugWSServer]";

    public boolean opened;

    public DebugWSServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {

    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        Log.e(TAG, "WebSocket Server onError");
        opened = false;
    }

    @Override
    public void onStart() {
        Log.e(TAG, "WebSocket Server onStart");
        opened = true;
    }
}
