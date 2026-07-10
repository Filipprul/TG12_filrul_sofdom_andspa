package view.gui;
import processing.core.PApplet;

public class gui extends PApplet {
    int schwarz = 0;
    public static void main(String[] args) {
        PApplet.main(new String[] {"view.gui.gui"});
    }
    public void settings() {
        size(47 * 18 + 24, 47 * 18 + 24);
    }

    public void setup() {
        background(0);
        // use int for colors in Processing Java mode
        int first = color(0xffa9e53d);
        int second = color(0xff2fd710);
        drawGrid(first, second, 47, 18, 18);
        drawSnake();
    }
    void drawSnake() {
        fill(255);
        circle(Snake[0].get_x * 47 + 34, Snake[0].get_y * 47 + 34, 45);
        for(int i=1; i < Snake.size; i++){
            fill(205,127,50);
            circle(Snake[i].get_x* 47 + 34, Snake[i].get_y * 47 + 34, 45);
        }
    }

    void drawFood(){
        for(int py = 0; py < grid_size.size; py++){
            for(int px = 0 < grid_size[0].size; px++){
                if(grid_size[py][px].get_value() == 2){
                    fill(129,0,21);
                    circle(px * 47 + 34, py * 47 + 34, 30)
                }
            }
        }
    }
    
    // renamed from draw to avoid conflict with Processing's draw()
    void drawGrid(int firstColor, int secondColor, int size, int nx, int ny) {
        for (int y = 0; y < ny; y++) {
            for (int x = 0; x < nx; x++) {
                farbwechsel(firstColor, secondColor);
                rect(10 + x * size, 10 + y * size, size, size);
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
