
package intermedio.net;

import java.io.IOException;
import java.io.DataOutputStream;
import java.net.Socket;


/**
 *
 * @author Luis Antonio Correa Leyva
 * @email correaleyval@gmail.com
 * 
 */
public class Response {
    private DataOutputStream out;
    
    Response(Socket s) throws IOException {
        out = new DataOutputStream(s.getOutputStream());
        out.flush();
    }
    
    public void send(String s) throws IOException {
        out.writeUTF(s);
        out.flush();
    }
    
    public void close() throws IOException {
        out.close();
    }
}
