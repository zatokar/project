/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author zatokar
 */
public class TestSocket {
     public static void main(String args[]) throws IOException, InterruptedException {
      ServerSocket welcomeSocket = new ServerSocket(6789);
      
    while(true){
    Socket connectionSocket=welcomeSocket.accept();
    HTTPServer si=new HTTPServer(connectionSocket);
    si.run();
}
    }
}
