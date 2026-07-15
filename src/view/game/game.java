package view.game;
import game.core.Direction;
import game.core.GameController;
import game.core.Grid;
import main.Main;
import processing.core.PApplet;
import view.gameover.gameover;

public class game {
    private gameover gameover;
    private GameController controller;
    private int schwarz = 0;
    private PApplet parent;
    private Main main;

    private final int cellSize = 47;
    private final int gridSize = 18; // Aus deiner Grid.java
    //private final int offsetX = (1000 - (gridSize * cellSize)) / 2;
    //private final int offsetY = (1000 - (gridSize * cellSize)) / 2;

    public game(PApplet p) {
        this.controller = new GameController(new Grid());
        this.gameover = new gameover(p);
        this.parent = p;
        this.main = (Main) p;
        this.controller.start();
    }

    public void draw() {
        parent.background(0);
        drawGrid(parent.color(0xffa9e53d), parent.color(0xff2fd710), 47, 18, 18);
        drawSnake();
        drawFood();
        drawScore();
        controller.getGrid().eat_food();

        if (parent.frameCount % 10 == 0) {
            controller.update();
        }

        if (!controller.isRunning()) {
            main.setState(Main.State.GAMEOVER);
        }
    }

    void drawSnake() {
        int offsetX = getOffsetX();
        int offsetY = getOffsetY();
        var snake = controller.getGrid().getSnake();
        parent.noStroke();

    for(int i = 0; i < snake.size(); i++){
        var part = snake.get(i);
        float posX = offsetX + (part.get_x() * cellSize) + (cellSize / 2f);
        float posY = offsetY + (part.get_y() * cellSize) + (cellSize / 2f);

        // 1. Glow-Effekt (Schein nach außen)
        parent.fill(255, 255, 255, 100);
        parent.circle(posX, posY, cellSize * 1.1f);

        // 2. Körper mit Farb-Abstufung (wird nach hinten dunkler/rötlicher)
        if (i == 0) {
            // Kopf
            parent.fill(255, 255, 0); // Gelber Kopf
            parent.circle(posX, posY, cellSize * 0.9f);

            // Augen
            parent.fill(0);
            parent.circle(posX - 8, posY - 5, 6);
            parent.circle(posX + 8, posY - 5, 6);
        } else {
            // Körper
            int red = PApplet.constrain(150 + (i * 3), 150, 220);
            parent.fill(red, 100, 50);
            parent.circle(posX, posY, cellSize * 0.85f);
        }
    }
    }

    void drawFood(){
        int offsetX = getOffsetX();
        int offsetY = getOffsetY();
        var cells = controller.getGrid().getGridSize();
        controller.getGrid().spawn_food();
        for(int py = 0; py < gridSize; py++){
            for(int px = 0; px < gridSize; px++){
                if(cells[py][px] != null && cells[py][px].get_value() == 2){
                    float posX = offsetX + (px * cellSize) + (cellSize / 2f);
                    float posY = offsetY + (py * cellSize) + (cellSize / 2f);
                    parent.fill(129, 0, 21);
                    parent.circle(posX, posY, cellSize * 0.6f); // Essen ist kleiner als Schlange
                }
            }
        }
    }

    // Ändere deine Variablen in der gui-Klasse zu dynamischen Methoden
    public int getOffsetX() {
        return (parent.width - (gridSize * cellSize)) / 2; // Hier deine Grid-Größe
    }
    public int getOffsetY() {
        return (parent.height - (gridSize * cellSize)) / 2; // Hier deine Grid-Größe
    }   

    void drawGrid(int firstColor, int secondColor, int size, int nx, int ny) {
    int offsetX = getOffsetX();
    int offsetY = getOffsetY();
    for (int y = 0; y < ny; y++) {
        for (int x = 0; x < nx; x++) {
            // Wenn x + y gerade ist, Farbe 1, sonst Farbe 2
            if ((x + y) % 2 == 0) {
                parent.fill(firstColor);
            } else {
                parent.fill(secondColor);
            }
            parent.rect(offsetX + (x * size), offsetY + (y * size), size, size);
        }
    }
}

    void drawScore(){
        int currentScore = controller.getGrid().getScore();
        parent.fill(255);
        parent.textSize(32);
        parent.text("Score: " + currentScore, 500, 50); // der abstand zum text ist vieleicht zu klein / nicht existent
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

    public void resetGame() {
        this.controller = new GameController(new Grid());
        this.controller.start();
        this.schwarz
    }

    public GameController getController() {return this.controller;}
}
