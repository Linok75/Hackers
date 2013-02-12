/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Point;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.InputStream;
import java.util.ArrayList;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author ara
 */
public class MasterFrame extends BasicGame {

    private static TrueTypeFont FONT;
    private static Color FONTCOLOR = new Color(19, 180, 7);
    private Illustration background;
    private Illustration grid;
    private Illustration infosNode;
    private Image tmp;
    private String path;
    private ArrayList<Node> nodeList;
    private ArrayList<Image> nodeImageList;
    private boolean infosVisible;
    private float scaleX;
    private float scaleY;
    private Illustration arrowHide;
    private Illustration nodeFaceOld;
    private String nodeFacePathOld;
    private String nodeDescribeOld;
    private Illustration nodeFaceNew;
    private String nodeFacePathNew;
    private String nodeDescribeNew;
    private String tmpDescribe;
    private boolean next;
    private Point nodeDescribeOldPos;
    private Point nodeDescribeNewPos;

    public MasterFrame() throws SlickException {
        super("Hackers");
        this.nodeList = new ArrayList();
        this.nodeImageList = new ArrayList();
        this.infosVisible = false;
        this.nodeFaceOld = null;
        this.tmpDescribe = "En attente d'une description réelle...";
        this.nodeDescribeOld = "En attente d'une description réelle...";
        this.nodeFacePathOld = "";

        this.next = false;
        this.nodeFaceNew = null;
        this.nodeDescribeNew = "";
        this.nodeFacePathNew = "";

        this.makeNodeList();
    }

    private void makeNodeList() {
        boolean tall = true, altX = true, altY = true;
        int maxRow = 7;
        int maxCol = 9;
        int x, y;

        x = 70;
        y = 288;

        for (int row = 0; row < maxRow; row++) {
            if (tall) {
                maxCol = 9;
                x = 70;
                tall = false;
            } else {
                maxCol = 8;
                x = 130;
                tall = true;
            }
            for (int col = 0; col < maxCol; col++) {
                this.nodeList.add(new Node((int) (Math.random() * 6), new Point(x, y)));
                if (altX) {
                    x += 117;
                    altX = false;
                } else {
                    x += 116;
                    altX = true;
                }
            }
            if (altY) {
                y += 115;
                altY = false;
            } else {
                y += 114;
                altY = true;
            }

        }
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        try {
            Font fontBase = new Font("Agency FB", java.awt.Font.PLAIN, 30);
            InputStream tmpFont = ResourceLoader.getResourceAsStream(getClass().getResource("ressources/AgencyFB.ttf").getPath());
            fontBase.createFont(Font.TRUETYPE_FONT, tmpFont);
            this.FONT = new TrueTypeFont(fontBase, false);

        } catch (Exception e) {
            e.printStackTrace();
        }


        this.path = "ressources/background.png";
        this.tmp = new Image(getClass().getResource(this.path).getPath());
        this.background = new Illustration(this.tmp, new Point(0, 0));

        this.path = "ressources/tabHexa.png";
        this.tmp = new Image(getClass().getResource(this.path).getPath());
        this.grid = new Illustration(this.tmp, new Point(65, 282));

        this.path = "ressources/infosNode.png";
        this.tmp = new Image(getClass().getResource(this.path).getPath());
        this.infosNode = new Illustration(this.tmp, new Point(0, -350));

        this.path = "ressources/arrowHide.png";
        this.tmp = new Image(getClass().getResource(this.path).getPath());
        this.tmp = this.tmp.getScaledCopy((float) 0.08);
        this.arrowHide = new Illustration(this.tmp, new Point(1095, -180));

        for (int i = 0; i < this.nodeList.size(); i++) {
            this.nodeImageList.add(new Image(getClass().getResource(this.nodeList.get(i).getPath()).getPath()));
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        this.scaleX = (float) container.getWidth() / this.background.getImage().getWidth();
        this.scaleY = (float) container.getHeight() / this.background.getImage().getHeight();
        if (!this.next) {
            if (!this.nodeFacePathOld.equals("")) {
                this.nodeFaceOld = new Illustration(new Image(getClass().getResource(this.nodeFacePathOld).getPath()), new Point(70, -320));
                this.nodeFacePathOld = "";
                this.nodeDescribeOldPos = new Point(this.nodeFaceOld.getPos().x + 150, this.nodeFaceOld.getPos().y + 50);
            }

            if (this.infosVisible && this.infosNode.getPos().y < 0) {
                this.infosNode.setPos(new Point(this.infosNode.getPos().x, this.infosNode.getPos().y + 5));
                this.arrowHide.setPos(new Point(this.arrowHide.getPos().x, this.arrowHide.getPos().y + 5));
                this.nodeFaceOld.setPos(new Point(this.nodeFaceOld.getPos().x, this.nodeFaceOld.getPos().y + 5));
                this.nodeDescribeOldPos.setLocation(this.nodeDescribeOldPos.x, this.nodeDescribeOldPos.y + 5);
            } else if (!this.infosVisible && this.infosNode.getPos().y > -350) {
                this.infosNode.setPos(new Point(this.infosNode.getPos().x, this.infosNode.getPos().y - 5));
                this.arrowHide.setPos(new Point(this.arrowHide.getPos().x, this.arrowHide.getPos().y - 5));
                this.nodeFaceOld.setPos(new Point(this.nodeFaceOld.getPos().x, this.nodeFaceOld.getPos().y - 5));
                this.nodeDescribeOldPos.setLocation(this.nodeDescribeOldPos.x, this.nodeDescribeOldPos.y - 5);
            }
        } else {
            if (this.nodeDescribeNewPos == null) {
                this.nodeFaceNew = new Illustration(new Image(getClass().getResource(this.nodeFacePathNew).getPath()).getSubImage(0, 0, 0, 140), new Point(1000, 30));
                this.nodeDescribeNewPos = new Point(this.nodeFaceNew.getPos().x + 150, this.nodeFaceNew.getPos().y + 50);
            }

            if (this.nodeFaceNew.getPos().x > 70) {
                if (this.nodeFaceNew.getImage().getWidth() < 110) {
                    this.nodeFaceNew.setImage(new Image(getClass().getResource(this.nodeFacePathNew).getPath()).getSubImage(0, 0, this.nodeFaceNew.getImage().getWidth() + 5, 140));
                }
                if (this.nodeDescribeNewPos.x < 975) {
                    if (this.nodeDescribeNew.length() < this.tmpDescribe.length()) {
                        this.nodeDescribeNew = this.tmpDescribe.substring(0, this.nodeDescribeNew.length() + 1);
                    }
                }
                this.nodeFaceNew.setPos(new Point(this.nodeFaceNew.getPos().x - 5, this.nodeFaceNew.getPos().y));
                this.nodeDescribeNewPos.setLocation(this.nodeDescribeNewPos.x - 5, this.nodeDescribeNewPos.y);
            } else {
                this.nodeFaceOld = this.nodeFaceNew;
                this.nodeDescribeOld = this.nodeDescribeNew;
                this.nodeDescribeOldPos = this.nodeDescribeNewPos;
                this.next = false;
                this.nodeDescribeNew = "";
                this.nodeDescribeNewPos = null;
                this.nodeFaceNew = null;
            }

            this.nodeFaceOld.setImage(this.nodeFaceOld.getImage().getSubImage(5, 0, this.nodeFaceOld.getImage().getWidth() - 5, this.nodeFaceOld.getImage().getHeight()));
            this.nodeDescribeOldPos.setLocation(this.nodeDescribeOldPos.x, this.nodeDescribeOldPos.y - 5);
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        container.sleep(10);
        g.scale(this.scaleX, this.scaleY);
        g.setColor(this.FONTCOLOR);
        g.setFont(this.FONT);

        g.drawImage(this.background.getImage(), this.background.getPos().x, this.background.getPos().y);

        g.drawImage(this.infosNode.getImage(), this.infosNode.getPos().x, this.infosNode.getPos().y);
        g.drawImage(this.arrowHide.getImage(), this.arrowHide.getPos().x, this.arrowHide.getPos().y);

        for (int i = 0; i < this.nodeList.size(); i++) {
            g.drawImage(this.nodeImageList.get(i), (int) this.nodeList.get(i).getPos().getX(), (int) this.nodeList.get(i).getPos().getY());
        }

        g.drawImage(this.grid.getImage(), this.grid.getPos().x, this.grid.getPos().y);

        if (this.nodeFaceOld != null) {
            g.drawImage(this.nodeFaceOld.getImage(), this.nodeFaceOld.getPos().x, this.nodeFaceOld.getPos().y);
            g.drawString(this.nodeDescribeOld, this.nodeDescribeOldPos.x, this.nodeDescribeOldPos.y);
        }
        if (this.nodeFaceNew != null) {
            g.drawImage(this.nodeFaceNew.getImage(), this.nodeFaceNew.getPos().x, this.nodeFaceNew.getPos().y);
            g.drawString(this.nodeDescribeNew, this.nodeDescribeNewPos.x, this.nodeDescribeNewPos.y);
        }
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        Rectangle scaleArea;

        for (Node node : this.nodeList) {
            scaleArea = new Rectangle((int) (node.getPos().x * this.scaleX), (int) (node.getPos().y * this.scaleY), (int) (node.getClickableArea().width * this.scaleX), (int) (node.getClickableArea().height * this.scaleY));
            if (scaleArea.contains(new Point(x, y))) {
                if (this.infosVisible) {
                    this.nodeFacePathNew = node.getPath();
                    this.next = true;
                } else {
                    this.nodeFacePathOld = node.getPath();
                    this.infosVisible = true;
                }
                break;
            }
        }

        scaleArea = new Rectangle((int) (this.arrowHide.getPos().x * this.scaleX), (int) (this.arrowHide.getPos().y * this.scaleY), (int) (this.arrowHide.getImage().getWidth() * this.scaleX), (int) (this.arrowHide.getImage().getHeight() * this.scaleY));
        if (scaleArea.contains(x, y)) {
            this.infosVisible = false;
        }

    }
}
