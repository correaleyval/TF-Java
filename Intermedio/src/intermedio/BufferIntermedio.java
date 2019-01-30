package intermedio;
/**
 *
 * @author Luis Antonio Correa Leyva
 * @email correaleyval@gmail.com
 * 
 */

import static java.lang.Thread.sleep;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

// Definicion del objeto buffer para el proceso Intermedio
public class BufferIntermedio implements Runnable {    

    @Override
    public void run() {
        System.out.println(":- Subproceso para ordenamiento ejecutandose");
        
        while(true) {
            
            if(statevar == ESTADO.DESORD) {
                Arrays.sort(fiveint);
                statevar = ESTADO.ORD;
                System.out.println(":-    Array Sorted");
            }
            
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(BufferIntermedio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public enum ESTADO {VACIO, DESORD, ORD};
        
    private static ESTADO statevar;        
    private static int[] fiveint;

    public BufferIntermedio() {
        fiveint = new int[5];
        statevar = ESTADO.VACIO;
    }
    
    public boolean getdata(int[] d) {
        System.out.println(statevar);
        System.out.println(Arrays.toString(fiveint));
        
        if(statevar.equals(ESTADO.ORD)) {
            for(int i = 0; i < 5; i++)
                d[i] = fiveint[i];
            
            statevar = ESTADO.VACIO;
            return true;
        }
        else
            return false;
    }
    
    public boolean setdata(int[] d) {        
        System.out.println(statevar);
        System.out.println(Arrays.toString(fiveint));
        
        if(statevar.equals(ESTADO.VACIO)) {
            fiveint = d.clone();
            statevar = ESTADO.DESORD;
            return true;
        }
        else {
            return false;
        }
    }
  }
