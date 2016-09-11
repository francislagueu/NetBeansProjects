
package insanity;


public class Driver {
    public static void main(String[] args){
        long time1 = System.currentTimeMillis();
        Solver solver = new Solver("four.txt");

        solver.getAllSoluions();

        long time2 = System.currentTimeMillis();
        System.out.println();

        long second = (time2-time1)/1000;
        long hour = second/3600;
        long minute = (second-(hour*3600))/60;
        long seconds = second - (hour*3600)-(minute*60);
        System.out.println("Time elapsed in = "+ hour+ ":"+ minute + ":"+seconds);
        System.exit(0);
    }
}

