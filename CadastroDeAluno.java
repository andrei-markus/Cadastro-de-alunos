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

    Arquivo RelatorioAlunos = new Arquivo("Relatorio.txt");
    Arquivo RealtorioAlfabetico = new Arquivo("RelatorioAlfabetico.txt");
    Arquivo RelatorioPorCidade = new Arquivo("RelatorioPorCidade.txt");

    Arquivo DadosSalvosCSV = new Arquivo("Dados.csv");

    String dadosDosAlunos[][] = new String[100][5];

    int alunosCadastrados = carega_csv(DadosSalvosCSV, dadosDosAlunos);

    char op = ' ';

    // Loop para fazer a logica do programa
    while (op != 'x') {
      String menuInicial = "##########################\n" +
          "#      MENU PRINCIPAL    \n" +
          "#   1) Adicionar alunos     \n" +
          "#   2) Listar alunos    \n" +
          "#   3) Pesquisar aluno     \n" +
          "#   4) Gerar relatório     \n" +
          "#   x) Sair              \n" +
          "##########################\n" +
          "# Alunos " + alunosCadastrados + "/" + dadosDosAlunos.length + "\n";

      op = Entrada.leiaChar(menuInicial);

      if (op == '1') {
        // Aumenta matriz se estiver cheia
        if (alunosCadastrados >= dadosDosAlunos.length) {
          dadosDosAlunos = aumentarMatriz(dadosDosAlunos);
        }

        // usa numero de alunos como indice do novo aluno e aumenta o contador
        cadastrarAlunos(dadosDosAlunos, alunosCadastrados);
        alunosCadastrados++;

      } else if (op == '2') {
        listarAlunos(dadosDosAlunos);
      } else if (op == '3') {
        pesquisarAluno(dadosDosAlunos);
      } else if (op == '4') {
        gerarRelatorio(dadosDosAlunos, RelatorioAlunos);

        ordenarPorNome(dadosDosAlunos);
        gerarRelatorio(dadosDosAlunos, RealtorioAlfabetico);

        ordenarPorCidade(dadosDosAlunos);
        gerarRelatorio(dadosDosAlunos, RelatorioPorCidade);
      }

    }
    salvar_dados(DadosSalvosCSV, dadosDosAlunos);
  }

  public static void cadastrarAlunos(String[][] dadosDosAlunos, int index) {

    // Ler do dados do novo aluno
    String nomeDoAluno = Entrada.leiaString("Nome completo do aluno: ");
    String emailDoAluno = Entrada.leiaString("Email do aluno: ");
    String telefoneDoAluno = Entrada.leiaString("Telefone do aluno: ");
    String idadeDoAluno = Entrada.leiaString("Idade do aluno: ");
    String cidadeDoAlunoDoAluno = Entrada.leiaString("Cidade do aluno: ");

    // Salvar dados do aluno na matriz na ultima posição
    dadosDosAlunos[index][0] = nomeDoAluno;
    dadosDosAlunos[index][1] = emailDoAluno;
    dadosDosAlunos[index][2] = telefoneDoAluno;
    dadosDosAlunos[index][3] = idadeDoAluno;
    dadosDosAlunos[index][4] = cidadeDoAlunoDoAluno;

  }

  public static void listarAlunos(String[][] dadosDosAlunos) {

    System.out.println("------------------------");
    System.out.println("Todo os alunos cadastrados");

    // Percorre toda matriz
    for (int i = 0; i < dadosDosAlunos.length; i++) {

      // pega nome do alunos
      String nomeNaLista = dadosDosAlunos[i][0];

      // verifica se existe alunos é difernte de vazio
      if (nomeNaLista == null || nomeNaLista == "") {
        break;
      }

      // se existe aluno imprimi na tela
      System.out.println(i + " - " + dadosDosAlunos[i][0]);

    }
    System.out.println("------------------------");

  }

  public static void pesquisarAluno(String[][] dadosDosAlunos) {
    // Nome do aluno que deseja buscar
    String nomeDesejado = Entrada.leiaString("Nome do aluno que deseja buscar: ");

    // Vetor para salvar os dados completo do aluno
    String[] dadosDoAlunoDesejado = new String[5];

    for (int i = 0; i < dadosDosAlunos.length; i++) {

      // Salva nome por cada linha
      String nomeNaLista = dadosDosAlunos[i][0];

      if (nomeNaLista == null || nomeNaLista == "") {
        break;
      }

      // verifica se nome
      if (nomeNaLista.toUpperCase().contains(nomeDesejado.toUpperCase())) {
        // Salva nome do aluno
        dadosDoAlunoDesejado[0] = dadosDosAlunos[i][0];
        dadosDoAlunoDesejado[1] = dadosDosAlunos[i][1];
        dadosDoAlunoDesejado[2] = dadosDosAlunos[i][2];
        dadosDoAlunoDesejado[3] = dadosDosAlunos[i][3];
        dadosDoAlunoDesejado[4] = dadosDosAlunos[i][4];
        imprimirDadosDoAluno(dadosDoAlunoDesejado);
      }
    }
  }

  public static void imprimirDadosDoAluno(String[] aluno) {
    System.out.println("------------------------");
    System.out.println("Nome do aluno: " + aluno[0]);
    System.out.println("Email do aluno: " + aluno[1]);
    System.out.println("Telefone do aluno: " + aluno[2]);
    System.out.println("Idade do aluno: " + aluno[3]);
    System.out.println("Cidade do aluno: " + aluno[4]);
  }

  public static String[][] aumentarMatriz(String[][] matriz) {
    String[][] nova = new String[matriz.length * 2][5];
    for (int i = 0; i < matriz.length; i++) {
      for (int j = 0; j < matriz[i].length; j++) {
        nova[i][j] = matriz[i][j];
      }
    }
    return nova;
  }

  public static int carega_csv(Arquivo Dados, String[][] matriz) {
    int alunosCadastrados = 0;
    if (Dados.abrirLeitura()) {

      while (true) {

        String linha = Dados.lerLinha();

        if (linha == "" || linha == null) {
          break;
        }

        if (alunosCadastrados >= matriz.length) {
          matriz = aumentarMatriz(matriz);
        }

        String[] aluno = linha.split(";");

        matriz[alunosCadastrados][0] = (aluno[0]);// Aluno
        matriz[alunosCadastrados][1] = (aluno[1]);// e-mail
        matriz[alunosCadastrados][2] = (aluno[2]);// telefone
        matriz[alunosCadastrados][3] = (aluno[3]);// idade
        matriz[alunosCadastrados][4] = (aluno[4]);// cidade
        alunosCadastrados++;

      }

      Dados.fecharArquivo();
    }
    return alunosCadastrados;
  }

  public static void salvar_dados(Arquivo Dados, String[][] matriz) {

    Dados.abrirEscrita();

    for (int i = 0; i < matriz.length; i++) {

      if (matriz[i][0] != null) {
        Dados.escreverLinha(
            matriz[i][0] + ";" + matriz[i][1] + ";" + matriz[i][2] + ";" + matriz[i][3] + ";" + matriz[i][4]);
      }
    }

    Dados.fecharArquivo();
  }

  public static void gerarRelatorio(String[][] matriz, Arquivo Relatorio) {

    Relatorio.abrirEscrita();

    Relatorio.escreverLinha("------------Relatorio------------");
    for (int i = 0; i < matriz.length; i++) {

      if (matriz[i][0] == null) {
        break;
      }
      Relatorio.escreverLinha("Aluno:" + matriz[i][0] + "\t\t\t\t" + matriz[i][1] + "\t\t\t\t" + matriz[i][2] + "\t\t\t\t"
          + matriz[i][3] + "\t\t\t\t" + matriz[i][4]);
    }
    Relatorio.escreverLinha("------------------------");

    Relatorio.fecharArquivo();
  }

  public static void ordenarPorNome(String[][] matriz) {
    ordenar(matriz, 0);
  }
  public static void ordenarPorCidade(String[][] matriz) {
    ordenar(matriz, 4);
  }

  public static void ordenar(String[][] matriz, int coluna) {
    boolean flag = true;
    while (flag) {
      flag = false;
      for (int i = 1; matriz[i][0] != null; i++) {
        if (matriz[i][coluna].compareTo(matriz[i-1][coluna]) < 0) {
          flag = true;
          String[] temp = matriz[i-1];
          matriz[i-1] = matriz[i];
          matriz[i] = temp;
        }
      }
    }
  }

}