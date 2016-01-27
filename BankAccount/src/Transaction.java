
import java.io.Serializable;


class Transaction implements Serializable{
    private int transactionNumber;
    private int transactionId;
    private double transactionAmount;
    
    public Transaction(int number, int id, double amount){
        transactionNumber = number;
        transactionId = id;
        transactionAmount = amount;
    }
    public int getTransactionNumber(){
        return transactionNumber;
    }
    public int getTransactionId(){
        return transactionId;
    }
    public double getTransactionAmount(){
        return transactionAmount;
    }
    
    
}
