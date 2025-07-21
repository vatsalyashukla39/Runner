package core;

public class GetHandler implements HttpHandler{
    @Override
    public void handle(HttpRequest request, HttpResponse response) {
        // Basic example: return HTML content for GET
        if ("/form".equals(request.getPath())) {
            String html = """
        <!DOCTYPE html>
        <html>
        <head><title>Submit Name</title></head>
        <body>
          <form action="/submit" method="post">
            Name: <input type="text" name="name"/><br/><br/>
            <button type="submit">Send</button>
          </form>
        </body>
        </html>
        """;
            response.sendHtml(200, "OK", html);
            System.out.println("[FormHandler] Handling " + request.getMethod() + " at " + request.getPath());
            return;
        }

        String responseBody = "Welcome to Runner Web Server!";
        response.sendResponse(200, "OK", responseBody);
    }
}
