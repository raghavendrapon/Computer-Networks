import java.io.*;
import java.net.*;

class Client {
    public static void main(String args[]) {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            Socket socket = new Socket("127.0.0.1", 3636);

            BufferedReader din = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            System.out.print("Enter the Logical address (IP): ");
            String ipInput = userInput.readLine();

            dout.writeBytes(ipInput + '\n');

            String response = din.readLine();
            System.out.println("The Physical Address is: " + response);

            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
