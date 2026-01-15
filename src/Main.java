//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

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

    private final String CAMINHO_FICHEIRO_LOGS_ERROS_VIATURAS = "logsErrosViaturas.txt";
    private final String CAMINHO_FICHEIRO_LOGS_ERROS_CLIENTE = "logsErrosClientes.txt";
    private final String CAMINHO_FICHEIRO_LOGS_ERROS_CONDUTOR = "logsErrosCondutor.txt";

    EmpresaTVDE empresaTVDE = new EmpresaTVDE();
    ArrayList<Cliente> clientes = empresaTVDE.carregarClientes();
    ArrayList<Condutor> condutores = empresaTVDE.carregarCondutores();
    ArrayList<Viatura> viaturas = empresaTVDE.carregarViaturas();
    ArrayList<Reserva> reservas = new ArrayList<>();
    ArrayList<Viagem> viagens = new ArrayList<>();


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

        clientes = empresaTVDE.carregarClientes();
        condutores = empresaTVDE.carregarCondutores();

        int opcao;
        Scanner ler = new Scanner(System.in);
        do {
            opcao = menu(ler);
            switch (opcao) {
                case 1:
                    Clientes(ler);
                    break;
                case 2:
                    Condutores(ler);
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

    //region Validações
    public boolean isMatriculaValida(String matricula) {
        if (matricula == null) return false;
        String regex = "^[A-Z0-9]{6}$";

        return matricula.trim().toUpperCase().matches(regex);
    }
    public boolean isMarcaValida (String marca) {
        if (marca == null) return false;
        String regex = "^[A-Z]$";

        return marca.trim().matches(regex);
    }public boolean isCorValida (String cor) {
        if (cor == null) return false;
        String regex = "^[A-Z]$";

        return cor.trim().matches(regex);
    }
    public boolean isAnoDeFabricoValido (String anoDeFabrico) {
        if (anoDeFabrico == null) return false;
        String regex = "^(200[1-9]|201[0-9]|202[0-6])$";

        return anoDeFabrico.trim().matches(regex);
    }
    public boolean isModeloValido (String modelo) {
        if (modelo == null) return false;

        return modelo.trim().isEmpty();
    }
    public boolean isNifValido(String nif) {
        if (nif == null) return false;
        String regex = "^\\d{9}$";

        return nif.trim().matches(regex);
    }
    public boolean isCcValido(String cc) {
        if (cc == null) return false;
        String regex = "^\\d{8}$";

        return cc.trim().matches(regex);
    }
    public boolean isCartaValido (String carta) {
        if (carta == null) return false;
        String regex = "^[A-Z]-\\\\d{7}$";

        return carta.trim().matches(regex);
    }
    public boolean isIdadeValido(String idade) {
        if (idade == null) return false;
        String regex = "^(?:[1-9][0-9]?|1[0-2][0-9])$";

        return idade.trim().matches(regex);
    }
    public boolean isSexoValido(String sexo) {
        if (sexo == null) return false;
        return sexo.equalsIgnoreCase("1") || sexo.equalsIgnoreCase("2") || sexo.equalsIgnoreCase("3");
    }
    public boolean isEmailValido(String email) {
        if (email == null) return false;
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        return email.trim().toLowerCase().matches(regex);
    }
    public boolean isTelefoneValido(String telefone) {
        if (telefone == null) return false;
        String regex = "^\\d{9}$";

        return telefone.trim().matches(regex);
    }
    public boolean isMoradaValido(String morada) {
        if (morada == null) return false;
        String regex = "^.+ \\d{4}-\\d{3}$";

        return morada.toLowerCase().matches(regex);
    }
    public boolean opcaoSair(String texto) {
        if (texto == null) return false;
        return texto.equalsIgnoreCase("sair");
    }


    public static String toCapitalize(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        String[] palavras = texto.split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palavra : palavras) {
            if (palavra.length() > 0) {
                resultado.append(Character.toUpperCase(palavra.charAt(0)))
                        .append(palavra.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return resultado.toString().trim();
    }
    //endregion

    //region Clientes
    void Clientes(Scanner ler) {
        int opcao;
        do {
            opcao = subMenuClientes(ler);
            if (opcao == 1) {
                registarCliente(ler);
            } else if (!clientes.isEmpty() && opcao == 2) {
                pesquisarCliente(ler);
            }else if (!clientes.isEmpty() && opcao == 3){
                atualizarCliente(ler);
            } else if (!clientes.isEmpty() && opcao == 4) {
                removerCliente(ler);
            } else if (opcao == 0) {
                break;
            } else {
                System.out.println("Opção Inválida!");
            }
        } while (opcao != 0);
    } //Completo

    int subMenuClientes(Scanner ler) {
        int count = 1;
        limparConsola();
        printTituloPrincipal();
        printTituloSecundario("CLIENTES");
        System.out.printf(VERDE + "%d\t-\tRegistar Cliente\n" + RESET, count);
        if (!clientes.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tPesquisar Cliente\n" + RESET, count);
        }
        if (!clientes.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tAtualizar Cliente\n" + RESET, count);
        }
        if (!clientes.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tRemover Cliente\n" + RESET, count);
        }
        System.out.println(VERDE + "0\t-\tVoltar ao menu anterior" + RESET);
        System.out.print("Indique a opção que queira realizar: ");
        try { return Integer.parseInt(ler.nextLine()); } catch (Exception e) { return -1; }
    } //Completo

    private void registarCliente(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Novo Registo de Cliente (Escreva 'sair' para cancelar) ---\n\n" + RESET);

            String ccStr, sexo, email, morada;
            int cc, idade, telefone, nif;

            while (true) {
                System.out.print("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine();

                if (opcaoSair(nifStr)) return;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }

                nif = Integer.parseInt(nifStr);

                if (empresaTVDE.procurarNifCliente(nif) != null) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Esse NIF já está registado no sistema. Tente outro.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Indique o número de cartão de cidadão (8 primeiros dígitos): ");
                ccStr = ler.nextLine();

                if (opcaoSair(ccStr)) return;
                if (!isCcValido(ccStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O cartão de cidadão deve conter exatamente 8 números. Tente novamente.");
                    continue;
                }

                cc = Integer.parseInt(ccStr);

                if (empresaTVDE.procurarCartaoDeCidadaoCliente(cc) != null) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Esse cartão de cidadão já está registado no sistema. Tente outro.");
                } else {
                    break;
                }
            }

            System.out.print("Indique o seu nome: ");
            String nome = ler.nextLine();
            if (opcaoSair(nome)) return;

            while (true) {
                System.out.print("Indique a sua idade: ");
                String idadeStr = ler.nextLine();
                if (opcaoSair(idadeStr)) return;
                if (!isIdadeValido(idadeStr)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A idade deve estar no formato correto. Tente novamente.");
                    continue;
                }
                idade = Integer.parseInt(idadeStr);
                break;
            }

            while (true) {
                System.out.print("Indique o seu género:\n1\t-\tMasculino\n2\t-\tFeminino\n3\t-\tOutro\n ");
                sexo = ler.nextLine().toLowerCase();
                if (opcaoSair(sexo)) return;
                if (!isSexoValido(sexo)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O sexo informado não está entre os válidos. Tente novamente.");
                    continue;
                }
                if(sexo.equalsIgnoreCase("1")) sexo = "Masculino";
                if(sexo.equalsIgnoreCase("2")) sexo = "Feminino";
                if(sexo.equalsIgnoreCase("3")) sexo = "Outro";
                break;
            }

            while (true) {
                System.out.print("Indique o seu email: ");
                email = ler.nextLine();
                if (opcaoSair(email)) return;
                if (!isEmailValido(email)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O email informado não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Indique o número de telefone: ");
                String telStr = ler.nextLine();
                if (opcaoSair(telStr)) return;
                if (!isTelefoneValido(telStr)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O telefone informado não está no formato correto. Tente novamente.");
                    continue;
                }
                telefone = Integer.parseInt(telStr);
                break;
            }

            while (true) {
                System.out.print("Indique a sua morada [Rua de Santa catarina, 123 - 3210-450]: ");
                morada = ler.nextLine();
                if (opcaoSair(morada)) return;
                if (!isMoradaValido(morada)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
            }

            Cliente cliente = new Cliente(toCapitalize(nome), idade, toCapitalize(sexo), email, telefone, morada, cc, nif);
            if (empresaTVDE.adicionarCliente(cliente)) {
                System.out.print(VERDE_BRILHANTE + "\n\nCliente registado com sucesso!\n\n" + RESET);
                clientes = empresaTVDE.carregarClientes();
            }
        } catch (Exception e) {
            System.out.println("Dados inválidos.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_CLIENTE, e.getMessage());
        }
    } //Completo

    private void pesquisarCliente(Scanner ler){
        while (true) {
            System.out.print(ROXO + "\n\n--- Buscar Cliente (Escreva 'sair' para cancelar) ---\n\n" + RESET);

            System.out.print("Indique o NIF: ");
            String nifStr = ler.nextLine();
            if (!isNifValido(nifStr)) {
                System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                continue;
            }
            if (opcaoSair(nifStr)) break;

            try {
                int nif = Integer.parseInt(nifStr);
                Cliente cliente = empresaTVDE.procurarNifCliente(nif);
                if (cliente != null) {
                    System.out.println(cliente.toString());
                    System.out.println("Enter para continuar...");
                    ler.nextLine();
                    break;
                } else System.out.println("Cliente não encontrado.");
            } catch (NumberFormatException e) {
                System.out.println("NIF inválido.");
            }
        }
    } //Completo

    void atualizarCliente(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Atualizar Cliente (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            while (true) {
                System.out.print("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine();

                if (opcaoSair(nifStr)) return;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }

                int nif = Integer.parseInt(nifStr);
                Cliente cliente = empresaTVDE.procurarNifCliente(nif);
                if (cliente != null) {
                    while (true) {
                        System.out.println("Escolha a opção que deseja atualizar:");
                        System.out.println("1\t-\tNome");
                        System.out.println("2\t-\tIdade");
                        System.out.println("3\t-\tGénero");
                        System.out.println("4\t-\tEmail");
                        System.out.println("5\t-\tTelefone");
                        System.out.println("6\t-\tMorada");
                        System.out.println("7\t-\tCartão de Cidadão");
                        System.out.println("0\t-\tSair");
                        String opcao = ler.nextLine();

                        String nome = "", sexo = "", email = "", morada = "";
                        int cc = 0, idade = 0, telefone = 0;

                        switch (Integer.parseInt(opcao)) {
                            case 1:
                                System.out.print("Indique o seu nome: ");
                                nome = ler.nextLine();
                                if (opcaoSair(nome)) return;
                                try {
                                    cliente.setNome(nome);
                                    System.out.println(VERDE_BRILHANTE + "\n\nCliente atualizado com sucesso!\n\n" + RESET);
                                } catch (Exception e) {
                                    System.out.println("Erro: Cliente não encontrado ou não pode ser atualizado.");
                                }
                                break;
                            case 2:
                                while (true) {
                                    System.out.print("Indique a sua idade: ");
                                    String idadeStr = ler.nextLine();
                                    if (opcaoSair(idadeStr)) return;
                                    if (!isIdadeValido(idadeStr)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A idade deve estar no formato correto. Tente novamente.");
                                        continue;
                                    } else {
                                        idade = Integer.parseInt(idadeStr);
                                        cliente.setIdade(idade);
                                        System.out.println(VERDE_BRILHANTE + "\n\nCliente atualizado com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case 3:
                                while (true) {
                                    System.out.print("Indique o seu género:\n1\t-\tMasculino\n2\t-\tFeminino\n3\t-\tOutro\n ");
                                    sexo = ler.nextLine().toLowerCase();
                                    if (opcaoSair(sexo)) return;
                                    if (!isSexoValido(sexo)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O sexo informado não está entre os válidos. Tente novamente.");
                                        continue;
                                    }
                                    if (sexo.equalsIgnoreCase("1")) sexo = "Masculino";
                                    if (sexo.equalsIgnoreCase("2")) sexo = "Feminino";
                                    if (sexo.equalsIgnoreCase("3")) sexo = "Outro";

                                    cliente.setSexo(sexo);
                                    System.out.println(VERDE_BRILHANTE + "\n\nCliente atualizado com sucesso!\n\n" + RESET);
                                    break;
                                }
                                break;
                            case 4:
                                while (true) {
                                    System.out.print("Indique o seu email: ");
                                    email = ler.nextLine();
                                    if (opcaoSair(email)) return;
                                    if (!isEmailValido(email)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O email informado não está no formato correto. Tente novamente.");
                                        continue;
                                    }
                                    break;
                                }
                                break;
                            case 5:
                                while (true) {
                                    System.out.print("Indique o número de telefone: ");
                                    String telStr = ler.nextLine();
                                    if (opcaoSair(telStr)) return;
                                    if (!isTelefoneValido(telStr)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O telefone informado não está no formato correto. Tente novamente.");
                                        continue;
                                    }
                                    telefone = Integer.parseInt(telStr);
                                    break;
                                }
                                break;
                            case 6:
                                while (true) {
                                    System.out.print("Indique a sua morada [Rua de Santa catarina, 123 - 3210-450]: ");
                                    morada = ler.nextLine();
                                    if (opcaoSair(morada)) return;
                                    if (!isMoradaValido(morada)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                                        continue;
                                    }
                                    break;
                                }
                                break;
                            case 7:
                                while (true) {
                                    System.out.print("Indique o número de cartão de cidadão (8 primeiros dígitos): ");
                                    String ccStr = ler.nextLine();

                                    if (opcaoSair(ccStr)) return;
                                    if (!isCcValido(ccStr)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O cartão de cidadão deve conter exatamente 8 números. Tente novamente.");
                                        continue;
                                    }

                                    cc = Integer.parseInt(ccStr);

                                    if (empresaTVDE.procurarCartaoDeCidadaoCliente(cc) != null) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Esse cartão de cidadão já está registado no sistema. Tente outro.");
                                    } else {
                                        break;
                                    }
                                }
                                break;
                            case 0:
                                return;
                            default:
                                System.out.print("Opção invalida. Tente novamente.");
                                break;
                        }

                    }
//                    System.out.println("Indique o seu novo nome:");
//                    String nome = ler.nextLine();
//                    if (nome.equalsIgnoreCase("sair")) return;
//
//                    System.out.println("Indique a sua nova idade:");
//                    String idadeStr = ler.nextLine();
//                    if (idadeStr.equalsIgnoreCase("sair")) return;
//                    int idade = Integer.parseInt(idadeStr);
//
//                    System.out.println("Indique o seu novo género:");
//                    String sexo = ler.nextLine();
//                    if (sexo.equalsIgnoreCase("sair")) return;
//
//                    System.out.println("Indique o seu novo email:");
//                    String email = ler.nextLine();
//                    if (email.equalsIgnoreCase("sair")) return;
//
//                    System.out.println("Indique o novo número de telefone:");
//                    String telStr = ler.nextLine();
//                    if (telStr.equalsIgnoreCase("sair")) return;
//                    int telefone = Integer.parseInt(telStr);
//
//                    System.out.println("Indique a sua nova morada:");
//                    String morada = ler.nextLine();
//                    if (morada.equalsIgnoreCase("sair")) return;
//
//                    String ccStr;
//                    int cc = 0;
//
//                    while (true) {
//                        System.out.println("Indique o novo número de cartão de cidadão (8 primeiros dígitos):");
//                        ccStr = ler.nextLine();
//
//                        if (ccStr.equalsIgnoreCase("sair")) {
//                            return;
//                        }
//                        if (!ccStr.matches("\\d{8}")) {
//                            System.out.println("Erro: O cartão de cidadão deve conter exatamente 8 números. Tente novamente.");
//                            continue;
//                        }
//                        cc = Integer.parseInt(ccStr);
                }
                clientes = empresaTVDE.carregarClientes();
                break;
//                    }
//                    break;
//                }
            }
        }catch(Exception e){
            System.out.println("Dados inválidos.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_CLIENTE, e.getMessage());
        }
    }

    void removerCliente(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Remover Cliente (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            while (true) {
                System.out.print("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine();
                if (opcaoSair(nifStr)) break;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }
                int nif = Integer.parseInt(nifStr);
                String nomeCliente = empresaTVDE.procurarNifCliente(nif).getNome();
                System.out.printf("Tem certeza que deseja remover o cliente %s ? [S/N]", nomeCliente);
                String validacao = ler.nextLine().trim().toUpperCase();
                if(validacao.equalsIgnoreCase("S")){
                    if (empresaTVDE.removerCliente(nif)) {
                        System.out.printf("Cliente %s removido com sucesso.", nomeCliente);
                        clientes = empresaTVDE.carregarClientes();
                        break;
                    } else {
                        System.out.println("Erro: Cliente não encontrado ou não pode ser removido.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro de input.");
        }
    } //Completo Dinis?? precisa de verificação :)
    //endregion

    //region Condutores
    void Condutores (Scanner ler){
        int opcao;
        do {
            opcao = subMenuCondutores(ler);
            if (opcao == 1) {
                registarCondutor(ler);
            } else if (!condutores.isEmpty() && opcao == 2) {
                pesquisarCondutor(ler);
            } else if (!condutores.isEmpty() && opcao == 3) {
                atualizarCondutor(ler);
            }else if (!condutores.isEmpty() && opcao == 4) {
                removerCondutor(ler);
            } else if (opcao == 0) {
                break;
            } else {
                System.out.println("Opção Inválida!");
            }
        } while (opcao != 0);
    } //Completo Dinis :)

    int subMenuCondutores(Scanner ler) {
        int count = 1;
        limparConsola();
        printTituloPrincipal();
        printTituloSecundario("CONDUTORES");
        System.out.printf(VERDE + "%d\t-\tRegistar Condutores\n" + RESET, count);
        if (!condutores.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tPesquisar Condutores\n" + RESET, count);
        }
        if (!condutores.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tAtualizar Condutores\n" + RESET, count);
        }
        if (!condutores.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tRemover Condutores\n" + RESET, count);
        }
        System.out.println(VERDE + "0\t-\tVoltar ao menu anterior" + RESET);
        System.out.print("Indique a opção que queira realizar: ");
        try { return Integer.parseInt(ler.nextLine()); } catch (Exception e) { return -1; }
    } //Completo Dinis :)

    void registarCondutor(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Novo Condutor (Escreva 'sair' para cancelar) ---\n\n" + RESET);

            String ccStr, sexo, email, morada, cartaStr;
            int cc, idade, telefone, nif;

            while (true) {
                System.out.println("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine();

                if (opcaoSair(nifStr)) return;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }

                nif = Integer.parseInt(nifStr);

                if (empresaTVDE.procurarNifCondutor(nif) != null) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET +"Esse NIF já está registado no sistema. Tente outro.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Indique o número de cartão de cidadão (8 primeiros dígitos): ");
                ccStr = ler.nextLine();

                if (opcaoSair(ccStr)) return;
                if (!isCcValido(ccStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O cartão de cidadão deve conter exatamente 8 números. Tente novamente.");
                    continue;
                }
                cc = Integer.parseInt(ccStr);

                if (empresaTVDE.procurarCartaoDeCidadaoCondutor(cc) != null) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Esse cartão de cidadão já está registado no sistema. Tente outro.");
                } else {
                    break;
                }

            }

            while (true) {
                System.out.println("Indique a Carta de Condução (Formato: L-1234567):");
                cartaStr = ler.nextLine().toUpperCase().trim();

                if (opcaoSair(cartaStr)) return;
                if (!isCartaValido(cartaStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A carta de condução deve conter exatamente uma letra e sete números (L-1234567). Tente novamente.");
                    continue;
                }
                if (empresaTVDE.procurarCartaDeConducaoCondutor(cartaStr) != null) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Essa carta de condução já está registada no sistema. Tente outro.");
                } else {
                    break;
                }
            }

            System.out.println("Indique o seu nome: ");
            String nome = ler.nextLine();
            if (opcaoSair(nome)) return;

            while (true) {
                System.out.println("Indique a sua idade:");
                String idadeStr = ler.nextLine();
                if ((opcaoSair(idadeStr))) return;
                if (!isIdadeValido(idadeStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A idade deve estar no formato correto. Tente novamente.");
                    continue;
                }
                idade = Integer.parseInt(idadeStr);
                break;
            }

            while (true) {
                System.out.print("Indique o seu género:\n1\t-\tMasculino\n2\t-\tFeminino\n3\t-\tOutro\n ");
                sexo = ler.nextLine().toLowerCase();
                if (opcaoSair(sexo)) return;
                if (!isSexoValido(sexo)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O sexo informado não está entre os válidos. Tente novamente.");
                    continue;
                }
                if(sexo.equalsIgnoreCase("1")) sexo = "Masculino";
                if(sexo.equalsIgnoreCase("2")) sexo = "Feminino";
                if(sexo.equalsIgnoreCase("3")) sexo = "Outro";
                break;
            }

            while (true) {
                System.out.print("Indique o seu email: ");
                email = ler.nextLine();
                if (opcaoSair(email)) return;
                if (!isEmailValido(email)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O email informado não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
            }
            while (true) {
                System.out.println("Indique a sua morada [Rua de Santa catarina, 123 - 3210-450]: ");
                morada = ler.nextLine();
                if (opcaoSair(morada)) return;
                if (!isMoradaValido(morada)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Indique o número de telefone: ");
                String telStr = ler.nextLine();
                if (opcaoSair(telStr)) return;
                if (!isTelefoneValido(telStr)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O telefone informado não está no formato correto. Tente novamente.");
                    continue;
                }
                telefone = Integer.parseInt(telStr);
                break;
            }

            Condutor condutor = new Condutor(nome,idade,sexo,email,telefone,morada,cc,cartaStr,nif);
            if (empresaTVDE.adicionarCondutor(condutor)) {
                System.out.print(VERDE_BRILHANTE + "\n\nCondutor registado com sucesso!\n\n" + RESET);
                condutores = empresaTVDE.carregarCondutores();
            }
        } catch (Exception e) {
            System.out.println("Dados inválidos.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_CONDUTOR, e.getMessage());
        }
    } //Completo Dinis :)

    private void pesquisarCondutor(Scanner ler){
        while (true) {
            System.out.print(ROXO + "\n\n--- Buscar Condutor (Escreva 'sair' para cancelar) ---\n\n" + RESET);

            System.out.print("Indique o NIF: ");
            String nifStr = ler.nextLine();
            if (!isNifValido(nifStr)) {
                System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                continue;
            }
            if (opcaoSair(nifStr)) break;

            try {
                int nif = Integer.parseInt(nifStr);
                Condutor condutor = empresaTVDE.procurarNifCondutor(nif);
                if (condutor != null) {
                    System.out.println(condutor.toString());
                    System.out.println("Enter para continuar...");
                    ler.nextLine();
                    break;
                } else System.out.println("Condutor não encontrado.");
            } catch (NumberFormatException e) {
                System.out.println("NIF inválido.");
            }
        }
    } //Completo Dinis :)

    void atualizarCondutor(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Atualizar Condutor (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            while (true) {
                System.out.println("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine();
                if (nifStr.equalsIgnoreCase("sair")) break;

                int nif = Integer.parseInt(nifStr);
                if (empresaTVDE.procurarNifCondutor(nif) != null) {
                    System.out.print(ROXO + "\n\n--- Atualização do Condutor (Escreva 'sair' para cancelar) ---\n\n" + RESET);

                    System.out.println("Indique o seu novo nome:");
                    String nome = ler.nextLine();
                    if (nome.equalsIgnoreCase("sair")) return;

                    System.out.println("Indique a sua nova idade:");
                    String idadeStr = ler.nextLine();
                    if (idadeStr.equalsIgnoreCase("sair")) return;
                    int idade = Integer.parseInt(idadeStr);

                    System.out.println("Indique o seu novo género:");
                    String sexo = ler.nextLine();
                    if (sexo.equalsIgnoreCase("sair")) return;

                    System.out.println("Indique o seu novo email:");
                    String email = ler.nextLine();
                    if (email.equalsIgnoreCase("sair")) return;

                    String cartaStr;
                    String carta = "";

                    while (true) {
                        System.out.println("Indique o novo número da carta de condução (9 dígitos numéricos):");
                        cartaStr = ler.nextLine();

                        if (cartaStr.equalsIgnoreCase("sair")) {
                            return;
                        }
                        if (!cartaStr.matches("\\d{9}")) {
                            System.out.println("Erro: A carta de condução deve conter exatamente 9 números. Tente novamente.");
                            continue;
                        }
                        carta = (cartaStr);
                        break;

                    }

                    String ccStr;
                    int cc = 0;

                    while (true) {
                        System.out.println("Indique o novo número de cartão de cidadão (8 primeiros dígitos):");
                        ccStr = ler.nextLine();

                        if (ccStr.equalsIgnoreCase("sair")) {
                            return;
                        }
                        if (!ccStr.matches("\\d{8}")) {
                            System.out.println("Erro: O cartão de cidadão deve conter exatamente 8 números. Tente novamente.");
                            continue;
                        }
                        cc = Integer.parseInt(ccStr);
                        break;
                    }
                    System.out.println("Indique a nova sua morada:");
                    String morada = ler.nextLine();
                    if (morada.equalsIgnoreCase("sair")) return;

                    System.out.println("Indique o novo número de telemóvel:");
                    String telStr = ler.nextLine();
                    if (telStr.equalsIgnoreCase("sair")) return;
                    int telemovel = Integer.parseInt(telStr);

                    if (empresaTVDE.atualizarCondutor(nome,idade,sexo,email,telemovel,morada,carta,cc,nif)) {
                        System.out.println("Cliente atualizado com sucesso.");
                        condutores = empresaTVDE.carregarCondutores();
                        break;
                    } else {
                        System.out.println("Erro: Condutor não encontrado ou não pode ser atualizado.");
                    }
                }


            }
        }catch(Exception e){
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_CONDUTOR, e.getMessage());
        }
    }

    void removerCondutor (Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Remover Condutor (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            while (true) {
                System.out.println("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine();
                if (opcaoSair(nifStr)) return;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }
                int nif = Integer.parseInt(nifStr);
                String nomeCondutor = empresaTVDE.procurarNifCondutor(nif).getNome();
                System.out.printf("Tem certeza que deseja remover o cliente %s ? [S/N]", nomeCondutor);
                String validacao = ler.nextLine().trim().toUpperCase();
                if (validacao.equalsIgnoreCase("S")) {
                    if (empresaTVDE.removerCondutor(nif)) {
                        System.out.printf("Condutor %s removido com sucesso.", nomeCondutor);
                        condutores = empresaTVDE.carregarCondutores();
                        break;
                    } else {
                        System.out.println("Erro: Condutor não encontrado ou não pode ser removido.");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Erro de input.");
        }
    } //Completo mas precisa de verificação Dinis :)
    //endregion

    //region Viaturas
    void Viaturas(Scanner ler){
        int opcao;
        do {
            opcao = subMenuViaturas(ler);
            if (opcao == 1) {
                registarViatura(ler);
            } else if (!viaturas.isEmpty() && opcao == 2) {
                pesquisarViatura(ler);
            } else if (!viaturas.isEmpty() && opcao == 3) {

            } else if (!viaturas.isEmpty() && opcao == 4){
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

    void registarViatura(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Novo Registo de Viatura (Escreva 'sair' para cancelar) ---\n\n" + RESET);

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
                System.out.println("Indique a matrícula no formato [XXXXXX]:");
                matricula = ler.nextLine().toUpperCase();

                if (matricula.equalsIgnoreCase("sair"))
                    return;

                if (!isMatriculaValida(matricula)) {
                    System.out.println("Formato incorreto. Tente novamente.");
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
                    Viatura viatura = new Viatura(matricula.toUpperCase(), marca, modelo, Integer.parseInt(anoDeFabrico), cor, true);
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

    private void pesquisarViatura(Scanner ler) {
        while (true) {
            System.out.print(ROXO + "\n\n--- Buscar Viatura (Escreva 'sair' para cancelar) ---\n\n" + RESET);

            System.out.println("Indique a matrícula: ");
            String matriculaStr = ler.nextLine().trim().toUpperCase();
            if (opcaoSair(matriculaStr)) break;
            if (!isMatriculaValida(matriculaStr)) {
                System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A matrícula deve conter exatamente 6 dígitos [XX-XX-XX]. Tente novamente.");
                continue;
            }


            try {
                Viatura viatura = empresaTVDE.procurarViatura(matriculaStr);
                if (viatura != null) {
                    System.out.println(viatura.toString());
                    System.out.println("Enter para continuar...");
                    ler.nextLine();
                    break;
                } else System.out.println("Viatura não encontrada.");
            } catch (Exception e) {
                System.out.println("Matrícula inválida.");
            }
        }
    }

    private void atualizarViatura (Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Atualizar Viatura (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            while (true) {
                System.out.println("Indique a matrícula [XX-XX-XX]: ");
                String matriculaStr = ler.nextLine().trim().toUpperCase();

                if (opcaoSair(matriculaStr)) return;
                if (!isMatriculaValida(matriculaStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A matrícula deve conter exatamente 6 dígitos [XX-XX-XX]. Tente novamente.");
                    continue;
                }
                Viatura viatura = empresaTVDE.procurarViatura(matriculaStr);
                if (viatura != null) {
                    while (true) {
                        System.out.println("Escolha a opção que deseja atualizar:" + "");
                        System.out.println("1\t-\tMarca");
                        System.out.println("2\t-\tModelo");
                        System.out.println("3\t-\tAno de Fabrico");
                        System.out.println("4\t-\tCor");
                        System.out.println("5\t-\tStatus");
                        System.out.println("0\t-\tSair");
                        String opcao = ler.nextLine();

                        String marca = "", modelo = "", cor = "", statusStr = "";
                        int anoDeFabrico = 0;

                        switch (Integer.parseInt(opcao)) {
                            case 1:
                                while (true) {
                                    System.out.println("Indique a marca: ");
                                    marca = ler.nextLine();
                                    if (opcaoSair(marca)) break;
                                    if (!isMarcaValida(marca)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A marca deve ser escrito com letras. Tente novamente.");
                                        continue;
                                    } else {
                                        viatura.setMarca(marca);
                                        System.out.println(VERDE_BRILHANTE + "\n\nMarca atualizado com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case 2:
                                while (true) {
                                    System.out.println("Indique o modelo: ");
                                    modelo = ler.nextLine();
                                    if (opcaoSair(modelo)) return;
                                    if (!isModeloValido(modelo)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O modelo não pode ficar vazio. Tente novamente.");
                                        continue;
                                    } else {
                                        viatura.setModelo(modelo);
                                        System.out.println(VERDE_BRILHANTE + "\n\nModelo atualizado com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case 3:
                                while (true) {
                                    System.out.println("Indique o ano de fabrico: [2000 a 2026]");
                                    String anoDeFabricoStr = ler.nextLine();
                                    if (opcaoSair(anoDeFabricoStr)) return;
                                    if (!isAnoDeFabricoValido(anoDeFabricoStr)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Ano de fabrico inválido [2000 a 2026]. Tente novamente.");
                                        continue;
                                    } else {
                                        anoDeFabrico = Integer.parseInt(anoDeFabricoStr);
                                        viatura.setAnoDeFabrico(anoDeFabrico);
                                        System.out.println(VERDE_BRILHANTE + "\n\nAno de fabrico atualizado com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case 4:
                                while (true) {
                                    System.out.println("Indique a cor: ");
                                    cor = ler.nextLine();
                                    if (opcaoSair(cor)) return;
                                    if (!isCorValida(cor)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A cor que inseriu é inválido. Tente novamente.");
                                        continue;
                                    } else {
                                        viatura.setCor(cor);
                                        System.out.println(VERDE_BRILHANTE + "\n\nCor atualizado com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case 5:
                                while (true) {
                                    System.out.println("Indique o status da viatura:\n1\t-\tDisponível\n2\t-\tIndisponível\n");
                                    statusStr = ler.nextLine().toLowerCase();
                                    if (opcaoSair(statusStr)) return;

                                    boolean status;

                                    if (statusStr.equalsIgnoreCase("1")) {
                                        status = true;
                                    } else if (statusStr.equalsIgnoreCase("2")) {
                                        status = false;
                                    } else {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O status informado não está entre os válidos. Tente novamente.");
                                        continue;
                                    }
                                    viatura.setDisponivel(status);

                                    System.out.println(VERDE_BRILHANTE + "\n\nStatus atualizado com sucesso!\n\n" + RESET);
                                    break;
                                }
                                break;

                            case 0:
                                return;
                            default:
                                System.out.print("Opção invalida. Tente novamente.");
                                break;
                        }
                    }
                }
                viaturas = empresaTVDE.carregarViaturas();
                break;
            }
        } catch (Exception e) {
            System.out.println("Dados inválidos.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIATURAS, e.getMessage());
        }
    } // Completo Dinis mas precisa-se adicionar para guardar nos ficheiros :)

    private void removerViatura(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Remover Viatura (Escreva 'sair' para cancelar) ---\n\n" + RESET);
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
                    boolean resposta = empresaTVDE.removerViaturas(matricula);
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

    //region Reservas
    void Reservas(Scanner ler) {
        int opcao;
        do{
            opcao = subMenuReservas(ler);
                if (opcao == 1){
                    System.out.println("caga nisso agr");
                } else if (!reservas.isEmpty() && opcao == 2) {
                    consultarReservas(ler);
                } else if (!reservas.isEmpty() && opcao == 3) {
                    removerReserva(ler);
                } else if (!reservas.isEmpty() && opcao == 4) {
                    alterarReserva(ler);
                } else if (!reservas.isEmpty() && opcao == 0) {
                    break;
                } else {
                    System.out.println("Opção Invalida, Tente novamente.");
                }

        }while(opcao!=0);
    }
    int subMenuReservas(Scanner ler) {
        int count = 1;
        limparConsola();
        printTituloPrincipal();
        printTituloSecundario("RESERVAS");
        System.out.printf(VERDE + "%d\t-\tCriar Reserva\n" + RESET, count);
        if (!reservas.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tConsultar Reserva\n" + RESET, count);
        }
        if (!reservas.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tRemover Reserva\n" + RESET, count);
        }
        if (!reservas.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tAlterar Reserva\n" + RESET, count);
        }
        System.out.println(VERDE + "0\t-\tVoltar ao menu anterior" + RESET);
        System.out.print("Indique a opção que queira realizar: ");
        String opcao = ler.nextLine();
        return Integer.parseInt(opcao);
    }
    /*Validar antes de inserir se a reserva já existe*/



    /*
    Fazer um switch case
    * Criar um SUb menu para Registrar Reservas e Eliminar
    * */
    /*criarReserva(ler);
    consultarReservas(ler);
    alterarReserva(ler);
    removerReserva(ler); */
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
                    System.out.println("Alterações concluídas");
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
        LocalDate dataParte = LocalDate.parse(ler.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Indique a hora que pretenda reservar (em formato de HH:mm):");
        LocalTime horaParte = LocalTime.parse(ler.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime dataHoraInicio = LocalDateTime.of(dataParte, horaParte);
        System.out.println("Indique a sua atual morada:");
        String moradaOrigem = ler.nextLine();
        System.out.println("Indique o destino:");
        String moradaDestino = ler.nextLine();
        System.out.println("Indique a distância:");
        double distancia = ler.nextDouble();
        Reserva reserva = new Reserva(cliente, viatura, dataHoraInicio, moradaOrigem, moradaDestino, distancia);
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
    //endregion

    //region Viagens
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
        viatura.setDisponivel(false);
        Viagem viagem = new Viagem(cliente, condutor, viatura, dataViagem, hora, concluida, moradaOrigem, moradaDestino, custoViagem);
        viagem.add(viagens);
    }

    void removerViagem(Scanner ler) {
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
    //endregion

    //region Informações
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
    //endregion

    public void limparConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    void distanciaMedia(Scanner ler) {
        try {
            System.out.println("Indique a data inicial em formato (dd/MM/aaaa): ");
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime dataInicio = LocalDateTime.parse(ler.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("Indique a data fim em formato (dd/MM/aaaa): ");
            LocalDateTime dataFim = LocalDateTime.parse(ler.nextLine(), formatterData);
            double media = empresaTVDE.calculaDistanciaMedia(dataInicio, dataFim);

            if (media > 0) {
                System.out.println("A distância média é de: " + media);
            } else System.out.println("Não foi encontrado registos entre as datas de viagens inseridas");
        }catch (DateTimeParseException e){
            System.out.println("Erro no formato da data" + e.getMessage());
        }
    private void verListaDeClientes(Scanner ler, String matricula) {
    }
}