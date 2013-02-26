/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tools;

import java.awt.Point;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author ara
 */
public class Illustration {
    private Image image;
    private Point pos;
    private Color filter;
    private boolean disabled;

    public Illustration(Image image, Point pos) throws SlickException{
        this.image = image;
        this.pos = pos;
        this.filter = new Color(200, 200, 200, 200);
        this.disabled=false;
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
    
     public Color getFilter() {
        return this.filter;
    }
    
    public void disabled(){
        this.filter = new Color(75, 75, 75, 200);
        this.disabled=true;
    }
    
    public void enabled(){
        this.filter = new Color(200, 200, 200, 200);
        this.disabled=false;
    }
    
    public boolean isDisabled(){
        return this.disabled;
    }
}
