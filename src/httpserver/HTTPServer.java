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

/**
 *
 * @author zatokar
 */
public class HTTPServer {

    Socket connectionSocket;
    public final static int SERVER_PORT = 8888;
    private static final String ROOT_CATALOG = "C:/project";
    public static final String CRLF = "\r\n";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            ServerSocket welcomeSocket = new ServerSocket(SERVER_PORT);

            while (true) {
                System.out.println("Waiting for connection");
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println("Connection has been made");

                BufferedReader fromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                String request = fromClient.readLine();
                String[] parts = request.split(" ");
                String filename = parts[1];
                OutputStream output = connectionSocket.getOutputStream();
                try {
                    FileInputStream file = new FileInputStream(ROOT_CATALOG + filename);
                    output.write(("HTTP/1.0 200 OK" + CRLF).getBytes());
                    output.write(CRLF.getBytes());
                    output.write("BODY IS HERE".getBytes());
                    output.flush();
                    connectionSocket.close();
                } catch (FileNotFoundException ex) {
                    output.write(("HTTP/1.0 404 Not found:  /doesNotExist.html" + CRLF).getBytes());
                    output.flush();
                    connectionSocket.close();
                }
                catch(ProtocolException ex){
                
                }
                System.out.println(filename);




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
            //output.write(buffer, 0, bytesRead);
        }
    }
}
