/*
 * Line.java
 *
 * Created on 23. November 2006, 15:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package visabsorber;

/**
 *
 * @author Jan-Stefan Fischer
 */
public class Line {
    Node node0, node1;
    boolean neumann=false;
    boolean cauchy=false;
    double q, alpha, t_fluid;
    int type;
    /** Creates a new instance of Line */
    public Line(Node n0, Node n1, int typ) {
        node0 = n0;
        node1 = n1;  
        type=typ;
    }
    public Line(Node n0, Node n1, boolean neumann1, double q1, boolean cauchy1, double alpha1, double t_fluid1 ) {
        node0 = n0;
        node1 = n1;
        neumann = neumann1;
        cauchy = cauchy1;
        q = q1;
        alpha = alpha1;
        t_fluid = t_fluid1;   
    }
    
    public int getType() {
        return type;
    }
    
    public void setProperties(boolean neumann1, double q1, boolean cauchy1, double alpha1, double t_fluid1) {
        neumann = neumann1;
        cauchy = cauchy1;
        q = q1;
        alpha = alpha1;
        t_fluid = t_fluid1;          
    }
    
    public boolean hasNeumann() {
        return neumann;
    }
    
    public boolean hasCauchy() {
        return cauchy;
    }
    
    public Node getNode0() {
        return node0;
    }
    
    public Node getNode1() {
        return node1;
    }
    
    public double getQ() {
        return q;
    }
    
    public double getAlpha() {
        return alpha;
    }
    
    public double getT_Fluid() {
        return t_fluid;
    }
    
    public double getLength() {
        return Math.sqrt((node0.getX()-node1.getX())*(node0.getX()-node1.getX())+ (node0.getY()-node1.getY())*(node0.getY()-node1.getY()));
    }
    
    public double getQProMeter() {
        double l=getLength();
        if (neumann) {
            return q*l;
        }
        else {
            double t0=(node0.getU()+node1.getU())/2.0;
            return alpha*l*(t_fluid-t0);
        }
    }
    
}
