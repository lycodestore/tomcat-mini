package request;

import java.io.IOException;
import java.io.InputStream;

public class Request {
    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public void parse() {
        try {
            byte[] buffer = new byte[2048];
            StringBuffer sb = new StringBuffer();
            int readLength = input.read(buffer);
            while (readLength == 2048) {
                String newString = new String(buffer, 0, readLength);
                sb.append(newString);
                readLength = input.read(buffer);
            }
            if (readLength != -1) {
                String newString = new String(buffer, 0, readLength);
                sb.append(newString);
                System.out.println(newString);
            }
            uri = parseUri(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }
        }

        return null;
    }

    public String getUri() {
        return uri;
    }
}
