package admin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import app.InventoryManager;

public class AdminService {
    private InventoryManager inventoryManager;

    public AdminService(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void startServer(int port) {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                while (true) {
                    try (Socket clientSocket = serverSocket.accept();
                         BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                         OutputStream os = clientSocket.getOutputStream();
                         PrintWriter pw = new PrintWriter(os, true)) {

                        String input = br.readLine();
                        if (input != null) {
                            String[] parts = input.split("\\|", 2);
                            String command = parts[0];
                            String data = parts.length > 1 ? parts[1] : "";

                            String response = processCommand(command, data);
                            pw.println(response);
                        }
                    } catch (Exception e) {
                        System.out.println("Error processing command: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println("Server error: " + e.getMessage());
            }
        }).start();
    }

    private String processCommand(String command, String data) {
        switch (command) {
            case "U":
                inventoryManager.initializeFromJsonString(data);
                return "Inventory updated.";
            case "R":
                return inventoryManager.toJsonString();
            default:
                return "Unknown command.";
        }
    }
}
