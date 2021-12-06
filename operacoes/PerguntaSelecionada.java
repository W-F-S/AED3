package operacoes;

import java.util.Scanner;
import entidades.perguntas.*;

public class PerguntaSelecionada {

    private static Scanner console = new Scanner(System.in);

    private static Pergunta selecionada;

    public PerguntaSelecionada(Pergunta a){
        selecionada = a;
    }
    public PerguntaSelecionada(){
        selecionada = null;
    }

    public static void menu() throws Exception{
        int opcao;    
    
        do{
        System.out.println("\n\nPERGUNTAS 1.0");
        System.out.println("=============");
        System.out.println("\nINÍCIO > Buscar Perguntas > Pergunta selecionada");
        printarPerguntaSelecionada();
        //printar respostas
        System.out.print("\nOpcao: ");
        try {
          opcao = Integer.valueOf(console.nextLine());
        } catch (NumberFormatException e) {
          opcao = -1;
        }
  
        switch (opcao) {
        case 1:
          //responder
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
}
