
import java.util.ArrayList;


public class CheckingAccount extends Account implements Comparable<CheckingAccount>{

    private double totalServiceCharge;
    boolean charge_for_below_500 = false;
    private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
    private int transactionCount;
    public CheckingAccount(String acctName, double initBalance) {
        super(acctName, initBalance);
        balance = initBalance;
        totalServiceCharge = 0;
    }
    public void setBalance(double transAmt, int transCode){
        if(transCode == 1){
            balance = balance - transAmt;
            totalServiceCharge += 0.15;
            if(balance < 500 && charge_for_below_500 == false){
                charge_for_below_500 = true;
                totalServiceCharge += 5.00;
            }
            if(balance < 0){
                totalServiceCharge += 10.00;
            }
        } else if(transCode == 2){
            balance = balance + transAmt;
            totalServiceCharge += 0.10;
        } else if(transCode == 0){
            balance = balance - totalServiceCharge;
        }
    }
    
    public double getTotalServiceCharge(){
        return totalServiceCharge;
    }
    public void setServiceCharge(double currentServiceCharge, int transCode){
        if(transCode == 1){
            currentServiceCharge = 0.15;
            if(balance < 500 && charge_for_below_500 == false){
                charge_for_below_500 = true;
                currentServiceCharge = 5.00;
            }
            if(balance < 0){
                currentServiceCharge = 10.00;
            }
        } else if(transCode == 2){
            currentServiceCharge = 0.10;
        }
        totalServiceCharge = currentServiceCharge;
    }
    public void addTransaction(Transaction newTransaction){
        transactionList.add(newTransaction);
        transactionCount = transactionList.size();
    }
    public int getTransactionCount(){
        return transactionList.size();
    }
    public Transaction getTransaction(int i){
        return transactionList.get(i);
    }

    @Override
    public int compareTo(CheckingAccount account) {
        return this.getName().compareTo(account.getName());
        //To change body of generated methods, choose Tools | Templates.
    }
    
}

