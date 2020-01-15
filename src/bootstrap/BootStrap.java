package bootstrap;

import server.HttpServer;

public class BootStrap {
    public static void main(String[] args) throws Exception {
        HttpServer httpServer = new HttpServer();
        httpServer.start();
    }
}
