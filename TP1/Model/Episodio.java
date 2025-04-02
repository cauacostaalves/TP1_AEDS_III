package TP1.Model;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import TP1.Interfaces.Registro;

public class Episodio implements Registro{
    protected int ID;
    protected String Nome;
    protected int temporada;
    protected LocalDate DataLancamento;
    protected long Duracao;

    public Episodio(int i, String n, int t, LocalDate d, long du){
        ID = i;
        Nome = n;
        temporada = t;
        DataLancamento = d;
        Duracao = du;
    }

    public Episodio( ){
        ID=-1;
        Nome = "";
        temporada = 0;
        LocalDate.now();
        Duracao = 0;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public int getId() {
        return ID;
    }

     public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public LocalDate getDataLancamento() {
        return DataLancamento;
    }

    public void setDataLancamento(LocalDate DataLancamento) {
        this.DataLancamento = DataLancamento;
    }

    public long getDuracao() {
        return Duracao;
    }

    public void setDuracao(long Duracao) {
        this.Duracao = Duracao;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(b);
        dos.writeUTF(Nome);
        dos.writeInt(temporada);
        dos.writeLong(Duracao);
        dos.writeInt((int) this.DataLancamento.toEpochDay());
        return b.toByteArray();
    }

    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        Nome = dis.readUTF();
        temporada = dis.readInt();
        Duracao = dis.readLong();
        this.DataLancamento = LocalDate.ofEpochDay(dis.readInt());
    }

}

