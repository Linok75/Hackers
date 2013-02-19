/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import model.Game;
import model.Level;
import model.maps.Defence;
import model.ressources.attacks.Attack;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Ara
 */
public final class View {

    private static volatile View viewInstance = null;
    protected HashMap<Attack, Color> assocColorAtk;
    protected Dimension gridDimension;
    protected Game gameInstance;
    protected ArrayList<NodeView> nodeViewList;

    public View() {
        this.gameInstance = Game.getInstance();
        this.gridDimension = this.gameInstance.getLevel().getMap().getDimensionMap();
        this.nodeViewList = new ArrayList<>();
        this.assocColorAtk = new HashMap<>();

        this.setAssocColorDef();
        this.setNodeViewList();
    }

    public static View makeInstance() {
        if (viewInstance != null) {
            throw new RuntimeException("Il est impossible de lancer plusieurs instance de view !");
        }
        View.viewInstance = new View();
        return View.viewInstance;
    }

    public static View getInstance() {
        if (viewInstance == null) {
            throw new RuntimeException("La classe 'View' n'a pas encore été instanciée !");
        } else {
            return viewInstance;
        }
    }

    public void start() {
//        try {
//            AppGameContainer baseFrame = new AppGameContainer(new MasterFrame());
//            baseFrame.setDisplayMode(baseFrame.getScreenWidth() * 75 / 100, baseFrame.getScreenHeight() * 75 / 100, false);
//            baseFrame.setShowFPS(false);
//            baseFrame.start();
//        } catch (SlickException e) {
//            e.printStackTrace();
//        }
        System.out.println("On start !");
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
        maxCol = (int) this.gridDimension.getWidth();
        x = 70;
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
                System.out.print(this.gameInstance.getLevel().getMap().getNode(row, col));
                for (Entry<Attack, Color> assoc : this.assocColorAtk.entrySet()) {
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

                x += 117;
            }
            System.out.println("");
            y += 115;
        }
    }
}
