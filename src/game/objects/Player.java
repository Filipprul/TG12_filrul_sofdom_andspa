package objects;

public class Player{
    final int id;
    final String username;
    private String passwort;
    private int highscore;

    public Player(int id, String username, String passwort, int highscore) {
        this.id = id;
        this.username = username;
        this.passwort = passwort;
        this.highscore = highscore;
    }

    public int getId() {return id;}
    public String getUsername() {return username;}
    public String getPasswort() {return passwort;}
    public int getHighscore() {return highscore;}

    public void setPasswort(String passwort) {this.passwort = passwort;}
    public void sethighscore(int highscore) {this.highscore = highscore;}
}