/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.levelsFrame;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import model.Game;
import model.Menu;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import view.MasterFrame;

/**
 *
 * @author Ara
 */
public class GameOver extends BasicGameState implements ComponentListener{
    private Game gameInstance;
    private int stateID;
    private StateBasedGame parentState;
    private TrueTypeFont font;
    private TrueTypeFont fontCitation;
    private GameContainer container;
    private MouseOverArea quit;
    private MouseOverArea recommencer;
    private ArrayList<String> citations;
    private String citation;

    public GameOver(int stateID){
        this.stateID = stateID;
        this.citations=new ArrayList<String>();
        
        try{
            InputStream ips=new FileInputStream(getClass().getResource("../ressources/citation.txt").getPath()); 
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null){
		this.citations.add(ligne);
            }
	br.close(); 
        }catch (Exception e){
            System.out.println("Erreur sur le fichier à lire");
	}
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
        if (!this.citations.isEmpty()) this.citation=this.citations.get((int)(Math.random()*this.citations.size()));
        try {
            Font fontStart;
            fontStart = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(getClass().getResourceAsStream("../ressources/AgencyFB.ttf")));
            Font fontBase = fontStart.deriveFont(Font.PLAIN, 70);
            Font fontBaseCitation = fontStart.deriveFont(Font.ITALIC,30);
            this.font = new TrueTypeFont(fontBase, true);
            this.fontCitation = new TrueTypeFont(fontBaseCitation, true);
        } catch (FontFormatException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        quit = new MouseOverArea(container, new Image(getClass().getResource("../ressources/quit.png").getPath()), 350, 485, this);
        quit.setNormalColor(new Color(0.7f, 0.7f, 0.7f, 1f));
        quit.setMouseOverColor(new Color(0.9f, 0.9f, 0.9f, 1f));

        recommencer = new MouseOverArea(container, new Image(getClass().getResource("../ressources/recommencer.png").getPath()), 350, 430, this);
        recommencer.setNormalColor(new Color(0.7f, 0.7f, 0.7f, 1f));
        recommencer.setMouseOverColor(new Color(0.9f, 0.9f, 0.9f, 1f));
       
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        quit.render(container, g);
        recommencer.render(container, g);
        g.setFont(font);
        g.drawString("GAME OVER",100,100);
        g.setFont(fontCitation);
        g.drawString(this.citation, 100, 200);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }
    
    @Override
    public void componentActivated(AbstractComponent source) { //methode de l'interface ComponentListener
        if (source == quit) {
            this.container.exit();
        }
        if (source == recommencer) {
            try {
                Menu.replay();
                this.parentState.addState(new view.levelsFrame.Level(MasterFrame.LEVELSTATE));
                this.parentState.addState(new FinishLevel(MasterFrame.FINISHLEVELSTATE));
                this.parentState.initStatesList(container);
            } catch (SlickException ex) {
                
            }
            parentState.enterState(MasterFrame.LEVELSTATE, new FadeOutTransition(), new FadeInTransition());
            //game.enterState(MasterFrame.LEVELSTATE);
        }
    }

}
