/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author ara
 */
public class MainView {
   public static void main(String arg[]) {
        try {
            AppGameContainer baseFrame = new AppGameContainer(new MasterFrame());
            baseFrame.setDisplayMode(baseFrame.getScreenWidth() * 75 / 100, baseFrame.getScreenHeight() * 75 / 100, false);
            baseFrame.setShowFPS(false);
            baseFrame.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
