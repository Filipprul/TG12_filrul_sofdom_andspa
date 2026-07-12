package game.objects;

public class Body extends Obj {
    public Body(int x, int y, int value){
        super(x, y, value);
    }

    public void follow(Obj objekt){
        updatePreviousPosition();
        set_x(objekt.get_previus_x());
        set_y(objekt.get_previus_y());
    }
    
}
