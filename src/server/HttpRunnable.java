package server;

import request.Request;
import response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpRunnable extends Thread {
    private Socket socket;

    public HttpRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            Request request = new Request(input);
            request.parse();

            Response response = new Response(output);
            response.setRequest(request);
            response.sendStaticResource();

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
