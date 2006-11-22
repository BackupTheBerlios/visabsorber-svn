/*
 * nodeList.java
 *
 * Created on 22. November 2006, 16:22
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
public class NodeList {
    Vector<Node> list;
    /** Creates a new instance of nodeList */
    public NodeList() {
        list = new Vector<Node>();
    }
    
    public void addNode (Node n) {
        list.add(n);
    }
    
    public Node getNode (int i) {
        return (Node)list.get(i);
    }
    
    public int getCount() {
        return list.size();
    }
    
}
