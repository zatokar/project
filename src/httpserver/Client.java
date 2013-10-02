/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author zatokar
 */
public class Client {
     public static void main(String argv[]) throws Exception{
		int port = 6789;  //default
                    if (argv.length > 0) port = Integer.parseInt(argv[0]);
                   BufferedReader inFromUser = new BufferedReader(
			new InputStreamReader(System.in));
                // To server on local host
		Socket clientSocket = new Socket("127.0.0.1", port);
                // To server on other host with IP-address = 83.92.58.109
                //Socket clientSocket = new Socket("83.92.58.109", port);
		PrintStream outToServer = new PrintStream(
			clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(
			new InputStreamReader(clientSocket.getInputStream()));
                for (int i=0;i<5;i++){
		String sentence = inFromUser.readLine();
		outToServer.println(sentence);
		String modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER: " + modifiedSentence);
                }
		clientSocket.close();
   }
}
