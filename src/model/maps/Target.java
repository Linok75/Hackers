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
    //private int defence;

    public Target(int life, String description, String path) {
        super(null, description, path);
        this.life = life;
    }

    @Override
    public boolean hack(Attack attack) {

        //Vérifier que ce n'est pas déjà hacké

        if (!(attack instanceof DDoS)) {
            throw new IllegalArgumentException("Pour attaquer la cible, il faut obligatoirement une attaque DDoS");
        }
        DDoS ddos = (DDoS) attack;
        if (ddos.getPower() >= this.life) {
            this.isHack = true;
            //...

            System.out.println("Target HACKED !");

            return true;
        } else {
            this.life-=ddos.getPower();
            return false;
        }
    }

    public int getLife() {
        return this.life;
    }
}
