/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.animations;

import java.awt.Point;
import org.newdawn.slick.Graphics;
import tools.DrawUtil;

/**
 *
 * @author MyMac
 */
public class SegmentAnimation {

    private Point src;
    private Point cur; //current
    private Point cur1; //gestion of segment
    private Point trg;
    private static final int maxRadius = 7;
    private static final int minRadius = 0;
    private int radius = minRadius;
    private boolean up = true; //gestion of radius
    private static final int PHASE_START = 0;
    private static final int PHASE_INFINI = 1;
    private int phase;

    public SegmentAnimation(Point src, Point trg) {
        this.src = src;
        this.trg = trg;
        this.cur = new Point(src.x, src.y);
        this.cur1 = new Point(cur.x, cur.y);
        this.phase = PHASE_START;
    }

    public void draw(Graphics g) {
        g.setLineWidth(3);
        g.drawLine(src.x, src.y, cur1.x, cur1.y);
        g.drawOval(cur.x - (radius / 2), cur.y - (radius / 2), radius, radius);
    }

    public void update() {
        //MOUVEMENT
        cur = goTo(cur, trg);
        if (phase == PHASE_START) {
            cur1 = new Point(cur.x, cur.y);
            if (cur.x == trg.x && cur.y == trg.y) {
                phase = PHASE_INFINI;
            }
        } else if (phase == PHASE_INFINI) {

            if (cur.x == trg.x && cur.y == trg.y) {
                cur = new Point(src.x, src.y);
            }
        }

        //RADIUS
        if (up) {
            radius++;
            if (radius == maxRadius) {
                up = false;
            }
        } else {
            radius--;
            if (radius == minRadius) {
                up = true;
            }
        }
    }

    private Point goTo(Point src, Point trg) {
        Point[] points = DrawUtil.bresenham_line(src, trg);
        if (points.length < 2) {
            return trg;
        }
        else {
            return points[1];
        }
    }

    public boolean continu() {
        return phase == PHASE_INFINI;
    }

}
