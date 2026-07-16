package main;
import processing.core.PApplet;
import view.game.game;
import view.login.login;
import view.Menu.menu;
import view.gameover.gameover;
import view.register.register;

public class Main extends PApplet {
    public enum State { LOGIN, MENU, GAME, REGISTER, GAMEOVER}
    State currentState = State.LOGIN;

    login loginScreen;
    game gameScreen;
    menu menuScreen;
    gameover gameoverScreen;
    register registerScreen;

    public void settings() {size(1000, 1000);}

    public void setup() {
        loginScreen = new login(this);
        gameScreen = new game(this);
        menuScreen = new menu(this);
        gameoverScreen = new gameover(this);
        registerScreen = new register(this);
    }

    public void draw() {
        switch (currentState) {
            case LOGIN:
                loginScreen.draw();
                if (loginScreen.isLoggedIn()) {
                    menuScreen.setCurrentUsername(loginScreen.getCurrentUsername());
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
                gameoverScreen.over(gameScreen.getController().getGrid().getScore());
                break;
            case REGISTER:
                registerScreen.draw();
                break;
        }
    }

    public void keyPressed() {
        if (currentState == State.GAMEOVER){
            if(key == 'r' || key == 'R'){
                gameScreen.resetGame();
                currentState = State.GAME;
            } else if (key == 'm' || key == 'M') {
                gameScreen.resetGame();
                currentState = State.MENU;}
        }

        if (currentState == State.LOGIN) {
            loginScreen.keyPressed(key);
        } else if (currentState == State.MENU) {
            menuScreen.keyPressed(key);
        } else if (currentState == State.REGISTER) {
            registerScreen.keyPressed(key);
        } else {
            gameScreen.keyPressed(key);
        }
    }

    public void setState(State state){currentState = state;}

    public void handleGameOver(int score) {
        menuScreen.addHighscore(loginScreen != null ? loginScreen.getCurrentUsername() : "Spieler", score);
        currentState = State.GAMEOVER;
    }

    public static void main(String[] args) {PApplet.main("main.Main");}
}