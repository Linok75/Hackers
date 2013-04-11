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
 * @author Ara
 */
public class DF_Hexa_Effraction extends DiffusionMethod{

    public DF_Hexa_Effraction(Attack attack, MapHexa map){
        super(attack, map);
    }

    @Override
    protected ArrayList<Node> getAllNodesAround(Node node) {
        MapHexa map = (MapHexa) this.map;
        ArrayList<Node> nodes = new ArrayList<Node>();
        addNodeIfNotNull(nodes, map.getNordEst(map.getEst(node)));
        addNodeIfNotNull(nodes, map.getNordOuest(map.getOuest(node)));

        addNodeIfNotNull(nodes, map.getSudEst(map.getEst(node)));
        addNodeIfNotNull(nodes, map.getSudOuest(map.getOuest(node)));

        return nodes;
    }
}
