package view.gui;
import game.core.Direction;
import game.core.GameController;
import game.core.Grid;
import processing.core.PApplet;
import java.awt.event.KeyEvent;

public class gui {
    private GameController controller;
    private int schwarz = 0;

    private PApplet parent;
    private int score = controller.getGrid().getScore();

    public gui(PApplet p) {
        this.controller = new GameController(new Grid());
        this.parent = p;
        this.controller.start();
    }

    public void settings() {
        parent.size(47 * 18 + 32, 47 * 18 + 24);
    }

    public void draw() {
        if (parent.frameCount % 10 == 0) {
            controller.update();
            parent.background(0);
            drawGrid(parent.color(0xffa9e53d), parent.color(0xff2fd710), 47, 18, 18);
            drawSnake();
            drawFood();
            drawScore();
        }

        if (!controller.isRunning()) {
            parent.fill(255, 0, 0);
            parent.textSize(32);
            parent.text("Game Over", 100, 200);
        }
    }

    void drawSnake() {
        var snake = controller.getGrid().getSnake();
        parent.fill(255);
        parent.circle(snake.get(0).get_x() * 47 + 34, snake.get(0).get_y() * 47 + 34, 45);
        for(int i = 1; i < snake.size(); i++){
            parent.fill(205,127,50);
            parent.circle(snake.get(i).get_x() * 47 + 34, snake.get(i).get_y() * 47 + 34, 45);
        }
    }

    void drawFood(){
        var cells = controller.getGrid().getGridSize();
        for(int py = 0; py < 18; py++){
            for(int px = 0; px < 18; px++){
                if(cells[py][px] != null && cells[py][px].get_value() == 2){
                    parent.fill(129, 0, 21);
                    parent.circle(px * 47 + 34, py * 47 + 34, 30);
                }
            }
        }
    }
    
    void drawGrid(int firstColor, int secondColor, int size, int nx, int ny) {
        for (int y = 0; y < ny; y++) {
            for (int x = 0; x < nx; x++) {
                farbwechsel(firstColor, secondColor);
                parent.rect(10 + x * size, 10 + y * size, size, size);
            }
            farbwechsel(firstColor, secondColor);
        }
    }
    void drawScore(){
        parent.text("Score:", 2, 47 * 18 + 4);
        parent.text(score, 14, 47 * 18 + 4); // der abstand zum text ist vieleicht zu klein / nicht existent
    }

    void farbwechsel(int firstColor, int secondColor) {
        if (schwarz == 0) {
            parent.fill(firstColor);
            schwarz = 1;
        } else {
            parent.fill(secondColor);
            schwarz = 0;
        }
    }

    public void keyPressed(char k) {
        if (k == 'w' || k == 'W') {controller.getGrid().setDirection(Direction.UP);
        } else if (k == 's' || k == 'S') {controller.getGrid().setDirection(Direction.DOWN);
        } else if (k == 'a' || k == 'A') {controller.getGrid().setDirection(Direction.LEFT);
        } else if (k == 'd' || k == 'D') {controller.getGrid().setDirection(Direction.RIGHT);
        }
    }
}
