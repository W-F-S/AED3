package operacoes;

import java.util.Scanner;
import entidades.usuarios.*;

public class Acesso {

  private static ArquivoUsuario arqUsuarios;
  private static Scanner console = new Scanner(System.in);

  public static boolean menu() throws Exception {

    boolean acessoLiberado = false;
    int opcao;

    arqUsuarios = new ArquivoUsuario("dados/usuarios");

    // menu
    do {
      System.out.println("\n\nPERGUNTAS 1.0");
      System.out.println("=============");
      System.out.println("\nACESSO");
      System.out.println("\n1) Acessar o sistema");
      System.out.println("2) Novo usuário");
      System.out.println("\n0) Encerrar");

      System.out.print("\nOpcao: ");
      try {
        opcao = Integer.valueOf(console.nextLine());
      } catch (NumberFormatException e) {
        opcao = -1;
      }

      switch (opcao) {
      case 1:
        acessoLiberado = acesso();
        break;
      case 2:
        cadastro();
        break;
      case 0:
        break;
      default:
        System.out.println("Opção inválida");
      }

    } while (!acessoLiberado && opcao != 0);

    arqUsuarios.close();

    return acessoLiberado;

  }

  // --------------------------------------------------------
  // ACESSO
  // --------------------------------------------------------
  public static boolean acesso() throws Exception {
    String email;
    int hashSenha;
    System.out.println("\nACESSO AO SISTEMA");
    try {
      email = InterfacesUsuario.leEmail();
      hashSenha = InterfacesUsuario.leSenha();
    } catch (Exception e) {
      Utils.pausa("Operação cancelada");
      return false;
    }

    Usuario u = arqUsuarios.read(email);
    if (u == null) {
      Utils.pausa("E-mail ou senha inválidos!");
      return false;
    }

    if (!u.validaSenha(hashSenha)) {
      Utils.pausa("E-mail ou senha inválidos!");
      return false;
    }

    Utils.armazenaUsuario(u.getID());

    return true;
  }

  // --------------------------------------------------------
  // CADASTRO NOVO USUÁRIO
  // --------------------------------------------------------
  public static void cadastro() throws Exception {

    System.out.println("\nNOVO USUÁRIO");
    String email, nome;
    int senha;
    try {
      email = InterfacesUsuario.leEmail();
    } catch (Exception e) {
      Utils.pausa(e.getMessage());
      return;
    }

    if (arqUsuarios.read(email) != null) {
      Utils.pausa("E-mail já cadastrado!");
      return;
    }

    try {
      nome = InterfacesUsuario.leNome();
      senha = InterfacesUsuario.leSenha();
    } catch (Exception e) {
      Utils.pausa("Operação cancelada");
      return;
    }

    Usuario u = new Usuario(nome, email, senha);

    System.out.print("\nConfirma inclusão? (S/N) ");
    char confirma = console.nextLine().charAt(0);
    if (confirma == 's' || confirma == 'S') {
      arqUsuarios.create(u);
      Utils.pausa("Usuário cadastrado!");
    } else {
      Utils.pausa("Inclusão cancelada!");
    }
    return;
  }
}
