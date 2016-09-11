
package Lexer;

import java.util.*;


public class Lexer {
    
    private final String progText;
    private int nextCharPtr;
    private char nextChar;
    
    private final ArrayList<Token> tokenList;
    private boolean isToken;
    public String keyWord[] = {"begin", "end", "read", "write"};
    
    public Lexer(String p){
        progText = p;
        nextCharPtr = 0;
        tokenList = new ArrayList<>();
    }
    
    private char getNextChar(){
        nextChar = progText.charAt(nextCharPtr);
        nextCharPtr++;
        return nextChar;
    }
    
    private void pushBackChar(){
        nextCharPtr--;
    }
    
    public ArrayList<Token> scan(){
        System.out.println("Start Scanner.....");
        int state = 0;
        
        while(nextChar !='$'){
            switch(state){
                case 0:
                {
                    char x = getNextChar();
                    while(Character.isWhitespace(x) && nextCharPtr < progText.length())
                        x = getNextChar();
                    if(Character.isLetter(x))
                        state = 1;
                    else if(Character.isDigit(x))
                        state = 2;
                    else
                        state = 3;
                    break;
                }
                case 1:
                {
                    pushBackChar();
                    int m = nextCharPtr, n;
                    //int n;
                    char next = getNextChar();
                    while(Character.isLetterOrDigit(next))
                        next = getNextChar();
                    pushBackChar();
                    n = nextCharPtr;
                    String token = progText.substring(m, n);
                    isToken = false;
                    String kind = "id";
                    Token t;
                    for(int i=0; i<4; i++){
                        if(token.equals(keyWord[i])){
                            isToken = true;
                            kind = keyWord[i];
                            i=4;
                        }
                    }
                    if(isToken == false)
                        t = new Token(kind, token);
                    else
                        t = new Token(kind);
                    tokenList.add(t);
                    state = 0;
                    break;
                }
                case 2:
                {
                   pushBackChar();
                   int m = nextCharPtr, n;
                   //int n;
                   char next = getNextChar();
                   while(Character.isDigit(next))
                       next = getNextChar();
                   pushBackChar();
                   n = nextCharPtr;
                   String token = progText.substring(m, n);
                   //String kind = "int";
                   Token t = new Token("int", token);
                   tokenList.add(t);
                   state = 0;
                   break;
                }
                case 3:
                {
                    pushBackChar();
                   int m = nextCharPtr;
                   char next = getNextChar();
                   boolean t;
                   if(next == ':'){
                       next = getNextChar();
                       if(next != '=')
                           t = false;
                   }
                   int n = nextCharPtr;
                   String token = progText.substring(m, n);
                   Token tok = new Token(token);
                   t = getChar(tok);
                   if(t == true){
                       tokenList.add(tok);
                       state = 0;
                   }else{
                       System.out.println("At program index: "+ m);
                       nextChar = '$';
                   }
                   break;
                }
            }
        }
        System.out.println("Scan completed");
        
        return tokenList;
        
    }
    
    public boolean getChar(Token tok){
        String kind;
        String str = tok.kind;
        boolean t = true;
        switch(str){
            case ":=":
                kind = "assign";
                break;
            case "+":
                kind = "plus";
                break;
            case "-":
                kind = "minus";
                break;
            case "*":
                kind = "times";
                break;
            case "/":
                kind = "divide";
                break;
            case "(":
                kind = "lparen";
                break;
            case ")":
                kind = "rparen";
                break;
            case ";":
                kind = "semicolon";
                break;
            default:
            {
                kind = "unidentified";
                t = false;
                System.out.println("There is an error in the input string!!! Undefined operator found: '"+str+"'");
                break;
            }
        }
        tok.kind = kind;
        return t;
    }
    public static void main(String[] args) {
        String program = "begin read a; book := 62 ; write book end $ ";
        Lexer lex = new Lexer(program);
        System.out.println("Input to Lexer: " + program);
        ArrayList<Token> tokenList = lex.scan();
        System.out.println("Output tokenlist: " + tokenList);
        //Test Case1:
      System.out.println("Test Case 1: ");
      String test1 = " begin read a12b4c; book := 62 ; write book end  $";
      Lexer lex1 = new Lexer(test1);
      System.out.println("Input to Lexer:  " + test1);
      ArrayList<Token> tokenList1 = lex1.scan();
      System.out.println("Output tokenlist: " + tokenList1); 
    //Test Case2:
      System.out.println("Test Case 2: ");
      String test2 = " a :=b+c  5009*6 := c ; read 80 end $";
      Lexer lex2 = new Lexer(test2);
      System.out.println("Input to Lexer:  " + test2);
      ArrayList<Token> tokenList2 = lex2.scan();
      System.out.println("Output tokenlist: " + tokenList2);
    //Test Case3:
      System.out.println("Test Case 3: ");
      String test3 = " a := b & c  5 * 6 = c end $";
      Lexer lex3 = new Lexer(test3);
      System.out.println("Input to Lexer:  " + test3);
      ArrayList<Token> tokenList3 = lex3.scan();
      System.out.println("Output tokenlist: " + tokenList3);
    //Test Case 4:
      System.out.println("Test Case 4: ");
      String test4 = "a := b + (c + 1); apple end $";
      Lexer lex4 = new Lexer(test4);
      System.out.println("Input to Lexer:  " + test4);
      ArrayList<Token> tokenList4 = lex4.scan();
      System.out.println("Output tokenlist: " + tokenList4);
    //Test Case5:
      System.out.println("Test Case 5: ");
      String test5 = " a :  = b*( a + s)  $";
      Lexer lex5 = new Lexer(test5);
      System.out.println("Input to Lexer:  " + test5);
      ArrayList<Token> tokenList5 = lex5.scan();
      System.out.println("Output tokenlist: " + tokenList5);
    //Test Case6:
      System.out.println("Test Case 6: ");
      String test6 = " a := b*( a * ) + s)  ";
      Lexer lex6 = new Lexer(test6);
      System.out.println("Input to Lexer:  " + test6);
      ArrayList<Token> tokenList6 = lex6.scan();
      System.out.println("Output tokenlist: " + tokenList6);
    }

}
