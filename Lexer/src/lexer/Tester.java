
package lexer;

import java.util.*;
import java.io.*;
public class Tester {
    public static void main(String[] args) {

        //Typical Test Case –
        String program = " begin read a; book := 62 ; write book end  $";
        Lexer lex = new Lexer( program);
        System.out.println("Input to Lexer:  " + program);
        ArrayList<Token> tokenList = lex.scan();
        System.out.println("Output tokenlist: " + tokenList + "\n");

        String program2 = " a :=b+c  5009*6 := c ; read 80 end $";
        Lexer lex2 = new Lexer( program2);
        System.out.println("Input to Lexer:  " + program2);
        ArrayList<Token> tokenList2 = lex2.scan();
        System.out.println("Output tokenlist: " + tokenList2 + "\n");

        String program3 = " a := b & c  5 * 6 = c end $";
        Lexer lex3 = new Lexer( program3);
        System.out.println("Input to Lexer:  " + program3);
        ArrayList<Token> tokenList3 = lex3.scan();
        System.out.println("Output tokenlist: " + tokenList3 + "\n");

        String program4 = " a := b + (c + 1); apple end $";
        Lexer lex4 = new Lexer( program4);
        System.out.println("Input to Lexer:  " + program4);
        ArrayList<Token> tokenList4 = lex4.scan();
        System.out.println("Output tokenlist: " + tokenList4 + "\n");

        String program5 = " a :  = b*( a + s)  $";
        Lexer lex5 = new Lexer( program5);
        System.out.println("Input to Lexer:  " + program5);
        ArrayList<Token> tokenList5 = lex5.scan();
        System.out.println("Output tokenlist: " + tokenList5 + "\n");

        String program6 = " a := b*( a * ) + s)  ";
        Lexer lex6 = new Lexer( program6);
        System.out.println("Input to Lexer:  " + program6);
        ArrayList<Token> tokenList6 = lex6.scan();
        System.out.println("Output tokenlist: " + tokenList6 + "\n");
    }


}
