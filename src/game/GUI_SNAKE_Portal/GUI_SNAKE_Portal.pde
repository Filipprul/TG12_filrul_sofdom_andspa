import game.*;
int schwarz = 0;


void setup() {
    size(47 * 18 + 24, 47 * 18 + 24);
    background(0);

    drawGrid(color(#a9e53d), color(#2fd710), 47, 18, 18);
    drawSnake();
}

void drawSnake() {
    fill(255);
    circle(34, 34, 45);
}

// renamed from draw to avoid conflict with Processing's draw()
void drawGrid(color firstColor, color secondColor, int size, int nx, int ny) {
    for (int y = 0; y < ny; y++) {
        for (int x = 0; x < nx; x++) {
            farbwechsel(firstColor, secondColor);
            rect(10 + x*size, 10 + y*size, size, size);
        }
        farbwechsel(firstColor, secondColor);
    }
}

void farbwechsel(color firstColor, color secondColor) {
    if (schwarz == 0) {
        fill(firstColor);
        schwarz = 1;
    } else {
        fill(secondColor);
        schwarz = 0;
    }
}