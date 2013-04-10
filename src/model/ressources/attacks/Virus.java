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
 * @author ldavid
 */
public class Virus extends Attack {

    public Virus() {
        super("Virus", "...", Defence.Virus, 20);
    }

    public void execute(Node node) {
        this.nodesHack = Game.getInstance().getLevel().getMap().virus(node);
    }

}
