/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import exceptions.ImpossibleLoadOfXML;
import java.util.ArrayList;
import model.*;
import model.maps.*;
import model.ressources.attacks.DDoS;
import model.ressources.attacks.Phishing;
import model.ressources.attacks.Trojan;
import model.ressources.attacks.Virus;
import tools.XStreamer;

/**
 *
 * @author MyMac
 */
public class Main {

    public static void main(String args[]) {
        IMap m = new MapHexa();
        //Menu.run();
        makeLevel1();
        makeLevel2();
        makeLevel3(); // TODO
        makeLevel4(); // TODO
        //makeLevel5();
    }

    public static void makeLevel1() {
        Player player = new Player();
        player.addAttack(new DDoS());
        player.addAttack(new Phishing());
        //System.out.println("Player :\n"+player);

        ArrayList<Defence> behavior = new ArrayList<>();

        Target target = new Target(30, new ArrayList<Defence>(), "Serveur Cible", MapHexa.NODEDEFAULTPATH);

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

        map.setNode(5, 4, new model.maps.Node(behavior, MapHexa.NODEDEFAULTDESC, MapHexa.NODEDEFAULTPATH));
        map.setNode(1, 5, new model.maps.Node(behavior, MapHexa.NODEDEFAULTDESC, MapHexa.NODEDEFAULTPATH));
        map.setNode(2, 5, new model.maps.Node(behavior, MapHexa.NODEDEFAULTDESC, MapHexa.NODEDEFAULTPATH));
        map.setNode(4, 6, new model.maps.Node(behavior, MapHexa.NODEDEFAULTDESC, MapHexa.NODEDEFAULTPATH));
        map.setNode(3, 8, new model.maps.Node(behavior, MapHexa.NODEDEFAULTDESC, MapHexa.NODEDEFAULTPATH));
        map.setNode(5, 2, new model.maps.Node(behavior, MapHexa.NODEDEFAULTDESC, MapHexa.NODEDEFAULTPATH));
        map.setNode(1, 7, new model.maps.Node(behavior, MapHexa.NODEDEFAULTDESC, MapHexa.NODEDEFAULTPATH));
        map.setNode(6, 2, new model.maps.Node(behavior, MapHexa.NODEDEFAULTDESC, MapHexa.NODEDEFAULTPATH));

        Reward r = new Reward(0);
        r.addAttack(new Virus());

        Mission mission = new Mission("Level_1", "Attack !", r);

        Level level = new Level(map, 30, target, mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level1.xml").getPath());

        //Game.makeInstance(player, level);
        //Game.getInstance().play();
    }

    public static void makeLevel2() {
        Player player = new Player();
        player.addAttack(new DDoS());
        player.addAttack(new Phishing());
        player.addAttack(new Virus());
        player.addAttack(new Trojan());

        ArrayList<Defence> defPhishing = new ArrayList<Defence>();
        ArrayList<Defence> defVirus = new ArrayList<Defence>();
        ArrayList<Defence> defTrojan = new ArrayList<Defence>();
        String test = MapHexa.NODEDEFAULTPATH;

        Target target = new Target(30, new ArrayList<Defence>(), test, MapHexa.NODEDEFAULTPATH);

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
        map.setNode(1, 5, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(2, 5, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(6, 6, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(3, 8, new Node(defPhishing, test, MapHexa.NODEDEFAULTPATH));


        map.setNode(5, 3, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(4, 4, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(6, 1, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(0, 1, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(4, 8, new Node(defVirus, test, MapHexa.NODEDEFAULTPATH));

        map.setNode(6, 4, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(0, 4, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(3, 2, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(5, 7, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(1, 1, new Node(defTrojan, test, MapHexa.NODEDEFAULTPATH));

        Reward r = new Reward(0);
        r.addAttack(new Trojan());

        Mission mission = new Mission("Level_2", "Créez ton botnet et attaque la cible de la mission !", r);

        Level level = new Level(map, 20, target, mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level2.xml").getPath());
        //Game.makeInstance(player, level);
        //Game.getInstance().play();
    }

    public static void makeLevel3() {
        try {
            Level l = new XStreamer<Level>().load(Main.class.getResource("../levels/level3.xml").getPath());
            System.out.println(l.getMap().toString());
        } catch (ImpossibleLoadOfXML i) {
            
        }
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

        Target target = new Target(25, new ArrayList<Defence>(), test, MapHexa.NODEDEFAULTPATH);

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
        System.out.println(level.getMap().toString());
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

        Target target = new Target(25, new ArrayList<Defence>(), test, MapHexa.NODEDEFAULTPATH);

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

        Target target = new Target(25, new ArrayList<Defence>(), test, MapHexa.NODEDEFAULTPATH);

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

        Mission mission = new Mission("Level_5", "Créez ton botnet et attaque la cible de la mission !", null);

        Level level = new Level(map, 20, target, mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level5.xml").getPath());
        //Game.makeInstance(player, level);
        //Game.getInstance().play();
    }
}
