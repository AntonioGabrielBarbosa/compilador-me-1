enum enumToken {
    // Comandos
    MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT,
    JUMP, ATTACK, DEFEND,

    // Estruturas de Controle e Keywords
    IF, ELSE, WHILE, FOR,

    // Expressões e Operadores
    HERO, ENEMY, TREASURE, TRAP, // Elementos do jogo
    NUMBER,                      // Números
    PLUS, MINUS,                 // Operadores binários
    LPAREN, RPAREN,              // Parênteses para agrupamento
    LBRACE, RBRACE,              // Chaves para blocos
    SEMICOLON,                   // Separador de comandos

    // Identificadores Genéricos
    IDENTIFIER,

    // Fim do Arquivo
    EOF

}
