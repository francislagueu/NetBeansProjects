/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeloquent;

import java.util.Scanner;

/**
 *
 * @author edwige
 */
public class Driver {
    
    public static void main(String[] args){
        System.out.println("Enter the name of the database to create: ");
        Scanner scan = new Scanner(System.in);
        String db = scan.next();
        CreateDB database = new CreateDB();
        database.create(db);
    }
}
