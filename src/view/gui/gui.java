package view.gui;
import game.core.Direction;
import game.core.GameController;
import game.core.Grid;
import processing.core.PApplet;

public class gui extends PApplet {
    private GameController controller;
    private int schwarz = 0;
    public static void main(String[] args) {
        PApplet.main(new String[] {"view.gui.gui.gui"});
    }

    public void settings() {
        size(47 * 18 + 24, 47 * 18 + 24);
    }

    public void setup() {
        controller = new GameController(new Grid());
        controller.start();
        background(0);
    }

    public void draw() {
        if (frameCount % 10 == 0) {
            controller.update();
            background(0);
            drawGrid(color(0xffa9e53d), color(0xff2fd710), 47, 18, 18);
            drawSnake();
            drawFood();
        }

        if (!controller.isRunning()) {
            fill(255, 0, 0);
            textSize(32);
            text("Game Over", 100, 200);
            return;
        }
    }

    void drawSnake() {
        var snake = controller.getGrid().getSnake();
        fill(255);
        circle(snake.get(0).get_x() * 47 + 34, snake.get(0).get_y() * 47 + 34, 45);
        for(int i = 1; i < snake.size(); i++){
            fill(205,127,50);
            circle(snake.get(i).get_x() * 47 + 34, snake.get(i).get_y() * 47 + 34, 45);
        }
    }

    void drawFood(){
        var cells = controller.getGrid().getGridSize();
        for(int py = 0; py < 18; py++){
            for(int px = 0; px < 18; px++){
                if(cells[py][px] != null && cells[py][px].get_value() == 2){
                    fill(129, 0, 21);
                    circle(px * 47 + 34, py * 47 + 34, 30);
                }
            }
        }
    }
    
    // renamed from draw to avoid conflict with Processing's draw()
    void drawGrid(int firstColor, int secondColor, int size, int nx, int ny) {
        for (int y = 0; y < ny; y++) {
            for (int x = 0; x < nx; x++) {
                farbwechsel(firstColor, secondColor);
                rect(10 + x * size, 10 + y * size, size, size);
            }
            farbwechsel(firstColor, secondColor);
        }
    }

    void farbwechsel(int firstColor, int secondColor) {
        if (schwarz == 0) {
            fill(firstColor);
            schwarz = 1;
        } else {
            fill(secondColor);
            schwarz = 0;
        }
    }

    public void keyPressed() {
        if (key == 'w') controller.getGrid().setDirection(Direction.UP);
        else if (key == 's') controller.getGrid().setDirection(Direction.DOWN);
        else if (key == 'a') controller.getGrid().setDirection(Direction.LEFT);
        else if (key == 'd') controller.getGrid().setDirection(Direction.RIGHT);
    }
}
