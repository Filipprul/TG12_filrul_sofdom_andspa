package main;
import game.objects.Player;
import processing.core.PApplet;
import view.game.game;
import view.login.login;
import view.menu.menu;
import view.gameover.gameover;

public class Main extends PApplet {
    public enum State { LOGIN, MENU, GAME, GAMEOVER}
    State currentState = State.LOGIN;

    login loginScreen;
    game gameScreen;
    menu menuScreen;
    gameover gameoverScreen;

    public void settings() {size(1000, 1000);}

    public void setup() {
        loginScreen = new login(this);
        gameScreen = new game(this);
        menuScreen = new menu(this);
        gameoverScreen = new gameover(this);
    }

    public void draw() {
        switch (currentState) {
            case LOGIN:
                loginScreen.draw();
                if (loginScreen.isLoggedIn()) {
                    Player p = loginScreen.getCurrentPlayer();
                    menuScreen.setCurrentPlayer(p);
                    currentState = State.MENU;
                }
                break;
            case MENU:
                menuScreen.draw();
                if (menuScreen.ishighscoreVisible()){
                    currentState = State.GAME;
                    menuScreen.resetMenu();
                }
                break;
            case GAME:
                gameScreen.draw();
                break;
            case GAMEOVER:
                gameoverScreen.draw(gameScreen.getController().getGrid().getScore());
                break;
        }
    }

    public void keyPressed() {
        if (currentState == State.LOGIN) {
            loginScreen.keyPressed(key);
        } else if (currentState == State.GAMEOVER) {
            if (key == 'r' || key == 'R') {
                gameScreen.resetGame();
                currentState = State.GAME;
            } else if (key == 'm' || key == 'M') {
                gameScreen.resetGame();
                currentState = State.MENU;
            }
        } else if (currentState == State.GAME) {
            gameScreen.keyPressed(key);
        }
    }

    public void mousePressed() {
        switch (currentState) {
            case LOGIN:
                loginScreen.mousePressed();
                break;
            case MENU:
                menuScreen.mousePressed();
                break;
            case GAMEOVER:
                String action = gameoverScreen.mousePressed();
                if ("RESTART".equals(action)) {
                    gameScreen.resetGame();
                    currentState = State.GAME;
                } else if ("MENU".equals(action)) {
                    gameScreen.resetGame();
                    currentState = State.MENU;
                }
                break;
        }
    }

    public void setState(State state){currentState = state;}

    public void handleGameOver(int score) {
        Player p = loginScreen.getCurrentPlayer();
        if (score > p.getHighscore()) {
            loginScreen.getDatabaseConnector().updateHighscore(p.getId(), score);
            p.setHighscore(score);
        }
        currentState = State.GAMEOVER;
    }

    public static void main(String[] args) {PApplet.main("main.Main");}
}