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
        BinarySearchTree bst = new BinarySearchTree<Integer>();
        
        bst.insert(78);
        bst.insert(7);
        bst.insert(90);
        bst.insert(32);
        bst.insert(12);
        bst.insert(4);
        
        System.out.println(bst);
        bst.find(7);
    }
    
}
