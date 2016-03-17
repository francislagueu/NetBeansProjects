/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

/**
 *
 * @author Francis
 */
public class ObserverDemo {
    
    public static void main(String[] args){
        Subject subject = new Subject();
        
        new BinaryObserver(subject);
        new HexaObserver(subject);
        int num = 13;
        System.out.println("First State change: "+ num);
        subject.setState(num);
        num = 11;
        System.out.println("Second State change: "+ num);
        subject.setState(num);
        num = 14;
        System.out.println("Third State change: "+ num);
        subject.setState(num);
        
    }
    
}
