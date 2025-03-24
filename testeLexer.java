import java.util.List;

public class testeLexer {
    public static void main(String[] args) {
        String code = "if ( hero + 10 ) { move_up; attack; }";
        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.tokenize();
        
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
