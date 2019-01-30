package intermedio;
/**
 *
 * @author ragnar
 */
// Definicion del objeto buffer para el proceso Intermedio
public class BufferIntermedio {
     enum ESTADO {VACIO, DESORD, ORD};
        
    public static ESTADO statevar;        
    public static int[] fiveint;

    public BufferIntermedio() {
        this.fiveint = new int[5];
        statevar = ESTADO.VACIO;
    }
  }
