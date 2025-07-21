package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtil {

    private static final String LOG_FILE = "server.log";

    public static synchronized void log(String clientIP, String method, String path, int statusCode) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            fw.write(String.format("[%s] %s - %s %s -> %d\n", timestamp, clientIP, method, path, statusCode));
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }
}

