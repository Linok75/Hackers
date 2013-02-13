/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ressources.attacks;

import model.maps.Defence;
import model.maps.Node;
import model.maps.Target;

/**
 *
 * @author ldavid
 */
public class DDoS extends Attack {

    private int power;

    public DDoS() {
        super("DDoS", "", Defence.DDoS, 10);
        this.power = 0;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }

    @Override
    public void execute(Node node) {
        if (!(node instanceof Target)) {
            throw new IllegalArgumentException("Vous ne pouvez faire du DDoS que sur une cible");
        }
        node.hack(this);
    }
}
