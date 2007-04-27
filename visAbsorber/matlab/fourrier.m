echo off;

k=20; a=1; b=1; m=10;

[X,Y] = meshgrid(0:a/m:a, 0:b/m:b);
%Y(2,1)
T = zeros(m+1);
for x=1:m+1;
   for y=1:m+1;
      t=0;
      for j=0:40;
        n=2*j+1;
        t1 = sin(n*pi/a*X(x,y));
        t2 = sinh(n*pi/a*(b-Y(x,y)));
        t3 = csch(n*pi*b/a);
        t = 1/n/pi*t1*t2*t3+t;
        T(x,y)=4*k*t;
      end;
   end;
end;
echo on;
%T
%Y(2,1)
%Y
surf(X,Y,T);

