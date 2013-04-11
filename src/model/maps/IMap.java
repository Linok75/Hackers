/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.maps;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author MyMac
 */
public interface IMap {

    public void setNode(int li, int co, Node node);
    public Node getNode(int li, int co);
    public Point getPoint(Node node);
    public Dimension getDimensionMap();

    public boolean isNearTo(Node nS, Node nT);

    public int countAllNodesHack();

    public ArrayList<ArrayList<Node>> phishing(Node node);
    public ArrayList<ArrayList<Node>> virus(Node node);
    public ArrayList<ArrayList<Node>> trojan(Node node);
    public ArrayList<ArrayList<Node>> effraction(Node node);
    public ArrayList<ArrayList<Node>> exploitation(Node node);
    public ArrayList<ArrayList<Node>> bruteForcing (Node node);

    public boolean in0_LI(int n);
    public boolean in0_CO(int n);



}
