/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.EndOfGame;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Scanner;
import model.ressources.attacks.DDoS;
import model.ressources.attacks.Phishing;
import tools.XStreamer;

/**
 *
 * @author MyMac
 */
public class Menu {

    public static int MODE_CONSOLE = 0;
    private static float currentView = 0; //mode console
    private static final Scanner sc = new Scanner(System.in); //mode console
    private static Player player;
    private static int idOfSave = -1;
    //private static ArrayList<Level> levels;
    private static ArrayList<Level> levels = new ArrayList<Level>();
    private static ArrayList<Player> players = new ArrayList<Player>();
    //
    private static int currentLevel;
    //
    private static boolean menu = true;
    
    //View :
    // 0 - Menu
    // 1 - Nouvelle Partie
    // 2 - Charger Partie
    // 3 - Paramv®tres
    public static void run() throws EndOfGame {

        Menu.MODE_CONSOLE = 1;

        loadLevels();
        loadPlayers();

        do {
            if (menu) {
                if (currentView == 0) {
                    affichMenu();
                    saisieMenu();
                } else if (currentView == 1) {
                    affichNouvellePartie();
                    saisieNouvellePartie();
                } else if (currentView == 2) {
                    affichChargerPartie();
                    saisieChargerPartie();
                } else if (currentView == 3) {
                    affichParametre();
                    saisieParametre();
                } else if (currentView == 4) {
                    affichListOfLevels();
                    saisieListOfLevels();
                }
            } else {
                nextLevel();
            }
        } while (true);
    }

    public static void loadLevels() {
        XStreamer<Level> xsLevels = new XStreamer<Level>();
        File f = new File(Menu.class.getResource("../levels/").getPath());

        FileFilter ff = new FileFilter() {

            @Override
            public boolean accept(File file) {
                return file.getName().matches("level[0-9][0-9]*.xml");
            }
        };

        boolean mauvaisChargement = false;
        Level level;
        for (File file : f.listFiles(ff)) {
            try {
                System.out.println(file.getAbsolutePath());
                level = xsLevels.load(file.getAbsolutePath());
                levels.add(level);
            } catch (Exception e) {
                //e.printStackTrace();
                //System.err.println("Erreur de chargement");
                mauvaisChargement = true;
            }
        }

        if (mauvaisChargement) {
            System.out.println("/!\\ Certains niveaux n'ont pas pu v™tre chargv©s.");
        }

    }

    public static void loadPlayers() {
        XStreamer<Player> xsPlayers = new XStreamer<Player>();
        File f = new File(Menu.class.getResource("../saves/").getPath());
        FileFilter ff = new FileFilter() {

            @Override
            public boolean accept(File file) {
                return file.getName().matches("player[0-9][0-9]*.xml");
            }
        };

        boolean mauvaisChargement = false;
        Player player;
        for (File file : f.listFiles(ff)) {
            try {
                player = xsPlayers.load(file.getAbsolutePath());
                players.add(player);
            } catch (Exception e) {
                mauvaisChargement = true;
            }
        }

        if (mauvaisChargement) {
            System.out.println("/!\\ Certains players n'ont pas pu v™tre chargv©s.");
        }
    }

    private static void affichMenu() {
        System.out.println("Menu");
        System.out.println("\t1 - Nouvelle Partie");
        System.out.println("\t2 - Charger Partie");
        System.out.println("\t3 - Paramv®tres");
        System.out.println("\t4 - Quitter");
    }

    private static void saisieMenu() {

        String saisie = sc.nextLine();

        if (saisie.equals("1")) {
            currentView = 1;
        } else if (saisie.equals("2")) {
            currentView = 2;
        } else if (saisie.equals("3")) {
            currentView = 3;
        } else if (saisie.equals("4")) {
            System.out.println("Au revoir !");
            System.exit(0);
        } else {
            System.out.println("Commande non valide, veuillez saisir le chiffre liv© v† la commande...");
        }

    }

    private static void affichNouvellePartie() {
        System.out.println("Nouvelle Partie");
        System.out.println("\t0 - Retour");
        System.out.println("\tVeuillez saisir votre surnom :");
    }

    private static void saisieNouvellePartie() {
        String saisie = sc.nextLine();
        if (saisie.equals("0")) {
            currentView = 0;
        } else {
            System.out.println("Chargement...");

            nouvellePartie(saisie);
            System.out.println("Lancement niveau 0");
            Game.getInstance().play();

            menu = false;
            //throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    public static void nouvellePartie(String playerName) {
        // TO-DO : Vv©rifier que le nom ne comporte pas des caractv®res invalides
        player = new Player(playerName);
        player.addAttack(new DDoS());
        player.addAttack(new Phishing());

        // Chargement du premier niveau
        currentLevel = 0;
        Level level = levels.get(currentLevel);

        savePlayer();

        if (Menu.MODE_CONSOLE == 1) {
            System.out.println("Dv©but de la partie.");
        }

        // Lancement de la partie
        Game.makeInstance(player, level);
    }

    private static void affichChargerPartie() {
        System.out.println("Charger Partie");
        System.out.println("\t0 - Retour");
        System.out.println("-----------------------");
        int i = 1;
        for (Player player : players) {
            System.out.println("\t" + i + " - " + player.getName());
            i++;
        }
        System.out.println("-----------------------");
    }

    private static void saisieChargerPartie() {
        String saisie = sc.nextLine();
        if (saisie.equals("0")) {
            currentView = 0;
        } else { //Verification saisie = num d'une sauvegarde
            try {
                int num = Integer.parseInt(saisie);
                chargerPartie(num);
            } catch (NumberFormatException nfe) {
                System.out.println("Erreur, veuillez saisir le numv©ro de la sauvegarde que vous voulez charger.");
            }
        }
    }

    public static void chargerPartie(int idSave) {
        if (idSave >= 1 && idSave <= players.size()) {
            if (Menu.MODE_CONSOLE == 1) {
                System.out.println("Chargement...");
            }

            idOfSave = idSave; // - 1
            player = players.get(idOfSave - 1);

            if (player.getAdvanced() >= levels.size()) {
                if (Menu.MODE_CONSOLE == 1) {
                    System.out.println("Le joueur v† dv©jv† fini le jeu au moins une fois");
                }
                currentView = 4;
                return;
            }

            currentLevel = player.getAdvanced();
            Level level = levels.get(currentLevel);

            Game.makeInstance(player, level);

            if (Menu.MODE_CONSOLE == 1) {
                // Lancement de la partie
                Game.getInstance().play();
                menu = false;
                System.out.println("Lancement niveau " + currentLevel);
            }

        } else {
            if (Menu.MODE_CONSOLE == 1) {
                System.out.println("Numv©ro de sauvegarde inexistant");
            }
        }
    }

    private static void affichListOfLevels() {
        System.out.println("Selection du niveau");
        System.out.println("\t0 - Retour");
        System.out.println("-----------------------");
        int i = 1;
        for (Level level : levels) {
            System.out.println("\t" + i);
            i++;
        }
        System.out.println("-----------------------");
    }

    private static void saisieListOfLevels() {
        String saisie = sc.nextLine();
        if (saisie.equals("0")) {
            currentView = 0;
        } else { //Verification saisie = num d'une sauvegarde
            try {
                int num = Integer.parseInt(saisie);
                listOfLevels(idOfSave);
            } catch (NumberFormatException nfe) {
                System.out.println("Erreur, veuillez saisir le numv©ro de la sauvegarde que vous voulez charger.");
            }
        }
    }

    public static void listOfLevels(int idLevel) {
        if (idLevel >= 1 && idLevel <= levels.size()) {
            if (Menu.MODE_CONSOLE == 1) {
                System.out.println("Chargement...");
            }

            currentLevel = idLevel - 1;
            Level level = levels.get(currentLevel);

            // Lancement de la partie
            Game.makeInstance(player, level);

            if (Menu.MODE_CONSOLE == 1) {
                System.out.println("Lancement niveau " + currentLevel);
                Game.getInstance().play();
                menu = false;
            }
        } else {
            if (Menu.MODE_CONSOLE == 1) {
                System.out.println("Niveau inexistant");
            }
        }
    }

    private static void affichParametre() {
        System.out.println("Pas de paramv®tres pour le moment ...");
    }

    private static void saisieParametre() {
        currentView = 0;
    }

    private static void savePlayer() {

        XStreamer<Player> saver = new XStreamer<Player>();
        if (idOfSave == -1) {
            idOfSave = (players.size() + 1);
        }
        saver.save(player, Menu.class.getResource("../saves/").getPath() + "player" + idOfSave + ".xml");

    }

    public static void replay() {
        player.reset();
        Game.getInstance().setLevel(levels.get(currentLevel));
    }

    public static void nextLevel() throws EndOfGame { //TODOEND
        if (Menu.MODE_CONSOLE == 1) {
            System.out.println("NEXT LEVEL");
        }

        player.reset();
        Level level = levels.get(currentLevel);
        level.reward(player);

        //System.out.println(currentLevel+" -> "+(player.getAdvanced() + 1));

        if (player.getAdvanced() <= currentLevel) {
            player.setAdvanced(player.getAdvanced() + 1);
        }

        savePlayer();

        if (player.getAdvanced() >= levels.size() && currentLevel == levels.size() - 1) {
            if (Menu.MODE_CONSOLE == 1) {
                System.out.println("Vous avez terminer le jeu !");
                System.out.println("Retour au menu ... ");
            }else{
                throw new EndOfGame();
            }
            
            currentView = 0;
            menu = true;
            return;
        }

        currentLevel++;
        if (Menu.MODE_CONSOLE == 1) {
            System.out.println(currentLevel);
        }
        level = levels.get(currentLevel);

        Game.getInstance().setLevel(level);

        // Lancement de la partie
        //Game.makeInstance(player, level);
        //Game.getInstance().play();

    }
}