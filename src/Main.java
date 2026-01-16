//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

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
    private final String CAMINHO_FICHEIRO_LOGS_ERROS_RESERVAS = "logsErrosReservas.txt";
    private final String CAMINHO_FICHEIRO_LOGS_ERROS_VIAGENS = "logsErrosViagens.txt";

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
    public boolean isMarcaValida(String marca) {
        if (marca == null) return false;
        String regex = "^[a-zA-Z]{2,}$";
        return marca.trim().matches(regex);
    }
    public boolean isModeloValido (String modelo) {
        if (modelo == null) return false;

        return !modelo.trim().isEmpty();
    }
    public boolean isAnoDeFabricoValido(String anoDeFabrico) {
        if (anoDeFabrico == null) return false;
        String regex = "^(200[1-9]|201[0-9]|202[0-6])$";

        return anoDeFabrico.trim().matches(regex);
    }
    public boolean isCorValida (String cor) {
        if (cor == null) return false;
        String regex = "^[a-zA-Z]{3,}$";
        return cor.trim().matches(regex);
    }
    public boolean isStatusValido (String status) {
        if (status == null) return false;
        return status.equalsIgnoreCase("1") || status.equalsIgnoreCase("2");
    }
    public boolean isNifValido(String nif) {
        if (nif == null) return false;
        String regex = "^\\d{9}$";

        return nif.trim().matches(regex);
    }
    public boolean isNomeValido(String nome) {
        if (nome== null) return false;
        String regex = "^[a-zA-Z]{2,}$";
        return nome.trim().matches(regex);
    }
    public boolean isCcValido(String cc) {
        if (cc == null) return false;
        String regex = "^\\d{8}$";

        return cc.trim().matches(regex);
    }
    public boolean isCartaValida(String carta) {
        if (carta == null) return false;
        String regex = "^[A-Z]-\\d{7}$";

        return carta.trim().matches(regex);
    }
    public boolean isIdadeValida(String idade) {
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
    public boolean isMoradaValida(String morada) {
        if (morada == null) return false;
        String regex = "^.+ \\d{4}-\\d{3}$";

        return morada.toLowerCase().matches(regex);
    }
    public boolean isDataValida(String data, DateTimeFormatter formatterData) {
        if (data == null) return false;
        try{
            LocalDateTime.parse(data, formatterData);
            return true;
        } catch (Exception e) {
             throw new RuntimeException(e);
        }
    }
    public boolean isIntervaloDeDataValida(LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (dataInicio == null || dataFim == null) return false;
        try{
            if(dataFim.isAfter(dataInicio)){
                return false;
            }
            return true;
        } catch (Exception e) {
             throw new RuntimeException(e);
        }
    }
    public boolean isDistanciaValida(String distancia) {
        if (distancia == null) return false;
        String regex = "^[0-9]+(\\.[0-9]+)?$";

        return distancia.trim().matches(regex);
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
            } else if (!clientes.isEmpty() && opcao == 3) {
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
        try {
            return Integer.parseInt(ler.nextLine());
        } catch (Exception e) {
            return -1;
        }
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
                if (!isIdadeValida(idadeStr)) {
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
                if (!isSexoValido(sexo)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O sexo informado não está entre os válidos. Tente novamente.");
                    continue;
                }
                if (sexo.equalsIgnoreCase("1")) sexo = "Masculino";
                if (sexo.equalsIgnoreCase("2")) sexo = "Feminino";
                if (sexo.equalsIgnoreCase("3")) sexo = "Outro";
                break;
            }

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

            while (true) {
                System.out.print("Indique a sua morada [Rua de Santa catarina, 123 - 3210-450]: ");
                morada = ler.nextLine();
                if (opcaoSair(morada)) return;
                if (!isMoradaValida(morada)) {
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

    private void pesquisarCliente(Scanner ler) {
        while (true) {
            System.out.print(ROXO + "\n\n--- Buscar Cliente (Escreva 'sair' para cancelar) ---\n\n" + RESET);

            System.out.print("Indique o NIF: ");
            String nifStr = ler.nextLine();
            if (opcaoSair(nifStr)) break;
            if (!isNifValido(nifStr)) {
                System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                continue;
            }
            try {
                int nif = Integer.parseInt(nifStr);
                Cliente cliente = empresaTVDE.procurarNifCliente(nif);
                if (cliente != null) {
                    System.out.println(cliente.toString());
                    System.out.println("Enter para continuar...");
                    ler.nextLine();
                    break;
                } else {
                    System.out.println("Cliente não encontrado.");
                }
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
                String nifStr = ler.nextLine().trim();

                if (opcaoSair(nifStr)) return;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }
                int nif = Integer.parseInt(nifStr);
                Cliente cliente = empresaTVDE.procurarNifCliente(nif);
                if (cliente != null) {
                    while (true) {
                        System.out.println("Escolha a opção que deseja atualizar: ");
                        System.out.println("1\t-\tNome");
                        System.out.println("2\t-\tIdade");
                        System.out.println("3\t-\tGénero");
                        System.out.println("4\t-\tEmail");
                        System.out.println("5\t-\tTelefone");
                        System.out.println("6\t-\tMorada");
                        System.out.println("7\t-\tCartão de Cidadão");
                        System.out.println("0\t-\tSair");
                        String opcao = ler.nextLine();

                        switch (opcao) {
                            case "1":
                                while (true) {
                                    System.out.print("Indique o seu nome: ");
                                    String nome = ler.nextLine();
                                    if (opcaoSair(nome)) break;
                                    if (!isNomeValido(nome)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O nome que inseriu é inválido. Tente novamente.");
                                        continue;
                                    } else {
                                        cliente.setNome(nome);
                                        empresaTVDE.guardarAlteracoesClientes();
                                        System.out.println(VERDE_BRILHANTE + "\n\nNome atualizado com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case "2":
                                while (true) {
                                    System.out.print("Indique a sua idade: ");
                                    String idadeStr = ler.nextLine();
                                    if (opcaoSair(idadeStr)) break;
                                    if (!isIdadeValida(idadeStr)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A idade deve estar no formato correto. Tente novamente.");
                                        continue;
                                    } else {
                                        int idade = Integer.parseInt(idadeStr);
                                        cliente.setIdade(idade);
                                        empresaTVDE.guardarAlteracoesClientes();
                                        System.out.println(VERDE_BRILHANTE + "\n\nIdade atualizada com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case "3":
                                while (true) {
                                    System.out.print("Indique o seu género:\n1\t-\tMasculino\n2\t-\tFeminino\n3\t-\tOutro\n ");
                                    String sexo = ler.nextLine().toLowerCase();
                                    if (opcaoSair(sexo)) break;
                                    if (!isSexoValido(sexo)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O género informado não está entre os válidos. Tente novamente.");
                                        continue;
                                    }
                                    if (sexo.equalsIgnoreCase("1")) sexo = "Masculino";
                                    if (sexo.equalsIgnoreCase("2")) sexo = "Feminino";
                                    if (sexo.equalsIgnoreCase("3")) sexo = "Outro";

                                    cliente.setSexo(sexo);
                                    empresaTVDE.guardarAlteracoesClientes();
                                    System.out.println(VERDE_BRILHANTE + "\n\nGénero atualizado com sucesso!\n\n" + RESET);
                                    break;
                                }
                                break;
                            case "4":
                                while (true) {
                                    System.out.print("Indique o seu email: ");
                                    String email = ler.nextLine();
                                    if (opcaoSair(email)) break;
                                    if (!isEmailValido(email)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O email informado não está no formato correto. Tente novamente.");
                                        continue;
                                    } else {
                                        cliente.setEmail(email);
                                        empresaTVDE.guardarAlteracoesClientes();
                                        System.out.println(VERDE_BRILHANTE + "\n\nEmail atualizado com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case "5":
                                while (true) {
                                    System.out.print("Indique o número de telefone: ");
                                    String telStr = ler.nextLine();
                                    if (opcaoSair(telStr)) break;
                                    if (!isTelefoneValido(telStr)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O número de telefone informado não está no formato correto. Tente novamente.");
                                        continue;
                                    } else {
                                        int telefone = Integer.parseInt(telStr);
                                        cliente.setTelefone(telefone);
                                        empresaTVDE.guardarAlteracoesClientes();
                                        System.out.println(VERDE_BRILHANTE + "\n\nNúmero de telefone atualizado com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case "6":
                                while (true) {
                                    System.out.print("Indique a sua morada [Rua de Santa catarina, 123 - 3210-450]: ");
                                    String morada = ler.nextLine();
                                    if (opcaoSair(morada)) break;
                                    if (!isMoradaValida(morada)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                                        continue;

                                    } else {
                                        cliente.setMorada(morada);
                                        empresaTVDE.guardarAlteracoesClientes();
                                        System.out.println(VERDE_BRILHANTE + "\n\nMorada atualizada com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case "7":
                                while (true) {
                                    System.out.print("Indique o número de cartão de cidadão (8 primeiros dígitos): ");
                                    String ccStr = ler.nextLine();

                                    if (opcaoSair(ccStr)) break;
                                    if (!isCcValido(ccStr)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O cartão de cidadão deve conter exatamente 8 números. Tente novamente.");
                                        continue;
                                    } else {
                                        int cc = Integer.parseInt(ccStr);
                                        if (empresaTVDE.procurarCartaoDeCidadaoCliente(cc) != null && cliente.getCartaoDeCidadao() != cc) {
                                            System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Esse cartão de cidadão já está registado noutro cliente.");
                                            continue;
                                        }

                                        cliente.setCartaoDeCidadao(cc);
                                        empresaTVDE.guardarAlteracoesClientes();
                                        System.out.println(VERDE_BRILHANTE + "\n\nCartão de cidadão atualizado com sucesso!\n\n" + RESET);
                                    }
                                   break;
                                }
                                break;
                            case "0":
                                return;
                            default:
                                System.out.print("Opção invalida. Tente novamente.");
                                break;
                        }
                    }
                } else {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Cliente não encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Dados inválidos.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_CLIENTE, e.getMessage());
        }
    } //Completo Dinis :)

    void removerCliente(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Remover Cliente (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            while (true) {
                System.out.print("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine().trim();
                if (opcaoSair(nifStr)) break;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }
                int nif = Integer.parseInt(nifStr);
                Cliente cliente = empresaTVDE.procurarNifCliente(nif);
                if (cliente != null) {
                    System.out.printf("Tem certeza que deseja remover o cliente %s ? [S/N]: ", cliente.getContribuinte());
                    String validacao = ler.nextLine().trim();

                    if (validacao.equalsIgnoreCase("S")) {
                        if (empresaTVDE.removerCliente(cliente.getContribuinte())) {
                            System.out.printf("Cliente %s removido com sucesso.\n", cliente.getContribuinte());
                            clientes = empresaTVDE.carregarClientes();
                            break;
                        } else {
                            System.out.println("Erro: Cliente não encontrado ou não pode ser removido.");
                        }
                    } else {
                        System.out.println("Operação cancelada.");
                        break;
                    }
                } else {
                    System.out.println(VERMELHO_BRILHANTE + "Erro:" + RESET + " Cliente não encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro de input." + e.getMessage());
        }
    } //Completo Dinis :)
    //endregion

    //region Condutores
    void Condutores(Scanner ler) {
        int opcao;
        do {
            opcao = subMenuCondutores(ler);
            if (opcao == 1) {
                registarCondutor(ler);
            } else if (!condutores.isEmpty() && opcao == 2) {
                pesquisarCondutor(ler);
            } else if (!condutores.isEmpty() && opcao == 3) {
                atualizarCondutor(ler);
            } else if (!condutores.isEmpty() && opcao == 4) {
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
        try {
            return Integer.parseInt(ler.nextLine());
        } catch (Exception e) {
            return -1;
        }
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
                if (!isCartaValida(cartaStr)) {
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
                if (!isIdadeValida(idadeStr)) {
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
                if (!isSexoValido(sexo)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O sexo informado não está entre os válidos. Tente novamente.");
                    continue;
                }
                if (sexo.equalsIgnoreCase("1")) sexo = "Masculino";
                if (sexo.equalsIgnoreCase("2")) sexo = "Feminino";
                if (sexo.equalsIgnoreCase("3")) sexo = "Outro";
                break;
            }

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
            while (true) {
                System.out.println("Indique a sua morada [Rua de Santa catarina, 123 - 3210-450]: ");
                morada = ler.nextLine();
                if (opcaoSair(morada)) return;
                if (!isMoradaValida(morada)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
            }

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

            Condutor condutor = new Condutor(nome, idade, sexo, email, telefone, morada, cc, cartaStr, nif);
            if (empresaTVDE.adicionarCondutor(condutor)) {
                System.out.print(VERDE_BRILHANTE + "\n\nCondutor registado com sucesso!\n\n" + RESET);
                condutores = empresaTVDE.carregarCondutores();
            }
        } catch (Exception e) {
            System.out.println("Dados inválidos.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_CONDUTOR, e.getMessage());
        }
    } //Completo Dinis :)

    private void pesquisarCondutor(Scanner ler) {
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

                if (opcaoSair(nifStr)) return;
                if(!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }

                int nif = Integer.parseInt(nifStr);
                Condutor condutor = empresaTVDE.procurarNifCondutor(nif);
                if (condutor != null) {
                    while (true) {
                        System.out.println("Escolha a opção que deseja atualizar: ");
                        System.out.println("1\t-\tNome");
                        System.out.println("2\t-\tIdade");
                        System.out.println("3\t-\tGénero");
                        System.out.println("4\t-\tEmail");
                        System.out.println("5\t-\tTelefone");
                        System.out.println("6\t-\tMorada");
                        System.out.println("7\t-\tCartão de Cidadão");
                        System.out.println("8\t-\tCarta de Condução");
                        System.out.println("0\t-\tSair");
                        String opcao = ler.nextLine();

                        switch (opcao) {
                            case "1":
                            while (true) {
                                System.out.print("Indique o seu nome: ");
                                String nome = ler.nextLine();
                                if (opcaoSair(nome)) break;
                                if (!isNomeValido(nome)) {
                                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O nome que inseriu é inválido. Tente novamente.");
                                    continue;
                                } else {
                                    condutor.setNome(nome);
                                    empresaTVDE.guardarAlteracoesCondutores();
                                    System.out.println(VERDE_BRILHANTE + "\n\nNome atualizado com sucesso!\n\n" + RESET);
                                }
                                break;
                            }
                            break;
                        case "2":
                            while (true) {
                                System.out.print("Indique a sua idade: ");
                                String idadeStr = ler.nextLine();
                                if (opcaoSair(idadeStr)) break;
                                if (!isIdadeValida(idadeStr)) {
                                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A idade deve estar no formato correto. Tente novamente.");
                                    continue;
                                } else {
                                    int idade = Integer.parseInt(idadeStr);
                                    condutor.setIdade(idade);
                                    empresaTVDE.guardarAlteracoesCondutores();
                                    System.out.println(VERDE_BRILHANTE + "\n\nIdade atualizada com sucesso!\n\n" + RESET);
                                }
                                break;
                            }
                            break;
                        case "3":
                            while (true) {
                                System.out.print("Indique o seu género:\n1\t-\tMasculino\n2\t-\tFeminino\n3\t-\tOutro\n ");
                                String sexo = ler.nextLine().toLowerCase();
                                if (opcaoSair(sexo)) break;
                                if (!isSexoValido(sexo)) {
                                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O género informado não está entre os válidos. Tente novamente.");
                                    continue;
                                }
                                if (sexo.equalsIgnoreCase("1")) sexo = "Masculino";
                                if (sexo.equalsIgnoreCase("2")) sexo = "Feminino";
                                if (sexo.equalsIgnoreCase("3")) sexo = "Outro";

                                condutor.setSexo(sexo);
                                empresaTVDE.guardarAlteracoesCondutores();
                                System.out.println(VERDE_BRILHANTE + "\n\nGénero atualizado com sucesso!\n\n" + RESET);
                                break;
                            }
                            break;
                        case "4":
                            while (true) {
                                System.out.print("Indique o seu email: ");
                                String email = ler.nextLine();
                                if (opcaoSair(email)) break;
                                if (!isEmailValido(email)) {
                                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O email informado não está no formato correto. Tente novamente.");
                                    continue;
                                } else {
                                    condutor.setEmail(email);
                                    empresaTVDE.guardarAlteracoesCondutores();
                                    System.out.println(VERDE_BRILHANTE + "\n\nEmail atualizado com sucesso!\n\n" + RESET);
                                }
                                break;
                            }
                            break;
                        case "5":
                            while (true) {
                                System.out.print("Indique o número de telefone: ");
                                String telStr = ler.nextLine();
                                if (opcaoSair(telStr)) break;
                                if (!isTelefoneValido(telStr)) {
                                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O número de telefone informado não está no formato correto. Tente novamente.");
                                    continue;
                                } else {
                                    int telefone = Integer.parseInt(telStr);
                                    condutor.setTelefone(telefone);
                                    empresaTVDE.guardarAlteracoesCondutores();
                                    System.out.println(VERDE_BRILHANTE + "\n\nNúmero de telefone atualizado com sucesso!\n\n" + RESET);
                                }
                                break;
                            }
                            break;
                        case "6":
                            while (true) {
                                System.out.print("Indique a sua morada [Rua de Santa catarina, 123 - 3210-450]: ");
                                String morada = ler.nextLine();
                                if (opcaoSair(morada)) break;
                                if (!isMoradaValida(morada)) {
                                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                                    continue;

                                } else {
                                    condutor.setMorada(morada);
                                    empresaTVDE.guardarAlteracoesCondutores();
                                    System.out.println(VERDE_BRILHANTE + "\n\nMorada atualizada com sucesso!\n\n" + RESET);
                                }
                                break;
                            }
                            break;
                        case "7":
                            while (true) {
                                System.out.print("Indique o número de cartão de cidadão (8 primeiros dígitos): ");
                                String ccStr = ler.nextLine();

                                if (opcaoSair(ccStr)) break;
                                if (!isCcValido(ccStr)) {
                                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O cartão de cidadão deve conter exatamente 8 números. Tente novamente.");
                                    continue;
                                } else {
                                    int cc = Integer.parseInt(ccStr);
                                    if (empresaTVDE.procurarCartaoDeCidadaoCondutor(cc) != null && condutor.getCartaoDeCidadao() != cc) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Esse cartão de cidadão já está registado noutro condutor.");
                                        continue;
                                    }

                                    condutor.setCartaoDeCidadao(cc);
                                    empresaTVDE.guardarAlteracoesCondutores();
                                    System.out.println(VERDE_BRILHANTE + "\n\nCartão de cidadão atualizado com sucesso!\n\n" + RESET);
                                }
                                break;
                            }
                            break;
                        case "8":
                            while (true) {
                                System.out.print("Indique a carta de condução: ");
                                String cartaStr = ler.nextLine();

                                if (opcaoSair(cartaStr)) break;
                                if (!isCartaValida(cartaStr)) {
                                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A carta de condução informada não está no formato correto. Tente novamente.");
                                    continue;
                                }
                                Condutor condutor1 = empresaTVDE.procurarCartaDeConducaoCondutor(cartaStr);
                                if (condutor1 != null && !condutor.getCartaDeConducao().equalsIgnoreCase(cartaStr)) {
                                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Essa carta de condução já está registada noutro condutor.");
                                    continue;
                                }
                                    condutor.setCartaDeConducao(cartaStr);
                                    empresaTVDE.guardarAlteracoesCondutores();
                                    System.out.println(VERDE_BRILHANTE + "\n\nCarta de condução atualizada com sucesso!\n\n" + RESET);
                                break;
                            }
                            break;
                        case "0":
                            return;
                        default:
                            System.out.print("Opção invalida. Tente novamente.");
                            break;
                    }
                }
            } else {
                System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Condutor não encontrado.");
            }
        }
    } catch (Exception e){
        System.out.println("Dados inválidos.");
        empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_CONDUTOR, e.getMessage());
    }
} //Completo Dinis

    void removerCondutor(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Remover Condutor (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            while (true) {
                System.out.println("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine().trim();
                if (opcaoSair(nifStr)) break;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }
                int nif = Integer.parseInt(nifStr);
                Condutor condutor = empresaTVDE.procurarNifCondutor(nif);
                if (condutor != null) {
                System.out.printf("Tem certeza que deseja remover o condutor com NIF %s? [S/N]", condutor.getContribuinte());
                String validacao = ler.nextLine().trim();

                    if (validacao.equalsIgnoreCase("S")) {
                        if (empresaTVDE.removerCondutor(condutor.getContribuinte())) {
                            System.out.printf("Condutor %s removido com sucesso.", condutor.getContribuinte());
                            condutores = empresaTVDE.carregarCondutores();
                            break;
                        } else {
                            System.out.println("Erro: Condutor não encontrado ou não pode ser removido.");
                        }
                    } else {
                        System.out.println("Operação cancelada.");
                        break;
                    }
                } else {
                    System.out.println(VERMELHO_BRILHANTE + "Erro:" + RESET + " Condutor não encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro de input." + e.getMessage());
        }
    } //Completo Dinis :)
    //endregion

    //region Viaturas
    void Viaturas(Scanner ler) {
        int opcao;
        do {
            opcao = subMenuViaturas(ler);
            if (opcao == 1) {
                registarViatura(ler);
            } else if (!viaturas.isEmpty() && opcao == 2) {
                pesquisarViatura(ler);
            } else if (!viaturas.isEmpty() && opcao == 3) {
                atualizarViatura(ler);
            } else if (!viaturas.isEmpty() && opcao == 4){
                removerViatura(ler);
            } else if (opcao == 0) {
                break;
            } else {
                System.out.println("Opção Inválida!");
            }
        } while (opcao != 0);
    } // Completo Dinis :)

    int subMenuViaturas(Scanner ler) {
        int count = 1;
        limparConsola();
        printTituloPrincipal();
        printTituloSecundario("VIATURAS");
        System.out.printf(VERDE + "%d\t-\tRegistar Viatura\n" + RESET, count);
        if (!viaturas.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tPesquisar Viatura pela Matrícula\n" + RESET, count);
        }
        if(!viaturas.isEmpty()){
            count++;
            System.out.printf(VERDE + "%d\t-\tAtualizar Viatura pela Matrícula\n" + RESET, count);
        }
        if(!viaturas.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tRemover Viatura\n" + RESET, count);
        }
        System.out.println(VERDE + "0\t-\tVoltar ao menu anterior" + RESET);
        System.out.print("Indique a opção que queira realizar: ");
        try { return Integer.parseInt(ler.nextLine()); } catch (Exception e) { return -1; }
    } //Completo Dinis :)

    void registarViatura(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Novo Registo de Viatura (Escreva 'sair' para cancelar) ---\n\n" + RESET);

            String matricula, marca, modelo, cor;
            int anoDeFabrico;
            boolean status;
            while (true) {
                System.out.println("Indique a matrícula da viatura [XXXXXX]: ");
                matricula = ler.nextLine();

                if (opcaoSair(matricula)) return;
                if (!isMatriculaValida(matricula)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A Matrícula deve conter exatamente 6 dígitos [XXXXXX]. Tente novamente.");
                    continue;
                }
                if (empresaTVDE.procurarViatura(matricula) != null) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Essa matrícula já está registada no sistema. Tente outro.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.println("Indique a marca da viatura: ");
                marca = ler.nextLine();
                if (opcaoSair(marca)) return;
                if (!isMarcaValida(marca)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A marca deve ser escrita apenas com letras e ter no mínimo 2 letras. Tente novamente.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.println("Indique a modelo da viatura: ");
                modelo = ler.nextLine();
                if (opcaoSair(modelo)) return;
                if (!isModeloValido(modelo)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O modelo não pode ficar vazio. Tente novamente.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.println("Indique o ano de fabrico da viatura [XXXX]: ");
                String anoDeFabricoStr = ler.nextLine();
                if (opcaoSair(anoDeFabricoStr)) return;
                if (!isAnoDeFabricoValido(anoDeFabricoStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Ano de fabrico inválido [2000 a 2026]. Tente novamente.");
                    continue;
                }
                anoDeFabrico = Integer.parseInt(anoDeFabricoStr);
                break;
            }
            while (true) {
                System.out.println("Indique a cor da viatura: ");
                cor = ler.nextLine();
                if (opcaoSair(cor)) return;
                if (!isCorValida(cor)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A cor que inseriu é inválido. Tente novamente.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.println("Indique o status da viatura:\n1\t-\tDisponível\n2\t-\tIndisponível\n");
                String statusStr = ler.nextLine();
                if (opcaoSair(statusStr)) return;
                if (!isStatusValido(statusStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O status informado não está entre os válidos[1/2]. Tente novamente.");
                    continue;
                }
                if (statusStr.equals("1")){
                    status = true;
                } else {
                    status = false;
                }
                break;
            }

            Viatura viatura = new Viatura(matricula,marca,modelo,anoDeFabrico,cor,status);
            if (empresaTVDE.adicionarViatura(viatura)) {
                System.out.print(VERDE_BRILHANTE + "\n\nViatura registado com sucesso!\n\n" + RESET);
                viaturas = empresaTVDE.carregarViaturas();
            }

        } catch (Exception e) {
            System.out.println("Dados inválidos.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIATURAS, e.getMessage());
        }
    } //Completo Dinis :)

    private void pesquisarViatura(Scanner ler) {
        while (true) {
            System.out.print(ROXO + "\n\n--- Buscar Viatura (Escreva 'sair' para cancelar) ---\n\n" + RESET);

            System.out.println("Indique a matrícula[XXXXXX]: ");
            String matriculaStr = ler.nextLine().trim().toUpperCase();
            if (opcaoSair(matriculaStr)) break;
            if (!isMatriculaValida(matriculaStr)) {
                System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A matrícula deve conter exatamente 6 dígitos [XXXXXX]. Tente novamente.");
                continue;
            }
            try {
                Viatura viatura = empresaTVDE.procurarViatura(matriculaStr);
                if (viatura != null) {
                    System.out.println(viatura.toString());
                    System.out.println("Enter para continuar...");
                    ler.nextLine();
                    break;
                } else {
                    System.out.println("Viatura não encontrada.");
                }
            } catch (Exception e) {
                System.out.println("Matrícula inválida.");
            }
        }
    } //Completo Dinis :)

    private void atualizarViatura(Scanner ler) {
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
                        System.out.println("Escolha a opção que deseja atualizar: ");
                        System.out.println("1\t-\tMarca");
                        System.out.println("2\t-\tModelo");
                        System.out.println("3\t-\tAno de Fabrico");
                        System.out.println("4\t-\tCor");
                        System.out.println("5\t-\tStatus");
                        System.out.println("0\t-\tSair");
                        String opcao = ler.nextLine();

                        switch (opcao) {
                            case "1":
                                while (true) {
                                    System.out.println("Indique a marca: ");
                                    String marca = ler.nextLine();
                                    if (opcaoSair(marca)) break;
                                    if (!isMarcaValida(marca)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A marca deve ser escrito com letras. Tente novamente.");
                                        continue;
                                    } else {
                                        viatura.setMarca(marca);
                                        empresaTVDE.guardarAlteracoesViaturas();
                                        System.out.println(VERDE_BRILHANTE + "\n\nMarca atualizado com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case "2":
                                while (true) {
                                    System.out.println("Indique o modelo: ");
                                    String modelo = ler.nextLine();
                                    if (opcaoSair(modelo)) break;
                                    if (!isModeloValido(modelo)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O modelo não pode ficar vazio. Tente novamente.");
                                        continue;
                                    } else {
                                        viatura.setModelo(modelo);
                                        empresaTVDE.guardarAlteracoesViaturas();
                                        System.out.println(VERDE_BRILHANTE + "\n\nModelo atualizado com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case "3":
                                while (true) {
                                    System.out.println("Indique o ano de fabrico: [2000 a 2026]");
                                    String anoDeFabricoStr = ler.nextLine();
                                    if (opcaoSair(anoDeFabricoStr)) break;
                                    if (!isAnoDeFabricoValido(anoDeFabricoStr)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Ano de fabrico inválido [2000 a 2026]. Tente novamente.");
                                        continue;
                                    } else {
                                        int anoDeFabrico = Integer.parseInt(anoDeFabricoStr);
                                        viatura.setAnoDeFabrico(anoDeFabrico);
                                        empresaTVDE.guardarAlteracoesViaturas();
                                        System.out.println(VERDE_BRILHANTE + "\n\nAno de fabrico atualizado com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case "4":
                                while (true) {
                                    System.out.println("Indique a cor: ");
                                    String cor = ler.nextLine();
                                    if (opcaoSair(cor)) break;
                                    if (!isCorValida(cor)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A cor que inseriu é inválida. Tente novamente.");
                                        continue;
                                    } else {
                                        viatura.setCor(cor);
                                        empresaTVDE.guardarAlteracoesViaturas();
                                        System.out.println(VERDE_BRILHANTE + "\n\nCor atualizado com sucesso!\n\n" + RESET);
                                    }
                                    break;
                                }
                                break;
                            case "5":
                                while (true) {
                                    System.out.println("Indique o status da viatura:\n1\t-\tDisponível\n2\t-\tIndisponível\n");
                                    String statusStr = ler.nextLine().trim();
                                    if (opcaoSair(statusStr)) break;
                                    if (!isStatusValido(statusStr)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O status informado não está entre os válidos[1/2]. Tente novamente.");
                                        continue;
                                    }
                                    boolean status;
                                    if (statusStr.equals("1")){
                                        status = true;
                                    } else {
                                        status = false;
                                    }
                                    viatura.setDisponivel(status);
                                    empresaTVDE.guardarAlteracoesViaturas();
                                    System.out.println(VERDE_BRILHANTE + "\n\nStatus atualizado com sucesso!\n\n" + RESET);
                                    break;
                                }
                                break;
                            case "0":
                                return;
                            default:
                                System.out.print("Opção invalida. Tente novamente.");
                                break;
                        }
                    }
                } else {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Viatura não encontrada.");
                }
            }
        } catch (Exception e) {
            System.out.println("Dados inválidos.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIATURAS, e.getMessage());
        }
    } // Completo Dinis :)

    private void removerViatura(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Remover Viatura (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            while (true) {
                System.out.println("Indique a matrícula do veículo que deseja remover no formato [XXXXXX]: ");
                String matriculaStr = ler.nextLine().trim().toUpperCase();
                if (opcaoSair(matriculaStr)) break;
                if (!isMatriculaValida(matriculaStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A Matrícula deve conter exatamente 6 dígitos [XXXXXX]. Tente novamente.");
                    continue;
                }
                Viatura viatura = empresaTVDE.procurarViatura(matriculaStr);
                if (viatura != null) {
                    System.out.printf("Tem certeza que deseja remover a viatura %s ? [S/N]: ", viatura.getMatricula());
                    String validacao = ler.nextLine().trim().toUpperCase();

                    if (validacao.equalsIgnoreCase("S")) {
                        if(empresaTVDE.removerViaturas(viatura.getMatricula())) {
                            System.out.printf("Viatura %s removida com sucesso.\n", viatura.getMatricula());
                            viaturas = empresaTVDE.carregarViaturas();
                            break;
                        } else {
                            System.out.println("Erro: Viatura não encontrada ou não pode ser removida.");
                        }
                    } else {
                        System.out.println("Operação cancelada.");
                        break;
                    }
                } else {
                    System.out.println(VERMELHO_BRILHANTE + "Erro:" + RESET + " Viatura não encontrada.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro de input." + e.getMessage());
        }
    } //Completo Dinis :)
    //endregion

    private void verListaDeClientes(Scanner ler, String matricula) {
    }
    //region Reservas
    void Reservas(Scanner ler) {
        int opcao;
        do{
            opcao = subMenuReservas(ler);
            if (opcao == 1){
                criarReserva(ler);
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

        }while(opcao !=0);
    }

    int subMenuReservas(Scanner ler) {
        int count = 1;
        limparConsola();
        printTituloPrincipal();
        printTituloSecundario("RESERVAS");
        System.out.printf(VERDE + "%d\t-\tCriar Reserva\n" + RESET, count);
        if (!condutores.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tConsultar Reservas\n" + RESET, count);
        }
        if (!condutores.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tRemover Reserva\n" + RESET, count);
        }
        if (!condutores.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tAlterar Reserva\n" + RESET, count);
        }
        System.out.println(VERDE + "0\t-\tVoltar ao menu anterior" + RESET);
        System.out.print("Indique a opção que queira realizar: ");
        try { return Integer.parseInt(ler.nextLine()); } catch (Exception e) { return -1; }
    }
    /*Validar antes de inserir se a reserva já existe*/

        } while (opcao != 0);
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

            switch (opcao) {
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
    public ArrayList<Viatura> getViaturasDisponiveis(){
        ArrayList<Viatura> viaturasDisponiveis = new ArrayList<Viatura>();
        for(var viatura : viaturas){
            if(viatura.isStatus()){
                viaturasDisponiveis.add(viatura);
            }
        }
        return viaturasDisponiveis;
    }
    void criarReserva(Scanner ler) {
        ArrayList<Viatura> viaturasDisponiveis = getViaturasDisponiveis();
        Viatura viaturaParaReserva = new Viatura();
        for(var viatura : viaturasDisponiveis){
            viaturaParaReserva = viatura;
            break;
        }
        System.out.println("Indique o NIF do cliente para reserva");
        String nif = ler.nextLine();
        Cliente cliente = empresaTVDE.procurarNifCliente(Integer.parseInt(nif));
        System.out.println("Indique a data que pretenda reservar (em formato de dd/MM/yyyy):");
        String data = ler.nextLine();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dataFormatada = LocalDateTime.parse(data, formatoData);
        System.out.println("Indique a hora que pretenda reservar (em formato de HH:mm):");
        LocalTime horaParte = LocalTime.parse(ler.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime dataHoraInicio = LocalDateTime.of(dataParte, horaParte);
        System.out.println("Indique a sua atual morada:");
        String moradaOrigem = ler.nextLine();
        System.out.println("Indique o destino:");
        String moradaDestino = ler.nextLine();
        System.out.println("Indique a distância:");
        double distancia = ler.nextDouble();
        Reserva reserva = new Reserva(cliente, viaturaParaReserva, dataFormatada, hora, moradaOrigem, moradaDestino, distancia);
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
        int opcao;
        do {
            opcao = subMenuViagens(ler);
            if (opcao == 1) {
                //criarViagem(ler);
            } else if (!viagens.isEmpty() && opcao == 2) {
                //transformarReservaEmViagem(ler);
            } else if (!viagens.isEmpty() && opcao == 3) {
                removerViagem(ler);
            } else if (opcao == 0) {
                break;
            } else {
                System.out.println("Opção Inválida! Tente novamente!");
            }
        } while (opcao != 0);
    }
    int subMenuViagens(Scanner ler){
        int count = 1;
        limparConsola();
        printTituloPrincipal();
        printTituloSecundario("VIAGENS");
        System.out.printf(VERDE + "%d\t-\tRegistar Viagens\n" + RESET, count);
        if(!viaturas.isEmpty()){
            count++;
            System.out.printf(VERDE + "%d\t-\tTransformar Reserva Em Viagem\n" + RESET, count);
        }
        if(!viaturas.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tRemover Viagem\n" + RESET, count);
        }
        System.out.println(VERDE + "0\t-\tVoltar ao menu anterior" + RESET);
        System.out.print("Indique a opção que queira realizar: ");
        String opcao = ler.nextLine();
        return Integer.parseInt(opcao);
    }
        /*Permitir trasnformar uma reserva em viagem
         * Validar se a viagem já existe antes de inserir
         * */

        /*
         * Criar um SUb menu para Registrar Viagens e Eliminar
         * */
        //transformarReservaEmViagem(ler);
        //criarViagem(ler);


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
    int informacoes(Scanner ler) {
        int count = 1;
        limparConsola();
        printTituloPrincipal();
        printTituloSecundario("Informações");
        if (!viagens.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tPesquisar Viagem\n" + RESET, count);
            pesquisarViagem(ler);
        }
        if (!condutores.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tPesquisar Valor Faturado por Motorista\n" + RESET, count);
            totalFaturado(ler);
        }
        if (!viaturas.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tDistância Média das Viagens\n" + RESET, count);
            distanciaMedia(ler);
        }
        if (!reservas.isEmpty() || !viagens.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tDestino mais solicitado\n" + RESET, count);
            destinoMaisPopular(ler);
        }
        if (!clientes.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tLista de clientes\n" + RESET, count);
            verListaDeClientes(ler);
        }
        System.out.println(VERDE + "0\t-\tVoltar ao menu anterior" + RESET);
        System.out.print("Indique a opção que queira realizar: ");
        String opcao = ler.nextLine();
        return Integer.parseInt(opcao);
    }
    public void limparConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    void pesquisarViagem(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Pesquisar viagens (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            int nif;
            LocalDateTime inicio, fim;
            while (true) {
                System.out.print("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine();

                if (opcaoSair(nifStr)) return;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }

                nif = Integer.parseInt(nifStr);

                if (empresaTVDE.procurarNifCliente(nif) == null) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Esse NIF não está registado no sistema. Tente outro.");
                    continue;
                }
                break;
            }

            while (true) {
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.print("Indique a data de início que pretenda pesquisar, no seguinte formato [dd/MM/aaaa]: ");
                String inicioStr = ler.nextLine();

                if (opcaoSair(inicioStr)) return;
                if (!isDataValida(inicioStr, formatterData)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Adata deve estar no formato [dd/MM/aaaa]. Tente novamente.");
                    continue;
                }
                inicio = LocalDateTime.parse(inicioStr, formatterData);
                break;
            }

            while (true) {
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.print("Indique a data de termino que pretenda pesquisar, no seguinte formato [dd/MM/aaaa]: ");
                String fimStr = ler.nextLine();

                if (opcaoSair(fimStr)) return;
                if (!isDataValida(fimStr, formatterData)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data deve estar no formato [dd/MM/aaaa]. Tente novamente.");
                    continue;
                }
                fim = LocalDateTime.parse(fimStr, formatterData);
                if(!isIntervaloDeDataValida(inicio, fim)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data de fim deve posterior a data de início. Tente novamente.");
                    continue;
                }
                break;
            }


            ArrayList<Viagem> resultado = empresaTVDE.pesquisarViagemClienteData(nif, inicio, fim);
            if (resultado != null) {
                for (Viagem viagem : resultado) {
                    System.out.println(viagem.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar a viagem.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIAGENS, "pesquisarViagem - " + e.getMessage());
        }
    }
    void totalFaturado(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Ver total faturado por condutor (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            String nomeCondutor;
            int nif;
            LocalDateTime inicio, fim;
            while (true) {
                System.out.print("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine();

                if (opcaoSair(nifStr)) return;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }

                nif = Integer.parseInt(nifStr);

                if (empresaTVDE.procurarNifCliente(nif) == null) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Esse NIF não está registado no sistema. Tente outro.");
                    continue;
                }
                nomeCondutor = empresaTVDE.procurarNifCliente(nif).getNome();
                break;
            }

            while (true) {
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.print("Indique a data de início que pretenda pesquisar, no seguinte formato [dd/MM/aaaa]: ");
                String inicioStr = ler.nextLine();

                if (opcaoSair(inicioStr)) return;
                if (!isDataValida(inicioStr, formatterData)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Adata deve estar no formato [dd/MM/aaaa]. Tente novamente.");
                    continue;
                }
                inicio = LocalDateTime.parse(inicioStr, formatterData);
                break;
            }

            while (true) {
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.print("Indique a data de termino que pretenda pesquisar, no seguinte formato [dd/MM/aaaa]: ");
                String fimStr = ler.nextLine();

                if (opcaoSair(fimStr)) return;
                if (!isDataValida(fimStr, formatterData)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Adata deve estar no formato [dd/MM/aaaa]. Tente novamente.");
                    continue;
                }
                fim = LocalDateTime.parse(fimStr, formatterData);
                if(!isIntervaloDeDataValida(inicio, fim)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data de fim deve posterior a data de início. Tente novamente.");
                    continue;
                }
                break;
            }

            double total = empresaTVDE.calcularFaturacaoTotal(nif, inicio, fim);
            System.out.printf("Total faturado pelo condutor %s entre as datas %s e %s é €%.2f", nomeCondutor, inicio, fim, total);
        } catch (Exception e) {
            System.out.println("Erros ao pesquisar o total faturado.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIAGENS, "totalFaturado -" + e.getMessage());
        }
    }
    void distanciaMedia(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Ver distância média por viagens (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            LocalDateTime inicio, fim;
            while (true) {
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.print("Indique a data de início que pretenda pesquisar, no seguinte formato [dd/MM/aaaa]: ");
                String inicioStr = ler.nextLine();

                if (opcaoSair(inicioStr)) return;
                if (!isDataValida(inicioStr, formatterData)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Adata deve estar no formato [dd/MM/aaaa]. Tente novamente.");
                    continue;
                }
                inicio = LocalDateTime.parse(inicioStr, formatterData);
                break;
            }

            while (true) {
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.print("Indique a data de termino que pretenda pesquisar, no seguinte formato [dd/MM/aaaa]: ");
                String fimStr = ler.nextLine();

                if (opcaoSair(fimStr)) return;
                if (!isDataValida(fimStr, formatterData)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Adata deve estar no formato [dd/MM/aaaa]. Tente novamente.");
                    continue;
                }
                fim = LocalDateTime.parse(fimStr, formatterData);
                if(!isIntervaloDeDataValida(inicio, fim)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data de fim deve posterior a data de início. Tente novamente.");
                    continue;
                }
                break;
            }

            double media = empresaTVDE.calculaDistanciaMedia(inicio, fim);

            if (media > 0) {
                System.out.println("A distância média é de: " + media);
            } else{
                System.out.println("Não foram encontrados registos entre as datas de viagens inseridas");
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar a distancia média de viagens.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIAGENS, "distanciaMedia - " + e.getMessage());
        }
    }
    void destinoMaisPopular(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Ver destino mais solicitado (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            LocalDateTime inicio, fim;
            while (true) {
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.print("Indique a data de início que pretenda pesquisar, no seguinte formato [dd/MM/aaaa]: ");
                String inicioStr = ler.nextLine();

                if (opcaoSair(inicioStr)) return;
                if (!isDataValida(inicioStr, formatterData)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Adata deve estar no formato [dd/MM/aaaa]. Tente novamente.");
                    continue;
                }
                inicio = LocalDateTime.parse(inicioStr, formatterData);
                break;
            }

            while (true) {
                DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.print("Indique a data de termino que pretenda pesquisar, no seguinte formato [dd/MM/aaaa]: ");
                String fimStr = ler.nextLine();

                if (opcaoSair(fimStr)) return;
                if (!isDataValida(fimStr, formatterData)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Adata deve estar no formato [dd/MM/aaaa]. Tente novamente.");
                    continue;
                }
                fim = LocalDateTime.parse(fimStr, formatterData);
                if(!isIntervaloDeDataValida(inicio, fim)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data de fim deve posterior a data de início. Tente novamente.");
                    continue;
                }
                break;
            }

            String destinoPopular = empresaTVDE.destinoPopular(inicio, fim);
            System.out.print("O destino mais poupular é: " + destinoPopular);

        } catch (Exception e) {
            System.out.println("Erro ao pesquisar a distancia média de viagens.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIAGENS, "destinoMaisPopular - " + e.getMessage());
        }
    }
    private void verListaDeClientes(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Ver lista de clientes por distância (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            int nif;
            double distanciaMin, distanciaMax;
            while (true) {
                System.out.print("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine();

                if (opcaoSair(nifStr)) return;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }

                nif = Integer.parseInt(nifStr);

                if (empresaTVDE.procurarNifCliente(nif) == null) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Esse NIF não está registado no sistema. Tente outro.");
                    continue;
                }
                break;
            }
            while (true) {
                System.out.print("Indique a distância mínima: ");
                String distanciaMinStr = ler.nextLine();

                if (opcaoSair(distanciaMinStr)) return;
                if (!isDistanciaValida(distanciaMinStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A distância só aceita númeoros. Tente novamente.");
                    continue;
                }
                distanciaMin = Double.parseDouble(distanciaMinStr);
                break;
            }
            while (true) {
                System.out.print("Indique a distância máxima: ");
                String distanciaMaxStr = ler.nextLine();

                if (opcaoSair(distanciaMaxStr)) return;
                if (!isDistanciaValida(distanciaMaxStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A distância só aceita númeoros. Tente novamente.");
                    continue;
                }
                distanciaMax = Double.parseDouble(distanciaMaxStr);
                break;
            }

            ArrayList<Cliente> listaClientes = empresaTVDE.clientesPorDistancia(distanciaMin,distanciaMax);
            if (listaClientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado!");
            } else{
                System.out.println("CLIENTES:");
                for (Cliente cliente : listaClientes) {
                    System.out.printf("Nome:\t%s\tContribuinte:\t%d\n", cliente.getNome(), cliente.getContribuinte());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar a lista de clientes.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIAGENS, "verListaDeClientes - " + e.getMessage());
        }
    }
    //endregion
}