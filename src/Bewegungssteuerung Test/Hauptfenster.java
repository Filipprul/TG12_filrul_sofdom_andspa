import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Hauptfenster {
    public static void main(String[] args) {
        // Das Fenster erstellen
        JFrame fenster = new JFrame("Mein Snake Spiel");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setSize(400, 400); // Fenstergröße
        
        // Das Spielfeld hinzufügen
        Spielfeld feld = new Spielfeld();
        fenster.add(feld);
        
        // Steuerung hinzufügen
        Steuerung steuerung = new Steuerung(feld);
        feld.addKeyListener(steuerung);
        feld.setFocusable(true); // Damit das Spielfeld Tastatureingaben erhält
        
        // Fenster sichtbar machen
        fenster.setVisible(true);
    }
}

class Spielfeld extends JPanel {
    private static final long serialVersionUID = 1L;
    private Spieler spieler;

    public Spielfeld() {
        setBackground(Color.WHITE); // Hintergrundfarbe des Spielfelds
        // Spieler in der Mitte des Feldes erstellen
        spieler = new Spieler(175, 175, Color.BLUE);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Hintergrund zeichnen
        spieler.zeichnen(g); // Spieler zeichnen
    }
    
    public Spieler getSpieler() {
        return spieler;
    }
}
