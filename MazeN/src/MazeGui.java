
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import static javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;


public class MazeGui extends JFrame {
    
    private JButton btnGenerate;
    private JButton btnSolve;
    private JComboBox box;
    private JLabel label;
    private JPanel panel;
    private Maze maze;
    
    public MazeGui() {
        initComponents();
        btnSolve.setEnabled(false);
    }

    private void initComponents() {
        panel = new JPanel();
        btnGenerate = new JButton();
        btnSolve = new JButton();
        box = new JComboBox();
        label = new JLabel();
        maze = new Maze();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        btnGenerate.setText("Generate");
        btnGenerate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnGenerateActionPerformed(e);
            }
        });

        btnSolve.setText("Solve");
        btnSolve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSolveActionPerformed(e);
            }
        });

        box.setModel(new javax.swing.DefaultComboBoxModel(new String[] 
        { "10 x 10", "15 x 15", "20 x 20", "25 x 25", "30 x 30","40 x 40" }));

        label.setText("Dimensions");

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(label)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(box, GroupLayout.PREFERRED_SIZE, 155,
                        GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerate)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSolve)
                .addContainerGap(152, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(label)
                    .addComponent(box,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 
                            GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerate)
                    .addComponent(btnSolve))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout mazeLayout = new GroupLayout(maze);
        maze.setLayout(mazeLayout);
        mazeLayout.setHorizontalGroup(
            mazeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 557, Short.MAX_VALUE)
        );
        mazeLayout.setVerticalGroup(
            mazeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );

       GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, 
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(maze,GroupLayout.DEFAULT_SIZE, 
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 
                        GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maze, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE))
        );
        pack();
    }
    private void btnSolveActionPerformed(ActionEvent evt) {

        maze.solve(this);
        
    }
    private void btnGenerateActionPerformed(ActionEvent evt) {
        
        int index = box.getSelectedIndex();
        
        switch ( index ) {
            
            case 0: 
                maze.init(10, 10); 
                maze.generate();
                break;
            case 1: 
                maze.init(15, 15);
                maze.generate(); 
                break;
            case 2: 
                maze.init(20, 20);
                maze.generate(); 
                break;
            case 3: 
                maze.init(25, 25);
                maze.generate(); 
                break;
            case 4: 
                maze.init(30, 30);
                maze.generate(); 
                break;
            case 5:
                maze.init(40, 40);
                maze.generate(); 
                break;
            
            default: 
                maze.init(20, 20); 
                maze.generate();
        }
        btnSolve.setEnabled(true);
    }
    
    public void disableSolveButton() {
        this.btnSolve.setEnabled(false);
    }  
    
}
