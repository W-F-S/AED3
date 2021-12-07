package entidades.respostas;

//import java.util.Scanner;
import operacoes.Utils;


public class InterfacesResposta {

    public static void mostraRespostaSelecionada(Resposta p) {
        mostraRespostaSelecionada(p, "");
    }

    public static void mostraRespostaSelecionada(Resposta p, String nomeUsuario) {
        String barra = "-".repeat(p.getResposta().length());
        System.out.println("\n+-" + barra + "-+");
        System.out.println("| " + p.getResposta() + " |");
        System.out.println("+-" + barra + "-+");
        if (nomeUsuario.length() > 0)
            System.out.println("Autor.........: " + nomeUsuario);
        System.out.println("Criação.......: " + Utils.LongDateToString(p.getCriacao()));
        System.out.println("Nota..........: " + p.getNota());
        if (!p.getAtiva())
            System.out.println("(ARQUIVADA)");
    }
}
