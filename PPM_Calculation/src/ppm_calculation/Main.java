/*
 * Main.java
 *
 * Created on 26. Oktober 2006, 00:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ppm_calculation;

/**
 *
 * @author Jan-Stefan Fischer
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
              java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    
}
