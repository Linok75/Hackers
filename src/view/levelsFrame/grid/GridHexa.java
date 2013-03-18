/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.levelsFrame.grid;

import view.tools.Illustration;
import view.levelsFrame.Level;
import exceptions.NoSuffisantPA;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import model.Game;
import model.ressources.attacks.Attack;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import view.levelsFrame.infosNode.NodeIllustration;

/**
 *
 * @author Ara
 */
public class GridHexa extends BasicGameState {

    private int stateID;
    private Game gameInstance;
    private ArrayList<NodeView> nodeViewList;
    private HashMap<Attack, Color> assocColorAtk;
    private Dimension gridDimension;
    private Illustration grid;
    private Illustration defaultNode;

    public GridHexa(int stateID, Game gameInstance) throws SlickException {
        this.gameInstance = gameInstance;
        this.gridDimension = this.gameInstance.getLevel().getMap().getDimensionMap();
        this.nodeViewList = new ArrayList<NodeView>();
        this.assocColorAtk = new HashMap<Attack,Color>();

        this.setAssocColorDef();
        this.setNodeViewList();
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.grid = new Illustration(new Image(getClass().getResource("../../ressources/tabHexa.png").getPath()), new Point(65, 282));
        this.defaultNode = new Illustration(new Image(getClass().getResource("../../ressources/nodeDefault.png").getPath()), null);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        for (NodeView node : this.nodeViewList) {
            g.drawImage(this.defaultNode.getImage(), (int) node.getPos().getX(), (int) node.getPos().getY(), node.getColor());
        }
        g.drawImage(this.grid.getImage(), this.grid.getPos().x, this.grid.getPos().y);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    public NodeView nodeClicked(int x, int y, float scaleX, float scaleY) {
        Rectangle scaleArea;

        for (NodeView node : this.nodeViewList) {
            scaleArea = new Rectangle(node.getPos().x * scaleX, node.getPos().y * scaleY, node.getClickableArea().width * scaleX, node.getClickableArea().height * scaleY);
            if (scaleArea.contains(x, y)) {
                return node;
            }
        }
        return null;
    }

    private void setAssocColorDef() {
        for (Attack atk : this.gameInstance.getPlayer().getAttackList()) {
            switch (atk.getDefence()) {
                case Phishing:
                    this.assocColorAtk.put(atk, new Color(108, 46, 46)); //marron
                    break;

                case BruteForcing:
                    this.assocColorAtk.put(atk, new Color(193, 126, 39)); //marron-orange
                    break;

                case Effraction:
                    this.assocColorAtk.put(atk, new Color(237, 140, 13)); //orange
                    break;

                case Virus:
                    this.assocColorAtk.put(atk, new Color(236, 203, 160)); //pêche
                    break;

                case Trojan:
                    this.assocColorAtk.put(atk, new Color(39, 120, 54)); //vert
                    break;

                case Exploitation:
                    this.assocColorAtk.put(atk, new Color(23, 194, 9)); //vert fluo
                    break;

                default:
                    this.assocColorAtk.put(null, new Color(192, 38, 38)); //rouge


            }
        }
    }

    private void setNodeViewList() throws SlickException {
        boolean pair;
        int x, y, maxCol;
        NodeView tmp = null;

        pair = true;
        y = 288;

        for (int row = 0; row < (int) this.gridDimension.getHeight(); row++) {
            if (pair) {
                x = 70;
                maxCol = (int) this.gridDimension.getWidth();
                pair = false;
            } else {
                x = 130;
                maxCol = (int) this.gridDimension.getWidth() /*- 1*/;
                pair = true;
            }
            for (int col = 0; col < maxCol; col++) {
//                System.out.print(this.gameInstance.getLevel().getMap().getNode(row, col));
                for (Map.Entry<Attack, Color> assoc : this.assocColorAtk.entrySet()) {
                    if (assoc.getKey() != null) {
                        if (this.gameInstance.getLevel().getMap().getNode(row, col).isHackable(assoc.getKey())) {
                            break;
                        }
                        if (tmp == null) {
                            tmp = new NodeView(new Point(x, y), assoc.getValue(), this.gameInstance.getLevel().getMap().getNode(row, col).getPath(), this.gameInstance.getLevel().getMap().getNode(row, col).getDescription(), new Point(row, col));
                        } else {
                            tmp.setColor(assoc.getValue());
                        }
                    }
                }
                if (tmp == null) {
                    tmp = new NodeView(new Point(x, y), this.assocColorAtk.get(null), this.gameInstance.getLevel().getMap().getNode(row, col).getPath(), this.gameInstance.getLevel().getMap().getNode(row, col).getDescription(), new Point(row, col));
                }
                this.nodeViewList.add(tmp);
                tmp = null;
                x += 117;
            }
//            System.out.println("");
            y += 115;
        }
    }

    public void contamination(NodeIllustration nodeActive, String atk) {
        try {
            this.gameInstance.getPlayer().attack(atk, this.gameInstance.getLevel().getMap().getNode(nodeActive.getLinkToNode().x, nodeActive.getLinkToNode().y));
            for (NodeView node : this.nodeViewList) {
                if (this.gameInstance.getLevel().getMap().getNode(node.getLinkToNode().x, node.getLinkToNode().y).isHack()) {
                    node.corrupt();
                }
            }
        } catch (NoSuffisantPA ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public int getNbCorrupt() {
        int nbCorrupt;

        nbCorrupt = 0;
        for (NodeView node : this.nodeViewList) {
            if (this.gameInstance.getLevel().getMap().getNode(node.getLinkToNode().x, node.getLinkToNode().y).isHack()) {
                nbCorrupt++;
            }
        }
        return nbCorrupt;
    }
}
