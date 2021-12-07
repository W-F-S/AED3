package entidades.respostas;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParRespostaUsuario implements aed3.RegistroArvoreBMais<ParRespostaUsuario>{
    int idUsuario;
    int idResposta;
    private short TAMANHO = 34;

    public ParRespostaUsuario(){
        this.idUsuario = -1;
        this.idResposta = -1;
    }
    
    public ParRespostaUsuario( int p, int r){
        try {
            this.idUsuario = p;
            this.idResposta = r;
        } catch (Exception ec) {
            ec.printStackTrace();
        }
    }

    public int compareTo(ParRespostaUsuario a) {
        if (this.idUsuario != a.idUsuario)
          return this.idUsuario - a.idUsuario;
        else
          return this.idResposta == -1 ? 0 : this.idResposta - a.idResposta;
    }

    @Override
    public ParRespostaUsuario clone() {
      return new ParRespostaUsuario(this.idUsuario, this.idResposta);
    }
  
    public short size(){
        return this.TAMANHO;
    }  

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.idUsuario);
        dos.writeInt(this.idResposta);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        this.idUsuario = dis.readInt();
        this.idResposta = dis.readInt();         
    }
    
}
