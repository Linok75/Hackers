/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.animations;

import java.awt.Point;
import java.util.ArrayList;
import model.Game;
import model.maps.Node;
import model.ressources.attacks.Attack;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
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
    private static final int TIME = 500;
    private int currentTime = 0;

    public CorruptionAnimation(Game game, Map map, Attack atk) {
        this.game = game;
        this.map = map;
        ArrayList<ArrayList<Node>> nodes = atk.getNodesHack();
        this.nodeViews = new ArrayList<ArrayList<NodeView>>();
        this.source = getNodeViewOf(nodes.get(0).get(0)).getCenter();
        nodes.remove(0);
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


        if (!this.nodeViews.isEmpty()) {
            ArrayList<NodeView> array = this.nodeViews.get(0);
            for (NodeView nodeView : array) {
                this.sas.add(new SegmentAnimation(source, nodeView.getCenter()));
            }
            this.nodeViews.remove(0);
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
        if (!this.nodeViews.isEmpty()) {
            ArrayList<NodeView> array = this.nodeViews.get(0);
            for (NodeView nodeView : array) {
                this.sas.add(new SegmentAnimation(source, nodeView.getCenter()));
            }
            this.nodeViews.remove(0);
        } else {
            currentTime++;
        }
    }

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
