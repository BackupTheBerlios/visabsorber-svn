package visabsorber;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/** Creates new form MainFrame */
class MyCanvas extends Canvas {
    NodeList nodeList;
    ElementList elementList;
    Image imgTemp, imgGird;
    int offsetX=0, offsetY=0, minX=0, minY=0, maxX=0,maxY=0;
    double zoom=500.0, minU=0, maxU=0;
    
    public MyCanvas(NodeList nl, ElementList el) {
        nodeList=nl;
        elementList=el;
    }
    
    public void zoomPlus() {
        zoom=zoom*2;
    } 
    public void zoomMinus() {
        zoom=zoom/2;
    } 
    
    public void refreshImg(NodeList nl, ElementList el) {
        if (nl.getCount()>0) {
            nodeList=nl;
            elementList=el;
            Node node = nodeList.getNode(0);
            minX=Double.valueOf(node.getX()*zoom).intValue();
            minY=Double.valueOf(node.getY()*zoom).intValue();
            maxX=Double.valueOf(node.getX()*zoom).intValue();
            maxY=Double.valueOf(node.getY()*zoom).intValue();
            minU=node.getU();
            maxU=node.getU();
            for (int i=1;i<nodeList.getCount();i++) {
                node = nodeList.getNode(i);
                int bufX=Double.valueOf(node.getX()*zoom).intValue();
                int bufY=Double.valueOf(node.getY()*zoom).intValue();
                double bufU=node.getU();
                if (minX>bufX) minX=bufX;
                if (minY>bufY) minY=bufY;
                if (maxX<bufX) maxX=bufX;
                if (maxY<bufY) maxY=bufY;
                if (minU>bufU) minU=bufU;
                if (maxU<bufU) maxU=bufU;
            }
            //JOptionPane.showMessageDialog(null, ""+minU, "1", JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(null, ""+maxU, "2", JOptionPane.ERROR_MESSAGE);
            offsetX=0-minX;
            offsetY=0-minY;
            //Canvas canvas= new Canvas();
            //canvas.setSize(maxX-minX,maxY-minY);
            int h=maxX+offsetX;//Double.valueOf((maxX+offsetX)*zoom).intValue();
            int w=maxY+offsetY;//Double.valueOf((maxX+offsetX)*zoom).intValue();
            imgTemp=new BufferedImage(h, w,BufferedImage.TYPE_INT_ARGB); 
            Graphics gTemp=imgTemp.getGraphics();
            imgGird=new BufferedImage(h, w,BufferedImage.TYPE_INT_ARGB); 
            Graphics gGird=imgGird.getGraphics();
            
            
            //this.getGraphics()
            for (int i = 0; i < elementList.getCount(); i++) {
                //g.setColor(new Color(new Double(mainFrame.elementList.getElement(i).getlamda()*100.0).intValue(), 0, 0));
                int x0 = Double.valueOf(elementList.getElement(i).getNode0().getX()*zoom).intValue();
                int y0 = Double.valueOf(elementList.getElement(i).getNode0().getY()*zoom).intValue();
                int x1 = Double.valueOf(elementList.getElement(i).getNode1().getX()*zoom).intValue();
                int y1 = Double.valueOf(elementList.getElement(i).getNode1().getY()*zoom).intValue();
                int x2 = Double.valueOf(elementList.getElement(i).getNode2().getX()*zoom).intValue();
                int y2 = Double.valueOf(elementList.getElement(i).getNode2().getY()*zoom).intValue();
                drawDreieck(x0, y0, elementList.getElement(i).getNode0().getU(), x1,  y1, elementList.getElement(i).getNode1().getU(), x2,  y2, elementList.getElement(i).getNode2().getU(),  gTemp);
                gGird.drawLine(x0,y0,x1,y1);
                gGird.drawLine(x0,y0,x2,y2);
                gGird.drawLine(x2,y2,x1,y1);
            }
            //img=canvas.createImage(maxX-minX,maxY-minY);
        }
        
        
    }
    
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
    
    public Color clacTempColor(double u) {
        /*double mTemp=765.0/(maxU-minU);
        double offsetTemp=(4*minU+maxU)/3.0;
        int re=Double.valueOf(mTemp*(u-offsetTemp)).intValue();
        if (re<0)re=0;
        if (re>255)re=255;
        int gr=Double.valueOf(-mTemp*Math.abs(u-offsetTemp)+255).intValue();
        if (gr<0)gr=0;
        if (gr>255)gr=255;
        int bl=Double.valueOf(-mTemp*(u-(4*minU+maxU)/3.0)+255).intValue();
        if (bl<0)bl=0;
        if (bl>255)bl=255;*/
        return  Color.getHSBColor(Double.valueOf((-240.0/(maxU-minU)*(u-maxU))/360.0).floatValue(), 1,  1) ;
    }
    
    public double calcU(Matrix A, Matrix L, Matrix R, double x, double y, double au,double bu, double cu) {
        Matrix t=new Matrix(1,3);
        Matrix b=new Matrix(1,3);
        b.setValue(0,0,1.0);
        b.setValue(0,1,x);
        b.setValue(0,2,y);
        Calculator calc=new Calculator();
        Matrix Y=new Matrix(1, 3);
        
        calc.calc_YX(L, b, Y,0);
        calc.calc_YX(R, Y, t,1);
        //calc.clacJacobi(A,b,t,100);
        return au*t.getValue(0,0)+bu*t.getValue(0,1)+cu*t.getValue(0,2);
    }
    
    public void drawDreieck(int ax, int ay, double au, int bx, int by, double bu, int cx, int cy, double  cu, Graphics g) {
        int h;
        double h1;
        
        
        
        if (ay<by) {
            h = ay; ay = by; by = h;
            h = ax; ax = bx; bx = h;
            h1= au; au = bu; bu = h1;
        }
        if (by<cy) {
            h = by; by = cy; cy = h;
            h = bx; bx = cx; cx = h;
            h1= bu; bu = cu; cu = h1;
        }
        
        if (ay<by) {
            h = ay; ay = by; by = h;
            h = ax; ax = bx; bx = h;
            h1= au; au = bu; cu = h1;
        }
        Matrix L = new Matrix  (3,3);
        Matrix R = new Matrix(3,3);
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
        String failure=calc.LRCalc(A, R, L);
        
        //int y;
        int xA,xE, dir;
        double mAB,mBC,mAC;
        
        for (int y=ay;y>by;y--) {
            xA=Math.round(Float.valueOf((ax-bx)/(ay-by)*(y-ay)+ax));
            xE=Math.round(Float.valueOf((ax-cx)/(ay-cy)*(y-ay)+ax));
            if (xE-xA==0) {
                dir=1;
            } else dir=(xE-xA)/Math.abs(xE-xA);
            for (int x=xA;x!=xE+dir;x=x+dir) {
                double u = calcU(A, L, R, x, y, au,bu, cu);
                g.setColor(clacTempColor(u));
                g.drawLine(x,y, x,y);
            }
        }
        for (int y=by;y>cy;y--) {
            xA=Math.round(Float.valueOf((bx-cx)/(by-cy)*(y-by)+bx));
            xE=Math.round(Float.valueOf((ax-cx)/(ay-cy)*(y-ay)+ax));
            if (xE-xA==0) {
                dir=1;
            } else dir=(xE-xA)/Math.abs(xE-xA);
            for (int x=xA;x!=xE+dir;x=x+dir) {
                double u = calcU(A, L, R, x, y, au,bu, cu);
                g.setColor(clacTempColor(u));
                g.drawLine(x,y, x,y);
            }
        }
        
        
        
    }
    
    public void paint(Graphics g) {
        //refreshImg(nodeList, elementList);
        //this.setSize(new Dimension(imgTemp.getWidth(null),imgTemp.getHeight(null)));
        g.drawImage(imgTemp,0,0, null);
        
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
        /*for (int i = 0; i < this.mainFrame.elementList.getCount(); i++) {
            //g.setColor(new Color(new Double(mainFrame.elementList.getElement(i).getlamda()*100.0).intValue(), 0, 0));
            int x0 = Double.valueOf(mainFrame.elementList.getElement(i).getNode0().getX()*10000.0).intValue();
            int y0 = Double.valueOf(mainFrame.elementList.getElement(i).getNode0().getY()*10000.0).intValue();
            int x1 = Double.valueOf(mainFrame.elementList.getElement(i).getNode1().getX()*10000.0).intValue();
            int y1 = Double.valueOf(mainFrame.elementList.getElement(i).getNode1().getY()*10000.0).intValue();
            int x2 = Double.valueOf(mainFrame.elementList.getElement(i).getNode2().getX()*10000.0).intValue();
            int y2 = Double.valueOf(mainFrame.elementList.getElement(i).getNode2().getY()*10000.0).intValue();
            //drawDreieck(x0+30, y0+30, mainFrame.elementList.getElement(i).getNode0().getU(), x1+31,  y1+31, mainFrame.elementList.getElement(i).getNode1().getU(), x2+30,  y2+30, mainFrame.elementList.getElement(i).getNode2().getU(),  g);
            g.drawLine(x0+1000,y0+1000,x1+1000,y1+1000);
            g.drawLine(x0+1000,y0+1000,x2+1000,y2+1000);
            g.drawLine(x2+1000,y2+1000,x1+1000,y1+1000);
        }*/
        
    }
}