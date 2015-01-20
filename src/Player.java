import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

import org.newdawn.slick.Color;

/**
 * Created by qwer on 19.01.15.
 */
public class Player extends GameObject {

    public static final int STATE_NOT_MOVING = 0;
    public static final int STATE_MOVING_UP = 1;
    public static final int STATE_MOVING_DOWN = 2;

    public static final int POSITION_TOP = 0;
    public static final int POSITION_RIGHT = 1;
    public static final int POSITION_BOTTOM = 2;
    public static final int POSITION_LEFT = 3;

    private float length = 150;
    private float height = 20;

    private int state;
    private int position = POSITION_BOTTOM;

    public int antiScore = 0;

    private int keyUp, keyRight, keyDown, keyLeft;

    TrueTypeFont font;

    public Player(int position) {
        this.position = position;
        switch (position) {
            case POSITION_BOTTOM:
                x = PingPong2.WIDTH / 2;
                y = height;
                break;
            case POSITION_TOP:
                x = PingPong2.WIDTH / 2;
                y = PingPong2.HEIGHT;
                break;
        }

        Font  f = new Font("Arial", Font.BOLD, 64);
        font = new TrueTypeFont(f, false);

    }

    public float getHeight() {
        return height;
    }

    public float getLength() {
        return length;
    }

    public void setControls(int keyUp, int keyRight, int keyDown, int keyLeft) {
        this.keyUp = keyUp;
        this.keyRight = keyRight;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void update(int delta) {
        if (Keyboard.isKeyDown(keyLeft)) x -= 0.5f * delta;
        if (Keyboard.isKeyDown(keyRight)) x += 0.5f * delta;

        // if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) y -= 0.35f * delta;
//        if (state == STATE_NOT_MOVING) {
//            if (Keyboard.isKeyDown(keyUp)) state = STATE_MOVING_UP;
//        } else if (state == STATE_MOVING_UP && y >= 60) {
//            state = STATE_MOVING_DOWN;
//        } else if (state == STATE_MOVING_UP) {
//            y += 0.5f * delta;
//        } else if (state == STATE_MOVING_DOWN && y <= height) {
//            state = STATE_NOT_MOVING;
//        } else if (state == STATE_MOVING_DOWN) {
//            y -= 0.5f * delta;
//        }

        // keep player on the screen
        if (x - length / 2 < 0) x = length / 2;
        if (x + length / 2 > PingPong2.WIDTH) {
            x = PingPong2.WIDTH - length / 2;
        }
//        if (y < 0) y = 0;
//        if (y > 800) y = 800;
    }

    @Override
    public void render() {

        GL11.glBegin(GL11.GL_QUADS);
            GL11.glColor3f(0.2f, 0.2f, 1.0f);
            GL11.glVertex2f(x - length / 2, y);
            GL11.glVertex2f(x + length / 2, y);
            GL11.glVertex2f(x + length / 2, y - height);
            GL11.glVertex2f(x - length / 2, y - height);
        GL11.glEnd();

        GL11.glEnable(GL11.GL_BLEND);
        switch (position) {
            case POSITION_BOTTOM:
                font.drawString(PingPong2.WIDTH / 2 - font.getWidth(antiScore + "") / 2, PingPong2.HEIGHT - 200 - font.getHeight() / 2, antiScore + "", Color.gray);
                break;
            case POSITION_TOP:
                font.drawString(PingPong2.WIDTH / 2 - font.getWidth(antiScore + "") / 2, 200 - font.getHeight() / 2, antiScore + "", Color.gray);
                break;
        }
        GL11.glDisable(GL11.GL_BLEND);
    }

    public int getPosition() {
        return position;
    }
}
