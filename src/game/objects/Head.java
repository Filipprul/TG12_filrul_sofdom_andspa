package objects;

public class Head extends Objekt{  // Head of the sake and its movement 
    private int previus_x;
    private int previus_y;
    private int x;
    private int y;

    public Head(int x,int y, int value,int previus_x,int previus_y){
        super(x,y,value);
        x = this.x;
        y = this.y;
        previus_x = this.previus_x;
        previus_y = this.previus_y;
    }
    public void up(){
        previus_y = y;
        y = y+1;
    }
    public void down(){
        previus_y = y;
        y = y-1;
    }
    public void left(){
        previus_x = x;
        x = x-1; 
    }
    public void right(){
        previus_x = x;
        x = x+1;
    }
}
