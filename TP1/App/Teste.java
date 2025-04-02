package TP1.App;

import java.io.File;
import java.time.LocalDate;

import TP1.Model.*;
import TP1.Service.*;

/**
 * Teste
 */
public class Teste {
    
    private static Arquivo<Episodio> arqEpisodios;
    public static void main( String[] args ) {

        // Episodios de exemplos
        Episodio t1 = new Episodio(1, "Piloto", 1, LocalDate.of(2004, 9, 15), 45L);
        Episodio t2 = new Episodio(2, "A Jornada Continua", 1, LocalDate.of(2004, 9, 22), 50L);
        Episodio t3 = new Episodio(3, "O Grande Desafio", 1, LocalDate.of(2004, 9, 29), 42L);
        int id1 = 1, id2 = 0, id3 = 0;

        try {
            // Abre (cria) o arquivo de livros, apagando o anterior (apenas para teste)
            File f = new File(".\\TP1\\Data\\Episodios.db"); f.delete();
            File f2 = new File(".\\TP1\\Data\\Episodios.db.c.idx"); f2.delete();
            File f3 = new File(".\\TP1\\Data\\Episodios.db.d.idx"); f3.delete();

            arqEpisodios = new Arquivo<>("Episodios.db", Episodio.class.getConstructor());

            // Insere as Episodios no arquivo
            id1 = arqEpisodios.create(t1);
            t1.setId(id1);
            id2 = arqEpisodios.create(t2);
            t2.setId(id2);
            id3 = arqEpisodios.create(t3);
            t3.setId(id3);

            // Busca por duas Episodios
            System.out.println( arqEpisodios.read(id3) );
            System.out.println( arqEpisodios.read(id1) );

            // Altera o nome de uma Episodio para um nome maior e exibe o resultado
            t1.setNome("Aniversario Breno de 20 anos");
            arqEpisodios.update(t1);
            System.out.println( arqEpisodios.read(id1) );

            // Altera o nome de uma Episodio para um nome menor e exibe o resultado
            t1.setNome("Aniversario Breno");
            arqEpisodios.update(t1);
            System.out.println( arqEpisodios.read(id1) );

            // Exclui uma Episodio e exibe o resultado
            arqEpisodios.delete(id2);
            Episodio t = arqEpisodios.read(id2);
            if ( t == null ) {
                System.out.println("Episodio excluída com sucesso!");
            } else {
                System.out.println("Erro ao excluir Episodio!");
                System.out.println(t);
            } // end if

            arqEpisodios.close();
        } catch (Exception e) {
            e.printStackTrace();
        } // end try catch
    } // end main

} // end class Teste