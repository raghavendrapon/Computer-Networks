import java.io.*;
import java.net.*;

class Server {
    public static void main(String args[]) {
        try {
            DatagramSocket server = new DatagramSocket(1309);
            System.out.println("DNS Server is running...");

            while (true) {
                byte[] receivebyte = new byte[1024];
                DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
                server.receive(receiver);

                String str = new String(receiver.getData()).trim();
                InetAddress addr = receiver.getAddress();
                int port = receiver.getPort();

                String[] ip = {"165.165.80.80", "165.165.79.1"};
                String[] name = {"www.aptitudeguru.com", "www.downloadcyclone.blogspot.com"};
                byte[] sendbyte = new byte[1024];

                boolean found = false;

                for (int i = 0; i < ip.length; i++) {
                    if (str.equalsIgnoreCase(ip[i])) {
                        sendbyte = name[i].getBytes();
                        found = true;
                        break;
                    } else if (str.equalsIgnoreCase(name[i])) {
                        sendbyte = ip[i].getBytes();
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    String errorMsg = "No match found.";
                    sendbyte = errorMsg.getBytes();
                }

                DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, port);
                server.send(sender);
            }

        } catch (Exception e) {
            System.out.println("Server Error: " + e);
        }
    }
}
