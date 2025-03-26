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

            // Inicializa a variável tokens fora do try-catch
            List<Token> tokens = null;

            // Executar análise léxica
            Lexer lexer = new Lexer(sourceCode);
            try {
                tokens = lexer.tokenize();  // Agora tokens é atribuída dentro do bloco try
                System.out.println("\nTokens gerados:");
                for (Token token : tokens) {
                    System.out.println(token);
                }
            } catch (Lexer.SyntaxErrorException e) {
                System.err.println("Erro de sintaxe durante análise léxica: " + e.getMessage());
                continue;  // Continua com o próximo caso de teste
            }

            // Se tokens não for null, faz a análise sintática
            if (tokens != null) {
                System.out.println("\nAnalisando a sintaxe...");
                try {
                    Parser parser = new Parser(tokens);
                    parser.parse();
                    System.out.println("Análise sintática concluída sem erros.");
                } catch (RuntimeException e) {
                    System.err.println("Erro de sintaxe: " + e.getMessage());
                }
            }

            System.out.println("\n========================\n");
        }
    }
}
