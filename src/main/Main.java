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

        IMap map = new MapHexa();

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

        Target target = new Target(50, 25, "Serveur Web", MapHexa.NODEDEFAULTPATH);

        Level level = new Level(map, 30, target, mission);
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

        Mission mission = new Mission(levelName, descriptionMission, makeRewardNewAttack("Effraction"));

        Level level = new Level(map, 20, makeDefaultTarget(30), mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level2.xml").getPath());
    }
    public static void makeLevel3() {
        // In Progress
        IMap map = new MapHexa(10,10);

        // placement des cibles anti phishing sur la map
        mapAddDefaultNode(map, 0, 3, getBruteForcing());
        mapAddDefaultNode(map, 1, 1, getPhishing());
        mapAddDefaultNode(map, 1, 7, getPhishing());
        mapAddDefaultNode(map, 1, 8, getBruteForcing());
        mapAddDefaultNode(map, 2, 2, getBruteForcing());
        mapAddDefaultNode(map, 2, 4, getPhishing());
        mapAddDefaultNode(map, 2, 6, getEffraction());
        mapAddDefaultNode(map, 2, 8, getPhishing());
        mapAddDefaultNode(map, 3, 2, getBruteForcing());
        mapAddDefaultNode(map, 3, 4, getPhishing());
        mapAddDefaultNode(map, 3, 5, getBruteForcing());
        mapAddDefaultNode(map, 4, 3, getPhishing());
        mapAddDefaultNode(map, 4, 5, getPhishing());
        mapAddDefaultNode(map, 5, 1, getEffraction());
        mapAddDefaultNode(map, 5, 7, getPhishing());
        mapAddDefaultNode(map, 6, 4, getPhishing());
        mapAddDefaultNode(map, 6, 5, getBruteForcing());
        mapAddDefaultNode(map, 7, 2, getPhishing());
        mapAddDefaultNode(map, 7, 6, getPhishing());
        mapAddDefaultNode(map, 9, 1, getEffraction());
        mapAddDefaultNode(map, 1, 4, getPhishing());

        mapAddDefaultNode(map, 0, 8, getBruteForcing());
        mapAddDefaultNode(map, 4, 7, getPhishing());
        mapAddDefaultNode(map, 4, 9, getEffraction());
        mapAddDefaultNode(map, 5, 3, getBruteForcing());
        mapAddDefaultNode(map, 5, 5, getPhishing());
        mapAddDefaultNode(map, 6, 1, getBruteForcing());
        mapAddDefaultNode(map, 6, 9, getPhishing());
        mapAddDefaultNode(map, 7, 0, getEffraction());

        String descriptionMission = "Créez ton botnet et attaque la cible de la mission !";
        String levelName = "MISSION 3";

        Mission mission = new Mission(levelName, descriptionMission, makeRewardNewAttack("Virus"));

        Level level = new Level(map, 20, makeDefaultTarget(30), mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level3.xml").getPath());
    }
    public static void makeLevel4() {
        // In Progress
        IMap map = new MapHexa(10,13);

        // placement des cibles anti phishing sur la map
        mapAddDefaultNode(map, 0, 3, getBruteForcing());
        mapAddDefaultNode(map, 1, 1, getPhishing());
        mapAddDefaultNode(map, 1, 7, getPhishing());
        mapAddDefaultNode(map, 1, 8, getVirus());
        mapAddDefaultNode(map, 2, 2, getBruteForcing());
        mapAddDefaultNode(map, 2, 4, getVirus());
        mapAddDefaultNode(map, 2, 6, getVirus());
        mapAddDefaultNode(map, 2, 8, getVirus());
        mapAddDefaultNode(map, 3, 2, getBruteForcing());
        mapAddDefaultNode(map, 3, 4, getPhishing());
        mapAddDefaultNode(map, 3, 5, getVirus());
        mapAddDefaultNode(map, 4, 3, getPhishing());
        mapAddDefaultNode(map, 4, 5, getVirus());
        mapAddDefaultNode(map, 5, 1, getEffraction());
        mapAddDefaultNode(map, 5, 7, getVirus());
        mapAddDefaultNode(map, 6, 4, getVirus());
        mapAddDefaultNode(map, 6, 5, getVirus());
        mapAddDefaultNode(map, 7, 2, getPhishing());
        mapAddDefaultNode(map, 7, 6, getPhishing());
        mapAddDefaultNode(map, 9, 1, getEffraction());
        mapAddDefaultNode(map, 1, 4, getPhishing());

        mapAddDefaultNode(map, 0, 8, getBruteForcing());
        mapAddDefaultNode(map, 4, 7, getPhishing());
        mapAddDefaultNode(map, 4, 9, getEffraction());
        mapAddDefaultNode(map, 5, 3, getVirus());
        mapAddDefaultNode(map, 5, 5, getVirus());
        mapAddDefaultNode(map, 6, 1, getBruteForcing());
        mapAddDefaultNode(map, 6, 9, getVirus());
        mapAddDefaultNode(map, 7, 0, getEffraction());

        mapAddDefaultNode(map, 0, 10, getBruteForcing());
        mapAddDefaultNode(map, 4, 11, getVirus());
        mapAddDefaultNode(map, 4, 12, getEffraction());
        mapAddDefaultNode(map, 5, 10, getVirus());
        mapAddDefaultNode(map, 5, 11, getPhishing());
        mapAddDefaultNode(map, 6, 12, getBruteForcing());
        mapAddDefaultNode(map, 6, 10, getPhishing());
        mapAddDefaultNode(map, 7, 12, getVirus());

        String descriptionMission = "Créez ton botnet et attaque la cible de la mission !";
        String levelName = "MISSION 4";

        Mission mission = new Mission(levelName, descriptionMission, makeRewardNewAttack("Trojan"));

        Level level = new Level(map, 20, makeDefaultTarget(30), mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level4.xml").getPath());
    }
    public static void makeLevel5() {

        // In Progress
        IMap map = new MapHexa(13,13);

        // placement des cibles anti phishing sur la map
        mapAddDefaultNode(map, 0, 3, getTrojan());
        mapAddDefaultNode(map, 1, 1, getPhishing());
        mapAddDefaultNode(map, 1, 7, getTrojan());
        mapAddDefaultNode(map, 1, 8, getVirus());
        mapAddDefaultNode(map, 2, 2, getTrojan());
        mapAddDefaultNode(map, 2, 4, getVirus());
        mapAddDefaultNode(map, 2, 6, getVirus());
        mapAddDefaultNode(map, 2, 8, getTrojan());
        mapAddDefaultNode(map, 3, 2, getBruteForcing());
        mapAddDefaultNode(map, 3, 4, getPhishing());
        mapAddDefaultNode(map, 3, 5, getVirus());
        mapAddDefaultNode(map, 4, 3, getTrojan());
        mapAddDefaultNode(map, 4, 5, getVirus());
        mapAddDefaultNode(map, 5, 1, getEffraction());
        mapAddDefaultNode(map, 5, 7, getVirus());
        mapAddDefaultNode(map, 6, 4, getVirus());
        mapAddDefaultNode(map, 6, 5, getTrojan());
        mapAddDefaultNode(map, 7, 2, getTrojan());
        mapAddDefaultNode(map, 7, 6, getPhishing());
        mapAddDefaultNode(map, 9, 1, getEffraction());
        mapAddDefaultNode(map, 1, 4, getPhishing());

        mapAddDefaultNode(map, 0, 8, getBruteForcing());
        mapAddDefaultNode(map, 4, 7, getTrojan());
        mapAddDefaultNode(map, 4, 9, getEffraction());
        mapAddDefaultNode(map, 5, 3, getVirus());
        mapAddDefaultNode(map, 5, 5, getTrojan());
        mapAddDefaultNode(map, 6, 1, getBruteForcing());
        mapAddDefaultNode(map, 6, 9, getTrojan());
        mapAddDefaultNode(map, 7, 0, getTrojan());

        mapAddDefaultNode(map, 0, 10, getTrojan());
        mapAddDefaultNode(map, 4, 11, getTrojan());
        mapAddDefaultNode(map, 4, 12, getTrojan());
        mapAddDefaultNode(map, 5, 10, getVirus());
        mapAddDefaultNode(map, 5, 11, getTrojan());
        mapAddDefaultNode(map, 6, 12, getBruteForcing());
        mapAddDefaultNode(map, 6, 10, getTrojan());
        mapAddDefaultNode(map, 7, 12, getVirus());

        mapAddDefaultNode(map, 10, 10, getBruteForcing());
        mapAddDefaultNode(map, 11, 1, getVirus());
        mapAddDefaultNode(map, 12, 6, getEffraction());
        mapAddDefaultNode(map, 10, 2, getVirus());
        mapAddDefaultNode(map, 11, 8, getTrojan());
        mapAddDefaultNode(map, 12, 12, getTrojan());
        mapAddDefaultNode(map, 10, 0, getTrojan());
        mapAddDefaultNode(map, 12, 4, getTrojan());

        String descriptionMission = "Créez ton botnet et attaque la cible de la mission !";
        String levelName = "MISSION 5";

        Mission mission = new Mission(levelName, descriptionMission, makeRewardNewAttack("Exploitation"));

        Level level = new Level(map, 20, makeDefaultTarget(30), mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level5.xml").getPath());
    }
    public static void makeLevel6() {
        // In Progress
        IMap map = new MapHexa(15,15);

        // placement des cibles anti phishing sur la map
        mapAddDefaultNode(map, 0, 0, getVirus());
        mapAddDefaultNode(map, 0, 1, getExploitation());
        mapAddDefaultNode(map, 0, 2, getExploitation());
        mapAddDefaultNode(map, 0, 3, getVirus());
        mapAddDefaultNode(map, 0, 4, getExploitation());
        mapAddDefaultNode(map, 0, 5, getExploitation());
        mapAddDefaultNode(map, 0, 6, getExploitation());
        mapAddDefaultNode(map, 0, 7, getExploitation());
        mapAddDefaultNode(map, 0, 8, getTrojan());
        mapAddDefaultNode(map, 0, 9, getExploitation());
        mapAddDefaultNode(map, 0, 10, getTrojan());
        mapAddDefaultNode(map, 0, 11, getTrojan());
        mapAddDefaultNode(map, 0, 12, getExploitation());
        mapAddDefaultNode(map, 0, 13, getTrojan());
        mapAddDefaultNode(map, 0, 14, getEffraction());

        mapAddDefaultNode(map, 1, 0, getExploitation());
        mapAddDefaultNode(map, 1, 1, getExploitation());
        mapAddDefaultNode(map, 1, 2, getExploitation());
        mapAddDefaultNode(map, 1, 3, getExploitation());
        mapAddDefaultNode(map, 1, 4, getExploitation());
        mapAddDefaultNode(map, 1, 5, getExploitation());
        mapAddDefaultNode(map, 1, 6, getTrojan());
        mapAddDefaultNode(map, 1, 7, getExploitation());
        mapAddDefaultNode(map, 1, 8, getTrojan());
        mapAddDefaultNode(map, 1, 9, getExploitation());
        mapAddDefaultNode(map, 1, 10, getTrojan());
        mapAddDefaultNode(map, 1, 11, getExploitation());
        mapAddDefaultNode(map, 1, 12, getTrojan());
        mapAddDefaultNode(map, 1, 13, getTrojan());
        mapAddDefaultNode(map, 1, 14, getTrojan());

        mapAddDefaultNode(map, 2, 0, getExploitation());
        mapAddDefaultNode(map, 2, 1, getTrojan());
        mapAddDefaultNode(map, 2, 2, getTrojan());
        mapAddDefaultNode(map, 2, 3, getExploitation());
        mapAddDefaultNode(map, 2, 4, getTrojan());
        mapAddDefaultNode(map, 2, 5, getTrojan());
        mapAddDefaultNode(map, 2, 6, getTrojan());
        mapAddDefaultNode(map, 2, 7, getExploitation());
        mapAddDefaultNode(map, 2, 8, getTrojan());
        mapAddDefaultNode(map, 2, 9, getVirus());
        mapAddDefaultNode(map, 2, 10, getExploitation());
        mapAddDefaultNode(map, 2, 11, getExploitation());
        mapAddDefaultNode(map, 2, 12, getExploitation());
        mapAddDefaultNode(map, 2, 13, getExploitation());
        mapAddDefaultNode(map, 2, 14, getExploitation());

        mapAddDefaultNode(map, 3, 0, getExploitation());
        mapAddDefaultNode(map, 3, 1, getTrojan());
        mapAddDefaultNode(map, 3, 2, getTrojan());
        mapAddDefaultNode(map, 3, 3, getExploitation());
        mapAddDefaultNode(map, 3, 4, getTrojan());
        mapAddDefaultNode(map, 3, 5, getVirus());
        mapAddDefaultNode(map, 3, 6, getExploitation());
        mapAddDefaultNode(map, 3, 7, getTrojan());
        mapAddDefaultNode(map, 3, 8, getTrojan());
        mapAddDefaultNode(map, 3, 9, getExploitation());
        mapAddDefaultNode(map, 3, 10, getExploitation());
        mapAddDefaultNode(map, 3, 11, getExploitation());
        mapAddDefaultNode(map, 3, 12, getExploitation());
        mapAddDefaultNode(map, 3, 13, getExploitation());
        mapAddDefaultNode(map, 3, 14, getExploitation());

        mapAddDefaultNode(map, 4, 0, getEffraction());
        mapAddDefaultNode(map, 4, 1, getTrojan());
        mapAddDefaultNode(map, 4, 2, getExploitation());
        mapAddDefaultNode(map, 4, 3, getTrojan());
        mapAddDefaultNode(map, 4, 4, getTrojan());
        mapAddDefaultNode(map, 4, 5, getExploitation());
        mapAddDefaultNode(map, 4, 6, getTrojan());
        mapAddDefaultNode(map, 4, 7, getVirus());
        mapAddDefaultNode(map, 4, 8, getExploitation());
        mapAddDefaultNode(map, 4, 9, getExploitation());
        mapAddDefaultNode(map, 4, 10, getExploitation());
        mapAddDefaultNode(map, 4, 11, getExploitation());
        mapAddDefaultNode(map, 4, 12, getExploitation());
        mapAddDefaultNode(map, 4, 13, getTrojan());
        mapAddDefaultNode(map, 4, 14, getTrojan());

        mapAddDefaultNode(map, 5, 0, getExploitation());
        mapAddDefaultNode(map, 5, 1, getTrojan());
        mapAddDefaultNode(map, 5, 2, getTrojan());
        mapAddDefaultNode(map, 5, 3, getTrojan());
        mapAddDefaultNode(map, 5, 4, getExploitation());
        mapAddDefaultNode(map, 5, 5, getExploitation());
        mapAddDefaultNode(map, 5, 6, getExploitation());
        mapAddDefaultNode(map, 5, 7, getExploitation());
        mapAddDefaultNode(map, 5, 8, getExploitation());
        mapAddDefaultNode(map, 5, 9, getTrojan());
        mapAddDefaultNode(map, 5, 10, getVirus());
        mapAddDefaultNode(map, 5, 11, getExploitation());
        mapAddDefaultNode(map, 5, 12, getTrojan());
        mapAddDefaultNode(map, 5, 13, getTrojan());
        mapAddDefaultNode(map, 5, 14, getTrojan());

        mapAddDefaultNode(map, 6, 0, getExploitation());
        mapAddDefaultNode(map, 6, 1, getExploitation());
        mapAddDefaultNode(map, 6, 2, getExploitation());
        mapAddDefaultNode(map, 6, 3, getExploitation());
        mapAddDefaultNode(map, 6, 4, getExploitation());
        mapAddDefaultNode(map, 6, 5, getTrojan());
        mapAddDefaultNode(map, 6, 6, getTrojan());
        mapAddDefaultNode(map, 6, 7, getExploitation());
        mapAddDefaultNode(map, 6, 8, getTrojan());
        mapAddDefaultNode(map, 6, 9, getTrojan());
        mapAddDefaultNode(map, 6, 10, getVirus());
        mapAddDefaultNode(map, 6, 11, getExploitation());
        mapAddDefaultNode(map, 6, 12, getExploitation());
        mapAddDefaultNode(map, 6, 13, getExploitation());
        mapAddDefaultNode(map, 6, 14, getExploitation());

        mapAddDefaultNode(map, 7, 0, getTrojan());
        mapAddDefaultNode(map, 7, 1, getTrojan());
        mapAddDefaultNode(map, 7, 2, getExploitation());
        mapAddDefaultNode(map, 7, 3, getTrojan());
        mapAddDefaultNode(map, 7, 4, getTrojan());
        mapAddDefaultNode(map, 7, 5, getTrojan());
        mapAddDefaultNode(map, 7, 6, getExploitation());
        mapAddDefaultNode(map, 7, 7, getExploitation());
        mapAddDefaultNode(map, 7, 8, getExploitation());
        mapAddDefaultNode(map, 7, 9, getExploitation());
        mapAddDefaultNode(map, 7, 10, getTrojan());
        mapAddDefaultNode(map, 7, 11, getTrojan());
        mapAddDefaultNode(map, 7, 12, getExploitation());
        mapAddDefaultNode(map, 7, 13, getTrojan());
        mapAddDefaultNode(map, 7, 14, getTrojan());

        mapAddDefaultNode(map, 8, 0, getTrojan());
        mapAddDefaultNode(map, 8, 1, getExploitation());
        mapAddDefaultNode(map, 8, 2, getTrojan());
        mapAddDefaultNode(map, 8, 3, getTrojan());
        mapAddDefaultNode(map, 8, 4, getTrojan());
        mapAddDefaultNode(map, 8, 5, getExploitation());
        mapAddDefaultNode(map, 8, 6, getTrojan());
        mapAddDefaultNode(map, 8, 7, getTrojan());
        mapAddDefaultNode(map, 8, 8, getExploitation());
        mapAddDefaultNode(map, 8, 9, getTrojan());
        mapAddDefaultNode(map, 8, 10, getVirus());
        mapAddDefaultNode(map, 8, 11, getTrojan());
        mapAddDefaultNode(map, 8, 12, getExploitation());
        mapAddDefaultNode(map, 8, 13, getTrojan());
        mapAddDefaultNode(map, 8, 14, getExploitation());

        mapAddDefaultNode(map, 9, 0, getTrojan());
        mapAddDefaultNode(map, 9, 1, getExploitation());
        mapAddDefaultNode(map, 9, 2, getTrojan());
        mapAddDefaultNode(map, 9, 3, getTrojan());
        mapAddDefaultNode(map, 9, 4, getExploitation());
        mapAddDefaultNode(map, 9, 5, getTrojan());
        mapAddDefaultNode(map, 9, 6, getTrojan());
        mapAddDefaultNode(map, 9, 7, getTrojan());
        mapAddDefaultNode(map, 9, 8, getExploitation());
        mapAddDefaultNode(map, 9, 9, getTrojan());
        mapAddDefaultNode(map, 9, 10, getExploitation());
        mapAddDefaultNode(map, 9, 11, getTrojan());
        mapAddDefaultNode(map, 9, 12, getExploitation());
        mapAddDefaultNode(map, 9, 13, getTrojan());
        mapAddDefaultNode(map, 9, 14, getTrojan());

        mapAddDefaultNode(map, 10, 0, getExploitation());
        mapAddDefaultNode(map, 10, 1, getTrojan());
        mapAddDefaultNode(map, 10, 2, getEffraction());
        mapAddDefaultNode(map, 10, 3, getVirus());
        mapAddDefaultNode(map, 10, 4, getExploitation());
        mapAddDefaultNode(map, 10, 5, getTrojan());
        mapAddDefaultNode(map, 10, 6, getExploitation());
        mapAddDefaultNode(map, 10, 7, getTrojan());
        mapAddDefaultNode(map, 10, 8, getExploitation());
        mapAddDefaultNode(map, 10, 9, getTrojan());
        mapAddDefaultNode(map, 10, 10, getTrojan());
        mapAddDefaultNode(map, 10, 11, getExploitation());
        mapAddDefaultNode(map, 10, 12, getTrojan());
        mapAddDefaultNode(map, 10, 13, getTrojan());
        mapAddDefaultNode(map, 10, 14, getTrojan());

        mapAddDefaultNode(map, 11, 0, getExploitation());
        mapAddDefaultNode(map, 11, 1, getTrojan());
        mapAddDefaultNode(map, 11, 2, getExploitation());
        mapAddDefaultNode(map, 11, 3, getTrojan());
        mapAddDefaultNode(map, 11, 4, getExploitation());
        mapAddDefaultNode(map, 11, 5, getTrojan());
        mapAddDefaultNode(map, 11, 6, getTrojan());
        mapAddDefaultNode(map, 11, 7, getExploitation());
        mapAddDefaultNode(map, 11, 8, getTrojan());
        mapAddDefaultNode(map, 11, 9, getTrojan());
        mapAddDefaultNode(map, 11, 10, getTrojan());
        mapAddDefaultNode(map, 11, 11, getExploitation());
        mapAddDefaultNode(map, 11, 12, getTrojan());
        mapAddDefaultNode(map, 11, 13, getExploitation());
        mapAddDefaultNode(map, 11, 14, getVirus());

        mapAddDefaultNode(map, 12, 0, getExploitation());
        mapAddDefaultNode(map, 12, 1, getTrojan());
        mapAddDefaultNode(map, 12, 2, getTrojan());
        mapAddDefaultNode(map, 12, 3, getExploitation());
        mapAddDefaultNode(map, 12, 4, getTrojan());
        mapAddDefaultNode(map, 12, 5, getTrojan());
        mapAddDefaultNode(map, 12, 6, getTrojan());
        mapAddDefaultNode(map, 12, 7, getExploitation());
        mapAddDefaultNode(map, 12, 8, getTrojan());
        mapAddDefaultNode(map, 12, 9, getExploitation());
        mapAddDefaultNode(map, 12, 10, getTrojan());
        mapAddDefaultNode(map, 12, 11, getExploitation());
        mapAddDefaultNode(map, 12, 12, getTrojan());
        mapAddDefaultNode(map, 12, 13, getTrojan());
        mapAddDefaultNode(map, 12, 14, getExploitation());

        mapAddDefaultNode(map, 13, 0, getTrojan());
        mapAddDefaultNode(map, 13, 1, getEffraction());
        mapAddDefaultNode(map, 13, 2, getTrojan());
        mapAddDefaultNode(map, 13, 3, getExploitation());
        mapAddDefaultNode(map, 13, 4, getVirus());
        mapAddDefaultNode(map, 13, 5, getExploitation());
        mapAddDefaultNode(map, 13, 6, getTrojan());
        mapAddDefaultNode(map, 13, 7, getExploitation());
        mapAddDefaultNode(map, 13, 8, getTrojan());
        mapAddDefaultNode(map, 13, 9, getTrojan());
        mapAddDefaultNode(map, 13, 10, getExploitation());
        mapAddDefaultNode(map, 13, 11, getTrojan());
        mapAddDefaultNode(map, 13, 12, getTrojan());
        mapAddDefaultNode(map, 13, 13, getTrojan());
        mapAddDefaultNode(map, 13, 14, getExploitation());

        mapAddDefaultNode(map, 14, 0, getTrojan());
        mapAddDefaultNode(map, 14, 1, getExploitation());
        mapAddDefaultNode(map, 14, 2, getTrojan());
        mapAddDefaultNode(map, 14, 3, getExploitation());
        mapAddDefaultNode(map, 14, 4, getTrojan());
        mapAddDefaultNode(map, 14, 5, getTrojan());
        mapAddDefaultNode(map, 14, 6, getExploitation());
        mapAddDefaultNode(map, 14, 7, getTrojan());
        mapAddDefaultNode(map, 14, 8, getVirus());
        mapAddDefaultNode(map, 14, 9, getTrojan());
        mapAddDefaultNode(map, 14, 10, getExploitation());
        mapAddDefaultNode(map, 14, 11, getEffraction());
        mapAddDefaultNode(map, 14, 12, getExploitation());
        mapAddDefaultNode(map, 14, 13, getVirus());
        mapAddDefaultNode(map, 14, 14, getExploitation());




        String descriptionMission = "Créez ton botnet et attaque la cible de la mission !";
        String levelName = "MISSION 6";

        Mission mission = new Mission(levelName, descriptionMission, null);

        Level level = new Level(map, 20, makeDefaultTarget(30), mission);
        new XStreamer<Level>().save(level, Main.class.getResource("../levels/level6.xml").getPath());
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
