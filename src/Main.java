import view.gui.*;
import view.terminal.*;

public class Main {
    public static void main(String[] args) {
        boolean startGUI = false;

        if (startGUI) {
            // Hier kommt der Code für die GUI-Initialisierung.
            GUI_SNAKE_Portal Gui = new GUI_SNAKE_Portal();
        } else {
            // Nicht fertige Terminal-Implementierung.
            TerminalMenu menu = new TerminalMenu();
            menu.zeigeMenue();
        }
    }
}