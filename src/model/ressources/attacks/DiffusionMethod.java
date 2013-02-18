/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ressources.attacks;

import java.util.ArrayList;
import model.maps.IMap;
import model.maps.Node;

/**
 *
 * @author MyMac
 */
public abstract class DiffusionMethod {

    protected Attack attack;
    protected IMap map;
    //protected int nbDiffusionMax; //Permet de limiter la propagation d'attaques "puissantes"

    protected ArrayList<Node> old_nodes; // Contient les nodes hackes lors des precedentes execution de la difusionMethod

    public DiffusionMethod(Attack attack, IMap map) {
        this.attack = attack;
        this.map = map;

        this.old_nodes = new ArrayList<Node>();
    }

    public boolean start(ArrayList<Node> nodes) {
        //System.out.println("DiffusionMethod : start !");

        if (!nodes.isEmpty()) {
            for (Node node : nodes) {
                if (!node.isHackable(attack)) return false;
            }

            old_nodes.addAll(nodes);

            ArrayList<Node> nextNodes = new ArrayList<Node>();

            for (Node node : nodes) {
                nextNodes.addAll(getAllNodesAround(node));
                nextNodes.removeAll(old_nodes);
                node.hack(attack);
            }

            for (Node node : nodes) {
                if (!AllNodesAroundAreHackable(node)) {
                    //System.out.println("Au moins une node autour de la cible n'est pas hackable");
                    return false;
                }
            }
            return start(nextNodes);
        } else {
            return false;
        }
    }

    protected boolean AllNodesAroundAreHackable(Node node) {
        ArrayList<Node> nodes = getAllNodesAround(node);
        if (nodes.isEmpty()) return false;
        nodes.removeAll(old_nodes);
        for (Node node1 : nodes) {
            if (!node1.isHackable(attack)) {
                return false;
            }
        }
        return true;
    }

    protected void addNodeIfNotNull(ArrayList<Node> nodes, Node node) {
        if (node != null) {
            nodes.add(node);
        }
    }

    protected abstract ArrayList<Node> getAllNodesAround(Node node);
}
