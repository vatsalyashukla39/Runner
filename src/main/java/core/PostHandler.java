package core;

public class PostHandler implements HttpHandler {
    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        // Echo back the POST body for now
        String body = request.getBody();
        String responseBody = "Received POST data:\n" + body;
        response.sendResponse(200, "OK", responseBody);
    }
}
