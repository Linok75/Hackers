/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.levelsFrame.map;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author ara
 */
public class NodeView {

    public final static Dimension SIZE = new Dimension(110, 140);
    private Rectangle clickableArea;
    private Point pos;
    private boolean corrupt;
    private Color color;
    private Point linkToNode;
    private String pathPortrait;
    private String descr;
    private Image corBy;
    private int corPart;
    private Image state;
    private Image base;

    public NodeView(Point pos, Color color, String pathPortrait, String descr, Point linkToNode, Image image) {
        this.pos = pos;
        this.corrupt = false;
        this.clickableArea = new Rectangle(pos, NodeView.SIZE);
        this.color = color;
        this.pathPortrait = pathPortrait;
        this.descr = descr;
        this.linkToNode = linkToNode;
        this.corBy = null;
        this.corPart = 0;
        this.base = image.getScaledCopy(1);
        this.state = this.base.getScaledCopy(1);
    }

    public Point getLinkToNode() {
        return this.linkToNode;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public void corrupt(String atk) throws SlickException {
        this.corrupt = true;

        if (atk.equals("Phishing")) {
            this.corBy = new Image(getClass().getResource("../../ressources/phishing.png").getPath());
        }
        if (atk.equals("Trojan")) {
            this.corBy = new Image(getClass().getResource("../../ressources/cheval.png").getPath());
        }
        if (atk.equals("Effraction")) {
            this.corBy = new Image(getClass().getResource("../../ressources/effraction.png").getPath());
        }
        if (atk.equals("Virus")) {
            this.corBy = new Image(getClass().getResource("../../ressources/virus.png").getPath());
        }
        if (atk.equals("Exploitation")) {
            this.corBy = new Image(getClass().getResource("../../ressources/exploitation.png").getPath());
        }
        if (atk.equals("Brute Force")) {
            this.corBy = new Image(getClass().getResource("../../ressources/bruteForce.png").getPath());
        }

        this.corBy = this.corBy.getScaledCopy((float) (this.corBy.getWidth() / (this.base.getWidth() * 1.75)));
        this.refresh();

    }

    public Point getPos() {
        return pos;
    }

    public String getPathPortrait() {
        return this.pathPortrait;
    }

    public Color getColor() {
        return this.color;
    }

    public String getDescr() {
        return this.descr;
    }

    public Rectangle getClickableArea() {
        return clickableArea;
    }

    public void refresh() throws SlickException {
        Graphics g;

        g = this.state.getGraphics();

        g.drawImage(this.base, 0, 0, this.color);
        g.drawImage(this.base.getSubImage(0, 0, this.corPart, base.getHeight()), 0, 0, new Color(56, 118, 166));
        if (this.corPart >= this.state.getWidth() || this.corPart % 2 == 0) {
            this.corBy.drawCentered(this.state.getWidth() / 2, this.state.getHeight() / 2);
        }
        g.flush();

        if (this.corPart < this.state.getWidth()) {
            this.corPart++;
        }
    }

    public Image getState() {
        return this.state;
    }

    public boolean isCorrupt() {
        return this.corrupt;
    }

    public Point getCenter() {
        return new Point(this.pos.x + (this.base.getWidth() / 2), this.pos.y + (this.base.getHeight() / 2));
    }
}
