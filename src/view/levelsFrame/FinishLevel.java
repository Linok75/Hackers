/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.levelsFrame;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.logging.Logger;
import model.Game;
import model.Menu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import view.MasterFrame;

/**
 *
 * @author Ara
 */
public class FinishLevel extends BasicGameState{
    private Game gameInstance;
    private int stateID;
    private StateBasedGame parentState;
    private TrueTypeFont font;
    private boolean startUpdate;

    public FinishLevel(int stateID){
        this.stateID = stateID;
        this.startUpdate = false;
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.parentState = game;
        this.gameInstance = Game.getInstance();
        try {
            Font fontStart;
            fontStart = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(getClass().getResourceAsStream("../ressources/AgencyFB.ttf")));
            Font fontBase = fontStart.deriveFont(Font.PLAIN, 70);
            this.font = new TrueTypeFont(fontBase, true);
        } catch (FontFormatException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setFont(font);
        g.drawString("CONGRATULATIONS !!!",100,100);
        g.drawString("LEVEL SUCCESSFULLY COMPLETED !!!", 100, 300);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        //nextLevel
        if(this.startUpdate){
            this.startUpdate = false;
            container.sleep(5000);
            Menu.nextLevel();
            this.parentState.initStatesList(container);
            this.parentState.enterState(MasterFrame.LEVELSTATE);
        }
        this.startUpdate = true;
    }

}
