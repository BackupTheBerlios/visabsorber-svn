/*
 * Matrix.java
 *
 * Created on 26. Oktober 2006, 14:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ppm_calculation;

import java.awt.Canvas;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import javax.swing.JOptionPane;

/**
 *
 * @author Jan-Stefan Fischer
 */

public class Matrix {
    int x, y;
    
    double Vector[];
    /** Creates a new instance of Matrix */
    void resetVector() {
        Vector=new double[y*x];
        
        for (int i=0; i<x*y; i++) {
            Vector[i]=0.0;
            
        }
        
    }
    
    public Matrix(int xCount, int yCount) {
        x=xCount;
        y=yCount;
        resetVector();
    }
    
    public void setXCount(int xCount) {
        x=xCount;
        resetVector();
    }
    
    public void setYCount(int yCount) {
        y=yCount;
        resetVector();
    }
    
    public int getXCount() {
        return x;
    }
    
    public int getYCount() {
        return y;
    }
    
    public void setDim(int dim) {
        x=y=dim;
        resetVector();       
    }
    
    public void setValue(int xPos, int yPos, double Value) {
        Vector[x*xPos+yPos]=Value;
    }
    
    public double getValue(int xPos, int yPos) {
        return Vector[x*xPos+yPos];
    }
    
    public boolean saveMatrixToFile(File file) {
        try {
                FileWriter writer = new FileWriter(file);
                writer.write(""+y+"\r\n");
                for (int i=0;i<y;i++) {
                    String line = "";
                    for (int j=0;j<x;j++) {
                        line=line+""+getValue(j,i);
                        if (j<x-1) line=line+" ";
                        else line=line+"\r\n";
                    }
                    writer.write(line);
                    
                }
                writer.close();
            } catch(java.lang.Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
    }
    
    public boolean loadMatrixFromFile(File file) {
        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader buffer = new BufferedReader(reader);
                
                int bufX=Integer.valueOf(buffer.readLine()).intValue();
                double bufVector[]=new double[bufX*bufX];
                for (int i=0;i<bufX;i++) {
                    String line=buffer.readLine();
                    int position=0;
                    int start=0;
                    char Byte[]=line.toCharArray();
                    for (int j=0;j<bufX;j++) {
                        
                        do {
                            position++;
                        } while(position<line.length() && Byte[position]!=' ');
                        
                        double bufValue=Double.valueOf(line.substring(start,position)).doubleValue();
                        start=position;
                        bufVector[bufX*j+i]=bufValue;
                    }
                }
                Vector=bufVector;
                x=y=bufX;
            } catch(java.lang.Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
            
        }
        return false;
    }
    
    public boolean loadVectorFromFile(File file) {
        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader buffer = new BufferedReader(reader);
                
                int bufX=Integer.valueOf(buffer.readLine()).intValue();
                double bufVector[]=new double[bufX];
                for (int i=0;i<bufX;i++) {
                    String line=buffer.readLine();
                    int position=0;
                    int start=0;
                    char Byte[]=line.toCharArray();
                    
                    do {
                        position++;
                    } while(position<line.length() && Byte[position]!=' ');
                    
                    double bufValue=Double.valueOf(line.substring(start,position)).doubleValue();
                    start=position;
                    bufVector[i]=bufValue;
                }
                
                
                Vector=bufVector;
                x=1;
                y=bufX;
            } catch(java.lang.Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
            
        }
        return false;
    }
    
    public void drawMatrix(Canvas canvas, int xPos, int yPos, String titel) {
        canvas.getGraphics().drawString(titel,xPos, yPos+10);
        for (int i=0; i<y;i++){
            for (int j=0;j<x;j++) {
                
                BigDecimal value = new BigDecimal(getValue(j,i),new MathContext(6,RoundingMode.HALF_UP));
                //value.round(new MathContext(1,RoundingMode.HALF_UP));
                canvas.getGraphics().drawString(value.toString(),xPos+90*j+10, yPos+35+20*i);
            }
        }
        canvas.getGraphics().drawLine(xPos,yPos+20,xPos,yPos+20+20*y);
        canvas.getGraphics().drawLine(xPos,yPos+20,xPos+8,yPos+20);
        canvas.getGraphics().drawLine(xPos,yPos+20+20*y,xPos+8,yPos+20+20*y);
        
        canvas.getGraphics().drawLine(xPos+90*x+20,yPos+20,xPos+90*x+20,yPos+20+20*y);
        canvas.getGraphics().drawLine(xPos+90*x+20,yPos+20,xPos+90*x+12,yPos+20);
        canvas.getGraphics().drawLine(xPos+90*x+20,yPos+20+20*y,xPos+90*x+12,yPos+20+20*y);
        
    }
    
    public Matrix transpont() {
        Matrix trans=new Matrix(y,x);
            for (int i=0;i<y;i++) {
                for (int j=0;j<x;j++) {
                    trans.setValue(i,j,getValue(j,i));
            }
        }
        return trans;
    }
    
}
