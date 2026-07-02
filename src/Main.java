<<<<<<< Updated upstream
import view.gui.*;
import view.terminal.*;

public class Main {
    public static void main(String[] args) {
        boolean startGUI = false;

        if (startGUI) {
            // Hier kommt der Code für die GUI-Initialisierung.
            GUI_SNAKE_Portal Gui = new GUI_SNAKE_Portal();
=======
package src;

import view.gui.GUI_SNAKE_Portal;
import view.terminal.TerminalMenu;

public class Main {
    public static void main(String[] args) {
        boolean startGUI = true;

        if (startGUI) {
            // Hier kommt der Code für die GUI-Initialisierung.
            GUI_SNAKE_Portal gui = new GUI_SNAKE_Portal();
>>>>>>> Stashed changes
        } else {
            // Nicht fertige Terminal-Implementierung.
            TerminalMenu menu = new TerminalMenu();
            menu.zeigeMenue();
        }
    }
}