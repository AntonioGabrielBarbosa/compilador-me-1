import java.util.List;

public class compilerTest {
    public static void main(String[] args) {
        String[] testCases = {
            
            "if(hero) { move_up; attack; } else { move_down; }",
            "while(enemy) { defend; move_left; }",
            "for(hero; enemy; treasure) { move_right; }",
            "if(hero + treasure) { jump; }",
            
            "attack", 
            "if hero { attack; }", 
            "while (enemy) attack;", 
            "move_right!", 
            "run_away;" 
        };

        for (String sourceCode : testCases) {
            System.out.println("\n--- Testando código ---");
            System.out.println(sourceCode);

            // Executar análise léxica
            Lexer lexer = new Lexer(sourceCode);
            List<Token> tokens = lexer.tokenize();
            
            System.out.println("\nTokens gerados:");
            for (Token token : tokens) {
                System.out.println(token);
            }

            System.out.println("\nAnalisando a sintaxe...");
            try {
                Parser parser = new Parser(tokens);
                parser.parse();
                System.out.println("Análise sintática concluída sem erros.");
            } catch (RuntimeException e) {
                System.err.println("Erro de sintaxe: " + e.getMessage());
            }

            System.out.println("\n========================\n");
        }
    }
}
