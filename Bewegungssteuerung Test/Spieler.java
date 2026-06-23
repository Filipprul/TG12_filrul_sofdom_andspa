import java.awt.Color;
import java.awt.Graphics;

public class Spieler {
    private int x;
    private int y;
    private Color farbe;
    private int groesse = 30; // Größe des Spielers in Pixel

    public Spieler(int startX, int startY, Color farbe) {
        this.x = startX;
        this.y = startY;
        this.farbe = farbe;
    }

    // Spieler zeichnen
    public void zeichnen(Graphics g) {
        g.setColor(farbe);
        g.fillOval(x, y, groesse, groesse); // Zeichnet einen Kreis
    }

    // Bewegungsmethoden
    public void bewegeRechts() { x += 10; }
    public void bewegeLinks() { x -= 10; }
    public void bewegeOben() { y -= 10; }
    public void bewegeUnten() { y += 10; }
}
