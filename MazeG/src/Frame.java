
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;


public class Frame extends JFrame{
    public Panel panel;
    
    Frame(Maze maze){
        final int WIDTH = 500;
        final int HEIGHT = 500;
        setSize(WIDTH, HEIGHT);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WindowClose list = new WindowClose();
        addWindowListener(list);
        panel = new Panel(maze);
        Container content = getContentPane();
        content.add(panel);
    }
    
    private class WindowClose extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }
}
