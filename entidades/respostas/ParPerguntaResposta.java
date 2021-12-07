package entidades.respostas;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class ParPerguntaResposta implements aed3.RegistroArvoreBMais<ParPerguntaResposta> {
    int idPergunta;
    int idResposta;
    private short TAMANHO = 34;



    public ParPerguntaResposta(){
        this.idPergunta = -1;
        this.idResposta = -1;
    }
    
    public ParPerguntaResposta( int p, int r){
        try {
            this.idPergunta = p;
            this.idResposta = r;
        } catch (Exception ec) {
            ec.printStackTrace();
        }
    }

    public int getIDResposta(){
        return this.idResposta;
    }

    public int getIDPergunta(){
        return this.idPergunta;
    }

    public void setIDResposta(int idResposta){
        this.idResposta = idResposta;
    }

    public void setIDPergunta(int idPergunta){
        this.idPergunta = idPergunta;
    }

    public int compareTo(ParPerguntaResposta a) {
        if (this.idPergunta != a.idPergunta)
          return this.idPergunta - a.idPergunta;
        else
          return this.idResposta == -1 ? 0 : this.idResposta - a.idResposta;
    }

    @Override
    public ParPerguntaResposta clone() {
      return new ParPerguntaResposta(this.idPergunta, this.idResposta);
    }
  
    public short size(){
        return this.TAMANHO;
    }  

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.idPergunta);
        dos.writeInt(this.idResposta);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.idPergunta = dis.readInt();
        this.idResposta = dis.readInt();         
    }
}
