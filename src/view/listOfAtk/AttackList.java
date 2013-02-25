/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.listOfAtk;

import view.tools.TextToImg;
import view.tools.SubIllustration;
import java.awt.Point;
import java.util.ArrayList;
import model.ressources.attacks.Attack;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Ara
 */
public class AttackList extends SubIllustration {

    private ArrayList<Attack> attacks;
    private TrueTypeFont font;
    private Image cadreList;
    private int scrolledPx;

    public AttackList(ArrayList<Attack> attacks, TrueTypeFont font, Point pos) throws SlickException {
        super(new Image(1, 1), pos);

        this.cadreList = new Image(AttackList.class.getResource("ressources/cadreListElem.png").getPath());
        this.attacks = attacks;
        this.font = font;
        this.scrolledPx = 0;
        super.setImage(constructImg());
        super.resetSubPos();
        System.out.println(this.getImage().getHeight());
    }

    public boolean scrollAgain(boolean next) {

        if (this.scrolledPx + 1 > this.cadreList.getHeight()) {
            if (next) {
                this.setStartY(this.getStartY() + (this.cadreList.getHeight() - this.scrolledPx));
            } else {
                this.setStartY(this.getStartY() - (this.cadreList.getHeight() - this.scrolledPx));
            }
            this.scrolledPx = 0;
            return false;
        } else {
            this.scrolledPx += 1;
            return true;
        }
    }

    private Image constructImg() throws SlickException {
        Graphics graph;
        Image completed;
        int nbLigne, height;

        nbLigne = 0;

        if (cadreList.getHeight() * this.attacks.size() >= 390) {
            height = cadreList.getHeight() * this.attacks.size();
        } else {
            height = 390;
        }

        completed = new Image(cadreList.getWidth(), height);
        graph = completed.getGraphics();

        while (cadreList.getHeight() * nbLigne <= height) {
            graph.drawImage(cadreList, 0, cadreList.getHeight() * nbLigne);
            try {
                graph.drawImage(TextToImg.convertTextToImg(this.attacks.get(nbLigne).getTitle(), font), 25, cadreList.getHeight() * nbLigne + 25);
            } catch (IndexOutOfBoundsException e) {
                graph.drawImage(TextToImg.convertTextToImg("?????", font), 25, cadreList.getHeight() * nbLigne + 25);
//                attacks.add(null);
            }
            nbLigne++;
        }

        graph.flush();

        return completed;
    }
}
