package src;

import view.gui.GUI_SNAKE_Portal;
import view.terminal.TerminalMenu;

public class Main {
    public static void main(String[] args) {
        boolean startGUI = true;

        if (startGUI) {
            // Hier kommt der Code für die GUI-Initialisierung.
            GUI_SNAKE_Portal gui = new GUI_SNAKE_Portal();
        } else {
            // Nicht fertige Terminal-Implementierung.
            TerminalMenu menu = new TerminalMenu();
            menu.zeigeMenue();
        }
    }
}