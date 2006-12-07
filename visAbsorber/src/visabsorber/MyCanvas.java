package visabsorber;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;


/** Creates new form MainFrame */
class MyCanvas extends Canvas {
    public MyCanvas(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    private final MainFrame mainFrame;
   /* 
    Raster3_scan(vert v[3])
    v0 {
        l2
                int y;
        l1
                edge l, r; value ybot, ymid, ytop;
                v2
                        v1
                        ybot = ceil(v[0].y);
                l0
                        ymid = ceil(v[1].y);
                ytop = ceil(v[2].y);
                differencey(v[0],v[2],&l,ybot);
                differencey(vert a, vert b,
                        differencey(v[0],v[1],&r,ybot);
                edge* e, int y) {
                    e->dxdy=(b.x-a.x)/(b.y-a.y);
                    for( y=ybot; y<ymid; y++ ) {
                        e->x=a.x+(y-a.y)*e->dxdy;
                        scanx(l,r,y);
                    }
                    l.x += l.dxdy; r.x += r.dxdy;
                }
                scanx(edge l, edge r, int y){
                    differencey(v[1],v[2],&r,ymid);
                    lx= ceil(l.x);
                    for( y=ybot; y<ymid; y++ ) {
                        rx= ceil(r.x);
                        scanx(l,r,y);
                        for (x=lx; x < rx; x++)
                            l.x += l.dxdy; r.x += r.dxdy;
                            fragment(x,y);
                    }
                }
    }*/
    
    
    public void drawDreieck(int ax, int ay, double au, int bx, int by, double bu, int cx, int cy, double  cu, Graphics g) {
        int h;
        
        if (ay>by) {
            h = ay; ay = by; by = h;
            h = ax; ax = bx; bx = h;
        }
        if (by>cy) {
            h = by; by = cy; cy = h;
            h = bx; bx = cx; cx = h;
        }
        
        if (ay>by) {
            h = ay; ay = by; by = h;
            h = ax; ax = bx; bx = h;
        }
        
        Matrix A=new Matrix(3,3);
        for (int i=0;i<3;i++) {
            A.setValue(i,0,1.0);
        }
        A.setValue(0,1,ax);
        A.setValue(0,2,ay);
        A.setValue(1,1,bx);
        A.setValue(1,2,by);
        A.setValue(2,1,cx);
        A.setValue(2,2,cy);
        
        Calculator calc=new Calculator();
        Matrix MatrixL = new Matrix  (3,3);
        Matrix MatrixR = new Matrix (3,3);
        String failure=calc.LRCalc(A, MatrixR, MatrixL);
        if (failure==null) {
            int y;
            double x1,x2,x3;
            double m1,m2,m3;
            
            x1  = ax;
            x2  = bx;
            x3  = ax;
            
            m1 = (bx-ax) / (by-ay);
            m2 = (bx-cx) / (by-cy);
            m3 = (cx-ax) / (cy-ay);
            
            for (y = ay; y< by-1;y++){
                long xstart=Math.round(x1);
                long xend=Math.round(x3);
                for (long i=xstart;i<=xend;i++) {
                    Matrix t=new Matrix(1,3);
                    Matrix yVector=new Matrix(1,3);
                    Matrix b=new Matrix(1,3);
                    b.setValue(0,0,1.0);
                    b.setValue(0,1,i);
                    b.setValue(0,1,y);
                    failure=calc.calc_YX(MatrixL, b, yVector,0); 
                    if (failure==null) {
                        failure=calc.calc_YX(MatrixR, yVector, t,1);
                        if (failure==null) {
                            double u = au*t.getValue(0,0)+bu*t.getValue(0,1)+cu*t.getValue(0,2);
                            int xi= new Long(i).intValue();
                            //int r= new Double(2*(u-20)).intValue();
                            if (u<0.0||u>255.0) {
                                //JOptionPane.showMessageDialog(null, ""+au, "2", JOptionPane.ERROR_MESSAGE);
                            }
                            else g.setColor(new Color(new Double(u).intValue(), 0, 0));
                            g.drawLine(xi,y, xi,y);
                        }
                    }
                }
                //Line (round(x1), y, round(x3), y, color); { horiz. Linie          }
                
                
                
                x1 = x1 + m1;
                x3 = x3 + m3;
            }
            
            
            for (y = by; y< cy-1;y++){
                long xstart=Math.round(x1);
                long xend=Math.round(x3);
                for (long i=xstart;i<=xend;i++) {
                    Matrix t=new Matrix(1,3);
                    Matrix yVector=new Matrix(1,3);
                    Matrix b=new Matrix(1,3);
                    b.setValue(0,0,1.0);
                    b.setValue(0,1,i);
                    b.setValue(0,1,y);
                    failure=calc.calc_YX(MatrixL, b, yVector,0); 
                    if (failure==null) {
                        failure=calc.calc_YX(MatrixR, yVector, t,1);
                        if (failure==null) {
                            double u = au*t.getValue(0,0)+bu*t.getValue(0,1)+cu*t.getValue(0,2);
                            //JOptionPane.showMessageDialog(null, ""+au, "2", JOptionPane.ERROR_MESSAGE);
                            int xi= new Long(i).intValue();
                            int r= new Double(2*(u-40)).intValue();
                            if (u<0.0||u>255.0) {
                                //JOptionPane.showMessageDialog(null, ""+au, "2", JOptionPane.ERROR_MESSAGE);
                            }
                            else g.setColor(new Color(new Double(u).intValue(), 0, 0));
                            
                            g.drawLine(xi,y, xi,y);
                        }
                    }
                }
                x2 = x2 + m2;
                x3 = x3 + m3;
            }
        }
        
        
        
    }
    
    public void paint(Graphics g) {
        /*for (int i = 0; i < this.mainFrame.nodeList.getCount(); i++) {
            int x = Double.valueOf(mainFrame.nodeList.getNode(i).getX()*100.0).intValue();
            int y = Double.valueOf(mainFrame.nodeList.getNode(i).getY()*100.0).intValue();
            g.drawString("" + this.mainFrame.nodeList.getNode(i).getIndex(),x+30,y+30);
         
        }
        for (int i = 0; i < this.mainFrame.lineList.getCount(); i++) {
            int x0 = Double.valueOf(mainFrame.lineList.getLine(i).getNode0().getX()*100.0).intValue();
            int y0 = Double.valueOf(mainFrame.lineList.getLine(i).getNode0().getY()*100.0).intValue();
            int x1 = Double.valueOf(mainFrame.lineList.getLine(i).getNode1().getX()*100.0).intValue();
            int y1 = Double.valueOf(mainFrame.lineList.getLine(i).getNode1().getY()*100.0).intValue();
            g.drawLine(x0+30,y0+30,x1+30,y1+30);
        }
        g.setColor(Color.RED);*/
        for (int i = 0; i < this.mainFrame.elementList.getCount(); i++) {
            int x0 = Double.valueOf(mainFrame.elementList.getElement(i).getNode0().getX()*100.0).intValue();
            int y0 = Double.valueOf(mainFrame.elementList.getElement(i).getNode0().getY()*100.0).intValue();
            int x1 = Double.valueOf(mainFrame.elementList.getElement(i).getNode1().getX()*100.0).intValue();
            int y1 = Double.valueOf(mainFrame.elementList.getElement(i).getNode1().getY()*100.0).intValue();
            int x2 = Double.valueOf(mainFrame.elementList.getElement(i).getNode2().getX()*100.0).intValue();
            int y2 = Double.valueOf(mainFrame.elementList.getElement(i).getNode2().getY()*100.0).intValue();
            drawDreieck(x0+30, y0+30, mainFrame.elementList.getElement(i).getNode0().getU(), x1+31,  y1+31, mainFrame.elementList.getElement(i).getNode1().getU(), x2+30,  y2+30, mainFrame.elementList.getElement(i).getNode2().getU(),  g);
            g.drawLine(x0+30,y0+30,x1+30,y1+30);
            g.drawLine(x0+30,y0+30,x2+30,y2+30);
            g.drawLine(x2+30,y2+30,x1+30,y1+30);
        }
        
    }
}