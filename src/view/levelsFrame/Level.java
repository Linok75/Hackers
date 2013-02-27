/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.levelsFrame;

import exceptions.NoSuffisantPA;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.logging.Logger;
import model.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import view.MasterFrame;
import view.levelsFrame.grid.GridHexa;
import view.levelsFrame.grid.NodeView;
import view.levelsFrame.infosNode.InfosNode;
import view.levelsFrame.infosNode.NodeIllustration;
import view.levelsFrame.listOfAtk.ListOfAtk;
import view.tools.Illustration;

/**
 *
 * @author Ara
 */
public class Level extends BasicGameState {

    private int stateID;
    private Game gameInstance;
    private StateBasedGame parentState;
    private TrueTypeFont font;
    private Color fontColor = new Color(19, 180, 7);
    private Illustration background;
    private float scaleX;
    private float scaleY;
    private GridHexa grid;
    private InfosNode infosNode;
    private ListOfAtk atkList;
    private InfosTarget infosTarget;

    public Level(int stateID, Game gameInstance) throws SlickException {
        this.stateID = stateID;
        this.gameInstance = gameInstance;

        this.grid = new GridHexa(stateID, gameInstance);
        this.infosNode = new InfosNode(stateID, gameInstance);
        this.atkList = new ListOfAtk(stateID, gameInstance);
        this.infosTarget = new InfosTarget(stateID, gameInstance);
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.parentState = game;
        try {
            Font fontStart;
            fontStart = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(getClass().getResourceAsStream("../ressources/AgencyFB.ttf")));
            Font fontBase = fontStart.deriveFont(Font.PLAIN, 40);
            this.font = new TrueTypeFont(fontBase, true);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }




        this.background = new Illustration(new Image(getClass().getResource("../ressources/background.png").getPath()), new Point(0, 0));
        this.scaleX = (float) container.getWidth() / this.background.getImage().getWidth();
        this.scaleY = (float) container.getHeight() / this.background.getImage().getHeight();

        this.grid.init(container, game);
        this.infosNode.init(container, game);
        this.atkList.init(container, game);
        this.infosTarget.init(container, game);


    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        container.sleep(20);
        g.scale(this.scaleX, this.scaleY);
        g.setColor(this.fontColor);
        g.setFont(this.font);

        g.drawImage(this.background.getImage(), this.background.getPos().x, this.background.getPos().y);

        this.infosNode.render(container, game, g);
        this.grid.render(container, game, g);
        this.atkList.render(container, game, g);
        this.infosTarget.render(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        this.grid.update(container, game, delta);
        this.infosNode.update(container, game, delta);
        this.atkList.update(container, game, delta);
        this.infosTarget.update(container, game, delta);

    }

    @Override
    public void mousePressed(int button, int x, int y) {

        NodeView node;
        NodeIllustration nodeIllustration;
        String atk;

        node = this.grid.nodeClicked(x, y, this.scaleX, this.scaleY);
        if (node != null) {
            this.infosNode.setInfosNode(node, this.font);
        }

        this.infosNode.setToHide(x, y, this.scaleX, this.scaleY);
        this.atkList.scrollAtk(x, y, scaleX, scaleY);

        atk = this.atkList.launchAtk(x, y, scaleX, scaleY);
        if (atk != null) {
            nodeIllustration = this.infosNode.launchContamination();
            if (nodeIllustration != null) {
                this.grid.contamination(nodeIllustration, atk);
            }
        }
        
        if(this.infosTarget.launchDdos(x, y, scaleX, scaleY)){
            try {
                this.gameInstance.getPlayer().ddos(this.grid.getNbCorrupt(), this.gameInstance.getLevel().getTarget());
                if(this.gameInstance.getLevel().completed()){
                    this.parentState.enterState(MasterFrame.FINISHLEVELSTATE);
                }
            } catch (NoSuffisantPA ex) {
                Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }
}
