package operacoes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Set;
//import java.util.List;
//import java.util.HashSet;
import java.util.Arrays;
//import java.util.ArrayList;
import java.util.Scanner;



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

<<<<<<< HEAD
=======
<<<<<<< HEAD
  public static String semAcento(String str) {
    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(nfdNormalizedString).replaceAll("").toLowerCase();
=======
>>>>>>> 34a6bf5 (segunda tentativa)
  public static int[] intersectionBetweenArrays(int[] array1, int[] array2){
    //Integer[] resultado;
   // List<Integer> resultado= new ArrayList < Integer> ();
    int[] resultado2 = new int[array1.length];
    //int resp;

    Arrays.sort(array1);

    System.out.println("Primeiro array: " + " tamanho do primeiro array " + array1.length);
    for(int j =0; j < array1.length; j++){
      System.out.println(array1[j]);
    }

    System.out.println("Segundo array: " + " tamanho do segundo array " + array2.length);
    for(int j =0; j < array2.length; j++){
      System.out.println(array2[j]);
    }
    Utils.pausa();

/*    for(int i =1; i < array2.length; i++){
      resp = Arrays.binarySearch(array1, array2[i]);
      Utils.pausa();
      System.out.println("resultado de resp: " + resp);
      Utils.pausa();
      if(resp >= 0){
        resultado.add(array2[i]);              
      } 
    }*/

    int k = 0; //dumb way. refatorar essa parte. intersection between two arrays in java
    for(int i = 0; i < array1.length; i++){
      for(int j = 0; j < array2.length; j++){
        if(array1[i] == array2[j]){
          resultado2[k] = array1[i];
          k++;
        }
      }
    }

 /*   for(int i = 0; i < resultado.size(); i++) {
      if (resultado.get(i) != null) {
        resultado2[i] = resultado.get(i);
      }
    }*/
    return resultado2;
<<<<<<< HEAD
=======
>>>>>>> 5a6167b (segunda tentativa)
>>>>>>> 34a6bf5 (segunda tentativa)
  }
}
