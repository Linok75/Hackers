/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tools;

import java.awt.Point;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author ara
 */
public class Illustration {
    private Image image;
    private Point pos;
    
    public Illustration(Image image, Point pos) throws SlickException{
        this.image = image;
        this.pos = pos;
    }
    
    public void setPos(Point pos){
        this.pos = pos;
    }

    public void setImage(Image image){
        this.image=image;
    }
    
    public Image getImage() {
        return image;
    }

    public Point getPos() {
        return pos;
    }
}
