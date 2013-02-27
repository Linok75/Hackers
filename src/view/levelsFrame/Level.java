/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.levelsFrame;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import model.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;
import view.levelsFrame.grid.GridHexa;
import view.tools.Illustration;
import view.levelsFrame.infosNode.InfosNode;
import view.levelsFrame.listOfAtk.ListOfAtk;
import view.levelsFrame.infosNode.NodeIllustration;
import view.levelsFrame.grid.NodeView;

/**
 *
 * @author Ara
 */
public class Level extends BasicGameState {

    private int stateID;
    private Game gameInstance;
    private TrueTypeFont font;
    private Color fontColor = new Color(19, 180, 7);
    private Illustration background;
    private float scaleX;
    private float scaleY;

    private GridHexa grid;
    private InfosNode infosNode;
    private ListOfAtk atkList;

    public Level(int stateID, Game gameInstance) throws SlickException {
        this.stateID = stateID;
        this.gameInstance = gameInstance;
        
        this.grid = new GridHexa(stateID, gameInstance);
        this.infosNode = new InfosNode(stateID, gameInstance);
        this.atkList = new ListOfAtk(stateID, gameInstance);
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        try {
            Font fontBase = new Font("Agency FB", java.awt.Font.TRUETYPE_FONT, 30);
            InputStream tmpFont = ResourceLoader.getResourceAsStream(getClass().getResource("../ressources/AgencyFB.ttf").getPath());
            Font.createFont(Font.TRUETYPE_FONT, tmpFont);
            this.font = new TrueTypeFont(fontBase, true);

        } catch (FontFormatException | IOException ffe) {
            ffe.printStackTrace();
        }

        this.background = new Illustration(new Image(getClass().getResource("../ressources/background.png").getPath()), new Point(0, 0));
        this.scaleX = (float) container.getWidth() / this.background.getImage().getWidth();
        this.scaleY = (float) container.getHeight() / this.background.getImage().getHeight();
        
        this.grid.init(container, game);
        this.infosNode.init(container, game);
        this.atkList.init(container, game);

        
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
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        this.grid.update(container, game, delta);
        this.infosNode.update(container, game, delta);
        this.atkList.update(container, game, delta);

        
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        
        NodeView node;
        NodeIllustration nodeIllustration;
        String atk;

        node = this.grid.nodeClicked(x, y,this.scaleX,this.scaleY);
        if (node != null) {
            this.infosNode.setInfosNode(node, this.font);
        }

        this.infosNode.setToHide(x, y,this.scaleX,this.scaleY);
        this.atkList.scrollAtk(x, y, scaleX, scaleY);
        
        atk = this.atkList.launchAtk(x, y, scaleX, scaleY);
        if(atk != null){
            nodeIllustration = this.infosNode.launchContamination();
            if(nodeIllustration != null){
                this.grid.contamination(nodeIllustration, atk);
            }
        }
        
    }
}
