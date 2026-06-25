public class Objekt {
    private int x; // x cordinate
    private int y; // y cordinate
    private int value; // value of 0 = nothing is sopoused to happen. value of 1 = is a body part. value of 2 = is food.
    private int previus_x;
    private int previus_y;

    public Objekt (int x , int y , int value){
        this.x = x;
        this.y = y;
        this.value = value;
        previus_x = x; // }
        previus_y = y; // }dont know if this works

    }
    public int get_value(){
        return value;
    }
    public int get_x(){
        return x;
    }
    public int get_y(){
        return y;
    }
    public int get_previus_y(){
        return previus_y;
    }
    public int get_previus_x(){
        return previus_x;
    }
    public void set_x(int x){
        this.x = x;
    }
    public void set_y(int y){
        this.y = y;
    }
    
}

public class Head extends Objekt{  // Head of the sake and its movement 
    private int previus_x;
    private int previus_y;

    public Head(int x,int y, int value,previus_x,previus_y){
        super(x,y,value,previus_x,previus_y)
    }
    public void up(){
        previus_y = y;
        y = y+1;
    }
    public void down(){
        previus_y = y;
        y = y-1
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
public class Body extends Objekt{ // Body of the snake and how it follows the head.
    
   public Body(int x,int y, int value,previus_x,previus_y){
        super(x,y,value,previus_x,previus_y)
    }
    public void follow(Objekt objekt){
        x = objekt.get_previus_x;
        y = objekt.get_previus_y;
    }
}

public class Food extends Objekt(){} //Not done jet


