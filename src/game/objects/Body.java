package objects;

public class Body extends Objekt{ // Body of the snake and how it follows the head.
    public Body(int x, int y, int value){
        super(x, y, value);
    }

    public void follow(Objekt objekt){
        updatePreviousPosition();
        set_x(objekt.get_previus_x());
        set_y(objekt.get_previus_y());
    }
}
