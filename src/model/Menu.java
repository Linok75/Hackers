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
    //private static ArrayList<Level> levels;
    private static ArrayList<Level> levels = new ArrayList<Level>();

    //View :
    // 0 - Menu
    // 1 - Nouvelle Partie
    // 2 - Charger Partie
    // 3 - Paramètres
    public static void run() {

        loadLevels();

        do {
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
            System.out.println("/!\\ Certains niveaux n'ont pas pu être chargés.");
        }

    }

    public static void affichMenu() {
        System.out.println("Menu");
        System.out.println("\t1 - Nouvelle Partie");
        System.out.println("\t2 - Charger Partie");
        System.out.println("\t3 - Paramètres");
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
            System.out.println("Commande non valide, veuillez saisir le chiffre lié à la commande...");
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

            // TO-DO : Vérifier que le nom ne comporte pas des caractères invalides
            player = new Player(saisie);
            player.addAttack(new DDoS());
            player.addAttack(new Phishing());

            // Chargement du premier niveau
            Level level = levels.get(0);

            System.out.println("Début de la partie.");

            // Lancement de la partie
            Game.makeInstance(player, level);
            Game.getInstance().play();

            //throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    public static void affichChargerPartie() {
        System.out.println("Charger Partie");
        System.out.println("\t0 - Retour");
        System.out.println("-----------------------");
        //for
        //System.out.println("i - Niveau Date");
        System.out.println("-----------------------");
    }

    public static void saisieChargerPartie() {
        String saisie = sc.nextLine();
        if (saisie.equals("0")) {
            currentView = 0;
        } //else if () { //Verification saisie = num d'une sauvegarde

        //}
    }

    public static void affichParametre() {
        System.out.println("Pas de paramètres pour le moment ...");
    }

    public static void saisieParametre() {
        currentView = 0;
    }
}
