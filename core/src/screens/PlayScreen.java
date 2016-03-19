package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.geometry.slide.GSGame;

import inputs.PlayProcessor;
import renders.PlayRender;
import update.GameLogic;

/**
 * Created by stanislav on 15.03.16.
 */
public class PlayScreen implements Screen {

    private GSGame game;
    private PlayRender render;
    private GameLogic logic;

    public PlayScreen(GSGame game) {
        this.game = game;
        logic = new GameLogic();
        render = new PlayRender(logic);

        Gdx.input.setInputProcessor(new PlayProcessor(logic,render.getCamera(),game));
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        logic.update();
        render.render();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
