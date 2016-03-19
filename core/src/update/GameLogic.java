package update;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.awt.Rectangle;

import elements.SimpleButton;
import objects.Box;
import objects.Cactus;
import objects.Hero;
import objects.Moveable;

/**
 * Created by stanislav on 15.03.16.
 */
public class GameLogic {

    private SimpleButton greenBtn;
    private SimpleButton blueBtn;
    private SimpleButton redBtn;
    private Array<SimpleButton> btns;
    private Hero hero;
    private Moveable moveable;
    private GameState state = GameState.RUNNING;
    private int score = 0;

    public enum GameState {
        RUNNING,
        GAMEOVER
    }

    public GameLogic() {
        greenBtn = new SimpleButton(30,18,195,90,"green",0);
        greenBtn.setRGB(0, 255f, 145f);
        blueBtn = new SimpleButton(735,18,195,90,"blue",0);
        blueBtn.setRGB(0,0,255f);
        redBtn = new SimpleButton(255,18,450,90,"red",0);
        redBtn.setRGB(255f,0,0);

        hero = new Hero(50, 130, 65,210);
        moveable = new Moveable(this);

        btns = new Array<SimpleButton>();
        btns.add(greenBtn);
        btns.add(blueBtn);
        btns.add(redBtn);
    }

    public Array<SimpleButton> getBtns() {
        return btns;
    }

    public Hero getHero() {
        return hero;
    }

    public void update() {
        if (state == GameState.RUNNING) {
            hero.update();
            moveable.update();

            for (Cactus cactus : moveable.getArrCactuses()) {
                if(hero.checkCollision(cactus.getRect()) && (hero.getColor()[0] != cactus.r() ||
                hero.getColor()[1] != cactus.g() || hero.getColor()[2] != cactus.b())) {
                    state = GameState.GAMEOVER;
                }

                if ((hero.getX() > cactus.getX() + cactus.width()) && cactus.getScored() == false) {
                    score += 10;
                    cactus.setScored();
                }
            }

            for (Box boxer : moveable.getArrBoxes()) {
                if (hero.checkCollision(boxer.getRect())) {
                    state = GameState.GAMEOVER;
                }

                if((hero.getX() > boxer.getX() + boxer.getSize()) && boxer.getScored() ==false) {
                    boxer.setScored();
                    score += 5;
                }
            }

            if (moveable.getAllowCreate()) {
                float val = MathUtils.random(0, 100);
                if (val <= 60) {
                    moveable.createCactus(960, 130, 60, 130, 210);
                } else {
                    moveable.createBox(960, 130, 45, 210);
                }
            }

        }
    }

    public Moveable getMoveable() {
        return moveable;
    }

    public int getScore() {return score;}

}
