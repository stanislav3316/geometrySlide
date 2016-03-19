package inputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.geometry.slide.GSGame;

import elements.SimpleButton;
import renders.GetSkinRender;
import screens.MainMenuScreen;

/**
 * Created by stanislav on 14.03.16.
 */
public class GetSkinProcessor implements InputProcessor {

    private GSGame game;
    private SimpleButton btn;
    private GetSkinRender render;
    private Vector3 coordinats;

    public GetSkinProcessor(GSGame game, SimpleButton btn, GetSkinRender render) {
        this.game = game;
        this.btn = btn;
        this.render = render;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
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
        render.getCamera().unproject(coordinats);
        screenX = (int) coordinats.x;
        screenY = (int) coordinats.y;

        if(btn.isClicked(screenX, screenY)) {
            game.setScreen(new MainMenuScreen(game));
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
