/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insanity;

import java.util.Stack;

public class Trace extends Object {
   int array;
    int index;

    public Trace(int array, int index) {
        this.array = array;
        this.index = index;
    }

    public int getArray() {
        return array;
    }

    public void setArray(int array) {
        this.array = array;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void push(Stack stack){
        stack.push(this);
    }

    public static void push(Stack stack, Trace trace){
        stack.push(trace);
    }

    public static Trace pop(Stack stack){
        return (Trace) stack.pop();
    }
}

