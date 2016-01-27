/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edwige
 */
public class Driver {
    
    public static void main(String[] args){
        BST bst = new BST();
        
        bst.insert(54);
        bst.insert(5);
        bst.insert(90);
        bst.insert(7);
        bst.insert(12);
        bst.insert(40);
        bst.insert(34);
        
        //System.out.print(bst);
        //bst.toString();
        bst.Print();
    }
    
}
