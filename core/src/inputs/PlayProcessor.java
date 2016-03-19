package inputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.geometry.slide.GSGame;

import elements.SimpleButton;
import objects.Hero;
import screens.MainMenuScreen;
import update.GameLogic;

/**
 * Created by stanislav on 15.03.16.
 */
public class PlayProcessor implements InputProcessor {

    private GameLogic logic;
    private OrthographicCamera camera;
    private Vector3 coordinats;
    private GSGame game;
    private Hero hero;

    public PlayProcessor(GameLogic logic, OrthographicCamera camera, GSGame game) {
        this.logic = logic;
        this.camera = camera;
        this.game = game;
        this.hero = logic.getHero();
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.BACK) {
            //Gdx.input.vibrate(20);
            game.setScreen(new MainMenuScreen(game));
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        coordinats = new Vector3(screenX,screenY,0);
        camera.unproject(coordinats);
        screenX = (int) coordinats.x;
        screenY = (int) coordinats.y;

        if (screenY <= 130) {
            for (SimpleButton btn : logic.getBtns()) {
                if (btn.isClicked(screenX,screenY)) {
                    hero.setColor(btn.getR(), btn.getG(), btn.getB());
                    break;
                }
            }
        } else {
            if(hero.isNeedRotation() == false) {
                hero.jump();
            } else {
                hero.setDownFaster();
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
