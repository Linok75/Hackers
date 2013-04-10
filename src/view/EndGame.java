/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.logging.Logger;
import model.Game;
import model.Menu;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import view.levelsFrame.FinishLevel;
import view.levelsFrame.Level;

/**
 *
 * @author Ara
 */
class EndGame extends BasicGameState implements ComponentListener {

    private int stateID;
    private MouseOverArea quit;
    private MouseOverArea newgame;
    private StateBasedGame parentState;
    private Game gameInstance;
    private GameContainer container;
    private TrueTypeFont font;

    public EndGame(int stateID) {
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.parentState = game;
        this.gameInstance = Game.getInstance();
        this.container = container;

        try {
            Font fontStart;
            fontStart = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(getClass().getResourceAsStream("../ressources/AgencyFB.ttf")));
            Font fontBase = fontStart.deriveFont(Font.PLAIN, 70);
            this.font = new TrueTypeFont(fontBase, true);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        quit = new MouseOverArea(container, new Image(getClass().getResource("ressources/quit.png").getPath()), 350, 485, this);
        quit.setNormalColor(new Color(0.7f, 0.7f, 0.7f, 1f));
        quit.setMouseOverColor(new Color(0.9f, 0.9f, 0.9f, 1f));

        newgame = new MouseOverArea(container, new Image(getClass().getResource("ressources/newgame.png").getPath()), 350, 430, this);
        newgame.setNormalColor(new Color(0.7f, 0.7f, 0.7f, 1f));
        newgame.setMouseOverColor(new Color(0.9f, 0.9f, 0.9f, 1f));
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        quit.render(container, g);
        newgame.render(container, g);
        g.setFont(font);
        g.drawString("Great ! You have finish the game !", 100, 100);
        g.drawString("Wait a futur update to try new levels !", 100, 150);


    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void componentActivated(AbstractComponent source) {
        if (source == quit) {
            this.container.exit();
        }
        if (source == newgame) {
            try {
                Menu.nouvellePartie("Linok");
                this.parentState.initStatesList(container);
                parentState.enterState(MasterFrame.LEVELSTATE, new FadeOutTransition(), new FadeInTransition());
            } catch (SlickException ex) {
                Logger.getLogger(EndGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }
}
