/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.NoSuffisantPA;
import java.util.Scanner;
import model.maps.Target;
import model.ressources.attacks.Attack;

/**
 *
 * @author ldavid
 */
public final class Game {

    //L'utilisation du mot clé volatile permet, en Java version 5 et supérieur, d'éviter le cas où "Singleton.instance" est non-nul,
    // mais pas encore "réellement" instancié.
    private static volatile Game instance = null;
    private static final Scanner sc = new Scanner(System.in); //gestion de la saisie en mode console
    private boolean PLAY; //gestion de la fin du jeu en mode console
    //private ... time; // TODO : système de temps
    private Player player;
    private Level level;

    private Game(Player player, Level level) {
        this.player = player;
        this.level = level;

        this.PLAY = false;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Level getLevel() {
        return level;
    }
    public Player getPlayer() {
        return player;
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
            return null;
        } else {
            return instance;
        }
    }

    // Jeu console
    public void play() {
        this.PLAY = true;

        System.out.println("Début de la partie !");
        System.out.println("\n**************** MAP ****************\n" + this.level.getMap() + "************************************\n");

        // Test Console
        String request;
        while (this.PLAY) {
            request = sc.nextLine();
            request = request.toLowerCase();

            for (Attack attack : player.getAttackList()) {
                if (!attack.getTitle().equalsIgnoreCase("ddos") && request.equalsIgnoreCase(attack.getTitle())) {
                    makeAttack(request);
                }
            }


            if (request.equals("player")) {
                System.out.println("\n**************** PLAYER ****************\n" + this.player + "************************************\n");

            } else if (request.equals("level")) {
                System.out.println("\n**************** LEVEL ****************\n" + this.level + "************************************\n");

            } else if (request.equals("map")) {
                System.out.println("\n**************** MAP ****************\n" + this.level.getMap() + "************************************\n");
                /*
                 * } else if (request.equals("phishing")) {
                 * makeAttack("Phishing"); } else if (request.equals("virus")) {
                 * makeAttack("Virus");
                 */            } else if (request.equals("ddos")) {
                try {
                    player.ddos(this.level.getMap().countAllNodesHack(), (Target) this.level.getTarget());

                    if (this.level.getTarget().isHack()) {//TODO
                        System.out.println("Mission Accomplie !");

                        // Vous avez terminé le jeu ...
                        // Crédits ... (lol)
                        // Retour au menu principal
                        // Il peut toujours charger sa partie, mais cette fois ci, avoir accès au niveau qu'il désire

                        //System.exit(0);
                    } else {
                        System.out.println("L'attaque DDoS n'a pas été suffisante pour faire tomber le serveur ...");
                    }
                    //System.out.println("DDoS OK !");
                } catch (NoSuffisantPA ex) {
                    System.out.println("DDoS KO !");
                    //Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            } else if (request.equals("help")) {
                printCommands();
            } else if (request.equals("exit")) {
                System.exit(0);
            } else {
                System.out.println("Commande introuvable ... (taper 'help' pour afficher la liste des commandes)");
            }

            // Ne pas mettre 10 en dur : TODO
            if (this.player.getPower() < 10) {
                System.out.println("Vous ne disposez plus de suffisament de 'ressources' pour continuer :\n\n\tGAME OVER");

                // Recommencer le niveau
                // Retour au menu

                System.exit(0);
            }

            if (this.level.completed()) {
                System.out.println("Bravo ! Vous avez réussi le niveau");
                this.PLAY = false;
                //System.exit(0);
            }
        }

        //System.out.println("**************** END OF GAME ****************");

    }
    private void makeAttack(String nameOfAttack) {
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
        try {
            this.player.attack(nameOfAttack, this.level.getMap().getNode(li, co));
            System.out.println(nameOfAttack + " OK !");
            System.out.println("\n**************** MAP ****************\n" + this.level.getMap() + "************************************\n");
            System.out.println("Total de noeuds dans votre botnet : " + this.level.getMap().countAllNodesHack());
        } catch (NoSuffisantPA ex) {
            System.out.println(nameOfAttack + " KO : Vous ne possedez pas suffisament de points d'action !");
        }
    }

    // Test Console
    public void printCommands() {
        String str = "Liste des commandes :\n\t"
                + "- help\t\taffiche la liste des commandes\n\t"
                + "- player\taffiche les caractéristiques du joueur\n\t"
                + "- level\t\taffiche les caractéristiques de niveau\n\t";

        for (Attack attack : player.getAttackList()) {
            if (!attack.getTitle().equalsIgnoreCase("ddos")) {
                str += "- " + attack.getTitle().toLowerCase() + "\tlance une attaque '" + attack.getTitle().toLowerCase() + "' sur un 'node'\n\t";
            }
        }

        str += "- ddos\t\tlance une attaque DDoS sur le serveur cible\n\t"
                + "- map\t\taffiche la map\n\t"
                + "- exit\t\tpermet de quitter le jeu\n";

        System.out.println(str);
    }
}
