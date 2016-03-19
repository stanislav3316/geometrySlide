package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.geometry.slide.GSGame;

import elements.SimpleButton;
import inputs.GetSkinProcessor;
import renders.GetSkinRender;

/**
 * Created by stanislav on 14.03.16.
 */
public class GetSkinScreen implements Screen {

    private GSGame game;
    private GetSkinRender render;
    private SimpleButton backBtn;

    public GetSkinScreen(GSGame game) {
        this.game = game;
        backBtn = new SimpleButton(22,470,100,50,"back",19);
        render = new GetSkinRender(backBtn);
        Gdx.input.setInputProcessor(new GetSkinProcessor(game,backBtn,render));
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
