/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.levelsFrame.map;

import exceptions.NoSuffisantPA;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;
import model.Game;
import model.ressources.attacks.Attack;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import view.MasterFrame;
import view.animations.CorruptionAnimation;
import view.levelsFrame.Level;
import view.levelsFrame.infosNode.NodeIllustration;
import view.tools.Illustration;

/**
 *
 * @author Ara
 */
public class Map extends BasicGameState {
    private StateBasedGame parentState;
    private Illustration background;
    private Image hexagone;
    private Image node;
    private Dimension dim;
    private Dimension gridDim;
    private Game instance;
    private int stateID;
    private ArrayList<NodeView> nodeViewList;
    private HashMap<Attack, Color> assocColorAtk;
    private Point gridPos;
    // Contamination Animation
    private ArrayList<CorruptionAnimation> corruptionAnimations = new ArrayList<CorruptionAnimation>();
    //

    public Map(Game instance, int stateID, StateBasedGame game) throws SlickException {
        this.instance = instance;
        this.parentState=game;
        this.dim = this.instance.getLevel().getMap().getDimensionMap();
        this.stateID = stateID;
        this.nodeViewList = new ArrayList<NodeView>();
        this.assocColorAtk = new HashMap<Attack,Color>();
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.background = new Illustration(new Image(getClass().getResource("../../ressources/map.png").getPath()), new Point(-50, 180));
        this.hexagone = new Image(getClass().getResource("../../ressources/hexagone.png").getPath());
        this.node = new Image(getClass().getResource("../../ressources/nodeMap.png").getPath());


        this.setGrid();
        this.setAssocColorDef();
        this.setNodeViewList();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(this.background.getImage(), (int) this.background.getPos().getX(), (int) this.background.getPos().getY());

        for (NodeView nd : this.nodeViewList) {
            if (!nd.isCorrupt()) {
                g.drawImage(nd.getState(), (int) nd.getPos().getX(), (int) nd.getPos().getY(), nd.getColor());
            } else {
                g.drawImage(nd.getState(), (int) nd.getPos().getX(), (int) nd.getPos().getY());
                nd.refresh();
            }
            g.drawImage(this.hexagone, (int) nd.getPos().getX() - 5, (int) nd.getPos().getY() - 5);
        }

        for (CorruptionAnimation ca: corruptionAnimations) {
            ca.render(container, game, g);
        }

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        for (Iterator<CorruptionAnimation> it = corruptionAnimations.iterator(); it.hasNext();) {
            CorruptionAnimation ca = it.next();
            ca.update(container, game, delta);
            if (ca.isEnded()) {
                it.remove();
            }
        }
    }

    private void setNodeViewList() throws SlickException {
        boolean pair;
        int x, y, maxCol;
        NodeView tmp = null;

        pair = true;
        y = (int) this.gridPos.getY();

        for (int row = 0; row < (int) this.dim.getHeight(); row++) {
            if (pair) {
                x = (int) this.gridPos.getX();
                pair = false;
            } else {
                x = (int) (this.gridPos.getX() + this.hexagone.getWidth() / 2);
                pair = true;
            }
            for (int col = 0; col < (int) this.dim.getWidth(); col++) {
//                System.out.print(this.gameInstance.getLevel().getMap().getNode(row, col));
                for (Attack atk : this.instance.getPlayer().getAttackList()) {
                    if (!this.instance.getLevel().getMap().getNode(row, col).isHackable(atk)) {
                        if (tmp == null) {
                            tmp = new NodeView(new Point(x, y), this.assocColorAtk.get(atk), this.instance.getLevel().getMap().getNode(row, col).getPath(), this.instance.getLevel().getMap().getNode(row, col).getDescription(), new Point(row, col), this.node); 
                        } else {
                            tmp.setColor(this.assocColorAtk.get(atk));
                        }
                    } else{
                        break;
                    }
                }
                if (tmp == null) {
                    tmp = new NodeView(new Point(x, y), this.assocColorAtk.get(null), this.instance.getLevel().getMap().getNode(row, col).getPath(), this.instance.getLevel().getMap().getNode(row, col).getDescription(), new Point(row, col), this.node);
                }
                this.nodeViewList.add(tmp);
                tmp = null;
                x += this.node.getWidth() + 13;
            }
//            System.out.println("");
            y += this.node.getHeight() - 17;
        }
    }

    private void setAssocColorDef() {
        for (Attack atk : this.instance.getPlayer().getAttackList()) {
            switch (atk.getDefence()) {
                case Exploitation:
                    this.assocColorAtk.put(atk, new Color(192, 38, 38)); //rouge
                    //this.assocColorAtk.put(atk, new Color(23, 194, 9)); //vert fluo
                    break;

                case Trojan:
                    this.assocColorAtk.put(atk, new Color(248, 99, 0)); //orange-rouge
                    //this.assocColorAtk.put(atk, new Color(39, 120, 54)); //vert
                    break;

                case Virus:
                    this.assocColorAtk.put(atk, new Color(237, 140, 13)); //orange
                    break;

                case Effraction:
                    this.assocColorAtk.put(atk, new Color(236, 203, 160)); //pÃªche
                    break;

                case BruteForcing:
                    this.assocColorAtk.put(atk, new Color(240, 248, 0)); //jaune
                    //this.assocColorAtk.put(atk, new Color(193, 126, 39)); //marron-orange
                    break;

                case Phishing:
                    this.assocColorAtk.put(atk, new Color(190, 256, 0)); //vert-jaune
                    //this.assocColorAtk.put(atk, new Color(108, 46, 46)); //marron
                    break;

                default:
                    //this.assocColorAtk.put(null, new Color(192, 38, 38)); //rouge
                    //this.assocColorAtk.put(null, new Color(84, 177, 13)); //vert
                    this.assocColorAtk.put(null, new Color(166, 238, 111)); //vert

            }
        }
    }

    private void setGrid() {
        double scale;

        this.gridDim = new Dimension((int) (this.hexagone.getWidth() * this.dim.getWidth()), (int) (this.hexagone.getHeight() * this.dim.getHeight()));
        scale = this.gridDim.getWidth() / this.background.getImage().getWidth();
        this.hexagone = this.hexagone.getScaledCopy((float) scale);
        this.node = this.node.getScaledCopy((float) scale);
        this.gridDim.setSize(this.gridDim.getWidth() * scale, this.gridDim.getHeight() * scale);
        this.gridPos = new Point((int) (this.background.getPos().getX() + ((this.background.getImage().getWidth() - 50) - this.gridDim.getWidth()) / 2), (int) (this.background.getPos().getY() + ((this.background.getImage().getHeight() + 200) - this.gridDim.getHeight()) / 2));
    }

    public NodeView nodeClicked(int x, int y, float scaleX, float scaleY) {
        Rectangle scaleArea;

        for (NodeView nd : this.nodeViewList) {
            scaleArea = new Rectangle(nd.getPos().x * scaleX, nd.getPos().y * scaleY, nd.getClickableArea().width * scaleX, nd.getClickableArea().height * scaleY);
            if (scaleArea.contains(x, y)) {
                return nd;
            }
        }
        return null;
    }

    public void contamination(NodeIllustration nodeActive, String atk) throws SlickException {
        //if (animation != null) return;

        try {
            this.instance.getPlayer().attack(atk, this.instance.getLevel().getMap().getNode(nodeActive.getLinkToNode().x, nodeActive.getLinkToNode().y));
            this.corruptionAnimations.add(new CorruptionAnimation(this.instance, this, this.instance.getPlayer().getAttack(atk)));
            /*
             * for (NodeView nd : this.nodeViewList) { if
             * (this.instance.getLevel().getMap().getNode(nd.getLinkToNode().x,
             * nd.getLinkToNode().y).isHack()) { nd.corrupt(atk); }
             }
             */
        } catch (NoSuffisantPA ex) {
            this.parentState.enterState(MasterFrame.GAMEOVERSTATE);
        }
    }

    public ArrayList<NodeView> getNodeViewList() {
        return nodeViewList;
    }

    public int getNbCorrupt() {
        int nbCorrupt;

        nbCorrupt = 0;
        for (NodeView nd : this.nodeViewList) {
            if (this.instance.getLevel().getMap().getNode(nd.getLinkToNode().x, nd.getLinkToNode().y).isHack()) {
                nbCorrupt++;
            }
        }
        return nbCorrupt;
    }
}
