package core;
import objects.*;

import java.util.ArrayList;

public class Grid {

private ArrayList<objects.Objekt> Snake = new ArrayList<>(); // all data abaout the Snake "head","boddy","psoition"...
private int[][] grid_size = new int[16][16]; // game bord size 16 * 16
private String[] imput = {"up", "down", "left", "right"}; // imput neads to be implemented/defined with key imputs for this to work properly

public Grid (){}

public void spawn_snake(){} // put snake on bord Not done yet
public void spawn_food(){} // random spawn food on bord funktion Not done yet
public void snake_grow(){} // grow boddy of snake funktion Not done yet

public void snake_move(){ // neads to be conected to Steurung by replacing imput with the key imputs!
    switch(imput[i]){ // imput neads to be implemented/defined with key imputs for this to work properly
        case "up":
            Snake.get(0).up;
        case "down":
            Snake.get(0).down;
        case "left":
            Snake.get(0).left;
        case "right":
            Snake.get(0).right;
    }
    for(int i = 1; i<Snake.size();i++){
        Snake.get(i).follow(Snake.get(i-1));
    }
}
}


