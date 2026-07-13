package main;
import processing.core.PApplet;
import view.gui.gui;
import view.login.login;

public class Main extends PApplet {
    
    enum State { LOGIN, GAME }
    State currentState = State.LOGIN;

    login loginScreen;
    gui game;

    public void settings() {
        size(864, 864); // Deine Größe
    }

    public void setup() {
        loginScreen = new login(this);
        game = new gui(this);
    }

    public void draw() {
        if (currentState == State.LOGIN) {
            loginScreen.draw();
            if (loginScreen.isLoggedIn()) {
                currentState = State.GAME;
            }
        } else {
            game.draw();
        }
    }

    public void keyPressed() {
        if (currentState == State.LOGIN) {
            loginScreen.keyPressed(key);
        } else {
            game.keyPressed(key);
        }
    }

    public static void main(String[] args) {
        PApplet.main("main.Main"); // Startet EIN Fenster
    }
}