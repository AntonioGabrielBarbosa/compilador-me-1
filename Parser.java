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
        System.out.println("Código compilado com sucesso!");
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
        consume(enumToken.LPAREN, "Esperado '('");
        expression();
        consume(enumToken.RPAREN, "Esperado ')'");
        consume(enumToken.LBRACE, "Esperado '{'");
        while (!check(enumToken.RBRACE) && !isAtEnd()) {
            statement();
        }
        consume(enumToken.RBRACE, "Esperado '}'");

        if (match(enumToken.ELSE)) {
            consume(enumToken.LBRACE, "Esperado '{'");
            while (!check(enumToken.RBRACE) && !isAtEnd()) {
                statement();
            }
            consume(enumToken.RBRACE, "Esperado '}'");
        }
    }

    private void whileStatement() {
        consume(enumToken.LPAREN, "Esperado '('");
        expression();
        consume(enumToken.RPAREN, "Esperado ')'");
        consume(enumToken.LBRACE, "Esperado '{'");
        while (!check(enumToken.RBRACE) && !isAtEnd()) {
            statement();
        }
        consume(enumToken.RBRACE, "Esperado '}'");
    }

    private void forStatement() {
        consume(enumToken.LPAREN, "Esperado '('");
        expression();
        consume(enumToken.SEMICOLON, "Esperado ';'");
        expression();
        consume(enumToken.SEMICOLON, "Esperado ';'");
        expression();
        consume(enumToken.RPAREN, "Esperado ')'");
        consume(enumToken.LBRACE, "Esperado '{'");
        while (!check(enumToken.RBRACE) && !isAtEnd()) {
            statement();
        }
        consume(enumToken.RBRACE, "Esperado '}'");
    }

    private void command() {
        if (match(enumToken.MOVE_UP, enumToken.MOVE_DOWN, enumToken.MOVE_LEFT, enumToken.MOVE_RIGHT,
                  enumToken.JUMP, enumToken.ATTACK, enumToken.DEFEND)) {
            consume(enumToken.SEMICOLON, "Esperado ';'");
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
        if (match(enumToken.HERO, enumToken.ENEMY, enumToken.TREASURE, enumToken.TRAP, enumToken.NUMBER)) {
            return;
        }
        throw error("Esperado hero, enemy, treasure, trap ou número.");
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
        if (isAtEnd()) return false;
        return tokens.get(current).type == type;
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
