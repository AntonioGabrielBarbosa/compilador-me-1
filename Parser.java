import java.util.List;

class Parser {
    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void parse() {
        while (!isAtEnd()) {
            statement();
        }
    }

    private void statement() {
        if (match(enumToken.IF)) {
            ifStatement();
        } else if (match(enumToken.WHILE)) {
            whileStatement();
        } else if (match(enumToken.FOR)) {
            forStatement();
        } else {
            command();
        }
    }

    private void ifStatement() {
        consume(enumToken.LPAREN, "Esperado '(' após 'if'");
        expression();
        consume(enumToken.RPAREN, "Esperado ')' após condição");
        
        block(); // Processa o bloco do IF

        if (match(enumToken.ELSE)) {
            block(); // Processa o bloco do ELSE (se existir)
        }
    }

    private void whileStatement() {
        consume(enumToken.LPAREN, "Esperado '(' após 'while'");
        expression();
        consume(enumToken.RPAREN, "Esperado ')' após condição");

        block();
    }

    private void forStatement() {
        consume(enumToken.LPAREN, "Esperado '(' após 'for'");
        expression();
        consume(enumToken.SEMICOLON, "Esperado ';' após inicialização");
        expression();
        consume(enumToken.SEMICOLON, "Esperado ';' após condição");
        expression();
        consume(enumToken.RPAREN, "Esperado ')' após incremento");

        block();
    }

    private void block() {
        consume(enumToken.LBRACE, "Esperado '{' para abrir bloco");
        while (!check(enumToken.RBRACE) && !isAtEnd()) {
            statement();
        }
        consume(enumToken.RBRACE, "Esperado '}' para fechar bloco");
    }

    private void command() {
        if (match(enumToken.MOVE_UP, enumToken.MOVE_DOWN, enumToken.MOVE_LEFT, enumToken.MOVE_RIGHT,
                  enumToken.JUMP, enumToken.ATTACK, enumToken.DEFEND)) {
            consume(enumToken.SEMICOLON, "Esperado ';' após comando");
        } else {
            throw error("Comando inválido.");
        }
    }

    private void expression() {
        term();
        while (match(enumToken.PLUS, enumToken.MINUS)) {
            term();
        }
    }

    private void term() {
        if (!match(enumToken.HERO, enumToken.ENEMY, enumToken.TREASURE, enumToken.TRAP, enumToken.NUMBER)) {
            throw error("Esperado hero, enemy, treasure, trap ou número.");
        }
    }

    private boolean match(enumToken... types) {
        for (enumToken type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean check(enumToken type) {
        return !isAtEnd() && tokens.get(current).type == type;
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean isAtEnd() {
        return tokens.get(current).type == enumToken.EOF;
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private Token consume(enumToken type, String message) {
        if (check(type)) return advance();
        throw error(message);
    }

    private RuntimeException error(String message) {
        int line = tokens.get(current).line;
        return new RuntimeException("Erro de sintaxe na linha " + line + ": " + message);
    }
}
