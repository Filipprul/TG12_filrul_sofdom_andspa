import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Steuerung implements KeyListener {
    private Spielfeld spielfeld;
    
    public Steuerung(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        // Pfeiltasten oder WASD
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            spielfeld.getSpieler().bewegeLinks();
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            spielfeld.getSpieler().bewegeRechts();
        } else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            spielfeld.getSpieler().bewegeOben();
        } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            spielfeld.getSpieler().bewegeUnten();
        }
        
        spielfeld.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Nicht benötigt für dieses Projekt
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nicht benötigt für dieses Projekt
    }
}
