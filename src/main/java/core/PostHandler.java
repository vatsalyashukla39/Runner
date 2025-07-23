package core;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class PostHandler implements HttpHandler {


    @Override
    public void handle(HttpRequest request, HttpResponse response) {

        System.out.println("[PostHandler] Handling " + request.getMethod() + " at " + request.getPath());
        System.out.println("[POST] Received Body: " + request.getBody());

        String body = request.getBody();


        Map<String,String> params = parseForm(body);


        String name = params.getOrDefault("name", "Guest");
        String responseBody = "Hello, " + name + "!!!";


        response.sendResponse(200,"OK",responseBody);
    }

    private Map<String,String> parseForm(String body) {
        Map<String,String> map = new HashMap<>();
        String[] pairs = body.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=", 2);
            try {
                String key = URLDecoder.decode(kv[0], "UTF-8");
                String value = kv.length > 1
                        ? URLDecoder.decode(kv[1], "UTF-8")
                        : "";
                map.put(key, value);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UTF-8 encoding not supported", e);
            }
        }
        return map;
    }
}

