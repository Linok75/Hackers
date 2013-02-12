/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hackers;

import java.util.ArrayList;
import model.Game;
import model.Level;
import model.Mission;
import model.Player;
import model.maps.*;
import model.ressources.attacks.Phishing;

/**
 *
 * @author ldavid
 */
public final class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        test();
    }

    public static void test() {

        Player player = new Player();
        player.addAttack(new Phishing());
        //System.out.println("Player :\n"+player);

        ArrayList<Defence> behavior = new ArrayList<Defence>();

        Target target = new Target(30, new ArrayList<Defence>());

        IMap map = new MapHexa();

        behavior.add(Defence.Phishing);

        //map.setNode(5,1, new Node(behavior));
        //map.setNode(1,2, new Node(behavior));
        //map.setNode(7,0, new Node(behavior));
        map.setNode(5,4, new Node(behavior));
        map.setNode(1,5, new Node(behavior));
        map.setNode(2,5, new Node(behavior));
        map.setNode(7,6, new Node(behavior));
        map.setNode(3,8, new Node(behavior));
        map.setNode(5,10, new Node(behavior));
        map.setNode(1,10, new Node(behavior));
        map.setNode(7,11, new Node(behavior));

        Mission mission = new Mission("Level_1", "Attack !");

        Level level = new Level(map, 30, target, mission);

        Game.makeInstance(player, level);
        Game.getInstance().play();
    }


}
