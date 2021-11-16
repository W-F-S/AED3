package operacoes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class Utils {

  public static int idUsuarioGlobal = -1;
  private static Scanner console = new Scanner(System.in);

  // --------------------------------------------------------
  // UTILIDADES DE DADOS ESPECÍFICAS DESTE PROJETO
  // --------------------------------------------------------
  public static void armazenaUsuario(int id) {
    idUsuarioGlobal = id;
  }

  public static int usuarioGlobal() {
    return idUsuarioGlobal;
  }

  public static void apagaArquivos() {
    (new File("dados/usuarios/dados.db")).delete();
    (new File("dados/usuarios/id1.db")).delete();
    (new File("dados/usuarios/id2.db")).delete();
    (new File("dados/usuarios/email1.db")).delete();
    (new File("dados/usuarios/email2.db")).delete();
    (new File("dados/perguntas/dados.db")).delete();
    (new File("dados/perguntas/id1.db")).delete();
    (new File("dados/perguntas/id2.db")).delete();
    (new File("dados/perguntas/palavras1.db")).delete();
    (new File("dados/perguntas/palavras2.db")).delete();
    (new File("dados/perguntas/usuario-pergunta.db")).delete();
  }

  // --------------------------------------------------------
  // UTILIDADES GERAIS
  // --------------------------------------------------------
  public static void pausa() {
    System.out.println("\nPressione ENTER para continuar...");
    console.nextLine();
  }

  public static void pausa(String mensagem) {
    System.out.println("\n" + mensagem);
    System.out.println("\nPressione ENTER para continuar...");
    console.nextLine();
  }

  public static long StringDateToLong(String s) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    sdf.setLenient(false);
    Date dData = sdf.parse(s);
    return dData.getTime();
  }

  public static String LongDateToString(long l) {
    Date dData = new Date(l);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    return sdf.format(dData);
  }

  public static String semAcento(String str) {
    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(nfdNormalizedString).replaceAll("").toLowerCase();
  }
}
