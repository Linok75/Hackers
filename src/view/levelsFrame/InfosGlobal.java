/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.levelsFrame;

import java.awt.Point;
import model.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import view.tools.Illustration;

/**
 *
 * @author Ara
 */
public class InfosGlobal extends BasicGameState {
    
    private int stateID;
    private Game gameInstance;
    private Illustration targetView;
    private Illustration ddosButton;
    private Illustration targetBackground;
    private Illustration playerBackground;
    
    public InfosGlobal(int stateID, Game gameInstance){
        this.stateID=stateID;
        this.gameInstance=gameInstance;
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.targetBackground = new Illustration(new Image(getClass().getResource("../ressources/infosTarget.png").getPath()), new Point(1077,-20));
        this.playerBackground = new Illustration(new Image(getClass().getResource("../ressources/infosPlayer.png").getPath()), new Point(this.targetBackground.getPos().x+23,this.targetBackground.getPos().y+this.targetBackground.getImage().getHeight()-180));
        this.targetView = new Illustration(new Image(getClass().getResource(this.gameInstance.getLevel().getTarget().getPath()).getPath()), new Point(this.targetBackground.getPos().x+120,this.targetBackground.getPos().y+150));
        this.ddosButton = new Illustration(new Image(getClass().getResource("../ressources/buttonDDOS.png").getPath()), new Point(this.playerBackground.getPos().x+240, this.playerBackground.getPos().y+157));
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(this.targetBackground.getImage(), this.targetBackground.getPos().x, this.targetBackground.getPos().y);
        g.drawImage(this.playerBackground.getImage(), this.playerBackground.getPos().x, this.playerBackground.getPos().y);
        g.drawImage(this.targetView.getImage(), this.targetView.getPos().x, this.targetView.getPos().y);
        g.drawString(this.gameInstance.getLevel().getTarget().getDescription(), this.targetView.getPos().x+this.targetView.getImage().getWidth()+10, this.targetView.getPos().y+20);
        g.drawString("Difficulté : "+this.gameInstance.getLevel().getTarget().getDifficulty(), this.targetBackground.getPos().x+120, this.targetBackground.getPos().y+450);
        g.drawString("Taille du botnet : "+this.gameInstance.getPlayer().getPower(), this.ddosButton.getPos().x-150,this.ddosButton.getPos().y-50);
        g.drawString("PA restant : ",this.ddosButton.getPos().x-150,this.ddosButton.getPos().y);
        g.drawString(""+this.gameInstance.getPlayer().getPower(), this.ddosButton.getPos().x-100, this.ddosButton.getPos().y+35);
        g.drawImage(this.ddosButton.getImage(), this.ddosButton.getPos().x, this.ddosButton.getPos().y);
        g.drawString("Launch DDOS", this.ddosButton.getPos().x+33, this.ddosButton.getPos().y+30);
        
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
       
    }
    
    public boolean launchDdos(int x, int y, float scaleX, float scaleY){
        Rectangle scaleArea;

        scaleArea = new Rectangle((int) (this.ddosButton.getPos().x * scaleX), (int) (this.ddosButton.getPos().y * scaleY), (int) (this.ddosButton.getImage().getWidth() * scaleX), (int) (this.ddosButton.getImage().getHeight() * scaleY));
        if (scaleArea.contains(x, y)) {
            return true;
        }
        return false;
    }
}
