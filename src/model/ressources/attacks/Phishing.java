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
public class Phishing extends Attack {

    public Phishing() {
        super("Phishing", "...", Defence.Phishing, 15);
    }

    public void execute(Node node) {
        Game.getInstance().getLevel().getMap().phishing(node);
    }

}
