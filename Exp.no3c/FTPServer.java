import java.io.*;
import java.net.*;

public class FTPServer {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(1024);
        System.out.println("Server started and waiting for connection...");

        Socket s = ss.accept();
        System.out.println("Client connected!");

        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        PrintStream outStream = new PrintStream(s.getOutputStream());

        System.out.print("Enter a file name to send: ");
        String fname = keyboardInput.readLine();

        File file = new File(fname);
        if (file.exists()) {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = fileReader.readLine()) != null) {
                outStream.println(line);
            }
            fileReader.close();
            System.out.println("File sent successfully.");
        } else {
            outStream.println("File not found.");
            System.out.println("File does not exist on the server.");
        }

        outStream.close();
        s.close();
        ss.close();
    }
}

