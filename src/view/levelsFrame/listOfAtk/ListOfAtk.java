/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.levelsFrame.listOfAtk;

import java.awt.Point;
import model.Game;
import model.ressources.attacks.Attack;
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
public class ListOfAtk extends BasicGameState {

    private int stateID;
    private Game gameInstance;
    private boolean preAtk;
    private boolean nextAtk;
    private Illustration nextButton;
    private Illustration preButton;
    private Illustration cadreList;
    private Illustration background;
    private int scrolledPx, ligneMax;
    private Rectangle clipArea;

    public ListOfAtk(int stateID, Game gameInstance) {
        this.gameInstance = gameInstance;
        this.stateID = stateID;
        this.preAtk = false;
        this.nextAtk = false;
        this.scrolledPx = 0;
        this.ligneMax = 0;
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.background = new Illustration(new Image(getClass().getResource("../../ressources/atkList.png").getPath()), new Point(1100, 607));
        this.cadreList = new Illustration(new Image(getClass().getResource("../../ressources/cadreAtk.png").getPath()), new Point(this.background.getPos().x + 80, this.background.getPos().y + 131));
        this.nextButton = new Illustration(new Image(getClass().getResource("../../ressources/buttonScroll.png").getPath()), new Point(this.background.getPos().x + 380, this.background.getPos().y + 200));
        this.preButton = new Illustration(this.nextButton.getImage().getFlippedCopy(false, true), new Point(this.nextButton.getPos().x, this.nextButton.getPos().y + this.nextButton.getImage().getHeight() + 50));
        this.clipArea = new Rectangle(this.cadreList.getPos().x, this.cadreList.getPos().y, this.cadreList.getImage().getWidth(), this.cadreList.getImage().getHeight() * 5 - 10);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        int numLigne;

        g.drawImage(this.background.getImage(), this.background.getPos().x, this.background.getPos().y);
        g.drawImage(this.nextButton.getImage(), this.nextButton.getPos().x, this.nextButton.getPos().y, this.nextButton.getFilter());
        g.drawImage(this.preButton.getImage(), this.preButton.getPos().x, this.preButton.getPos().y, this.preButton.getFilter());

        g.setWorldClip(this.clipArea);
        numLigne = 0;
        for (Attack atk : this.gameInstance.getPlayer().getAttackList()) {
            if (!"DDoS".equals(atk.getTitle())) {
                g.drawImage(this.cadreList.getImage(), this.cadreList.getPos().x, this.cadreList.getPos().y + this.cadreList.getImage().getHeight() * numLigne);
                g.drawString(atk.getTitle(), this.cadreList.getPos().x + 50, this.cadreList.getPos().y + this.cadreList.getImage().getHeight() * numLigne + 25);
                numLigne++;
            }
        }
        while (numLigne < 6) {
            g.drawImage(this.cadreList.getImage(), this.cadreList.getPos().x, this.cadreList.getPos().y + this.cadreList.getImage().getHeight() * numLigne);
            g.drawString("?????", this.cadreList.getPos().x + 50, this.cadreList.getPos().y + this.cadreList.getImage().getHeight() * numLigne + 25);
            numLigne++;
        }
        this.ligneMax = numLigne;
        g.clearWorldClip();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (this.cadreList.getPos().y >= this.clipArea.getY()) {
            this.nextButton.disabled();
        } else {
            this.nextButton.enabled();
        }

        if (this.cadreList.getPos().y + this.cadreList.getImage().getHeight() * this.ligneMax - 10 <= this.clipArea.getY() + this.clipArea.getHeight()) {
            this.preButton.disabled();
        } else {
            this.preButton.enabled();
        }

        if (this.nextAtk || this.preAtk) {
            this.scrolledAtk();
        }
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

    public String launchAtk(int x, int y, float scaleX, float scaleY) {
        Rectangle scaleArea;
        int numLigne;

        scaleArea = new Rectangle((int) (this.clipArea.getX() * scaleX), (int) (this.clipArea.getY() * scaleY), (int) (this.clipArea.getWidth() * scaleX), (int) (this.clipArea.getHeight() * scaleY));
        if (scaleArea.contains(x, y)) {
            numLigne = 0;
            for (Attack atk : this.gameInstance.getPlayer().getAttackList()) {
                if (!"DDoS".equalsIgnoreCase(atk.getTitle())) {
                    scaleArea = new Rectangle((int) (this.cadreList.getPos().x * scaleX), (int) ((this.cadreList.getPos().y + this.cadreList.getImage().getHeight() * numLigne) * scaleY), (int) (this.cadreList.getImage().getWidth() * scaleX), (int) (this.cadreList.getImage().getHeight() * scaleY));
                    if (scaleArea.contains(x, y)) {
                        return atk.getTitle();
                    }
                    numLigne++;
                }
            }
        }
        return null;
    }

    private void scrolledAtk() {
        if (this.scrolledPx + 1 > this.cadreList.getImage().getHeight()) {
            if (this.nextAtk) {
                if (!this.nextButton.isDisabled()) {
                    this.cadreList.setPos(new Point(this.cadreList.getPos().x, this.cadreList.getPos().y + (this.cadreList.getImage().getHeight() - this.scrolledPx)));
                }
            } else if (!this.preButton.isDisabled()) {
                this.cadreList.setPos(new Point(this.cadreList.getPos().x, this.cadreList.getPos().y - (this.cadreList.getImage().getHeight() - this.scrolledPx)));
            }
            this.scrolledPx = 0;
            this.nextAtk = false;
            this.preAtk = false;
        } else {
            this.scrolledPx += 1;
            if (this.nextAtk) {
                if (!this.nextButton.isDisabled()) {
                    this.cadreList.setPos(new Point(this.cadreList.getPos().x, this.cadreList.getPos().y + 1));
                }
            } else if (!this.preButton.isDisabled()) {
                this.cadreList.setPos(new Point(this.cadreList.getPos().x, this.cadreList.getPos().y - 1));
            }
        }
    }
}
