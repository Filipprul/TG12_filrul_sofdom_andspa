package view.gameover;
import processing.core.PApplet;

public class gameover {
    private PApplet parent;

    public final int RESTART_X = 400;
    public final int MENU_X = 600;
    public final int BUTTON_Y = 690;
    public final int BUTTON_W = 150;
    public final int BUTTON_H = 50;

    public gameover(PApplet p){
        this.parent = p;
    }

     public void draw(int finalScore){
         parent.noStroke();
         parent.fill(0, 200);
         parent.rect(0, 0, parent.width, parent.height);
         parent.rectMode(PApplet.CENTER);
         parent.fill(30, 30, 30, 240);
         parent.stroke(255, 50);
         parent.rect(parent.width / 2, parent.height / 2, 520, 360, 25);

         parent.fill(255, 50, 50);
         parent.textSize(64);
         parent.text("GAME OVER", parent.width / 2, parent.height / 2 - 90);
         parent.fill(255);
         parent.textSize(36);
         parent.text("Score: " + finalScore, parent.width / 2, parent.height / 2 - 10);

         // Buttons zeichnen
         parent.stroke(255);
         parent.fill(50, 200, 50);
         parent.rect(RESTART_X, BUTTON_Y, BUTTON_W, BUTTON_H, 10); // Restart
         parent.fill(50, 50, 200);
         parent.rect(MENU_X, BUTTON_Y, BUTTON_W, BUTTON_H, 10);    // Menu

         parent.fill(255);
         parent.textSize(20);
         parent.text("NEUSTART", RESTART_X, BUTTON_Y);
         parent.text("MENÜ", MENU_X, BUTTON_Y);
    }

    public String mousePressed() {
        if (parent.mouseX > RESTART_X - BUTTON_W/2 && parent.mouseX < RESTART_X + BUTTON_W/2 &&
                parent.mouseY > BUTTON_Y - BUTTON_H/2 && parent.mouseY < BUTTON_Y + BUTTON_H/2) {
            return "RESTART";
        }
        if (parent.mouseX > MENU_X - BUTTON_W/2 && parent.mouseX < MENU_X + BUTTON_W/2 &&
                parent.mouseY > BUTTON_Y - BUTTON_H/2 && parent.mouseY < BUTTON_Y + BUTTON_H/2) {
            return "MENU";
        }
        return null;
    }
}