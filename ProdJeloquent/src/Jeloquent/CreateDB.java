/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeloquent;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author edwige
 */
public class CreateDB {
    
    private static  String database;
    private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String DB_url = "jdbc:mysql://localhost:8888/";
    private static String User;
    private static String password;
    public CreateDB(){
       CreateDB.database = "";
    }

    public static String getDatabase() {
        return database;
    }

    public static void setDatabase(String database) {
        CreateDB.database = database;
    }
    
    public void create(String db){
        Connection conn = null;
        Statement stmt = null;
        System.out.println("Enter the username: ");
        Scanner scan = new Scanner(System.in);
        User = scan.next();
        System.out.println("Enter the password: ");
        password = scan.next();
        try {
            //productClass.forName(Driver);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_url, User, password);
            System.out.println("Creating database...");
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE "+ db;
            stmt.executeQuery(sql);
            System.out.println("Database successfully created...");
        } catch (SQLException se) {
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se){
                
            }try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!!!");
    }
}
