package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.geometry.slide.GSGame;

import elements.SimpleButton;
import inputs.HowToPlayProcessor;
import renders.HowToPlayRender;


/**
 * Created by stanislav on 13.03.16.
 */
public class HowToPlayScreen implements Screen {

    private GSGame game;
    private HowToPlayRender render;
    private SimpleButton backBtn;

    public HowToPlayScreen(GSGame game) {
        this.game = game;
        backBtn = new SimpleButton(22,470,100,50,"back",19);
        render = new HowToPlayRender(backBtn);
        Gdx.input.setInputProcessor(new HowToPlayProcessor(game,backBtn,render));
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        render.render();
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
