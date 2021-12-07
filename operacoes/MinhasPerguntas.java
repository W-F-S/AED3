package operacoes;

import java.util.Scanner;
import entidades.perguntas.*;

public class MinhasPerguntas {

  private static Scanner console = new Scanner(System.in);
  private static ArquivoPergunta arqPerguntas;

  // --------------------------------------------------------
  // MINHAS PERGUNTAS - Controle de perguntas do próprio usuário
  // --------------------------------------------------------
  public static void menu() throws Exception {

    arqPerguntas = new ArquivoPergunta("dados/perguntas");

    int opcao;
    do {
      System.out.println("\n\nPERGUNTAS 1.0");
      System.out.println("=============");
      System.out.println("\nINÍCIO > MINHA ÁREA > MINHAS PERGUNTAS");
      System.out.println("\n1) Listar");
      System.out.println("2) Incluir");
      System.out.println("3) Alterar");
      System.out.println("4) Arquivar");
      System.out.println("\n0) Retornar");

      System.out.print("\nOpcao: ");
      try {
        opcao = Integer.valueOf(console.nextLine());
      } catch (NumberFormatException e) {
        opcao = -1;
      }

      switch (opcao) {
      case 1:
        listarMinhasPerguntas();
        break;
      case 2:
        incluirMinhaPergunta();
        break;
      case 3:
        alterarMinhaPergunta();
        break;
      case 4:
        arquivarMinhaPergunta();
        break;
      case 0:
        break;
      default:
        System.out.println("Opção inválida");
      }

    } while (opcao != 0);

    arqPerguntas.close();

  }

  // --------------------------------------------------------
  // LISTAR MINHAS PERGUNTAS
  // --------------------------------------------------------
  public static void listarMinhasPerguntas() throws Exception {
    System.out.println("\nMINHAS PERGUNTAS");
    Pergunta[] perguntas = arqPerguntas.buscaPorUsuario(Utils.usuarioGlobal());
    int i = 0; 
    if (perguntas.length == 0) {
      Utils.pausa("Nenhuma pergunta encontrada!");
     return;
    }
    for (Pergunta p : perguntas) {
      System.out.println("\n" + (++i) + ") ");
      InterfacesPergunta.mostraPergunta(p);
    }

    Utils.pausa();
    return;
  }

  // --------------------------------------------------------
  // INCLUIR MINHA PERGUNTA
  // --------------------------------------------------------
  public static void incluirMinhaPergunta() throws Exception {
    System.out.println("\nNOVA PERGUNTA");
    String pergunta, palavrasChave;
    try {
      pergunta = InterfacesPergunta.lePergunta();
      palavrasChave = InterfacesPergunta.lePalavrasChave();
    } catch (Exception e) {
      Utils.pausa("Operação cancelada");
      return;
    }

    Pergunta p = new Pergunta(Utils.usuarioGlobal(), pergunta, palavrasChave);

    System.out.print("\nConfirma inclusão? (S/N) ");
    char confirma = console.nextLine().charAt(0);
    if (confirma == 's' || confirma == 'S') {
      arqPerguntas.create(p);
      Utils.pausa("Pergunta cadastrada!");
    } else {
      Utils.pausa("Inclusão cancelada!");
    }
    return;
  }

  // --------------------------------------------------------
  // ALTERAR MINHA PERGUNTA
  // --------------------------------------------------------
  public static void alterarMinhaPergunta() throws Exception {
    System.out.println("\nMINHAS PERGUNTAS");
    Pergunta[] perguntas = arqPerguntas.buscaPorUsuario(Utils.usuarioGlobal(), true);
    if (perguntas.length == 0) {
      Utils.pausa("Nenhuma pergunta encontrada!");
      return;
    }
    int i = 0;
    for (Pergunta p : perguntas) {
      System.out.println("\n" + (++i) + ") ");
      InterfacesPergunta.mostraPergunta(p);
    }

    System.out.print("\nSelecione a pergunta que deseja alterar: ");
    String sel = console.nextLine();
    if (sel.compareTo("") == 0)
      return;
    int nPergunta = Integer.valueOf(sel);
    if (nPergunta <= 0 || nPergunta > perguntas.length) {
      return;
    }

    Pergunta p = perguntas[nPergunta - 1];
    InterfacesPergunta.mostraPerguntaSelecionada(p);

    String pergunta = "", palavrasChave = "";
    System.out.println("\nNOVOS DADOS (deixe em branco para não mudar)");

    pergunta = InterfacesPergunta.lePergunta(false);
    palavrasChave = InterfacesPergunta.lePalavrasChave(false);
    if (pergunta.length() == 0 && palavrasChave.length() == 0) {
      Utils.pausa("Nenhuma alteração realizada");
      return;
    }

    if (pergunta.length() > 0)
      p.setPergunta(pergunta);
    if (palavrasChave.length() > 0)
      p.setPalavrasChave(palavrasChave);

    System.out.print("\nConfirma alteração? (S/N) ");
    char confirma = console.nextLine().charAt(0);
    if (confirma == 's' || confirma == 'S') {
      arqPerguntas.update(p);
      Utils.pausa("Pergunta alterada!");
    } else {
      Utils.pausa("Alteração cancelada!");
    }
    return;
  }

  // --------------------------------------------------------
  // ARQUIVAR MINHA PERGUNTA
  // --------------------------------------------------------
  public static void arquivarMinhaPergunta() throws Exception {
    System.out.println("\nMINHAS PERGUNTAS");
    Pergunta[] perguntas = arqPerguntas.buscaPorUsuario(Utils.usuarioGlobal(), true);
    int i = 0;
    if (perguntas.length == 0) {
      Utils.pausa("Nenhuma pergunta encontrada!");
      return;
    }
    for (Pergunta p : perguntas) {
      System.out.println("\n" + (++i) + ") ");
      InterfacesPergunta.mostraPergunta(p);
    }

    System.out.print("\nSelecione a pergunta que deseja arquivar: ");
    String sel = console.nextLine();
    if (sel.compareTo("") == 0)
      return;
    int nPergunta = Integer.valueOf(sel);
    if (nPergunta <= 0 || nPergunta > perguntas.length) {
      return;
    }

    Pergunta p = perguntas[nPergunta - 1];
    InterfacesPergunta.mostraPerguntaSelecionada(p);

    p.setAtiva(false);

    System.out.print("\nConfirma arquivamento? (S/N) ");
    char confirma = console.nextLine().charAt(0);
    if (confirma == 's' || confirma == 'S') {
      arqPerguntas.update(p);
      Utils.pausa("Pergunta arquivada!");
    } else {
      Utils.pausa("Arquivamento cancelado!");
    }
    return;
  }
}
