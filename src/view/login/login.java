package view.login;
import processing.core.PApplet;
import data.DatabaseConnector;
import main.Main;
import game.objects.Player;

public class login {
    // Konstanten für das Layout
    private static final int FIELD_WIDTH = 400;
    private static final int FIELD_HEIGHT = 60;
    private static final int BOX_SIZE = 700;

    private boolean loggedIn = false;
    private PApplet parent;
    private Main main;
    private DatabaseConnector db = new DatabaseConnector();
    private String currentUsername = "";
    private String currentPassword = "";
    private boolean isUsernameActive = true;
    private Player currentPlayer;

    public login(PApplet p) {
        this.parent = p;
        this.main = (Main) p;
    }

    // Zentrale Login-Methode (DRY-Prinzip)
    private void attemptLogin() {
        if (db.checkLogin(currentUsername, currentPassword)) {
            currentPlayer = db.getPlayer(currentUsername);
            loggedIn = true;
        } else {
            if (db.registerUser(currentUsername, currentPassword)) {
                System.out.println("Neuer User automatisch registriert!");
                currentPlayer = db.getPlayer(currentUsername);
                loggedIn = true;
            } else {
                System.out.println("Login fehlgeschlagen und Registrierung nicht möglich!");
            }
        }
    }

    public void draw() {
        parent.background(0);
        parent.rectMode(PApplet.CENTER);

        // Haupt-Box
        parent.fill(255);
        parent.noStroke();
        parent.rect(parent.width / 2, parent.height / 2, BOX_SIZE, BOX_SIZE);

        // Überschrift
        parent.fill(0);
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);
        parent.textSize(64);
        parent.text("SNAKE", parent.width / 2, 250);

        // Felder
        drawDarkInputField(parent.width / 2, 430, "Benutzername: " + currentUsername, isUsernameActive);
        drawDarkInputField(parent.width / 2, 530, "Passwort: " + currentPassword.replaceAll(".", "*"), !isUsernameActive);

        // Button
        parent.fill(0);
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);
        parent.textSize(32);
        parent.text("Anmelden", parent.width / 2, 680);
    }

    // Hilfsmethode für die dunklen Eingabefelder
    private void drawDarkInputField(int x, int y, String label, boolean isActive) {
        parent.stroke(isActive ? parent.color(255, 100, 100) : parent.color(255, 255, 255, 0));
        parent.strokeWeight(3);
        parent.fill(40);
        parent.rect(x, y, FIELD_WIDTH, FIELD_HEIGHT, 10);
        parent.fill(255);
        parent.textAlign(PApplet.LEFT, PApplet.CENTER);
        parent.textSize(24);
        parent.text(label, x - (FIELD_WIDTH/2 - 20), y);
    }

    public void keyPressed(char key) {
        if (key == PApplet.BACKSPACE) {
            if (isUsernameActive && !currentUsername.isEmpty())
                currentUsername = currentUsername.substring(0, currentUsername.length() - 1);
            else if (!isUsernameActive && !currentPassword.isEmpty())
                currentPassword = currentPassword.substring(0, currentPassword.length() - 1);
        } else if (key == PApplet.TAB) {
            isUsernameActive = !isUsernameActive;
        } else if (key >= 32 && key <= 126) { // Nur druckbare Zeichen
            if (isUsernameActive) currentUsername += key;
            else currentPassword += key;
        }
    }

    public void mousePressed() {
        int centerX = parent.width / 2;

        // Prüfen, ob ein Feld getroffen wurde
        if (isMouseOver(centerX, 430)) isUsernameActive = true;
        else if (isMouseOver(centerX, 530)) isUsernameActive = false;
        else if (isMouseOver(centerX, 680)) attemptLogin(); // Button
    }

    private boolean isMouseOver(int x, int y) {
        return parent.mouseX > (x - FIELD_WIDTH/2) && parent.mouseX < (x + FIELD_WIDTH/2) &&
                parent.mouseY > (y - FIELD_HEIGHT/2) && parent.mouseY < (y + FIELD_HEIGHT/2);
    }

    public boolean isLoggedIn() { return loggedIn; }
    public Player getCurrentPlayer() {return currentPlayer;}
    public DatabaseConnector getDatabaseConnector() { return db; }
}