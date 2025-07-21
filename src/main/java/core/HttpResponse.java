package core;



import java.io.OutputStream;
import java.io.PrintWriter;

public class HttpResponse {
    private OutputStream outputStream;
    private int statusCode = 200;

    public void setStatusCode(int code) {
        this.statusCode = code;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendResponse(int statusCode, String statusText, String body) {
        try {
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println("HTTP/1.1 " + statusCode + " " + statusText);
            writer.println("Content-Type: text/plain");
            writer.println("Content-Length: " + body.length());
            writer.println(); // blank line between headers and body
            writer.println(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

