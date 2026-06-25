package menu;

import java.util.Scanner;
public class menu{
    private Scanner scanner;

    public menu() {
        scanner = new Scanner(System.in);
    }

    public void zeigeMenue() {
        boolean laufend = true;

        while (laufend) {
            System.out.println("==================================================");
            System.out.println("   _____    _    _               _   _    ______ ");
            System.out.println("  / ____|  | \\ | |     /\\     |  |/  / |  ____|");
            System.out.println(" | (___ |  |  \\| |    /  \\    |  | /   | |____ ");
            System.out.println("  \\___ \\ |      |   / /  \\   |   <    |  ____|");
            System.out.println("  ____) |  | |\\  |  / ____ \\  |  | \\  | |____ ");
            System.out.println(" |_____/   |_| \\_| /_/      \\ |\\|__\\ |______|");
            System.out.println("==================================================");
            System.out.println("[1] Spiel starten");
            System.out.println("[2] Beenden");
            System.out.println("==================================================");

            String eingabe = scanner.nextLine();

            switch (eingabe) {
                case "1":
                System.out.println("\n--- Spiel wird gestartet... ---");
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
        // Hier wird die Brücke zu Andys Core geschlagen
        core.Grid grid = new core.Grid();
        core.GameController controller = new core.GameController(grid);
        controller.start();
    }
}