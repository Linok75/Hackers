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
public class Trojan extends Attack {
    public Trojan() {
        super("Trojan", "...", Defence.Trojan, 15);
    }

    public void execute(Node node) {
        Game.getInstance().getLevel().getMap().trojan(node);
    }
}
