import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

class Lexer {
    private final String input;
    private int line = 1; // Contador de linhas

    private static final Pattern TOKEN_PATTERN = Pattern.compile(
        "\\s*(move_up|move_down|move_left|move_right|jump|attack|defend|" + 
        "if|else|while|for|hero|enemy|treasure|trap|\\d+|[a-zA-Z_][a-zA-Z0-9_]*|" +
        "\\+|\\-|\\*|/|\\(|\\)|\\{|\\}|;|\\n)"
    );

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
                line++; // Incrementa a linha ao encontrar uma quebra de linha
                continue;
            }

            enumToken type;
            switch (match) {
                case "move_up": type = enumToken.MOVE_UP; break;
                case "move_down": type = enumToken.MOVE_DOWN; break;
                case "move_left": type = enumToken.MOVE_LEFT; break;
                case "move_right": type = enumToken.MOVE_RIGHT; break;
                case "jump": type = enumToken.JUMP; break;
                case "attack": type = enumToken.ATTACK; break;
                case "defend": type = enumToken.DEFEND; break;
                case "if": type = enumToken.IF; break;
                case "else": type = enumToken.ELSE; break;
                case "while": type = enumToken.WHILE; break;
                case "for": type = enumToken.FOR; break;
                case "hero": type = enumToken.HERO; break;
                case "enemy": type = enumToken.ENEMY; break;
                case "treasure": type = enumToken.TREASURE; break;
                case "trap": type = enumToken.TRAP; break;
                case "+": type = enumToken.PLUS; break;
                case "-": type = enumToken.MINUS; break;
                case "(": type = enumToken.LPAREN; break;
                case ")": type = enumToken.RPAREN; break;
                case "{": type = enumToken.LBRACE; break;
                case "}": type = enumToken.RBRACE; break;
                case ";": type = enumToken.SEMICOLON; break;
                default:
                    if (match.matches("\\d+")) {
                        type = enumToken.NUMBER;
                    } else {
                        type = enumToken.IDENTIFIER;
                    }
                    break;
            }

            tokens.add(new Token(type, match, line));
        }

        tokens.add(new Token(enumToken.EOF, "", line)); // Token de fim de arquivo
        return tokens;
    }
}
