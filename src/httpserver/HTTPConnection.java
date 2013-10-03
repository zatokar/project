/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import static httpserver.HTTPServer.SERVER_PORT;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zatokar
 */
public class HTTPConnection {

    static Date today = new Date();
    private static final Logger LOGGER = Logger.getLogger("server");

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket welcomeSocket = new ServerSocket(SERVER_PORT);
            while (true) {
                LOGGER.log(Level.INFO, "Server start up. ");
                writeToLog(today, "Server start up. ");
                Socket connectionSocket = welcomeSocket.accept();
                LOGGER.log(Level.INFO, "The client requested a connection.");
                writeToLog(today, "The client requested a connection.");
                HTTPServer connection = new HTTPServer(connectionSocket);
                Thread connectionThread = new Thread(connection);
                connectionThread.start();
                LOGGER.log(Level.INFO, "Connection has been made.");
                writeToLog(today, "Connection has been made.");
                
            }
        } catch (IOException ex) {
            System.err.println(today + " ... Connection closed." + ex);
            writeToLog(today, "Connection closed.");
        }
    }

    private static void writeToLog(Date today, String input) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:/project/file.txt", true)))) {
            out.println(today+" "+input);
            out.close();
        }
    }
}
