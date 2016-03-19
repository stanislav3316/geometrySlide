package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by stanislav on 17.03.16.
 */
public class Cactus {

    private float x,y,width,height,speed;
    private Rectangle cactus;
    private float r,g,b;
    private boolean isDead = false;
    private String color;
    private boolean scored = false;

    public Cactus(float x, float y, float width, float height, float speed, String color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;

        cactus = new Rectangle(x,y,width,height);
    }

    public void update() {
        x -= speed* Gdx.graphics.getDeltaTime();
        cactus.x = x;

        if(x < -width) {
            speed = 0;
            isDead = true;
        }
    }

    public Rectangle getRect() {
        return cactus;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float width() {
        return width;
    }

    public float height() {
        return height;
    }

    public boolean checkDead() {
        return isDead;
    }

    public String getColor() {
        return color;
    }

    public void setColor() {
        if (color == "RED") {
            r = 255f;
            g = 0;
            b = 0;
        } else if (color == "GREEN") {
            r= 0;
            g = 255f;
            b = 145f;
        } else if (color == "BLUE") {
            r = 0;
            g = 0;
            b = 255f;
        } else if (color == "RND") {
            r = 45;
            g = 45;
            b = 45;
        }
    }

    public void randomColor() {
        double rnd = MathUtils.random(0,3);
        if (rnd <=1) {
            color = "RED";
        } else if(rnd <= 2) {
            color = "GREEN";
        } else {
            color = "BLUE";
        }

        setColor();
    }

    public void setScored() {scored = true;}

    public boolean getScored() {return scored;}

    public float r() {
        return r;
    }

    public float g() {
        return g;
    }

    public float b() {
        return b;
    }

}
