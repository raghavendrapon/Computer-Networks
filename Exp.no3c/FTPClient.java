import java.io.*;
import java.net.*;

public class FTPClient {
    public static void main(String args[]) throws Exception {
        InetAddress ia = InetAddress.getLocalHost();
        Socket s = new Socket(ia, 1024);

        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a file name to save received data: ");
        String fname = keyboardInput.readLine();

        PrintWriter fileWriter = new PrintWriter(new FileWriter(fname));
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String line;
        while ((line = serverInput.readLine()) != null) {
            fileWriter.println(line);
        }

        System.out.println("File received and saved successfully.");
        fileWriter.close();
        s.close();
    }
}
