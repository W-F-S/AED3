package entidades.respostas;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Resposta implements aed3.Registro {
  protected int idResposta;
  protected int idPergunta;
  protected int idUsuario;
  protected long criacao;
  protected short nota;
  protected String resposta;
  protected boolean ativa;

  public Resposta(int i, int pe, int u, long c, short n, String p, boolean a) {
    this.idResposta = i;
    this.idPergunta = pe;
    this.idUsuario = u;
    this.criacao = c;
    this.nota = n;
    this.resposta = p;
    this.ativa = a;
  }
  

  public Resposta(int u, int p, String resp) {
    this.idResposta = -1;
    this.idUsuario = u;
    this.idPergunta = p;
    this.criacao = System.currentTimeMillis();
    this.nota = 0;
    this.resposta = resp;
    this.ativa = true;
  }

  public Resposta() {
    this.idPergunta = -1;
    this.idUsuario = -1;
    this.criacao = 0;
    this.nota = 0;
    this.resposta = "";
    this.ativa = false;
  }




public int getID(){
  return this.idResposta;
}


  public int getIDPergunta(){
    return this.idPergunta;
  }

  public int getIDUsuario(){
    return this.idUsuario;
  }
  
  public long getCriacao(){
    return this.criacao;
  }
  
  public short getNota(){
    return this.nota;
  }
  
  public String getResposta(){
    return this.resposta;
  }

  public boolean getAtiva(){
    return this.ativa;
  }

  public void setID(int idResposta){
      this.idResposta = idResposta;
  }

  public void setIdUsuario(int idUsuario) {
      this.idUsuario = idUsuario;
  }

  public void setIdPergunta(int idPergunta) {
    this.idPergunta = idPergunta;
  }

  public void setCriacao (long criacao) {
    this.criacao = criacao;
  }

  public void setNota(short nota) {
    this.nota = nota;
  }

  public void setResposta(String resposta) {
    this.resposta = resposta;
  }

  public void setAtiva(boolean ativa) {
    this.ativa = ativa;
  }
  public String toString() {
    return "\nID......: " + this.idResposta + "\nResposta:\n" + this.resposta;
  }

  public byte[] toByteArray() throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(this.idResposta);
    dos.writeInt(this.idPergunta);
    dos.writeInt(this.idUsuario);
    dos.writeLong(this.criacao);
    dos.writeShort(this.nota);
    dos.writeUTF(this.resposta);
    dos.writeBoolean(this.ativa);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] b) throws Exception {
    ByteArrayInputStream bais = new ByteArrayInputStream(b);
    DataInputStream dis = new DataInputStream(bais);
    this.idResposta = dis.readInt();
    this.idPergunta = dis.readInt();
    this.idUsuario = dis.readInt();
    this.criacao = dis.readLong();
    this.nota = dis.readShort();
    this.resposta = dis.readUTF();
    this.ativa = dis.readBoolean();
  }
}
