package server;

import config.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HttpServer {
    private ServerSocket serverSocket;
    private Executor pool;
    private boolean shutdown;

    public HttpServer() {
        try {
            serverSocket = new ServerSocket(Config.port);
            pool = Executors.newFixedThreadPool(Config.poolSize);
            shutdown = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Server start on: " + Config.port);
        System.out.println("Listening......");
        while (!shutdown) {
            try {
                Socket socket = serverSocket.accept();
                pool.execute(new HttpRunnable(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
