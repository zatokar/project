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
import java.net.UnknownHostException;
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
    public void testResponseOK() throws UnknownHostException, IOException {
   final Socket client = new Socket("localhost", HTTPServer.SERVER_PORT);

    final OutputStream output = client.getOutputStream();
    output.write(("GET /file.html HTTP/1.0" + HTTPServer.CRLF + HTTPServer.CRLF).getBytes());
    output.flush();

    final BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
    final String statusLine = input.readLine();
    assertEquals("HTTP/1.0 200 OK", statusLine);
    client.close();
  }
    @Test
  public void testResponseNotOK() throws IOException {
    final Socket client = new Socket("localhost", HTTPServer.SERVER_PORT);

    final OutputStream output = client.getOutputStream();
    output.write(("GET /doesNotExist.html HTTP/1.0" + HTTPServer.CRLF + HTTPServer.CRLF).getBytes());
    output.flush();

    final BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
    final String statusLine = input.readLine();
    assertEquals("HTTP/1.0 404 Not found: /doesNotExist.html", statusLine);
    client.close();
  }
//     @Test
  public void testIllegalProtocol() throws IOException {
    final Socket client = new Socket("localhost", HTTPServer.SERVER_PORT);

    final OutputStream output = client.getOutputStream();
    output.write(("GET /doesNotExist.html" + HTTPServer.CRLF + HTTPServer.CRLF).getBytes());
    output.flush();

    final BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
    final String statusLine = input.readLine();
    assertEquals("HTTP/1.0 400 Illegal request", statusLine);
    client.close();
  }
}