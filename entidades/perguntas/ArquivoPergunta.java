package entidades.perguntas;

import aed3.Arquivo;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import aed3.ArvoreBMais;
import aed3.ListaInvertida;

public class ArquivoPergunta extends Arquivo<Pergunta> {

  String nomePasta;
  ArvoreBMais<ParUsuarioPergunta> relacionamentoUsuarioPergunta;
  ListaInvertida indiceInvertidoPalavrasChave;

  public ArquivoPergunta(String nome) throws Exception {
    super(Pergunta.class.getConstructor(), nome);
    this.nomePasta = nome;
    relacionamentoUsuarioPergunta = new ArvoreBMais<>(ParUsuarioPergunta.class.getConstructor(), 4,
        this.nomePasta + "/usuario-pergunta.db");
    indiceInvertidoPalavrasChave = new ListaInvertida(4, this.nomePasta + "/palavras1.db",
        this.nomePasta + "/palavras2.db");
  }


  // --------------------------------------------------------
  // CREATE
  // --------------------------------------------------------
  @Override
  public int create(Pergunta objeto) throws Exception {
    int id = super.create(objeto);
    relacionamentoUsuarioPergunta.create(new ParUsuarioPergunta(objeto.getIDUsuario(), id));
    String[] palavras = semAcento(objeto.getPalavrasChave()).split(";");
    for (String pc : palavras) {
      indiceInvertidoPalavrasChave.create(pc.trim(), id);
    }
    return id;
  }

  // Usaremos o método read por ID da superclasse
  // Assim, não precisamos implementar algo como:
  // @Override
  // public Pergunta read(int id) throws Exception {
  // return super.read(id);
  // }

  // --------------------------------------------------------
  // BUSCA POR ID DE USUÁRIO
  // --------------------------------------------------------
  public Pergunta[] buscaPorUsuario(int idUsuario) throws Exception {
    return buscaPorUsuario(idUsuario, false);
  }

  public Pergunta[] buscaPorUsuario(int idUsuario, boolean ativas) throws Exception {
    ArrayList<ParUsuarioPergunta> listaIDPergunta = relacionamentoUsuarioPergunta
        .read(new ParUsuarioPergunta(idUsuario, -1));
    ArrayList<Pergunta> perguntas = new ArrayList<>();
    for (int i = 0; i < listaIDPergunta.size(); i++) {
      Pergunta p = super.read(listaIDPergunta.get(i).getIDPergunta());
      if (ativas ? p.getAtiva() : true) // testa se a listagem é de apenas perguntas ativas
        perguntas.add(p);
    }
    Pergunta[] pergs = new Pergunta[perguntas.size()];
    return perguntas.toArray(pergs);
  }

  //busca por uma pergunta usando o id
  public Pergunta buscarPorId(int idPergunta) throws Exception{
    return super.read(idPergunta);
  }

  /**public static void ordenarPerguntas(Pergunta[] desordenado, int lo, int hi){
    Pergunta[] ordenado = new Pergunta[desordenado.length];

    //return ordenado;
  }*/

  /*private static void particao(Pegunta[] a, int lo, int hi){
    int i = lo, j = hi +1;
    Pergunta v = a[lo];

    while(true){

      while(a[++i].getNota <= v.getNota()){

      }

      if(i >= j) break;
    }
  }*/

  // --------------------------------------------------------
  // UPDATE
  // --------------------------------------------------------
  @Override
  public boolean update(Pergunta objetoNovo) throws Exception {
    Pergunta objetoAntigo = super.read(objetoNovo.getID());
    if (objetoAntigo != null && super.update(objetoNovo)) {
      ArrayList<String> antigas = new ArrayList<>(Arrays.asList(objetoAntigo.getPalavrasChave().split(";")));
      ArrayList<String> antigas2 = new ArrayList<>(Arrays.asList(objetoAntigo.getPalavrasChave().split(";")));
      ArrayList<String> novas = new ArrayList<>(Arrays.asList(objetoNovo.getPalavrasChave().split(";")));

      for (int i = 0; i < antigas.size(); i++) {
        String p = semAcento(antigas.get(i)).trim();
        antigas.set(i, p);
      }
      for (int i = 0; i < antigas2.size(); i++) {
        String p = semAcento(antigas2.get(i)).trim();
        antigas2.set(i, p);
      }
      for (int i = 0; i < novas.size(); i++) {
        String p = semAcento(novas.get(i)).trim();
        novas.set(i, p);
      }

      if (objetoNovo.ativa) { // altera a lista de palavras chave se a pergunta ainda estiver ativa
        boolean retirou = antigas.removeAll(novas);
        boolean acrescentou = novas.removeAll(antigas2);

        int i;
        if (retirou) {
          for (i = 0; i < antigas.size(); i++)
            indiceInvertidoPalavrasChave.delete(antigas.get(i), objetoNovo.getID());
        }
        if (acrescentou) {
          for (i = 0; i < novas.size(); i++)
            indiceInvertidoPalavrasChave.create(novas.get(i), objetoNovo.getID());
        }
      } else { // a pergunta não está mais ativa (remove todas as palavras chave)
        for (int i = 0; i < antigas.size(); i++)
          indiceInvertidoPalavrasChave.delete(antigas.get(i), objetoNovo.getID());
      }

      return true;
    }
    return false;
  }

  // --------------------------------------------------------
  // DELETE
  // --------------------------------------------------------
  @Override
  public boolean delete(int id) throws Exception {
    Pergunta objeto = super.read(id);
    if (objeto != null && super.delete(id)) {
      String[] palavras = objeto.getPalavrasChave().split(";");
      for (String p : palavras)
        indiceInvertidoPalavrasChave.delete(p, id);
      return true;
    }
    return false;
  }

  public static String semAcento(String str) {
    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(nfdNormalizedString).replaceAll("").toLowerCase();
  }

  // --------------------------------------------------------
  // CLOSE
  // --------------------------------------------------------
  @Override
  public void close() throws Exception {
    super.close();
    relacionamentoUsuarioPergunta.close();
    indiceInvertidoPalavrasChave.close();
  }

}