/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.levelsFrame.infosNode;

import java.awt.Point;
import java.util.logging.Logger;
import model.Game;
import model.Level;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import view.levelsFrame.map.NodeView;
import view.tools.Illustration;
import view.tools.TextToImg;

/**
 *
 * @author Ara
 */
public class InfosNode extends BasicGameState {

    private int stateID;
    private Illustration infosNode;
    private Illustration arrowHide;
    private NodeIllustration nodeFocus;
    private NodeIllustration nodeActive;
    private boolean infosNodeVisible;
    private Game gameInstance;

    public InfosNode(int stateID, Game gameInstance) {
        this.gameInstance = gameInstance;
        this.stateID = stateID;
        this.nodeFocus = null;
        this.nodeActive = null;
        this.infosNodeVisible = false;
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.nodeFocus = null;
        this.nodeActive = null;
        this.infosNodeVisible = false;
        this.infosNode = new Illustration(new Image(getClass().getResource("../../ressources/infosNode.png").getPath()), new Point(-27, -400));
        this.arrowHide = new Illustration(new Image(getClass().getResource("../../ressources/arrowHide.png").getPath()).getScaledCopy((float) 0.08), new Point((int)(this.infosNode.getImage().getWidth()+this.infosNode.getPos().getX()-130), (int)(this.infosNode.getImage().getHeight()+this.infosNode.getPos().getY()-190)));
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(this.infosNode.getImage(), this.infosNode.getPos().x, this.infosNode.getPos().y);
        g.drawImage(this.arrowHide.getImage(), this.arrowHide.getPos().x, this.arrowHide.getPos().y);

        if (this.nodeFocus != null) {
            if (this.nodeFocus.getXEnd() < this.nodeFocus.getImage().getWidth()) {
                g.drawImage(this.nodeFocus.getImage().getSubImage(0, 0, this.nodeFocus.getXEnd(), this.nodeFocus.getImage().getHeight()), (int) this.nodeFocus.getPos().getX(), (int) this.nodeFocus.getPos().getY());
            } else {
                g.drawImage(this.nodeFocus.getImage(), (int) this.nodeFocus.getPos().getX(), (int) this.nodeFocus.getPos().getY());
                if (this.nodeFocus.getXEnd() > this.nodeFocus.getImage().getWidth() + 20) {
                    g.drawImage(this.nodeFocus.getDescr().getSubImage(0, 0, this.nodeFocus.getXEnd() - (this.nodeFocus.getImage().getWidth() + 20), this.nodeFocus.getDescr().getHeight()), (int) this.nodeFocus.getPos().getX() + this.nodeFocus.getImage().getWidth() + 20, (int) this.nodeFocus.getPos().getY());
                } else if (this.nodeFocus.getXEnd() > this.nodeFocus.getImage().getWidth() + 20 + this.nodeFocus.getDescr().getWidth()) {
                    g.drawImage(this.nodeFocus.getDescr(), (int) this.nodeFocus.getPos().getX() + this.nodeFocus.getImage().getWidth() + 20, (int) this.nodeFocus.getPos().getY());
                }
            }

        }

        if (this.nodeActive != null) {
            g.drawImage(this.nodeActive.getImage(), (int) this.nodeActive.getPos().getX(), (int) this.nodeActive.getPos().getY());
            g.drawImage(this.nodeActive.getDescr(), (int) this.nodeActive.getPos().getX() + this.nodeActive.getImage().getWidth() + 20, (int) this.nodeActive.getPos().getY());
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (this.infosNodeVisible) {
            if (this.nodeActive == null) {
                this.showInfosNode();
            } else {
                if (this.nodeFocus != null) {
                    this.transitionInfosNode();
                }
            }
        } else {
            this.hideInfosNode();
        }
    }

    public void setInfosNode(NodeView node, TrueTypeFont font) {
        this.infosNodeVisible = true;
        try {
            if (this.nodeActive == null) {
                this.nodeFocus = new NodeIllustration(new Image(getClass().getResource("../"+node.getPathPortrait()).getPath()), new Point(this.infosNode.getPos().x + 125, this.infosNode.getPos().y + 185), TextToImg.convertTextToImg(node.getDescr(), font), node.getLinkToNode());
            } else {
                this.nodeFocus = new NodeIllustration(new Image(getClass().getResource("../"+node.getPathPortrait()).getPath()), new Point(this.infosNode.getPos().x + this.infosNode.getImage().getWidth(), this.infosNode.getPos().y + 185), TextToImg.convertTextToImg(node.getDescr(), font), node.getLinkToNode());
                this.nodeFocus.setXEnd(0);
            }
        } catch (SlickException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void setToHide(int x, int y, float scaleX, float scaleY) {
        Rectangle scaleArea;

        scaleArea = new Rectangle((int) (this.arrowHide.getPos().x * scaleX), (int) (this.arrowHide.getPos().y * scaleY), (int) (this.arrowHide.getImage().getWidth() * scaleX), (int) (this.arrowHide.getImage().getHeight() * scaleY));
        if (scaleArea.contains(x, y)) {
            this.infosNodeVisible = false;
        }
    }

    private void transitionInfosNode() {
        if (this.nodeActive.getXStart() < this.nodeActive.getImage().getWidth()) {
            this.nodeActive.setXStart(this.nodeActive.getXStart() + 1);
            this.nodeActive.setImage(this.nodeActive.getImage().getSubImage(this.nodeActive.getXStart(), 0, this.nodeActive.getImage().getWidth() - this.nodeActive.getXStart(), this.nodeActive.getImage().getHeight()));
        } else {
            this.nodeActive.setImage(this.nodeActive.getImage().getSubImage(0, 0, 0, 0));

            if (this.nodeActive.getPos().x <= (this.infosNode.getPos().x + 125)) {
                this.nodeActive.setXStart(this.nodeActive.getXStart() + 1);
                if (this.nodeActive.getXStart() < this.nodeActive.getDescr().getWidth()) {
                    this.nodeActive.setDescr(this.nodeActive.getDescr().getSubImage(this.nodeActive.getXStart(), 0, this.nodeActive.getDescr().getWidth() - this.nodeActive.getXStart(), this.nodeActive.getDescr().getHeight()));
                } else {
                    this.nodeActive.setDescr(this.nodeActive.getDescr().getSubImage(0, 0, 0, 0));
                }
            } else {
                if (this.nodeActive.getXStart() != 0) {
                    this.nodeActive.setXStart(0);
                }
                this.nodeActive.setPos(new Point(this.nodeActive.getPos().x - 5, this.infosNode.getPos().y + 30));
            }
        }

        if (this.nodeFocus.getXEnd() < this.nodeFocus.getImage().getWidth() + 20 + this.nodeFocus.getDescr().getWidth() + 5) {
            this.nodeFocus.setXEnd(this.nodeFocus.getXEnd() + 10);
            this.nodeFocus.setPos(new Point(this.nodeFocus.getPos().x - 10, this.nodeFocus.getPos().y));
        } else {
            if (this.nodeFocus.getPos().x > this.nodeActive.getPos().x + 30) {
                this.nodeFocus.setPos(new Point(this.nodeFocus.getPos().x - 10, this.nodeFocus.getPos().y));
            } else {
                this.nodeFocus.setPos(new Point(this.infosNode.getPos().x + 125, this.infosNode.getPos().y + 185));
                this.nodeActive = this.nodeFocus;
                this.nodeFocus = null;
            }
        }
    }

    private void showInfosNode() {
        if (this.infosNode.getPos().y < -150) {
            this.infosNode.setPos(new Point(this.infosNode.getPos().x, this.infosNode.getPos().y + 5));
            this.arrowHide.setPos(new Point(this.arrowHide.getPos().x, this.arrowHide.getPos().y + 5));
            this.nodeFocus.setPos(new Point(this.nodeFocus.getPos().x, this.nodeFocus.getPos().y + 5));
        } else {
            if (this.nodeFocus != null) {
                this.nodeActive = this.nodeFocus;
                this.nodeFocus = null;
            }
        }
    }

    private void hideInfosNode() {
        if (this.infosNode.getPos().y > -400) {
            this.infosNode.setPos(new Point(this.infosNode.getPos().x, this.infosNode.getPos().y - 5));
            this.arrowHide.setPos(new Point(this.arrowHide.getPos().x, this.arrowHide.getPos().y - 5));
            if (this.nodeFocus == null) {
                this.nodeFocus = this.nodeActive;
                this.nodeActive = null;
            }
            this.nodeFocus.setPos(new Point(this.nodeFocus.getPos().x, this.nodeFocus.getPos().y - 5));
        } else {
            this.nodeFocus = null;
        }
    }

    public NodeIllustration launchContamination() {
        if (this.infosNodeVisible && this.nodeFocus == null) {
            return this.nodeActive;
        } else {
            return null;
        }
    }
}
