package server;

import core.HttpRequest;
import core.HttpResponse;
import core.RequestRouter;

import java.io.*;
import java.net.Socket;

    public class ClientHandler implements Runnable {

        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (
                    InputStream input = clientSocket.getInputStream();
                    OutputStream output = clientSocket.getOutputStream();
            ) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                PrintWriter writer = new PrintWriter(output, true);

                // Parse the incoming request
                HttpRequest request = new HttpRequest(reader);
                HttpResponse response = new HttpResponse(output);

                // Route and handle the request
                RequestRouter.route(request, response);

            } catch (Exception e) {
                System.err.println("Error handling client: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException ignored) {}
            }
        }
}
