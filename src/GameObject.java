/**
 * Created by qwer on 19.01.15.
 */
abstract public class GameObject {
    protected float x, y;
    abstract public void update(int delta);
    abstract public void render();
}
