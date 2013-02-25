/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.listOfAtk;

import view.tools.Illustration;
import java.awt.Point;
import model.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Ara
 */
public class ListOfAtk extends BasicGameState {

    private TrueTypeFont font;
    private int stateID;
    private Game gameInstance;
    private AttackList atkList;
    private boolean preAtk;
    private boolean nextAtk;
    private Illustration nextButton;
    private Illustration preButton;

    public ListOfAtk(int stateID, Game gameInstance, TrueTypeFont font) {
        this.gameInstance = gameInstance;
        this.stateID = stateID;
        this.font = font;
        this.preAtk = false;
        this.nextAtk = false;
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.atkList = new AttackList(gameInstance.getPlayer().getAttackList(), this.font, new Point(1180, 735));
        this.nextButton = new Illustration(new Image(getClass().getResource("ressources/button.png").getPath()), new Point(1480, 830));
        this.preButton = new Illustration(this.nextButton.getImage().getFlippedCopy(false, true), new Point(this.nextButton.getPos().x, this.nextButton.getPos().y + this.nextButton.getImage().getHeight() + 50));
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(this.atkList.getImage().getSubImage(this.atkList.getStartX(), this.atkList.getStartY(), this.atkList.getEndX(), this.atkList.getEndY()), this.atkList.getPos().x, this.atkList.getPos().y);
        g.drawImage(this.nextButton.getImage(), this.nextButton.getPos().x, this.nextButton.getPos().y);
        g.drawImage(this.preButton.getImage(), this.preButton.getPos().x, this.preButton.getPos().y);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (this.nextAtk) {
            this.scrollNextAtk();
        }

        if (this.preAtk) {
            this.scrollPreAtk();
        }
    }
    
    public void setFont(TrueTypeFont font){
        this.font=font;
    }

    public void scrollAtk(int x, int y, float scaleX, float scaleY) {
        Rectangle scaleArea;
        
        if (!this.nextAtk && !this.preAtk) {
            scaleArea = new Rectangle((int) (this.nextButton.getPos().x * scaleX), (int) (this.nextButton.getPos().y * scaleY), (int) (this.nextButton.getImage().getWidth() * scaleX), (int) (this.nextButton.getImage().getHeight() * scaleY));
            if (scaleArea.contains(x, y)) {
                this.nextAtk = true;
            }

            scaleArea = new Rectangle((int) (this.preButton.getPos().x * scaleX), (int) (this.preButton.getPos().y * scaleY), (int) (this.preButton.getImage().getWidth() * scaleX), (int) (this.preButton.getImage().getHeight() * scaleY));
            if (scaleArea.contains(x, y)) {
                this.preAtk = true;
            }
        }
    }

    public boolean launchAtk(int x, int y, float scaleX, float scaleY) {
        Rectangle scaleArea;
        
        scaleArea = new Rectangle((int) (this.atkList.getPos().x * scaleX), (int) (this.atkList.getPos().y * scaleY), (int) (this.atkList.getImage().getWidth() * scaleX), (int) (this.atkList.getImage().getHeight() * scaleY));
        if (scaleArea.contains(x, y)) {
            return true;
        }
        return false;
    }
    
    private void scrollNextAtk() {
        if (this.atkList.scrollAgain(true)) {
            this.atkList.setStartY(this.atkList.getStartY() + 1);
        } else {
            this.nextAtk = false;
        }
    }

    private void scrollPreAtk() {
        if (this.atkList.scrollAgain(false)) {
            this.atkList.setStartY(this.atkList.getStartY() - 1);
        } else {
            this.preAtk = false;
        }
    }
}
