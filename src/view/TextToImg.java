/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

/**
 *
 * @author Ara
 */
public class TextToImg {

    public static Image convertTextToImg(String text) throws SlickException {
        Image img;
        Texture tmp;
        
        img = null;
        tmp = null;

        try {
            Font font;
            font = Font.createFont(Font.TRUETYPE_FONT, new File(TextToImg.class.getResource("ressources/AgencyFB.ttf").getPath()));
            font = font.deriveFont(40f);
            
            tmp = BufferedImageUtil.getTexture("",imageFromText(text, font));
            img = new Image(tmp.getImageWidth(), tmp.getImageHeight());
            img.setTexture(tmp);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    private static BufferedImage imageFromText(String text, Font font) {
        Graphics graph;
        BufferedImage buff;
        FontRenderContext context;
        Rectangle2D bounds;
        
        context = new FontRenderContext(null,true,true);
        bounds = font.getStringBounds(text, context);
        
        buff = new BufferedImage((int)bounds.getWidth(), (int)bounds.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graph = buff.createGraphics();
        graph.setFont(font);
        graph.setColor(new Color(19, 180, 7));
        graph.drawString(text,(int) bounds.getX(),(int) -bounds.getY());
        
        return buff;
    }
}
