
public class Grid {

private Arraylist<> Snake = new Arraylist<3>; // all data abaout the Snake "head","boddy","psoition"...
private int[][] grid_size = new int[16][16]; // game bord size 16 * 16

public Grid (){}

public void spawn_snake(){} // put snake on bord Not done yet
public void spawn_food(){} // random spawn food on bord funktion Not done yet
public void snake_grow(){} // grow boddy of snake funktion Not done yet
}

public void snake_move(){ // neads to be conected to Steurung by replacing imput with the key imputs!
    switch(imput){ // imput neads to be implemented/defined with key imputs for this to work properly
        case up:
            Snake[0].up;
        case down:
            Snake[0].down;
        case left:
            Snake[0].left;
        case right:
            Snake[0].right;
    }
    for(int i = 1; i<Snake.length;i++){
        Snake[i].follow(Snake[i-1]);
    }
}
