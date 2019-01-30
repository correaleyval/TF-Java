
package clienttester;

import java.net.*; 
import java.io.*; 
import static java.lang.Thread.sleep;

import java.util.Scanner;
  
public class ClientTester 
{ 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
    
    private Scanner keyboard = null;
    
    private String msg;
  
    public ClientTester(String address, int port) throws ClassNotFoundException, IOException, InterruptedException 
    { 
        
        keyboard = new Scanner(System.in);

        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected: " + socket.getInetAddress().getHostAddress()); 
  
            input  = new DataInputStream( new BufferedInputStream( socket.getInputStream() ) );
            out    = new DataOutputStream( socket.getOutputStream());
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
  
        while(true) {
            System.out.print("Message: ");
            msg = keyboard.nextLine();
             
            out.writeUTF(msg);
            out.flush();
            
            System.out.println("Sended Message");
            System.out.println("Response: " + input.readUTF());
        }
    }
    
    public static void main(String args[]) throws ClassNotFoundException, IOException, InterruptedException {
        ClientTester c= new ClientTester("10.42.0.1", 7777);
    }
}
