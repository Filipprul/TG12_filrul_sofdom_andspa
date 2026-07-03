//package game;
//import core.Grid;
//import Libaries.core.jar;
int schwarz=0;
void setup() {
    size(47*18+24, 47*18+24);
    background(255);

    draw(color(#a9e53d),color(#2fd710),47,18,18);
}

void draw(color firstColor, color secondColor, int size, int nx, int ny) {
    for(int y=0; y<ny; y++) {
        for(int x=0; x<nx; x++) {
            farbwechsel(firstColor, secondColor);
            rect(10+x*size, 10+y*size, size, size);
        }
        farbwechsel(firstColor, secondColor);
    }
}

void farbwechsel (color firstColor, color secondColor) {
    if (schwarz==0) {
        fill(firstColor);
        schwarz=1;
    } else {
        fill(secondColor);
        schwarz=0;
    }
}

// wert 47 als Variable Kästchensize machen
