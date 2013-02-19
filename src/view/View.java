/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import model.Game;

/**
 *
 * @author Ara
 */
public class View {
    private Dimension grid;
    private Game instance;
    
    public View(){
        this.instance=Game.getInstance();
        this.grid=this.instance.getLevel().getMap().getDimensionMap();
    }
}
