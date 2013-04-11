/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ressources.attacks.DF_Hexa;

import java.util.ArrayList;
import model.maps.IMap;
import model.maps.MapHexa;
import model.maps.Node;
import model.ressources.attacks.Attack;
import model.ressources.attacks.DiffusionMethod;

/**
 *
 * @author MyMac
 */
public class DF_Hexa_Phishing extends DiffusionMethod {

    public DF_Hexa_Phishing(Attack attack, MapHexa map) {
        super(attack, map);
    }

    //Methode de diffusion en étoile
    public ArrayList<Node> getAllNodesAround(Node node) {
        MapHexa map = (MapHexa) this.map;
        ArrayList<Node> nodes = new ArrayList<Node>();
        addNodeIfNotNull(nodes, map.getNordOuest(node));
        addNodeIfNotNull(nodes, map.getOuest(node));
        addNodeIfNotNull(nodes, map.getSudOuest(node));
        addNodeIfNotNull(nodes, map.getNordEst(node));
        addNodeIfNotNull(nodes, map.getEst(node));
        addNodeIfNotNull(nodes, map.getSudEst(node));
        return nodes;

    }
}
