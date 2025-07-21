package core;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class PostHandler implements HttpHandler {

//    public void handle(HttpRequest request, HttpResponse response) {
//        // Echo back the POST body for now
//        String body = request.getBody();
//        String responseBody = "Received POST data:\n" + body;
//        response.sendResponse(200, "OK", responseBody);
//    }
    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        // 1. Extract the raw body
        System.out.println("[PostHandler] Handling " + request.getMethod() + " at " + request.getPath());
        System.out.println("[POST] Received Body: " + request.getBody());

        String body = request.getBody();

        // 2. (Optional) Parse form‑encoded data into key/value pairs
        Map<String,String> params = parseForm(body);

        // 3. Generate a response based on the parsed data
        String name = params.getOrDefault("name", "Guest");
        String responseBody = "Hello, " + name + "!!!";

        // 4. Send back a 200 OK with plain‑text body
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
                // This should never happen as UTF-8 is always supported
                throw new RuntimeException("UTF-8 encoding not supported", e);
            }
        }
        return map;
    }
}

