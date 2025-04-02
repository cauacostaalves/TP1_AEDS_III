package TP1_AEDS_III.TP1.Model;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import TP1_AEDS_III.TP1.Interfaces.Registro;

public class Serie implements Registro {
    protected int idSerie;
    protected String Nome;
    protected long AnoLancamento;
    protected int SinopseSize;
    protected String Sinopse;
    protected String Streaming;


        //construtor para passar os valores dos atributos
        public Serie(int i, String n, long a , int s, String si, String st){
            idSerie = i;
            Nome = n;
            AnoLancamento = a;
            SinopseSize = s;
            Sinopse = si;
            Streaming = st;
        }
        public Serie() {
        idSerie = -1;
        Nome = "";
        AnoLancamento = 0;
        SinopseSize = 0;
        Sinopse = "";
        Streaming = "";
        }

        public void setId(int id) {
            this.idSerie = id;
        }
    
        public int getId() {
            return idSerie;
        }
    
        public String getNome() {
            return Nome;
        }
    
        public void setNome(String Nome) {
            this.Nome = Nome;
        }
    
        public long getAnoLancamento() {
            return AnoLancamento;
        }
    
        public void setAnoLancamento(long AnoLancamento) {
            this.AnoLancamento = AnoLancamento;
        }
    
        public int getSinopseSize() {
            return SinopseSize;
        }
    
        public void setSinopseSize(int SinopseSize) {
            this.SinopseSize = SinopseSize;
        }
    
        public String getSinopse() {
            return Sinopse;
        }
    
        public void setSinopse(String Sinopse) {
            this.Sinopse = Sinopse;
        }
    
        public String getStreaming() {
            return Streaming;
        }
    
        public void setStreaming(String Streaming) {
            this.Streaming = Streaming;
        }

        //METODO QUE DESCREVE A SERIE POR MEIO DE UM VETOR DE BYTES
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(b);
        dos.writeInt(idSerie);
        dos.writeUTF(Nome);
        dos.writeLong(AnoLancamento);
        dos.writeUTF(Sinopse);
        dos.writeUTF(Streaming);
       
        return b.toByteArray();
    }

    //METODO INVERSO: LE DO ARQUIVO O VETOR DE BYTES E CARREGA O OBJ

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        idSerie = dis.readInt();
        Nome = dis.readUTF();
        AnoLancamento = dis.readLong();
        Sinopse = dis.readUTF();
        Streaming = dis.readUTF();
    }
}


