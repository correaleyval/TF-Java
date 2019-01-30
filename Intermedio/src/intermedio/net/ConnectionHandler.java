
package intermedio.net;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author luis
 */
public class ConnectionHandler implements Runnable {
    
    private final Request request;
    private final Response response;
    private final Socket socket;
    
    public ConnectionHandler(Socket s) throws IOException {        
        request = new Request(s);
        response = new Response(s);
        socket = s;       
    }

    @Override
    public void run() {
        manage(request.url);        
    }
    
    private void manage (String req) {
        System.out.println(req);
    }
    
    public void closeConnection() throws IOException {
        request.close();
        response.close();
        socket.close();
    }
}
