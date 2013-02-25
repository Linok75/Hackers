/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.maps;

import java.util.ArrayList;
import model.ressources.attacks.Attack;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author ldavid
 */
public class Node implements Hackable {

    private ArrayList<Defence> behaviors;
    protected boolean isHack;
    private String description;
    private String path; //chemin pour accéder au portrait du personnage dans l'interface

    public Node(ArrayList<Defence> behavior, String description, String path) {
        this.behaviors = behavior;
        this.isHack = false;
        this.description = description;
        this.path = path;
    }

    public boolean isHack() {
        return this.isHack;
    }

    public boolean isHackable(Attack attack) {

        if (attack == null || (!isHack && this.behaviors != null && this.behaviors.contains(attack.getDefence()))) {
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

    public String getDescription() {
        return this.description;
    }

    public String getPath() {
        return this.path;
    }

    public Image getImage() {
        try {
            return new Image(this.path);
        } catch (SlickException se) {
            return null;
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
        }  else {
            str = "O";
            //str = " ";
        }



        return str;
    }
}
