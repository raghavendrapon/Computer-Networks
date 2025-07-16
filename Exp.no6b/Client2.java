import java.io.*;
import java.net.*;
class Client {
    public static void main(String args[]) {
        try {
            // BufferedReader for user input
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            // Connect to server at localhost and port 3000
            Socket socket = new Socket("127.0.0.1", 3000);
            // BufferedReader to read from server input stream
            BufferedReader din = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // DataOutputStream to write to server output stream
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            // Ask user for MAC address
            System.out.print("Enter the Physical Address (MAC): ");
            String macInput = userInput.readLine();
            // Send MAC address to server
            dout.writeBytes(macInput + '\n');
            // Read and display the response from the server
            String response = din.readLine();
            System.out.println("The Logical address is (IP): " + response);
            // Close the socket
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
