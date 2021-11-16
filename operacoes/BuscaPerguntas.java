package operacoes;

import java.util.Scanner;

public class BuscaPerguntas {
    private static Scanner console = new Scanner(System.in);
    private static String[] palavrasChave;

    public static void readChaves() {
        String stringChave;
        stringChave = console.nextLine();
        stringChave = Utils.semAcento(stringChave.toLowerCase());
        if(stringChave.contains(";")){
            palavrasChave = stringChave.split(";");
        } else {
            palavrasChave[0] = stringChave;
        }
    }

    public static void menu(){
        System.out.println("\n\nPerguntas 1.0");
        System.out.println("=============");
        System.out.println("\nINÍCIO > BUSCA");
        System.out.println("\n Busque as perguntas por palavra chave separadas por ponto e vírgula");
        System.out.println("Ex: política;Brasil;eleições");
    
        System.out.print("Palavras chave: ");
        readChaves();
    }
}
