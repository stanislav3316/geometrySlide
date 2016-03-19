package elements;

import com.badlogic.gdx.math.Rectangle;

import sun.java2d.pipe.SpanShapeRenderer;

/**
 * Created by stanislav on 11.03.16.
 */
public class SimpleButton {
    private float x, y, width, height;
    private String text;
    private Rectangle button;
    private boolean isPressed;
    private int textPaddingLeft;
    private float r,g,b;

    public SimpleButton(float x, float y, float width, float height, String text, int padding) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        button = new Rectangle(x, y, width, height);
        isPressed = false;
        this.textPaddingLeft = padding;
    }

    public boolean isClicked(int screenX, int screenY) {
        return button.contains(screenX, screenY);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }

    public void setPressed() {
        isPressed = true;
    }

    public void notPressed() {
        isPressed = false;
    }

    public boolean getPressed() {return isPressed;}

    public float getAlpha() {
        if (isPressed)
            return 100f;
        else return 50f;
    }

    public void setRGB(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public int getPadding() {
        return textPaddingLeft;
    }

}
