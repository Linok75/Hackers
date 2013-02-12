/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ressources.attacks;

import model.maps.Defence;
import model.maps.Node;

/**
 *
 * @author ldavid
 */
public class DDoS extends Attack {

    private int power;

    public DDoS(int power) {
        super("DDoS", "", Defence.DDoS);
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }

    @Override
    public void execute(Node node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
