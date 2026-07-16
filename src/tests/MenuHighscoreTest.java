import processing.core.PApplet;
import view.Menu.menu;

public class MenuHighscoreTest {
    public static void main(String[] args) {
        menu menuScreen = new menu(new PApplet());
        menuScreen.addHighscore("Alice", 120);
        menuScreen.addHighscore("Bob", 200);

        if (menuScreen.getHighscores().size() < 2) {
            throw new AssertionError("Highscores should be stored in the menu");
        }
    }
}
