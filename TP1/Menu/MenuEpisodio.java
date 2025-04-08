package TP1.Menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import TP1.Model.Episodio;
import TP1.Model.Serie;
import TP1.Service.Arquivo;

public class MenuEpisodio {
    public static Scanner sc = new Scanner(System.in);
    static Arquivo<Serie> arqSerie;
    static Arquivo<Episodio> arqEpisodios;

    public MenuEpisodio() throws Exception{
        arqEpisodios = new Arquivo<>("Episodios", Episodio.class.getConstructor());
        arqSerie = new Arquivo<>("Serie", Serie.class.getConstructor());
        
    }

    public void menu() throws Exception {

        int opcao;
        do {

            System.out.println("\n\nAEDsIII");
            System.out.println("-------");
            System.out.println("> Início > Episódio");
            System.out.println("\n1 - Buscar");
            System.out.println("2 - Incluir");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");

            System.out.print("\nOpção: ");
            try {
                opcao = Integer.valueOf(sc.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    buscarEpisodio();
                    break;
                case 2:
                    incluirEpisodio();
                    break;
                case 3:
                    alterarEpisodio();
                    break;
                case 4:
                    exluirEpisodio();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);
    }

    public void buscarEpisodio() {
        System.out.println("\nBusca de episódio");
        System.out.println("Digite o id do Episódio que deseja encontrar: ");
        int idE = sc.nextInt();
        sc.nextLine(); // Limpa o buffer

        try {
            Episodio E = arqEpisodios.read(idE);
            if (E != null) {
                mostraEpisodio(E); // Exibe os detalhes do E encontrado
            } else {
                System.out.println("Episódio não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro do sistema. Não foi possível buscar o Episódio!");
            e.printStackTrace();
        }
    }

    public void incluirEpisodio() throws Exception {
        System.out.println("Inclusão de episódio: ");
        String nome = "";
        int temporada = 0;
        LocalDate DataL = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        long duracao = 0;
        int NumeroEpisodio = 0;
        int idSerie = 0;
        boolean dadosCorretos = false;

        System.out.print("Nome: ");
        nome = sc.nextLine();

        do {
            System.out.print("Temporada: ");
            temporada = sc.nextInt();
            if (temporada <= 0) {
                System.err.println("A temporada deve ser inteira e positiva.");
            }
        } while (temporada <= 0);

        do {
            dadosCorretos = false;
            System.out.println("Escreva a data de lançamento nesse formato (DD/MM/AAAA): ");
            String dataStr = sc.nextLine();
            try {
                DataL = LocalDate.parse(dataStr, formatter);
                dadosCorretos = true;
            } catch (Exception e) {
                System.err.println("Data inválida! Use o formato DD/MM/AAAA.");
            }
        } while (!dadosCorretos);

        do {
            dadosCorretos = false;
            System.out.print("Duração em minutos: ");
            if (sc.hasNextLong()) {
                duracao = sc.nextLong();
                dadosCorretos = true;
            } else {
                System.err.println("Duração inválida! Insira um número válido.");
            }
        } while (!dadosCorretos);

        do {
            System.out.println("Escolha o número do episódio:");
            NumeroEpisodio = sc.nextInt();
            if (NumeroEpisodio <= 0) {
                System.err.println("o número do episódio deve ser inteiro e positivo.");
            }
        } while (NumeroEpisodio <= 0);

        // Andar no arquivo de series para o usuário escolher para qual série vai esse
        // episódio
        System.out.println("Séries dispóniveis: ");
        Serie S = new Serie();
        int ultimoId = arqSerie.ultimoId();
        for (int i = 1; i <= ultimoId; i++) {
            S = arqSerie.read(i);
            if (S != null) {
                System.out.println("Nome: " + S.getNome() + "  ||  ID: " + S.getId());
            }
        }
        System.out.println("<---------------------------------------------->");

        do {
            System.out.println("Escolha o id da série que deseja vincular o episódio: ");
            idSerie = sc.nextInt();
            if (idSerie <= 0) {
                System.err.println("Id inválido!");
            }
        } while (idSerie <= 0);

        System.out.print("\nConfirma a inclusão do episódio? (S/N) ");
        char resp = sc.nextLine().charAt(0);
        if (resp == 'S' || resp == 's') {
            try {
                Episodio E = new Episodio(nome, temporada, DataL, duracao, idSerie, NumeroEpisodio);
                arqEpisodios.create(E);
                System.out.println("Episódio incluído com sucesso.");
            } catch (Exception e) {
                System.out.println("Erro do sistema. Não foi possível incluir o episódio!");
            }
        }

    }

    public void alterarEpisodio() {
        System.out.println("\nAlteração de episódio: ");

        int id = 0;
        do {
            System.out.println("Digite o ID do episódio que deseja encontrar: ");
            id = sc.nextInt();
            if (id <= 0) {
                System.err.println("ID inválido! O ID deve ser inteiro e positivo.");
            }
        } while (id <= 0);

        try {
            Episodio E = arqEpisodios.read(id);
            if (E != null) {
                System.out.println("Episódio encontrado: ");
                mostraEpisodio(E);

                // Alteração de nome
                System.out.print("\nNovo nome (deixe em branco para manter o anterior): ");
                String novoNome = sc.nextLine();
                if (!novoNome.isEmpty()) {
                    E.setNome(novoNome);
                }

                // Alteração de temporada
                System.out.print("\nNova temporada (deixe em branco para manter a anterior): ");
                String temporada = sc.nextLine();
                if (!temporada.isEmpty()) {
                    try {
                        int x= Integer.parseInt(temporada);
                        E.setTemporada(x);
                    } catch (NumberFormatException e) {
                        System.err.println("Temporada inválida! Valor mantido.");
                    }
                }

                // Alteração de DataLançamento
                System.out.print("\nNova data de lançamento (DD/MM/AAAA) (deixe em branco para manter a anterior): ");
                String novaDataStr = sc.nextLine();
                if (!novaDataStr.isEmpty()) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate x = LocalDate.parse(novaDataStr, formatter);  // Atualiza a data de lançamento se fornecida
                        E.setDataLancamento(x);
                    } catch (Exception e) {
                        System.err.println("Data inválida. Valor mantido.");
                    }
                }

                // Alteração de duraçao
                System.out.print("\nNova duração (deixe em branco para manter a anterior): ");
                String duracao = sc.nextLine();
                if (!duracao.isEmpty()) {
                    try {
                        long x = Long.parseLong(temporada);
                        E.setDuracao(x);
                    } catch (NumberFormatException e) {
                        System.err.println("Duração inválida! Valor mantido.");
                    }
                }

                // Alteração de idserie
                System.out.print("\nNovo ID da serie (deixe em branco para manter o anterior): ");

                // Andar no arquivo de series para o usuário escolher para qual série vai esse
                // episódio
                System.out.println("Séries dispóniveis: ");
                Serie S = new Serie();
                int ultimoId = arqSerie.ultimoId();
                for (int i = 1; i <= ultimoId; i++) {
                    S = arqSerie.read(i);
                    if (S != null) {
                        System.out.println("Nome: " + S.getNome() + "  ||  ID: " + S.getId());
                    }
                }
                System.out.println("<---------------------------------------------->");

                String idSerie = sc.nextLine();
                if (!idSerie.isEmpty()) {
                    try {
                        int x = Integer.parseInt(idSerie);
                        E.setIdSerie(x);
                    } catch (NumberFormatException e) {
                        System.err.println("id de Serie inválida! Valor mantido.");
                    }
                }

                // Alteração de numero do episodio
                System.out.print("\nNovo número do episódio (deixe em branco para manter o anterior): ");
                String EPnumero = sc.nextLine();
                if (!EPnumero.isEmpty()) {
                    try {
                        int x = Integer.parseInt(EPnumero);
                        E.setNumeroEpisodio(x);
                    } catch (NumberFormatException e) {
                        System.err.println("Número de episódio inválida! Valor mantido.");
                    }
                }

                // Confirmação da alteração
                System.out.print("\nConfirma as alterações? (S/N) ");
                char resp = sc.next().charAt(0);
                if (resp == 'S' || resp == 's') {
                    // Salva as alterações no arquivo
                    boolean alterado = arqEpisodios.update(E);
                    if (alterado) {
                        System.out.println("Episódio alterado com sucesso.");
                    } else {
                        System.out.println("Erro ao alterar o episódio.");
                    }
                } else {
                    System.out.println("Alterações canceladas.");
                }

            } else {
                System.out.println("Episódio não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro do sistema. Não foi possível alterar o episódio!");
            e.printStackTrace();
        }
    }

    public void exluirEpisodio() {
        System.out.println("\nExclusão de episódio");
        int IdE = 0;
        do {
            System.out.println("Digite o id do episódio que deseja remover: ");
            IdE = sc.nextInt();
            if (IdE <= 0) {
                System.err.println("Id inválido! Tente um número inteiro e positivo.");
            }
        } while (IdE <= 0);

        try {
            Episodio E = arqEpisodios.read(IdE);
            if (E != null) {
                System.out.println("Episódio encontrado: ");
                mostraEpisodio(E);

                System.out.print("\nConfirma a exclusão do cliente? (S/N) ");
                char resp = sc.next().charAt(0); // Lê a resposta do usuário

                if (resp == 'S' || resp == 's') {
                    boolean excluido = arqEpisodios.delete(IdE); // Chama o método de exclusão no arquivo
                    if (excluido) {
                        System.out.println("Episódio excluído com sucesso.");
                    } else {
                        System.out.println("Erro ao excluir o episódio.");
                    }
                } else {
                    System.out.println("Exclusão cancelada.");
                }
            } else {
                System.out.println("Episódio não encontrado");
            }
        } catch (Exception e) {
            System.out.println("Erro do sistema. Não foi possível excluir o episódio!");
            e.printStackTrace();
        }

    }

    public void mostraEpisodio(Episodio E) {
        if (E != null) {
            System.out.println("\nDetalhes do Episódio:");
            System.out.println("----------------------");
            System.out.printf("Nome do episódio.........: %s%n", E.getNome());
            System.out.printf("Duração em minutos.......: %d%n", E.getDuracao());
            System.out.printf("Número do episódio.......: %d%n", E.getNumero());
            System.out.printf("Data lançamento..........: %s%n",
                    E.getDataLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("----------------------");
        }
    }
}
