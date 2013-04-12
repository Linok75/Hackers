/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import model.Game;
import model.Level;
import model.maps.Defence;
import model.ressources.attacks.Attack;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Ara
 */
public final class View {

    private static volatile View viewInstance = null;

    public View() {

    }

    public static View makeInstance() {
        if (viewInstance != null) {
            throw new RuntimeException("Il est impossible de lancer plusieurs instance de view !");
        }
        View.viewInstance = new View();
        return View.viewInstance;
    }

    public static View getInstance() {
        if (viewInstance == null) {
            throw new RuntimeException("La classe 'View' n'a pas encore été instanciée !");
        } else {
            return viewInstance;
        }
    }

    public void start() {
        try {
            AppGameContainer baseFrame = new AppGameContainer(new MasterFrame());
//            baseFrame.setDisplayMode(baseFrame.getScreenWidth() * 75 / 100, baseFrame.getScreenHeight() * 75 / 100, false);
            baseFrame.setDisplayMode(baseFrame.getScreenWidth(), baseFrame.getScreenHeight(), true);
            baseFrame.setShowFPS(false);
            baseFrame.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
//        System.out.println("On start !");
    }
}
