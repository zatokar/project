/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author zatokar
 */
public class HTTPServer {
  Socket connectionSocket;
    public final static int Server_Port = 8888;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ServerSocket welcomeSocket = new ServerSocket(Server_Port);
            
            while (true) {
                System.out.println("Waiting for connection");
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println("Connection has been made");
                
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                String request = fromClient.readLine();
                String[] parts = request.split(" ");
                String filename = parts[1];
                System.out.println(filename);
                OutputStream output = connectionSocket.getOutputStream();
                output.write("HTTP/1.0 200 OK\r\n".getBytes());
                output.write("\r\n".getBytes());
                output.write("BODY IS HERE".getBytes());
                output.flush();
                connectionSocket.close();
                
               
            }
        } catch (IOException ex) {
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
        }dsafsa
    }
}
