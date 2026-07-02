package view.terminal;
import controller.LoginController;
import java.util.Scanner;
import objects.Player;

public class TerminalLogin {
    private LoginController loginController;
    private Scanner scanner;

    public TerminalLogin() {
        this.loginController = new LoginController();
        this.scanner = new Scanner(System.in);
    }

    public Player zeigeLogin() {
        System.out.println("==================================================");
        System.out.println("                  SNAKE LOGIN                     ");
        System.out.println("==================================================");

        System.out.print("Benutzername: ");
        String username = scanner.nextLine();

        System.out.print("Passwort: ");
        String passwort = scanner.nextLine();
        System.out.println("==================================================");

        Player spieler = loginController.login(username, passwort);
        if (spieler != null) {
            System.out.println("Login erfolgreich! Willkommen, " + username + ".");
            return spieler;
        } else {
            System.out.println("\nFalscher Benutzername oder Passwort!");
            System.out.println("[1] Erneut versuchen");
            System.out.println("[2] Programm beenden");
            System.out.print("Wählen Sie eine Option: ");

            String option = scanner.nextLine();
            if (option.equals("1")) {
                return zeigeLogin();
            } else {
                System.out.println("Programm wird beendet.");
                System.exit(0);
            }
        }
        return null; // This line will never be reached, but is required to satisfy the method's return type
    }
}
