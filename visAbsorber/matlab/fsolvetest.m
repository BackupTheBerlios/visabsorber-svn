format long e
a=1;

x=0.01;

x0 = 3.01;

[x0,fval] = fsolve(@myfun,x0)  