/*
 * ReadFile.java
 *
 * Created on 13. Dezember 2006, 18:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package visabsorber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Jan-Stefan Fischer
 */
public class ReadFile {
    
    public static File openDialog() {
        JFileChooser fileChooser = new JFileChooser(".");
        int status = fileChooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.exists()) {
                return selectedFile;
            }
        }
        return null;
    }

    private static double[] readLine(String line, int numberCount, char c) {
        int position=0;
        int start=0;
        double buf[] = new double[numberCount];
        char Byte[]=line.toCharArray();
        for (int j=0;j<numberCount;j++) {            
            do {
                position++;
            } while(position<line.length() && Byte[position]!=c);
            if (start!=position-1) {
                double bufValue=Double.valueOf(line.substring(start,position)).doubleValue();               
                buf[j]=bufValue;               
            }
            start=position;
            
        }
        return buf;
    }
    
    public static ElementList loadElementsFromFile(File file, NodeList nodeList) {
        ElementList el= new ElementList();
        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader buffer = new BufferedReader(reader);              
                int bufX=Integer.valueOf(buffer.readLine()).intValue();
                double buf[] = new double[6];
                for (int i=0;i<bufX;i++) {
                    String line=buffer.readLine();
                    buf=readLine(line, 6, ' ');
                    el.addElement(new Element(nodeList.getNode(Double.valueOf(buf[1]).intValue()-1),nodeList.getNode(Double.valueOf(buf[2]).intValue()-1),nodeList.getNode(Double.valueOf(buf[3]).intValue()-1), Double.valueOf(buf[5]).intValue(), 0, el.getCount()));
                }
   
            } catch(java.lang.Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler Elementenliste", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return el;         
        }
        return null;
    }
    
        public static LineList loadLinesFromFile(File file, NodeList nodeList) {
        LineList li= new LineList();
        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader buffer = new BufferedReader(reader);              
                int bufX=Integer.valueOf(buffer.readLine()).intValue();
                double buf[] = new double[5];
                for (int i=0;i<bufX;i++) {
                    String line=buffer.readLine();
                    buf=readLine(line, 5, ' ');
                    li.addLine(new Line(nodeList.getNode(Double.valueOf(buf[1]).intValue()-1),nodeList.getNode(Double.valueOf(buf[2]).intValue()-1), Double.valueOf(buf[4]).intValue()));
                    
                }
   
            } catch(java.lang.Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler Elementenliste", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return li;         
        }
        return null;
    }
    
    public static NodeList loadNodesFromFile(File file) {
        NodeList nl= new NodeList();
        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader buffer = new BufferedReader(reader);
                
                int bufX=Integer.valueOf(buffer.readLine()).intValue();
                //double bufVector[]=new double[bufX*bufX];
                //Double bufMtrx[][]=new Double[bufX][bufX];//new double[bufX*bufX];
                double buf[] = new double[3];
                for (int i=0;i<bufX;i++) {
                    String line=buffer.readLine();
                    buf=readLine(line, 3, ' ');
                    nl.addNode(new Node(buf[1],buf[2],0.0,false,nl.getCount()));
                }
            } catch(java.lang.Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler Knotenliste", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return nl;
            
        }
        return null;
    }
    
}
    /** Creates a new instance of ReadFile */
    /*public int getNext(String value, String string, int start) {
        char Byte[]=string.toCharArray();
        int position=start;
        do {
                   position++;
        } while(position<string.length() && Byte[position]!=',');
        value.concat(string.substring(start,position));
        position++;
        return position;
    }*/

        /*NodeList nodeList=new NodeList();
        ElementList elementList = new ElementList();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            do {
                line=buffer.readLine();
            }
            while (line.startsWith("*NODE")==false);
         
            //scan Nodes
            JOptionPane.showMessageDialog(null, "Ende1 ", "Ende", JOptionPane.ERROR_MESSAGE);
            line=buffer.readLine();
            while (line.startsWith("*ELEMENT")==false) {
                int start=0;
                double x,y;
                char Byte[]=line.toCharArray();
                String bufValue="";
                start=getNext(bufValue, line, start);
                JOptionPane.showMessageDialog(null, ""+bufValue, "1", JOptionPane.ERROR_MESSAGE);
                start=getNext(bufValue, line, start);
         
                x=Double.valueOf(bufValue).doubleValue();
                JOptionPane.showMessageDialog(null, ""+x, "2", JOptionPane.ERROR_MESSAGE);
                start=getNext(bufValue, line, start);
                y=Double.valueOf(bufValue).doubleValue();
                JOptionPane.showMessageDialog(null, ""+y, "3", JOptionPane.ERROR_MESSAGE);
                int nodeCount=nodeList.getCount();
                nodeList.addNode(new Node(x,y,0.0,false,nodeCount));
         
                line=buffer.readLine();
            }
         
         
            //scan Elements
            JOptionPane.showMessageDialog(null, "Ende2", "Ende", JOptionPane.ERROR_MESSAGE);
            line=buffer.readLine();
            while (line.startsWith("*NSET, NSET=neumann_knoten")==false) {
                int start=0;
                int node0,node1,node2;
                char Byte[]=line.toCharArray();
                String bufValue="";
                start=getNext(bufValue, line, start);
                start=getNext(bufValue, line, start);
                node0=Double.valueOf(bufValue).intValue();
                start=getNext(bufValue, line, start);
                node1=Double.valueOf(bufValue).intValue();
                start=getNext(bufValue, line, start);
                node2=Double.valueOf(bufValue).intValue();
         
                elementList.addElement(new Element(nodeList.getNode(node0),nodeList.getNode(node1),nodeList.getNode(node2),0,0,0,0));
         
                line=buffer.readLine();
         
            }
            JOptionPane.showMessageDialog(null, "Ende3", "Ende", JOptionPane.ERROR_MESSAGE);
         
         
            //int bufX=Integer.valueOf(buffer.readLine()).intValue();
            //double bufVector[]=new double[bufX*bufX];
            /*for (int i=0;i<bufX;i++) {
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
            JOptionPane.showMessageDialog(null, e.getMessage() , "Fehler", JOptionPane.ERROR_MESSAGE);
         
        }*/
