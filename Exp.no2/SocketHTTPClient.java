import java.io.*;
import java.net.*;

public class SocketHTTPClient {
    public static void main(String[] args) {
        try {
            // Create a socket to connect to the website
            Socket socket = new Socket("www.martinbroadhurst.com", 80);

            // Get the output stream to send request
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send an HTTP GET request
            out.println("GET / HTTP/1.1");
            out.println("Host: www.martinbroadhurst.com");
            out.println(""); // End of the HTTP request

            // Get the input stream to read the server's response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read and print the server's response
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                System.out.println(responseLine);
            }

            // Close the streams and socket
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
