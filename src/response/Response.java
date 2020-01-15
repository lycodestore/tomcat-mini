package response;

import config.Config;
import request.Request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
    private OutputStream output;
    private Request request;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() {
        String uri = request.getUri();
        if (uri != null) {
            File file = new File(Config.webRoot, uri);

            try {
                if (file.exists()) {
                    if (file.isFile()) {
                        output.write(generateFileHeader().getBytes());
                        FileInputStream fis = new FileInputStream(file);
                        byte[] buffer = new byte[2048];
                        int readLength = fis.read(buffer);
                        while (readLength != -1) {
                            output.write(buffer, 0, readLength);
                            readLength = fis.read(buffer);
                        }
                    } else {
                        output.write(generateDirectoryResponse(file).getBytes());
                    }
                    output.flush();
                } else {
                    output.write(generateNotFoundResponse().getBytes());
                    output.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateFileHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Connection: close\r\n");
        sb.append("Server: wetty\r\n");

        sb.append("Content-Type: ");
        String uri = request.getUri();
        int index = uri.lastIndexOf(".") + 1;
        String externalName = uri.substring(index);
        sb.append(Config.mime.get(externalName));
        sb.append("\r\n\r\n");

        return sb.toString();
    }

    private String generateDirectoryResponse(File file) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Connection: close\r\n");
        sb.append("Server: wetty\r\n");
        sb.append("Content-Type: text/html;charset=utf-8\r\n\r\n");

        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<title>\n");
        sb.append("fileshare");
        sb.append("</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        for (File ele : file.listFiles()) {
            sb.append("<a href='./");
            sb.append(ele.getName());
            if (ele.isDirectory()) {
                sb.append("/");
            }
            sb.append("'>");
            sb.append(ele.getName());
            sb.append("</a>\n");
            sb.append("<br>");
        }
        sb.append("</body>\n");
        sb.append("</html>\n");

        return sb.toString();
    }

    private String generateNotFoundResponse() {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Connection: close\r\n");
        sb.append("Server: wetty\r\n");
        sb.append("Content-Type: text/html;charset=UTF-8\r\n\r\n");
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append("<head>\n");
        sb.append("<title>\n");
        sb.append("fileshare");
        sb.append("</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("<h1>404</h1>\n");
        sb.append("</body>\n");
        sb.append("</html>\n");

        return sb.toString();
    }
}
