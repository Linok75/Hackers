/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.maps;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import model.ressources.attacks.*;

/**
 *
 * @author bpetit
 */
public final class MapHexa implements IMap {

    public final static String NODEDEFAULTPATH = "../../ressources/defaultPortrait.png";
    public final static String NODEDEFAULTDESC = "Description encore inconnue...";
    private static final int LI = 7;
    private static final int CO = 9;
    private Node nodes[][]; // [LI][CO]

    public MapHexa() {
        this.nodes = new Node[LI][CO];
        for (int li = 0; li < LI; li++) {
            for (int co = 0; co < CO; co++) {
                this.nodes[li][co] = new Node(null, NODEDEFAULTDESC, NODEDEFAULTPATH);
            }
        }
    }

    public Node getNode(int li, int co) {
        if (this.in0_LI(li) && this.in0_CO(co)) {
            return nodes[li][co];
        } else {
            return null;
        }

    }

    public int countAllNodesHack() {
        int nbNodesHack = 0;
        for (Node[] n1 : nodes) {
            for (Node node : n1) {
                if (node.isHack) {
                    nbNodesHack++;
                }
            }
        }
        return nbNodesHack;
    }

    public int nbNodes() {
        return LI * CO;
    }

    public boolean in0_LI(int n) {
        return n >= 0 && n < LI;
    }

    public boolean in0_CO(int n) {
        return n >= 0 && n < CO;
    }

    public Point getPoint(Node node) {
        for (int i = 0; i < nodes.length; i++) {
            Node[] nodes1 = nodes[i];
            for (int j = 0; j < nodes1.length; j++) {
                Node node1 = nodes1[j];
                if (node1.equals(node)) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    @Override
    public Dimension getDimensionMap() {
        return new Dimension(CO, LI);
    }

    @Override
    public void phishing(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        new DF_Hexa_Phishing(new Phishing(), this).start(nodes);
        // DF_Hexa_Courbe(new Phishing(), this).start(nodes);
    }

    @Override
    public void setNode(int li, int co, Node node) {
        // verifier li et co ... 
        if (li < LI && co < CO) {
            this.nodes[li][co] = node;
        }
    }

    @Override
    public String toString() {
        String str = "   ";

        for (int co = 0; co < CO; co++) {
            str += co;
            //if (co < 10)
            str += "  ";
        }

        str += "\n";

        for (int li = 0; li < LI; li++) {
            if (li >= 10) {
                str += li + " ";
            } else {
                str += li + "  ";
            }
            if (li % 2 == 1) {
                str += " ";
            }
            for (int co = 0; co < CO; co++) {

                if (this.nodes[li][co] != null) {
                    str += this.nodes[li][co].toString() + "  ";
                } else {
                    str += "   ";
                }

                if (co >= 10) {
                    //str += " ";
                }

            }
            str += "\n";
        }

        return str;
    }

    // **************************************************** AIDE ****************************************************
    public Node getNordOuest(Node node) {
        Point p = getPoint(node);
        int li = p.x;
        int co = p.y;

        if (li % 2 == 0) {
            return this.getNode(li - 1, co - 1);
        } else {
            return this.getNode(li - 1, co);
        }
    }

    public Node getOuest(Node node) {
        Point p = getPoint(node);
        int li = p.x;
        int co = p.y;
        if (li % 2 == 0) {
            return this.getNode(li, co - 1);
        } else {
            return this.getNode(li, co - 1);
        }
    }

    public Node getSudOuest(Node node) {
        Point p = getPoint(node);
        int li = p.x;
        int co = p.y;
        if (li % 2 == 0) {
            return this.getNode(li + 1, co - 1);
        } else {
            return this.getNode(li + 1, co);
        }
    }

    public Node getSudEst(Node node) {
        Point p = getPoint(node);
        int li = p.x;
        int co = p.y;
        if (li % 2 == 0) {
            return this.getNode(li + 1, co);
        } else {
            return this.getNode(li + 1, co + 1);
        }
    }

    public Node getEst(Node node) {
        Point p = getPoint(node);
        int li = p.x;
        int co = p.y;
        if (li % 2 == 0) {
            return this.getNode(li, co + 1);
        } else {
            return this.getNode(li, co + 1);
        }
    }

    public Node getNordEst(Node node) {
        Point p = getPoint(node);
        int li = p.x;
        int co = p.y;
        if (li % 2 == 0) {
            return this.getNode(li - 1, co);
        } else {
            return this.getNode(li - 1, co + 1);
        }
    }

    @Override
    public void virus(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        new DF_Hexa_Virus(new Virus(), this).start(nodes);
    }
    
    @Override
    public void trojan(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        new DF_Hexa_Trojan(new Trojan(), this).start(nodes);
    }
}
