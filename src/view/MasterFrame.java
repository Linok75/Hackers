/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.levelsFrame.Level;
import model.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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

        this.addState(new MainMenu(MAINMENUSTATE));
        this.addState(new Level(LEVELSTATE, this.gameInstance));
        this.enterState(LEVELSTATE);
//        this.enterState(MAINMENUSTATE);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.getState(MAINMENUSTATE).init(container, this);
        this.getState(LEVELSTATE).init(container, this);
    }

}
