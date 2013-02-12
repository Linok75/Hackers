/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Scanner;
import model.maps.Node;

/**
 *
 * @author ldavid
 */
public final class Game {

    //L'utilisation du mot clé volatile permet, en Java version 5 et supérieur, d'éviter le cas où "Singleton.instance" est non-nul,
    // mais pas encore "réellement" instancié.
    private static volatile Game instance = null;
    private boolean PLAY;
    //private ... time;
    private Player player;
    private Level level;

    private Game(Player player, Level level) {
        this.player = player;
        this.level = level;

        this.PLAY = false;
    }

    public Level getLevel() {
        return level;
    }

    public static Game makeInstance(Player player, Level level) {
        if (instance != null) {
            throw new RuntimeException("Il est impossible de lancer plusieurs instance de game !");
        }
        Game.instance = new Game(player, level);
        return Game.instance;
    }

    public static Game getInstance() {
        if (instance == null) {
            throw new RuntimeException("La classe 'Game' n'a pas encore été instanciée !");
        } else {
            return instance;
        }
    }

    public void play() {
        this.PLAY = true;

        // Test Console
        Scanner sc = new Scanner(System.in);
        String request;
        while (this.PLAY) {
            request = sc.nextLine();
            request = request.toLowerCase();
            if (request.equals("player")) {
                System.out.println("\n**************** PLAYER ****************\n" + this.player + "************************************\n");

            } else if (request.equals("level")) {
                System.out.println("\n**************** LEVEL ****************\n" + this.level + "************************************\n");

            } else if (request.equals("map")) {
                System.out.println("\n**************** MAP ****************\n" + this.level.getMap() + "************************************\n");
            } else if (request.equals("phishing")) {

                System.out.println("Quel 'node' voulez vous hacker ?");
                System.out.println("\n**************** MAP ****************\n" + this.level.getMap() + "************************************\n");

                int li = -1, co = -1;
                while (!this.level.getMap().in0_LI(li)) {
                    System.out.println("Veuillez saisir la ligne :");
                    li = sc.nextInt();
                }
                while (!this.level.getMap().in0_CO(co)) {
                    System.out.println("Veuillez saisir la colonne :");
                    co = sc.nextInt();
                }

                this.level.getMap().phishing(this.level.getMap().getNode(li, co));
                //System.out.println("Phishing terminé !");
                System.out.println("\n**************** MAP ****************\n" + this.level.getMap() + "************************************\n");
                System.out.println("Total de noeuds dans votre botnet : "+this.level.getMap().countAllNodesHack());
            } else if (request.equals("ddos")) {

            } else if (request.equals("help")) {
                printCommands();
            } else if (request.equals("exit")) {
                System.exit(0);
            } else {
                System.out.println("Commande introuvable");
            }
        }

        System.out.println("**************** END OF GAME ****************");

    }

    // Test Console
    public void printCommands() {
        System.out.println("Liste des commandes :\n\t"
                + "- help\t\taffiche la liste des commandes\n\t"
                + "- player\taffiche les caractéristiques du joueur\n\t"
                + "- level\t\taffiche les caractéristiques de niveau\n\t"
                + "- phishing\tlance une attaque 'phishing' sur un 'node'\n\t"
                + "- ddos\t\tlance une attaque DDoS sur le serveur cible\n\t"
                + "- map\t\taffiche la map\n\t"
                + "- exit\t\tpermet de quitter le jeu\n");
    }
}
