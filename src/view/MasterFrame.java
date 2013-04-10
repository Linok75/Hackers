      /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import model.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import view.levelsFrame.FinishLevel;
import view.levelsFrame.Level;

/**
 *
 * @author ara
 */
public class MasterFrame extends StateBasedGame {

    public static final int MAINMENUSTATE = 0;
    public static final int LEVELSTATE = 1;
    public static final int FINISHLEVELSTATE = 2;
    public static final int GAMEOVERSTATE = 3;
    public static final int ENDGAME = 4;
    private Game gameInstance;
    //Music
    private ArrayList<Music> musics = new ArrayList<Music>();
    private int idOfMusic = 0;
    private boolean music = true;

    public MasterFrame() throws SlickException {
        super("Hackers");

        this.addState(new MainMenu(MAINMENUSTATE));
        this.enterState(MAINMENUSTATE);

        this.musics.add(new Music("soundtrack/Hacker1.aiff"));
        this.musics.add(new Music("soundtrack/Hacker.aiff"));
        this.musics.get(idOfMusic).loop();
    }
    private static final int F7 = 65;
    private static final int F8 = 66;
    private static final int F9 = 67;

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);

        // Gestion du son
        if (key >= F7 && key <= F9) {
            if (key == F7) { // BACK
                idOfMusic--;
                music = true;
            } else if (key == F8) { // PLAY|PAUSE
                music = !music;
            } else if (key == F9) { // NEXT
                idOfMusic++;
                music = true;
            }

            if (idOfMusic < 0) {
                idOfMusic = musics.size() - 1;
            } else if (idOfMusic >= musics.size()) {
                idOfMusic = 0;
            }

            if (music) {
                musics.get(idOfMusic).loop();
            } else {
                musics.get(idOfMusic).pause();
            }

        }

    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        for (int i = 0; i < this.getStateCount(); i++) {
            this.getState(i).init(container, this);
        }
    }
}