/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.maps;

import java.util.ArrayList;
import model.ressources.attacks.Attack;
import model.ressources.attacks.DDoS;

/**
 *
 * @author ldavid
 */
public final class Target extends Node {

    //private int difficulty;
    private int life;
    private int defence;

    public Target(int life, String description, String path) {
        super(null, description, path);
        this.life = life;
        this.defence = 0;
    }

    public Target(int life, int defence, String description, String path) {
        this(life, description, path);
        this.defence = defence;
    }

    @Override
    public boolean hack(Attack attack) {

        //Vérifier que ce n'est pas déjà hacké

        if (!(attack instanceof DDoS)) {
            throw new IllegalArgumentException("Pour attaquer la cible, il faut obligatoirement une attaque DDoS");
        }
        DDoS ddos = (DDoS) attack;
        int power = ddos.getPower() - this.defence;

        if (power >= this.life) {
            this.isHack = true;
            //...

            System.out.println("Target HACKED !");

            return true;
        } else {
            this.life -= power;
            return false;
        }
    }

    public int getLife() {
        return this.life;
    }
}
