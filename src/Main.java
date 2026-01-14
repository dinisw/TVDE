//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Classe principal da aplicação TVDE.
 * Apresenta um menu de opções do sistema de viagens TVDE
 * e permite ao utilizador realizar operações através da
 * leitura de dados introduzidos pelo teclado.
 * Atualmente, o menu permite o registo de um condutor,
 * solicitando os seus dados pessoais e profissionais,
 * criando um objeto da classe Condutor e armazenando-o
 * numa lista de condutores.
 */
public class Main {

    //ArrayList<Viatura> viaturas = new ArrayList<>();

    private final String CAMINHO_FICHEIRO_LOGS_ERROS_VIATURAS = "logsErrosViaturas.txt";



    ArrayList<Viagem> viagens = new ArrayList<>();
    ArrayList<Reserva>reservas = new ArrayList<>();
    ArrayList<Condutor>condutores = new ArrayList<>();
    //Cliente cliente = new Cliente();
    //Condutor condutor = new Condutor();
    //Viatura viatura = new Viatura();
    //Reserva reserva = new Reserva();
    EmpresaTVDE empresaTVDE = new EmpresaTVDE();
    ArrayList<Viatura> viaturas = empresaTVDE.carregarViaturas();
    //Antes de qualuqer coisa temos que carregar os itens da memória pra ca e guardar em um arraylist e depois irmos consultando

    //region Design
    /*Reset*/
    public static final String RESET = "\u001B[0m";
    /*Cores*/
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String ROXO = "\u001B[35m";
    public static final String CIANO = "\u001B[36m";

    /*Cores Brilhantes*/
    public static final String VERMELHO_BRILHANTE = "\u001B[91m";
    public static final String VERDE_BRILHANTE = "\u001B[92m";
    public static final String AMARELO_BRILHANTE = "\u001B[93m";
    public static final String AZUL_BRILHANTE = "\u001B[94m";
    public static final String ROXO_BRILHANTE = "\u001B[95m";
    public static final String CIANO_BRILHANTE = "\u001B[96m";
    /*Negrito*/
    public static final String NEGRITO = "\u001B[1m";

    /*função centralizar texto*/
    public static void printCentralizado(String texto) {
        int largura = 80;
        int espacos = (largura - texto.length()) / 2;

        if (espacos > 0) {
            System.out.print(" ".repeat(espacos));
        }
        System.out.println(texto);
    }
    /*Função titulo principal*/
    public static void printTituloPrincipal() {
        System.out.println();
        printCentralizado(NEGRITO + AZUL + "========= Sistema de Viagens TVDE ===========" + RESET);
        System.out.println();
    }
    /*Função titulo secundario */
    public static void printTituloSecundario(String texto) {
        printCentralizado(CIANO + texto + RESET);
        System.out.println();
    }
    //endregion

    void main() {
        int opcao;
        Scanner ler = new Scanner(System.in);
        do {
            opcao = menu(ler);
            switch (opcao) {
                case 1:
                    registarCliente(ler);
                    break;
                case 2:
                    registarCondutor(ler);
                    break;
                case 3:
                    Viaturas(ler);
                    break;
                case 4:
                    Reservas(ler);
                    break;
                case 5:
                    Viagens(ler);
                    break;
                case 6:
                    informacoes(ler);
                    break;
                case 0:
                    System.out.print("Obrigado por utilizar a App da TVDE!!");
                    break;
                default:
                    System.out.println("Opção Inválida! Tente novamente!");
                    break;
            }
        } while (opcao != 0);
    }

    int menu(Scanner ler) {
        printTituloPrincipal();
        printTituloSecundario("MENU");
        System.out.println(VERDE + "1\t-\tCliente" + RESET);
        System.out.println(VERDE + "2\t-\tCondutor" + RESET);
        System.out.println(VERDE + "3\t-\tViaturas" + RESET);
        System.out.println(VERDE + "4\t-\tReservas" + RESET);
        System.out.println(VERDE + "5\t-\tViagens" + RESET);
        System.out.println(VERDE + "6\t-\tInformações" + RESET);
        System.out.println(VERDE + "0\t-\tSair" + RESET);
        System.out.print("Indique a opção que queira realizar utilizando os números de 0 a 6: ");
        String opcao = ler.nextLine();
        return Integer.parseInt(opcao);
    }

    void registarCliente(Scanner ler) {
        //empresaTVDE.adicionarCliente(cliente);
    }

    private void Clientes(){

    }

    void registarCondutor(Scanner ler) {
        System.out.println("Indique o nome do/a condutor/a");
        String nome = ler.nextLine();
        System.out.println("Indique a sua idade:");
        int idade = Integer.parseInt(ler.nextLine());
        System.out.println("Indique o seu genero:");
        String sexo = ler.nextLine();
        System.out.println("Indique o seu email:");
        String email = ler.nextLine();
        System.out.println("Indique o numero da carta de condução:");
        int cartaDeConducao = Integer.parseInt(ler.nextLine());
        System.out.println("Indique o numero de cartão de cidadão sem os últimos 4 dígitos:");
        int cartaDeCidadao = Integer.parseInt(ler.nextLine());
        System.out.println("Indique o seu número de contribuinte:");
        int contribuinte = Integer.parseInt(ler.nextLine());
        System.out.println("Indique a sua morada:");
        String morada = ler.nextLine();
        System.out.println("Indique o seu número de telemóvel:");
        int telefone = Integer.parseInt(ler.nextLine());
        Condutor condutor = new Condutor(nome, idade, sexo, email, telefone, morada, cartaDeCidadao, contribuinte);
        condutores.add(condutor);
    }

    public void limparConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //region Viaturas
    private void Viaturas(Scanner ler){
        int opcao;
        do {
            opcao = subMenuViaturas(ler);
            if (opcao == 1) {
                registarViatura(ler);
            } else if (!viaturas.isEmpty() && opcao == 2) {
                while (true) {
                    System.out.println("--- Bucar Viatura (Escreva 'sair' para cancelar) ---");
                    System.out.print("Indique a matrícula que deseja buscar no formato [XX-XX-XX]: ");
                    String matricula = ler.nextLine().toUpperCase();

                    if (matricula.equalsIgnoreCase("sair"))
                        return;

                    if (!isMatriculaValida(matricula)) {
                        System.out.println("Formato incorreto - ");
                        continue;
                    }

                    for (Viatura viatura : viaturas) {
                        if (viatura.getMatricula().equalsIgnoreCase(matricula)) {
                            System.out.print(viatura.toString());
                            System.out.println("Digite uma tecla qualquer para continuar");
                            ler.nextLine();
                            return;
                        }
                    }
                }
            } else if (!viaturas.isEmpty() && opcao == 3) {
                removerViatura(ler);
            } else if (opcao == 0) {
                break;
            } else {
                System.out.println("Opção Inválida! Tente novamente!");
            }
        } while (opcao != 0);
    }

    int subMenuViaturas(Scanner ler){
        int count = 1;
        limparConsola();
        printTituloPrincipal();
        printTituloSecundario("VIATURAS");
        System.out.printf(VERDE + "%d\t-\tRegistar Viatura\n" + RESET, count);
        if(!viaturas.isEmpty()){
            count++;
            System.out.printf(VERDE + "%d\t-\tPesquisar Viatura pela Matrícula\n" + RESET, count);
        }
        if(!viaturas.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tRemover Viatura\n" + RESET, count);
        }
        System.out.println(VERDE + "0\t-\tVoltar ao menu anterior" + RESET);
        System.out.print("Indique a opção que queira realizar: ");
        String opcao = ler.nextLine();
        return Integer.parseInt(opcao);
    }

    public boolean isMatriculaValida(String matricula) {
        if (matricula == null) return false;
        String regex = "^[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}$";

        return matricula.trim().toUpperCase().matches(regex);
    }

    void registarViatura(Scanner ler) {
        try {
            System.out.println("--- Novo Registo de Viatura (Escreva 'sair' para cancelar) ---");

            System.out.println("Indique a marca da viatura.");
            String marca = ler.nextLine();
            if (marca.equalsIgnoreCase("sair"))
                return;

            System.out.println("Indique a modelo da viatura.");
            String modelo = ler.nextLine();
            if (modelo.equalsIgnoreCase("sair"))
                return;

            String anoDeFabrico = "";
            while (true) {
                System.out.println("Indique o ano de fabrico da viatura [XXXX].");
                anoDeFabrico = ler.nextLine();

                if (anoDeFabrico.equalsIgnoreCase("sair"))
                    return;

                if (anoDeFabrico.matches("^(19|20)\\d{2}$")) {
                    break;
                } else {
                    System.out.println("Ano inválido. Tente novamente.");
                }
            }

            String matricula = "";
            while (true) {
                System.out.println("Indique a matrícula no formato [XX-XX-XX]:");
                matricula = ler.nextLine().toUpperCase();

                if (matricula.equalsIgnoreCase("sair"))
                    return;

                if (!isMatriculaValida(matricula)) {
                    System.out.println("Formato incorreto (use XX-XX-XX).");
                    continue;
                }

                boolean existe = false;
                for (Viatura viatura : viaturas) {
                    if (viatura.getMatricula().equalsIgnoreCase(matricula)) {
                        existe = true;
                        break;
                    }
                }

                String cor = "";
                System.out.println("Indique a cor da viatura:");
                cor = ler.nextLine();
                if (cor.equalsIgnoreCase("sair"))
                    return;

                if (existe) {
                    System.out.println("Erro: Essa matrícula já existe no sistema.");
                } else {
                    Viatura viatura = new Viatura(matricula.toUpperCase(), marca, modelo, Integer.parseInt(anoDeFabrico), cor, existe);
                    String resposta = empresaTVDE.adicionarViatura(viatura);
                    if(resposta.equals("Viatura inserida com Sucesso!")) {
                        System.out.println(resposta);
                        viaturas.add(viatura);
                        break;
                    }else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIATURAS, e.getMessage() + ";");
        }
    }

    private void removerViatura(Scanner ler) {
        try {
            System.out.println("--- Remover Viatura (Escreva 'sair' para cancelar) ---");
            String matricula = "";
            while (true) {
                System.out.println("Indique a matrícula do veículo que deseja remover no formato [XX-XX-XX]: ");
                matricula = ler.nextLine().toUpperCase();

                if (matricula.equalsIgnoreCase("sair"))
                    break;

                if (!isMatriculaValida(matricula)) {
                    System.out.print("Formato incorreto - ");
                    continue;
                }

                boolean existe = false;
                for (Viatura viatura : viaturas) {
                    if (viatura.getMatricula().equalsIgnoreCase(matricula)) {
                        existe = true;
                        break;
                    }
                }

                if (!existe) {
                    System.out.println("Erro: Essa matrícula não existe no sistema.");
                } else {
                    boolean resposta = empresaTVDE.deletarViaturas(matricula);
                    if(resposta) {
                        System.out.printf("Viatura com matrícula %s removida com sucesso.", matricula);
                        System.out.print("Digite uma tecla qualquer para continuar");
                        viaturas = empresaTVDE.carregarViaturas();
                        ler.nextLine();
                        break;
                    }else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIATURAS, e.getMessage() + ";");
        }
    }
    //endregion


    private void verListaDeClientes(Scanner ler, String matricula) {
    }

    void Reservas(Scanner ler) {
        /*Validar antes de inserir se a reserva já existe*/



    /*
    Fazer um switch case
    * Criar um SUb menu para Registrar Reservas e Eliminar
    * */
        //criarReserva(ler);
        consultarReservas(ler);
        alterarReserva(ler);
        removerReserva(ler);
    }

    private void alterarReserva(Scanner ler) {
        System.out.println("Lista de Reservas:");
        for (Reserva reserva : reservas) {
            for (int i = 0; i < reservas.size(); i++) {
                //System.out.println( i + reservas.get(i));
            }
        }
        System.out.println("Introduza o numero da reserva que deseja alterar");
        int indice = Integer.parseInt(ler.nextLine());

        if (indice < reservas.size() || indice >= 0) {
            System.out.println("Reserva invalida");
        }

        Reserva reserva = reservas.get(indice);
        int opcao;
        do {
            System.out.println("O que deseja alterar?");
            System.out.println("1. Cliente");
            System.out.println("2. Viatura");
            System.out.println("3. Data");
            System.out.println("4. Hora");
            System.out.println("5. Origem");
            System.out.println("6. Destino");
            System.out.println("0. Concluir");

            opcao = Integer.parseInt(ler.nextLine());

            switch (opcao){
                case 1:
                    System.out.println("Indique o nome do cliente:");
                    String cliente = ler.nextLine();
                    break;
                case 2:
                    System.out.println("Indique o nome do viatura:");
                    String viatura = ler.nextLine();
                    break;
                case 3:
                    System.out.println("Indique a data que pretenda reservar (em formato de dd/MM/yyyy):");
                    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate data = LocalDate.parse(ler.nextLine(), formatoData);
                    break;
                case 4:
                    System.out.println("Indique a hora que pretenda reservar (em formato de HH:mm):");
                    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime hora = LocalTime.parse(ler.nextLine(), formatoHora);
                    break;
                case 5:
                    System.out.println("Indique a sua atual morada:");
                    String moradaOrigem = ler.nextLine();
                    break;
                case 6:
                    String moradaDestino = ler.nextLine();
                    System.out.println("Indique a distância:");
                    break;
                case 0:
                    System.out.println("Alterações concluidas");
                    break;
            }
        } while (opcao == 0);
    }

    private void consultarReservas(Scanner ler) {
        System.out.println("Lista de Reservas");
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    void criarReserva(Scanner ler, Cliente cliente, Viatura viatura) {
        System.out.println("Indique a data que pretenda reservar (em formato de dd/MM/yyyy):");
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime data = LocalDateTime.parse(ler.nextLine(), formatoData);
        System.out.println("Indique a hora que pretenda reservar (em formato de HH:mm):");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime hora = LocalTime.parse(ler.nextLine(), formatoHora);
        System.out.println("Indique a sua atual morada:");
        String moradaOrigem = ler.nextLine();
        System.out.println("Indique o destino:");
        String moradaDestino = ler.nextLine();
        System.out.println("Indique a distância:");
        double distancia = ler.nextDouble();
        Reserva reserva = new Reserva(cliente, viatura, data, hora, moradaOrigem, moradaDestino, distancia);
        reserva.add(reservas);
    }

    void removerReserva(Scanner ler) {
        System.out.println("Lista de Reservas");
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
        System.out.println("Escreva o nome do cliente da reserva que deseja remover:");
        String nome = ler.nextLine();
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().getNome().equals(nome)) {
                reservas.remove(reserva);
                System.out.println("A Reserva" + nome + "removida com sucesso!");
                break;
            }
        }
    }

    void Viagens(Scanner ler) {
        /*Permitir trasnformar uma reserva em viagem
         * Validar se a viagem já existe antes de inserir
         * */

        /*
         * Criar um SUb menu para Registrar Viagens e Eliminar
         * */
        //transformarReservaEmViagem(ler);
        //criarViagem(ler);
        removerViagem(ler);

    }

    void transformarReservaEmViagem(Scanner ler, Cliente cliente, Condutor condutor, Viatura viatura) {

        if (reservas.isEmpty()) {
            System.out.println("Não existe nenhuma reserva para transformar em viagem.");
            return;
        }


        if (condutores.isEmpty()) {
            System.out.println("Não existe nenhum condutor registado. Registe um condutor primeiro.");
            return;
        }

        System.out.println("=== Escolha a Reserva a Transformar ===");
        for (int i = 0; i < reservas.size(); i++) {
            System.out.println((i + 1) + ". " + reservas.get(i).toString());
        }

        System.out.print("Introduza o número da reserva: ");
        int indexReserva = ler.nextInt() - 1;
        ler.nextLine();

        if (indexReserva < 0 || indexReserva >= reservas.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        Reserva reservaSelecionada = reservas.get(indexReserva);

        for (Viagem v : viagens) {
            if (v.getCliente().equals(reservaSelecionada.getCliente()) &&
                    v.getInicio().equals(reservaSelecionada.getDataHoraInicio())) {
                System.out.println("Erro: Esta viagem já foi registada anteriormente!");
                return;
            }
        }

        System.out.println("=== Escolha o Condutor ===");
        for (int i = 0; i < condutores.size(); i++) {
            System.out.println((i + 1) + ". " + condutores.get(i).toString());
        }

        System.out.print("Introduza o número do condutor: ");
        int indexCondutor = ler.nextInt() - 1;
        ler.nextLine();

        if (indexCondutor < 0 || indexCondutor >= condutores.size()) {
            System.out.println("Condutor inválido.");
            return;
        }

        Condutor condutorSelecionado = condutores.get(indexCondutor);

        //Viagem novaViagem = new Viagem(cliente, condutor, viatura, reservaSelecionada, condutorSelecionado);
        //viagens.add(novaViagem);

        System.out.println("Sucesso! A reserva foi transformada em viagem.");
    }

    void criarViagem(Scanner ler, Cliente cliente, Condutor condutor, Viatura viatura) {
        System.out.println("Indique a hora de inicio:");
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("Indique a hora:");
        LocalTime hora = LocalTime.parse(ler.nextLine(), formatter);
        System.out.println("Indique a data");
        LocalDateTime dataViagem = LocalDateTime.parse(ler.nextLine(), formatterData);
        System.out.println("Indique a morada de origem:");
        String moradaOrigem = ler.nextLine();
        System.out.println("Indique a morada de destino:");
        String moradaDestino = ler.nextLine();
        System.out.println("Indique a custo da viagem:");
        double custoViagem = ler.nextDouble();
        System.out.println("Indique a distancia percorrida:");
        double distancia = ler.nextDouble();
        boolean concluida = false;
        Viagem viagem = new Viagem(cliente, condutor, viatura, dataViagem, hora, concluida, moradaOrigem, moradaDestino, custoViagem);
        viagem.add(viagens);
    }

    void removerViagem(Scanner ler) {
    }

    void informacoes(Scanner ler) {
        /*Pesquisar viagens de um cliente num intervalo de data dada pelo liente
         *Apresentar valor total faturado por um motorista num intervalo de datas indicado pelo utilizador
         * Apresentar a distância media em kms das viagens num intervalo de data
         * Apresentar o destino mais solicitado (reservas e viagens) durante intervalo de data
         * Apresentar lista de clientes em viagens a distância esteja dentro do indicado pelo utilizador
         *
         */
    }

    int menuInformacoes(Scanner ler){
        int count = 1;
        limparConsola();
        printTituloPrincipal();
        printTituloSecundario("Informações");
        if(!viagens.isEmpty()){
            count++;
            System.out.printf(VERDE + "%d\t-\tPesquisar Viagem\n" + RESET, count);
        }
        if(!condutores.isEmpty()){
            count++;
            System.out.printf(VERDE + "%d\t-\tPesquisar Valor Faturado por Motorista\n" + RESET, count);
        }
        if(!viaturas.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tDistância Média das Viagens\n" + RESET, count);
        }
        if(!reservas.isEmpty() || !viagens.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tDestino mais solicitado\n" + RESET, count);
        }
        if(!reservas.isEmpty() || !viagens.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tDestino mais solicitado\n" + RESET, count);
        }
        System.out.println(VERDE + "0\t-\tVoltar ao menu anterior" + RESET);
        System.out.print("Indique a opção que queira realizar: ");
        String opcao = ler.nextLine();
        return Integer.parseInt(opcao);
    }
}