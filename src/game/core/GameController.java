package game.core;

public class GameController {
    private final Grid grid; // Speicherung Spielfeld
    private boolean running = false;

    public GameController(Grid grid) {
        this.grid = grid;
    }

    public void start() {
        grid.spawn_snake();
        grid.spawn_food();
        grid.syncSnakeToGrid();
        running = true;
    }

    public void update() {
        if (!running) {return;}

        grid.snake_move();
        grid.syncSnakeToGrid();

        if (grid.check_colision()) {
            running = false;
        }
        grid.eat_food();
    }

    public Grid getGrid() {return grid;}
    public boolean isRunning() {return running;}
}
