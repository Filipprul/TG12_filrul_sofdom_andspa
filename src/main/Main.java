package main;

import code.game.objects.Player;
import view.terminal.TerminalLogin;
import view.terminal.TerminalMenu;

public class Main {
    public static void main(String[] args) {
        boolean startGUI = false;

        if (startGUI) {
            // GUI-Startlogik hier einfügen
        } else {
            TerminalLogin login = new TerminalLogin();
            Player eingeloggterSpieler = login.zeigeLogin();

            TerminalMenu menu = new TerminalMenu(eingeloggterSpieler);
            menu.zeigeMenue();
        }
    }
}
