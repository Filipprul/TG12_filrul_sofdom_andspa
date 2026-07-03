import view.gui.GUI_SNAKE_Portal;
import view.terminal.TerminalLogin;
import view.terminal.TerminalMenu;
import objects.Player;

public class Main {
    public static void main(String[] args) {
        boolean startGUI = true;

        if (startGUI) {
            GUI_SNAKE_Portal gui = new GUI_SNAKE_Portal();
        } else {
            TerminalLogin login = new TerminalLogin();
            Player eingeloggterSpieler = login.zeigeLogin();

            TerminalMenu menu = new TerminalMenu(eingeloggterSpieler);
            menu.zeigeMenue();
        }
    }
}