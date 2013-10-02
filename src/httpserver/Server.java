/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author zatokar
 */
public class Server {
  Socket connectionSocket;

    Server(Socket connection) {
        connectionSocket = connection;
    }

    void run() throws IOException, InterruptedException {
        
        BufferedReader inFromClient = new BufferedReader(
                new InputStreamReader(connectionSocket.getInputStream()));
        PrintStream outToClient = new PrintStream(
                connectionSocket.getOutputStream());
        String clientSentence = inFromClient.readLine();
        while(clientSentence!=null){
            Thread.sleep(100);
        System.out.println("FROM CLIENT: " + clientSentence);
        String capitalizedSentence = clientSentence.toUpperCase();
        outToClient.println(capitalizedSentence);
        clientSentence = inFromClient.readLine();
        }

    }
}
