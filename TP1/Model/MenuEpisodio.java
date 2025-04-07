package TP1.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import TP1.Service.Arquivo;

public class MenuEpisodio {
    static Scanner sc = new Scanner(System.in);
    static Arquivo<Serie> arqSerie;
    static Arquivo<Episodio> arqEpisodios;

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

                // Andar no arquivo de series para o usuário escolher para qual série vai esse
                // episódio
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

                // Andar no arquivo de series para o usuário escolher para qual série vai esse
                // episódio
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
}
