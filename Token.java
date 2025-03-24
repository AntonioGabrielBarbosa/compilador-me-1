public class Token {
    final enumToken type;
    final String valor;
    final int line;

    public Token(enumToken type, String valor, int line){
        this.type = type;
        this.valor = valor;
        this.line = line;
    }

    @Override
    public String toString() {
        return "Token(" + type + ", " + valor + ")";
    }

}
