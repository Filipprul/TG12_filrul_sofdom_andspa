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
        
        // 1. Überschrift
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);
        parent.fill(0, 50);
        parent.textSize(64);
        parent.text("HIGHSCORES", parent.width / 2 + 4, 84);
        parent.fill(255, alpha);
        parent.text("HIGHSCORES", parent.width / 2, 80);

        // 2. Highscore-Tabelle
        if (highscores.isEmpty()) {
            parent.fill(150, alpha);
            parent.textSize(22);
            parent.text("Noch keine Einträge gespeichert", parent.width / 2, 250);
        } else {
            int startY = 180;
            int rowHeight = 45;
            parent.rectMode(PApplet.CENTER);

            for (int i = 0; i < Math.min(highscores.size(), 5); i++) {
                Highscore entry = highscores.get(i);
                int yPos = startY + (i * rowHeight);

                // Hintergrund der Zeile
                parent.noStroke();
                parent.fill(255, 15);
                parent.rect(parent.width / 2, yPos, 360, 40, 5);

                // Text: Platzierung, Name, Score
                parent.fill(255, alpha);
                parent.textSize(22);
                
                // Nummer
                parent.textAlign(PApplet.LEFT, PApplet.CENTER);
                parent.text((i + 1) + ".", parent.width / 2 - 160, yPos);
                
                // Name
                parent.text(entry.getUsername(), parent.width / 2 - 120, yPos);
                
                // Score
                parent.textAlign(PApplet.RIGHT, PApplet.CENTER);
                parent.text(entry.getScore(), parent.width / 2 + 160, yPos);
            }
        }

        // 3. Footer
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);
        parent.textSize(20);
        parent.fill(120, alpha);
        parent.text("Drücke ENTER zum Starten", parent.width / 2, 450);
    }

    public void keyPressed(char key) {
        if (key == PApplet.ENTER || key == '\n' || key == '\r') {
            highscoreVisible = true;
        }
    }

    public boolean ishighscoreVisible() {
        return highscoreVisible;
    }

    public void resetMenu() {
        this.highscoreVisible = false;
    }

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