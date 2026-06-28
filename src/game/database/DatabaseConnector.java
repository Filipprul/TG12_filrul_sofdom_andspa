package database;

public class DatabaseConnector {
    public void saveHighscore(String username, int score) {
        String sql = "INSERT INTO highscores (username, score) VALUES ('" + username + "', " + score + ");";
        System.out.println("Speichere Highscore für Benutzer: " + username + " mit Punktzahl: " + score);
    }

    // Diese Methode ist noch nicht fertig.
    public int getPlayerId() {
        return 1;
    }
}