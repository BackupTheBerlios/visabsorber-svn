/*
 * ElementList.java
 *
 * Created on 22. November 2006, 17:36
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
public class ElementList {
    Vector<Element> list;  
    /** Creates a new instance of ElementList */
    public ElementList() {
        list = new Vector<Element>();
    }
    
    public void addElement (Element e) {
        list.add(e);
    }
    
    public Element getElement(int i) {
        return (Element)list.get(i);
    }
    
    public int getCount() {
        return list.size();
    }
    
}
