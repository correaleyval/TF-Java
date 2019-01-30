
package intermedio.net;

import intermedio.BufferIntermedio;
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
    private final BufferIntermedio buffer;
    
    public ConnectionHandler(Socket s, BufferIntermedio b) throws IOException {        
        request = new Request(s);
        response = new Response(s);
        socket = s;
        buffer = b;
    }

    @Override
    public void run() {
        manage(request.input);        
    }
    
    private void manage (String req) {
        System.out.println(req);
        response.writeHead(200, "text/html");
        response.end(req);
    }
    
    public void closeConnection() throws IOException {
        request.close();
        response.close();
        socket.close();
    }
}
