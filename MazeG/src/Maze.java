
import java.awt.Graphics;


public class Maze {
    public static final int NUM = 20;
    public static final int WIDTH = 20;
    public static final int X = 30;
    public static final int Y = 30;
    
    public void draw(Graphics page){
        for(int i=0; i<=NUM; i++)
            page.drawLine(X, Y+i*WIDTH, X+WIDTH*NUM, Y+i*WIDTH);
        for(int i = 0; i<=NUM;i++)
            page.drawLine(X+i*WIDTH, Y, X+i*WIDTH, Y+WIDTH*NUM);
    }
          
}
