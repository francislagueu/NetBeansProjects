
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class EOptionsFrame extends JFramL{
    private  JMenu fileMenu;
    private  JMenuItem readFile;
    private  JMenuItem writeFile;
    private  JMenu accountMenu;
    private  JMenuItem addAccount;
    private  JMenuItem Name;
    private  JMenuItem listTransaction;
    private  JMenuItem listCheck;
    private  JMenuItem listDeposit;
    private  JMenuItem findAccount;
    private  JMenu transaction;
    private  JMenuItem addTransaction;
    private  JMenuBar bar;
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;

    public EOptionsFrame(String title) {
        super(title);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fileMenu = new JMenu("File");
        EOptionsFrame.MenuListener ml = new EOptionsFrame.MenuListener();
        readFile = new JMenuItem("Read From File");
        readFile.addActionListener(ml);
        fileMenu.add(readFile);
        
        writeFile = new JMenuItem("Write to File");
        writeFile.addActionListener(ml);
        fileMenu.add(writeFile);
        
        accountMenu = new JMenu("Accounts");
        
        addAccount = new JMenuItem("Add New Account");
        addAccount.addActionListener(ml);
        accountMenu.add(addAccount);
        
        Name = new JMenuItem("List of account holders");
        Name.addActionListener(ml);
        accountMenu.add(Name);
        
        listTransaction = new JMenuItem("List All Account Transactions");
        listTransaction.addActionListener(ml);
        accountMenu.add(listTransaction);
        
        listCheck = new JMenuItem("List All Checks");
        listCheck.addActionListener(ml);
        accountMenu.add(listCheck);
        
        listDeposit = new JMenuItem("List All Deposits");
        listDeposit.addActionListener(ml);
        accountMenu.add(listDeposit);
        
        findAccount = new JMenuItem("Find An Account");
        findAccount.addActionListener(ml);
        accountMenu.add(findAccount);
        
        transaction = new JMenu("Transactions");
        
        addTransaction = new JMenuItem("Add Transactions");
        addTransaction.addActionListener(ml);
        transaction.add(addTransaction);
        
        bar = new JMenuBar();
        bar.add(fileMenu);
        bar.add(accountMenu);
        bar.add(transaction);
        setJMenuBar(bar);
    }

    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {   
           String source = event.getActionCommand();
           
           if(source.equals("Add Transactions")){
               BankAccount.processTransaction();
           } else if(source.equals("List All Account Transactions")){
               BankAccount.listTransactions();
           } else if(source.equals("List All Checks")){
               BankAccount.listChecks();
           } else if(source.equals("List All Deposits")){
               BankAccount.listDeposits();
           } else if(source.equals("Read From File")){
               BankAccount.readTransaction();
           } else if(source.equals("Write to File")){
               BankAccount.writeTransaction();
           } else if(source.equals("Add New Account")){
               BankAccount.addAccount();
           } else if(source.equals("Find An Account")){
               BankAccount.findAccount();
           } else if(source.equals("List of account holders")){
               BankAccount.printAccountNames();
           }
        }
    }
    
}
