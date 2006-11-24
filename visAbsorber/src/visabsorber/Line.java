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
    boolean neumann;
    boolean cauchy;
    double q, alpha, t_fluid;
    /** Creates a new instance of Line */
    public Line(Node n0, Node n1, boolean neumann1, double q1, boolean cauchy1, double alpha1, double t_fluid1 ) {
        node0 = n0;
        node1 = n1;
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
    
}
