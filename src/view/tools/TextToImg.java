/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tools;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Ara
 */
public class TextToImg {

    public static Image convertTextToImg(String text, TrueTypeFont font) throws SlickException {
        Image img;
        
        img = imageFromText(text, font);
        return img;
    }

    private static Image imageFromText(String text, TrueTypeFont font) throws SlickException {
        Graphics graph;
        Image buff = new Image(font.getWidth(text), font.getHeight(text));

        graph = buff.getGraphics();
        graph.setFont(font);
        graph.setColor(new Color(19, 180, 7));
        graph.drawString(text, 0, 0);
        graph.flush();
        
        return buff;
    }
}
