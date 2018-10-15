package org.ithot.debug.wslog;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DebugWSService extends Service {

    private static DebugWSServer server = null;
    private static final int PORT = 27016;
    private static final int INTERVAL = 5000;
    private Timer timer = new Timer();

    private static Executor executor = Executors.newCachedThreadPool();

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (server == null) {
                server = new DebugWSServer(new InetSocketAddress(PORT));
                executor.execute(server);
            } else {
                if (!server.opened) {
                    try {
                        server.stop();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    server = null;
                    server = new DebugWSServer(new InetSocketAddress(PORT));
                    executor.execute(server);
                }
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        timer.schedule(timerTask, 0, INTERVAL);
    }


    public static void send(final String message) {
        if (server == null || !server.opened) return;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                server.broadcast(message);
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        timerTask.cancel();
        timerTask = null;
        timer.cancel();
        timer = null;
        if (server != null) {
            try {
                server.stop();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            server = null;
        }
        super.onDestroy();
    }
}
