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
import model.Game;
import model.ressources.attacks.Attack;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import view.animations.CorruptionAnimation;
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
    private float globalScaleX;
    // Contamination Animation
    private ArrayList<CorruptionAnimation> corruptionAnimations = new ArrayList<CorruptionAnimation>();
    //

    public Map(Game instance, int stateID, StateBasedGame game, float globalScaleX) throws SlickException {
        this.instance = instance;
        this.parentState = game;
        this.dim = this.instance.getLevel().getMap().getDimensionMap();
        this.stateID = stateID;
        this.nodeViewList = new ArrayList<NodeView>();
        this.assocColorAtk = new HashMap<Attack, Color>();
        this.globalScaleX = globalScaleX;
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.background = new Illustration(new Image(getClass().getResource("../../ressources/map.png").getPath()), new Point(72, 271));
        this.hexagone = new Image(getClass().getResource("../../ressources/hexagone.png").getPath());
        this.node = new Image(getClass().getResource("../../ressources/nodeMap.png").getPath());


        this.setGrid();
        this.setAssocColorDef();
        this.setNodeViewList();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(this.background.getImage(), (int) this.background.getPos().getX(), (int)this.background.getPos().getY());

        for (NodeView nd : this.nodeViewList) {
            if (!nd.isCorrupt()) {
                g.drawImage(nd.getState(), (int) nd.getPos().getX(), (int) nd.getPos().getY(), nd.getColor());
            } else {
                g.drawImage(nd.getState(), (int) nd.getPos().getX(), (int) nd.getPos().getY());
                nd.refresh();
            }
            g.drawImage(this.hexagone, (int) nd.getPos().getX() - 5, (int) nd.getPos().getY() - 5);
        }

        for (CorruptionAnimation ca : corruptionAnimations) {
            ca.render(container, game, g);
        }

        /*g.drawLine((int)this.background.getPos().getX(),
                (int)this.background.getPos().getY(),
                (int) this.background.getPos().getX() + this.background.getImage().getWidth(),
                (int) this.background.getPos().getY() + this.background.getImage().getHeight());*/
        /*g.drawLine((int)this.background.getPos().getX(),
                (int)this.background.getPos().getY(),
                (int) this.background.getPos().getX() + this.gridDim.width,
                (int) this.background.getPos().getY() + this.gridDim.height);*/

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

        for (int row = 0; row < this.dim.height; row++) {
            if (pair) {
                x = (int) this.gridPos.getX();
                pair = false;
            } else {
                x = (int) (this.gridPos.getX() + this.hexagone.getWidth() / 2);
                pair = true;
            }
            for (int col = 0; col < this.dim.width; col++) {
                //System.out.println(this.dim.width + " : " + this.dim.height);
                //System.out.println(col + " : " + row + "\n");
//                System.out.print(this.gameInstance.getLevel().getMap().getNode(row, col));
                for (Attack atk : this.instance.getPlayer().getAttackList()) {
                    if (!"ddos".equalsIgnoreCase(atk.getTitle())) {
                        if (!this.instance.getLevel().getMap().getNode(row, col).isHackable(atk)) {
                            if (tmp == null) {
                                tmp = new NodeView(new Point(x, y),
                                        this.assocColorAtk.get(atk),
                                        this.instance.getLevel().getMap().getNode(row, col).getPath(),
                                        this.instance.getLevel().getMap().getNode(row, col).getDescription(),
                                        new Point(row, col),
                                        this.node);
                            } else {
                                tmp.setColor(this.assocColorAtk.get(atk));
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (tmp == null) {
                    tmp = new NodeView(new Point(x, y), this.assocColorAtk.get(null), this.instance.getLevel().getMap().getNode(row, col).getPath(), this.instance.getLevel().getMap().getNode(row, col).getDescription(), new Point(row, col), this.node);
                }
                this.nodeViewList.add(tmp);
                tmp = null;
                x += (int)((float)this.node.getWidth()*1.15f);
            }
//            System.out.println("");
            y += (int)((float)this.node.getHeight()*0.875f);
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

        /*
        //Dimension du cadre
        Vector2f dimCadrePX = new Vector2f(this.background.getImage().getWidth(), this.background.getImage().getHeight());
        System.out.println("Dimension du cadre : "+dimCadrePX);
        //Dimension de la map (nb hexa)
        Vector2f dimMapHX = new Vector2f(this.dim.width + 0.5f, this.dim.height);
        System.out.println("Dimension de la map (nbHexa) : "+dimMapHX);
        //Dimension d'un hexagone
        Vector2f dimHexaPX = new Vector2f(this.hexagone.getWidth(), this.hexagone.getHeight());
        System.out.println("Dimension d'un hexagone : "+dimHexaPX);
        //Dimension de la map (pixel)
        Vector2f dimMapPX = new Vector2f(dimHexaPX.x * dimMapHX.x, dimHexaPX.y * dimMapHX.y);
        System.out.println("Dimension de la map (pixel) : "+dimMapPX);

        float scale;
        if (dimMapPX.x > dimMapPX.y) {
            scale = dimCadrePX.x / dimMapPX.x;
        } else {
            scale = dimCadrePX.y / dimMapPX.y;
        }

        Vector2f newDimMapPx = new Vector2f(dimMapPX.x * scale, dimMapPX.y * scale);
        System.out.println("Nouvelle dimension de la map (pixel) : "+newDimMapPx);

        while (newDimMapPx.x > dimCadrePX.x || newDimMapPx.y > dimCadrePX.y) {
            if (newDimMapPx.x > dimCadrePX.x) {
                newDimMapPx.x = newDimMapPx.x*0.99f;
            } else if (newDimMapPx.y > dimCadrePX.y) {
                newDimMapPx.y = newDimMapPx.y*0.99f;
            }
        }

        System.out.println("Nouvelle dimension de la map (pixel) : "+newDimMapPx);

        this.gridDim = new Dimension((int)newDimMapPx.x, (int)newDimMapPx.y);

        float widthForHexa = newDimMapPx.x/dimMapHX.x;
        float scaleForHexa = widthForHexa/dimHexaPX.x;

        float heightForHexa = newDimMapPx.y/dimMapHX.y;
        float scaleForHexa2 = heightForHexa/dimHexaPX.y;

        System.out.println(scaleForHexa);
        System.out.println(scaleForHexa2);

        float min = Math.min(scaleForHexa, scaleForHexa2);

        this.hexagone = this.hexagone.getScaledCopy(min);
        this.node = this.node.getScaledCopy(min);

        System.out.println("WIDTH : "+this.hexagone.getWidth()*dimMapHX.x);
        System.out.println("HEIGHT : "+this.hexagone.getHeight()*dimMapHX.y);

        int posX = this.background.getPos().x;// + ((this.background.getImage().getWidth() - this.gridDim.width)/2);
        System.out.println("posX : "+posX);
        int posY = this.background.getPos().y;// + ((this.background.getImage().getHeight() - this.gridDim.height));
        System.out.println("posY : "+posY);
        this.gridPos = new Point(posX, posY);
        *
        */

        float precision = 0.0001f;

        scale = 1;
        while (this.hexagone.getScaledCopy(scale).getWidth()*(this.dim.width + 0.5) > this.background.getImage().getWidth()) {
            scale -= precision;
        }
        while (this.hexagone.getScaledCopy(scale).getHeight()*this.dim.height > this.background.getImage().getHeight()) {
            scale -= precision;
        }

        this.hexagone = this.hexagone.getScaledCopy(scale);
        this.node = this.node.getScaledCopy(scale);

        int widthDIV2 = (int)((this.background.getImage().getWidth() - this.hexagone.getWidth()*(this.dim.width + 0.5))/2);
        //int heightDIV2 = (int)((this.background.getImage().getHeight() - this.hexagone.getHeight()*this.dim.height)/2);

        this.gridPos = new Point(this.background.getPos().x + widthDIV2 - this.hexagone.getWidth()/10, this.background.getPos().y + 100);

    }

    private float scale;

    public NodeView nodeClicked(int x, int y, float scaleX, float scaleY) {
        Rectangle scaleArea;

        for (NodeView nd : this.nodeViewList) {
            scaleArea = new Rectangle(nd.getPos().x * scaleX, nd.getPos().y * scaleY, (nd.getClickableArea().width * scaleX) * scale, (nd.getClickableArea().height * scaleY) * scale);
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
             * nd.getLinkToNode().y).isHack()) { nd.corrupt(atk); } }
             */
        } catch (NoSuffisantPA ex) {
            //this.parentState.enterState(MasterFrame.GAMEOVERSTATE);
        }
    }

    public ArrayList<NodeView> getNodeViewList() {
        return nodeViewList;
    }

    public boolean canIDoDDoS() {
        return this.corruptionAnimations.isEmpty();
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
