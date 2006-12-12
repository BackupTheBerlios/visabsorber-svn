/*
 * Calculator.java
 *
 * Created on 4. November 2006, 15:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package visabsorber;

/**
 *
 * @author Jan-Stefan Fischer
 */
public class Calculator {
    
    /** Creates a new instance of Calculator */
    FEM fem=null;
    public Calculator() {
    }
    
    public Calculator(FEM f) {
        fem=f;
    }
    
    public void genHibert_A(Matrix MatrixA, int dim) {
        MatrixA.setDim(dim);
        for (int i=0;i<dim;i++) {
            for (int j=0;j<dim;j++) {
                MatrixA.setValue(j,i,1.0/((double)(i+1)+(double)(j+1)-1.0));
            }
        }
    }
        public void genHibert_B(Matrix VectorB, int dim) {
        VectorB.setXCount(1);
        VectorB.setYCount(dim);
        for (int i=0;i<dim;i++) {
            VectorB.setValue(0,i,(1.0/(double)(i+1.0)) - 1.0/((double)(i+1.0)+(double)dim-1.0));
            
        }
    }
    
    public String LRCalc(Matrix MatrixA, Matrix MatrixR, Matrix MatrixL) {
        int n=MatrixA.getXCount();
        Matrix bufMatrix=new Matrix(n,n);
        bufMatrix.resetVector();
        bufMatrix.Mtrx=MatrixA.Mtrx;
        /*for (int x=0; x<n;x++) {
            for (int y=0; y<n;y++) {
                bufMatrix.setValue(x,y,MatrixA.getValue(x,y));
            }
        }*/
        
        
        for (int k=0;k<n-1;k++) {
            if (fem!=null) fem.progress("LR-Zerlegung", k, n-1);
            for (int i=k+1;i<n;i++) {
                if (bufMatrix.getValue(k,k)!=0.0)
                    //try {
                    bufMatrix.setValue(k,i,bufMatrix.getValue(k,i)/bufMatrix.getValue(k,k));
                else return "Teilung durch Null!";
                //} catch(java.lang.Exception e) {
                //return "Teilung durch Null!";
                //}
                for (int j=k+1;j<n;j++) {
                    double temp=bufMatrix.getValue(j,i)-(bufMatrix.getValue(k,i)*bufMatrix.getValue(j,k));
                    bufMatrix.setValue(j,i,temp);
                }
            }
        }
        MatrixR.setDim(n);
        for (int y=0; y<n;y++) {
            for (int x=y; x<n;x++) {
                MatrixR.setValue(x,y,bufMatrix.getValue(x,y));
            }
        }
        MatrixL.setDim(n);
        for (int y=0; y<n;y++) {
            for (int x=0; x<=y;x++) {
                if (x==y) MatrixL.setValue(x,y,1.0);
                else
                    MatrixL.setValue(x,y,bufMatrix.getValue(x,y));
            }
        }
        return null;
    }
    
    public String calc_YX(Matrix MatrixL, Matrix VectorB, Matrix VectorY, int Direction) {
        int d, start, end1, end2, n=MatrixL.getYCount();
        if (n!=VectorB.getYCount()) return "Dimension von Vektor B nicht richtig";
        VectorY.setYCount(n);
        VectorY.setXCount(1);
        if (Direction==0) {
            d=+1;
            start=0;
            end1=n;
            end2=0;
        }
        else {
            d=-1;
            start=n-1;
            end1=-1;
            end2=+1;
        }
        /*for (int y=MatrixR.getYCount()-1;y>=0;y--) {
            double Summe=0.0;
            for (int x=MatrixR.getYCount()-1;x>=y;x--) {*/
        for (int y=start;y!=end1;y=y+d) {
            double Summe=0.0;
            for (int x=start;x!=(y-end2);x=x+d) {
                Summe=Summe+(MatrixL.getValue(x,y)*VectorY.getValue(0,x));
            }
            if (MatrixL.getValue(y,y)==0.0) return "Teilung durch Null!";
            VectorY.setValue(0,y,(VectorB.getValue(0,y)-Summe)/MatrixL.getValue(y,y));
        }
        return null;
    
    
    }
      
    public String MatrixMulti(Matrix MatrixA, Matrix MatrixB, Matrix Result) {
        double temp=0;
        
        if (MatrixA.getXCount()==MatrixB.getYCount()) {
            Result.setXCount(MatrixB.getXCount());
            Result.setYCount(MatrixA.getYCount());
            for (int y=0; y<MatrixA.getYCount(); y++) {
                for (int x=0; x<MatrixB.getXCount(); x++) {
                    temp=0;
                    for (int m=0;m<MatrixB.getYCount();m++) {
                        temp=temp+(MatrixA.getValue(m,y)*MatrixB.getValue(x,m));
                    }
                    Result.setValue(x,y,temp);
                }
                
            }
            return null;
        }
        return "Dimensionsfehler";
    }
    
    public String Cholesky(Matrix MatrixA, Matrix MatrixL) {
        
        int n=MatrixA.getXCount();
        MatrixL.setDim(n);
        MatrixL.resetVector();
        for (int y=0; y<n;y++) {
            for (int x=0; x<=y;x++) {
                MatrixL.setValue(x,y,MatrixA.getValue(x,y));
            }
        }
        for (int j=0;j<n;j++) {
            
            //MatrixL.setValue(j,j,Math.sqrt(MatrixL.getValue(j,j)));
            for (int i=j;i<n;i++) {
                double Summe=MatrixL.getValue(j,i);
                //if (MatrixL.getValue(j,j)==0.0) return "Teilung durch Null!";
                //MatrixL.setValue(j,i,MatrixL.getValue(j,i)/MatrixL.getValue(j,j));
                
                for (int k=i-1;k>0;k--) {
                    //MatrixL.setValue(k,i,MatrixL.getValue(k,i)-(MatrixL.getValue(j,i)*MatrixL.getValue(j,k)));
                    Summe=Summe-MatrixL.getValue(k,i)*MatrixL.getValue(k,j);
                }
                if (i==j) {
                    if (Summe<=0.0) {
                        return "A ist nicht positiv definit";
                    }
                    else {
                        if (MatrixL.getValue(i,i)==0.0) return "Teilung durch Null!";
                        MatrixL.setValue(i,j,Summe/MatrixL.getValue(i,i));
                    }
                }
            }
        }
        return null;
        /*
         *   
   For i = 1 To n
       For j = i To n
           Summe = a(i, j)
           For k = i - 1 To 1 Step -1
               Summe = Summe - a(i, k) * a(j, k)
           Next k
 
           If i = j Then
               If Summe <= 0 Then EXIT // A ist nicht positiv definit
               else a(j, i) = Sqrt(Summe) // Summe ist positiv
           Else
               a(j, i) = Summe / a(i, i)
           End If
       Next j
   Next i*/
        
        /*for (int j=0;j<n;j++) {
            MatrixL.setValue(j,j,Math.sqrt(MatrixL.getValue(j,j)));
            for (int i=j+1;i<n;i++) {
                if (MatrixL.getValue(j,j)==0.0) return "Teilung durch Null!";
                MatrixL.setValue(j,i,MatrixL.getValue(j,i)/MatrixL.getValue(j,j));
                
                for (int k=j+1;k<i;k++) {
                    MatrixL.setValue(k,i,MatrixL.getValue(k,i)-(MatrixL.getValue(j,i)*MatrixL.getValue(j,k)));
                }
            }
        }*/
    }
    
    public void calc_Failure(Matrix VectorB, Matrix VectorBF, Matrix VectorAbsFailure, Matrix VectorRelFailure) {
        VectorAbsFailure.setXCount(1);
        VectorAbsFailure.setYCount(VectorBF.getYCount());
        VectorRelFailure.setXCount(1);
        VectorRelFailure.setYCount(VectorBF.getYCount());
        for (int i=0; i<VectorBF.getYCount();i++) {
            VectorAbsFailure.setValue(0,i,VectorB.getValue(0,i)-VectorBF.getValue(0,i));
            VectorRelFailure.setValue(0,i,VectorAbsFailure.getValue(0,i)/VectorB.getValue(0,i));
        }
        
    }
}
