


import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class BankAccount {
    static boolean saved;
    private static EOptionsFrame frame;
    private static JTextArea ta;
    private static String fileName;
    public static CheckingAccount checkingAccount;
    private static ArrayList<CheckingAccount> accountList;
    public static Check check;

    public static void main(String[] args) {
        frame = new EOptionsFrame("Checking Account Operations");
        ta = new JTextArea(30, 80);
        ta.setFont(new Font("Monospaced", Font.PLAIN, 15));
        
        frame.getContentPane().add(ta);
        frame.pack();
        frame.setVisible(true);
    }

    static void chooseFile(int ioOption) {
       int status;
       JFileChooser chooser = new JFileChooser();
       if(ioOption == 1){
           status = chooser.showOpenDialog(null);
       } else {
           status = chooser.showSaveDialog(null);
       }
       if(status == JFileChooser.APPROVE_OPTION){
           File file = chooser.getSelectedFile();
           fileName = file.getPath();
       }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void processTransaction() {
       String transactionAmount, message;
       double transAmount;
       int transCode, checkNumber;
       double serviceCharge = 0;
       String Warning;
       boolean charge_for_below_500 = false;
       
       NumberFormat fmt = NumberFormat.getCurrencyInstance();
       frame.setVisible(false);
       do{
           transCode = getTransCode();
           if(transCode == 1){
               checkNumber = getCheckNumber();
               transAmount = getTransAmt();
               
               checkingAccount.setBalance(transAmount, transCode);
               message = "Transaction: Check #" + checkNumber+ " in amount of "+ fmt.format(transAmount)+ "\n"
                       +"Current Balance: " + fmt.format(checkingAccount.getBalance()) + "\n"
                       +"Service charge: check --- charge "+ fmt.format(0.15) + "\n";
               if(checkingAccount.getBalance() < 50){
                   Warning = "Warning this transaction caused your balance to go below 50";
                   JOptionPane.showMessageDialog(null, Warning, "Warning", JOptionPane.WARNING_MESSAGE);
                   
               }
               if(checkingAccount.getBalance() < 500 && charge_for_below_500 == false){
                  charge_for_below_500 = true;
                  message += "Service charge below " + fmt.format(500) + " charge " + fmt.format(5.00) + "\n";
                  checkingAccount.addTransaction(new Transaction(3, checkingAccount.getTransactionCount(), 5.00));
                  saved = false;
               }
               if(checkingAccount.getBalance() < 0){
                  
                  message += "Service charge below " + 0.00 + " charge " + fmt.format(10.00) + "\n";
                  checkingAccount.addTransaction(new Transaction(3, checkingAccount.getTransactionCount(), 5.00));
                  saved = false;
               }
               message += "Total Service Charge: " +fmt.format(checkingAccount.getTotalServiceCharge());
               
               check = new Check(1, transAmount, checkingAccount.getTransactionCount(), checkNumber);
               checkingAccount.addTransaction(check);
           }
       } while(transCode != 0);
       
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void listTransactions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void listChecks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void listDeposits() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public static int getTransCode() {
        return Integer.parseInt(JOptionPane.showInputDialog("0-> End" + "\n" + "1-> Check" + "\n" + "2-> Deposit" + "\n" + "Enter the transaction code: "));
    }

    public static String getName() {
        return JOptionPane.showInputDialog("Enter the account holder name: ");
    }

    public static double getTransAmt() {
        return Double.parseDouble(JOptionPane.showInputDialog("Enter transaction amount: "));
    }

    public static int getCheckNumber() {
        return Integer.parseInt(JOptionPane.showInputDialog("Enter the check number: "));
    }

    public static double processCheck(double currentBalance, double transAmt) {
        return currentBalance - transAmt;
    }

    public static double processDeposit(double currentBalance, double transAmt) {
        return currentBalance + transAmt;
    }
    public static void readTransaction() {
      frame.setVisible(false);
      chooseFile(1);
      try{
          FileInputStream fis = new FileInputStream(fileName);
          ObjectInputStream in = new ObjectInputStream(fis);
          checkingAccount = (CheckingAccount) in.readObject();
          accountList = (ArrayList<CheckingAccount>) in.readObject();
          in.close();
          saved = true;
      }catch(ClassNotFoundException e){
          System.out.println(e);
      }catch(IOException e){
          System.out.println(e);
      }
      frame.setVisible(true);
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void writeTransaction() {
        frame.setVisible(false);
        chooseFile(2);
        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(checkingAccount);
            out.writeObject(accountList);
            out.close();
            saved = true;
        }catch(IOException e){
            System.out.println(e);
        }
        frame.setVisible(true);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void addAccount() {
       frame.setVisible(false);
       String newName = getName();
       double initialBalance = Double.parseDouble(JOptionPane.showInputDialog("Enter the initial balance of the account: "));
       checkingAccount = new CheckingAccount(newName, initialBalance);
       accountList.add(checkingAccount);
       frame.setVisible(true);
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void findAccount() {
        frame.setVisible(false);
        String name, message;
        name = JOptionPane.showInputDialog("Enter the Account Name: ");
        for(int i=0; i<accountList.size(); i++){
            if(name.equals(accountList.get(i).getName())){
                checkingAccount = accountList.get(i);
                ta.setText("Account holder name found: " + name);
                frame.setVisible(true);
                return;
            }
        }
        frame.setVisible(true);
        ta.setText("Couldn't find the account holder name " + name);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void printAccountNames() {
        String message = "";
        Sort();
        for(int i = 0; i < accountList.size(); i++){
            message += accountList.get(i).getName()+"\n";
        }
        ta.setText(message);

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void Sort(){
        Collections.sort(accountList);
    }
    public String toString(){
        String message = "";
        message += "";
        return message;
    }
    
    
}
