/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Menu;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import model.Game;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
/**
 *
 * @author Quentin
 */
public class MainMenu extends BasicGameState implements ComponentListener{

    private int stateID;

    private StateBasedGame game;
    private GameContainer container;

    /* les deux boutons en bas du ChoixState*/

    private MouseOverArea quit;
    private MouseOverArea play;

    public MainMenu (int stateID) {
        this.stateID=stateID;
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game=game;
        this.container=container;
        quit = new MouseOverArea(container,new Image(getClass().getResource("ressources/quit.png").getPath()), 350, 485,this);
        quit.setNormalColor(new Color(0.7f,0.7f,0.7f,1f));
        quit.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));

        play = new MouseOverArea(container,new Image(getClass().getResource("ressources/newgame.png").getPath()), 350, 430, this);
        play.setNormalColor(new Color(0.7f,0.7f,0.7f,1f));
        play.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));

        Music music = new Music("soundtrack/Hacker.aiff");
        music.loop();

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        quit.render(container, g);
        play.render(container, g);
        g.drawString("HACKERS",100,100);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void componentActivated(AbstractComponent source) { //methode de l'interface ComponentListener
        if (source == quit) {
            this.container.exit();
        }
        if (source == play) {
            Game.destroyInstance();
            Menu.nouvellePartie("Linok");
            try {
                this.game.initStatesList(container);
            } catch (SlickException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            game.enterState(MasterFrame.LEVELSTATE, new FadeOutTransition(), new FadeInTransition());
            //game.enterState(MasterFrame.LEVELSTATE);
        }
    }

}