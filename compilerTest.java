import java.util.List;

public class compilerTest {
    public static void main(String[] args) {
        String sourceCode = "if ( hero + id1 ) { move_up; attack; } else { move_down; }";
        
        // Executa o analisador léxico
        Lexer lexer = new Lexer(sourceCode);
        List<Token> tokens = lexer.tokenize();
        
        // Exibe os tokens gerados pelo Lexer
        System.out.println("Tokens gerados:");
        for (Token token : tokens) {
            System.out.println(token);
        }
        
        // Executa o analisador sintático
        System.out.println("\nAnalisando a sintaxe...");
        try {
            Parser parser = new Parser(tokens);
            parser.parse();
            System.out.println("Análise sintática concluída sem erros.");
        } catch (RuntimeException e) {
            System.err.println("Erro de sintaxe: " + e.getMessage());
        }
    }
}
