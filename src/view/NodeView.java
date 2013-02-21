/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import org.newdawn.slick.Color;

/**
 *
 * @author ara
 */
public class NodeView implements Cloneable {
    
    private final static Dimension SIZE = new Dimension(110,140);
    private Rectangle clickableArea;
    private Point pos;
    private boolean corrupt;
    private Color color;
    private String pathPortrait;
    private String descr;

    public NodeView(Point pos, Color color, String pathPortrait, String descr) {
        this.pos = pos;
        this.corrupt = false;
        this.clickableArea=new Rectangle(pos, this.SIZE);
        this.color = color;
        this.pathPortrait = pathPortrait; 
        this.descr=descr;
   }

    public void setColor(Color color) {
        this.color=color;
    }
    
    public void setPos(Point pos){
        this.pos=pos;
    }
    
    public void corrupt(){
        this.corrupt=true;
        
        this.setColor(new Color(56, 118, 166));
    }

    public Point getPos() {
        return pos;
    }

    public String getPathPortrait(){
        return this.pathPortrait;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public String getDescr(){
        return this.descr;
    }
    
    public Rectangle getClickableArea() {
        return clickableArea;
    }
}
