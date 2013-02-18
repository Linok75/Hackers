/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ressources.attacks;

import model.maps.Defence;
import model.maps.Node;
import model.ressources.Ressource;

/**
 *
 * @author ldavid
 */
public abstract class Attack extends Ressource {

    protected boolean activated;
    //private ... time; //durée total d'execution
    //private ... currentTime; //temps passé pour l'execution
    private Defence defence;

    private int cost;

    //methdode de diffusion
    //private DiffusionMethod diffusion;


    public Attack(String titre, String description, Defence defence, int cost) {
        super(titre, description);
        this.activated = false;
        this.defence = defence;
        this.cost = cost;
    }

    public Defence getDefence() {
        return defence;
    }

    public int getCost() {
        return cost;
    }

    public abstract void execute(Node node);

}
