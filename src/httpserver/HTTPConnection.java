/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import static httpserver.HTTPServer.SERVER_PORT;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author zatokar
 */
public class HTTPConnection {
    
    public static void main(String[] args) {
        try {
            ServerSocket welcomeSocket = new ServerSocket(SERVER_PORT);
     while (true) {
         
                System.out.println("Waiting for connection");
                Socket connectionSocket = welcomeSocket.accept();
                HTTPServer connection=new HTTPServer(connectionSocket);
                connection.run();
                //Thread connectionThread=new Thread(connection);
                //connectionThread.start();
                System.out.println("Connection has been made");
     }
     } catch (IOException ex) {
            System.err.println("Connection closed" + ex);
        }
    }
}
