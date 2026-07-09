package main;

import game.objects.Player;
import view.terminal.TerminalLogin;
import view.terminal.TerminalMenu;
import processing.core.PApplet;

public class Main {
    public static void main(String[] args) {
        boolean startGUI = false;

        if (startGUI) {
            PApplet.main("view.gui.gui");
        } else {
            TerminalLogin login = new TerminalLogin();
            Player eingelogterPlayer = (Player) login.zeigeLogin();

            TerminalMenu menu = new TerminalMenu(eingelogterPlayer);
            menu.zeigeMenue();
        }
    }
}
