package game.core;

public final class Constants {
    // Spielfeld
    public static final int GRID_SIZE = 18;
    public static final int CELL_SIZE = 47;
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 1000;

    // Datenbank (Hier später besser über .env laden)
    public static final String DB_URL = "jdbc:mysql://localhost:3306/snake_game";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "IamFilipp&Rul";
}