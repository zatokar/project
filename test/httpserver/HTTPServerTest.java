/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zatokar
 */
public class HTTPServerTest {
    
    public HTTPServerTest() {
    }

    @Test
    public void testMain() {
    }
    @Test
  public void testResponseOK() throws IOException {
    final Socket client = new Socket("localhost", HTTPServer.Server_Port);

    final OutputStream output = client.getOutputStream();
    output.write(("GET /file.html HTTP/1.0" + HTTPServer.CRLF + HTTPServer.CRLF).getBytes());
    output.flush();

    final BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
    final String statusLine = input.readLine();
    assertEquals("HTTP/1.0 200 OK", statusLine);
    client.close();
  }
}