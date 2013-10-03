/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zatokar
 */

public class ShuttingDown implements Runnable{
    Socket ShutSocket;
    HTTPServer httpServer=new HTTPServer();
    ServerSocket acceptingSocket = new ServerSocket(9999);
    Socket shutingSocket = acceptingSocket.accept();
ShuttingDown(Socket shuttingSocket){
     ShutSocket=shuttingSocket;
}

    @Override
    public void run() {
        Runnable service2 = new ShuttingDown(shutingSocket);
        executor2.execute(service2);
                       
                if(acceptingSocket.isBound()){
                    LOGGER.log(Level.INFO, "Server has been shutted down. ");
                    writeToLog(today, "Server has been shutted down. ");
                    executor.shutdown();
                    break;
                }
        try {
            httpServer.connectionSocket.shutdownInput();
        } catch (IOException ex) {
            Logger.getLogger(ShuttingDown.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
