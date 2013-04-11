/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.maps;

import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import main.Main;
import model.Game;
import model.ressources.attacks.*;


/**
 *
 * @author bpetit
 */
public final class MapHexa implements IMap {

    public final static String NODEDEFAULTPATH = "../ressources/defaultPortrait.png";
    public final static String NODEDEFAULTDESC = "Description non disponible...";
    private int LI;
    private int CO;
    private Node nodes[][]; // [LI][CO]

    private static int idOfFirstNameForNode = 1;
    private static int idOfLastNameForNode = 1;
    private static File firstNames = new File(Main.class.getResource("../ressources/namesSource/namesFirst.txt").getPath());
    private static File lastNames = new File(Main.class.getResource("../ressources/namesSource/namesLast.txt").getPath());

    public MapHexa(int LI, int CO) {
        this.LI = LI;
        this.CO = CO;
        this.nodes = new Node[LI][CO];
        for (int li = 0; li < LI; li++) {
            for (int co = 0; co < CO; co++) {
                String description = getNextNameOfNode();
                this.nodes[li][co] = new Node(null, description, NODEDEFAULTPATH);
            }
        }
    }

    public MapHexa() {
        this(7,9); // default
    }

    public static String getNextNameOfNode() {
        return getNextFirstNameOfNode() + " " + getNextLastNameOfNode() + "\n" + NODEDEFAULTDESC;
    }

    private static String getNextFirstNameOfNode() {
        String s = null;
        try {
            FileReader fr = new FileReader(firstNames);
            BufferedReader br = new BufferedReader(fr);
            int i = 0;
            while (i<=idOfFirstNameForNode && (s = br.readLine()) != null) {
                i++;
                //System.out.println(s.split(" ")[0]);
            }
            fr.close();
            idOfFirstNameForNode++;
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return s.split(" ")[0];
    }

    private static String getNextLastNameOfNode() {
        String s = null;
        try {
            FileReader fr = new FileReader(lastNames);
            BufferedReader br = new BufferedReader(fr);
            int i = 0;
            while (i<=idOfLastNameForNode && (s = br.readLine()) != null) {
                i++;
                //System.out.println(s.split(" ")[0]);
            }
            fr.close();
            idOfLastNameForNode++;
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return s.split(" ")[0];
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
    public ArrayList<ArrayList<Node>> phishing(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        DiffusionMethod dm = new DF_Hexa_Phishing(new Phishing(), this);
        dm.run(nodes);
        return dm.getNodesHack();
    }
    @Override
    public ArrayList<ArrayList<Node>> virus(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        DiffusionMethod dm = new DF_Hexa_Virus(new Virus(), this);
        dm.run(nodes);
        return dm.getNodesHack();
    }

    @Override
    public ArrayList<ArrayList<Node>> trojan(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        DiffusionMethod dm = new DF_Hexa_Trojan(new Trojan(), this);
        dm.run(nodes);
        return dm.getNodesHack();
    }

    @Override
    public ArrayList<ArrayList<Node>> effraction(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        DiffusionMethod dm = new DF_Hexa_Effraction(new Effraction(), this);
        dm.run(nodes);
        return dm.getNodesHack();
    }

    public ArrayList<ArrayList<Node>> exploitation(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(node);
        DiffusionMethod dm = new DF_Hexa_Exploitation(new Exploitation(), this);
        dm.run(nodes);
        return dm.getNodesHack();
    }

    public ArrayList<ArrayList<Node>> bruteForcing(Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public void setNode(int li, int co, Node node) {
        // verifier li et co ...
        if (li >= 0 && li < LI && co >= 0 && co < CO) {
            this.nodes[li][co] = node;
        } else {
            throw new RuntimeException("Impossible d'ajouter des noeuds en dehors de la map ;)");
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
        if (node == null) return null;

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
        if (node == null) return null;

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
        if (node == null) return null;

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
        if (node == null) return null;

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
        if (node == null) return null;

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
        if (node == null) return null;

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
    public boolean isNearTo(Node source, Node target) {

        return this.getEst(source) == target || this.getNordEst(source) == target || this.getNordOuest(source) == target ||
                this.getOuest(source) == target || this.getSudEst(source) == target || this.getSudOuest(source) == target;
    }
}
