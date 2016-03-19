package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by stanislav on 17.03.16.
 */
public class Box {

    private float x,y,size,speed;
    private Rectangle box;
    private boolean isDead = false;
    private boolean scored = false;

    public Box(float x, float y, float size, float speed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;

        box = new Rectangle(x,y,size,size);
    }

    public void update() {
        x -= speed*Gdx.graphics.getDeltaTime();
        box.x = x;

        if(x < -size) {
            speed= 0;
            isDead = true;
        }
    }

    public Rectangle getRect() {
        return box;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public boolean checkedDead() {return isDead;}

    public void setScored() {scored = true;}

    public boolean getScored() {return scored;}
}
