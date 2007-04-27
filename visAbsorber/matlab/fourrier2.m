echo off;

a=1; b=1; m=10;h=4;V=20,alpha=0.01;
%h ebenfalls in myfun anpassen
%h=log(teta0/tetaa)/a
[X,Y] = meshgrid(0:a/m:a, 0:b/m:b);
%Y(2,1)
T = zeros(m+1);
A(100)=0;
for x=1:100 
    options=optimset('TolFun',0.00001,'TolX', 0.0001);
    A(x) = fsolve(@myfun,alpha,options);
    alpha=A(x)+pi;
end;
A
for x=1:m+1;
   for y=1:m+1;
      t=0;
      for j=1:100;
        n=j;
        echo off;
        alpha=A(j);
        %[alpha,fval]
       % options=optimset('TolFun',0.000000000001,'TolX', 0.000001);
       % alpha = fsolve(@myfun,alpha,options);
        
        t1 = cos(alpha*X(x,y))*cosh(alpha*(b-Y(x,y)));
        t2 = ((alpha*alpha+h*h)*a+h)*cos(alpha*a)*cosh(alpha*b);
        t = t1/t2+t;
        T(x,y)=2*h*V*t;
       % alpha=alpha+pi;
      end;
   end;
end;
echo on;
%T
%Y(2,1)
%Y
surf(X,Y,T);

