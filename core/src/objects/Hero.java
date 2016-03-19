package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by stanislav on 15.03.16.
 */
public class Hero {

    private float x,y,size;
    private int accelerationY = 195;
    private int jumpForce;
    private float positionY;
    private float rotation = 0;
    private boolean needRotation = false;
    private float color[] = {200f,100f, 190f};
    private Polygon body;

    private float speedY = 0;
    private float rotationSpeed = 0;
    private boolean jumping = false;
    private boolean downFaster = false;
    private boolean needToCompute = true;

    public Hero(float x,float y,float size, int jumpForce) {
        this.x = x;
        this.positionY = y;
        this.y = y;
        this.size = size;
        this.jumpForce = jumpForce;
        this.body = new Polygon(new float[]{0,0,size,0,size,size,0,size});
        this.body.setOrigin(size/2,size/2);
    }

    public void update() {
        body.setRotation(rotation);
        body.setPosition(x,y);
        if (needToCompute) {
            if (jumping == false && downFaster == false) {
                y -= (accelerationY + 50 + speedY) * Gdx.graphics.getDeltaTime();
                speedY += 280 * Gdx.graphics.getDeltaTime();
                rotation -= (190 + rotationSpeed) * Gdx.graphics.getDeltaTime();
                rotationSpeed -= 145 * Gdx.graphics.getDeltaTime();

            } else if (jumping == true && downFaster == false) {
                y += (jumpForce - speedY) * Gdx.graphics.getDeltaTime();
                speedY += 190 * Gdx.graphics.getDeltaTime();
                rotation -= (190 - rotationSpeed) * Gdx.graphics.getDeltaTime();
                rotationSpeed += 145 * Gdx.graphics.getDeltaTime();

                if (Math.abs(jumpForce - speedY) < 65) {
                    speedY = 0;
                    rotationSpeed = 0;
                    jumping = false;
                }
            } else if (downFaster == true) {
                y -= (accelerationY + 170 + speedY) * Gdx.graphics.getDeltaTime();
                speedY += 300 * Gdx.graphics.getDeltaTime();

                rotation -= (190 + rotationSpeed) * Gdx.graphics.getDeltaTime();
                rotationSpeed -= 245 * Gdx.graphics.getDeltaTime();
            }

            if (y < positionY) {
                y = positionY;
                needRotation = false;
                rotation = 0;
                speedY = 0;
                rotationSpeed = 0;
                downFaster = false;
                needToCompute = false;
                jumping = false;
            }
        }
    }

    public void jump() {
        if (jumping == false && y == positionY) {
            jumping = true;
            needRotation = true;
            downFaster = false;
            speedY = 0;
            needToCompute = true;
        }
    }

    public float getX() {
        return x;
    }

    public boolean isNeedRotation() {return needRotation;}

    public void setDownFaster() {downFaster = true;}

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public float getRotation() {
        return rotation;
    }

    public float[] getColor() {
        return color;
    }

    public void setColor(float r, float g, float b) {
        color[0] = r;
        color[1] = g;
        color[2] = b;
    }

    public boolean checkCollision(Rectangle r) {
        Polygon rPoly = new Polygon(new float[] { 0, 0, r.width, 0, r.width,
                r.height, 0, r.height });
        rPoly.setPosition(r.x, r.y);
        if (Intersector.overlapConvexPolygons(rPoly, body))
            return true;
        return false;
    }

}
