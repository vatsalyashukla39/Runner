package core;

public class GetHandler implements HttpHandler{
    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        // Basic example: return HTML content for GET
        String responseBody = "Welcome to Runner Web Server!";
        response.sendResponse(200, "OK", responseBody);
    }
}
