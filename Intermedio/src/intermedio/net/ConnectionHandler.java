
package intermedio.net;

import intermedio.BufferIntermedio;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
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
    private final BufferIntermedio buffer;
    private final String addr;
    
    public ConnectionHandler(Socket s, BufferIntermedio b) throws IOException, ClassNotFoundException {        
        request = new Request(s);
        response = new Response(s);
        socket = s;
        buffer = b;
        addr = socket.getInetAddress().getHostAddress();
    }

    @Override
    public void run() {
        System.out.println("Conexion recibida de: " + addr);
        
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
        System.out.println(req + " from " + addr);
        
        if(req.equals("GET")) {
            int[] d = new int[5];
            
            if(buffer.getdata(d)) {
                response.send(Arrays.toString(d));
            }
            else {
                response.send("Esperando");
            }
            
            return;
        }
        
        if(req.startsWith("POST")) {
            String[] tok = req.split(" ");
            
            if(tok.length != 6) {
                response.send("Bad Protocol");
                return;
            }
            
            int[] data = new int[5];
            
            for (int i = 1; i < 6; i++)
                data[i-1] = Integer.parseInt(tok[i]);
            
            if(buffer.setdata(data)) {
                response.send("Produccion exitosa");
            }
            else {
                response.send("Esperando");
            }
            
            return;
        }
        
        response.send("Bad Protocol");
    }
    
    public void closeConnection() throws IOException {
        request.close();
        response.close();
        socket.close();
    }
}
