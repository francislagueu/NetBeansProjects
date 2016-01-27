
public class Driver {
    public Driver(){}
    
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MazeGui().setVisible(true);
            }
        });

    }
}
