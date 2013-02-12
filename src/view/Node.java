/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author ara
 */
public class Node {
    
    private static Dimension SIZE = new Dimension(110,140);
    private Rectangle clickableArea;
    private int level;
    private Point pos;
    private boolean corrupt;
    private String path;

    public Node(int level, Point pos, boolean corrupt) {
        this.level = level;
        this.pos = pos;
        this.corrupt = corrupt;
        this.path = "";
        this.clickableArea=new Rectangle(pos, this.SIZE);
        
        this.upPath();
    }

    public Node(int level, Point pos) {
        this.level = level;
        this.pos = pos;
        this.corrupt = false;
        this.path = "";
        this.clickableArea=new Rectangle(pos, this.SIZE);
        
        this.upPath();
    }

    private void upPath() {
        if (!this.corrupt) {
            switch (this.level) {
                case 0:
                    this.path = "ressources/nodeLv0.png";
                    break;
                case 1:
                    this.path = "ressources/nodeLv1.png";
                    break;
                case 2:
                    this.path = "ressources/nodeLv2.png";
                    break;
                case 3:
                    this.path = "ressources/nodeLv3.png";
                    break;
                case 4:
                    this.path = "ressources/nodeLv4.png";
                    break;
                case 5:
                    this.path = "ressources/nodeLv5.png";
                    break;
                case 6:
                    this.path = "ressources/nodeLv6.png";
                    break;
                default:
                    this.path = "ressources/nodeMaster.png";
            }
        } else {
            this.path = "ressources/nodeCorrupt.png";
        }
    }
    
    public void corrupt(){
        this.corrupt=true;
        
        this.upPath();
    }

    public Point getPos() {
        return pos;
    }

    public String getPath() {
        return path;
    }
    
    public Rectangle getClickableArea() {
        return clickableArea;
    }
}
