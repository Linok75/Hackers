/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ressources.attacks;

import java.util.ArrayList;
import model.maps.MapHexa;
import model.maps.Node;

/**
 *
 * @author Benjamin
 */
public class DF_Hexa_Courbe extends DiffusionMethod {

    private int diffusion;

    public DF_Hexa_Courbe(Attack attack, MapHexa map) {
        super(attack, map);
        this.diffusion = 0;
    }

    @Override
    protected ArrayList<Node> getAllNodesAround(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        MapHexa map = (MapHexa) this.map;
        if (diffusion < 3) {
            addNodeIfNotNull(nodes, map.getEst(node));
        } else if (diffusion == 3) {
            addNodeIfNotNull(nodes, map.getNordEst(node));
        } else if (diffusion == 4) {
            addNodeIfNotNull(nodes, map.getNordOuest(node));
        } else {
            addNodeIfNotNull(nodes, map.getOuest(node));
        }
        //addNodeIfNotNull(nodes, map.getNordOuest(node));
        //addNodeIfNotNull(nodes, map.getSudOuest(node));
        //addNodeIfNotNull(nodes, map.getNordEst(node));
        //addNodeIfNotNull(nodes, map.getSudEst(node));
        return nodes;
    }

    @Override
    public boolean start(ArrayList<Node> nodes) {
        //System.out.println("DiffusionMethod : start !");

        if (!nodes.isEmpty()) {
            for (Node node : nodes) {
                if (!node.isHackable(attack)) {
                    return false;
                }
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
            this.diffusion++;
            return start(nextNodes);
        } else {
            return false;
        }
    }
}
