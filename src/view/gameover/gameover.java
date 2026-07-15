package view.gameover;
import game.core.GameController;
import processing.core.PApplet;

public class gameover {
    private PApplet parent;

    public gameover(PApplet p){
        this.parent = p;
    }

     public void over(int finalScore){
         // Hintergrund halbtransparent abdunkeln
         parent.fill(0, 200);
         parent.rect(0, 0, parent.width, parent.height);

         parent.textAlign(PApplet.CENTER, PApplet.CENTER);
         parent.fill(255, 0, 0);
         parent.textSize(64);
         parent.text("GAME OVER", parent.width / 2, parent.height / 2 - 50);

         parent.fill(255);
         parent.textSize(32);
         parent.text("Score: " + finalScore, parent.width / 2, parent.height / 2 + 20);
         parent.text("Drücke 'R' zum Neustart", parent.width / 2, parent.height / 2 + 80);
         parent.text("Drücke 'M' zum Munü", parent.width / 2, parent.height / 2 + 140);
     }
}