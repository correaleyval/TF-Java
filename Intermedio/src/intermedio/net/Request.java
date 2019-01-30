
package intermedio.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 *
 * @author Luis Antonio Correa Leyva
 * @email correaleyval@gmail.com
 * 
 */
public class Request {
    private final BufferedReader in;
    
    public String method;
    public String url;
    public String input;
    
    Request(Socket s) throws IOException {

        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
        input = in.readLine();
        
        try {
            StringTokenizer parse = new StringTokenizer(input);
            
            method = parse.nextToken().toUpperCase();
           
            url = parse.nextToken().toLowerCase();
        }
        catch(Exception e) {
            input = "GET / HTTP/1.1";
            
            StringTokenizer parse = new StringTokenizer(input);
            
            method = parse.nextToken().toUpperCase();
           
            url = parse.nextToken().toLowerCase();
        }
    }
    
    public void close() throws IOException {
        in.close();
    }
}
