import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HexFormat;

public class Main {
  public static void main(String[] args){
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.err.println("Logs from your program will appear here!");

    // Uncomment this block to pass the first stage
     
     ServerSocket serverSocket = null;
     Socket clientSocket = null;
     int port = 9092;
     try {
       serverSocket = new ServerSocket(port);
       // Since the tester restarts your program quite often, setting SO_REUSEADDR
       // ensures that we don't run into 'Address already in use' errors
       serverSocket.setReuseAddress(true);
       // Wait for connection from client.
       clientSocket = serverSocket.accept();
       /*
        * In the updated code, a string outputString is created to represent the hardcoded response that needs to be sent to the client. This string contains the hexadecimal representation of the message length (which is not validated at this stage and is set to 00000000) followed by the hardcoded correlation ID 00000007.
The HexFormat class is then used to convert this hexadecimal string into a byte array output. The HexFormat.of().parseHex(outputString) method parses the hexadecimal string into a byte array that can be sent over the network.
Finally, the output byte array is written to the client's output stream using clientSocket.getOutputStream().write(output), and clientSocket.getOutputStream().flush() is called to ensure that the data is sent immediately and not buffered.
        */
       String outputString = "0000000000000007";
       byte[] output = HexFormat.of().parseHex(outputString);
       clientSocket.getOutputStream().write(output);
       clientSocket.getOutputStream().flush();
     } catch (IOException e) {
       System.out.println("IOException: " + e.getMessage());
     } finally {
       try {
         if (clientSocket != null) {
           clientSocket.close();
         }
       } catch (IOException e) {
         System.out.println("IOException: " + e.getMessage());
       }
     }
  }
}
