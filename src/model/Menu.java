/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

    private static float currentView = 0;
    private static final Scanner sc = new Scanner(System.in);
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
    // 3 - Param√®tres
    public static void run() {

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
                }
            } else {
                nextLevel();
            }
        } while (true);
    }

    private static void loadLevels() {
        XStreamer<Level> xsLevels = new XStreamer<Level>();
        //System.out.println(Menu.class.getResource("../levels/").getPath());
        //File f = new File("/Users/Quentin/Documents/NetBeansProjects/hacking/Hackers/src/levels");
        File f = new File(Menu.class.getResource("../levels/").getPath());

        // /!\ Si on veut faire un executable, faudra voir le chemin qu'il faudra mettre /!\

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
                //System.out.println(file.getAbsolutePath());
                level = xsLevels.load(file.getAbsolutePath());
                levels.add(level);
            } catch (Exception e) {
                //e.printStackTrace();
                //System.err.println("Erreur de chargement");
                mauvaisChargement = true;
            }
        }

        if (mauvaisChargement) {
            System.out.println("/!\\ Certains niveaux n'ont pas pu √™tre charg√©s.");
        }

    }

    private static void loadPlayers() {
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
            System.out.println("/!\\ Certains players n'ont pas pu √™tre charg√©s.");
        }
    }

    public static void affichMenu() {
        System.out.println("Menu");
        System.out.println("\t1 - Nouvelle Partie");
        System.out.println("\t2 - Charger Partie");
        System.out.println("\t3 - Param√®tres");
        System.out.println("\t4 - Quitter");
    }

    public static void saisieMenu() {

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
            System.out.println("Commande non valide, veuillez saisir le chiffre li√© √† la commande...");
        }

    }

    public static void affichNouvellePartie() {
        System.out.println("Nouvelle Partie");
        System.out.println("\t0 - Retour");
        System.out.println("\tVeuillez saisir votre surnom :");
    }

    public static void saisieNouvellePartie() {
        String saisie = sc.nextLine();
        if (saisie.equals("0")) {
            currentView = 0;
        } else {
            System.out.println("Chargement...");

            // TO-DO : V√©rifier que le nom ne comporte pas des caract√®res invalides
            player = new Player(saisie);
            player.addAttack(new DDoS());
            player.addAttack(new Phishing());

            // Chargement du premier niveau
            currentLevel = 0;
            Level level = levels.get(currentLevel);

            savePlayer();

            System.out.println("D√©but de la partie.");

            // Lancement de la partie
            Game.makeInstance(player, level);
            System.out.println("Lancement niveau 0");
            Game.getInstance().play();

            menu = false;
            //throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    public static void affichChargerPartie() {
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

    public static void saisieChargerPartie() {
        String saisie = sc.nextLine();
        if (saisie.equals("0")) {
            currentView = 0;
        } else { //Verification saisie = num d'une sauvegarde
            try {
                int num = Integer.parseInt(saisie);
                if (num >= 1 && num <= players.size()) {
                    System.out.println("Chargement...");

                    idOfSave = num; // - 1
                    player = players.get(idOfSave - 1);

                    // Choix du niveau ? ou seulement le dernier ?

                    if (player.getAdvanced() >= levels.size()) {
                        System.out.println("Le joueur √† d√©j√† fini le jeu au moins une fois");
                        //TODO
                        System.err.println("TODO");
                        System.exit(0);
                    }

                    currentLevel = player.getAdvanced();
                    Level level = levels.get(currentLevel);

                    // Lancement de la partie
                    Game.makeInstance(player, level);

                    System.out.println("Lancement niveau "+currentLevel);
                    Game.getInstance().play();
                    menu = false;
                } else {
                    System.out.println("Num√©ro de sauvegarde inexistant");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Erreur, veuillez saisir le num√©ro de la sauvegarde que vous voulez charger.");
            }
        }
    }

    public static void affichParametre() {
        System.out.println("Pas de param√®tres pour le moment ...");
    }

    public static void saisieParametre() {
        currentView = 0;
    }

    public static void savePlayer() {

        XStreamer<Player> saver = new XStreamer<Player>();
        if (idOfSave == -1) {
            idOfSave = (players.size()+1);
        }
        saver.save(player, Menu.class.getResource("../saves/").getPath()+"player"+idOfSave+".xml");

    }

    public static void nextLevel() { //TODOEND
        System.out.println("NEXT LEVEL");

        //TODO = trouver une place a la ligne suivante x)
        player.setPower(100);

        System.out.println(currentLevel+" -> "+(player.getAdvanced() + 1));

        if (player.getAdvanced() <= currentLevel) {
            player.setAdvanced(player.getAdvanced() + 1);
        }

        savePlayer();

        if (player.getAdvanced() >= levels.size()) {
            System.out.println("Vous avez terminer le jeu !");
            System.out.println("Retour au menu !");
            currentView = 0;
            menu = true;
            return;
            //System.out.println("Le joueur √† d√©j√† fini le jeu au moins une fois");
        }

        currentLevel = player.getAdvanced();
        System.out.println(currentLevel);
        Level level = levels.get(currentLevel);

        Game.getInstance().setLevel(level);

        // Lancement de la partie
        //Game.makeInstance(player, level);
        Game.getInstance().play();

    }
}
