/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Menu;

/**
 *
 * @author ara
 */
public class MainView {

    public static void main(String arg[]) {
        //Construction Model...
        ViewLauncher viewLauncher = new ViewLauncher();
        Menu.loadLevels();
        Menu.loadPlayers();

        //Construction Vue...
        View.makeInstance();

        //Lancement vue...
        viewLauncher.start();
    }
}
