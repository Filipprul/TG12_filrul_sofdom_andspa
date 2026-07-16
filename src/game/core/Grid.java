package game.core;

import game.objects.Body;
import game.objects.Food;
import game.objects.Head;
import game.objects.Obj;
import java.util.ArrayList;
import java.util.Random;

public class Grid {

    private static final int GRID_SIZE = 18;
    private final ArrayList<Obj> Snake = new ArrayList<>(); // all data about the Snake "head","body","position"...
    private final Obj[][] grid_size = new Obj[GRID_SIZE][GRID_SIZE]; // game board size 16 * 16
    private Direction direction = Direction.UP;
    private int food_index = 0;
    private int max_food = 3;
    private int score = 0;
    private int foodX = -1;
    private int foodY = -1;

    public Grid (){
        spawn_snake();
        this.score = 0; // Starte bei 0
        spawn_food();
    }

    public boolean check_colision(){ // checks if the snake hits itsef or the void/border of the map
        Obj head = Snake.get(0);

        // 1. Wand-Collision
        if (head.get_x() < 0 || head.get_x() >= GRID_SIZE || head.get_y() < 0 || head.get_y() >= GRID_SIZE) {return true;}

        // 2. Self-Collision
        for (int i = 1; i < Snake.size(); i++) {
            if (head.get_x() == Snake.get(i).get_x() && head.get_y() == Snake.get(i).get_y()) {return true;}
        }
        return false;
    }

    public void eat_food(){
        int x = Snake.get(0).get_x();
        int y = Snake.get(0).get_y();
        if (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE) {
            if (foodX == x && foodY == y) {
                foodX = -1;
                foodY = -1;
                food_index = Math.max(0, food_index - 1);
                grid_size[y][x] = null;
                snake_grow();
                increaseScore(1);
                spawn_food();
            }
        }
    }

    public void increaseScore(int increaseBy){
        this.score += increaseBy * 10;
    }

    public void spawn_snake(){
        Snake.clear();
        Snake.add(new Head(8, 8, 3));
        Snake.add(new Body(7, 8, 1));
    }

    public void spawn_food(){
        if (food_index >= max_food) {
            return;
        }

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(GRID_SIZE);
            int y = random.nextInt(GRID_SIZE);
            if (isCellFree(x, y)) {
                grid_size[y][x] = new Food(x, y, 2);
                foodX = x;
                foodY = y;
                food_index++;
                return;
            }
        }
    }

    private boolean isCellFree(int x, int y) {
        if (x < 0 || x >= GRID_SIZE || y < 0 || y >= GRID_SIZE) {
            return false;
        }
        if (grid_size[y][x] != null) {
            return false;
        }
        for (Obj part : Snake) {
            if (part.get_x() == x && part.get_y() == y) {
                return false;
            }
        }
        return true;
    }

    public void snake_grow(){ // add a Body to the end of a Snake
        int y = Snake.get(Snake.size() - 1).get_previus_y();
        int x = Snake.get(Snake.size() - 1).get_previus_x();
        Snake.add(new Body(x, y, 1));
    }

    public void setDirection(Direction direction){
        if (direction == null) {return;}
        if (direction.isOpposite(this.direction)) {return;}
        this.direction = direction;
    }

    public void snake_move(){
        if (Snake.isEmpty()) {return;}
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

    public void syncSnakeToGrid() {
        for (int y = 0; y < grid_size.length; y++) {
            for (int x = 0; x < grid_size[0].length; x++) {
                if (grid_size[y][x] != null && grid_size[y][x].get_value() != 2) {
                    grid_size[y][x] = null;
                }
            }
        }

        for (Obj part : Snake) {
            int x = part.get_x();
            int y = part.get_y();
            if (x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE) {
                grid_size[y][x] = part;
            }
        }

        if (foodX >= 0 && foodY >= 0 && foodX < GRID_SIZE && foodY < GRID_SIZE) {
            grid_size[foodY][foodX] = new Food(foodX, foodY, 2);
        }
    }

    public ArrayList<Obj> getSnake() {return Snake;}

    public Obj[][] getGridSize() {return grid_size;}

    public int getScore(){
        return score;
    }
}

