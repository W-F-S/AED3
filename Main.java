import java.io.File;
import java.util.Scanner;

import operacoes.Acesso;
import operacoes.AreaPessoal;

public class Main {

  private static Scanner console = new Scanner(System.in);

  public static void main(String[] args) {

    File d;
    if (!(d = new File("dados")).exists())
      d.mkdir();
    if (!(d = new File("dados/usuarios")).exists())
      d.mkdir();
    if (!(d = new File("dados/perguntas")).exists())
      d.mkdir();

    // Operação auxiliar
    // apagaArquivos();

    try {
      if (Acesso.menu()) {
        menuPrincipal();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // --------------------------------------------------------
  // MENU PRINCIPAL
  // --------------------------------------------------------
  public static void menuPrincipal() throws Exception {

    int opcao;
    do {
      System.out.println("\n\nPERGUNTAS 1.0");
      System.out.println("=============");
      System.out.println("\nINÍCIO");
      System.out.println("\n1) Minha área");
      System.out.println("2) Buscar perguntas");
      System.out.println("\n0) Sair");

      System.out.print("\nOpcao: ");
      try {
        opcao = Integer.valueOf(console.nextLine());
      } catch (NumberFormatException e) {
        opcao = -1;
      }

      switch (opcao) {
      case 1:
        AreaPessoal.menu();
        break;
      case 2:
        System.out.println("Ainda não implementado.");
        break;
      case 0:
        break;
      default:
        System.out.println("Opção inválida");
      }

    } while (opcao != 0);
  }

}
