package core;
import objects.*;
import java.util.Random;
import objects.Obj;
import java.util.ArrayList;

public class Grid {

    private static final int GRID_SIZE = 18;
    private ArrayList<Obj> Snake = new ArrayList<>(); // all data about the Snake "head","body","position"...
    private final Obj[][] grid_size = new Obj[GRID_SIZE][GRID_SIZE]; // game board size 16 * 16
    private Direction direction = Direction.RIGHT;
    private int food_index = 0;
    private int max_food = 3;

    public Grid (){
        spawn_snake();
    }

    public boolean chek_colision(){
        boolean colision = false;
        for(int i = 1; i< Snake.size();i++){
            if (Snake.get(0).get_x() == Snake.get(i).get_x()){
                colision = true;
            }else if (Snake.get(0).get_y() > grid_size.length || Snake.get(0).get_previus_x() > grid_size[0].length){
                colision = true;
            }else {
                colision = false;
            }
        }
        return colision;
    }

    public void eat_food(){
        int x = Snake.get(0).get_x();
        int y = Snake.get(0).get_y();
        if (grid_size[y][x].get_value() == 1){
            grid_size[y][x] = null;
            food_index --;
            snake_grow();
        }
    }

    public void spawn_snake(){
        Snake.clear();
        Snake.add(new Head(8, 8, 1));
        Snake.add(new Body(7, 8, 1));
    }

    public void spawn_food(){ // randomly spawn a food obj on the grid
        Random random = new Random();
        if (food_index < max_food +1){
            int x = random.nextInt(GRID_SIZE+1);
            int y = random.nextInt(GRID_SIZE+1);
            if(grid_size[y][x].get(Obj).get_value == 2 || grid_size[y][x].get(Obj).get_value == 1){
                spawn_food();
            }else {
                grid_size[y][x] = new Food(x,y,2);
                Food food = new Food(x,y,2);
                food_index ++;
            }
        }
    }

    public void snake_grow(){ // add a Body to the end of a Snake
        int y = Snake.get(Snake.size()-1).get_previus_y();
        int x = Snake.get(Snake.size()-1).get_previus_x();
        Snake.add(new Body(x, y, 1));
    }

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
        Obj head = Snake.get(0);
        if (head instanceof Head) {
            ((Head) head).move(direction);
        }
        for (int j = 1; j < Snake.size(); j++) {
            Obj current = Snake.get(j);
            Obj previous = Snake.get(j - 1);
            if (current instanceof Body) {
                ((Body) current).follow(previous);
            }
        }
    }
}
