/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ressources.attacks.DF_Hexa;

import java.util.ArrayList;
import model.maps.IMap;
import model.maps.Node;
import model.ressources.attacks.Attack;
import model.ressources.attacks.DiffusionMethod;

/**
 *
 * @author Benjamin
 */
public class DF_Hexa_BruteForcing extends DiffusionMethod {

    public DF_Hexa_BruteForcing(Attack attack, IMap map) {
        super(attack, map);
    }

    @Override
    protected ArrayList<Node> getAllNodesAround(Node node) {
        return null;
    }

}
