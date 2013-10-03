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
import java.net.Socket;
/**
 *
 * @author zatokar
 */
public class HTTPServer implements Runnable {

    public Socket connectionSocket;
    public static int SERVER_PORT = 8080;
    private final String ROOT_CATALOG = "C:/project";
    public static final String CRLF = "\r\n";

    public HTTPServer() {
       
    }
    public HTTPServer(Socket connection) {
        connectionSocket = connection;
    }

    @Override
    public void run() {
        try {
            
            BufferedReader fromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));
            String request = fromClient.readLine();
            String[] parts = request.split(" ", -3);
            String filename = parts[1];
            System.out.println(filename);
            OutputStream output = connectionSocket.getOutputStream();
            try {
                FileInputStream file = new FileInputStream(ROOT_CATALOG + filename);
                output.write(("HTTP/1.0 200 OK" + CRLF).getBytes());
//                    output.write(CRLF.getBytes());
//                    output.write(("BODY IS HERE"+CRLF).getBytes());
                copy(file, output);
                output.write(CRLF.getBytes());
                output.flush();
                output.close();

            } catch (FileNotFoundException ex) {
                output.write(("HTTP/1.0 404 Not found: /doesNotExist.html").getBytes());
                output.flush();
                output.close();
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
        }
    }
}
