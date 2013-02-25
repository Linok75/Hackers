/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Ara
 */
public class MainMenu extends BasicGameState{
    private int stateID;
    private Test test;

    public MainMenu(int stateID){
        this.stateID=stateID;
        test = new Test(0);
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        test.init(container, game);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        container.sleep(20);
        test.render(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        test.update(container, game, delta);
    }

}
