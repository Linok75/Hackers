/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import model.*;
import model.maps.*;
import model.ressources.attacks.*;
import tools.XStreamer;

/**
 *
 * @author MyMac
 */
public class Main {

    public static void main(String args[]) {
        //IMap m = new MapHexa();
        //Menu.run();
        makeLevels();
    }
    public static void makeLevels() {
        makeLevel1(); //Phishing
        makeLevel2(); //BruteForcing
        makeLevel3(); //Effraction
        makeLevel4(); //Virus
        makeLevel5(); //Trojan
        makeLevel6(); //Exploitation
    }
    public static void makeLevel1() {

        IMap map = new MapHexa(8,9);

        mapAddDefaultNode(map, 5, 4, getPhishing());
        mapAddDefaultNode(map, 1, 5, getPhishing());
        mapAddDefaultNode(map, 2, 5, getPhishing());
        mapAddDefaultNode(map, 4, 6, getPhishing());
        mapAddDefaultNode(map, 3, 2, getPhishing());
        mapAddDefaultNode(map, 5, 2, getPhishing());
        mapAddDefaultNode(map, 1, 7, getPhishing());
        mapAddDefaultNode(map, 6, 2, getPhishing());

        String descriptionMission = "Bonjour,\n"
                + "Nous sommes une société spécialiste de la distribution dans le domaine agroalimentaire.\n"
                + "Nous faisons appel à vos services dans le but de faire tomber le site web d'un de "
                + "nos concurrents qui commence à prendre beaucoup trop d'ampleur sur le marché...\n"
                + "Vous avez jusqu'à demain pour tomber leur site par tous les moyens possibles, un retard sera synonyme d'échec...";
        String levelName = "MISSION 1 : On est tous passés par là... quoi que...";

        Mission mission = new Mission(levelName, descriptionMission, makeRewardNewAttack("BruteForcing"));

        Level level = new Level(map, 30, makeDefaultTarget(30), mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level1.xml").getPath());

    }
    public static void makeLevel2() {

        IMap map = new MapHexa(8,10);

        // placement des cibles anti phishing sur la map
        mapAddDefaultNode(map, 0, 3, getPhishing());
        mapAddDefaultNode(map, 1, 1, getPhishing());
        mapAddDefaultNode(map, 1, 7, getPhishing());
        mapAddDefaultNode(map, 1, 8, getBruteForcing());
        mapAddDefaultNode(map, 2, 2, getBruteForcing());
        mapAddDefaultNode(map, 2, 4, getPhishing());
        mapAddDefaultNode(map, 2, 6, getPhishing());
        mapAddDefaultNode(map, 2, 8, getPhishing());
        mapAddDefaultNode(map, 3, 2, getPhishing());
        mapAddDefaultNode(map, 3, 4, getPhishing());
        mapAddDefaultNode(map, 3, 5, getBruteForcing());
        mapAddDefaultNode(map, 4, 3, getPhishing());
        mapAddDefaultNode(map, 4, 5, getPhishing());
        mapAddDefaultNode(map, 5, 1, getPhishing());
        mapAddDefaultNode(map, 5, 7, getPhishing());
        mapAddDefaultNode(map, 6, 4, getPhishing());
        mapAddDefaultNode(map, 6, 5, getBruteForcing());
        mapAddDefaultNode(map, 7, 2, getPhishing());
        mapAddDefaultNode(map, 7, 6, getPhishing());

        String descriptionMission = "Créez ton botnet et attaque la cible de la mission !";
        String levelName = "MISSION 2";

        Mission mission = new Mission(levelName, descriptionMission, makeRewardNewAttack("Trojan"));

        Target t = new Target(30, 15, "Test", MapHexa.NODEDEFAULTPATH);

        Level level = new Level(map, 20, makeDefaultTarget(30), mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level2.xml").getPath());
    }
    public static void makeLevel3() {
        // In Progress
        Player player = new Player();
        player.addAttack(new DDoS());
        player.addAttack(new Phishing());
        player.addAttack(new Virus());
        player.addAttack(new Trojan());

        ArrayList<Defence> defPhishing = new ArrayList<Defence>();
        ArrayList<Defence> defVirus = new ArrayList<Defence>();
        ArrayList<Defence> defTrojan = new ArrayList<Defence>();
        String test = MapHexa.NODEDEFAULTPATH;

        Target target = makeDefaultTarget(30);

        IMap map = new MapHexa();

        // defense anti phishing
        defPhishing.add(Defence.Phishing);

        defVirus.add(Defence.Phishing);
        defVirus.add(Defence.Virus);
        defTrojan.add(Defence.Phishing);
        defTrojan.add(Defence.Virus);
        defTrojan.add(Defence.Trojan);

        // placement des cibles anti phishing sur la map
        map.setNode(1, 5, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(2, 5, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(3, 8, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(5, 4, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(6, 6, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));

        map.setNode(0, 1, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(4, 4, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(5, 3, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(6, 1, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(4, 8, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));

        map.setNode(0, 4, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(1, 1, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(3, 2, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(5, 7, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(6, 4, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));

        Mission mission = new Mission("Level_3", "Créez ton botnet et attaque la cible de la mission !", null);

        Level level = new Level(map, 20, target, mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level3.xml").getPath());
        //Game.makeInstance(player, level);
        //Game.getInstance().play();
    }
    public static void makeLevel4() {
        // In Progress
        Player player = new Player();
        player.addAttack(new DDoS());
        player.addAttack(new Phishing());
        player.addAttack(new Virus());
        player.addAttack(new Trojan());

        ArrayList<Defence> defPhishing = new ArrayList<Defence>();
        ArrayList<Defence> defVirus = new ArrayList<Defence>();
        ArrayList<Defence> defTrojan = new ArrayList<Defence>();
        String test = MapHexa.NODEDEFAULTPATH;

        Target target = makeDefaultTarget(30);

        IMap map = new MapHexa();

        // defense anti phishing
        defPhishing.add(Defence.Phishing);
        defVirus.add(Defence.Phishing);
        defVirus.add(Defence.Virus);
        defTrojan.add(Defence.Phishing);
        defTrojan.add(Defence.Virus);
        defTrojan.add(Defence.Trojan);

        // placement des cibles anti phishing sur la map
        map.setNode(5, 4, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(2, 5, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(3, 8, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(1, 7, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));

        map.setNode(4, 4, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(2, 1, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(4, 8, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(1, 5, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(6, 4, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(0, 4, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));

        map.setNode(3, 2, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(5, 7, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(1, 1, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(6, 1, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(5, 3, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));

        Mission mission = new Mission("Level_4", "Créez ton botnet et attaque la cible de la mission !", null);

        Level level = new Level(map, 20, target, mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level4.xml").getPath());
        //Game.makeInstance(player, level);
        //Game.getInstance().play();
    }
    public static void makeLevel5() {

        ArrayList<Defence> defPhishing = new ArrayList<Defence>();
        ArrayList<Defence> defVirus = new ArrayList<Defence>();
        ArrayList<Defence> defTrojan = new ArrayList<Defence>();
        String test = MapHexa.NODEDEFAULTPATH;

        Target target = makeDefaultTarget(25);

        IMap map = new MapHexa();

        // defense anti phishing
        defPhishing.add(Defence.Phishing);

        defVirus.add(Defence.Phishing);
        defVirus.add(Defence.Virus);

        defTrojan.add(Defence.Phishing);
        defTrojan.add(Defence.Virus);
        defTrojan.add(Defence.Trojan);

        // placement des cibles anti phishing sur la map
        map.setNode(5, 4, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(2, 5, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(3, 8, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(1, 7, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));

        map.setNode(4, 4, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(2, 1, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(4, 8, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(1, 5, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));

        map.setNode(6, 4, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(0, 4, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(3, 2, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(5, 7, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(1, 1, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(6, 1, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(5, 3, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));

        Reward r = new Reward(0);

        Mission mission = new Mission("Level_5", "Créez ton botnet et attaque la cible de la mission !", r);

        Level level = new Level(map, 20, target, mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level5.xml").getPath());
        //Game.makeInstance(player, level);
        //Game.getInstance().play();
    }
    public static void makeLevel6() {
    }
    // TOOLS
    public static void mapAddDefaultNode(IMap map, int x, int y, ArrayList<Defence> defs) {
        String description = MapHexa.getNextNameOfNode();
        map.setNode(x, y,new Node(defs, description, MapHexa.NODEDEFAULTPATH));
    }
    public static Target makeDefaultTarget(int life) {
        return new Target(life, "Test", MapHexa.NODEDEFAULTPATH);
    }
    // Ordre Croissant de force
    public static ArrayList<Defence> getPhishing() {
        ArrayList<Defence> defs = new ArrayList<Defence>();
        defs.add(Defence.Phishing);
        return defs;
    }
    public static ArrayList<Defence> getBruteForcing() {
        ArrayList<Defence> defs = getPhishing();
        defs.add(Defence.BruteForcing);
        return defs;
    }
    public static ArrayList<Defence> getEffraction() {
        ArrayList<Defence> defs = getBruteForcing();
        defs.add(Defence.Effraction);
        return defs;
    }
    public static ArrayList<Defence> getVirus() {
        ArrayList<Defence> defs = getEffraction();
        defs.add(Defence.Virus);
        return defs;
    }
    public static ArrayList<Defence> getTrojan() {
        ArrayList<Defence> defs = getVirus();
        defs.add(Defence.Trojan);
        return defs;
    }
    public static ArrayList<Defence> getExploitation() {
        ArrayList<Defence> defs = getTrojan();
        defs.add(Defence.Exploitation);
        return defs;
    }
    //Reward
    public static Reward makeRewardNewAttack(String nameAtk) {
        Reward reward = new Reward();
        reward.addAttack(getAttack(nameAtk));
        return reward;
    }
    // Atk
    public static Attack getAttack(String nameAtk) {
        for (Attack attack : getAllAttacks()) {
            if (attack.getTitle().equalsIgnoreCase(nameAtk)) {
                return attack;
            }
        }
        throw new IllegalArgumentException();
    }
    public static ArrayList<Attack> getAllAttacks() {
        ArrayList<Attack> attacks = new ArrayList<Attack>();
        attacks.add(new Phishing());
        attacks.add(new BruteForcing());
        attacks.add(new Effraction());
        attacks.add(new Virus());
        attacks.add(new Trojan());
        attacks.add(new Exploitation());
        return attacks;
    }
}
