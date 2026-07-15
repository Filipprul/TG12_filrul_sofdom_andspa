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
    private int score;

    private final int cellSize = 47;
    private final int gridSize = 18; // Aus deiner Grid.java
    private final int offsetX = (1000 - (gridSize * cellSize)) / 2;
    private final int offsetY = (1000 - (gridSize * cellSize)) / 2;

    public gui(PApplet p) {
        this.controller = new GameController(new Grid());
        this.parent = p;
        this.score = controller.getGrid().getScore();
        this.controller.start();
    }

    public void draw() {
        controller.getGrid().eat_food();
        if (parent.frameCount % 10 == 0) {
            controller.update();
        }
        if(controller.isRunning()){
            parent.background(0);
            drawGrid(parent.color(0xffa9e53d), parent.color(0xff2fd710), 47, 18, 18);
            drawSnake();
            drawFood();
        }
        drawScore();

        if (!controller.isRunning()) {
            // 1. Hintergrund-Rechteck zeichnen
            parent.rectMode(PApplet.CENTER); // Damit das Rechteck zentriert wird
            parent.fill(0, 255

            );             // Schwarz mit 150/255 Transparenz
            parent.noStroke();               // Kein Rahmen um das Rechteck
            // Zeichne ein Rechteck hinter dem Text (Breite 300, Höhe 60)
            parent.rect(parent.width/2, 450, 300, 60, 10); // Die 10 sorgt für abgerundete Ecken

            // 2. Text zeichnen
            parent.textAlign(PApplet.CENTER, PApplet.CENTER);
            parent.fill(255, 0, 0);
            parent.textSize(32);
            parent.text("Game Over", parent.width/2, 450);
        }
    }

    void drawSnake() {
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
    
    void drawGrid(int firstColor, int secondColor, int size, int nx, int ny) {
        for (int y = 0; y < ny; y++) {
            for (int x = 0; x < nx; x++) {
                farbwechsel(firstColor, secondColor);
                parent.rect(offsetX + (x * size), offsetY + (y * size), size, size);
            }
            farbwechsel(firstColor, secondColor);
        }
    }

    public int getScore() {
        return score;
    }

    void drawScore(){
        int currentScore = controller.getGrid().getScore();
        System.out.println("DEBUG - Aktueller Score im Grid: " + currentScore); // <--- HIER
        
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
}
