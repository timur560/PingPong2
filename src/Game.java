abstract class Game {
    public static int STATE_PLAYING = 1;
    public static int STATE_PAUSED = 2;

    protected int state = STATE_PLAYING;

    abstract void showMessage(String message);

    public void toggleState() {
        state = 3 - state;
    }
}
