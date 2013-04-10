/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author ldavid
 */
public final class Mission {

    private String title;
    private String description;

    private Reward rewards;
    //private ... bonus

    public Mission(String title, String description, Reward rewards) {
        this.title = title;
        this.description = description;
        this.rewards = rewards;
    }

    public void reward(Player p) {
        if (this.rewards != null) this.rewards.reward(p);
    }

    @Override
    public String toString() {
        String str = "";
        str += "Title : "+this.title+"\n";
        str += "Description : "+this.description;
        return str;
    }



}
