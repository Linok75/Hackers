/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Game;
import model.ressources.attacks.Attack;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Ara
 */
public class Level extends BasicGameState {

    private int stateID;
    private Game gameInstance;
    private ArrayList<NodeView> nodeViewList;
    private HashMap<Attack, Color> assocColorAtk;
    private Dimension gridDimension;
    private TrueTypeFont font;
    private Color fontColor = new Color(19, 180, 7);
    private Illustration background;
    private float scaleX;
    private float scaleY;
    private Illustration grid;
    private Illustration infosNode;
    private Illustration arrowHide;
    private Illustration defaultNode;
    private NodeView nodeFocus;
    private boolean infosNodeVisible;

    public Level(int stateID, Game gameInstance) {
        this.stateID = stateID;
        this.gameInstance = gameInstance;
        this.gridDimension = this.gameInstance.getLevel().getMap().getDimensionMap();
        this.nodeViewList = new ArrayList<>();
        this.assocColorAtk = new HashMap<>();
        this.nodeFocus = null;
        this.infosNodeVisible = false;

        this.setAssocColorDef();
        this.setNodeViewList();
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        try {
            Font fontBase = new Font("Agency FB", java.awt.Font.PLAIN, 30);
            InputStream tmpFont = ResourceLoader.getResourceAsStream(getClass().getResource("ressources/AgencyFB.ttf").getPath());
            Font.createFont(Font.TRUETYPE_FONT, tmpFont);
            this.font = new TrueTypeFont(fontBase, false);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        this.background = new Illustration(new Image(getClass().getResource("ressources/background.png").getPath()), new Point(0, 0));
        this.grid = new Illustration(new Image(getClass().getResource("ressources/tabHexa.png").getPath()), new Point(65, 282));
        this.infosNode = new Illustration(new Image(getClass().getResource("ressources/infosNode.png").getPath()), new Point(0, -350));
        this.arrowHide = new Illustration(new Image(getClass().getResource("ressources/arrowHide.png").getPath()).getScaledCopy((float) 0.08), new Point(1095, -180));
        this.defaultNode = new Illustration(new Image(getClass().getResource("ressources/nodeDefault.png").getPath()), null);

        this.scaleX = (float) container.getWidth() / this.background.getImage().getWidth();
        this.scaleY = (float) container.getHeight() / this.background.getImage().getHeight();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
//        container.sleep(10);
        g.scale(this.scaleX, this.scaleY);
        g.setColor(this.fontColor);
        g.setFont(this.font);

        g.drawImage(this.background.getImage(), this.background.getPos().x, this.background.getPos().y);

        g.drawImage(this.infosNode.getImage(), this.infosNode.getPos().x, this.infosNode.getPos().y);
        g.drawImage(this.arrowHide.getImage(), this.arrowHide.getPos().x, this.arrowHide.getPos().y);

        for (NodeView node : this.nodeViewList) {
            g.drawImage(this.defaultNode.getImage(), (int) node.getPos().getX(), (int) node.getPos().getY(), node.getColor());
        }
        

        g.drawImage(this.grid.getImage(), this.grid.getPos().x, this.grid.getPos().y);
        
        if(this.nodeFocus!=null){
            g.drawImage(this.defaultNode.getImage(), (int) this.nodeFocus.getPos().getX(), (int) this.nodeFocus.getPos().getY(), this.nodeFocus.getColor());
        }

//        if (this.nodeFaceOld != null) {
//            g.drawImage(this.nodeFaceOld.getImage(), this.nodeFaceOld.getPos().x, this.nodeFaceOld.getPos().y);
//            g.drawString(this.nodeDescribeOld, this.nodeDescribeOldPos.x, this.nodeDescribeOldPos.y);
//        }
//        if (this.nodeFaceNew != null) {
//            g.drawImage(this.nodeFaceNew.getImage(), this.nodeFaceNew.getPos().x, this.nodeFaceNew.getPos().y);
//            g.drawString(this.nodeDescribeNew, this.nodeDescribeNewPos.x, this.nodeDescribeNewPos.y);
//        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (this.infosNodeVisible && this.infosNode.getPos().y < 0) {
            this.infosNode.setPos(new Point(this.infosNode.getPos().x, this.infosNode.getPos().y + 5));
            this.arrowHide.setPos(new Point(this.arrowHide.getPos().x, this.arrowHide.getPos().y + 5));
            this.nodeFocus.setPos(new Point(this.nodeFocus.getPos().x, this.nodeFocus.getPos().y + 5));
//            this.nodeFaceOld.setPos(new Point(this.nodeFaceOld.getPos().x, this.nodeFaceOld.getPos().y + 5));
//            this.nodeDescribeOldPos.setLocation(this.nodeDescribeOldPos.x, this.nodeDescribeOldPos.y + 5);
        }else if (!this.infosNodeVisible && this.infosNode.getPos().y > -350) {
                this.infosNode.setPos(new Point(this.infosNode.getPos().x, this.infosNode.getPos().y - 5));
                this.arrowHide.setPos(new Point(this.arrowHide.getPos().x, this.arrowHide.getPos().y - 5));
                this.nodeFocus.setPos(new Point(this.nodeFocus.getPos().x, this.nodeFocus.getPos().y - 5));
//                this.nodeFaceOld.setPos(new Point(this.nodeFaceOld.getPos().x, this.nodeFaceOld.getPos().y - 5));
//                this.nodeDescribeOldPos.setLocation(this.nodeDescribeOldPos.x, this.nodeDescribeOldPos.y - 5);
            }
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        Rectangle scaleArea;

        for (NodeView node : this.nodeViewList) {
            scaleArea = new Rectangle((int) (node.getPos().x * this.scaleX), (int) (node.getPos().y * this.scaleY), (int) (node.getClickableArea().width * this.scaleX), (int) (node.getClickableArea().height * this.scaleY));
            if (scaleArea.contains(x,y)) {
//                if (this.infosVisible) {
//                    this.nodeFacePathNew = node.getPath();
//                    this.next = true;
//                } else {
//                    this.nodeFacePathOld = node.getPath();
//                    this.infosVisible = true;
//                }
                this.infosNodeVisible = true;
                this.nodeFocus = new NodeView(new Point(70,-320), node.getColor());
                break;
            }
        }
        scaleArea = new Rectangle((int) (this.arrowHide.getPos().x * this.scaleX), (int) (this.arrowHide.getPos().y * this.scaleY), (int) (this.arrowHide.getImage().getWidth() * this.scaleX), (int) (this.arrowHide.getImage().getHeight() * this.scaleY));
        if (scaleArea.contains(x, y)) {
            this.infosNodeVisible = false;
        }

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

    private void setNodeViewList() {
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
                maxCol = (int) this.gridDimension.getWidth() - 1;
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
                            tmp = new NodeView(new Point(x, y), assoc.getValue());
                        } else {
                            tmp.setColor(assoc.getValue());
                        }
                    }
                }
                if (tmp == null) {
                    tmp = new NodeView(new Point(x, y), this.assocColorAtk.get(null));
                }
                this.nodeViewList.add(tmp);
                tmp=null;
                x += 117;
            }
//            System.out.println("");
            y += 115;
        }
    }
}
