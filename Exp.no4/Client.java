import java.io.*;
import java.net.*;

class Client {
    public static void main(String args[]) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress addr = InetAddress.getByName("127.0.0.1");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the SERVER/DOMAIN NAME or IP address: ");
            String str = in.readLine();

            byte[] sendbyte = str.getBytes();
            DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, 1309);
            client.send(sender);

            byte[] receivebyte = new byte[1024];
            DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
            client.receive(receiver);

            String s = new String(receiver.getData()).trim();
            System.out.println("DNS Response: " + s);

            client.close();
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}
