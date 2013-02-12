/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.maps;

import java.awt.Point;
import java.util.ArrayList;
import model.ressources.attacks.DF_Grille_Phishing;
import model.ressources.attacks.Phishing;

/**
 *
 * @author MyMac
 */
public class MapGrille implements IMap {

    private static final int LI = 8;
    private static final int CO = 12;
    private Node nodes[][]; // [LI][CO]

    public MapGrille() {
        this.nodes = new Node[LI][CO];
        for (int li = 0; li < LI; li++) {
            for (int co = 0; co < CO; co++) {
                this.nodes[li][co] = new Node(null);
            }
        }
    }

    @Override
    public void setNode(int li, int co, Node node) {
        nodes[li][co] = node;
    }

    @Override
    public Node getNode(int li, int co) {
        return nodes[li][co];
    }

    @Override
    public Point getPoint(Node node) {
        for (int li = 0; li < LI; li++) {
            for (int co = 0; co < CO; co++) {
                if (nodes[li][co] == node) {
                    return new Point(li, co);
                }
            }
        }
        return null;
    }

    @Override
    public int countAllNodesHack() {
        int nbNodesHack = 0;
        for (Node[] nodes1 : nodes) {
            for (Node node : nodes1) {
                if (node.isHack()) {
                    nbNodesHack++;
                }
            }
        }
        return nbNodesHack;
    }

    @Override
    public boolean in0_LI(int n) {
        return n >= 0 && n < LI;
    }

    @Override
    public boolean in0_CO(int n) {
        return n >= 0 && n < CO;
    }

    @Override
    public void phishing(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        new DF_Grille_Phishing(new Phishing(), this).start(nodes);
    }

    @Override
    public String toString() {
        String str = "   ";

        for (int co = 0; co < CO; co++) {
            str += co;
            //if (co < 10)
            str += " ";
        }

        str += "\n";

        for (int li = 0; li < LI; li++) {
            if (li >= 10) {
                str += li + " ";
            } else {
                str += li + "  ";
            }
            for (int co = 0; co < CO; co++) {

                if (this.nodes[li][co] != null) {
                    str += this.nodes[li][co].toString() + " ";
                } else {
                    str += "  ";
                }

                if (co >= 10) {
                    str += " ";
                }

            }
            str += "\n";
        }

        return str;
    }
}
