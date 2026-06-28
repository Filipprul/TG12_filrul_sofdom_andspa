package controller;

import database.DatabaseConnector;
import objects.Player;

public class LoginController {
    private DatabaseConnector db;

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
