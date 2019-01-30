
package intermedio.net;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class Response {
    private PrintWriter out;
    private BufferedOutputStream dataOut;
    
    Response(Socket s) {
        try {
            out = new PrintWriter(s.getOutputStream());
            dataOut = new BufferedOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Response.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeHead(int st, String typ) {
        out.println(getStatus(st));
        out.println("Server: JWebServer");
        out.println("Date: " + new Date());
        out.println("Content-type: " + typ);
    }
    
    public void end(String str) {
        out.println("Content-length: " + str.length());
        out.println();
        out.flush();
        out.println(str);
        out.flush();
    }
    
    public void end(File file) throws IOException {
        int fileLength = (int) file.length();
        
        byte[] fileData = readFileData(file, fileLength);        
        
        out.println("Content-length: " + fileLength);
        out.println();
        out.flush();
                    
        dataOut.write(fileData, 0, fileLength);
        dataOut.flush();
    }
    
    private String getStatus(int st) {
        String status;
        
        switch(st) {
            case 200:
                status = "HTTP/1.1 200 OK";
                break;
            case 404:
                status = "HTTP/1.1 404 NOT FOUND";
                break;
            default:
                status = "HTTP/1.1 501 Internal Server Error";
        }
        
        return status;
    }
    
    public void close() throws IOException {
        out.close();
        dataOut.close();
    }
    
    private byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        
        byte[] fileData = new byte[fileLength];
        
        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        }
        finally {
            if(fileIn != null)
                fileIn.close();
        }
        
        return fileData;
    }
}
