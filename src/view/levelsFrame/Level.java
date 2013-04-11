/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.levelsFrame;

import exceptions.NoSuffisantPA;
import java.awt.Color;
import java.awt.Point;
import java.util.logging.Logger;
import model.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import view.MasterFrame;
import view.levelsFrame.infosNode.InfosNode;
import view.levelsFrame.infosNode.NodeIllustration;
import view.levelsFrame.listOfAtk.ListOfAtk;
import view.levelsFrame.map.Map;
import view.levelsFrame.map.NodeView;
import view.tools.Illustration;

/**
 *
 * @author Ara
 */
public class Level extends BasicGameState {

    private int stateID;
    private Game gameInstance;
    private StateBasedGame parentState;
    private UnicodeFont font;
    private Color fontColor = new Color(19, 180, 7);
    private Illustration background;
    private float scaleX;
    private float scaleY;
    private Map map;
    private InfosNode infosNode;
    private ListOfAtk atkList;
    private InfosGlobal infosTarget;

    public Level(int stateID) throws SlickException {
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


        this.font = new UnicodeFont(getClass().getResource("../ressources/AgencyFB.ttf").getPath(), 40, false, false);
        this.font.addAsciiGlyphs();
        this.font.addGlyphs(400, 600);
        this.font.getEffects().add(new ColorEffect(this.fontColor));
        this.font.loadGlyphs();




        this.background = new Illustration(new Image(getClass().getResource("../ressources/background.png").getPath()), new Point(0, 0));
        this.scaleX = (float) container.getWidth() / this.background.getImage().getWidth();
        this.scaleY = (float) container.getHeight() / this.background.getImage().getHeight();

        this.map = new Map(gameInstance, stateID, this.parentState, this.scaleX);
        this.infosNode = new InfosNode(stateID, gameInstance);
        this.atkList = new ListOfAtk(stateID, gameInstance);
        this.infosTarget = new InfosGlobal(stateID, gameInstance);


        this.map.init(container, game);
        this.infosNode.init(container, game);
        this.atkList.init(container, game);
        this.infosTarget.init(container, game);


    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        container.sleep(20);
        g.setAntiAlias(true);
        g.scale(this.scaleX, this.scaleY);
        g.setFont(this.font);

        g.drawImage(this.background.getImage(), this.background.getPos().x, this.background.getPos().y);

        this.infosNode.render(container, game, g);
        this.map.render(container, game, g);
        this.atkList.render(container, game, g);
        this.infosTarget.render(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        this.map.update(container, game, delta);
        this.infosNode.update(container, game, delta);
        this.atkList.update(container, game, delta);
        this.infosTarget.update(container, game, delta);

    }

    @Override
    public void mousePressed(int button, int x, int y) {

        NodeView node;
        NodeIllustration nodeIllustration;
        String atk;

        node = this.map.nodeClicked(x, y, this.scaleX, this.scaleY);
        if (node != null) {
            this.infosNode.setInfosNode(node, this.font);
        }

        this.infosNode.setToHide(x, y, this.scaleX, this.scaleY);
        this.atkList.scrollAtk(x, y, scaleX, scaleY);

        atk = this.atkList.launchAtk(x, y, scaleX, scaleY);
        if (atk != null) {
            nodeIllustration = this.infosNode.launchContamination();
            if (nodeIllustration != null) {
                try {
                    this.map.contamination(nodeIllustration, atk);
                } catch (SlickException ex) {
                    Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }

        if (this.map.canIDoDDoS() && this.infosTarget.launchDdos(x, y, scaleX, scaleY)) {
            try {
                this.gameInstance.getPlayer().ddos(this.map.getNbCorrupt(), this.gameInstance.getLevel().getTarget());
                if (this.gameInstance.getLevel().completed()) {
                    this.parentState.enterState(MasterFrame.FINISHLEVELSTATE);
                }
            } catch (NoSuffisantPA ex) {
                this.parentState.enterState(MasterFrame.GAMEOVERSTATE);
            }
        }

        if (this.gameInstance.getPlayer().getPower() < 10) {
            this.parentState.enterState(MasterFrame.GAMEOVERSTATE);
        }
    }
}
