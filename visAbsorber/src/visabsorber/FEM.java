/*
 * FEM.java
 *
 * Created on 22. November 2006, 17:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package visabsorber;

import java.io.File;
import javax.swing.JOptionPane;
        
public class FEM extends Thread {
    NodeList nodeList;
    ElementList elementList;
    LineList lineList;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JProgressBar statusBar;
    /** Creates a new instance of FEM */
    public FEM(NodeList nl, ElementList el, LineList ll, javax.swing.JLabel label, javax.swing.JProgressBar bar)  {
        nodeList=nl;
        elementList=el;
        lineList=ll;
        statusLabel=label;
        statusBar=bar;
    }
    
    public Matrix calcS () {
        Matrix S = new Matrix(nodeList.getCount(),nodeList.getCount());
        S.resetVector();
        
        for (int e=0;e<elementList.getCount(); e++) {
            progress("S calc",e,elementList.getCount());
            int u_i[]= new int [3];
            Element element=elementList.getElement(e);
            u_i[0]=element.getNode0().getIndex();
            u_i[1]=element.getNode1().getIndex();
            u_i[2]=element.getNode2().getIndex();
            Matrix s_e=element.getS();
            for (int x=0;x<3;x++) {
                for (int y=0;y<3;y++) {
                    S.setValue(u_i[x], u_i[y], S.getValue(u_i[x], u_i[y]) + s_e.getValue(x,y));
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
            progress("RB Calc (Neumann/Cauchy)",i,lineList.getCount());
            Line line = lineList.getLine(i);
            if (line.hasNeumann()) {
                double l = Math.sqrt((line.getNode0().getX()-line.getNode1().getX())*(line.getNode0().getX()-line.getNode1().getX()) +  (line.getNode0().getY()-line.getNode1().getY())*(line.getNode0().getY()-line.getNode1().getY()));
                double p_n = line.getQ()*l/2.0;
                double p_s = p.getValue(0,line.getNode0().getIndex()) + p_n;
                p.setValue(0, line.getNode0().getIndex(), p_s);
                p_s = p.getValue(0,line.getNode1().getIndex()) + p_n;
                p.setValue(0, line.getNode1().getIndex(), p_s);
            }
            if (line.hasCauchy()) {
                double l = Math.sqrt((line.getNode0().getX()-line.getNode1().getX())*(line.getNode0().getX()-line.getNode1().getX()) +  (line.getNode0().getY()-line.getNode1().getY())*(line.getNode0().getY()-line.getNode1().getY()));
                double p_n = line.getAlpha()*line.getT_Fluid()*l/2.0;
                double p_s = p.getValue(0,line.getNode0().getIndex()) + p_n;
                p.setValue(0, line.getNode0().getIndex(), p_s);
                p_s = p.getValue(0,line.getNode1().getIndex()) + p_n;
                p.setValue(0, line.getNode1().getIndex(), p_s);     
                
                double s_c=line.getAlpha()*l;
                double s_c0=s_c/3.0+S.getValue(line.getNode0().getIndex(),line.getNode0().getIndex());
                double s_c1=s_c/6.0+S.getValue(line.getNode0().getIndex(),line.getNode1().getIndex());
                double s_c2=s_c/6.0+S.getValue(line.getNode1().getIndex(),line.getNode0().getIndex());
                double s_c3=s_c/3.0+S.getValue(line.getNode1().getIndex(),line.getNode1().getIndex());
                S.setValue(line.getNode0().getIndex(),line.getNode0().getIndex(), s_c0);
                S.setValue(line.getNode0().getIndex(),line.getNode1().getIndex(), s_c1);
                S.setValue(line.getNode1().getIndex(),line.getNode0().getIndex(), s_c2);
                S.setValue(line.getNode1().getIndex(),line.getNode1().getIndex(), s_c3);                
            }
        }
        /*for (int i=0; i<nodeList.getCount(); i++) { //Löschen der horinzonzalen Zeilen
            Node node = nodeList.getNode(i);
            if (node.hasTemp()) {
                for (int j=0; j<S.getXCount(); j++) {
                    S.setValue(j,i,0.0);                
                }
            }
        }
        */
       for (int i=0; i<nodeList.getCount(); i++) {
            progress("RB Calc (Dirle)",i,nodeList.getCount());
            Node node = nodeList.getNode(i);
            if (node.hasTemp()) {
                double u = node.getU();
                //S.setValue(i,i,1.0);
                p.setValue(0,i,0.0);
                for (int j=0; j<S.getXCount(); j++) {         
                    if (j==i) {
                        p.setValue(0,j,u);
                        S.setValue(i,j,1.0);
                    }
                    else {
                        double p_n=p.getValue(0,j)-(u*S.getValue(i,j));
                        p.setValue(0,j,p_n);
                        S.setValue(i,j,0.0);
                        S.setValue(j,i,0.0); 
                        
                    }
                }
                
            }
        }
        //p.saveMatrixToFile(new File("p.txt"));
        //S.saveMatrixToFile(new File("S.txt"));
        
    }
    public synchronized void progress(String statusName, int position, int max) {
        statusLabel.setText(statusName);
        statusBar.setMaximum(max);
        statusBar.setValue(position);
        //JOptionPane.showMessageDialog(null, "Fertig", "Fertig", JOptionPane.ERROR_MESSAGE);
    }
    
    public void run() {
        //JOptionPane.showMessageDialog(null, "Fertig", "Fertig", JOptionPane.ERROR_MESSAGE);
        progress("S calc",0,0);
        Matrix S=calcS();
        Matrix p = new Matrix(1,nodeList.getCount());
        progress("RB calc",0,0);
        clacRB(S, p);
        progress("LR-Zerlegung",0,0);
        
        Calculator calc=new Calculator(this);
        Matrix MatrixL = new Matrix (nodeList.getCount(),nodeList.getCount());
        Matrix MatrixR = new Matrix (nodeList.getCount(),nodeList.getCount());
        Matrix VectorY = new Matrix (1,nodeList.getCount());
        Matrix VectorX = new Matrix (1,nodeList.getCount());
        String failure=calc.LRCalc(S, MatrixR, MatrixL);
        if (failure==null) {
            progress("L*Y",0,0);
            failure=calc.calc_YX(MatrixL, p, VectorY,0);
            if (failure==null) {
                progress("R*X",0,0);
                failure=calc.calc_YX(MatrixR, VectorY, VectorX,1);
                if (failure==null) {
                    progress("Save X-file",0,0);
                    Matrix xOutput = new Matrix(4,nodeList.getCount());
                    for (int i=0;i<nodeList.getCount();i++) {
                        nodeList.getNode(i).setU(VectorX.getValue(0,i));
                        xOutput.setValue(0,i,i);
                        xOutput.setValue(1,i,nodeList.getNode(i).getX());
                        xOutput.setValue(2,i,nodeList.getNode(i).getY());
                        xOutput.setValue(3,i,nodeList.getNode(i).getU());
                    }
                    progress("Save ele-file",0,0);
                    Matrix elOutput = new Matrix(4,elementList.getCount());
                    xOutput.saveMatrixToFile(new File("x.txt"));
                    for (int i=0;i<elementList.getCount();i++) {
                        elOutput.setValue(0,i,i);
                        elOutput.setValue(1,i,elementList.getElement(i).getNode0().getIndex());
                        elOutput.setValue(1,i,elementList.getElement(i).getNode1().getIndex());
                        elOutput.setValue(1,i,elementList.getElement(i).getNode2().getIndex());
                        
                    }
                    elOutput.saveMatrixToFile(new File("ele.txt"));
                    
                    
                    //return VectorX;
                }
                else JOptionPane.showMessageDialog(null, "Fehler XY2", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
            else JOptionPane.showMessageDialog(null, "Fehler XY1", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(null, "Fehler LR", "Fehler", JOptionPane.ERROR_MESSAGE);
        //JOptionPane.showMessageDialog(null, "Fertig", "Fertig", JOptionPane.ERROR_MESSAGE);
        progress("Ende",0,0);
        /*String failure=calc.Cholesky(S, MatrixR);
        if (failure==null) {
            failure=calc.calc_YX(MatrixR, p, VectorY,0);
            if (failure==null) {
                failure=calc.calc_YX(MatrixR.transpont(), VectorY, VectorX,1);
                if (failure==null) {
                    for (int i=0;i<nodeList.getCount();i++) {
                        nodeList.getNode(i).setU(VectorX.getValue(0,i));
                    }
                    return VectorX;
                }
                JOptionPane.showMessageDialog(null, "Fehler XY2", "Fehler", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            JOptionPane.showMessageDialog(null, "Fehler XY1", "Fehler", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        JOptionPane.showMessageDialog(null, "Fehler Cho", "Fehler", JOptionPane.ERROR_MESSAGE);*/
        //return null;
    }
    
    public void calcX() {
        run();
    }
    
}
