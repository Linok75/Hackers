/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import model.Game;
import model.Level;
import model.Menu;
import model.Mission;
import model.Player;
import model.maps.Defence;
import model.maps.IMap;
import model.maps.MapHexa;
import model.maps.Target;
import model.ressources.attacks.DDoS;
import model.ressources.attacks.Phishing;

/**
 *
 * @author ara
 */
public class MainView {

    public static void main(String arg[]) {
        //Construction Model...
        ViewLauncher viewLauncher = new ViewLauncher();
        Player player = new Player();
        player.addAttack(new DDoS());
        player.addAttack(new Phishing());

        ArrayList<Defence> behavior = new ArrayList<Defence>();

        String test = "test";

        Target target = new Target(30, new ArrayList<Defence>(), test, MapHexa.NODEDEFAULTPATH);

        IMap map = new MapHexa();

        // defense anti phishing
        behavior.add(Defence.Phishing);

        // placement des cibles anti phishing sur la map
        map.setNode(5, 4, new model.maps.Node(behavior, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(1, 5, new model.maps.Node(behavior, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(2, 5, new model.maps.Node(behavior, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(4, 6, new model.maps.Node(behavior, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(3, 8, new model.maps.Node(behavior, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(5, 2, new model.maps.Node(behavior, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(1, 7, new model.maps.Node(behavior, test, MapHexa.NODEDEFAULTPATH));
        map.setNode(6, 2, new model.maps.Node(behavior, test, MapHexa.NODEDEFAULTPATH));

        //-Djava.library=C:\Users\Maxou's computer\Dropbox\Hackers\Hackers\lib\lwjgl-2.8.5\native\windows
        Mission mission = new Mission("Level_1", "Créez ton botnet et attaque la cible de la mission !");

        Level level = new Level(map, 30, target, mission);

        Game.makeInstance(player, level);

        //Construction Vue...
        View.makeInstance();

        //Lancement vue...
        viewLauncher.start();

        //Lancement jeu...
        //Game.getInstance().play();
        
        Menu.loadLevels();
        Menu.loadPlayers();
    }
}
