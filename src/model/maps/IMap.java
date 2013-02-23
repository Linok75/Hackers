/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.maps;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author MyMac
 */
public interface IMap {

    public void setNode(int li, int co, Node node);
    public Node getNode(int li, int co);
    public Point getPoint(Node node);
    public Dimension getDimensionMap();
    
    public int countAllNodesHack();

    public void phishing(Node node);
    public void virus(Node node);
    public void chevalDeTroie(Node node);
    
    public boolean in0_LI(int n);
    public boolean in0_CO(int n);

    

}
