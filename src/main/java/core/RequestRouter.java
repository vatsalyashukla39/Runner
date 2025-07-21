package core;
import util.LoggerUtil;

public class RequestRouter {
    public static void route(HttpRequest request, HttpResponse response) {
        HttpHandler handler;

        switch (request.getMethod()) {
            case "GET":
                handler = new GetHandler();
                break;
            case "POST":
                handler = new PostHandler();
                break;
            default:
                response.sendResponse(405, "Method Not Allowed", "Method not supported: " + request.getMethod());
                return;
        }

        handler.handle(request, response);
    }


}
