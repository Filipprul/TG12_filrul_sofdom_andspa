package game.core;

import game.objects.Body;
import game.objects.Food;
import game.objects.Head;
import game.objects.Obj;
import java.util.ArrayList;
import java.util.Random;

public class Grid {

    private static final int GRID_SIZE = 18;
    private ArrayList<Obj> Snake = new ArrayList<>(); // all data about the Snake "head","body","position"...
    private final Obj[][] grid_size = new Obj[GRID_SIZE][GRID_SIZE]; // game board size 16 * 16
    private Direction direction = Direction.UP;
    private int food_index = 0;
    private int max_food = 3;
    private int score;
    private int currentScore;

    public Grid (){
        spawn_snake();
        this.score = currentScore;
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

    public void eat_food(){ // executes the growth of the Snake and removes the "eaten" food
        int x = Snake.get(0).get_x();
        int y = Snake.get(0).get_y();
        if (grid_size[y][x] != null && grid_size[y][x].get_value() == 2){
            grid_size[y][x] = null; // Futter entfernen
            food_index --;          // Futterindex verringern
            snake_grow();           // Snake wachsen lassen
            increaseScore(1);  // Score erhöhen
        }
    }

    public void increaseScore(int increaseBy){
        currentScore = score + increaseBy;
    }

    public void spawn_snake(){
        Snake.clear();
        Snake.add(new Head(8, 8, 3));
        Snake.add(new Body(7, 8, 1));
    }

    public void spawn_food(){ // randomly spawn a food obj on the grid
        Random random = new Random();
        if (food_index < max_food +1){
            int x = random.nextInt(GRID_SIZE); // ArrayIndexOutOfBoundsException Problem weil random.nextInt(GRID_SIZE) 0-17 liefert und grid_size[18][18] nur 0-17 hat
            int y = random.nextInt(GRID_SIZE);
            if (grid_size[y][x] != null && (grid_size[y][x].get_value() == 2 || grid_size[y][x].get_value() == 1)) {
                spawn_food();
            } else {
                grid_size[y][x] = new Food(x, y, 2);
                food_index++;
            }
        }
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

    public void setDirection(String input){
        if (input == null) {return;}
        switch (input.toLowerCase()) {
            case "up" -> setDirection(Direction.UP);
            case "down" -> setDirection(Direction.DOWN);
            case "left" -> setDirection(Direction.LEFT);
            case "right" -> setDirection(Direction.RIGHT);
            default -> {}
        }
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
        // 1. Das Gitter von der alten Schlange säubern
        for (int y = 0; y < grid_size.length; y++) {
            for (int x = 0; x < grid_size[0].length; x++) {
                // Wir löschen nur, wenn es ein Schlangenteil ist (value 1 oder 3)
                // Essen (value 2) lassen wir in Ruhe!
                if (grid_size[y][x] != null && grid_size[y][x].get_value() != 2) {
                    grid_size[y][x] = null;
                }
            }
        }

        // 2. Die aktuelle Schlange aus der Liste in das Gitter zeichnen
        for (Obj part : Snake) {
            int x = part.get_x();
            int y = part.get_y();
        }
    }

    public ArrayList<Obj> getSnake() {return Snake;}

    public Obj[][] getGridSize() {return grid_size;}

    public int getScore(){
        return score;
    }

}

