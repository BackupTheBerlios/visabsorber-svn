/*
 * LineList.java
 *
 * Created on 23. November 2006, 15:17
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
public class LineList {
    
    /** Creates a new instance of LineList */
    Vector<Line> list;  
    public LineList() {
    }
        
    /** Creates a new instance of ElementList */
    
    public void addLine (Element e) {
        list = new Vector<Line>();
    }
    
    public Line getLine(int i) {
        return (Line)list.get(i);
    }
    
    public int getCount() {
        return list.size();
    }
    
}
