/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ProtocolException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zatokar
 */
public class HTTPServer  implements Runnable{

    private static Socket connectionSocket;
    public final static int SERVER_PORT = 8888;
    private static final String ROOT_CATALOG = "C:/project";
    public static final String CRLF = "\r\n";
   
public HTTPServer(Socket connection)
{
  connectionSocket=connection; 
    }
    @Override
      public void run() {
         try {
           
while (true) {
              
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(connectionSocket.getInputStream()));
                String request = fromClient.readLine();
                String[] parts = request.split(" ");
                String filename = parts[1];
                System.out.println(filename);
                OutputStream output = connectionSocket.getOutputStream();
                try {
                    FileInputStream file = new FileInputStream(ROOT_CATALOG + filename);
                    output.write(("HTTP/1.0 200 OK" + CRLF).getBytes());
                    output.write(CRLF.getBytes());
                    output.write("BODY IS HERE".getBytes());
                    output.flush();
                    connectionSocket.close();
                    
               } catch (FileNotFoundException ex) {
                   output.write(("HTTP/1.0 404 Not found: /doesNotExist.html").getBytes());
                    output.flush();
                 connectionSocket.close();
                }
      }
        }
         catch (IOException ex) {
            System.err.println("Connection closed" + ex);
        }
    }
    
private static void copy(final InputStream input, final OutputStream output) throws IOException {
        final byte[] buffer = new byte[1024];
        while (true) {
            int bytesRead = input.read(buffer);
            if (bytesRead == -1) {
                break;
            }
            output.write(buffer, 0, bytesRead);
        }
    }
}
