/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import model.Game;
import model.Level;
import model.Mission;
import model.Player;
import model.maps.*;
import model.ressources.attacks.DDoS;
import model.ressources.attacks.Phishing;

/**
 *
 * @author MyMac
 */
public class Main {

    public static void main(String args[]) {
        test2();
    }

    public static void test() {
        Player player = new Player();
        player.addAttack(new DDoS());
        player.addAttack(new Phishing());
        //System.out.println("Player :\n"+player);

        ArrayList<Defence> behavior = new ArrayList<Defence>();

        Target target = new Target(30, new ArrayList<Defence>());

        IMap map = new MapHexa();

        behavior.add(Defence.Phishing);

        //map.setNode(5,1, new Node(behavior));
        //map.setNode(1,2, new Node(behavior));
        //map.setNode(7,0, new Node(behavior));

        // Changement des placements en attendant que je face de la génération d'hexagone (la map était trop grande)
//        map.setNode(5,4, new Node(behavior));
//        map.setNode(1,5, new Node(behavior));
//        map.setNode(2,5, new Node(behavior));
//        map.setNode(7,6, new Node(behavior));
//        map.setNode(3,8, new Node(behavior));
//        map.setNode(5,10, new Node(behavior));
//        map.setNode(1,10, new Node(behavior));
//        map.setNode(7,11, new Node(behavior));

        String test = "test";
        map.setNode(5, 4, new model.maps.Node(behavior, test, test));
        map.setNode(1, 5, new model.maps.Node(behavior, test, test));
        map.setNode(2, 5, new model.maps.Node(behavior, test, test));
        map.setNode(4, 6, new model.maps.Node(behavior, test, test));
        map.setNode(3, 8, new model.maps.Node(behavior, test, test));
        map.setNode(5, 2, new model.maps.Node(behavior, test, test));
        map.setNode(1, 7, new model.maps.Node(behavior, test, test));
        map.setNode(6, 2, new model.maps.Node(behavior, test, test));


        Mission mission = new Mission("Level_1", "Attack !");

        Level level = new Level(map, 30, target, mission);

        Game.makeInstance(player, level);
        Game.getInstance().play();
    }

    public static void test2() {
        Player player = new Player();
        player.addAttack(new DDoS());
        player.addAttack(new Phishing());

        ArrayList<Defence> behavior = new ArrayList<Defence>();

        Target target = new Target(30, new ArrayList<Defence>());

        IMap map = new MapHexa();

        // defense anti phishing
        behavior.add(Defence.Phishing);

        String test = "test";
        // placement des cibles anti phishing sur la map
        map.setNode(5, 4, new Node(behavior, test, test));
        map.setNode(1, 5, new Node(behavior, test, test));
        map.setNode(2, 5, new Node(behavior, test, test));
        map.setNode(7, 6, new Node(behavior, test, test));
        map.setNode(3, 8, new Node(behavior, test, test));
        map.setNode(5, 10, new Node(behavior, test, test));
        map.setNode(1, 10, new Node(behavior, test, test));
        map.setNode(7, 11, new Node(behavior, test, test));


        Mission mission = new Mission("Level_1", "Créez ton botnet et attaque la cible de la mission !");

        Level level = new Level(map, 30, target, mission);

        Game.makeInstance(player, level);
        Game.getInstance().play();
    }
}
