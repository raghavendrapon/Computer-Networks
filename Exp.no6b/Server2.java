import java.io.*;
import java.net.*;
class Server {
    public static void main(String args[]) {
        try {
            // Create a server socket on port 3000
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("Server started, waiting for client...");
            // Accept the incoming client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            // BufferedReader to read from client input stream
            BufferedReader din = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // DataOutputStream to write to client output stream
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            // Define IP and MAC address pairs
            String[] ip = {"165.165.80.80", "165.165.79.1"};
            String[] mac = {"6A:08:AA:C2", "8A:BC:E3:FA"}
            while (true) {
                // Read the MAC address from the client
                String inputMac = din.readLine();
                if (inputMac == null) break; // Exit if client disconnects
                boolean found = false;
                // Search for the MAC in the list and return corresponding IP
                for (int i = 0; i < mac.length; i++) {
                    if (inputMac.equalsIgnoreCase(mac[i])) {
                        dout.writeBytes(ip[i] + '\n');
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    dout.writeBytes("IP not found\n");
                }
            }
            // Close connections
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
