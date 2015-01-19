import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

/**
 * Created by qwer on 19.01.15.
 */
public class Ball extends GameObject {
    private float radius = 20;

    private float direction = 140;
    private float speed = 3;

    private List<Player> players = new ArrayList<Player>();

    public Ball() {
        x = 100;
        y = 300;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    @Override
    public void update(int delta) {
        // change position

        double xk = cos(toRadians(direction)) * speed;
        double yk = sin(toRadians(direction)) * speed;

        // check players

        for (Player p: players) {
            switch (p.getPosition()) {
                case Player.POSITION_BOTTOM:
                    if (y + yk - radius < p.getHeight()) {// height
                        if (x + xk > p.x - p.getLength() / 2 // left border
                                && x + xk < p.x + p.getLength() / 2) { // right border

                            direction = (float) (20 + 140 * (p.x + p.getLength() / 2 - x + xk) / p.getLength());

                            // speed ++;
                        } else {
                            reset();
                        }
                    }
                    break;
            }
        }

        // check borders
        if (x + xk - radius < 0 || x + xk + radius > PingPong2.WIDTH) { // left, right
            direction = 540 - direction;
        }

        if (y + yk - radius < 0 || y + yk + radius > PingPong2.HEIGHT) { // top, bottom
            direction = 360 - direction;
        }

        x += cos(toRadians(direction)) * speed;
        y += sin(toRadians(direction)) * speed;

        // x += 0.1f * delta; y += 0.1f * delta;

    }

    private void reset() {
        x = 100;
        y = 300;
        direction = 140;
        speed = 3;
    }

    @Override
    public void render() {

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(x - radius, y + radius);
        GL11.glVertex2f(x + radius, y + radius);
        GL11.glVertex2f(x + radius, y - radius);
        GL11.glVertex2f(x - radius, y - radius);

    }
}
