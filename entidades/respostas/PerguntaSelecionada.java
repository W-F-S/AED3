package entidades.respostas;

import java.util.Scanner;
import entidades.perguntas.*;
import operacoes.Utils;
//import entidades.respostas.InterfacesResposta;

public class PerguntaSelecionada {

    private static Scanner console = new Scanner(System.in);
    private static ArquivoResposta arqResposta;

    private static Pergunta selecionada;

/*    public PerguntaSelecionada(Pergunta a){
        selecionada = a;
    }
    public PerguntaSelecionada(){
        selecionada = null;
    }*/

    public static void menu(Pergunta selecionada1) throws Exception{
        int opcao;  
        selecionada = selecionada1;
        arqResposta = new ArquivoResposta("dados/respostas");

        do{
          System.out.println("\n\nPERGUNTAS 1.0");
          System.out.println("=============");
          System.out.println("\nINÍCIO > Buscar Perguntas > Pergunta selecionada");
          printarPerguntaSelecionada();
          printarRespostas();
          System.out.println("\n1) Responder");
          System.out.println("2) Avaliar a pergunta");
          System.out.println("3) Avaliar uma resposta");

          System.out.println("\n0) Retornar ao menu anterior");    
          System.out.print("\nOpcao: ");
          try {
            opcao = Integer.valueOf(console.nextLine());
          } catch (NumberFormatException e) {
            opcao = -1;
          }

          switch (opcao) {
          case 1:
            responder();
            break;
          case 2:
            //avaliar a pergunta
            break;
          case 3:
            //avaliar a resposta
            break;
          case 0:
            break;
          default:
            System.out.println("Opção inválida");
          }
        }while(opcao != 0);
    }

    public static void printarPerguntaSelecionada(){
        InterfacesPergunta.mostraPerguntaSelecionada(selecionada);
    }

    public static void responder() throws Exception{
      String resposta;
      System.out.println("\n\nPERGUNTAS 1.0");
      System.out.println("=============");
      System.out.println("\nINÍCIO > Buscar Perguntas > Pergunta selecionada > Responder");
      printarPerguntaSelecionada();
      System.out.print("\nResposta: ");
      resposta = console.nextLine();
      if (resposta == "")
        throw new Exception("Operação cancelada!");
      Resposta p = new Resposta( Utils.usuarioGlobal(), selecionada.getID(), resposta);
      System.out.print("\nDeseja mesmo responder a essa pergunta(S/N)?: ");
      char confirma = console.nextLine().charAt(0);
      if (confirma == 's' || confirma == 'S') {
        arqResposta.create(p);
        Utils.pausa("Resposta criada!");
      } else {
        Utils.pausa("Resposta cancelada!");
      }
      return;
    }

    public static void printarRespostas() throws Exception{
      Resposta[] respostas = arqResposta.procurarPorPergunta(selecionada.getID());
      for(int i = 0; i<respostas.length; i++){
        System.out.println("\n" + (i) + ") "); 
        InterfacesResposta.mostraRespostaSelecionada(respostas[i]);
      }
      return;
    }
}
