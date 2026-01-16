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
    ArrayList<Reserva> reservas = empresaTVDE.carregarReservas();
    ArrayList<Viagem> viagens = empresaTVDE.carregarViagem();


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
        if(!clientes.isEmpty() || !condutores.isEmpty() || !viaturas.isEmpty() || !reservas.isEmpty() || !viagens.isEmpty()) {
            System.out.println(VERDE + "6\t-\tInformações" + RESET);
        }
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
        String regex = "^[a-zA-Z\\u00C0-\\u00FF ]+$";
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
            LocalDate dataFormatada = LocalDate.parse(data, formatterData);
            dataFormatada.atStartOfDay();
            return true;
        } catch (Exception e) {
             throw new RuntimeException(e);
        }
    }
    public boolean isIntervaloDeDataValida(LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (dataInicio == null || dataFim == null) return false;
        try{
            if(dataFim.isAfter(dataInicio)){
                return true;
            }
            return false;
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
        count++;
        if (!clientes.isEmpty()) {
            System.out.printf(VERDE + "%d\t-\tPesquisar Cliente\n" + RESET, count);
            count++;
        }
        if (!clientes.isEmpty()) {
            System.out.printf(VERDE + "%d\t-\tAtualizar Cliente\n" + RESET, count);
            count++;
        }
        if (!clientes.isEmpty()) {
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
        count++;
        if (!condutores.isEmpty()) {
            System.out.printf(VERDE + "%d\t-\tPesquisar Condutores\n" + RESET, count);
            count++;
        }
        if (!condutores.isEmpty()) {
            System.out.printf(VERDE + "%d\t-\tAtualizar Condutores\n" + RESET, count);
            count++;
        }
        if (!condutores.isEmpty()) {
            System.out.printf(VERDE + "%d\t-\tRemover Condutores\n" + RESET, count);
            count++;
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
        count++;
        if (!viaturas.isEmpty()) {
            System.out.printf(VERDE + "%d\t-\tPesquisar Viatura pela Matrícula\n" + RESET, count);
            count++;
        }
        if(!viaturas.isEmpty()){
            System.out.printf(VERDE + "%d\t-\tAtualizar Viatura pela Matrícula\n" + RESET, count);
            count++;
        }
        if(!viaturas.isEmpty()) {
            System.out.printf(VERDE + "%d\t-\tRemover Viatura\n" + RESET, count);
            count++;
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
                System.out.println("Indique a matrícula da viatura no formato [XXXXXX]: ");
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

            Viatura viatura = new Viatura(matricula.toUpperCase(),marca,modelo,anoDeFabrico,cor,status);
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
                System.out.println("Indique a matrícula [XXXXXX]: ");
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
                                    viatura.setStatus(status);
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

    //region Reservas

    void Reservas(Scanner ler) {
        int opcao;
        do{
            opcao = subMenuReservas(ler);
            if (opcao == 1) {
                criarReserva(ler);
            } else if (!reservas.isEmpty() && opcao == 2) {
                consultarReservas(ler);
            } else if (!reservas.isEmpty() && opcao == 3) {
                alterarReserva(ler);
            } else if (!reservas.isEmpty() && opcao == 4) {
                removerReserva(ler);
            } else if (opcao == 0) {
                break;
            } else {
                System.out.println("Opção Inválida!");
            }
        } while (opcao != 0);
    } //Completo

    int subMenuReservas(Scanner ler) {
        int count = 1;
        limparConsola();
        printTituloPrincipal();
        printTituloSecundario("RESERVAS");
        System.out.printf(VERDE + "%d\t-\tCriar Reserva\n" + RESET, count);
        if (!reservas.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tConsultar Reservas\n" + RESET, count);
        }
        if (!reservas.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tAlterar Reserva\n" + RESET, count);
        }
        if (!reservas.isEmpty()) {
            count++;
            System.out.printf(VERDE + "%d\t-\tRemover Reserva\n" + RESET, count);
        }
        System.out.println(VERDE + "0\t-\tVoltar ao menu anterior" + RESET);
        System.out.print("Indique a opção que queira realizar: ");
        try {
            return Integer.parseInt(ler.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
    void criarReserva(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Nova Reserva (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            String moradaOrigem, moradaDestino, horaStr, dataStr, nifStr;
            Viatura viatura;
            Cliente cliente;
            double distancia;
            int nif, indexCliente, indexViatura = 0;
            LocalDate dataReserva;
            LocalTime horaReserva;

            while (true) {
                System.out.println("Indique o NIF (Contribuinte): ");
                nifStr = ler.nextLine();

                if (opcaoSair(nifStr)) return;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }

                nif = Integer.parseInt(nifStr);

                if (empresaTVDE.procurarNifReserva(nif) != null) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Esse NIF já está registado no sistema. Tente outro.");
                    continue;
                }
                break;
            }

            while (true) {

                if (clientes.isEmpty()) {
                    System.out.println("Não existe nenhum cliente registado. Registe um cliente primeiro.");
                    return;
                }
                for (int i = 0; i < clientes.size(); i++) {
                    System.out.println((i + 1) + ". " + clientes.get(i).toString());
                }

                System.out.println("=== Escolha o Cliente ===");
                System.out.print("Introduza o número do cliente que deseja selecionar: ");
                String opcao = ler.nextLine();
                indexCliente = Integer.parseInt(opcao) - 1;
                if (opcaoSair(opcao)) {
                    return;
                }

                if (indexCliente < 0 || indexCliente >= clientes.size()) {
                    System.out.println("Cliente inválido.");
                    continue;
                }
                cliente = clientes.get(indexCliente);
                break;
            }

            while (true) {

                if (viaturas.isEmpty()) {
                    System.out.println("Não existe nenhuma Viatura registada. Registe uma viatura primeiro.");
                    return;
                }
                for (int i = 0; i < viaturas.size(); i++) {
                    System.out.println((i + 1) + ". " + viaturas.get(i).toString());
                }
                System.out.println("=== Escolha a viatura ===");
                System.out.print("Introduza o número da viatura que deseja selecionar: ");
                String opcao = ler.nextLine();
                indexViatura = Integer.parseInt(opcao) - 1;
                if (opcaoSair(opcao)) {
                    return;
                }

                if (indexViatura < 0 || indexViatura >= viaturas.size()) {
                    System.out.println("Viatura inválida.");
                    continue;
                }
                viatura = viaturas.get(indexViatura);
                break;
            }

            while (true) {
                System.out.print("Indique a data que pretende reservar (dd/MM/yyyy): ");
                dataStr = ler.nextLine();

                if (opcaoSair(dataStr)) return;

                try {
                    dataReserva = LocalDate.parse(
                            dataStr,
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    );

                    if (dataReserva.isBefore(LocalDate.now())) {
                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data não pode ser no passado. Tente novamente.");
                        continue;
                    }

                    break;

                } catch (DateTimeParseException e) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data informada não está no formato correto. Exemplo: 25/12/2026");
                }
            }

            while (true) {
                System.out.print("Indique a hora que pretende reservar (HH:mm): ");
                horaStr = ler.nextLine();

                if (opcaoSair(horaStr)) return;

                try {
                    horaReserva = LocalTime.parse(
                            horaStr,
                            DateTimeFormatter.ofPattern("HH:mm")
                    );

                    break;

                } catch (DateTimeParseException e) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A hora informada não está no formato correto. Exemplo: 14:30");
                }
            }

            while (true) {
                System.out.print("Indique a morada de origem [Rua de Santa catarina, 123 - 3210-450]: ");
                moradaOrigem = ler.nextLine();
                if (opcaoSair(moradaOrigem)) return;
                if (!isMoradaValida(moradaOrigem)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Indique a morada de destino [Rua de Santa catarina, 123 - 3210-450]: ");
                moradaDestino = ler.nextLine();
                if (opcaoSair(moradaDestino)) return;
                if (!isMoradaValida(moradaDestino)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Indique a distância em Kms: ");
                String distanciaStr = ler.nextLine();
                if (opcaoSair(distanciaStr)) return;
                if (!isDistanciaValida(distanciaStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A distância não está no formato correto. Tente novamente.");
                    continue;
                }
                distancia = Double.parseDouble(distanciaStr);
                break;
            }

            Reserva reserva = new Reserva(cliente, viatura, dataReserva.atTime(horaReserva), moradaOrigem, moradaDestino, distancia);
            if (empresaTVDE.adicionarReserva(reserva)) {
                System.out.print(VERDE_BRILHANTE + "\n\nReserva criada com sucesso!\n\n" + RESET);
                reservas = empresaTVDE.carregarReservas();
            }
        } catch (Exception e) {
            System.out.println("Dados inválidos.");
            //empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_RESERVA, e.getMessage());
        }
    }
    private void alterarReserva(Scanner ler) {
        System.out.println(ROXO + "\n\n--- Alterar Reserva (Escreva 'sair' para cancelar) ---\n\n" + RESET);
        try {
            while (true) {
                System.out.print("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine().trim();

                if (opcaoSair(nifStr)) return;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }
                int nif = Integer.parseInt(nifStr);
                Reserva reserva = empresaTVDE.procurarNifReserva(nif);
                if (reserva != null) {
                    while (true) {
                        System.out.println("Escolha a opção que deseja Alterar: ");
                        System.out.println("1\t-\tMorada de Origem");
                        System.out.println("2\t-\tMorada de Destino");
                        System.out.println("3\t-\tData da Reserva");
                        System.out.println("4\t-\tHora da Reserva");
                        System.out.println("5\t-\tCliente");
                        System.out.println("6\t-\tViatura");
                        System.out.println("0\t-\tSair");
                        String opcao = ler.nextLine();

                        switch (opcao) {
                            case "1":
                                while (true) {
                                    System.out.print("Indique a morada de origem [Rua de Santa catarina, 123 - 3210-450]: ");
                                    String moradaOrigem = ler.nextLine();
                                    if (opcaoSair(moradaOrigem)) return;
                                    if (!isMoradaValida(moradaOrigem)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                                        continue;
                                    }
                                    reserva.setMoradaOrigem(moradaOrigem);
                                    empresaTVDE.guardarAlteracoesReservas();
                                    System.out.println(VERDE_BRILHANTE + "\n\nMorada de origem atualizada com sucesso!\n\n" + RESET);
                                    return;
                                }
                            case "2":
                                while (true) {
                                    System.out.print("Indique a morada de destino por exemplo [Rua de Santa catarina, 123 - 3210-450]: ");
                                    String moradaDestino = ler.nextLine();
                                    if (opcaoSair(moradaDestino)) return;
                                    if (!isMoradaValida(moradaDestino)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada inserida não está no formato correto. Tente novamente.");
                                        continue;
                                    }
                                    reserva.setMoradaDestino(moradaDestino);
                                    empresaTVDE.guardarAlteracoesReservas();
                                    System.out.println(VERDE_BRILHANTE + "\n\nMorada de destino atualizada com sucesso!\n\n" + RESET);
                                    return;
                                }
                            case "3":
                                while (true) {
                                    try {
                                        System.out.print("Data/Hora atual da reserva (AAAA-MM-DDTHH:MM): ");
                                        LocalDateTime atual = LocalDateTime.parse(ler.next());

                                        System.out.println("Introduza a nova data:");
                                        System.out.print("Dia: ");
                                        int d = ler.nextInt();
                                        System.out.print("Mês: ");
                                        int m = ler.nextInt();
                                        System.out.print("Ano: ");
                                        int a = ler.nextInt();

                                        if (empresaTVDE.alterarDataReserva(atual, d, m, a)) {
                                            System.out.println("Data alterada! Hora mantêm-se!");
                                            empresaTVDE.guardarAlteracoesReservas();
                                            return;
                                        } else {
                                            System.out.println("Reserva não encontrada.");
                                        }
                                    } catch (DateTimeParseException e) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data informada não está no formato correto. Exemplo: 25/12/2026");
                                    }
                                }
                            case "4":
                                while (true) {
                                    try {
                                        System.out.print("Data e Hora atual da reserva (dd-MM-aaaa THH:MM): ");
                                        LocalDateTime atual = LocalDateTime.parse(ler.next());

                                        System.out.println("Introduza a nova hora:");
                                        System.out.print("Hora: ");
                                        int hora = ler.nextInt();
                                        System.out.print("Minuto: ");
                                        int minuto = ler.nextInt();
                                        if (empresaTVDE.alterarHoraReserva(atual, hora, minuto)) {
                                            System.out.println("Hora alterada! Data mantêm-se!");
                                            empresaTVDE.guardarAlteracoesReservas();
                                            return;
                                        } else {
                                            System.out.println("Reserva não encontrada.");
                                        }
                                    } catch (DateTimeParseException e) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A hora informada não está no formato correto. Exemplo: 14:30");
                                    }
                                }
                            case "5":
                                while (true) {
                                    System.out.println("=== Escolha o Cliente ===");
                                    for (Cliente cliente : clientes) {
                                        if (clientes.isEmpty()) {
                                            System.out.println("Não existe nenhum cliente registado. Registe um cliente primeiro.");
                                            return;
                                        }
                                        for (int i = 0; i < clientes.size(); i++) {
                                            System.out.println((i + 1) + ". " + clientes.get(i).toString());
                                        }
                                        System.out.print("Introduza o número do cliente: ");
                                        int indexCliente = ler.nextInt() - 1;
                                        ler.nextLine();
                                        if (opcaoSair(String.valueOf(cliente))) {
                                            return;
                                        }
                                        if (indexCliente < 0 || indexCliente >= clientes.size()) {
                                            System.out.println("Cliente inválido.");
                                            continue;
                                        }
                                        reserva.setCliente(cliente);
                                        System.out.println(VERDE_BRILHANTE + "\n\nCliente atualizado com sucesso!\n\n" + RESET);
                                        empresaTVDE.guardarAlteracoesReservas();
                                        return;
                                    }
                                }
                            case "6":
                                while (true) {
                                    System.out.println("=== Escolha a viatura ===");
                                    for (Viatura viatura : viaturas) {
                                        if (viaturas.isEmpty()) {
                                            System.out.println("Não existe nenhuma Viatura registada. Registe uma viatura primeiro.");
                                            return;
                                        }
                                        for (int i = 0; i < viaturas.size(); i++) {
                                            System.out.println((i + 1) + ". " + viaturas.get(i).toString());
                                        }
                                        System.out.print("Introduza o número da viatura: ");
                                        int indexViatura = ler.nextInt() - 1;
                                        ler.nextLine();
                                        if (opcaoSair(String.valueOf(viatura))) {
                                            return;
                                        }

                                        if (indexViatura < 0 || indexViatura >= viaturas.size()) {
                                            System.out.println("Viatura inválida.");
                                            continue;
                                        }
                                        reserva.setViatura(viatura);
                                        System.out.println(VERDE_BRILHANTE + "\n\nViatura atualizada com sucesso!\n\n" + RESET);
                                        empresaTVDE.guardarAlteracoesReservas();
                                        return;
                                    }
                                }
                            case "0":
                                return;
                            default:
                                System.out.print("Opção invalida. Tente novamente.");
                                break;
                        }
                    }
                } else {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Reserva não encontrada.");
                }
            }
        } catch  (Exception e) {
            System.out.println("Dados inválidos.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_RESERVAS, e.getMessage());
        }
    }
    private void consultarReservas(Scanner ler) {
        System.out.print(ROXO + "\n\n--- Consultar Reservas (Escreva 'sair' para cancelar) ---\n\n" + RESET);
        while (true) {
            System.out.print("Indique o NIF: ");
            String nifStr = ler.nextLine();
            if (opcaoSair(nifStr)) break;
            if (!isNifValido(nifStr)) {
                System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                continue;
            }

            try {
                int nif = Integer.parseInt(nifStr);
                Reserva reserva = empresaTVDE.procurarNifReserva(nif);
                if (reserva != null) {
                    System.out.println(reserva.toString());
                    System.out.println("Enter para continuar...");
                    ler.nextLine();
                    break;
                } else System.out.println("Reserva não encontrado.");
            } catch (NumberFormatException e) {
                System.out.println("NIF inválido.");
            }
        }
    }
    void removerReserva(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Reserva (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            while (true) {
                System.out.println("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine();
                if (opcaoSair(nifStr)) break;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }
                int nif = Integer.parseInt(nifStr);
                Reserva reserva = empresaTVDE.procurarNifReserva(nif);
                if (reserva != null) {
                    System.out.print("Tem certeza que deseja remover a reserva com NIF %s? [S/N]" + reserva.getCliente().getContribuinte());
                    String validacao = ler.nextLine().trim();

                    if (validacao.equalsIgnoreCase("S")) {
                        if (empresaTVDE.removerReservas(reserva.getCliente().getContribuinte())) {
                            System.out.print("Reserva %s removida com sucesso." + reserva.getCliente().getContribuinte());
                            reservas = empresaTVDE.carregarReservas();
                            break;
                        } else {
                            System.out.println("Erro: Reserva não encontrada ou não pode ser removida.");
                        }
                    } else {
                        System.out.println("Operação cancelada.");
                        break;
                    }
                } else {
                    System.out.println(VERMELHO_BRILHANTE + "Erro:" + RESET + " Reserva não encontrado.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro de input." + e.getMessage());
        }
    }
    //endregion

    //region Viagens
    void Viagens(Scanner ler) {
        int opcao;
        do {
            opcao = subMenuViagens(ler);
            if (opcao == 1) {
                criarViagem(ler);
            } else if (!viagens.isEmpty() && opcao == 2) {
                transformarReservaEmViagem(ler);
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
        System.out.printf(VERDE + "%d\t-\tCriar Viagens\n" + RESET, count);
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

    void criarViagem(Scanner ler) {
        System.out.print(ROXO + "\n\n--- Nova Viagem (Escreva 'sair' para cancelar) ---\n\n" + RESET);
        try {
            String moradaOrigem, moradaDestino, dataStr;
            int indexCliente, indexViatura, indexCondutor;
            Cliente cliente;
            Viatura viatura;
            Condutor condutor;
            LocalDate dataInicio;
            LocalTime horaReserva;
            while (true) {
                System.out.print("Indique a data que pretende reservar (dd/MM/yyyy): ");
                dataStr = ler.nextLine();

                if (opcaoSair(dataStr)) return;

                try {
                    dataInicio = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    if (dataInicio.isBefore(LocalDate.now())) {
                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data não pode ser no passado. Tente novamente.");
                        continue;
                    }
                    break;

                } catch (DateTimeParseException e) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data informada não está no formato correto. Exemplo: 25/12/2026");
                }
            }

            while (true) {
                System.out.print("Indique a hora que pretende reservar (HH:mm): ");
                String horaStr = ler.nextLine();

                if (opcaoSair(horaStr)) return;

                try {
                    horaReserva = LocalTime.parse(
                            horaStr,
                            DateTimeFormatter.ofPattern("HH:mm")
                    );

                    break;

                } catch (DateTimeParseException e) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A hora informada não está no formato correto. Exemplo: 14:30");
                }
            }

            while (true) {
                System.out.print("Indique a morada de origem [Rua de Santa catarina, 123 - 3210-450]: ");
                moradaOrigem = ler.nextLine();
                if (opcaoSair(moradaOrigem)) return;
                if (!isMoradaValida(moradaOrigem)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Indique a morada de origem [Rua de Santa catarina, 123 - 3210-450]: ");
                moradaDestino = ler.nextLine();
                if (opcaoSair(moradaDestino)) return;
                if (!isMoradaValida(moradaDestino)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.println("=== Escolha o Cliente ===");

                if (clientes.isEmpty()) {
                    System.out.println("Não existe nenhum cliente registado. Registe um cliente primeiro.");
                    return;
                }
                for (int i = 0; i < clientes.size(); i++) {
                    System.out.println((i + 1) + ". " + clientes.get(i).toString());
                }
                System.out.print("Introduza o número do cliente: ");
                String opcao = ler.nextLine();
                indexCliente = Integer.parseInt(opcao) - 1;
                if (opcaoSair(opcao)) {
                    return;
                }

                if (indexCliente < 0 || indexCliente >= clientes.size()) {
                    System.out.println("Cliente inválido.");
                    continue;
                }
                cliente = clientes.get(indexCliente);
                break;
            }

            while (true) {
                System.out.println("=== Escolha a viatura ===");

                if (viaturas.isEmpty()) {
                    System.out.println("Não existe nenhuma Viatura registada. Registe uma viatura primeiro.");
                    return;
                }
                for (int i = 0; i < viaturas.size(); i++) {
                    System.out.println((i + 1) + ". " + viaturas.get(i).toString());
                }
                System.out.print("Introduza o número do cliente: ");
                String opcao = ler.nextLine();
                indexViatura = Integer.parseInt(opcao) - 1;
                if (opcaoSair(opcao)) {
                    return;
                }

                if (indexViatura < 0 || indexViatura >= viaturas.size()) {
                    System.out.println("Viatura inválida.");
                    continue;
                }
                viatura = viaturas.get(indexViatura);
                break;
            }

            while (true) {
                System.out.println("=== Escolha o condutor ===");

                if (condutores.isEmpty()) {
                    System.out.println("Não existe nenhum condutor registado. Registe um condutor primeiro.");
                    return;
                }
                for (int i = 0; i < condutores.size(); i++) {
                    System.out.println((i + 1) + ". " + condutores.get(i).toString());
                }
                System.out.print("Introduza o número do condutor: ");
                String opcao = ler.nextLine();
                indexCondutor = Integer.parseInt(opcao) - 1;
                if (opcaoSair(opcao)) {
                    if (indexCondutor < 0 || indexCondutor >= condutores.size()) {
                        System.out.println("Condutor inválido.");
                        return;
                    }
                    break;
                }

                condutor = condutores.get(indexCondutor);

                Viagem viagem = new Viagem(cliente, condutor, viatura, dataInicio, horaReserva, concluida, moradaOrigem, moradaDestino, custoViagem);
                if (empresaTVDE.adicionarViagem(viagem)) {
                    System.out.print(VERDE_BRILHANTE + "\n\nViagem criada com sucesso!\n\n" + RESET);
                    viagens = empresaTVDE.carregarViagem();
                }
            }
        } catch(Exception e){
            System.out.println("Dados inválidos.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIAGENS, e.getMessage());
        }

    }

    void removerViagem (Scanner ler){
        try {
            System.out.println(ROXO + "\n\n--- Remover Viagem (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            while (true) {
                System.out.println("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine().trim();
                System.out.println("Indique a data da viagem:");
                LocalDateTime dataInicio = LocalDateTime.parse(ler.nextLine());
                if (opcaoSair(nifStr)) break;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }
                int nif = Integer.parseInt(nifStr);
                if(empresaTVDE.removerViagem(nif,  dataInicio)) {
                    Viagem viagem = empresaTVDE.procurarViagens(nif, dataInicio);
                    System.out.println("A " + viagem + " foi removida!");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro de input." + e.getMessage());
        }
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

        Viagem novaViagem = new Viagem();
        viagens.add(novaViagem);

        System.out.println("Sucesso! A reserva foi transformada em viagem.");
    }
    //endregion

    //region Informações

    /**
     * Menu informações
     * @param ler
     */
    void informacoes(Scanner ler) {
        int opcao;
        do {
            opcao = subMenuInformacoes(ler);
            if (opcao == 1) {
                pesquisarViagem(ler);
            } else if (opcao == 2) {
                totalFaturado(ler);
            } else if (opcao == 3) {
                distanciaMedia(ler);
            } else if (opcao == 4) {
                destinoMaisPopular(ler);
            } else if (opcao == 5) {
                verListaDeClientes(ler);
            } else if (opcao == 0) {
                break;
            } else {
                System.out.println("Opção Inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Submenu informações
     * @param ler
     * @return retorna um inteiro com a opção selecionada
     */
    int subMenuInformacoes(Scanner ler) {
        limparConsola();
        printTituloPrincipal();
        printTituloSecundario("INFORMAÇÕES");

        int count = 1;
        ArrayList<Integer> mapOpcoes = new ArrayList<>();
        if (!viagens.isEmpty()) {
            System.out.printf(VERDE + "%d\t-\tPesquisar Viagem\n" + RESET, count);
            mapOpcoes.add(1);
            count++;
        }
        if (!condutores.isEmpty()) {
            System.out.printf(VERDE + "%d\t-\tPesquisar Valor Faturado por Motorista\n" + RESET, count);
            mapOpcoes.add(2);
            count++;
        }
        if (!viaturas.isEmpty()) {
            System.out.printf(VERDE + "%d\t-\tDistância Média das Viagens\n" + RESET, count);
            mapOpcoes.add(3);
            count++;
        }
        if (!reservas.isEmpty() || !viagens.isEmpty()) {
            System.out.printf(VERDE + "%d\t-\tDestino mais solicitado\n" + RESET, count);
            mapOpcoes.add(4);
            count++;
        }
        if (!clientes.isEmpty()) {
            System.out.printf(VERDE + "%d\t-\tLista de clientes\n" + RESET, count);
            mapOpcoes.add(5);
        }
        System.out.println(VERDE + "0\t-\tVoltar ao menu anterior" + RESET);
        System.out.print("Indique a opção que queira realizar: ");
        try {
            int opcao = Integer.parseInt(ler.nextLine());

            if (opcao == 0) return 0;

            if (opcao > 0 && opcao <= mapOpcoes.size()) {
                return mapOpcoes.get(opcao - 1);
            } else {
                return -1;
            }

        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Função para limpar o console
     */
    public void limparConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Método que pesquisa as viagens de um cliente em um intervalo de datas
     * @param ler
     */
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
                LocalDate dataFormatada = LocalDate.parse(inicioStr, formatterData);
                inicio = dataFormatada.atStartOfDay();
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
                LocalDate dataFormatada = LocalDate.parse(fimStr, formatterData);
                fim = dataFormatada.atStartOfDay();
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
                    System.out.println("\nEnter para continuar...");
                    ler.nextLine();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar a viagem.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIAGENS, "pesquisarViagem - " + e.getMessage());
        }
    }

    /**
     * Calcula o valor total faturado por um condutor
     * @param ler
     */
    void totalFaturado(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Ver total faturado por condutor (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            String nomeCondutor;
            int nif;
            LocalDateTime inicio, fim;
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
                System.out.print("Indique a data de início que pretenda pesquisar, no seguinte formato [dd/MM/aaaa]: ");
                String inicioStr = ler.nextLine();

                if (opcaoSair(inicioStr)) return;
                if (!isDataValida(inicioStr, formatterData)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Adata deve estar no formato [dd/MM/aaaa]. Tente novamente.");
                    continue;
                }
                LocalDate dataFormatada = LocalDate.parse(inicioStr, formatterData);
                inicio = dataFormatada.atStartOfDay();
                break;
            }

            while (true) {
                System.out.print("Indique a data de termino que pretenda pesquisar, no seguinte formato [dd/MM/aaaa]: ");
                String fimStr = ler.nextLine();

                if (opcaoSair(fimStr)) return;
                if (!isDataValida(fimStr, formatterData)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Adata deve estar no formato [dd/MM/aaaa]. Tente novamente.");
                    continue;
                }
                LocalDate dataFormatada = LocalDate.parse(fimStr, formatterData);
                fim = dataFormatada.atStartOfDay();
                if(!isIntervaloDeDataValida(inicio, fim)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data de fim deve posterior a data de início. Tente novamente.");
                    continue;
                }
                break;
            }

            double total = empresaTVDE.calcularFaturacaoTotal(nif, inicio, fim);
            System.out.printf("Total faturado pelo condutor %s entre as datas %s e %s é €%.2f", nomeCondutor, inicio.format(formatterData), fim.format(formatterData), total);
            System.out.println("\nEnter para continuar...");
            ler.nextLine();
        } catch (Exception e) {
            System.out.println("Erros ao pesquisar o total faturado.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIAGENS, "totalFaturado -" + e.getMessage());
        }
    }

    /**
     * Calcula a distância média em um intervalo de datas
     * @param ler
     */
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
                LocalDate dataFormatada = LocalDate.parse(inicioStr, formatterData);
                inicio = dataFormatada.atStartOfDay();
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
                LocalDate dataFormatada = LocalDate.parse(fimStr, formatterData);
                fim = dataFormatada.atStartOfDay();
                if(!isIntervaloDeDataValida(inicio, fim)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data de fim deve posterior a data de início. Tente novamente.");
                    continue;
                }
                break;
            }

            double media = empresaTVDE.calculaDistanciaMedia(inicio, fim);

            if (media > 0) {
                System.out.println("A distância média é de: " + media);
                System.out.println("\nEnter para continuar...");
                ler.nextLine();
            } else{
                System.out.println("Não foram encontrados registos entre as datas de viagens inseridas");
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar a distancia média de viagens.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIAGENS, "distanciaMedia - " + e.getMessage());
        }
    }

    /**
     * Consulta o destino mais solicitado entre todas as viagens
     * @param ler
     */
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
                LocalDate dataFormatada = LocalDate.parse(inicioStr, formatterData);
                inicio = dataFormatada.atStartOfDay();
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
                LocalDate dataFormatada = LocalDate.parse(fimStr, formatterData);
                fim = dataFormatada.atStartOfDay();
                if(!isIntervaloDeDataValida(inicio, fim)){
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A data de fim deve posterior a data de início. Tente novamente.");
                    continue;
                }
                break;
            }

            String destinoPopular = empresaTVDE.destinoPopular(inicio, fim);
            System.out.print("O destino mais poupular é: " + destinoPopular);
            System.out.println("\nEnter para continuar...");
            ler.nextLine();

        } catch (Exception e) {
            System.out.println("Erro ao pesquisar a distancia média de viagens.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIAGENS, "destinoMaisPopular - " + e.getMessage());
        }
    }

    /**
     * Método responsável por buscar lista de cliente por uma distancia escolhida pelo utilizador
     * @param ler
     */
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
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A distância só aceita números. Tente novamente.");
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
                System.out.println("\nEnter para continuar...");
                ler.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar a lista de clientes.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIAGENS, "verListaDeClientes - " + e.getMessage());
        }
    }
    //endregion
}