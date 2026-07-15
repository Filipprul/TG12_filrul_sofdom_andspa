package game.core;

public class GameController {
    private final Grid grid;
    private boolean running = false;

    public GameController(Grid grid) {
        this.grid = grid;
    }

    public void start() {
        running = true;
    }

    public void update() {
        if (!running) {return;}
        grid.snake_move();
        grid.syncSnakeToGrid();
        if (grid.check_colision()) {
            running = false;
        }
    }

    public Grid getGrid() {return grid;}

    public boolean isRunning() {return running;}
}
