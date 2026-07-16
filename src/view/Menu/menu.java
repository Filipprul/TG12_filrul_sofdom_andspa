package view.menu;
import game.objects.Player;
import main.Main;
import processing.core.PApplet;

public class menu {
    private Main main;
    private boolean highscoreVisible = false;
    private final PApplet parent;
    private Player currentPlayer;

    public menu(PApplet p) {
        this.parent = p;
        this.main = (Main) p;
    }

    public void draw() {
        parent.background(0); // Schwarzer Hintergrund

        // 1. Weiße Box mit lila Rahmen
        parent.rectMode(PApplet.CENTER);
        parent.fill(255);
        parent.rect(parent.width / 2, parent.height / 2, 700, 700);

        // 2. Spielername
        parent.fill(0);
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);
        parent.textSize(64);
        String name = (currentPlayer != null) ? currentPlayer.getUsername() : "PLAYER";
        parent.text(name, parent.width / 2, 250);

        // 3. Highscore "Button"
        parent.noStroke();
        parent.fill(40);
        parent.rect(parent.width / 2, 500, 400, 60, 15);

        parent.fill(255);
        parent.textSize(32);
        int score = (currentPlayer != null) ? currentPlayer.getHighscore() : 0;
        parent.text("HIGHSCORE: " + score, parent.width / 2, 500);

        // 4. "GAME" Text
        parent.fill(0);
        parent.textSize(48);
        parent.text("GAME", parent.width / 2, 650);
    }

    public void mousePressed() {
        if (parent.mouseX > (parent.width/2 - 100) && parent.mouseX < (parent.width/2 + 100) &&
                parent.mouseY > 600 && parent.mouseY < 700) {

            highscoreVisible = true; // Löst in Main den Wechsel zu State.GAME aus
        }
    }

    public boolean ishighscoreVisible() { return highscoreVisible; }
    public void resetMenu() { this.highscoreVisible = false; }
    public void setCurrentPlayer(Player p) { this.currentPlayer = p; }
}
