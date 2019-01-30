
package intermedio;

/**
 *
 * @author Luis Correa Leyva
 * @email correaleyval@gmail.com
 * 
 */

import java.io.IOException;
import java.net.ServerSocket;
import intermedio.net.ConnectionHandler;

public class Intermedio {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Intermedio p = new Intermedio();
        
        p.listen(7777);
    }
    
    private ConnectionHandler handler;
    private final BufferIntermedio b;

    public Intermedio() {
        this.b = new BufferIntermedio();
    }
        
    public void listen(int port) throws IOException, ClassNotFoundException {
        ServerSocket serverConnect = new ServerSocket(port);
        System.out.println("Proceso intermedio escuchando en el puerto: 7777");
        
        
        while (true) {
            handler = new ConnectionHandler(serverConnect.accept(), b);

            Thread thread = new Thread(handler);
            thread.start();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        
        handler.closeConnection();
    }  
}
