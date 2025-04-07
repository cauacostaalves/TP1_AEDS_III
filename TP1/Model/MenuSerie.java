package TP1.Model;

import java.util.Scanner;

import TP1.Service.Arquivo;

public class MenuSerie {
    static Scanner sc = new Scanner(System.in);
    static Arquivo<Serie> arqSerie;
    static Arquivo<Episodio> arqEpisodios;

    public static void menuSerie() throws Exception {
        System.out.println(
                "PUCFlix \n 1.0----------->  \n Início > Séries \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
        int op = sc.nextInt();
        sc.nextLine(); // Limpa o buffer
        while (op != 0) {
            if (op == 1) { // create
                System.out.println("Crie sua série: ");
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Ano de Lançamento: ");
                long AnoLancamento = sc.nextLong();
                sc.nextLine(); // Limpa o buffer
                System.out.print("Sinopse: ");
                String Sinopse = sc.nextLine();
                int SinopseSize = Sinopse.length();
                System.out.println("Quantas temporadas tem sua série:");
                int QtdTemporada = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
                System.out.println(
                        "Escolha seu streaming: \n 1) Netflix \n 2) Amazon Prime Video \n 3) Max \n 4) Disney Plus \n 5) Globo Play \n 6) Star Plus");
                int streaming = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
                String Streaming = "";
                if (streaming == 1) {
                    Streaming = "Netflix";
                }
                if (streaming == 2) {
                    Streaming = "Amazon Prime Video";
                }
                if (streaming == 3) {
                    Streaming = "Max";
                }
                if (streaming == 4) {
                    Streaming = "Disney Plus";
                }
                if (streaming == 5) {
                    Streaming = "Globo Play";
                }
                if (streaming == 6) {
                    Streaming = "Star Plus";
                }
                Serie S = new Serie(nome, AnoLancamento, SinopseSize, Sinopse, Streaming, QtdTemporada);
                arqSerie.create(S);

                System.out.println(
                        "PUCFlix \n 1.0----------->  \n Início > Séries \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                op = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
            }
            if (op == 2) { // read
                System.out.println("Digite o id da série que deseja encontrar: ");
                int idS = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
                Serie S = arqSerie.read(idS);
                if (S == null) {
                    System.out.println("Série não encontrada!");
                } else {
                    System.out.println("Série encontrada: ");
                    System.out.println(S.getNome());

                    System.out.println("Deseja ver os episódios dessa série ?");
                    System.out.println("Digite SIM ou NÃO");
                    String opS = sc.nextLine();

                    if (opS.equals("SIM")) {
                        int ultimoID = arqEpisodios.ultimoId();
                        int x = 1;
                        System.out.println("Temporada " + x);
                        for (int i = 1; i <= ultimoID; i++) {
                            Episodio E = arqEpisodios.read(i);
                            if (E != null && E.getIdSerie() == idS) {
                                if (E.getTemporada() != x) {
                                    x++;
                                    System.out.println("Temporada " + x);
                                } else if (E.getTemporada() == x) {
                                    System.out.println(E.getNome());
                                }
                            }
                        }
                    }
                }

                System.out.println(
                        "PUCFlix \n 1.0----------->  \n Início > Séries \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                op = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
            }
            if (op == 3) { // update
                System.out.println("Crie sua série: ");
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Ano de Lançamento: ");
                long AnoLancamento = sc.nextLong();
                sc.nextLine(); // Limpa o buffer
                System.out.print("Sinopse: ");
                String Sinopse = sc.nextLine();
                int SinopseSize = Sinopse.length();
                System.out.println("Quantas temporadas tem sua série:");
                int QtdTemporada = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
                System.out.println(
                        "Escolha seu streaming: \n 1) Netflix \n 2) Amazon Prime Video \n 3) Max \n 4) Disney Plus \n 5) Globo Play 6) Star Plus");
                int streaming = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
                String Streaming = "";
                if (streaming == 1) {
                    Streaming = "Netflix";
                }
                if (streaming == 2) {
                    Streaming = "Amazon Prime Video";
                }
                if (streaming == 3) {
                    Streaming = "Max";
                }
                if (streaming == 4) {
                    Streaming = "Disney Plus";
                }
                if (streaming == 5) {
                    Streaming = "Globo Play";
                }
                if (streaming == 6) {
                    Streaming = "Star Plus";
                }
                Serie S = new Serie(nome, AnoLancamento, SinopseSize, Sinopse, Streaming, QtdTemporada);
                boolean result = arqSerie.update(S);
                if (result) {
                    System.out.println("Série atualizada com sucesso!");
                } else {
                    System.out.println("Erro ao atualizar série, tente novamente mais tarde.");
                }

                System.out.println(
                        "PUCFlix \n 1.0----------->  \n Início > Séries \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                op = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
            }
            if (op == 4) { // delete
                System.out.println("Digite o id da série que deseja remover: ");
                int idS = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
                int ultimoId = arqEpisodios.ultimoId();
                for (int i = 0; i < ultimoId; i++) {
                    Episodio E = arqEpisodios.read(i);
                    if (E != null) {
                        if (E.getIdSerie() == idS) {
                            System.out.println("Essa série possui episódios cadastrados, não é possível excluí-la.");
                            i = ultimoId; // Para não ficar rodando o loop
                        } else {
                            System.out.println("Essa série não possui episódios cadastrados, você pode excluí-la.");
                        }
                    }
                }
                boolean result = arqSerie.delete(idS);
                if (result) {
                    System.out.println("Série deletada com sucesso!");
                } else {
                    System.out.println("Erro ao deletar série, tente novamente mais tarde.");
                }

                System.out.println(
                        "PUCFlix \n 1.0----------->  \n Início > Séries \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                op = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
            }
        }
    }
}
