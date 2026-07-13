package view.login;
import processing.core.PApplet;
import data.DatabaseConnector;

public class login {
    private boolean loggedIn = false;
    private PApplet parent;
    private DatabaseConnector db = new DatabaseConnector();
    private String currentUsername = "";
    private String currentPassword = "";
    private boolean isUsernameActive = true;

    public login(PApplet p) {this.parent = p;}

    public void settings() {parent.size(47 * 18 + 24, 47 * 18 + 24);}

    // draw() ausbessern/verbessern!
    public void draw() {
        parent.background(20);
        if (alpha < 255) alpha += 5;

        parent.textAlign(PApplet.CENTER, PApplet.CENTER);

        parent.fill(0, 50);
        parent.textSize(64);
        parent.text("WILLKOMMEN", parent.width/2 + 4, 154);
        parent.fill(255, alpha);
        parent.text("WILLKOMMEN", parent.width/2, 150);

        parent.textSize(24);
        parent.fill(200, alpha);
        parent.text("Drücke ENTER zum Starten", parent.width/2, 300);

        parent.stroke(100, alpha);
        parent.line(parent.width/2 - 100, 200, parent.width/2 + 100, 200);
    }

    public void keyPressed(char key) {
        if (key == PApplet.ENTER || key == '\n' || key == '\r') {
            loggedIn = true;}
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}