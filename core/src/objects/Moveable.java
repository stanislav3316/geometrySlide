package objects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

import update.GameLogic;

/**
 * Created by stanislav on 17.03.16.
 */
public class Moveable {

    private Cactus cactus;
    private Array<Cactus> arrCactuses;
    private Box box;
    private Array<Box> arrBoxes;

    private float distance = 960; //конкретное расстояние между объектами
    private Iterator<Box> iterBox;
    private Iterator<Cactus> iterCact;
    private Object lastMoveable;

    //рычаги воздействия на игру
    private int objectsOnScene = 0;
    private int maxObjectsOnScene = 3;
    private int minDistance = 350; //расстояни между объектами, можно менять
    private GameLogic logic;
    private boolean allowCreate = false;

    public Moveable(GameLogic logic) {
        lastMoveable = null;
        arrCactuses = new Array<Cactus>();
        arrBoxes = new Array<Box>();
        this.logic = logic;
    }

    public void update() {
        box = null;
        cactus = null;

        if (lastMoveable != null) {
            try {
                box = (Box) lastMoveable;
            } catch (Exception ex) {
                cactus = (Cactus) lastMoveable;
            }

            if (box != null) {
                distance = 960 - box.getX();
                box = null;
            } else {
                distance = 960 - cactus.getX();
                cactus = null;
            }
        }

        objectsOnScene = arrBoxes.size + arrCactuses.size;
        if(distance > minDistance && objectsOnScene < maxObjectsOnScene) {
            allowCreate = true;
        } else {
            allowCreate = false;
        }

        iterCact = arrCactuses.iterator();
        while(iterCact.hasNext()) {
            cactus = iterCact.next();
            cactus.update();

            if(cactus.checkDead()) {
                iterCact.remove();
            }
        }

        iterBox = arrBoxes.iterator();
        while(iterBox.hasNext()) {
            box = iterBox.next();
            box.update();
            if(box.checkedDead()) {
                iterBox.remove();
            }
        }
    }

    public void createBox(float x, float y, float size, float speed) {
        box = new Box(x,y,size,speed);
        arrBoxes.add(box);
        lastMoveable = box;
    }

    public void createCactus(float x, float y, float width, float height, float speed) {
        float value = MathUtils.random(0,40);
        if (value <= 10) { //red
            cactus = new Cactus(x,y,width,height,speed,"RED");
        } else if (value <= 20) { //green
            cactus = new Cactus(x,y,width,height,speed,"GREEN");
        } else if (value <= 30) { //blue
            cactus = new Cactus(x,y,width,height,speed,"BLUE");
        } else if (value <= 40) { //blue
            cactus = new Cactus(x,y,width,height,speed,"RND");
        }

        cactus.setColor();
        arrCactuses.add(cactus);
        lastMoveable = cactus;
    }

    public Array<Cactus> getArrCactuses() {
        return arrCactuses;
    }

    public Array<Box> getArrBoxes() {
        return arrBoxes;
    }

    public float getDistance() {
        return distance;
    }

    public int getObjectsOnScene() {return objectsOnScene;}

    public boolean getAllowCreate() {return allowCreate;}

}
