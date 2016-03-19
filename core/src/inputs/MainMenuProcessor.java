package inputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.geometry.slide.GSGame;

import elements.SimpleButton;
import renders.MainMenuRender;
import screens.GetSkinScreen;
import screens.HowToPlayScreen;
import screens.PlayScreen;

/**
 * Created by stanislav on 12.03.16.
 */
public class MainMenuProcessor implements InputProcessor {

    final private GSGame game;
    private Array<SimpleButton> btns;
    private SimpleButton clickedBtn;
    private MainMenuRender render;
    private Vector3 coordinats;

    public MainMenuProcessor(final GSGame game, Array<SimpleButton> btns, MainMenuRender render) {
        this.game = game;
        this.btns = btns;
        this.render = render;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
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
        render.getCamera().unproject(coordinats);
        screenX = (int) coordinats.x;
        screenY = (int) coordinats.y;

        for (SimpleButton btn : btns) {
            if(btn.isClicked(screenX, screenY) && clickedBtn == null) {
                clickedBtn = btn;
                clickedBtn.setPressed();
            }
        }

        if (clickedBtn != null) {
            if (clickedBtn.getText().equalsIgnoreCase("play")) {
                game.setScreen(new PlayScreen(game));
            } else if (clickedBtn.getText().equalsIgnoreCase("get free skin")) {
                game.setScreen(new GetSkinScreen(game));
            } else if (clickedBtn.getText().equalsIgnoreCase("how to play")) {
                game.setScreen(new HowToPlayScreen(game));
            } else if (clickedBtn.getText().equalsIgnoreCase("exit")) {
                Gdx.app.exit();
            }

        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(clickedBtn != null) {
            Gdx.app.log("hi","ОТЖАТО");
            clickedBtn.notPressed();
            clickedBtn = null;
        }

        return true;
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
