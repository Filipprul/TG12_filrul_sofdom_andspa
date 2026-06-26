package objects;

public class Body extends Objekt{ // Body of the snake and how it follows the head.
    private int previus_x;
    private int previus_y;
    private int x;
    private int y;
    
   public Body(int x,int y, int value,int previus_x,int previus_y){
        super(x,y,value);
        x = this.x;
        y = this.y;
        previus_x = this.previus_x;
        previus_y = this.previus_y;
        
    }
    public void follow(Objekt objekt){
        x = objekt.get_previus_x();
        y = objekt.get_previus_y();
    }
}
