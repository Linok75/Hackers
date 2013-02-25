/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.tools.Illustration;
import java.awt.Point;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author MyMac
 */
public class Test extends BasicGameState {

    private int stateID;
    private Illustration background;
    private float scaleX;
    private float scaleY;

    public Test(int stateID) {
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return this.stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet.");
        this.background = new Illustration(new Image(getClass().getResource("ressources/background.png").getPath()), new Point(0, 0));

        this.scaleX = (float) gc.getWidth() / this.background.getImage().getWidth();
        this.scaleY = (float) gc.getHeight() / this.background.getImage().getHeight();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet.");
        g.scale(this.scaleX, this.scaleY);
        g.drawImage(this.background.getImage(), this.background.getPos().x, this.background.getPos().y);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
