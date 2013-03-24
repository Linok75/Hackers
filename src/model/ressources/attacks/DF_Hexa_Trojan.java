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
 * @author Ara
 */
public class DF_Hexa_Trojan extends DiffusionMethod{
    public DF_Hexa_Trojan(Attack attack, MapHexa map){
        super(attack, map);
    }

    @Override
    protected ArrayList<Node> getAllNodesAround(Node node) {
        MapHexa map = (MapHexa) this.map;
        ArrayList<Node> nodes = new ArrayList<Node>();

        Node est = map.getEst(node);
        Node ouest = map.getOuest(node);

        if (est != null) addNodeIfNotNull(nodes, map.getEst(est));
        if (ouest != null) addNodeIfNotNull(nodes, map.getOuest(ouest));
        
        return nodes;
    }

}
