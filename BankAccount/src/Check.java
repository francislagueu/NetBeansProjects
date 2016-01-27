
public class Check extends Transaction{
    private int checkNumber;

    public Check(int number, int id, double amount, int checkNumber) {
        super(number, id, amount);
        this.checkNumber = checkNumber;
    }
    public int getCheckNumber(){
       return checkNumber; 
    }
    public void setCheckNumber(int checkNumber){
        this.checkNumber = checkNumber;
    }
}
