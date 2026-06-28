package objects;

public class Highscore {
    private final Player currentPlayer;
    private final int currentScore;
    private final String date;

    public Highscore(Player player) {
        this.currentPlayer = player;
        this.currentScore = 0;
        this.date = new java.util.Date().toString();
    }

    public String getUsername() {return currentPlayer.getUsername();}
    public int getScore() {return currentScore;}
    public String getDate() {return date;}
}
