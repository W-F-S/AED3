package operacoes;

import java.util.Scanner;
import entidades.perguntas.*;
import aed3.ListaInvertida;

import java.util.ArrayList;

//import aed3.Arquivo;

public class BuscarPergunta {
    protected static String[] palavras_chave;
    protected static int[] ids_chave;
    private static Scanner console = new Scanner(System.in);
    protected static ListaInvertida indiceInvertidoPalavrasChave;
    private static ArquivoPergunta arqPerguntas;
    private static Pergunta[] perguntas_geral; 

    


    public static void menu() throws Exception{
        int opcao;
        arqPerguntas = new ArquivoPergunta("dados/perguntas");
        
            String palavras;
            System.out.println("\n\nPERGUNTAS 1.0");
            System.out.println("=============");
            System.out.println("\nINÍCIO > Buscar Perguntas");
            palavras = InterfacesPergunta.lePalavrasChave();
            palavras_chave = extrairPalavras(ArquivoPergunta.semAcento(palavras));
            ids_chave = pesquisarPalavrasIndice();
            perguntas_geral = pegar_perguntas();
            printarPerguntas(perguntas_geral);
        
        do{
            System.out.println("Que pergunta deseja visualisar (0 para voltar) ?");
            try {
                opcao = Integer.valueOf(console.nextLine());
              } catch (NumberFormatException e) {
                opcao = -1;
              }        

        }while(opcao != 0);
        return;


    }

    //public void setPalavrasChave(String palavras){
    //    this.palavras_chave = palavras;
    //}
    //public String getPalavrasChave(){
    //    return this.palavras_chave;
    //}

/**
 * Pesquisa por chaves no indice invertido e retorna uma lista de Ids de perguntas que 
 * contem essas chaves
 * @param palavras array de chaves a serem pesquisadas
 * @return resultado_fim array de ids de perguntas que possuem todas as chaves dadas
 * @throws Exception
 */       
    public static int[] pesquisarPalavrasIndice() throws Exception{
        indiceInvertidoPalavrasChave = new ListaInvertida(4, "./dados/perguntas" + "/palavras1.db",
    "./dados/perguntas" + "/palavras2.db");

        int[] resultado_temp = new int[palavras_chave.length];
        int[] resultado_fim  = new int[palavras_chave.length];
        int i = 1;

        resultado_fim = indiceInvertidoPalavrasChave.read(palavras_chave[0]);
        
/*        System.out.println("Primeira chave: " + palavras_chave[0] + " tamanho de resultado_fim" + resultado_fim.length);
        for(int j =0; j < resultado_fim.length; j++){
            System.out.println(resultado_fim[j]);
        }
        Utils.pausa();*/

        for(; i < palavras_chave.length; i++){
            resultado_temp = indiceInvertidoPalavrasChave.read(palavras_chave[i]);
            resultado_fim = Utils.intersectionBetweenArrays(resultado_fim, resultado_temp);
        }

/*        Utils.pausa();
        System.out.println("Tamanho do array final: " + resultado_fim.length);
        for(int j =0; j < resultado_fim.length; j++){
            System.out.println(resultado_fim[j]);
        }*/
        return resultado_fim;
    }    

    private static Pergunta[] pegar_perguntas() throws Exception{
        ArrayList<Pergunta> perguntas = new ArrayList<>();
        Pergunta[] pergs; 

        for (int i = 0; i < ids_chave.length; i++) {
            Pergunta p = arqPerguntas.buscarPorId(ids_chave[i]);
            if(p != null && p.getAtiva()){
                perguntas.add(p);
            }
        }
        
        pergs = new Pergunta[perguntas.size()];

        return perguntas.toArray(pergs);
    }

    private static void printarPerguntas(Pergunta[] perguntas){
        for(int i = 0; i<perguntas.length; i++){
            System.out.println("\n" + (i) + ") ");
            InterfacesPergunta.mostraPerguntaSelecionada(perguntas[i]);
        }
    }

/*    private static void perguntaSelecionada(int id){
        perguntaSelecionada(perguntas_geral[id--]);

    }

    private static void perguntaSelecionada(Pergunta a){

    }*/

/**
 * 
 * @param palavras
 * @return
 */
    private static String[] extrairPalavras(String palavras){
        String chaves[];
        palavras = palavras.toLowerCase();
        chaves = palavras.split(";");
        return chaves;
    }
}
