/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import model.Game;
import model.ressources.attacks.Attack;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author ara
 */
public class MasterFrame extends StateBasedGame {

    public static final int MAINMENUSTATE = 0;
    public static final int LEVELSTATE = 1;
    private Game gameInstance;

    public MasterFrame() throws SlickException {
        super("Hackers");
        this.gameInstance = Game.getInstance();

        //this.addState(new MainMenu(MAINMENUSTATE));
        this.addState(new Level(LEVELSTATE, this.gameInstance));
        this.enterState(LEVELSTATE);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
//        this.getState(MAINMENUSTATE).init(container, this);
        this.getState(LEVELSTATE).init(container, this);
    }

//    @Override
//    public void update(GameContainer container, int delta) throws SlickException {
//        this.scaleX = (float) container.getWidth() / this.background.getImage().getWidth();
//        this.scaleY = (float) container.getHeight() / this.background.getImage().getHeight();
//        if (!this.next) {
//            if (!this.nodeFacePathOld.equals("")) {
//                this.nodeFaceOld = new Illustration(new Image(getClass().getResource(this.nodeFacePathOld).getPath()), new Point(70, -320));
//                this.nodeFacePathOld = "";
//                this.nodeDescribeOldPos = new Point(this.nodeFaceOld.getPos().x + 150, this.nodeFaceOld.getPos().y + 50);
//            }
//
//            if (this.infosVisible && this.infosNode.getPos().y < 0) {
//                this.infosNode.setPos(new Point(this.infosNode.getPos().x, this.infosNode.getPos().y + 5));
//                this.arrowHide.setPos(new Point(this.arrowHide.getPos().x, this.arrowHide.getPos().y + 5));
//                this.nodeFaceOld.setPos(new Point(this.nodeFaceOld.getPos().x, this.nodeFaceOld.getPos().y + 5));
//                this.nodeDescribeOldPos.setLocation(this.nodeDescribeOldPos.x, this.nodeDescribeOldPos.y + 5);
//            } else if (!this.infosVisible && this.infosNode.getPos().y > -350) {
//                this.infosNode.setPos(new Point(this.infosNode.getPos().x, this.infosNode.getPos().y - 5));
//                this.arrowHide.setPos(new Point(this.arrowHide.getPos().x, this.arrowHide.getPos().y - 5));
//                this.nodeFaceOld.setPos(new Point(this.nodeFaceOld.getPos().x, this.nodeFaceOld.getPos().y - 5));
//                this.nodeDescribeOldPos.setLocation(this.nodeDescribeOldPos.x, this.nodeDescribeOldPos.y - 5);
//            }
//        } else {
//            if (this.nodeDescribeNewPos == null) {
//                this.nodeFaceNew = new Illustration(new Image(getClass().getResource(this.nodeFacePathNew).getPath()).getSubImage(0, 0, 0, 140), new Point(1000, 30));
//                this.nodeDescribeNewPos = new Point(this.nodeFaceNew.getPos().x + 150, this.nodeFaceNew.getPos().y + 50);
//            }
//
//            if (this.nodeFaceNew.getPos().x > 70) {
//                if (this.nodeFaceNew.getImage().getWidth() < 110) {
//                    this.nodeFaceNew.setImage(new Image(getClass().getResource(this.nodeFacePathNew).getPath()).getSubImage(0, 0, this.nodeFaceNew.getImage().getWidth() + 5, 140));
//                }
//                if (this.nodeDescribeNewPos.x < 975) {
//                    if (this.nodeDescribeNew.length() < this.tmpDescribe.length()) {
//                        this.nodeDescribeNew = this.tmpDescribe.substring(0, this.nodeDescribeNew.length() + 1);
//                    }
//                }
//                this.nodeFaceNew.setPos(new Point(this.nodeFaceNew.getPos().x - 5, this.nodeFaceNew.getPos().y));
//                this.nodeDescribeNewPos.setLocation(this.nodeDescribeNewPos.x - 5, this.nodeDescribeNewPos.y);
//            } else {
//                this.nodeFaceOld = this.nodeFaceNew;
//                this.nodeDescribeOld = this.nodeDescribeNew;
//                this.nodeDescribeOldPos = this.nodeDescribeNewPos;
//                this.next = false;
//                this.nodeDescribeNew = "";
//                this.nodeDescribeNewPos = null;
//                this.nodeFaceNew = null;
//            }
//
//            this.nodeFaceOld.setImage(this.nodeFaceOld.getImage().getSubImage(5, 0, this.nodeFaceOld.getImage().getWidth() - 5, this.nodeFaceOld.getImage().getHeight()));
//            this.nodeDescribeOldPos.setLocation(this.nodeDescribeOldPos.x, this.nodeDescribeOldPos.y - 5);
//        }
//    }
    
}
