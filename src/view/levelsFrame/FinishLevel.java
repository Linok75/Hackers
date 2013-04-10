/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.levelsFrame;

import exceptions.EndOfGame;
import java.awt.Color;
import model.Game;
import model.Menu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
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
    private UnicodeFont font;
    private boolean startUpdate;
    private Color fontColor = new Color(19, 180, 7);

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
        this.font = new UnicodeFont(getClass().getResource("../ressources/AgencyFB.ttf").getPath(), 40, false, false);
        this.font.addAsciiGlyphs();
        this.font.addGlyphs(400, 600);
        this.font.getEffects().add(new ColorEffect(this.fontColor));
        this.font.loadGlyphs();
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
            try {
                this.startUpdate = false;
                container.sleep(5000);
                Menu.nextLevel();
                this.parentState.initStatesList(container);
                this.parentState.enterState(MasterFrame.LEVELSTATE);
            } catch (EndOfGame ex) {
                this.parentState.initStatesList(container);
                this.parentState.enterState(MasterFrame.ENDGAME);
            }
        }else{
            this.startUpdate = true;
        }
        
    }

}
