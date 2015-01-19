import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

/**
 * Created by qwer on 19.01.15.
 */
public class Player extends GameObject {

    private static final int STATE_NOT_MOVING = 0;
    private static final int STATE_MOVING_UP = 1;
    private static final int STATE_MOVING_DOWN = 2;
    private float length = 200;
    private float height = 20;

    /** position of quad */
    private float x = 400, y = height;

    private int state;

    public Player() {

    }

    @Override
    public void update(int delta) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) x -= 0.5f * delta;
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) x += 0.5f * delta;

        // if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) y -= 0.35f * delta;
        if (state == STATE_NOT_MOVING) {
            if (Keyboard.isKeyDown(Keyboard.KEY_UP)) state = STATE_MOVING_UP;
        } else if (state == STATE_MOVING_UP && y >= 60) {
            state = STATE_MOVING_DOWN;
        } else if (state == STATE_MOVING_UP) {
            y += 0.5f * delta;
        } else if (state == STATE_MOVING_DOWN && y <= height) {
            state = STATE_NOT_MOVING;
        } else if (state == STATE_MOVING_DOWN) {
            y -= 0.5f * delta;
        }

        // keep player on the screen
        if (x - length / 2 < 0) x = length / 2;
        if (x - length / 2 > 600) x = 600 + length / 2;
//        if (y < 0) y = 0;
//        if (y > 800) y = 800;
    }

    @Override
    public void render() {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x - length / 2, y);
        GL11.glVertex2f(x + length / 2, y);
        GL11.glVertex2f(x + length / 2, y - height);
        GL11.glVertex2f(x - length / 2, y - height);
    }
}
