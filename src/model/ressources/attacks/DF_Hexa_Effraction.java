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
public class DF_Hexa_Effraction extends DiffusionMethod{
    private int radius;
    
    public DF_Hexa_Effraction(Attack attack, MapHexa map){
        super(attack, map);
        this.radius = 1;
    }

    @Override
    protected ArrayList<Node> getAllNodesAround(Node node) {
        MapHexa map = (MapHexa) this.map;
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node tmpEst=node, tmpOuest=node, tmpSudEst=node, tmpSudOuest=node, tmpNordEst=node, tmpNordOuest=node;
        
        for(int i=0;i<this.radius;i++){
            tmpEst=map.getEst(tmpEst);
            tmpOuest=map.getEst(tmpOuest);
            tmpSudEst=map.getEst(tmpSudEst);
            tmpSudOuest=map.getEst(tmpSudOuest);
            tmpNordEst=map.getEst(tmpNordEst);
            tmpNordOuest=map.getEst(tmpNordOuest);
        }
        addNodeIfNotNull(nodes, tmpEst);
        addNodeIfNotNull(nodes, tmpOuest);
        addNodeIfNotNull(nodes, tmpSudEst);
        addNodeIfNotNull(nodes, tmpSudOuest);
        addNodeIfNotNull(nodes, tmpNordEst);
        addNodeIfNotNull(nodes, tmpNordOuest);
        
        this.radius++;
        
        return nodes;
    }
}
