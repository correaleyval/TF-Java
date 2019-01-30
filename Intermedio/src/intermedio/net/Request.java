
package intermedio.net;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Luis Antonio Correa Leyva
 * @email correaleyval@gmail.com
 * 
 */
public class Request {
    private final DataInputStream in;

    public String input;
    
    Request(Socket s) throws IOException, ClassNotFoundException {
        in = new DataInputStream(new BufferedInputStream ( s.getInputStream() ) );
    }
    
    public String getMsg() throws IOException {
        return in.readUTF();
    }
    
    public void close() throws IOException {
        in.close();
    }
}
