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

        

        public static void menu(){
            System.out.println("PUCFlix 1.0 \n ----------- \n> Início \n 1) Séries \n 2) Episódios \n 3) Atores \n 0) Sair");
        }

        public static void menuSerie() throws Exception{
            System.out.println("PUCFlix \n 1.0----------->  \n Início > Séries \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
            int op = sc.nextInt();
            sc.nextLine();
            while(op != 0){
                if(op == 1){ // create
                    System.out.println("Crie sua série: ");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Ano de Lançamento: ");
                    long AnoLancamento = sc.nextLong();
                    System.out.print("Tamanho da Sinopse: ");
                    int SinopseSize = sc.nextInt();
                    System.out.print("Sinopse: ");
                    String Sinopse = sc.next();
                    System.out.println("Escolha seu streaming: \n 1) Netflix \n 2) Amazon Prime Video \n 3) Max \n 4) Disney Plus \n 5) Globo Play \n 6) Star Plus");
                    int streaming = sc.nextInt();
                    String Streaming = "";
                    if(streaming == 1){
                        Streaming = "Netflix";
                    }
                    if(streaming == 2){
                        Streaming = "Amazon Prime Video";
                    }
                    if(streaming == 3){
                        Streaming = "Max";
                    }
                    if(streaming == 4){
                        Streaming = "Disney Plus";
                    }
                    if(streaming == 5){
                        Streaming = "Globo Play";
                    }
                    if(streaming == 6){
                        Streaming = "Star Plus";
                    }
                    Serie S = new Serie(nome,AnoLancamento,SinopseSize,Sinopse,Streaming);
                    arqSerie.create(S);
                   
                    System.out.println("PUCFlix \n 1.0----------->  \n Início > Séries \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                    op = sc.nextInt();
                }
                if(op == 2){ // read

                    System.out.println("Digite o id da série que deseja encontrar: ");
                    int idS = sc.nextInt();
                    Serie S = arqSerie.read(idS);
                    if(S == null){
                        System.out.println("Série não encontrada!");
                    }else{
                    System.out.println("Série encontrada: ");
                    System.out.println(S.getNome());

                    System.out.println("PUCFlix \n 1.0----------->  \n Início > Séries \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                    op = sc.nextInt();
                }
            }
                if(op == 3){ // update

                    System.out.println("Crie sua série: ");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Ano de Lançamento: ");
                    long AnoLancamento = sc.nextLong();
                    System.out.print("Tamanho da Sinopse: ");
                    int SinopseSize = sc.nextInt();
                    System.out.print("Sinopse: ");
                    String Sinopse = sc.next();
                    System.out.println("Escolha seu streaming: \n 1) Netflix \n 2) Amazon Prime Video \n 3) Max \n 4) Disney Plus \n 5) Globo Play 6) Star Plus");
                    int streaming = sc.nextInt();
                    String Streaming = "";
                    if(streaming == 1){
                        Streaming = "Netflix";
                    }
                    if(streaming == 2){
                        Streaming = "Amazon Prime Video";
                    }
                    if(streaming == 3){
                        Streaming = "Max";
                    }
                    if(streaming == 4){
                        Streaming = "Disney Plus";
                    }
                    if(streaming == 5){
                        Streaming = "Globo Play";
                    }
                    if(streaming == 6){
                        Streaming = "Star Plus";
                    }
                    Serie S = new Serie(nome,AnoLancamento,SinopseSize,Sinopse,Streaming);
                    boolean result = arqSerie.update(S);
                    if(result){
                        System.out.println("Série atualizada com sucesso!");
                    }else{
                        System.out.println("Erro ao atualizar série, tente novamente mais tarde.");
                    }

                    System.out.println("PUCFlix \n 1.0----------->  \n Início > Séries \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                    op = sc.nextInt();

                }
                if(op == 4){ // delete
                    System.out.println("Digite o id da série que deseja remover: ");
                    int idS = sc.nextInt();
                    int ultimoId = arqEpisodios.ultimoId();
                    for(int i=0; i<ultimoId;i++){
                        Episodio E = arqEpisodios.read(i);
                        if (E != null) {
                            if(E.getIdSerie() == idS){
                                System.out.println("Essa série possui episódios cadastrados, não é possível excluí-la.");
                                i = ultimoId; // Para não ficar rodando o loop
                            }else{
                                System.out.println("Essa série não possui episódios cadastrados, você pode excluí-la.");
                            } 
                        }
                    }
                    boolean result = arqSerie.delete(idS);
                    if(result){
                        System.out.println("Série deletada com sucesso!");
                    }else{
                        System.out.println("Erro ao deletar série, tente novamente mais tarde.");
                    }

                    System.out.println("PUCFlix \n 1.0----------->  \n Início > Séries \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                    op = sc.nextInt();
                }
            }
        }

        public static void menuEpisodio() throws Exception{
            System.out.println("PUCFlix \n 1.0----------->  \n Início > Episódios \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
            int op = sc.nextInt();
            sc.nextLine();
            while(op != 0){
                if(op == 1){  // create
                    System.out.println("Crie seu episódio: ");
                   
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    
                    System.out.print("Temporada: ");
                    int temporada = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.println("Escreva a data de lançamento nesse formato (DD/MM/AAAA): ");
                    String data = sc.nextLine();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate DataL = LocalDate.parse(data, dtf);
                   
                    System.out.print("Duração: ");
                    long duracao = sc.nextLong();
                    
                    // Andar no arquivo de series para o usuario escolher para qual serie vai esse episodio
                    int ultimoId = arqSerie.ultimoId();
                    for(int i=0; i<ultimoId;i++){
                        Serie S = arqSerie.read(i);
                        if (S != null) {
                            System.out.println("Nome: " + S.getNome() + "\nID: " + S.getId()); 
                        }
                    }
                    System.out.println("Escolha o id da série desejada: ");
                    int idSerie = sc.nextInt();
                
                    Episodio E = new Episodio(nome,temporada,DataL,duracao,idSerie);
                    arqEpisodios.create(E);

                    System.out.println("PUCFlix \n 1.0----------->  \n Início > Episódios \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                    sc.nextInt();
                }
                if(op == 2){ // read
                    System.out.println("Digite o id do Episódio que deseja encontrar: ");
                    int idE = sc.nextInt();
                    Episodio E = arqEpisodios.read(idE);
                    if(E == null){
                        System.out.println("Episódio não encontrado!");
                    }else{
                        System.out.println("Episódio encontrado: ");
                    System.out.println(E.getNome());

                    System.out.println("PUCFlix \n 1.0----------->  \n Início > Episódios \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                    sc.nextInt();
                }
            }
                if(op == 3){ // update
                    System.out.println("Atualize seu episódio: ");
                   
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    
                    System.out.print("Temporada: ");
                    int temporada = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.println("Escreva a data de lançamento nesse formato (DD/MM/AAAA): ");
                    String data = sc.nextLine();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate DataL = LocalDate.parse(data, dtf);
                   
                    System.out.print("Duração: ");
                    long duracao = sc.nextLong();
                    
                    // Andar no arquivo de series para o usuario escolher para qual serie vai esse episodio
                    int ultimoId = arqSerie.ultimoId();
                    for(int i=0; i<ultimoId;i++){
                        Serie S = arqSerie.read(i);
                        if (S != null) {
                            System.out.println("Nome: " + S.getNome() + "\nID: " + S.getId()); 
                        }
                    }
                    System.out.println("Escolha o id da série desejada: ");
                    int idSerie = sc.nextInt();

                    Episodio E = new Episodio(nome,temporada,DataL,duracao,idSerie);
                    arqEpisodios.update(E);

                    System.out.println("PUCFlix \n 1.0----------->  \n Início > Episódios \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                    sc.nextInt();
                }
                if(op == 4){ // delete
                    System.out.println("Digite o id do episódio que deseja remover: ");
                    int idE = sc.nextInt();
                    boolean result = arqEpisodios.delete(idE);
                    if(result){
                        System.out.println("Episódio deletado com sucesso!");
                    }else{
                        System.out.println("Erro ao deletar episódio, tente novamente mais tarde.");
                    }

                    System.out.println("PUCFlix \n 1.0----------->  \n Início > Episódios \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
                    sc.nextInt();
                }
            }
        }

        public static void menuAtores(){
            System.out.println("PUCFlix \n 1.0----------->  \n Início > Atores \n 1) Incluir \n 2) Buscar \n 3) Alterar \n 4) Excluir \n 0) Retornar ao menu anterior");
            int op = sc.nextInt();
            while(op != 0){
                if(op == 1){ 
                    
                }
                if(op == 2){

                }
                if(op == 3){

                }
                if(op == 4){

                }
            }
        }

    public static void main(String[] args) throws Exception {
        arqEpisodios = new Arquivo<>("Episodios.db", Episodio.class.getConstructor());
        arqSerie = new Arquivo<>("Serie.db", Serie.class.getConstructor());
        menu();
        int op = sc.nextInt();
        while(op != 0){
            if(op == 1){
                menuSerie();
                menu();
            }
            if(op == 2){
                menuEpisodio();
                menu();
            }
            if(op == 3){
                menuAtores();
                menu();
            }
            op = sc.nextInt();
        }
    }
}