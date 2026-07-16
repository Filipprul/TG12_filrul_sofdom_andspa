package view.Menu;

import game.objects.Highscore;
import game.objects.Player;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class menu {
    private boolean highscoreVisible = false;
    private final PApplet parent;
    private int alpha = 0;
    private final List<Highscore> highscores = new ArrayList<>();
    private String currentUsername = "Spieler";

    public menu(PApplet p) {
        this.parent = p;
    }

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
        if (highscores.isEmpty()) {
            parent.text("Noch keine Scores gespeichert", parent.width/2, 280);
        } else {
            parent.text("Top 5", parent.width/2, 250);
            int startY = 285;
            int lineHeight = 34;
            for (int i = 0; i < Math.min(highscores.size(), 5); i++) {
                Highscore entry = highscores.get(i);
                parent.text((i + 1) + ". " + entry.getUsername() + " - " + entry.getScore(), parent.width/2, startY + (i * lineHeight));
            }
        }

        parent.textSize(20);
        parent.fill(200, alpha);
        parent.text("Drücke ENTER zum Starten", parent.width/2, 440);

        parent.stroke(100, alpha);
        parent.line(parent.width/2 - 100, 200, parent.width/2 + 100, 200);
    }

    public void keyPressed(char key) {
        if (key == PApplet.ENTER || key == '\n' || key == '\r') {
            highscoreVisible = true;
        }
    }

    public boolean ishighscoreVisible() {
        return highscoreVisible;
    }

    public void resetMenu() {this.highscoreVisible = false;}

    public void addHighscore(String username, int score) {
        if (username == null || username.trim().isEmpty()) {
            username = currentUsername;
        }
        if (score <= 0) return;

        highscores.add(new Highscore(new Player(0, username.trim(), "", score), score));
        highscores.sort(Comparator.comparingInt(Highscore::getScore).reversed());
        if (highscores.size() > 10) {
            highscores.subList(10, highscores.size()).clear();
        }
    }

    public List<Highscore> getHighscores() {
        return highscores;
    }

    public void setCurrentUsername(String username) {
        if (username != null && !username.trim().isEmpty()) {
            this.currentUsername = username.trim();
        }
    }
}
