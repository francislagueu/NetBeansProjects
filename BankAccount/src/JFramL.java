
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class JFramL extends JFrame{
    public JFramL(String title){
        super(title);
        FrameListener listener = new FrameListener();
        addWindowListener(listener);
    }

    private class FrameListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e){
            int confirm = -11;
            if(BankAccount.saved == false){
                confirm = JOptionPane.showConfirmDialog(null,"Would you like to save your transaction in a file?");
                if(confirm == JOptionPane.YES_OPTION){
                    BankAccount.chooseFile(2);
                }
            }
           System.out.println("WindowListener method called: windowClosed.");
           System.exit(0);
        }
        @Override
        public void windowClosed(WindowEvent e){ }
        @Override
        public void windowOpened(WindowEvent e){ }
        public void WindowIconified(WindowEvent e){ }
        @Override
        public void windowDeiconified(WindowEvent e){ }
        @Override
        public void windowActivated(WindowEvent e){ }
        @Override
        public void windowDeactivated(WindowEvent e){ }
    }
}
