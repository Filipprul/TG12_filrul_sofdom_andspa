package view.register;

import data.DatabaseConnector;
import processing.core.PApplet;

public class register {
    private PApplet parent;
    private DatabaseConnector db;
    private String username = "";
    private String password = "";
    private boolean isUsernameActive = true;
    private String message = "Bitte Daten eingeben";

    public register(PApplet p){
        this.parent = p;
        this.db = new DatabaseConnector();
    }

    public void draw(){
        parent.background(20);
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);

        // Titel
        parent.fill(255);
        parent.textSize(40);
        parent.text("REGISTRIERUNG", parent.width / 2, 100);

        // Anzeige der Felder
        parent.textSize(24);
        parent.fill(isUsernameActive ? 255 : 150);
        parent.text("Username: " + username + (isUsernameActive ? "_" : ""), parent.width / 2, 250);

        parent.fill(!isUsernameActive ? 255 : 150);
        parent.text("Passwort: " + password + (!isUsernameActive ? "_" : ""), parent.width / 2, 300);

        // Statusmeldung
        parent.fill(200, 200, 0);
        parent.text(message, parent.width / 2, 400);

        parent.textSize(16);
        parent.text("Drücke TAB zum Wechseln, ENTER zum Speichern", parent.width / 2, 500);
    }

    public void keyPressed(char key) {
        if (key == PApplet.ENTER || key == '\n' || key == '\r') {
            if (db.registerUser(username, password)) {
                message = "Erfolg! Registriert.";
            } else {
                message = "Fehler! Name vergeben.";
            }
        } else if (key == PApplet.TAB) {
            isUsernameActive = !isUsernameActive;
        } else if (key == PApplet.BACKSPACE) {
            if (isUsernameActive && username.length() > 0) username = username.substring(0, username.length() - 1);
            else if (!isUsernameActive && password.length() > 0) password = password.substring(0, password.length() - 1);
        } else if (key != PApplet.CODED) {
            if (isUsernameActive) username += key;
            else password += key;
        }
    }
}
