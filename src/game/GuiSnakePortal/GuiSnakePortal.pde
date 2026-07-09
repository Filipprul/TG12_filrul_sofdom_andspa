//import game.GuiSnakePortal;
import processing.core.PApplet;

public class GuiSnakePortal extends PApplet {
    int schwarz = 0;
    public static void main(String[] args) {
        PApplet.main(new String[] {"game.GuiSnakePortal"});
    }
    public void settings() {
        size(47 * 18 + 24, 47 * 18 + 24);
    }

    public void setup() {
        background(0);
        // use int for colors in Processing Java mode
        int first = color(0xa9e53d);
        int second = color(0x2fd710);
        drawGrid(first, second, 47, 18, 18);
        drawSnake();
    }

    void drawSnake() {
        fill(255);
        circle(34, 34, 45);
    }

    // renamed from draw to avoid conflict with Processing's draw()
    void drawGrid(int firstColor, int secondColor, int size, int nx, int ny) {
        for (int y = 0; y < ny; y++) {
            for (int x = 0; x < nx; x++) {
                farbwechsel(firstColor, secondColor);
                rect(10 + x*size, 10 + y*size, size, size);
            }
            farbwechsel(firstColor, secondColor);
        }
    }

    void farbwechsel(int firstColor, int secondColor) {
        if (schwarz == 0) {
            fill(firstColor);
            schwarz = 1;
        } else {
            fill(secondColor);
            schwarz = 0;
        }
    }
}