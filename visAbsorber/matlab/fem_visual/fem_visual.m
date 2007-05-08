clear;



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
%Hier muessen die Dateiformate eingetragen werden
%
%Konstanten fuer die Dateiformate
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



% Anzahl der Daten pro Zeile in der Elementtabelle 
% (bei "velvet" normalerweise = 6; bei "netz" normalerweise = 4)

% bzw. der Knotendatei  
% (bei "velvet" normalerweise =4; bei "netz" normalerweise = 4)

zeilenlaenge_elementdatei = 4;
zeilenlaenge_knotendatei  = 4;



% Datennr. des ersten einzulesenden Datums
%
% Achtung: Die Knotenkoordinaten EINES Knotens und die Knotennummern 
% EINES Elements muessen nebeneinander in der jeweiligen Datei stehen
% 
% erster_elementknoten: 1.Knoten in Elementtabelle (normalerweise bei "velvet" 
% und "netz" = 3)
% erste_knotenkoordinate: 1.Koordinate in der Knotendatei 
% (normalerweise bei "velvet" und "netz" = 3)
% erste_temperatur: 1.Temperatur in der Knotentabelle (normalerweise bei 
% "velvet" und "netz" = 5)


erster_elementknoten = 3;
erste_knotenkoordinate = 3;
erste_temperatur = 5;



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
% Hier muessen die Dateinamen eingetragen werden:
%
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


% Nachstehend zwei Beispiele zum Ausprobieren.


% 1.Beispiel: es wurde mit "velvet" erstellt!

knotendatei  = 'grid_fehler10.knt';
elementdatei = 'grid10.ele';

% 2.Beispiel: es wurde mit "netz" erstellt!

%knotendatei  = 'kniso.tab';
%elementdatei = 'nel.tab';


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
% Einlesen der Knoten- und Elementtabellen
% p: Matrix mit Knotenkoordinaten
% u: Vektor der Temperaturen in den Knoten
% t: Matrix der Knotennummern pro Element
%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

fid = fopen(knotendatei);
a = fscanf(fid,'%g');
fclose(fid);

i = a(1);
for j=1:i
for k=0:1
p(j,k+1)=a((j-1)*zeilenlaenge_knotendatei+k+erste_knotenkoordinate);
end
u(j)=a((j-1)*zeilenlaenge_knotendatei+erste_temperatur);
end
p=p';
u=u';


fid = fopen(elementdatei);
b = fscanf(fid,'%g');
fclose(fid); 

i=b(1);
for j=1:i
for k=0:2
t(j,k+1)=b((j-1)*zeilenlaenge_elementdatei+k+erster_elementknoten);
end
end 
t=t';  


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
%Menue fuer  graphische Ausgabe mit Aufruf des jeweiligen M-Files
%
%Hintergrund: mit Hintergrundfarbe und Isolinien
%Isolinien:   ohne Hintergrundfarbe und mit Isolinien
%
%mit Gitter:  mit Dreiecksgitter
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



f= uimenu('Label','Display');
uimenu(f,'Label','Gitter','Callback','gitter');
uimenu(f,'Label','Hintergrundfarbe','Callback','hintergrund');
uimenu(f,'Label','mit Gitter','Callback','hintergrund_gitter');
uimenu(f,'Label','Isolinien','Callback','isolinien');
uimenu(f,'Label','mit Gitter','Callback','isolinien_gitter');
uimenu(f,'Label','Exit Display','Callback','close');


