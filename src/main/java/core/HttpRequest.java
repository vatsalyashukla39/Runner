package core;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String method;
    private String path;
    private Map<String, String> headers = new HashMap<>();
    private String body;
    private String version;

    public HttpRequest() {}
    public HttpRequest(BufferedReader reader) throws IOException {
        parseRequest(reader);
    }

    private void parseRequest(BufferedReader reader) throws IOException {
        //"GET /hello HTTP/1.1"
        String line = reader.readLine();
        if (line == null || line.isEmpty()) return;

        String[] tokens = line.split(" ");
        if (tokens.length >= 3) {
            this.method = tokens[0];
            this.path = tokens[1];
            this.version = tokens[2];
        }

        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] header = line.split(":", 2);
            if (header.length == 2) {
                headers.put(header[0].trim(), header[1].trim());
            }
        }


        if ("POST".equalsIgnoreCase(method)) {
            int contentLength = 0;
            if (headers.containsKey("Content-Length")) {
                contentLength = Integer.parseInt(headers.get("Content-Length"));
            }

            char[] bodyChars = new char[contentLength];
            int bytesRead = reader.read(bodyChars);
            body = new String(bodyChars, 0, bytesRead);
        }
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

