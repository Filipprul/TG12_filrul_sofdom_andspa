package core;
import objects.*;
import game.objects.Objekt;

import java.util.ArrayList;

public class Grid {

    private static final int GRID_SIZE = 16;
    private final ArrayList<Objekt> Snake = new ArrayList<>(); // all data about the Snake "head","body","position"...
    private final int[][] grid_size = new int[GRID_SIZE][GRID_SIZE]; // game board size 16 * 16
    private Direction direction = Direction.RIGHT;

    public Grid (){
        spawn_snake();
    }

    public boolean isInsideGrid(int x, int y) {
        return x >= 0 && x < grid_size.length && y >= 0 && y < grid_size[0].length;
    }

    public void spawn_snake(){
        Snake.clear();
        Snake.add(new Head(8, 8, 1));
        Snake.add(new Body(7, 8, 1));
    }

    public void spawn_food(){} // random spawn food on board funktion Not done yet
    public void snake_grow(){} // grow body of snake funktion Not done yet

    public void setDirection(Direction direction){
        if (direction == null) {
            return;
        }
        if (direction.isOpposite(this.direction)) {
            return;
        }
        this.direction = direction;
    }

    public void setDirection(String input){
        if (input == null) {
            return;
        }
        switch (input.toLowerCase()) {
            case "up":
                setDirection(Direction.UP);
                break;
            case "down":
                setDirection(Direction.DOWN);
                break;
            case "left":
                setDirection(Direction.LEFT);
                break;
            case "right":
                setDirection(Direction.RIGHT);
                break;
            default:
                break;
        }
    }

    public void snake_move(){
        if (Snake.isEmpty()) {
            return;
        }
        Objekt head = Snake.get(0);
        if (head instanceof Head) {
            ((Head) head).move(direction);
        }
        for (int j = 1; j < Snake.size(); j++) {
            Objekt current = Snake.get(j);
            Objekt previous = Snake.get(j - 1);
            if (current instanceof Body) {
                ((Body) current).follow(previous);
            }
        }
    }
}


