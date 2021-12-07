package entidades.respostas;

import aed3.Arquivo;

import aed3.ArvoreBMais;

import java.util.ArrayList;

public class ArquivoResposta extends Arquivo<Resposta>{

    String nomePasta;
    ArvoreBMais<ParRespostaUsuario> relacionamentoRespostaUsuario;
    ArvoreBMais<ParPerguntaResposta> relacionamentoPerguntaResposta;

    public ArquivoResposta(String nome) throws Exception {
      super(Resposta.class.getConstructor(), nome);
      this.nomePasta = nome;
      relacionamentoRespostaUsuario = new ArvoreBMais<>(ParRespostaUsuario.class.getConstructor(), 4,
          this.nomePasta + "/usuario-resposta.db");

      relacionamentoPerguntaResposta= new ArvoreBMais<>(ParPerguntaResposta.class.getConstructor(), 4,
      this.nomePasta + "/resposta-pergunta.db");

    }
  
  // --------------------------------------------------------
  // CREATE
  // --------------------------------------------------------
  @Override
  public int create(Resposta objeto) throws Exception {
    int id = super.create(objeto);
    relacionamentoPerguntaResposta.create(new ParPerguntaResposta(objeto.getIDPergunta(), id));
    relacionamentoRespostaUsuario.create(new ParRespostaUsuario(objeto.getIDUsuario(), id));    
    return id;  
  }

  public Resposta[] procurarPorPergunta(int idPergunta) throws Exception {
      return procurarPorPergunta(idPergunta, true );
  }

  public Resposta[] procurarPorPergunta(int idPergunta, boolean ativas) throws Exception {
                                                     
    ArrayList<ParPerguntaResposta> listaIDResposta = relacionamentoPerguntaResposta.read(new ParPerguntaResposta(idPergunta, -1));
    ArrayList<Resposta> respostas = new ArrayList<>();
    for (int i = 0; i < listaIDResposta.size(); i++) {
        Resposta p = super.read(listaIDResposta.get(i).getIDResposta());
        if (ativas ? p.getAtiva() : true) // testa se a listagem é de apenas perguntas ativas
            respostas.add(p);
    }
    Resposta[] pergs = new Resposta[respostas.size()];
    return respostas.toArray(pergs);
  }
}
