package game;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseConnector {
    public void saveHighscore(String username, int score) {
        String sql = "INSERT INTO highscores (username, score) VALUES (?, ?);";
        System.out.println("Speichere Highscore für Benutzer: " + username + " mit Punktzahl: " + score);
    }

    public int getPlayerId() {
        return 1;
    }
}

