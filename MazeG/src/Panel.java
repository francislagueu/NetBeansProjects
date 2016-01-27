
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Panel extends JPanel{
    public Maze maze;
    
    Panel(Maze maze){
        this.maze = maze;
    }
    
    public void paintComponent(Graphics page){
        super.paintComponent(page);
        setBackground(Color.WHITE);
        page.setColor(Color.BLACK);
        maze.draw(page);
    }
}
