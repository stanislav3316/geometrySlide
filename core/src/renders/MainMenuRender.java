package renders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import elements.SimpleButton;

/**
 * Created by stanislav on 11.03.16.
 */
public class MainMenuRender {

    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Array<SimpleButton> btns;
    private BitmapFont font;
    private SpriteBatch batch;

    public MainMenuRender(Array<SimpleButton> btns) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,960,540);
        shapeRenderer = new ShapeRenderer();

        font = new BitmapFont();
        batch = new SpriteBatch();
        this.btns = btns;
    }
    
    public void render() {
        Gdx.gl.glClearColor(10/255.0f,15/255.0f,230/255.0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(SimpleButton btn : btns) {
            shapeRenderer.setColor(btn.getAlpha()/255.0f, 80/255.0f,100/255.0f, 0.5f);
            shapeRenderer.rect(btn.getX(), btn.getY(), btn.getWidth(), btn.getHeight());
        }
        shapeRenderer.end();

        for (SimpleButton btn : btns) {
            batch.begin();
            font.getData().setScale(2f, 2f);
            font.setColor(btn.getPressed()?Color.BLACK:Color.WHITE);
            font.draw(batch,btn.getText(),btn.getX() + btn.getPadding() ,btn.getY() + 48);
            batch.end();
        }
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
