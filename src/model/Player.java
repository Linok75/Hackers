/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.NoSuffisantMoney;
import exceptions.NoSuffisantPA;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import model.maps.Node;
import model.maps.Target;
import model.ressources.Hardware;
import model.ressources.attacks.Attack;
import model.ressources.attacks.DDoS;

/**
 *
 * @author ldavid
 */
public final class Player {

    private String name;

    private int money;
    private Set<Attack> attacks;
    private ArrayList<Hardware> hardwares;
    private int power;

    //private int advanced (numero du niveau max que le joueur a atteint)

    public Player() {
        this.money = 0;
        this.attacks = new HashSet<Attack>();
        this.hardwares = new ArrayList<Hardware>();
        this.power = 100;
        this.name = "Unnamed";
    }

    public Player(String name) {
        this();
        this.name = name;
    }

    public void addAttack(Attack attack) {
        //Vérifier qu'il n'y a pas une attaque du même type (classe) ?

        //if (!(class instanceof Attack)) ...
        //if (attacks.contains(class)) ...

        this.attacks.add(attack);
    }

    public int getPower() {
        return power;
    }

    public ArrayList<Attack> getAttackList(){
        return new ArrayList<Attack>(this.attacks);
    }

    public void attack(String nameOfAttack, Node node) throws NoSuffisantPA {

        for (Attack attack : attacks) {
            System.out.println("test1");
            if (attack.getTitle().equals(nameOfAttack)) {
                System.out.println("test2");
                if (attack.getCost() > power) {
                    System.out.println("test3");
                    throw new NoSuffisantPA();
                } else {
                    System.out.println("test4");
                    power -= attack.getCost();
                }
                attack.execute(node);
            }
        }
        System.out.println("test5");

    }

    public void ddos(int power, Target target) throws NoSuffisantPA {
        for (Attack attack : attacks) {
            if (attack instanceof DDoS) {
                if (attack.getCost() > this.power) {
                    throw new NoSuffisantPA();
                } else {
                    this.power -= attack.getCost();
                }
                ((DDoS)attack).setPower(power);
                attack.execute(target);
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
        if (money < 0) {
            throw new IllegalArgumentException("Impossible d'ajouter de la 'money' négatif !");
        }
        this.money += money;
    }

    public void addPower(int nb) {
        if (nb < 0) {
            throw new IllegalArgumentException("Impossible d'ajouter du 'power' négatif !");
        }
        this.power += nb;
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
        return str;
    }
}
