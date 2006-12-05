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
    
    NodeList neighbors;
    double u, x, y;
    boolean temp;
    int index;
    int border;
    /** Creates a new instance of node */
    public Node(double x1, double y1, double u1, boolean temp1, int i) {
       u=u1;
       y=y1;
       x=x1;
       temp=temp1;
       index=i;
       neighbors = new NodeList();
       border=0;
    }  
    
    public void setBorderIndex(int i) {
       border=i;
    }
    
    public int getBorderIndex() {
       return border;
    }
    
    public boolean addNeighbor(Node node) {
        for (int i=0;i<neighbors.getCount(); i++) {
            if (neighbors.getNode(i)==node) return false;
        }
        neighbors.addNode(node);
        return true;
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
    
    public boolean hasTemp() {
        return temp;
    }
    
    public double getU() {
        return u;
    }
    
    public void setU(double u1) {
        temp=true;
        u=u1;
    }
}
