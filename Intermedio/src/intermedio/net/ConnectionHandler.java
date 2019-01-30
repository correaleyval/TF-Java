
package intermedio.net;

import intermedio.BufferIntermedio;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class ConnectionHandler implements Runnable {
    
    private final Request request;
    private final Response response;
    private final Socket socket;
    
    public ConnectionHandler(Socket s, BufferIntermedio b) throws IOException, ClassNotFoundException {        
        request = new Request(s);
        response = new Response(s);
        socket = s;
    }

    @Override
    public void run() {
        System.out.println("Conexion recibida de: " + socket.getInetAddress().getHostAddress());
        
        while(true) {
            try {
                manage(request.getMsg());
            } catch (IOException ex) {
                try {
                    closeConnection();
                    System.out.println("Conexion cerrada para: " + socket.getInetAddress().getHostAddress());
                    break;
                } catch (IOException ex1) {
                    Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } 
        }
    }
    
    private void manage (String req) throws IOException {
        System.out.println(req);
        response.send("OK");
    }
    
    public void closeConnection() throws IOException {
        request.close();
        response.close();
        socket.close();
    }
}
