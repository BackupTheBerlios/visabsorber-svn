fid = fopen('ref_quadrat_rand.txt', 'r');
ende=421;summanden=200;
B = fscanf(fid, '%i %g %g') ;
fclose(fid)
echo off;
d = 0:1:ende;
X= [d];
Y= [d];
T= [d];
for j=0:ende-1;
    m=3*j+2;
    l=3*j+3;
    k=j+1;
    X(k)=B(m);
    Y(k)=B(l);
end;
echo on;

a=1; b=1; h=20; V=20; alpha=0.01;
%h ebenfalls in myfun anpassen
%T = zeros(m+1);
A(100)=0;
for x=1:summanden ;
    options=optimset('TolFun',0.00001,'TolX', 0.0001);
    A(x) = fsolve(@myfun,alpha,options);
    alpha=A(x)+pi;
end;
A;
for i=1:ende;
      t=0;
      for j=1:summanden;
        echo off;
        alpha=A(j);
        t1 = cos(alpha*X(i))*cosh(alpha*(b-Y(i)));
        t2 = ((alpha*alpha+h*h)*a+h)*cos(alpha*a)*cosh(alpha*b);
        t = t1/t2+t;
        T(i)=2*h*V*t;
   end;
end;

Z = [X; Y; T];
fid = fopen('analyt_solut.txt', 'wt');
fprintf(fid, '%12.8f %12.8f %12.8f\n', Z);
fclose(fid)
echo on;
%X
%[XX,YY] = meshgrid(X,Y)
%XX
%surf(XX,YY,T);
