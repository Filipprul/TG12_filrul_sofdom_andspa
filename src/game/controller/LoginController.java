package game.controller;
import game.database.DatabaseConnector;
import game.objects.Player;

public class LoginController {
    private final DatabaseConnector db;

    public LoginController() {
        this.db = new DatabaseConnector();
    }

    public Player login(String username, String password) {
        int id = db.getPlayerId();

        if (id != -1) {
            return new Player(id, username, password, 0);
        } else {
            return null;
        }
    }
}