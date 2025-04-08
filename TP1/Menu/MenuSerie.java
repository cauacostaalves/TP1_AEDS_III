package TP1.Menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import TP1.Model.Episodio;
import TP1.Model.Serie;
import TP1.Service.Arquivo;

public class MenuSerie {
    public static Scanner sc = new Scanner(System.in);
    static Arquivo<Serie> arqSerie;
    static Arquivo<Episodio> arqEpisodios;

    public MenuSerie() throws Exception{
        arqSerie = new Arquivo<>("Serie", Serie.class.getConstructor());
        arqEpisodios = new Arquivo<>("Episodios", Episodio.class.getConstructor());
    }

    public void menu() throws Exception {

        int opcao;
        do {

            System.out.println("\n\nAEDsIII");
            System.out.println("-------");
            System.out.println("> Início > Série");
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
                    buscarSerie();
                    break;
                case 2:
                    incluirSerie();
                    break;
                case 3:
                    alterarSerie();
                    break;
                case 4:
                    excluirSerie();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);
    }

    public void buscarSerie() {
        System.out.println("\nBusca de Série");
        System.out.println("Digite o id da série que deseja encontrar: ");
        int idS = sc.nextInt();
        sc.nextLine(); // Limpa o buffer

        try {
            Serie S = arqSerie.read(idS);
            if (S != null) {
                mostraSerie(S); // Exibe os detalhes do S encontrado
            } else {
                System.out.println("Série não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro do sistema. Não foi possível buscar a Série!");
            e.printStackTrace();
        }
    }

    public void incluirSerie() throws Exception {
        System.out.println("Inclusão de série: ");
        String Nome = "";
        LocalDate AnoLancamento = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String Sinopse = "";
        String Streaming = "";
        int QtdTemporada = 0;
        boolean dadosCorretos = false;

        System.out.println("Nome:");
        Nome = sc.nextLine();

        do {
            dadosCorretos = false;
            System.out.println("Escreva o ano de lançamento nesse formato (DD/MM/AAAA): ");
            String dataStr = sc.nextLine();
            try {
                AnoLancamento = LocalDate.parse(dataStr, formatter);
                dadosCorretos = true;
            } catch (Exception e) {
                System.err.println("Data inválida! Use o formato DD/MM/AAAA.");
            }
        } while (!dadosCorretos);

        do {
            dadosCorretos = false;
            System.out.println("Escreva a sinopse: ");
            Sinopse = sc.nextLine();
            if (!Sinopse.isEmpty()) {
                dadosCorretos = true;

            } else {
                System.err.println("Erro ao criar sinopse.");
            }
        } while (!dadosCorretos);

        do {
            System.out.println("Quantidade de temporada:");
            QtdTemporada = sc.nextInt();
            if (QtdTemporada <= 0) {
                System.err.println("A quantidade de temporada deve ser inteira e positiva.");
            }
        } while (QtdTemporada <= 0);

        do {
            dadosCorretos = false;
            System.out.println(
                    "Escolha o seu streaming: \n 1) Netflix \n 2) Amazon Prime Video \n 3) Max \n 4) Disney Plus \n 5) Globo Play \n 6) Star Plus");
            int opStreaming = 0;
            opStreaming = sc.nextInt();
            sc.nextLine();
            if (opStreaming == 1) {
                Streaming = "Netflix";
                dadosCorretos = true;
            }
            if (opStreaming == 2) {
                Streaming = "Amazon Prime Video";
                dadosCorretos = true;
            }
            if (opStreaming == 3) {
                Streaming = "Max";
                dadosCorretos = true;
            }
            if (opStreaming == 4) {
                Streaming = "Disney Plus";
                dadosCorretos = true;
            }
            if (opStreaming == 5) {
                Streaming = "Globo Play";
                dadosCorretos = true;
            }
            if (opStreaming == 6) {
                Streaming = "Star Plus";
                dadosCorretos = true;
            }
        } while (!dadosCorretos);

        System.out.print("\nConfirma a inclusão da série? (S/N) ");
            char resp = sc.next().charAt(0);
            
            if (resp == 'S' || resp == 's') {
                try {
                    Serie S = new Serie(Nome, AnoLancamento, Sinopse, Streaming, QtdTemporada);
                    S.getSinopse();
                    arqSerie.create(S);
                    System.out.println("Série incluída com sucesso.");
                } catch (Exception e) {
                    System.out.println("Erro do sistema. Não foi possível incluir a série!");
                }
            }
        

    }

    public void alterarSerie() {
        System.out.println("\nAlteração de série: ");

        int id = 0;
        do {
            System.out.println("Digite o ID da série que deseja encontrar: ");
            id = sc.nextInt();
            if (id <= 0) {
                System.err.println("ID inválido! O ID deve ser inteiro e positivo.");
            }
        } while (id <= 0);

        try {
            Serie S = arqSerie.read(id);
            if (S != null) {
                System.out.println("Série encontrada: ");
                mostraSerie(null);

                // Alteração de nome
                System.out.print("\nNovo nome (deixe em branco para manter o anterior): ");
                String novoNome = sc.nextLine();
                if (!novoNome.isEmpty()) {
                    S.setNome(novoNome);
                }

                // Alteração ano de lançamento
                System.out.print("\n Ano de lançamento (DD/MM/AAAA)  (deixe em branco para manter a anterior): ");
                String novaDataStr = sc.nextLine();
                if (!novaDataStr.isEmpty()) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate x = LocalDate.parse(novaDataStr, formatter); // Atualiza a data de lançamento se
                        S.setAnoLancamento(x);
                                                                                   // fornecida
                    } catch (Exception e) {
                        System.err.println("Data inválida. Valor mantido.");
                    }
                }

                // SInopse e sinopse size
                System.out.println("\n Escreva a nova sinopse(deixe em branco para manter a anterior): ");
                String sinopse = sc.nextLine();
                if (!sinopse.isEmpty()) {
                    try {
                        S.setSinopse(sinopse);
                    } catch (Exception e) {
                        System.err.println("Erro ao modificar sinopse! Sinopse mantida.");
                    }
                }

                // Alteração de QTD temporada
                System.out.print("\n Quantidade de temporada(deixe em branco para manter a anterior): ");
                String temporada = sc.nextLine();
                if (!temporada.isEmpty()) {
                    try {
                        int x = Integer.parseInt(temporada);
                        S.setQtdTemporada(x);
                    } catch (NumberFormatException e) {
                        System.err.println("Quantidade inválida! Valor mantido.");
                    }
                }

                // Alteração streaming
                System.out.print("\n Escolha um streaming(Escolha a opção 0 se deseja manter o streaming): ");
                boolean dadosCorretos = false;
                String Streaming = S.getStreaming();
                do {
                    dadosCorretos = false;
                    System.out.println(
                            "Escolha o seu streaming: \n 1) Netflix \n 2) Amazon Prime Video \n 3) Max \n 4) Disney Plus \n 5) Globo Play \n 6) Star Plus");
                    int opStreaming = 0;
                    opStreaming = sc.nextInt();
                    if (opStreaming == 0) {
                        dadosCorretos = true;
                    }
                    if (opStreaming == 1) {
                        Streaming = "Netflix";
                        dadosCorretos = true;
                    }
                    if (opStreaming == 2) {
                        Streaming = "Amazon Prime Video";
                        dadosCorretos = true;
                    }
                    if (opStreaming == 3) {
                        Streaming = "Max";
                        dadosCorretos = true;
                    }
                    if (opStreaming == 4) {
                        Streaming = "Disney Plus";
                        dadosCorretos = true;
                    }
                    if (opStreaming == 5) {
                        Streaming = "Globo Play";
                        dadosCorretos = true;
                    }
                    if (opStreaming == 6) {
                        Streaming = "Star Plus";
                        dadosCorretos = true;
                    }
                    S.setStreaming(Streaming);
                } while (!dadosCorretos);

                // Confirmação da alteração
                System.out.print("\nConfirma as alterações? (S/N) ");
                char resp = sc.next().charAt(0);
                if (resp == 'S' || resp == 's') {
                    // Salva as alterações no arquivo
                    boolean alterado = arqSerie.update(S);
                    if (alterado) {
                        System.out.println("Série alterada com sucesso.");
                    } else {
                        System.out.println("Erro ao alterar a série.");
                    }
                } else {
                    System.out.println("Alterações canceladas.");
                }

            } else {
                System.out.println("Série não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro do sistema. Não foi possível alterar a série!");
            e.printStackTrace();
        }
    }

    public void excluirSerie() {
        System.out.println("\nExclusão de Série:");
        int idS = 0;
        do {
            System.out.println("Digite o id da série que deseja remover: ");
            if (idS <= 0) {
                System.err.println("Id inválido! Tente um número inteiro e positivo.");
            }
        } while (idS <= 0);

        try {
            Serie S = arqSerie.read(idS);
            if (S != null) {
                System.out.println("Série encontrada: ");
                mostraSerie(S);

                System.out.print("\nConfirma a exclusão da série? (S/N) ");
                char resp = sc.next().charAt(0); // Lê a resposta do usuário

                int ultimoId = arqEpisodios.ultimoId();
                for (int i = 0; i < ultimoId; i++) {
                    Episodio E = arqEpisodios.read(i);
                    if (E != null) {
                        if (E.getIdSerie() == idS) {
                            System.out.println("Essa série possui episódios cadastrados, não é possível excluí-la.");
                            i = ultimoId; // Para não ficar rodando o loop
                            resp = 'N';
                        } else {
                            System.out.println("Essa série não possui episódios cadastrados, você pode excluí-la.");
                        }
                    }
                }

                if (resp == 'S' || resp == 's') {
                    boolean excluido = arqSerie.delete(idS); // Chama o método de exclusão no arquivo
                    if (excluido) {
                        System.out.println("Série excluída com sucesso.");
                    } else {
                        System.out.println("Erro ao excluir a série.");
                    }
                } else {
                    System.out.println("Exclusão cancelada.");
                }
            } else {
                System.out.println("Série não encontrado");
            }

        } catch (Exception e) {
            System.out.println("Erro do sistema. Não foi possível excluir a série!");
            e.printStackTrace();
        }

    }

    public void mostraSerie(Serie S) {
        if (S != null) {
            System.out.println("\nDetalhes da Série:");
            System.out.println("----------------------");
            System.out.printf("Nome da série..................: %s%n", S.getNome());
            System.out.printf("Quantidade de temporadas.......: %d%n", S.getQtdTemporada());
            System.out.printf("Sinopse........................: %d%n", S.getSinopse());
            System.out.printf("Streaming......................:%d%n", S.getStreaming());
            System.out.printf("Ano de lançamento..............: %s%n",
                    S.getAnoLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("----------------------");
        }
    }

}
