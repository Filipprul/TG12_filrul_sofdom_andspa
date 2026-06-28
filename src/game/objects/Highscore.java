package objects;

public class Highscore {
    private final Player currentPlayer;
    private final int currentScore;
    private final String date;

    public Highscore(Player player, int score) {
        this.currentPlayer = player;
        this.currentScore = score;
        this.date = new java.util.Date().toString();
    }

    public String getUsername() {return currentPlayer.getUsername();}
    public int getScore() {return currentScore;}
    public String getDate() {return date;}
}
