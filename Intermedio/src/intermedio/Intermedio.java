
package intermedio;

/**
 *
 * @author Luis Correa Leyva
 * @email correaleyval@gmail.com
 * 
 */
public class Intermedio {
    
    // Definicion del objeto buffer para el proceso Intermedio
    public static class Buffer {
        enum ESTADO {VACIO, DESORD, ORD};
        
        ESTADO statevar;        
        public static int[] fiveint;

        private Buffer() {
            this.fiveint = new int[5];
            statevar = ESTADO.VACIO;
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        new Buffer();  // inicializar buffer      
        
        System.out.println(" Proceso Intermedio");
        System.out.println(Buffer.fiveint[0]);
    }
    
}
