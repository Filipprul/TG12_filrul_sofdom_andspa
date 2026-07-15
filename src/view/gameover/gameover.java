package view.gameover;
import game.core.GameController;
import processing.core.PApplet;

public class gameover {
    private PApplet parent;

    public gameover(PApplet p){
        this.parent = p;
    }

     public void over(int finalScore){
         // 1. Hintergrund abdunkeln (ganzes Fenster)
            parent.noStroke();
            parent.fill(0, 200);
            parent.rect(0, 0, parent.width, parent.height);

        // 2. Panel (Hintergrund-Box für den Text)
            parent.rectMode(PApplet.CENTER);
            parent.fill(30, 30, 30, 240);
            parent.stroke(255, 50);
            parent.strokeWeight(2);
            parent.rect(parent.width / 2, parent.height / 2, 520, 360, 25);

        // 3. GAME OVER Titel mit Schatten
            parent.textAlign(PApplet.CENTER, PApplet.CENTER);
            parent.fill(0, 150); // Schatten
            parent.textSize(64);
            parent.text("GAME OVER", parent.width / 2 + 4, parent.height / 2 - 86);
    
            parent.fill(255, 50, 50); // Rote Schrift
            parent.text("GAME OVER", parent.width / 2, parent.height / 2 - 90);

        // 4. Score Anzeige
            parent.fill(255);
            parent.textSize(36);
            parent.text("Dein Score: " + finalScore, parent.width / 2, parent.height / 2 - 10);

         // 5. Trennlinie
            parent.stroke(255, 100);
            parent.strokeWeight(1);
            parent.line(parent.width / 2 - 120, parent.height / 2 + 35, parent.width / 2 + 120, parent.height / 2 + 35);

        // 6. Steuerungs-Hinweise
            parent.textSize(22);
            parent.fill(200);
            parent.text("[R] Neustart    [M] Hauptmenü", parent.width / 2, parent.height / 2 + 90);
        }
}