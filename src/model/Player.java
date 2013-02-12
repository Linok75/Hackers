/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.NoSuffisantMoney;
import java.util.ArrayList;
import model.maps.Node;
import model.ressources.Hardware;
import model.ressources.attacks.Attack;

/**
 *
 * @author ldavid
 */
public final class Player {

    private int money;
    private ArrayList<Attack> attacks;
    private ArrayList<Hardware> hardwares;
    private int power;
    //private Attack currentAttack;

    public Player() {
        this.money = 0;
        this.attacks = new ArrayList<Attack>();
        this.hardwares = new ArrayList<Hardware>();
        this.power = 35;
        //this.currentAttack = null;
    }

    public void addAttack(Attack attack) {
        //Vérifier qu'il n'y a pas une attaque du même type (classe) ?

        //if (!(class instanceof Attack)) ...
        //if (attacks.contains(class)) ...


        this.attacks.add(attack);
    }

//    public void attack(Attack attack, Target target) throws CurrentAttackIsNotNull {
//        if (this.currentAttack != null) {
//            throw new CurrentAttackIsNotNull();
//        }
//        if (!this.attacks.contains(attack)) {
//            //throw new ...
//        }
//        this.currentAttack = attack;
//        this.currentAttack.active(target);
//
//    }
    /*
     * public void cancel() { this.currentAttack.cancel(); this.currentAttack =
     * null; }
     */

    public void attack(String nameOfAttack, Node node) {

        for (Attack attack : attacks) {
            if (attack.getTitle().equals(nameOfAttack)) {
                //if (attack.getRessource() > power) exception ...
                //else power -= attack.getRessource()
                //attack.execute();
            }
        }

    }

    public void buy(Hardware hardware) throws NoSuffisantMoney {

        if (this.money - hardware.getPrice() < 0) {
            throw new NoSuffisantMoney();
        }
        //Vérifier qu'il ne peut pas acheter deux hardware de même type (classe) ...

        this.money -= hardware.getPrice();
        this.hardwares.add(hardware);
    }

    public void sell(Hardware hardware) {
        boolean harwareExist = this.hardwares.remove(hardware);
        if (harwareExist) {
            this.money += hardware.getPrice();
        }
    }

    public void addHardware(Hardware hardware) {
        this.hardwares.add(hardware);
    }

    public void receiveMoney(int money) {
        // money > 0
        this.money += money;
    }

    public void addPower() {
        this.power++;
    }

    public String toString() {
        String str = "";
        str += "Money : " + this.money + "\n";
        str += "Attacks :\n";
        for (Attack attack : attacks) {
            str += "\t- " + attack.getTitle() + "\n";
        }
        str += "Hardwares :\n";
        for (Hardware hardware : hardwares) {
            str += "\t- " + hardware.getTitle() + "\n";
        }
        str += "Power : " + this.power + "\n";
        //if (this.currentAttack != null)
        //str += "CurrentAttack : "+this.currentAttack.getTitle()+"\n";
        //else
        //str += "CurrentAttack : -\n";
        return str;
    }
}
