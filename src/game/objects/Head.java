package objects;

import core.Direction;

public class Head extends Objekt{  // Head of the snake and its movement 
    public Head(int x, int y, int value){
        super(x, y, value);
    }

    public void move(Direction direction){
        updatePreviousPosition();
        switch (direction) {
            case UP:
                set_y(get_y() + 1);
                break;
            case DOWN:
                set_y(get_y() - 1);
                break;
            case LEFT:
                set_x(get_x() - 1);
                break;
            case RIGHT:
                set_x(get_x() + 1);
                break;
        }
    }
}
