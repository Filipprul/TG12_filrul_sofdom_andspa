package view.menu;
import processing.core.PApplet;

public class highscoreAnzeige {

    private boolean highscoreVisible = false;
    private PApplet parent;
    private int alpha = 0;

    public highscoreAnzeige(PApplet p) {
        this.parent = p;
    }

    public void settings() {parent.size(47 * 18 + 24, 47 * 18 + 24);}

    public void draw() {
        parent.background(20);
        if (alpha < 255) alpha += 5;
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);

        parent.fill(0, 50);
        parent.textSize(64);
        parent.text("HIGHSCORES", parent.width/2 + 4, 154);
        parent.fill(255, alpha);
        parent.text("HIGHSCORES", parent.width/2, 150);

        parent.textSize(24);
        parent.fill(200, alpha);
        parent.text("-- | -- | --", parent.width/2, 300);

        parent.stroke(100, alpha);
        parent.line(parent.width/2 - 100, 200, parent.width/2 + 100, 200);
    
        
    }
    public void keyPressed(char key) {
        if (key == PApplet.ENTER || key == '\n' || key == '\r') {
            highscoreVisible = true;}
    }

    public boolean ishighscoreVisible() {
        return highscoreVisible;
    }

}
