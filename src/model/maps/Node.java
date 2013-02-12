/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.maps;

import java.util.ArrayList;
import model.ressources.attacks.Attack;

/**
 *
 * @author ldavid
 */
public class Node implements Hackable {

    private ArrayList<Defence> behaviors;
    protected boolean isHack;

    public Node(ArrayList<Defence> behavior) {
        this.behaviors = behavior;
        this.isHack = false;
    }

    public boolean isHack() {
        return this.isHack;
    }

    public boolean isHackable(Attack attack) {

        if (!isHack && this.behaviors != null && this.behaviors.contains(attack.getDefence())) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hack(Attack attack) {
        if (isHack) { // pour la propagation ...
            return true;
        }

        if (this.behaviors != null && this.behaviors.contains(attack.getDefence())) {
            return false;
        } else {
            this.isHack = true;
            return true;
        }
    }

    @Override
    public String toString() {
        String str = "";
        /*
         * str += "behaviors :"; for (Defence defence : behaviors) { str +=
         * defence+"\n"; } str += "\n"; str += "isHack : "+this.isHack+"\n";
         *
         */

        if (isHack) {
            str = "X";
        } else if (this.behaviors != null && this.behaviors.contains(Defence.Phishing)) {
            str = "P";
        } else {
            str = "O";
            //str = " ";
        }



        return str;
    }
}
