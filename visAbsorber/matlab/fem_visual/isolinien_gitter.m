pdeplot(p,[],t,'mesh','on');
hold on;
pdeplot(p,[],t,'xydata',u,'contour','on','title','Temperaturfeld','mesh','off','colormap','autumn','xystyle','off','zdata',u);
view(0,90);
