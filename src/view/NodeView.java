/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author ara
 */
public class NodeView {
    
    private final static Dimension SIZE = new Dimension(110,140);
    private Rectangle clickableArea;
    private Point pos;
    private boolean corrupt;
    private Color color;

    public NodeView(Point pos, Color color) {
        this.pos = pos;
        this.corrupt = false;
        this.clickableArea=new Rectangle(pos, this.SIZE);
        this.color = color;
    }

    public void setColor(Color color) {
        this.color=color;
    }
    
    public void corrupt(){
        this.corrupt=true;
        
        this.setColor(new Color(56, 118, 166));
    }

    public Point getPos() {
        return pos;
    }

    public Color getColor() {
        return this.color;
    }
    
    public Rectangle getClickableArea() {
        return clickableArea;
    }
}
