/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ressources.attacks.DF_Hexa;

import java.util.ArrayList;
import model.maps.MapHexa;
import model.maps.Node;
import model.ressources.attacks.Attack;
import model.ressources.attacks.DiffusionMethod;

/**
 *
 * @author Benjamin
 */
public class DF_Hexa_Troyen extends DiffusionMethod {

    public DF_Hexa_Troyen(Attack attack, MapHexa map) {
        super(attack, map);
    }

    @Override
    protected ArrayList<Node> getAllNodesAround(Node node) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        MapHexa map = (MapHexa) this.map;
        addNodeIfNotNull(nodes, map.getNordOuest(node));
        //addNodeIfNotNull(nodes, map.getSudOuest(node));
        //addNodeIfNotNull(nodes, map.getNordEst(node));
        addNodeIfNotNull(nodes, map.getSudEst(node));
        return nodes;
    }
}
