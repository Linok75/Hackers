/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Menu;
import model.maps.IMap;
import model.maps.MapHexa;

/**
 *
 * @author ara
 */
public class MainView {

    public static void main(String arg[]) {
        //Construction Model...
        ViewLauncher viewLauncher = new ViewLauncher();
        IMap map = new MapHexa(); //Pour des raisons inconnues, sans cette ligne, la map ne s'affiche pas...
        Menu.loadLevels();
        Menu.loadPlayers();

        //Construction Vue...
        View.makeInstance();
        
        //Lancement vue...
        viewLauncher.start();
    }
}
