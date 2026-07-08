package view.terminal;

import java.util.Scanner;

import code.game.core.GameController;
import code.game.core.Grid;
import code.game.objects.Player;

public class TerminalMenu{
    private Scanner scanner;
    private Player spieler;

    public TerminalMenu(Player spieler) {
        scanner = new Scanner(System.in);
        this.spieler = spieler;
    }

    public void zeigeMenue() {
        boolean laufend = true;

        while (laufend) {
            System.out.println("==================================================");
            System.out.println("   _____    _    _       _       _   _    ______ ");
            System.out.println("  / ____|  | \\ | |     /\\     |  |/  / |  ____|");
            System.out.println(" | (___ |  |  \\| |    /  \\    |  | /   | |____ ");
            System.out.println("  \\___ \\ |      |   / /  \\   |   <    |  ____|");
            System.out.println("  ____) |  | |\\  |  / ____ \\  |  | \\  | |____ ");
            System.out.println(" |_____/   |_| \\_| /_/      \\ |\\|__\\ |______|");
            System.out.println("==================================================");
            System.out.println("       Eingeloggt als : " + spieler.getUsername()   );
            System.out.println("==================================================");
            System.out.println("[1] Spiel starten");
            System.out.println("[2] Beenden");
            System.out.println("==================================================");

            String eingabe = scanner.nextLine();

            switch (eingabe) {
                case "1":
                    System.out.println("\n Spiel wird gestartet...");
                    starteSpiel();
                    break;
                case "2":
                    System.out.println("\nSpiel wird beendet. Tschüss!");
                    laufend = false;
                    break;
                default:
                    System.out.println("\nUngültige Eingabe. Bitte wähle 1 oder 2.");
                    break;
            }
        }
    }

    private void starteSpiel() {
        Grid grid = new Grid();

        TerminalGame gameView = new TerminalGame(grid);
        gameView.render();

        GameController controller = new GameController(grid);
        controller.start();

        System.out.println("Drücke ENTER, um das Spiel zu verlassen und ins Menü zurückzukehren...");
        scanner.nextLine();
    }
}