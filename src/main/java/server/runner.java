package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class runner {

        private static final int PORT = 8083;
        private static final int MAX_THREADS = 10;

        public static void main(String[] args) {
            ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Runner Web Server started on port " + PORT);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    executor.submit(new ClientHandler(clientSocket));
                }

            } catch (IOException e) {
                System.err.println("Server error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                executor.shutdown();
            }
        }
    }


