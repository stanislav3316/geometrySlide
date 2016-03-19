package renders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import elements.SimpleButton;
import objects.Box;
import objects.Cactus;
import objects.Hero;
import objects.Moveable;
import update.GameLogic;

/**
 * Created by stanislav on 15.03.16.
 */
public class PlayRender {

    private GameLogic logic;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BitmapFont font;
    private Hero hero;
    private Moveable moveable;

    private Array<Box> boxes;
    private Array<Cactus> cactuses;

    public PlayRender(GameLogic logic) {
        this.logic = logic;
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        hero = logic.getHero();
        moveable = logic.getMoveable();
        boxes = moveable.getArrBoxes();
        cactuses = moveable.getArrCactuses();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,960,540);
    }

    public void render() {
        Gdx.gl.glClearColor(100/255.0f,150/255.0f,120/255.0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(139f/255.0f,143f/255.0f,141f/255.0f,1);
        shapeRenderer.rect(0,0,960,130);
        shapeRenderer.end();

        for (SimpleButton btn : logic.getBtns()) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(btn.getR()/255.0f,btn.getG()/255.0f,btn.getB()/255.0f,1);
            shapeRenderer.rect(btn.getX(),btn.getY(),btn.getWidth(),btn.getHeight());
            shapeRenderer.end();
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(100f,100f,100f,1);
        for (Box box : boxes) {
            shapeRenderer.rect(box.getX(), box.getY(), box.getSize(), box.getSize());
        }

        for (Cactus cactus : cactuses) {
            shapeRenderer.setColor(cactus.r()/255.0f,cactus.g()/255.0f,cactus.b()/255.0f,1);
            shapeRenderer.rect(cactus.getX(), cactus.getY(),cactus.width(),cactus.height());
            if (cactus.getColor() == "RND" && ((cactus.getX() - hero.getX() < 200))) {
                cactus.randomColor();
            }
        }
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(hero.getColor()[0] / 255.0f, hero.getColor()[1] / 255.0f, hero.getColor()[2] / 255.0f, 1);
        shapeRenderer.rect(hero.getX(), hero.getY(),hero.getSize()/2,hero.getSize()/2, hero.getSize(), hero.getSize(),1f,1f,hero.getRotation());
        shapeRenderer.end();

        batch.begin();
        font.getData().setScale(1.5f, 1.5f);
        font.draw(batch,"Score: " + logic.getScore(),800,525);
        batch.end();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

}
