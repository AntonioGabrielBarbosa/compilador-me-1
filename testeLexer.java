import java.util.List;

public class testeLexer {
    public static void main(String[] args) {

        String[] testCases = {

            "if ( hero + 10 ) { move_up; attack; }",
            "while ( enemy - 5 ) { defend; }",
            "for ( hero; enemy; treasure ) { move_left; }",
            "if ( treasure ) { jump; }",
            "hero + enemy - trap;",
            

            "move_right!", 
            "123abc", 
            "if ( 5hero ) { attack; }", 
            "move_up ;;; attack;", 
            "run_away;", 
        };

        for (String code : testCases) {
            System.out.println("\n--- Testando código ---");
            System.out.println(code);


            Lexer lexer = new Lexer(code);
            List<Token> tokens = lexer.tokenize();

            System.out.println("\nTokens gerados:");
            for (Token token : tokens) {
                System.out.println(token);
            }

            System.out.println("\n========================\n");
        }
    }
}
