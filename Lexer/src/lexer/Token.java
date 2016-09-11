
package lexer;


public class Token {
    
    String kind;
    String value;
    public Token(String t, String val){
        kind = t;
        value = val;
    }
    
    public Token(String t){
        kind = t;
        value = "";
    }
    
    public String getKind(){
        return kind;
    }
    
    public String getValue(){
        return value;
    }
    
    @Override
    public String toString(){
        String s = "" + kind;
        if(null != kind)
            switch (kind) {
            case "id":
                s = s + "(" + "\"" + value + "\"" + ")";
                break;
            case "int":
                s = s + "(" + value + ")";
                break;
        }
        return s;
    }
}

