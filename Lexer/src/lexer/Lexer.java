package lexer;

import java.util.*;

public class Lexer {
    private String progText;  //holds the input string
    private int nextCharPtr;
    private char nextChar;

    private ArrayList<Token> tokenList;  //holds list of tokens found

    public Lexer( String p)
    {
        this.progText = p;
        this.nextCharPtr = 0;
        this.tokenList = new ArrayList<Token>();
    }

    private char getNextChar()
    {
        this.nextChar = this.progText.charAt(nextCharPtr);
        this.nextCharPtr++;
        return this.nextChar;
    }

    private void pushBackChar()
    {
        this.nextCharPtr--;
    }

    public ArrayList<Token> scan()
    {
        System.out.println("Start Scanner");
        int state = 0;

        while( nextChar != '$')
        {
            switch(state)
            {
                case 0:  //  state 0
                {
                    char x = getNextChar();
                    while( Character.isWhitespace(x) && nextCharPtr < progText.length())
                        x = getNextChar();
                    if(Character.isWhitespace(x))
                        System.out.println("Syntax error no $");
                    if( Character.isLetter(x))  state = 1;
                    else if(Character.isDigit(x)) state = 2;
                    else state = 3;

                    break;
                }
                case 1:  //state 1
                {
                    String kind;
                    Token t;
                    pushBackChar();
                    int front = nextCharPtr;
                    int end;
                    char curr = getNextChar();

                    while (Character.isLetterOrDigit(curr))
                        curr = getNextChar();

                    pushBackChar();
                    end = nextCharPtr;
                    String value = progText.substring(front, end);

                    if (value.equals("begin") || value.equals("end") || value.equals("read") || value.equals("write")) {
                        kind = value;
                        t = new Token(kind);
                    } else {
                        kind = "id";
                        t = new Token(kind, value);
                    }
                    this.tokenList.add(t);
                    state = 0;
                    break;
                }
                case 2:  //state 2
                {
                    String kind = "int";
                    Token t;
                    pushBackChar();
                    int front = nextCharPtr;
                    int end = 0;
                    char curr = getNextChar();

                    while (Character.isDigit(curr))
                        curr = getNextChar();

                    pushBackChar();
                    end = nextCharPtr;
                    String value = progText.substring(front, end);
                    t = new Token(kind, value);
                    this.tokenList.add(t);
                    state = 0;
                    break;
                }
                case 3:  //state 3
                {
                    int front = nextCharPtr--;
                    int end = nextCharPtr;
                    Token t;
                    String kind;
                    char curr = getNextChar();
                    char s = curr;

                    if (curr == ':') {
                        curr = getNextChar();
                        if (curr != '=') {
                            //System.out.println("syntax error unidentified operator '" + s +"'");
                            nextChar = '$';
                        } else {
                            curr = 'e';
                        }
                    }

                    kind = defineOperator(curr);
                    t = new Token(kind);

                    boolean check = kind.equals("error");
                    if (check) {
                        if(s!='$' && s!=' ')
                            System.out.println("syntax error unidentified operator '" +s+"'");
                        nextChar = '$';
                    } else {
                        this.tokenList.add(t);
                        state = 0;
                    }
                    break;
                }
            } //end switch statement
        }//end while
        System.out.println("Scan completed");
        return tokenList;

    }

    private String defineOperator(char curr)
    {
        String kind = "unknown";
        switch (curr)
        {
            case 'e':
                kind = "assign";
                break;
            case '+':
                kind = "plus";
                break;
            case '-':
                kind = "minus";
                break;
            case '*':
                kind = "times";
                break;
            case '/':
                kind = "divide";
                break;
            case ';':
                kind = "semicolon";
                break;
            case '(':
                kind = "lparen";
                break;
            case ')':
                kind = "rparen";
                break;
            default:
                kind = "error";
        }
        return kind;
    }
}
