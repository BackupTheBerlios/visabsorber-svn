/*
 * node.java
 *
 * Created on 22. November 2006, 16:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package visabsorber;

/**
 *
 * @author Jan-Stefan Fischer
 */
public class Node {
    
    Double u=null, x, y;
    int index;
    /** Creates a new instance of node */
    public Node(double x1, double y1, Double u1, int i) {
       u=u1;
       y=y1;
       x=x1;
       index=i;
    }   
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public int getIndex() {
        return index;
    }
}
