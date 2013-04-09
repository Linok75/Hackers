      /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import view.levelsFrame.FinishLevel;
import view.levelsFrame.Level;

/**
 *
 * @author ara
 */
public class MasterFrame extends StateBasedGame {

    public static final int MAINMENUSTATE = 0;
    public static final int LEVELSTATE = 1;
    public static final int FINISHLEVELSTATE = 2;
    private Game gameInstance;

    public MasterFrame() throws SlickException {
        super("Hackers");

        this.addState(new MainMenu(MAINMENUSTATE));
        
        this.enterState(MAINMENUSTATE);
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        System.out.println(key+" = "+c);

        // Gestion du son

        // 65 = F7
        // 68 = F10
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        for(int i=0;i<this.getStateCount();i++){
            this.getState(i).init(container, this);
        }
    }

}