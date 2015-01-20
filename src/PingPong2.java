import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.io.File;

public class PingPong2 extends Game {

    public Player player1, player2;
    public Ball ball;

    // float rotation = 0;

    long lastFrame;
    int fps;
    long lastFPS;

    public static int WIDTH = 400;
    public static int HEIGHT = 600;

    public void start() {

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        initGL(); // init OpenGL
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer

        ball = new Ball(this);

        player1 = new Player(Player.POSITION_BOTTOM);
        player1.setControls(Keyboard.KEY_UP, Keyboard.KEY_RIGHT, Keyboard.KEY_DOWN, Keyboard.KEY_LEFT);

        player2 = new Player(Player.POSITION_TOP);
        player2.setControls(Keyboard.KEY_W, Keyboard.KEY_D, Keyboard.KEY_S, Keyboard.KEY_A);

        ball.addPlayer(player1);
        ball.addPlayer(player2);

        while (!Display.isCloseRequested()) {
            // pause
            while(Keyboard.next()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
                    if (Keyboard.getEventKeyState()) {
                        toggleState();
                    }
                }
            }

            int delta = getDelta();

            update(delta);
            renderGL();

            Display.update();
            Display.sync(60); // cap fps to 60fps
        }

        Display.destroy();
    }

    public void update(int delta) {
        // rotate quad
        // rotation += 0.45f * delta;

        if (state == STATE_PAUSED) {
            return;
        }

        player1.update(delta);
        player2.update(delta);
        ball.update(delta);

        updateFPS(); // update FPS Counter
    }

    /**
     * Calculate how many milliseconds have passed
     * since last frame.
     *
     * @return milliseconds passed since last frame
     */
    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;
    }

    /**
     * Get the accurate system time
     *
     * @return The system time in milliseconds
     */
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    /**
     * Calculate the FPS and set it in the title bar
     */
    public void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    public void initGL() {
        // GL11.glMatrixMode(GL11.GL_PROJECTION);

//        GL11.glEnable(GL11.GL_TEXTURE_2D);
//        GL11.glShadeModel(GL11.GL_SMOOTH);
//        GL11.glDisable(GL11.GL_DEPTH_TEST);
//        GL11.glDisable(GL11.GL_LIGHTING);

//        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
//        GL11.glClearDepth(1);

//        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

//        GL11.glViewport(0, 0, WIDTH, HEIGHT);
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);

//        GL11.glMatrixMode(GL11.GL_PROJECTION);

        GL11.glLoadIdentity();
        GL11.glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    public void renderGL() {
// Clear The Screen And The Depth Buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

// draw player
//        GL11.glPushMatrix();
//        GL11.glTranslatef(x, y, 0);
//        GL11.glRotatef(rotation, 0f, 0f, 1f);
//        GL11.glTranslatef(-x, -y, 0);

        player1.render();
        player2.render();

        ball.render();

        GL11.glEnd();
        GL11.glPopMatrix();


    }

    public static void main(String[] argv) {
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"),
                LWJGLUtil.getPlatformName()).getAbsolutePath());

        PingPong2 inputExample = new PingPong2();
        inputExample.start();
    }

    @Override
    void showMessage(String message) {

    }

}