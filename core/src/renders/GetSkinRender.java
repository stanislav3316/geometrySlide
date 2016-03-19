package renders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import elements.SimpleButton;

/**
 * Created by stanislav on 14.03.16.
 */
public class GetSkinRender {

    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private SimpleButton btn;
    private SpriteBatch batch;

    public GetSkinRender(SimpleButton btn) {
        this.btn = btn;
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,960,540);
    }

    public void render() {
        Gdx.gl.glClearColor(10/255.0f,15/255.0f,230/255.0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1/255.0f, 80/255.0f,100/255.0f, 0.5f);
        shapeRenderer.rect(btn.getX(), btn.getY(), btn.getWidth(), btn.getHeight());
        shapeRenderer.end();

        batch.begin();
        font.getData().setScale(2f,2f);
        font.draw(batch,"Back",btn.getX() + btn.getPadding() ,btn.getY() + 36);
        batch.end();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
