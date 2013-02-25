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
 * @author Ara
 */
public class SubIllustration extends Illustration{
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    
    
    public SubIllustration(Image image, Point pos, int startX, int startY, int endX, int endY) throws SlickException{
        super(image,pos);
        this.startX=startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        

    }
    
    public SubIllustration(Image image, Point pos) throws SlickException{
        super(image,pos);
        this.startX=0;
        this.endX = image.getWidth();
        this.startY = 0;
        this.endY = image.getHeight();
    }
    
    public void resetSubPos(){
        this.startX=0;
        this.endX = this.getImage().getWidth();
        this.startY = 0;
        this.endY = this.getImage().getHeight();
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }
}
