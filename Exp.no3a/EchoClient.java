import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try {
            // Connect to server on localhost and port 6666
            Socket socket = new Socket("localhost", 6666);
            System.out.println("Connected to server.");

            // Setup input and output streams
            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            Scanner input = new Scanner(System.in);

            String messageToSend = "";
            while (!messageToSend.equalsIgnoreCase("stop")) {
                System.out.print("Enter message to server: ");
                messageToSend = input.nextLine();
                dout.writeUTF(messageToSend); // Send message
                String response = din.readUTF(); // Read echo
                System.out.println("Server echoed: " + response);
            }

            // Close resources
            din.close();
            dout.close();
            socket.close();
            input.close();
            System.out.println("Client stopped.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
