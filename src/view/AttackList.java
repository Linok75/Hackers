/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import model.Game;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Ara
 */
public class AttackList {

    public static Image getListImg(Game instance) throws SlickException {
        Image img;

        img = null;
        
        try {
            Font fontBase = new Font("Agency FB", java.awt.Font.PLAIN, 30);
            InputStream tmpFont = ResourceLoader.getResourceAsStream(AttackList.class.getResource("ressources/AgencyFB.ttf").getPath());
            Font.createFont(Font.TRUETYPE_FONT, tmpFont);
            TrueTypeFont font = new TrueTypeFont(fontBase, true);

            img = constructImg(instance, font);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }


        return img;
    }

    private static Image constructImg(Game instance, TrueTypeFont font) throws SlickException {
        Graphics graph;
        Image completed, cadreList;
        int nbLigne, height;

        nbLigne = 0;
        cadreList = new Image(AttackList.class.getResource("ressources/cadreListElem.png").getPath());

        if (cadreList.getHeight() * instance.getPlayer().getAttackList().size() >= 390) {
            height = cadreList.getHeight() * instance.getPlayer().getAttackList().size();
        } else {
            height = 390;
        }

        completed = new Image(cadreList.getWidth(), height);
        graph = completed.getGraphics();

        while (cadreList.getHeight() * nbLigne < height) {
            graph.drawImage(cadreList, 0, cadreList.getHeight() * nbLigne);
            try {
                graph.drawImage(TextToImg.convertTextToImg(instance.getPlayer().getAttackList().get(nbLigne).getTitle(), font), 25, cadreList.getHeight() * nbLigne + 25);
            } catch (IndexOutOfBoundsException e) {
                graph.drawImage(TextToImg.convertTextToImg("?????", font), 25, cadreList.getHeight() * nbLigne + 25);
            }
            nbLigne++;
        }

        graph.flush();

        return completed;
    }
}
