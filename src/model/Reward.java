/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import model.ressources.Hardware;
import model.ressources.attacks.Attack;

/**
 *
 * @author bpetit
 */
public class Reward {

    private int money;
    private ArrayList<Hardware> hardware;
    private ArrayList<Attack> newAttacks;

    public Reward(int money, ArrayList<Hardware> hardware, ArrayList<Attack> newAttacks) {
        this.money = money;
        this.hardware = hardware;
        this.newAttacks = newAttacks;
    }

    public Reward(int money) {
        this.money = money;
        this.hardware = new ArrayList<Hardware>();
        this.newAttacks = new ArrayList<Attack>();
    }

    public ArrayList<Hardware> getHardware() {
        return hardware;
    }

    public int getMoney() {
        return money;
    }

    public ArrayList<Attack> getNewAttacks() {
        return newAttacks;
    }

    public void addAttack(Attack attack) {
        if (attack != null && this.newAttacks.isEmpty()) {
            this.newAttacks.add(attack);
        }
    }

    public void addHardware(Hardware hardware) {
        if (hardware != null && this.newAttacks.isEmpty()) {
            this.hardware.add(hardware);
        }
    }

    public void reward(Player player) {
        if (money > 0) {
            player.receiveMoney(money);
        }
        if (this.hardware != null && !this.hardware.isEmpty()) {
            for (Hardware hardware1 : hardware) {
                player.addHardware(hardware1);
            }
        }
        if (this.newAttacks != null && !this.newAttacks.isEmpty()) {
            for (int i = 0; i < this.newAttacks.size(); i++) {
                player.addAttack(this.newAttacks.get(i));
            }
        }
    }
}