import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient {
    public static void main(String args[]) {
        try {
            Socket s = new Socket("localhost", 6666);
            System.out.println("Connected to server.");

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            Scanner input = new Scanner(System.in);

            String senddata = "";
            String receivedata = "";

            while (!senddata.equalsIgnoreCase("stop")) {
                System.out.print("TO SERVER: ");
                senddata = input.nextLine();
                dout.writeUTF(senddata);

                receivedata = din.readUTF();
                System.out.println("SERVER SAYS: " + receivedata);
            }

            din.close();
            dout.close();
            s.close();
            System.out.println("Client stopped.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
