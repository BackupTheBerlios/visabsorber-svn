/*
 * MainFrame.java
 *
 * Created on 24. November 2006, 11:31
 */

package visabsorber;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author  Jan-Stefan Fischer
 */
public class MainFrame extends javax.swing.JFrame {
      ElementList elementList = new ElementList();
        NodeList nodeList = new NodeList();
        LineList lineList = new LineList();
    public MainFrame() {
        initComponents();
        scrollPane1.add(visMatrix);
        visMatrix.setSize(new Dimension(3000,1500));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        startFEM = new javax.swing.JButton();
        scrollPane1 = new java.awt.ScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        statusBar = new javax.swing.JProgressBar();
        jLabel7 = new javax.swing.JLabel();
        nodeFileField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        elementFileField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        rbdFileField = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("visAbsorber");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        startFEM.setText("start FEM");
        startFEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startFEMActionPerformed(evt);
            }
        });

        jLabel1.setText("Art des Absorbers:");

        jButton1.setText("importiere Querschnittnetz");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Umgebungstemperatur [K]:");

        jLabel3.setText("Temperaturfluid [K]:");

        jLabel4.setText("W\u00e4rmeleitf\u00e4higkeit Absorber:");

        jLabel5.setText("Absorbtionsgrad Beschichtung:");

        jLabel6.setText("Lichtst\u00e4rke (direkt):");

        statusLabel.setText("Status");

        jLabel7.setText("Knotenliste");

        jButton2.setText("...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Elementenliste");

        jButton3.setText("...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setText("Randbedingungsliste");

        jButton4.setText("...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Zeichne Absorber");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(startFEM, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jLabel5)
                        .add(1, 1, 1))
                    .add(layout.createSequentialGroup()
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(layout.createSequentialGroup()
                        .add(jLabel3)
                        .add(53, 53, 53))
                    .add(jTextField4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jLabel6)
                        .add(56, 56, 56))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel7))
                    .add(layout.createSequentialGroup()
                        .add(jLabel1)
                        .add(59, 59, 59))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel8))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel9))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .add(rbdFileField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .add(elementFileField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel2)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(nodeFileField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jButton3)
                                .add(jButton2))
                            .add(jButton4)))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jButton5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(scrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                    .add(statusLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                    .add(statusBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(statusLabel)
                    .add(jLabel7))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(statusBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(nodeFileField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jButton2)))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(scrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jLabel8)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(elementFileField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButton3))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel9)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(rbdFileField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButton4))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 31, Short.MAX_VALUE)
                        .add(jLabel1)
                        .add(18, 18, 18)
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel6)
                        .add(26, 26, 26)
                        .add(jButton5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(startFEM)))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        visMatrix.refreshImg(nodeList, elementList);
        visMatrix.repaint();
        /*nodeList=loadNodesFromFile(new File(nodeFileField.getText()));
        JOptionPane.showMessageDialog(null, ""+nodeList.getCount(), "Fehler Elementenliste", JOptionPane.ERROR_MESSAGE);
        elementList=loadElementsFromFile(new File(elementFileField.getText()));*/
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    JFileChooser fileChooser = new JFileChooser(".");
        int status = fileChooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.exists()) {
                rbdFileField.setText(selectedFile.toString());
            }
            
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    JFileChooser fileChooser = new JFileChooser(".");
        int status = fileChooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.exists()) {
                elementFileField.setText(selectedFile.toString());
            }
            
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fileChooser = new JFileChooser(".");
        int status = fileChooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.exists()) {
                nodeFileField.setText(selectedFile.toString());
            }
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
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
    public ElementList loadElementsFromFile(File file) {
        ElementList el= new ElementList();
        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader buffer = new BufferedReader(reader);
                
                int bufX=Integer.valueOf(buffer.readLine()).intValue();
                //double bufVector[]=new double[bufX*bufX];
                //Double bufMtrx[][]=new Double[bufX][bufX];//new double[bufX*bufX];
                int buf[] = new int[6];
                for (int i=0;i<bufX;i++) {
                    String line=buffer.readLine();
                    int position=0;
                    int start=0;
                    char Byte[]=line.toCharArray();
                    for (int j=0;j<6;j++) {
                        
                        do {
                            position++;
                        } while(position<line.length() && Byte[position]!=' ');
                        if (start!=position-1) {
                            double bufValue=Double.valueOf(line.substring(start,position)).doubleValue();
                            
                            //bufVector[bufX*j+i]=bufValue;
                            
                            buf[j]=new Double(bufValue).intValue();
                            //JOptionPane.showMessageDialog(null, "" + buf[j], " fhcgh", JOptionPane.ERROR_MESSAGE);
                            
                        }
                        start=position;
                        
                    }
                    el.addElement(new Element(nodeList.getNode(buf[1]-1),nodeList.getNode(buf[2]-1),nodeList.getNode(buf[3]-1), buf[5], 0,0,el.getCount()));
                }
                //Vector=bufVector;
      
            } catch(java.lang.Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler Elementenliste", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return el;
            
        }
        return null;
    }
    
    public NodeList loadNodesFromFile(File file) {
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
                    int position=0;
                    int start=0;
                    char Byte[]=line.toCharArray();
                    for (int j=0;j<3;j++) {
                        
                        do {
                            position++;
                        } while(position<line.length() && Byte[position]!=' ');
                        
                        double bufValue=Double.valueOf(line.substring(start,position)).doubleValue();
                        start=position;
                        //bufVector[bufX*j+i]=bufValue;
                        buf[j]=bufValue;
                    }
                    nl.addNode(new Node(buf[1],buf[2],0.0,false,nl.getCount()));
                }
                //Vector=bufVector;
      
            } catch(java.lang.Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler Knotenliste", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            return nl;
            
        }
        return null;
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
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChooser = new JFileChooser(".");
        int status = fileChooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.exists()) {
                //loadFromFile(selectedFile);
                //VectorB.drawMatrix(visMatrix,0,0, "Vektor B");
            }
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void startFEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startFEMActionPerformed
// TODO add your handling code here:
        elementList = new ElementList();
        nodeList = new NodeList();
        lineList = new LineList();
        int XCount=Integer.valueOf(jTextField1.getText()),YCount=Integer.valueOf(jTextField2.getText()), Count=0;
        for (int y=0;y<YCount;y++) {
            for (int x=0;x<XCount;x++) {
                nodeList.addNode(new Node(x*0.05,y*0.05,0.0,false,Count));
                if (y!=0) {
                    if (x>0) {
                        elementList.addElement(new Element(nodeList.getNode(Count-XCount),nodeList.getNode(Count),nodeList.getNode(Count-1),10,0,0,elementList.getCount()));
                        elementList.addElement(new Element(nodeList.getNode(Count-2*XCount+1),nodeList.getNode(Count),nodeList.getNode(Count-XCount),10,0,0,elementList.getCount()));
                    }
                    if (x<XCount-1) {
                        elementList.addElement(new Element(nodeList.getNode(Count-XCount+1),nodeList.getNode(Count),nodeList.getNode(Count-2*XCount+1),10,0,0,elementList.getCount()));
                    }
                }
                Count++;
            }
            if (y<YCount-1) {
                for (int x=0;x<XCount-1;x++) {
                    nodeList.addNode(new Node(x*0.05+0.025,y*0.05+0.025,0.0,false,Count));
                    elementList.addElement(new Element(nodeList.getNode(Count),nodeList.getNode(Count-XCount),nodeList.getNode(Count-XCount+1),10,0,0,elementList.getCount()));
                    Count++;
                    
                } 
            }
                     
        }
        for (int x=0;x<XCount-1;x++) {
            lineList.addLine(new Line(nodeList.getNode(x),nodeList.getNode(x+1),false,0.0,true,40.0,20.0));
            lineList.addLine(new Line(nodeList.getNode((2*XCount-1)*(YCount-1)+x),nodeList.getNode((2*XCount-1)*(YCount-1)+x+1),false,0.0,true,40.0,20.0));
            //lineList.addLine(new Line(nodeList.getNode(x),nodeList.getNode(x+1),false,0.0,true,40,20));
            //lineList.addLine(new Line(nodeList.getNode((2*XCount-1)*(YCount-1)+x),nodeList.getNode((2*XCount-1)*(YCount-1)+x+1),false,0.0,true,-40,20));
        }
        for (int y=0;y<YCount-1;y++) {
            lineList.addLine(new Line(nodeList.getNode((2*XCount-1)*y),nodeList.getNode((2*XCount-1)*(y+1)),true,500.0,false,0.0,0.0));
            lineList.addLine(new Line(nodeList.getNode((2*XCount-1)*y+XCount-1),nodeList.getNode((2*XCount-1)*(y+1)+XCount-1),false,0.0,true,40.0,20.0));
        }
        //nodeList.getNode(0).setU(20);
        Matrix e=new Matrix(4,elementList.getCount());
        for (int i=0;i<elementList.getCount();i++) {
            Element ele=elementList.getElement(i);
            e.setValue(0,i,ele.index);
            e.setValue(1,i,ele.getNode0().getIndex());
            e.setValue(2,i,ele.getNode1().getIndex());
            e.setValue(3,i,ele.getNode2().getIndex());
            
        }
        
        e.saveMatrixToFile(new File("elements.txt"));
        
        Matrix nl=new Matrix(3,lineList.getCount());
        for (int i=0;i<lineList.getCount();i++) {
            Line l=lineList.getLine(i);
            nl.setValue(0,i,i);
            nl.setValue(1,i,l.getNode0().getIndex());
            nl.setValue(2,i, l.getNode1().getIndex());       
        }
        nl.saveMatrixToFile(new File("lines.txt"));
        
        Matrix nodes=new Matrix(3,nodeList.getCount());
        for (int i=0;i<nodeList.getCount();i++) {
            Node n=nodeList.getNode(i);
            nodes.setValue(0,i,n.getIndex());
            nodes.setValue(1,i,n.getX());
            //JOptionPane.showMessageDialog(null, ""+nodes.getValue(1,i), "Fehler", JOptionPane.ERROR_MESSAGE);
            nodes.setValue(2,i,n.getY());       
        }
        nodes.saveMatrixToFile(new File("nodes.txt"));
        //nodeList.addNode(new Node(0.0,0.0,10.0,true,0));
        /*
         *
        nodeList.addNode(new Node(0.0,10.0,10.0,false,1));
        nodeList.addNode(new Node(5.0,5.0,0.0,false,2));
        nodeList.addNode(new Node(10.0,0.0,20.0,false,3));
        nodeList.addNode(new Node(10.0,10.0,20.0,false,4));
        lineList.addLine(new Line(nodeList.getNode(0),nodeList.getNode(1),true,-10.0,false,10,20));
        lineList.addLine(new Line(nodeList.getNode(3),nodeList.getNode(4),true,10.0,false,20,10));
        elementList.addElement(new Element(nodeList.getNode(0),nodeList.getNode(2),nodeList.getNode(1),10,0,0,0));
        elementList.addElement(new Element(nodeList.getNode(0),nodeList.getNode(3),nodeList.getNode(2),10,0,0,1));
        elementList.addElement(new Element(nodeList.getNode(3),nodeList.getNode(4),nodeList.getNode(2),10,0,0,2));
        elementList.addElement(new Element(nodeList.getNode(2),nodeList.getNode(4),nodeList.getNode(1),10,0,0,3));*/
        FEM fem= new FEM(nodeList, elementList, lineList, statusLabel, statusBar);
        try {
                fem.join();
            }
        catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage() , "Fehler", JOptionPane.ERROR_MESSAGE);
        }
        fem = new FEM(nodeList, elementList, lineList, statusLabel, statusBar);;
        fem.start();
        //if (X!=null)  X.saveMatrixToFile(new File("x.txt"));
        //visMatrix.repaint();
        
    }//GEN-LAST:event_startFEMActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField elementFileField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField nodeFileField;
    private javax.swing.JTextField rbdFileField;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JButton startFEM;
    private javax.swing.JProgressBar statusBar;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
    public MyCanvas visMatrix = new MyCanvas(nodeList, elementList);
}
