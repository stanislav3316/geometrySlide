package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import com.geometry.slide.GSGame;

import elements.SimpleButton;
import inputs.MainMenuProcessor;
import renders.MainMenuRender;

/**
 * Created by stanislav on 11.03.16.
 */
public class MainMenuScreen implements Screen {

    final private GSGame game;
    private MainMenuRender render;

    private SimpleButton play;
    private SimpleButton howPlay ;
    private SimpleButton getSkin;
    private SimpleButton exit;
    private Array<SimpleButton> buttons;

    private int buttonWidth = 960 / 4;
    private int buttonHeight = 70;

    public MainMenuScreen(final GSGame game) {
        this.game = game;

        exit = new SimpleButton(960 /2 - buttonWidth/2, 40, buttonWidth, buttonHeight, "exit",93);
        howPlay = new SimpleButton(960 /2 - buttonWidth/2, 300, buttonWidth, buttonHeight, "how to play",45);
        getSkin = new SimpleButton(960 /2 - buttonWidth/2, 170, buttonWidth, buttonHeight, "get free skin",45);
        play = new SimpleButton(960 /2 - buttonWidth/2, 430, buttonWidth, buttonHeight, "play",90);

        buttons = new Array<SimpleButton>();
        buttons.add(play);
        buttons.add(getSkin);
        buttons.add(howPlay);
        buttons.add(exit);

        render = new MainMenuRender(buttons);
        Gdx.input.setInputProcessor(new MainMenuProcessor(game,buttons,render));
    }

    @Override
    public void render(float delta) {
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
