/*
 * Element.java
 *
 * Created on 22. November 2006, 16:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package visabsorber;

/**
 *
 * @author Jan-Stefan Fischer
 */
public class Element {
    Node node0, node1, node2;
    int index;
    double rho, lamda, cp, J;
    Matrix S=new Matrix(3,3);
    /** Creates a new instance of Element */
    
    public Element(Node n0, Node n1, Node n2, double rho1, double lamda1, double cp1,  int i) {
        node0=n0;
        node1=n1;
        node2=n2;
        index=i;
        rho=rho1;
        lamda=lamda1;
        cp=cp1;
        J=(node1.getX()-node0.getX()) * (node2.getY()-node1.getY()) - (node2.getX()-node1.getX()) * (node1.getY()-node0.getY());
        calcS();
    }
    
    public void calcS() {
        double dx[]=new double[3];
        double dy[]=new double[3];
        dx[0]=node2.getX()-node1.getX();//x32
        dx[1]=node0.getX()-node2.getX();//x13
        dx[2]=node1.getX()-node0.getX();//x21
        dy[0]=node2.getY()-node1.getY();//y32
        dy[1]=node0.getY()-node2.getY();//y13
        dy[2]=node1.getY()-node0.getY();//y21
        double factor=lamda/(2.0*J);    
        for (int x=0;x<3;x++) {
            for (int y=0;y<3;y++) {
                S.setValue(x,y, (dx[x]*dx[y]+dy[x]*dy[y]) * factor ) ;
            }
        }
    }
    
    public Node getNode0() {
        return node0;
    }
    
    public Node getNode1() {
        return node1;
    }
    
    public Node getNode2() {
        return node2;
    }
    
    public Matrix getS() {
        return S;
    }
    
}
