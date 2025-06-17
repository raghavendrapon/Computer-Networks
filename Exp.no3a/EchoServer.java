import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            // Create a server socket on port 6666
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("Server is ready and waiting for client...");

            // Accept client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Setup input and output streams
            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            String receivedMessage = "";
            while (!receivedMessage.equalsIgnoreCase("stop")) {
                receivedMessage = din.readUTF(); // Receive message
                System.out.println("Client says: " + receivedMessage);
                dout.writeUTF("Echo: " + receivedMessage); // Send back echo
            }

            // Close resources
            din.close();
            dout.close();
            socket.close();
            serverSocket.close();
            System.out.println("Server stopped.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
