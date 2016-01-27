
public class MazeGui {
    
    public static void main(String[] args){
        Maze  maze = new Maze();
        Frame frame = new Frame(maze);
        frame.setTitle("MAZE");
        frame.setVisible(true);
    }
}
