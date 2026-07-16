package view.login;
import processing.core.PApplet;
import data.DatabaseConnector;

public class login {
    private boolean loggedIn = false;
    private PApplet parent;
    private DatabaseConnector db = new DatabaseConnector();
    private int alpha = 0;
    private String currentUsername = "";
    private String currentPassword = "";
    private boolean isUsernameActive = true;

    public login(PApplet p) {
        this.parent = p;
    }

    public void settings() {
        parent.size(47 * 18 + 24, 47 * 18 + 32);
    }

    public void draw() {
        parent.background(20);
        if (alpha < 255) alpha += 5;
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);

        // 1. WILLKOMMEN Schriftzug
        parent.fill(0, 50);
        parent.textSize(64);
        parent.text("WILLKOMMEN", parent.width / 2 + 4, 84);
        parent.fill(255, alpha);
        parent.text("WILLKOMMEN", parent.width / 2, 80);

        // 2. Eingabefelder
        drawInputField(parent.width / 2, 180, "User: " + currentUsername, isUsernameActive);
        drawInputField(parent.width / 2, 250, "Pass: " + (currentPassword.replaceAll(".", "*")), !isUsernameActive);

        // 3. Hinweis (nach unten verschoben, damit er nicht stört)
        parent.textSize(20);
        parent.fill(200, alpha);
        parent.text("Drücke TAB zum Wechseln, ENTER zum Login", parent.width / 2, 310);

        // 4. Buttons
        drawButton(parent.width / 2 - 80, 380, 140, 40, "LOGIN");
        drawButton(parent.width / 2 + 80, 380, 140, 40, "REGISTER");
    }
    

    private void drawInputField(int x, int y, String label, boolean isActive) {
        parent.rectMode(PApplet.CENTER);
        parent.stroke(isActive ? 255 : 100);
        parent.strokeWeight(2);
        parent.fill(40);
        parent.rect(x, y, 300, 50, 10);
        
        parent.fill(255);
        parent.textSize(20);
        parent.text(label, x, y);
    }

    private void drawButton(int x, int y, int w, int h, String label) {
        parent.rectMode(PApplet.CENTER);
        parent.fill(80, 80, 200);
        parent.noStroke();
        parent.rect(x, y, w, h, 8);
        
        parent.fill(255);
        parent.textSize(16);
        parent.text(label, x, y);
    }

    public void keyPressed(char key) {
        if (key == PApplet.BACKSPACE) {
            if (isUsernameActive && currentUsername.length() > 0) 
                currentUsername = currentUsername.substring(0, currentUsername.length() - 1);
            else if (!isUsernameActive && currentPassword.length() > 0) 
                currentPassword = currentPassword.substring(0, currentPassword.length() - 1);
        } else if (key == PApplet.TAB) {
            isUsernameActive = !isUsernameActive;
        } else if (key != PApplet.ENTER && key != PApplet.CODED) {
            if (isUsernameActive) currentUsername += key;
            else currentPassword += key;
        } else if (key == PApplet.ENTER) {
            loggedIn = true; 
        }
    }

    public boolean isLoggedIn() { return loggedIn; }

    public String getCurrentUsername() {
        return currentUsername;
    }
}