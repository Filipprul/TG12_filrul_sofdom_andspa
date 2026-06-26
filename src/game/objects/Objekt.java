package objects;

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
