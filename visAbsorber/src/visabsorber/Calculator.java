/*
 * Calculator.java
 *
 * Created on 4. November 2006, 15:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package visabsorber;

import java.util.Vector;

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
        } else {
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
            //Result.setXCount(MatrixB.getXCount());
            //Result.setYCount(MatrixA.getYCount());
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
                    } else {
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
        /*VectorAbsFailure.setXCount(1);
        VectorAbsFailure.setYCount(VectorBF.getYCount());
        VectorRelFailure.setXCount(1);
        VectorRelFailure.setYCount(VectorBF.getYCount());*/
        for (int i=0; i<VectorBF.getYCount();i++) {
            VectorAbsFailure.setValue(0,i,VectorB.getValue(0,i)-VectorBF.getValue(0,i));
            //VectorRelFailure.setValue(0,i,VectorAbsFailure.getValue(0,i)/VectorB.getValue(0,i));
        }
        
    }
    
    public int max1(Matrix vector, int jmax, int absVal) {
        double tmp1 = 0, tmp2 = 0;
        int j, n=0;
        
        for (j=0; j<jmax; j++) {
            if (absVal == 1) {
                if (vector.getValue(0,j) < 0) {
                    tmp1=-vector.getValue(0,j);
                } else {
                    tmp1=vector.getValue(0,j);
                }
            }
            
            if (tmp1 > tmp2) {
                tmp2 = tmp1;
                n = j;
            }
        }
        
        // GEBE DIE ZEILE MIT DEM MAXIMALEN WERT ZURÜCK
        return n;
    }
    
    public Vector<Integer>[] optS(Matrix S) {
        Vector<Integer>[] optS;
        optS=new <Integer>Vector[S.getYCount()];
        int n=S.getYCount();
        for (int y=0;y<n;y++)
        {
            fem.progress("optimiere S" ,y,n);
            Vector<Integer> line = new Vector<Integer>();
            for (int x=0;x<S.getXCount();x++) {
                if (S.getValue(x,y)!=0.0) {
                    line.add(new Integer(x));
                }
            }
            optS[y]=line;
            
        }
        return optS;
    }
    
    public double calcGauss(Matrix A, Matrix b, Matrix x, int maxc, double eps, double w) {
        int n=A.getXCount();
        //x.setXCount(1);
        //x.setYCount(n);
        //Matrix internalArray = new Matrix(n,n);
        Matrix tempArray = new Matrix(1,n);
        Matrix res=new Matrix(1,n);
        double Residuum=eps+1,globRes=0;
        Vector<Integer>[] oS = optS(A);
        for (int k=0;k<maxc && Residuum > eps;k++) {
            if (fem!=null) fem.progress("Gauss (Residuum: " + Residuum + ")",k,maxc);
            double normr=0.;
            for (int i=0;i<n;i++) {

                double sum = 0;
                Vector<Integer> line = oS[i];
                for (int j=0;j<line.size();j++) {
                    int x1= line.get(j).intValue();
                    if (x1<i) sum = sum + A.getValue(x1,i)*tempArray.getValue(0,x1);
                    if (x1>i) sum = sum + A.getValue(x1,i)*x.getValue(0,x1);
                }
                /*for (int j=0; j<i; j++) {
                    sum = sum + A.getValue(j,i)*tempArray.getValue(0,j);
                }
                for (int j=i+1; j<n; j++) {
                    sum = sum + A.getValue(j,i)*x.getValue(0,j);

                }*/
                // DIAGONALELEMENT A_ii = matrixPtr[i*(jmax+1)]
                tempArray.setValue(0,i,w*(1.0/A.getValue(i,i)*(b.getValue(0,i) - sum)) + (1.0-w)*x.getValue(0,i));
            }
            
            
            for (int i=0;i<n;i++) {
                res.setValue(0,i,tempArray.getValue(0,i)-x.getValue(0,i));
                x.setValue(0,i,tempArray.getValue(0,i));
               
            }
            Matrix bF =new Matrix(1,n);
            /*MatrixMulti(A, x, bF);
            calc_Failure(b, bF, res, null);*/
            int m = max1(res, n, 1);
            double ResiduumOld=Residuum;
            if (res.getValue(0,m) < 0) {
                Residuum=-res.getValue(0,m);
                        /*// BERECHNE GLOBALES RESIDUUM
                        globRes = 0;
                        for (int j=0; j<n; j++) {
                                globRes = globRes + res.getValue(0,j);
                        }
                        if (globRes < 0) globRes=globRes*(-1);*/
            } else {
                Residuum=+res.getValue(0,m);
                // BERECHNE GLOBALES RESIDUUM
                        /*globRes = 0;
                        for (int j=0; j<n; j++) {
                                globRes = globRes + res.getValue(0,j);
                        }
                        if (globRes < 0) globRes=globRes*(-1);*/
            }
            if (ResiduumOld<Residuum) w=w+0.01;
            else w=w-0.01;
            if (w<0.1) w=0.1;
            
        }
        return Residuum;
        

    }
    
    public double clacJacobi(Matrix A, Matrix b, Matrix x, int maxc, double eps) {
        int n=A.getXCount();
        //Matrix internalArray = new Matrix(n,n);
        Matrix tempArray = new Matrix(1,n);
        Matrix res=new Matrix(1,n);
        double Residuum=eps+1,globRes=0;
        
        for (int k=0;k<maxc && Residuum > eps;k++) {
            if (fem!=null) fem.progress("Jacobi (Residuum: " + Residuum + ")",k,maxc);
            double normr=0.;
            for (int i=0;i<n;i++) {
                /*double c=0;
                for (int j=0;j<n;j++) {
                    if (j!=i) c = c+ A.getValue(j,i)*x.getValue(0,j);
                }
                c=(b.getValue(0,i)-c)/A.getValue(i,i);
                tempArray.setValue(0,i,c);*/
                
                double sum = 0;
                for (int j=0; j<n; j++) {
                    //ij = (i*jmax)+ j;
                    // BERECHNE DIE SUMME MIT DEM ALTEN LSG-VEKTOR
                    if (i != j) {
                        sum = sum + A.getValue(j,i)*x.getValue(0,j);
                    }
                }
                // DIAGONALELEMENT A_ii = matrixPtr[i*(jmax+1)]
                tempArray.setValue(0,i,1.0/A.getValue(i,i)*(b.getValue(0,i) - sum));
            }
            
            
            for (int i=0;i<n;i++) {
                res.setValue(0,i,tempArray.getValue(0,i)-x.getValue(0,i));
                x.setValue(0,i,tempArray.getValue(0,i));
               
            }
            Matrix bF =new Matrix(1,n);
            /*MatrixMulti(A, x, bF);
            calc_Failure(b, bF, res, null);*/
            int m = max1(res, n, 1);
            if (res.getValue(0,m) < 0) {
                Residuum=-res.getValue(0,m);
                        /*// BERECHNE GLOBALES RESIDUUM
                        globRes = 0;
                        for (int j=0; j<n; j++) {
                                globRes = globRes + res.getValue(0,j);
                        }
                        if (globRes < 0) globRes=globRes*(-1);*/
            } else {
                Residuum=+res.getValue(0,m);
                // BERECHNE GLOBALES RESIDUUM
                        /*globRes = 0;
                        for (int j=0; j<n; j++) {
                                globRes = globRes + res.getValue(0,j);
                        }
                        if (globRes < 0) globRes=globRes*(-1);*/
            }
            
        }
        return Residuum;
    }
    
    public String calcLUShort (Matrix MatrixA, Matrix b, Matrix x) {
        int n=MatrixA.getXCount();
         for (int k=0;k<n-1;k++) {
            if (fem!=null) fem.progress("LR-Zerlegung",k,n-1);
            for (int i=k+1;i<n;i++) {
                MatrixA.setValue(k,i,MatrixA.getValue(k,i)/MatrixA.getValue(k,k));
                
                //} catch(java.lang.Exception e) {
                //return "Teilung durch Null!";
                //}
                for (int j=k+1;j<n;j++) {
                    double temp=MatrixA.getValue(j,i)-(MatrixA.getValue(k,i)*MatrixA.getValue(j,k));
                    MatrixA.setValue(j,i,temp);
                }
            }
        }
        Matrix y=new Matrix(1,n);
        for (int i=0;i<n;i++) {
            if (fem!=null) fem.progress("Vorwärtsrechnung",i,n);
            double sum=0.0;
            for (int k=0;k<i;k++) {
                double Lik=MatrixA.getValue(k,i);
                sum=sum+(Lik*y.getValue(0,k));
            }
            y.setValue(0,i,(b.getValue(0,i)-sum));
        }
        
        for (int i=n-1;i>-1;i--) {
            if (fem!=null) fem.progress("Rückwärtsrechnung",n-i,n);
            double sum=0.0;
            for (int k=i+1;k<n;k++) {
                double Rik;
                Rik=MatrixA.getValue(k,i);
                sum=sum+(Rik*x.getValue(0,k));
            }
            x.setValue(0,i,(y.getValue(0,i)-sum)/MatrixA.getValue(i,i));
        }
     return null;
    
    }
}
