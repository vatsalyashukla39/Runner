# Runner Web Server

A simple multithreaded HTTP server in Java—built from scratch with no external frameworks.

## Features

- Handles multiple clients concurrently via a fixed‐size thread pool  
- Supports basic **GET** and **POST** requests  
- Serves simple HTML pages and processes form data  
- Returns `405` for unsupported methods  
- Logs each request (IP, method, path, status) to `server.log`

## Quick Start

1. **Compile**  
   ```bash
   javac runner.java
2. **Run**  
   ```bash
   java runner
