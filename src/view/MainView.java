/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import model.Game;
import model.Level;
import model.Mission;
import model.Player;
import model.maps.Defence;
import model.maps.IMap;
import model.maps.MapHexa;
import model.maps.Target;
import model.ressources.attacks.DDoS;
import model.ressources.attacks.Phishing;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

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

        Target target = new Target(30, new ArrayList<Defence>());

        IMap map = new MapHexa();

        // defense anti phishing
        behavior.add(Defence.Phishing);

        // placement des cibles anti phishing sur la map
        map.setNode(5, 4, new model.maps.Node(behavior));
        map.setNode(1, 5, new model.maps.Node(behavior));
        map.setNode(2, 5, new model.maps.Node(behavior));
        map.setNode(7, 6, new model.maps.Node(behavior));
        map.setNode(3, 8, new model.maps.Node(behavior));
        map.setNode(5, 10, new model.maps.Node(behavior));
        map.setNode(1, 10, new model.maps.Node(behavior));
        map.setNode(7, 11, new model.maps.Node(behavior));


        Mission mission = new Mission("Level_1", "Créez ton botnet et attaque la cible de la mission !");

        Level level = new Level(map, 30, target, mission);

        Game.makeInstance(player, level);
        
        //Construction Vue...
        View.makeInstance();
        
        //Lancement vue...
        viewLauncher.start();
        
        //Lancement jeu...
        Game.getInstance().play();
    }
}
