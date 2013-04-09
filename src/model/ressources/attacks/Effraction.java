/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ressources.attacks;

import model.Game;
import model.maps.Defence;
import model.maps.Node;

/**
 *
 * @author Quentin
 */
public class Effraction extends Attack {
    public Effraction() {
        super("Effraction", "...", Defence.Effraction, 15);
    }

    public void execute(Node node) {
        this.nodesHack = Game.getInstance().getLevel().getMap().effraction(node);
    }
}
