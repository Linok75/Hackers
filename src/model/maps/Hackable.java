/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.maps;

import model.ressources.attacks.Attack;

/**
 *
 * @author ldavid
 */
public interface Hackable {

    public boolean isHack();
    public boolean isHackable(Attack attack);
    public boolean hack(Attack attack);

}
