package TP1.App;

import java.io.File;
import java.time.LocalDate;

import TP1.Model.*;
import TP1.Service.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Arquivo<Episodio> arqEpisodios;
    static Arquivo<Serie> arqSerie;

    public static void menu() {
        System.out
                .println("PUCFlix 1.0 \n ----------- \n> Início \n 1) Séries \n 2) Episódios \n 3) Atores \n 0) Sair");
    }

    public static void menuEpisodio() throws Exception {
        System.out.println(
                "PUCFlix \n 1.0----------->  \n Início > Episódios \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
        int op = sc.nextInt();
        sc.nextLine(); // Limpa o buffer
        while (op != 0) {
            if (op == 1) { // create
                System.out.println("Crie seu episódio: ");
    
                System.out.print("Nome: ");
                String nome = sc.nextLine();
    
                System.out.print("Temporada: ");
                int temporada = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
    
                System.out.println("Escreva a data de lançamento nesse formato (DD/MM/AAAA): ");
                String data = sc.nextLine();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate DataL = LocalDate.parse(data, dtf);
    
                System.out.print("Duração: ");
                long duracao = sc.nextLong();
                sc.nextLine(); // Limpa o buffer
    
                System.out.println("Escolha o número do episódio:");
                int NumeroEpisodio = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
    
                // Andar no arquivo de series para o usuário escolher para qual série vai esse episódio
                Serie S = new Serie();
                int ultimoId = arqSerie.ultimoId();
                for (int i = 1; i <= ultimoId; i++) {
                    S = arqSerie.read(i);
                    if (S != null) {
                        System.out.println("Nome: " + S.getNome() + "\nID: " + S.getId());
                    }
                }
    
                System.out.println("Escolha o id da série desejada: ");
                int idSerie = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
    
                Episodio E = new Episodio(nome, temporada, DataL, duracao, idSerie, NumeroEpisodio);
                arqEpisodios.create(E);
    
                System.out.println(
                        "PUCFlix \n 1.0----------->  \n Início > Episódios \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                op = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
            }
            if (op == 2) { // read
                System.out.println("Digite o id do Episódio que deseja encontrar: ");
                int idE = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
    
                Episodio E = arqEpisodios.read(idE);
                if (E == null) {
                    System.out.println("Episódio não encontrado!");
                } else {
                    System.out.println("Episódio encontrado: ");
                    System.out.println(E.getNome());
                }
    
                System.out.println(
                        "PUCFlix \n 1.0----------->  \n Início > Episódios \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                op = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
            }
            if (op == 3) { // update
                System.out.println("Atualize seu episódio: ");
    
                System.out.print("Nome: ");
                String nome = sc.nextLine();
    
                System.out.print("Temporada: ");
                int temporada = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
    
                System.out.println("Escreva a data de lançamento nesse formato (DD/MM/AAAA): ");
                String data = sc.nextLine();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate DataL = LocalDate.parse(data, dtf);
    
                System.out.print("Duração: ");
                long duracao = sc.nextLong();
                sc.nextLine(); // Limpa o buffer
    
                System.out.println("Escolha o número do episódio:");
                int NumeroEpisodio = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
    
                // Andar no arquivo de series para o usuário escolher para qual série vai esse episódio
                int ultimoId = arqSerie.ultimoId();
                for (int i = 0; i < ultimoId; i++) {
                    Serie S = arqSerie.read(i);
                    if (S != null) {
                        System.out.println("Nome: " + S.getNome() + "\nID: " + S.getId());
                    }
                }
                System.out.println("Escolha o id da série desejada: ");
                int idSerie = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
    
                Episodio E = new Episodio(nome, temporada, DataL, duracao, idSerie, NumeroEpisodio);
                arqEpisodios.update(E);
    
                System.out.println(
                        "PUCFlix \n 1.0----------->  \n Início > Episódios \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                op = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
            }
            if (op == 4) { // delete
                System.out.println("Digite o id do episódio que deseja remover: ");
                int idE = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
    
                boolean result = arqEpisodios.delete(idE);
                if (result) {
                    System.out.println("Episódio deletado com sucesso!");
                } else {
                    System.out.println("Erro ao deletar episódio, tente novamente mais tarde.");
                }
    
                System.out.println(
                        "PUCFlix \n 1.0----------->  \n Início > Episódios \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                op = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
            }
        }
    }

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

            System.out.println("PUCFlix \n 1.0----------->  \n Início > Séries \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
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

    
    
    public static void menuAtores() {
        System.out.println(
                "PUCFlix \n 1.0----------->  \n Início > Atores \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
        int op = sc.nextInt();
        while (op != 0) {
            if (op == 1) {

            }
            if (op == 2) {

            }
            if (op == 3) {

            }
            if (op == 4) {

            }
        }
    }

    public static void main(String[] args) throws Exception {
        arqEpisodios = new Arquivo<>("Episodios.db", Episodio.class.getConstructor());
        arqSerie = new Arquivo<>("Serie.db", Serie.class.getConstructor());
        menu();
        int op = sc.nextInt();
        while (op != 0) {
            if (op == 1) {
                menuSerie();
                menu();
            }
            if (op == 2) {
                menuEpisodio();
                menu();
            }
            if (op == 3) {
                menuAtores();
                menu();
            }
            op = sc.nextInt();
        }
    }
}