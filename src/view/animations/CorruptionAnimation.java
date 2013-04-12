<<<<<<< HEAD
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.animations;

import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Game;
import model.maps.MapHexa;
import model.maps.Node;
import model.ressources.attacks.Attack;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import view.levelsFrame.map.Map;
import view.levelsFrame.map.NodeView;

/**
 *
 * @author MyMac
 */
public class CorruptionAnimation {

    private Game game;
    private Map map;
    private Attack atk;
    //private ArrayList<ArrayList<Node>> nodes;
    private ArrayList<ArrayList<NodeView>> nodeViews;
    private Point source;
    //
    private ArrayList<SegmentAnimation> sas;
    //
    private static final int TIME = 100;
    private int currentTime = 0;
    //
    private int state = 0;

    public CorruptionAnimation(Game game, Map map, Attack atk) {
        this.game = game;
        this.map = map;
        ArrayList<ArrayList<Node>> nodes = atk.getNodesHack();
        this.nodeViews = new ArrayList<ArrayList<NodeView>>();
        this.source = getNodeViewOf(nodes.get(0).get(0)).getCenter();
        this.atk = atk;

        try {
            getNodeViewOf(nodes.get(0).get(0)).corrupt(atk.getTitle());
        } catch (SlickException ex) {
            Logger.getLogger(CorruptionAnimation.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.sas = new ArrayList<SegmentAnimation>();
        //

        for (ArrayList<Node> arrayList : nodes) {
            ArrayList<NodeView> array = new ArrayList<NodeView>();
            for (Node node : arrayList) {
                NodeView nv = this.getNodeViewOf(node);
                array.add(nv);
            }
            this.nodeViews.add(array);
        }

        state++;

        if (this.nodeViews.size() >= 2) {
            ArrayList<NodeView> array = this.nodeViews.get(state);
            for (NodeView nodeView : array) {
                this.sas.add(new SegmentAnimation(source, nodeView.getCenter()));
            }
            //this.nodeViews.remove(0);
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        Color c = grphcs.getColor();
        grphcs.setColor(Color.red);
        boolean continu = true;
        for (SegmentAnimation sa : sas) {
            sa.draw(grphcs);
            if (!sa.continu()) {
                continu = false;
            }
        }
        if (continu) {
            nextState();
        }
        grphcs.setColor(c);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        for (SegmentAnimation sa : sas) {
            sa.update();
        }
    }

    private void nextState() {
        if (state < this.nodeViews.size()) {
            for (NodeView nodeView : this.nodeViews.get(state)) {
                try {
                    nodeView.corrupt(atk.getTitle());
                } catch (SlickException ex) {
                    Logger.getLogger(CorruptionAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            state++;
            //this.sas.clear();
            if (state < this.nodeViews.size()) {
                for (NodeView nodeViewTarget : this.nodeViews.get(state)) {
                    addNearNode(nodeViewTarget);
                    /*for (NodeView nodeViewSource : this.nodeViews.get(state - 1)) {
                        //if (isNearTo(nodeViewSource, nodeViewTarget))
                        this.sas.add(new SegmentAnimation(nodeViewSource.getCenter(), nodeViewTarget.getCenter()));
                    }*/
                }
            }
        } else {
            currentTime++;
        }
    }

    private void addNearNode(NodeView nvT) {
        double distance = Double.MAX_VALUE;
        ArrayList<NodeView> moreNear = new ArrayList<NodeView>();

        for (NodeView nodeViewSource : this.nodeViews.get(state - 1)) {
            if (distance(nodeViewSource, nvT) < distance) {
                distance = distance(nodeViewSource, nvT);
                moreNear.clear();
                moreNear.add(nodeViewSource);
            } else if (distance(nodeViewSource, nvT) == distance) {
                moreNear.add(nodeViewSource);
            }
        }

        for (NodeView nodeViewSource : moreNear) {
            this.sas.add(new SegmentAnimation(nodeViewSource.getCenter(), nvT.getCenter()));
        }

    }

    private double distance(NodeView nvS, NodeView nvT) {
        Node source = Game.getInstance().getLevel().getMap().getNode(nvS.getLinkToNode().x, nvS.getLinkToNode().y);
        Node target = Game.getInstance().getLevel().getMap().getNode(nvT.getLinkToNode().x, nvT.getLinkToNode().y);

        Point src = Game.getInstance().getLevel().getMap().getPoint(source);
        Point trg = Game.getInstance().getLevel().getMap().getPoint(target);

        return src.distance(trg);
    }

    /*
    private boolean isNearTo(NodeView nvS, NodeView nvT) {
        Node source = Game.getInstance().getLevel().getMap().getNode(nvS.getLinkToNode().x, nvS.getLinkToNode().y);
        Node target = Game.getInstance().getLevel().getMap().getNode(nvT.getLinkToNode().x, nvT.getLinkToNode().y);

        Point src = Game.getInstance().getLevel().getMap().getPoint(source);
        Point trg = Game.getInstance().getLevel().getMap().getPoint(target);

        return Game.getInstance().getLevel().getMap().isNearTo(source, target);
    }*/

    public boolean isEnded() {
        return currentTime == TIME;
    }

    private NodeView getNodeViewOf(Node n) {
        Point p = this.game.getLevel().getMap().getPoint(n);
        for (NodeView nv : this.map.getNodeViewList()) {
            if (nv.getLinkToNode().equals(p)) {
                return nv;
            }
        }
        return null;
    }
}
=======
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.animations;

import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Game;
import model.maps.MapHexa;
import model.maps.Node;
import model.ressources.attacks.Attack;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import view.levelsFrame.map.Map;
import view.levelsFrame.map.NodeView;

/**
 *
 * @author MyMac
 */
public class CorruptionAnimation {

    private Game game;
    private Map map;
    private Attack atk;
    //private ArrayList<ArrayList<Node>> nodes;
    private ArrayList<ArrayList<NodeView>> nodeViews;
    private Point source;
    //
    private ArrayList<SegmentAnimation> sas;
    //
    private static final int TIME = 100;
    private int currentTime = 0;
    //
    private int state = 0;

    public CorruptionAnimation(Game game, Map map, Attack atk) {
        this.game = game;
        this.map = map;
        ArrayList<ArrayList<Node>> nodes = atk.getNodesHack();
        this.nodeViews = new ArrayList<ArrayList<NodeView>>();
        this.source = getNodeViewOf(nodes.get(0).get(0)).getCenter();
        this.atk = atk;

        try {
            getNodeViewOf(nodes.get(0).get(0)).corrupt(atk.getTitle());
        } catch (SlickException ex) {
            Logger.getLogger(CorruptionAnimation.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.sas = new ArrayList<SegmentAnimation>();
        //

        for (ArrayList<Node> arrayList : nodes) {
            ArrayList<NodeView> array = new ArrayList<NodeView>();
            for (Node node : arrayList) {
                NodeView nv = this.getNodeViewOf(node);
                array.add(nv);
            }
            this.nodeViews.add(array);
        }

        state++;

        if (this.nodeViews.size() >= 2) {
            ArrayList<NodeView> array = this.nodeViews.get(state);
            for (NodeView nodeView : array) {
                this.sas.add(new SegmentAnimation(source, nodeView.getCenter()));
            }
            //this.nodeViews.remove(0);
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        Color c = grphcs.getColor();
        grphcs.setColor(Color.red);
        boolean continu = true;
        for (SegmentAnimation sa : sas) {
            sa.draw(grphcs);
            if (!sa.continu()) {
                continu = false;
            }
        }
        if (continu) {
            nextState();
        }
        grphcs.setColor(c);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        for (SegmentAnimation sa : sas) {
            sa.update();
        }
    }

    private void nextState() {
        if (state < this.nodeViews.size()) {
            for (NodeView nodeView : this.nodeViews.get(state)) {
                try {
                    nodeView.corrupt(atk.getTitle());
                } catch (SlickException ex) {
                    Logger.getLogger(CorruptionAnimation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            state++;
            //this.sas.clear();
            if (state < this.nodeViews.size()) {
                for (NodeView nodeViewTarget : this.nodeViews.get(state)) {
                    addNearNode(nodeViewTarget);
                    /*for (NodeView nodeViewSource : this.nodeViews.get(state - 1)) {
                        //if (isNearTo(nodeViewSource, nodeViewTarget))
                        this.sas.add(new SegmentAnimation(nodeViewSource.getCenter(), nodeViewTarget.getCenter()));
                    }*/
                }
            }
        } else {
            currentTime++;
        }
    }

    private void addNearNode(NodeView nvT) {
        double distance = Double.MAX_VALUE;
        ArrayList<NodeView> moreNear = new ArrayList<NodeView>();

        for (NodeView nodeViewSource : this.nodeViews.get(state - 1)) {
            if (distance(nodeViewSource, nvT) < distance) {
                distance = distance(nodeViewSource, nvT);
                moreNear.clear();
                moreNear.add(nodeViewSource);
            } else if (distance(nodeViewSource, nvT) == distance) {
                moreNear.add(nodeViewSource);
            }
        }

        for (NodeView nodeViewSource : moreNear) {
            this.sas.add(new SegmentAnimation(nodeViewSource.getCenter(), nvT.getCenter()));
        }

    }

    private double distance(NodeView nvS, NodeView nvT) {
        Node source = Game.getInstance().getLevel().getMap().getNode(nvS.getLinkToNode().x, nvS.getLinkToNode().y);
        Node target = Game.getInstance().getLevel().getMap().getNode(nvT.getLinkToNode().x, nvT.getLinkToNode().y);

        Point src = Game.getInstance().getLevel().getMap().getPoint(source);
        Point trg = Game.getInstance().getLevel().getMap().getPoint(target);

        return src.distance(trg);
    }

    /*
    private boolean isNearTo(NodeView nvS, NodeView nvT) {
        Node source = Game.getInstance().getLevel().getMap().getNode(nvS.getLinkToNode().x, nvS.getLinkToNode().y);
        Node target = Game.getInstance().getLevel().getMap().getNode(nvT.getLinkToNode().x, nvT.getLinkToNode().y);

        Point src = Game.getInstance().getLevel().getMap().getPoint(source);
        Point trg = Game.getInstance().getLevel().getMap().getPoint(target);

        return Game.getInstance().getLevel().getMap().isNearTo(source, target);
    }*/

    public boolean isEnded() {
        return currentTime == TIME;
    }

    private NodeView getNodeViewOf(Node n) {
        Point p = this.game.getLevel().getMap().getPoint(n);
        for (NodeView nv : this.map.getNodeViewList()) {
            if (nv.getLinkToNode().equals(p)) {
                return nv;
            }
        }
        return null;
    }
}
>>>>>>> 72de65b06a8a7504fea44ab5d4482691cb4efe72
