/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ressources.attacks;

import java.awt.Point;
import java.util.ArrayList;
import model.maps.IMap;
import model.maps.MapGrille;
import model.maps.Node;

/**
 *
 * @author MyMac
 */
public class DF_Grille_Phishing extends DiffusionMethod {

    public DF_Grille_Phishing(Attack attack, MapGrille map) {
        super(attack, map);
    }

    //Methode de diffusion en étoile
    public ArrayList<Node> getAllNodesAround(Node node, IMap map) {
        Point p = map.getPoint(node);
        int li = p.x;
        int co = p.y;
        return getNodesAround(li, co);
    }

    private ArrayList<Node> getNodesAround(int li, int co) {

        // Vérifier que li et co sont dedans

        //Point : x = li, y = co
        ArrayList<Node> nodes = new ArrayList<Node>();

        if (map.in0_LI(li - 1) && map.in0_CO(co)) {
            nodes.add(map.getNode(li - 1, co));             // N (Nord)
        }
        if (map.in0_LI(li - 1) && map.in0_CO(co - 1)) {
            nodes.add(map.getNode(li - 1, co - 1));         // NO (Nord-Ouest)
        }
        if (map.in0_LI(li) && map.in0_CO(co - 1)) {
            nodes.add(map.getNode(li, co - 1));             // O
        }
        if (map.in0_LI(li + 1) && map.in0_CO(co - 1)) {
            nodes.add(map.getNode(li + 1, co - 1));         // SO
        }
        if (map.in0_LI(li + 1) && map.in0_CO(co)) {
            nodes.add(map.getNode(li + 1, co));             // S
        }
        if (map.in0_LI(li + 1) && map.in0_CO(co + 1)) {
            nodes.add(map.getNode(li + 1, co + 1));         // SE
        }
        if (map.in0_LI(li) && map.in0_CO(co + 1)) {
            nodes.add(map.getNode(li, co + 1));             // E
        }
        if (map.in0_LI(li - 1) && map.in0_CO(co + 1)) {
            nodes.add(map.getNode(li - 1, co + 1));         // NE
        }
        return nodes;

    }

}
