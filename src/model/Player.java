/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.NoSuffisantMoney;
import exceptions.NoSuffisantPA;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
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
    private static final int DEFAULT_POWER = 100;

    private int advanced; //(numero du niveau max que le joueur a atteint)
    //private Date dateOfLastGame; //(date de la dernière partie jouée (aide pour se retrouver lors des chargements))

    public Player() {
        this.money = 0;
        this.attacks = new EnumSet<Attack>();
        this.hardwares = new ArrayList<Hardware>();
        this.power = DEFAULT_POWER;
        this.name = "Unnamed";
        this.advanced = 0;
    }

    public Player(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAdvanced() {
        return advanced;
    }

    public void setAdvanced(int advanced) {
        if (advanced <= this.advanced) throw new IllegalArgumentException("Impossible de 'set' l'avancé d'un joueur sur un niveaux antérieur");
        this.advanced = advanced;
    }

    public void addAttack(Attack attack) {
        //Vérifier qu'il n'y a pas une attaque du même type (classe) ?

        //if (!(class instanceof Attack)) ...
        //if (attacks.contains(class)) ...

        this.attacks.add(attack);
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public ArrayList<Attack> getAttackList(){
        return new ArrayList<Attack>(this.attacks);
    }

    public void attack(String nameOfAttack, Node node) throws NoSuffisantPA {

        for (Attack attack : attacks) {
            if (attack.getTitle().equalsIgnoreCase(nameOfAttack)) {
                if (attack.getCost() > power) {
                    throw new NoSuffisantPA();
                } else {
                    power -= attack.getCost();
                }
                attack.execute(node);
            }
        }

    }

    public Attack getAttack(String nameOfAttack) {
        for (Attack attack : attacks) {
            if (attack.getTitle().equalsIgnoreCase(nameOfAttack)) {
                return attack;
            }
        }
        return null;
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

    public void reset() {
        this.power = DEFAULT_POWER;
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
