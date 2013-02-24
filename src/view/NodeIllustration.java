/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Point;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Ara
 */
public class NodeIllustration extends Illustration {

    private Point linkToNode;
    private Image descr;
    private int xStart;
    private int xEnd;

    public NodeIllustration(Image image, Point pos, Image descr, Point linkToNode) throws SlickException {
        super(image, pos);
        this.descr = descr;
        this.xStart = 0;
        this.xEnd = image.getWidth() + 20 + descr.getWidth() + 5;
        this.linkToNode = linkToNode;
    }
    
    public Point getLinkToNode(){
        return this.linkToNode;
    }

    public void setDescr(Image descr) {
        this.descr = descr;
    }

    public void setXStart(int xStart) {
        this.xStart = xStart;
    }

    public void setXEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public int getXStart() {
        return this.xStart;
    }

    public int getXEnd() {
        return this.xEnd;
    }

    public Image getDescr() {
        return this.descr;
    }
}
