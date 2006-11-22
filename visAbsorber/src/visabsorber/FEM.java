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
public class FEM {
    NodeList nodeList;
    ElementList elementList;
    
    /** Creates a new instance of FEM */
    public FEM(NodeList nl, ElementList el) {
        nodeList=nl;
        elementList=el;
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
    
}
