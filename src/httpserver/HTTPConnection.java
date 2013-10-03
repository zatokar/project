/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import static httpserver.HTTPServer.SERVER_PORT;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author zatokar
 */
public class HTTPConnection {
    static java.util.Date  today = new java.util.Date();
    public static void main(String[] args) throws IOException {
        try {
           ServerSocket welcomeSocket = new ServerSocket(SERVER_PORT);
    while (true) {
          
                System.out.println(today+ " ... Waiting for connection. (Server start up)");
                writeToLog(today+ " ... Waiting for connection. (Server start up)");
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println(today+ " ... The client requested a connection.");
                writeToLog(today+ " ... The client requested a connection.");
                HTTPServer connection=new HTTPServer(connectionSocket);
                //connection.run();
                Thread connectionThread=new Thread(connection);
                connectionThread.start();
                System.out.println(today+ " ... Connection has been made. (Response to client)");
                writeToLog(today+ " ... Connection has been made. (Response to client)");
                //HTTPServer.writeToLog("Server shout down.");
     }
     } catch (IOException ex) {
            System.err.println(today+ " ... Connection closed." + ex);
            writeToLog(today+ " ... Connection closed." + ex);
        }
    }
    private static void writeToLog(String input) throws IOException
{
    BufferedWriter out = new BufferedWriter(new FileWriter("file.txt"));
    out.write(input);
    out.close();
}
}
