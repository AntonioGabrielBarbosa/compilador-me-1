import java.util.*;
import java.util.regex.*;

class Lexer {
    private final String input;
    private int line = 1;
    private static final Pattern TOKEN_PATTERN = Pattern.compile(
        "\\s*(move_up|move_down|move_left|move_right|jump|attack|defend|" + 
        "if|else|while|for|hero|enemy|treasure|trap|\\d+|[a-zA-Z_][a-zA-Z0-9_]*|" +
        "\\+|\\-|\\*|/|\\(|\\)|\\{|\\}|;|\\n)"
    );
    private static final Map<String, enumToken> TOKEN_MAP = new HashMap<>();

    static {
        TOKEN_MAP.put("move_up", enumToken.MOVE_UP);
        TOKEN_MAP.put("move_down", enumToken.MOVE_DOWN);
        TOKEN_MAP.put("move_left", enumToken.MOVE_LEFT);
        TOKEN_MAP.put("move_right", enumToken.MOVE_RIGHT);
        TOKEN_MAP.put("jump", enumToken.JUMP);
        TOKEN_MAP.put("attack", enumToken.ATTACK);
        TOKEN_MAP.put("defend", enumToken.DEFEND);
        TOKEN_MAP.put("if", enumToken.IF);
        TOKEN_MAP.put("else", enumToken.ELSE);
        TOKEN_MAP.put("while", enumToken.WHILE);
        TOKEN_MAP.put("for", enumToken.FOR);
        TOKEN_MAP.put("hero", enumToken.HERO);
        TOKEN_MAP.put("enemy", enumToken.ENEMY);
        TOKEN_MAP.put("treasure", enumToken.TREASURE);
        TOKEN_MAP.put("trap", enumToken.TRAP);
        TOKEN_MAP.put("+", enumToken.PLUS);
        TOKEN_MAP.put("-", enumToken.MINUS);
        TOKEN_MAP.put("(", enumToken.LPAREN);
        TOKEN_MAP.put(")", enumToken.RPAREN);
        TOKEN_MAP.put("{", enumToken.LBRACE);
        TOKEN_MAP.put("}", enumToken.RBRACE);
        TOKEN_MAP.put(";", enumToken.SEMICOLON);
    }

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        Matcher matcher = TOKEN_PATTERN.matcher(input);

        while (matcher.find()) {
            String match = matcher.group().trim();
            if (match.isEmpty()) continue;

            if (match.equals("\n")) { 
                line++;
                continue;
            }

            enumToken type = TOKEN_MAP.getOrDefault(match, null);
            if (type == null) {
                type = match.matches("\\d+") ? enumToken.NUMBER : enumToken.IDENTIFIER;
            }
            tokens.add(new Token(type, match, line));
        }

        tokens.add(new Token(enumToken.EOF, "", line));
        return tokens;
    }
}
