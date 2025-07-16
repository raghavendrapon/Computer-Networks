import java.io.*;
import java.net.*;

class Server {
    public static void main(String args[]) {
        try {
            ServerSocket serverSocket = new ServerSocket(3636);
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader din = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            String[] ip = {"165.165.80.80", "165.165.79.1"};
            String[] mac = {"6A:08:AA:C2", "8A:BC:E3:FA"};

            while (true) {
                String str = din.readLine();
                if (str == null) break;

                System.out.println("Received IP: " + str);
                boolean found = false;

                for (int i = 0; i < ip.length; i++) {
                    if (str.equals(ip[i])) {
                        dout.writeBytes(mac[i] + '\n');
                        System.out.println("MAC Sent: " + mac[i]);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    dout.writeBytes("MAC not found\n");
                    System.out.println("MAC not found for: " + str);
                }
            }

            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
