package admin;

import java.util.Scanner;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class AdminApplication {
    private Scanner scanner;

    public AdminApplication() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Update Inventory");
            System.out.println("2. Retrieve Inventory");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter JSON data to update inventory: ");
                    String json = scanner.nextLine();
                    sendUpdateCommand(json);
                    break;
                case 2:
                    sendRetrieveCommand();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void sendUpdateCommand(String json) {
        sendCommand("U", json);
    }

    private void sendRetrieveCommand() {
        sendCommand("R", "");
    }

    private void sendCommand(String command, String data) {
        try (Socket socket = new Socket("localhost", 12345);
             OutputStream os = socket.getOutputStream();
             PrintWriter pw = new PrintWriter(os, true)) {

            pw.println(command + "|" + data);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = br.readLine();
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.out.println("Error sending command: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AdminApplication adminApp = new AdminApplication();
        adminApp.run();
    }
}
