//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.lang.reflect.Array;
import java.time.LocalDate;
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


    ArrayList<Viatura> viaturas = new ArrayList<>();

    private final String CAMINHO_FICHEIRO_LOGS_ERROS_VIATURAS = "logsErrosViaturas.txt";



    ArrayList<Viagem> viagens = new ArrayList<>();
    ArrayList<Reserva>reservas = new ArrayList<>();
    Cliente cliente = new Cliente();
    Condutor condutor = new Condutor();
    Viatura viatura = new Viatura();
    Reserva reserva = new Reserva();
    EmpresaTVDE empresaTVDE = new EmpresaTVDE();
    //Antes de qualuqer coisa temos que carregar os itens da memória pra ca e guardar em um arraylist e depois irmos consultando

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
            break;
        } while (opcao != 0);
    }

    /*Reset*/
    public static final String RESET = "\u001B[0m";

    /*Cores*/
    public static final String AZUL = "\u001B[34m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String VERMELHO = "\u001B[31m";

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
    public static void printTituloPrincipal(String texto) {
        System.out.println();
        printCentralizado(NEGRITO + AZUL + texto + RESET);
        System.out.println();
    }

    /*Função titulo secundario */
    public static void printTituloSecundario(String texto) {
        printCentralizado(VERDE + texto + RESET);
        System.out.println();
    }


    int menu(Scanner ler) {
        printTituloPrincipal("========= Sistema de Viagens TVDE ===========");
        printTituloSecundario(VERDE + "MENU" + RESET);
        System.out.println("1. Registar o/a Cliente");
        System.out.println("2. Registar o/a Condutor");
        System.out.println("3. Viaturas");
        System.out.println("4. Reservas");
        System.out.println("5. Viagens");
        System.out.println("6. Informações");
        System.out.println("0. Sair");
        System.out.print("Indique a opção que queira realizar utilizando os números de 0 a 6.");
        int opcao = Integer.parseInt(ler.nextLine());
        return Integer.parseInt(ler.nextLine());
    }

    void registarCliente(Scanner ler) {
        try {
            System.out.println("--- Novo Registo de Cliente (Escreva 'sair' para cancelar) ---");

            System.out.println("Indique o nome:");
            String nome = ler.nextLine();
            if (nome.equalsIgnoreCase("sair")) return;

            System.out.println("Indique a idade:");
            String idadeStr = ler.nextLine();
            if (idadeStr.equalsIgnoreCase("sair")) return;
            int idade = Integer.parseInt(idadeStr);

            System.out.println("Indique o seu género:");
            String sexo = ler.nextLine();
            if (sexo.equalsIgnoreCase("sair")) return;

            System.out.println("Indique o seu email:");
            String email = ler.nextLine();
            if (email.equalsIgnoreCase("sair")) return;

            System.out.println("Indique o número de telefone:");
            String telStr = ler.next();
            if (telStr.equalsIgnoreCase("sair")) return;
            int telefone = Integer.parseInt(telStr);

            System.out.println("Indique a sua morada:");
            String morada = ler.nextLine();
            if (morada.equalsIgnoreCase("sair")) return;

            String ccStr;
            int cc = 0;

            while (true) {
                System.out.println("Indique o número de cartão de cidadão (8 primeiros dígitos):");
                ccStr = ler.nextLine();

                if (ccStr.equalsIgnoreCase("sair")) {
                    return;
                }
                if (ccStr.matches("\\d{8}")) {
                    cc = Integer.parseInt(ccStr);
                    break;
                } else {
                    System.out.println("Erro: O cartão de cidadão deve conter exatamente 8 número. Tente novamente.");
                }
            }

            while (true) {
                System.out.println("Indique o NIF (Contribuinte):");
                String nifStr = ler.nextLine();

                if (nifStr.equalsIgnoreCase("sair")) return;

                if (!nifStr.matches("\\d{9}")) {
                    System.out.println("Erro: O NIF deve conter exatamente 9 digitos numéricos.");
                    continue;
                }
                int nif = Integer.parseInt(nifStr);

                if (empresaTVDE.procurarCliente(nif) != null) {
                    System.out.println("Erro : Esse NIF já está registado no Sistema. Tente outro.");
                } else {
                    Cliente novo = new Cliente(nome, idade, sexo, email, telefone, morada, cc, nif);
                    empresaTVDE.adicionarCliente(novo);
                    System.out.println("Cliente registado com sucesso!");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Dados inválidos.");
            empresaTVDE.adicionarLogsDeErros(empresaTVDE.CAMINHO_FICHEIRO_LOGS_CLIENTES, "Erro registo: " + e.getMessage())


        }
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
        condutor.add(condutores);
    }


    void Viaturas(Scanner ler){
        int opcao;
        do {
            opcao = subMenuViaturas(ler);
            if (opcao == 1) {
                registarViatura(ler);
            } else if (!viaturas.isEmpty() && opcao == 2) {
                while(true) {
                    System.out.println("Digite a Matrícula que deseja buscar [XX-XX-XX]");
                    String matricula = ler.nextLine();
                    if(isMatriculaValida(matricula)){
                        pesquisarViaturaPelaMatricula(ler, matricula);
                        break;
                    }else {
                        System.out.println("Formato incorreto, tente novamente com o formato [XX-XX-XX]");
                    }
                }
            } else if (!viaturas.isEmpty() && opcao == 3) {
                String matricula = "";
                removerViatura(ler);
            } else if (opcao == 0) {
                System.out.print("Obrigado por utilizar a App da TVDE!!");
            } else {
                System.out.println("Opção Inválida! Tente novamente!");
            }
        } while (opcao != 0);
    }

    void tituloViaturas(){

    }

    int subMenuViaturas(Scanner ler){
        int count = 1;
        System.out.println("========= VIATURAS ===========");
        System.out.println("            MENU            ");
        System.out.printf("%d\t-\tRegistar Viatura", count);
        if(!viaturas.isEmpty()){
            count++;
            System.out.printf("%d\t-\tPesquisar Viatura pela Matrícula", count);
        }
        if(!viaturas.isEmpty()) {
            count++;
            System.out.printf("%d\t-\tRemover Viatura", count);
        }
        System.out.println("0\t-\tVoltar ao menu anterior");
        System.out.print("Indique a opção que queira realizar");
        int opcao = Integer.parseInt(ler.nextLine());
        return Integer.parseInt(ler.nextLine());
    }


    //Feito na classe Empresa.
    void registarViatura(Scanner ler) {
        try {
            /*("========= VIATURAS ===========") */
            System.out.println("--- Novo Registo (Escreva 'sair' para cancelar) ---");

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
                matricula = ler.nextLine();

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
                    Viatura viatura = new Viatura(matricula, marca, modelo, Integer.parseInt(anoDeFabrico), cor, existe);
                    viaturas.add(viatura);
                    System.out.println("Viatura registada com sucesso!");
                    empresaTVDE.adicionarViatura(viatura);
                    break;
                }
            }
        } catch (Exception e) {
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIATURAS, e.getMessage() + ";");
        }
    }

    public boolean isMatriculaValida(String matricula) {
        if (matricula == null) return false;
        String regex = "^[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}$";

        return matricula.trim().toUpperCase().matches(regex);
    }

    private void verListaDeClientes(Scanner ler, String matricula) {
    }

    void removerViatura(Scanner ler) {
    }

    void pesquisarViaturaPelaMatricula(Scanner ler, String matricula) {
        for (var viatura : viaturas){
            viatura.toString();
            System.out.println("Aperte qualquer tecla para continuar ...");
        }
    }

    void Reservas(Scanner ler) {
        /*Validar antes de inserir se a reserva já existe*/



    /*
    Fazer um switch case
    * Criar um SUb menu para Registrar Reservas e Eliminar
    * */
        criarReserva(ler);
        consultarReservas(ler);
        alterarReserva(ler);
        removerReserva(ler);
    }

    private void alterarReserva(Scanner ler) {
        System.out.println("Lista de Reservas:");
        for (Reserva reserva : reservas) {
            for (int i = 0; i < reservas.size(); i++) {
                System.out.println( i + reservas.get(i));
            }
        }
        System.out.println("Introduza o numero da reserva que deseja alterar");
        int indice = Integer.parseInt(ler.nextLine());

        if (indice < reservas.size() || indice >= 0) {
            System.out.println("Reserva invalida");
        }

        Reserva reserva = reservas.get(indice);
        int opçao;
        do {
            System.out.println("O que deseja alterar?");
            System.out.println("1. Cliente");
            System.out.println("2. Viatura");
            System.out.println("3. Data");
            System.out.println("4. Hora");
            System.out.println("5. Origem");
            System.out.println("6. Destino");
            System.out.println("0. Concluir");

            opçao = Integer.parseInt(ler.nextLine());

            switch (opçao){
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
        } while (opçao = 0);
    }

    private void consultarReservas(Scanner ler) {
        System.out.println("Lista de Reservas");
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    void criarReserva(Scanner ler) {
        System.out.println("Indique a data que pretenda reservar (em formato de dd/MM/yyyy):");
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(ler.nextLine(), formatoData);
        System.out.println("Indique a hora que pretenda reservar (em formato de HH:mm):");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime hora = LocalTime.parse(ler.nextLine(), formatoHora);
        System.out.println("Indique a sua atual morada:");
        String moradaOrigem = ler.nextLine();
        System.out.println("Indique o destino:");
        String moradaDestino = ler.nextLine();
        System.out.println("Indique a distância:");
        double distancia = ler.nextDouble();
        Reserva reserva = new Reserva(cliente, data, hora, moradaOrigem, moradaDestino, distancia);
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
        transformarReservaEmViagem(ler);
        criarViagem(ler);
        removerViagem(ler);

    }

    void transformarReservaEmViagem(Scanner ler) {

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
                    v.getDataHoraInicio().equals(reservaSelecionada.getDataHoraInicio())) {
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

        Viagem novaViagem = new Viagem(reservaSelecionada, condutorSelecionado);
        viagens.add(novaViagem);

        System.out.println("Sucesso! A reserva foi transformada em viagem.");
    }

    void criarViagem(Scanner ler) {
        System.out.println("Indique a hora de inicio:");
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("Indique a hora:");
        LocalTime hora = LocalTime.parse(ler.nextLine(), formatter);
        System.out.println("Indique a data");
        LocalDate dataViagem = LocalDate.parse(ler.nextLine(), formatterData);
        System.out.println("Indique a morada de origem:");
        String moradaOrigem = ler.nextLine();
        System.out.println("Indique a morada de destino:");
        String moradaDestino = ler.nextLine();
        System.out.println("Indique a custo da viagem:");
        double custoViagem = ler.nextDouble();
        System.out.println("Indique a distancia percorrida:");
        double distancia = ler.nextDouble();
        Viagem viagem = new Viagem(cliente, condutor, viatura, dataViagem, hora, moradaDestino, moradaOrigem, custoViagem, distancia, concluida);
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
}