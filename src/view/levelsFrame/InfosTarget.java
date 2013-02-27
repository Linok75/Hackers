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
public class InfosTarget extends BasicGameState {
    
    private int stateID;
    private Game gameInstance;
    private Illustration targetView;
    private Illustration ddosButton;
    
    public InfosTarget(int stateID, Game gameInstance){
        this.stateID=stateID;
        this.gameInstance=gameInstance;
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.targetView = new Illustration(new Image(getClass().getResource(this.gameInstance.getLevel().getTarget().getPath()).getPath()), new Point(1200,250));
        this.ddosButton = new Illustration(new Image(getClass().getResource("../ressources/launchDdos.png").getPath()), new Point(this.targetView.getPos().x+15, this.targetView.getPos().y+this.targetView.getImage().getHeight()+220));
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(this.targetView.getImage(), this.targetView.getPos().x, this.targetView.getPos().y);
        g.drawString(this.gameInstance.getLevel().getTarget().getDescription(), this.targetView.getPos().x+this.targetView.getImage().getWidth()+10, this.targetView.getPos().y+20);
        g.fillRect(this.targetView.getPos().x-20, this.targetView.getPos().y+this.targetView.getImage().getHeight()+100, 356, 10);
        g.drawString("Point d'action : "+this.gameInstance.getPlayer().getPower(), this.targetView.getPos().x, this.targetView.getPos().y+this.targetView.getImage().getHeight()+150);
        g.drawImage(this.ddosButton.getImage(), this.ddosButton.getPos().x, this.ddosButton.getPos().y);
        
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
