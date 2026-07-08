package main;

//import game.Player;
//import game.TerminalLogin;
//import game.TerminalMenu;

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
