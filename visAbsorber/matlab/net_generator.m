echo off;
m=3, a=1
kntCount=m*m+(m-1)*(m-1)
eleCount=(m-1)*(m-1)*4;
rbdCount=(m-1)*4;
KNT=zeros(kntCount,4);
ELE = zeros(eleCount,5);
RBD = zeros(rbdCount,4);
j=1;
fid = fopen('grid.knt', 'wt');
fprintf(fid, '%i\n', kntCount);
for y=1:m;
    for x=1:m;
        KNT(j,1)=j;
        KNT(j,2)=a/(m-1)*(x-1);
        KNT(j,3)=a/(m-1)*(y-1);
        fprintf(fid, '%i %.8f %.8f\n', KNT(j,1),KNT(j,2),KNT(j,3));
        j=j+1;
    end;
    if j<kntCount;
        for x=1:m-1;
            KNT(j,1)=j;
            KNT(j,2)=a/(m-1)*(x-1)+(a/(m-1))/2;
            KNT(j,3)=a/(m-1)*(y-1)+(a/(m-1))/2;
            fprintf(fid, '%i %.8f %.8f\n', KNT(j,1),KNT(j,2),KNT(j,3));
            j=j+1;
        end;
    end;
    
end;
fclose(fid);
k=1;
fid = fopen('grid.ele', 'wt');
fprintf(fid, '%i\n', eleCount);
for y=1:(m-1);
    kntnr=y*m+(y-1)*(m-1)+1;
    for x=1:(m-1);
        
        %
        ELE(k,1)=k;
        ELE(k,2)=kntnr;
        ELE(k,4)=kntnr-m+1;
        ELE(k,3)=kntnr-m;   
        fprintf(fid, '%i %i %i %i  %i\n', ELE(k,1), ELE(k,2), ELE(k,3), ELE(k,4), ELE(k,5));
        %
        k=k+1;        
        ELE(k,1)=k;
        ELE(k,2)=kntnr;
        ELE(k,4)=kntnr-m;  
        ELE(k,3)=kntnr+m-1;
        fprintf(fid, '%i %i %i %i  %i\n', ELE(k,1), ELE(k,2), ELE(k,3), ELE(k,4), ELE(k,5));
        %
        k=k+1;
        ELE(k,1)=k;
        ELE(k,2)=kntnr;
        ELE(k,4)=kntnr+m-1;
        ELE(k,3)=kntnr+m;
        fprintf(fid, '%i %i %i %i  %i\n', ELE(k,1), ELE(k,2), ELE(k,3), ELE(k,4), ELE(k,5));
        %
        k=k+1;        
        ELE(k,1)=k;
        ELE(k,2)=kntnr;
        ELE(k,4)=kntnr+m;
        ELE(k,3)=kntnr-m+1;
        fprintf(fid, '%i %i %i %i  %i\n', ELE(k,1), ELE(k,2), ELE(k,3), ELE(k,4), ELE(k,5));
        %
        k=k+1; 
        kntnr=kntnr+1;
    end;
end;
fclose(fid);
k=1;
fid = fopen('grid.rbd', 'wt');
fprintf(fid, '%i\n', rbdCount);
for y=1:(m-1);
    RBD(k,1)=k;
    RBD(k,2)=y;
    RBD(k,3)=y+1;
    RBD(k,4)=0;
    fprintf(fid, '%i %i %i  %i\n', RBD(k,1),RBD(k,2),RBD(k,3),RBD(k,4));
    k=k+1;
end;
for y=1:(m-1);
    RBD(k,1)=k;
    RBD(k,2)=(y-1)*m+(y-1)*(m-1)+1;
    RBD(k,3)=y*m+y*(m-1)+1;
    RBD(k,4)=1;
    fprintf(fid, '%i %i %i  %i\n', RBD(k,1),RBD(k,2),RBD(k,3),RBD(k,4));
    k=k+1;
end;
for y=1:(m-1);
    RBD(k,1)=k;
    RBD(k,2)=(m-1)*m+(m-1)*(m-1)+y;
    RBD(k,3)=(m-1)*m+(m-1)*(m-1)+y+1;
    RBD(k,4)=2;
    fprintf(fid, '%i %i %i  %i\n', RBD(k,1),RBD(k,2),RBD(k,3),RBD(k,4));
    k=k+1;
end;
for y=1:(m-1);
    RBD(k,1)=k;
    RBD(k,2)=y*m+(y-1)*(m-1);
    RBD(k,3)=(y+1)*m+y*(m-1);
    RBD(k,4)=3;
    fprintf(fid, '%i %i %i  %i\n', RBD(k,1),RBD(k,2),RBD(k,3),RBD(k,4));
    k=k+1;
end;
fclose(fid);

KNT
ELE
RBD

b=a; h=4; V=20; alpha=0.01; summanden=200; 
%h ebenfalls in myfun anpassen
%T = zeros(m+1);
A(summanden)=0;
for x=1:summanden ;
    options=optimset('TolFun',0.00001,'TolX', 0.0001);
    A(x) = fsolve(@myfun,alpha,options);
    alpha=A(x)+pi;
end;
A;

fid = fopen('grid_sol_ana.knt', 'wt');
fprintf(fid, '%i\n', kntCount);
for i=1:kntCount;
      t=0;
      for j=1:summanden;
        echo off;
        alpha=A(j);
        t= cos(alpha*KNT(i,2))*cosh(alpha*(b-KNT(i,3)))/(((alpha*alpha+h*h)*a+h)*cos(alpha*a)*cosh(alpha*b))+t;
        %t1 = cos(alpha*X(i))*cosh(alpha*(b-Y(i)));
        %t2 = (((alpha*alpha+h*h)*a+h)*cos(alpha*a)*cosh(alpha*b));
        %t = t1/t2+t;
        
   end;
   KNT(i,4)=2*h*V*t;
   fprintf(fid, '%i %.8f %.8f %.8f\n', KNT(i,1),KNT(i,2),KNT(i,3),KNT(i,4));
end;
fclose(fid);
