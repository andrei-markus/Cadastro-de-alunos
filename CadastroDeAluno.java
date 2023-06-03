/**
 * CadastroDeAluno
 * 
 * Elabore um sistema de cadastro de alunos em Java. O programa deve permitir ao
 * usuário adicionar alunos, exibir a lista de alunos cadastrados, pesquisar
 * alunos por nome e gerar um relatório com os dados dos alunos em um arquivo
 * TXT. Dados mínimos: nome, e-mail, telefone, idade, cidade.
 * 
 * DESAFIO: Listar os alunos em ordem alfabética de nome e listar os alunos em
 * ordem alfabética da cidade
 * 
 */

public class CadastroDeAluno {

  public static void main(String[] args) {

    // Criar matriz com numeros de alunos inicial
    // [0] [1] [2] [3] [4]
    // [0]Aluno e-mail telefone idade cidade
    // [1]Aluno e-mail telefone idade cidade
    // ...

    // Sera 100 linhas com 5 colunas
    // Cada linha um alunos com os dados desse aluno

    int dadosDosAlunos[][] = new int[100][5];

    // char para função entrada do menu
    char op = ' ';

    // Tela do menu principal
    String menuInicial = "##########################\n" +
        "#      MENU PRINCIPAL    \n" +
        "#   1) Genrecias alunos     \n" +
        "#   2) Listar alunos ordem alfabética    \n" +
        "#   x) Sair              \n" +
        "##########################\n";

    // Loop para fazer a logica do programa
    while (op != 'x') {
      op = Entrada.leiaChar(menuInicial);
    }

  }

}