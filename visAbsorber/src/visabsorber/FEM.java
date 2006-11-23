/*
 * FEM.java
 *
 * Created on 22. November 2006, 17:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package visabsorber;

/**
 *
 * @author Jan-Stefan Fischer
 */
import java.lang.Math;
        
public class FEM {
    NodeList nodeList;
    ElementList elementList;
    LineList lineList;
    
    /** Creates a new instance of FEM */
    public FEM(NodeList nl, ElementList el, LineList ll) {
        nodeList=nl;
        elementList=el;
        lineList=ll;
    }
    
    public Matrix calcS () {
        Matrix S = new Matrix(nodeList.getCount(),nodeList.getCount());
        for (int e=0;e<elementList.getCount(); e++) {
            int u_i[]= new int [3];
            Element element=elementList.getElement(e);
            u_i[0]=element.getNode0().getIndex();
            u_i[1]=element.getNode1().getIndex();
            u_i[2]=element.getNode2().getIndex();
            Matrix s_e=element.getS();
            for (int x=0;x<3;x++) {
                for (int y=0;y<3;y++) {
                    S.setValue(u_i[x], u_i[y], S.getValue(u_i[x], u_i[y]) + s_e.getValue(y,x));
                }
            }
        }
        return S;
    }
    
    public void clacRB(Matrix S, Matrix p) {
        p.setXCount(1);
        p.setYCount(nodeList.getCount());
        //Neumann
        for (int i=0; i<lineList.getCount(); i++) {
            Line line = lineList.getLine(i);
            if (line.hasNeumann()) {
                double l = Math.sqrt((line.getNode0().getX()-line.getNode1().getX())*(line.getNode0().getX()-line.getNode1().getX()) +  (line.getNode0().getY()-line.getNode1().getY())*(line.getNode0().getY()-line.getNode1().getY()));
                double p_n = line.getQ()*l/2.0;
                double p_s = p.getValue(0,line.getNode0().getIndex()) + p_n;
                p.setValue(0, line.getNode0().getIndex(), p_s);
                p_s = p.getValue(0,line.getNode1().getIndex()) + p_n;
                p.setValue(0, line.getNode1().getIndex(), p_s);
            }
        }
    }
    
}
