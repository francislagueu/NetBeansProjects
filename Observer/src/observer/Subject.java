/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Francis
 */
public class Subject {
    
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyObservers();
    }

    public void notifyObservers() {
        observers.stream().forEach((observer) -> {
            observer.update();
        });
    }
    
    public void attach(Observer observer){
        observers.add(observer);
    }
    
}
