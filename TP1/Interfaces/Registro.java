package TP1_AEDS_III.TP1.Interfaces;

import java.io.IOException;

public interface Registro{
    public void setId(int id);
    public int getId();

    public byte[] toByteArray() throws IOException;
    public void fromByteArray(byte[] b) throws IOException;
    
}