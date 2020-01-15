package config;

import java.util.HashMap;

public class Config {
    public static final int port = 8080;

    public static final String webRoot = "/home/ly/Desktop/www";

    public static final int poolSize = 1000;

    public static final HashMap<String, String> mime = new HashMap<>();

    static {
        mime.put("css", "text/css");
        mime.put("gif", "image/gif");
        mime.put("html", "text/html");
        mime.put("ico", "image/x-icon");
        mime.put("jpeg", "image/jpeg");
        mime.put("jpg", "image/jpeg");
        mime.put("js", "text/javascript");
        mime.put("json", "application/json");
        mime.put("pdf", "application/pdf");
        mime.put("png", "image/png");
        mime.put("svg", "image/svg+xml");
        mime.put("swf", "application/x-shockwave-flash");
        mime.put("tiff", "image/tiff");
        mime.put("txt", "text/plain");
        mime.put("wav", "audio/x-wav");
        mime.put("wma", "audio/x-ms-wma");
        mime.put("wmv", "video/x-ms-wmv");
        mime.put("xml", "text/xml");
        mime.put("zip", "application/octet-stream");
        mime.put("rar", "application/octet-stream");
        mime.put("exe", "application/octet-stream");
        mime.put("deb", "application/octet-stream");
        mime.put("gz", "application/octet-stream");
        mime.put("tar", "application/octet-stream");
        mime.put("bz", "application/octet-stream");
        mime.put("bz2", "application/octet-stream");
        mime.put("gz2", "application/octet-stream");
    }

}
