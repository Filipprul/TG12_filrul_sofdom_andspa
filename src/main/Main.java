package main;
import processing.core.PApplet;
import view.gui.gui;
import view.login.login;
import view.Menu.highscoreAnzeige;

public class Main extends PApplet {
    enum State { LOGIN, GAME }
    State currentState = State.LOGIN;

    login loginScreen;
    gui game;
    highscoreAnzeige highscoreScreen;

    public void settings() {size(864, 864);}

    public void setup() {
        loginScreen = new login(this);
        game = new gui(this);
        highscoreScreen = new highscoreAnzeige(this);
    }

    public void draw() {
        if (currentState == State.LOGIN) {
            loginScreen.draw();
            if (loginScreen.isLoggedIn()) {
                currentState = State.GAME;
            }
        } else {game.draw();}
    }

    public void keyPressed() {
        if (currentState == State.LOGIN) {
            loginScreen.keyPressed(key);
        } else {game.keyPressed(key);}
    }

    public static void main(String[] args) {PApplet.main("main.Main");}
}