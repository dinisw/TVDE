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
        return marca.trim().length() >=2;
    } //! dinis fazer
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

        return cor.trim().matches("^[a-zA-ZãõáéíóúçÃÕÁÉÍÓÚÇ ]+$");
    } //! dinis fazer
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

        return nome.trim().matches("^[a-zA-ZãõáéíóúçÃÕÁÉÍÓÚÇ ]{2,}$");
    } //! dinis fazer
    public boolean isCcValido(String cc) {
        if (cc == null) return false;
        String regex = "^\\d{8}$";

        return cc.trim().matches(regex);
    }

    public boolean isCartaValido(String carta) {
        if (carta == null) return false;
        String regex = "^[A-Z]-\\d{7}$";

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
                if (!isMoradaValido(morada)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
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
                                    if (!isIdadeValido(idadeStr)) {
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
                                    if (!isMoradaValido(morada)) {
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
                if (!isMoradaValido(morada)) {
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
                                if (!isIdadeValido(idadeStr)) {
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
                                if (!isMoradaValido(morada)) {
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
                                if (!isCartaValido(cartaStr)) {
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
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A marca deve ser escrito com letras. Tente novamente.");
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
        do {
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
            String moradaOrigem, moradaDestino, horaStr, dataStr, cliente = "", viatura = "", nifStr;
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
                System.out.println("=== Escolha o Cliente ===");

                if (clientes.isEmpty()) {
                    System.out.println("Não existe nenhum cliente registado. Registe um cliente primeiro.");
                    return;
                }
                for (int i = 0; i < clientes.size(); i++) {
                    System.out.println((i + 1) + ". " + clientes.get(i).toString());
                }
                System.out.print("Introduza o número do cliente: ");
                indexCliente = ler.nextInt() - 1;
                ler.nextLine();
                if (opcaoSair(cliente)) {
                    return;
                }

                if (indexCliente < 0 || indexCliente >= clientes.size()) {
                    System.out.println("Cliente inválido.");
                    continue;
                }
                Cliente cliente1 = clientes.get(indexCliente);
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
                indexViatura = ler.nextInt() - 1;
                ler.nextLine();
                if (opcaoSair(viatura)) {
                    return;
                }

                if (indexViatura < 0 || indexViatura >= viaturas.size()) {
                    System.out.println("Viatura inválida.");
                    continue;
                }
                Viatura viatura1 = viaturas.get(indexViatura);
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
                if (!isMoradaValido(moradaOrigem)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Indique a morada de origem [Rua de Santa catarina, 123 - 3210-450]: ");
                moradaDestino = ler.nextLine();
                if (opcaoSair(moradaDestino)) return;
                if (!isMoradaValido(moradaDestino)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
            }
        Reserva reserva = new Reserva(dataReserva, horaReserva, moradaOrigem, moradaDestino, viatura, cliente);
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
        try {
            System.out.println(ROXO + "\n\n--- Alterar Reserva (Escreva 'sair' para cancelar) ---\n\n" + RESET);
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
                                    if (!isMoradaValido(moradaOrigem)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                                        continue;
                                    }
                                    break;
                                }
                                break;
                            case "2":
                                while (true) {
                                    System.out.print("Indique a morada de origem [Rua de Santa catarina, 123 - 3210-450]: ");
                                    String moradaDestino = ler.nextLine();
                                    if (opcaoSair(moradaDestino)) return;
                                    if (!isMoradaValido(moradaDestino)) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                                        continue;
                                    }
                                    break;
                                }
                                break;
                            case "3":
                                while (true) {
                                    System.out.print("Indique a data que pretende reservar (dd/MM/yyyy): ");
                                    String dataStr = ler.nextLine();

                                    if (opcaoSair(dataStr)) return;

                                    try {
                                        LocalDate dataReserva = LocalDate.parse(
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
                                break;
                            case "4":
                                while (true) {
                                    System.out.print("Indique a hora que pretende reservar (HH:mm): ");
                                    String horaStr = ler.nextLine();

                                    if (opcaoSair(horaStr)) return;

                                    try {
                                        LocalTime horaReserva = LocalTime.parse(
                                                horaStr,
                                                DateTimeFormatter.ofPattern("HH:mm")
                                        );

                                        break;

                                    } catch (DateTimeParseException e) {
                                        System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A hora informada não está no formato correto. Exemplo: 14:30");
                                    }
                                }
                                break;
                            case "5":
                                while (true) {
                                    String cliente = "";
                                    System.out.println("=== Escolha o Cliente ===");

                                    if (cliente.isEmpty()) {
                                        System.out.println("Não existe nenhum cliente registado. Registe um cliente primeiro.");
                                        return;
                                    }
                                    for (int i = 0; i < clientes.size(); i++) {
                                        System.out.println((i + 1) + ". " + clientes.get(i).toString());
                                    }
                                    System.out.print("Introduza o número do cliente: ");
                                    int indexCliente = ler.nextInt() - 1;
                                    ler.nextLine();
                                    if (opcaoSair(cliente)) {
                                        return;
                                    }

                                    if (indexCliente < 0 || indexCliente >= clientes.size()) {
                                        System.out.println("Cliente inválido.");
                                        continue;
                                    }
                                    Cliente cliente1 = clientes.get(indexCliente);
                                    break;
                                }
                                break;
                            case "6":
                                while (true) {
                                    String viatura = "";
                                    System.out.println("=== Escolha a viatura ===");

                                    if (viaturas.isEmpty()) {
                                        System.out.println("Não existe nenhuma Viatura registada. Registe uma viatura primeiro.");
                                        return;
                                    }
                                    for (int i = 0; i < viaturas.size(); i++) {
                                        System.out.println((i + 1) + ". " + viaturas.get(i).toString());
                                    }
                                    System.out.print("Introduza o número do cliente: ");
                                    int indexViatura = ler.nextInt() - 1;
                                    ler.nextLine();
                                    if (opcaoSair(viatura)) {
                                        return;
                                    }

                                    if (indexViatura < 0 || indexViatura >= viaturas.size()) {
                                        System.out.println("Viatura inválida.");
                                        continue;
                                    }
                                    Viatura viatura1 = viaturas.get(indexViatura);
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
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "Reserva não encontrada.");
                }
            }
        } catch  (Exception e) {
            System.out.println("Dados inválidos.");
            //empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_RESERVA, e.getMessage());
        }
    }


    private void consultarReservas(Scanner ler) {
        while (true) {
            System.out.print(ROXO + "\n\n--- Consultar Reservas (Escreva 'sair' para cancelar) ---\n\n" + RESET);

            System.out.print("Indique o NIF: ");
            String nifStr = ler.nextLine();
            if (!isNifValido(nifStr)) {
                System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                continue;
            }
            if (opcaoSair(nifStr)) break;

            try {
                int nif = Integer.parseInt(nifStr);
                Reserva reserva = empresaTVDE.procurarNifReserva(nif);
                if (reserva != null) {
                    System.out.println(reserva.toString());
                    System.out.println("Enter para continuar...");
                    ler.nextLine();
                    break;
                } else System.out.println("Condutor não encontrado.");
            } catch (NumberFormatException e) {
                System.out.println("NIF inválido.");
            }
        }
    }

    void removerReserva(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Remover Reserva (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            while (true) {
                System.out.println("Indique o NIF (Contribuinte): ");
                String nifStr = ler.nextLine().trim();
                if (opcaoSair(nifStr)) break;
                if (!isNifValido(nifStr)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                    continue;
                }
                int nif = Integer.parseInt(nifStr);
                Reserva reserva = empresaTVDE.procurarNifReserva(nif);
                if (reserva != null) {
                    System.out.printf("Tem certeza que deseja remover a reserva com NIF %s? [S/N]", reserva.getContribuinte());
                    String validacao = ler.nextLine().trim();

                    if (validacao.equalsIgnoreCase("S")) {
                        if (empresaTVDE.removerCondutor(reserva.getContribuinte())) {
                            System.out.printf("Condutor %s removido com sucesso.", reserva.getContribuinte());
                            condutores = empresaTVDE.carregarCondutores();
                            break;
                        } else {
                            System.out.println("Erro: Reserva não encontrada ou não pode ser removida.");
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

        /*Permitir trasnformar uma reserva em viagem
         * Validar se a viagem já existe antes de inserir
         * */

        /*
         * Criar um SUb menu para Registrar Viagens e Eliminar
         * */
        //transformarReservaEmViagem(ler);
        //criarViagem(ler);
        //removerViagem(ler);


    void criarViagem(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Nova Viagem (Escreva 'sair' para cancelar) ---\n\n" + RESET);
            String moradaOrigem, moradaDestino, dataStr, horaStr, cliente = "", viatura = "", condutor ="";
            int indexCliente, indexViatura, indexCondutor = 0;
            LocalDate dataInicio;
            LocalTime horaInicio;
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
                horaStr = ler.nextLine();

                if (opcaoSair(horaStr)) return;

                try {
                    horaInicio = LocalTime.parse(horaStr, DateTimeFormatter.ofPattern("HH:mm"));

                    break;

                } catch (DateTimeParseException e) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A hora informada não está no formato correto. Exemplo: 14:30");
                }
            }
            while (true) {
                System.out.print("Indique a morada de origem [Rua de Santa catarina, 123 - 3210-450]: ");
                moradaOrigem = ler.nextLine();
                if (opcaoSair(moradaOrigem)) return;
                if (!isMoradaValido(moradaOrigem)) {
                    System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "A morada informada não está no formato correto. Tente novamente.");
                    continue;
                }
                break;
            }

            while (true) {
                System.out.print("Indique a morada de origem [Rua de Santa catarina, 123 - 3210-450]: ");
                moradaDestino = ler.nextLine();
                if (opcaoSair(moradaDestino)) return;
                if (!isMoradaValido(moradaDestino)) {
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
                indexCliente = ler.nextInt() - 1;
                ler.nextLine();
                if (opcaoSair(cliente)) {
                    return;
                }

                if (indexCliente < 0 || indexCliente >= clientes.size()) {
                    System.out.println("Cliente inválido.");
                    continue;
                }
                Cliente cliente1 = clientes.get(indexCliente);
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
                indexViatura = ler.nextInt() - 1;
                ler.nextLine();
                if (opcaoSair(viatura)) {
                    return;
                }

                if (indexViatura < 0 || indexViatura >= viaturas.size()) {
                    System.out.println("Viatura inválida.");
                    continue;
                }
                Viatura viatura1 = viaturas.get(indexViatura);
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
                indexCondutor = ler.nextInt() - 1;
                ler.nextLine();
                if (opcaoSair(condutor)) {
                    return;
                }

                if (indexCondutor < 0 || indexCondutor >= condutores.size()) {
                    System.out.println("Condutor inválido.");
                    continue;
                }
                Condutor condutor1 = condutores.get(indexCondutor);
                break;
            }

            Viagem viagem = new Viagem();
            if (empresaTVDE.adicionarViagem(viagem)){
                System.out.println(VERDE_BRILHANTE + "\n\nViagem registada com sucesso!\n\n" + RESET);
                //condutores = empresaTVDE.carregarViagens();
            }
        } catch (Exception e) {
            System.out.println("Dados inválidos.");
            //empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_VIAGEM, e.getMessage());
        }

    }

            void removerViagem (Scanner ler){
                try {
                    System.out.println(ROXO + "\n\n--- Remover Viagem (Escreva 'sair' para cancelar) ---\n\n" + RESET);
                    while (true) {
                        System.out.println("Indique o NIF (Contribuinte): ");
                        String nifStr = ler.nextLine().trim();
                        if (opcaoSair(nifStr)) break;
                        if (!isNifValido(nifStr)) {
                            System.out.println(VERMELHO_BRILHANTE + "Erro: " + RESET + "O NIF deve conter exatamente 9 dígitos numéricos. Tente novamente.");
                            continue;
                        }
                        int nif = Integer.parseInt(nifStr);
                        Viagem viagem = empresaTVDE.procurarViagens(int contribuinte)
                    }
                } catch (Exception e) {
                    System.out.println("Erro de input." + e.getMessage());
                }
            }

            void transformarReservaEmViagem (Scanner ler, Cliente cliente, Condutor condutor, Viatura viatura){


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
                            v.getDatanIcio().equals(reservaSelecionada.getDataHoraInicio())) {
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
            void informacoes (Scanner ler){
                /*Pesquisar viagens de um cliente num intervalo de data dada pelo liente
                 *Apresentar valor total faturado por um motorista num intervalo de datas indicado pelo utilizador
                 * Apresentar a distância media em kms das viagens num intervalo de data
                 * Apresentar o destino mais solicitado (reservas e viagens) durante intervalo de data
                 * Apresentar lista de clientes em viagens a distância esteja dentro do indicado pelo utilizador
                 *
                 */

            }


            int menuInformacoes (Scanner ler){
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
            //endregion

            public void limparConsola () {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

            //Pesquisar viagens de um cliente num intervalo de data dada pelo cliente
            void pesquisarViagem (Scanner ler){
                try {
                    System.out.println("Indique o contribuinte do Cliente:");
                    int contribuinte = Integer.parseInt(ler.nextLine());
                    System.out.println("Indique a data que pretenda pesquisar no seguinte formato(dd/MM/aaaa)");
                    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDateTime inicio = LocalDateTime.parse(ler.nextLine(), formatterData);
                    System.out.println("Indique a segunda data no formato(dd/MM/aaaa)");
                    LocalDateTime fim = LocalDateTime.parse(ler.nextLine(), formatterData);
                    ArrayList<Viagem> resultado = empresaTVDE.pesquisarViagemClienteData(contribuinte, inicio, fim);
                    if (resultado != null) {
                        for (Viagem viagem : resultado) {
                            System.out.println(viagem.toString());
                        }
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Erro no formato da data" + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao inserir o contribuinte" + e.getMessage());
                }
            }

            //Apresentar valor total faturado por um motorista num intervalo de datas indicado pelo utilizador
            void totalFaturado (Scanner ler){
                try {
                    System.out.println("Indique o contribuinte do Condutor:");
                    int nifCondutor = Integer.parseInt(ler.nextLine());
                    for (Condutor condutor : condutores) {
                        if (condutor.getContribuinte() != nifCondutor) {
                            break;
                        }
                    }
                    System.out.println("Indique a data de inicio com o formato DD/MM/aaaa");
                    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDateTime inicio = LocalDateTime.parse(ler.nextLine(), formatterData);
                    System.out.println("Indique a data de fim com o formato DD/MM/aaaa");
                    LocalDateTime fim = LocalDateTime.parse(ler.nextLine(), formatterData);
                    double total = empresaTVDE.calcularFaturacaoTotal(nifCondutor, inicio, fim);
                    System.out.println("Total faturado pelo condutor entre as datas (" + inicio + ") e (" + fim + ") é :" + total);
                } catch (DateTimeParseException e) {
                    System.out.println("Erro no formato da data" + e.getMessage());
                }
            }

            void distanciaMedia (Scanner ler){
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
                } catch (DateTimeParseException e) {
                    System.out.println("Erro no formato da data" + e.getMessage());
                }
    /*private void verListaDeClientes(Scanner ler, String matricula) {
    } */
            }
            //Apresentar o destino mais solicitado (reservas e viagens) durante intervalo de data

            void destinoMaisPopular (Scanner ler){
                try {
                    System.out.println("Indique a data de inicial em formato (dd/MM/aaaa): ");
                    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDateTime dataInicio = LocalDateTime.parse(ler.nextLine(), formatterData);
                    System.out.println("Indique a data final em formato (dd/MM/aaaa): ");
                    LocalDateTime dataFim = LocalDateTime.parse(ler.nextLine(), formatterData);
                    if (dataInicio.isBefore(dataFim)) {
                        System.out.println("Erro a data inserida é inválida!");
                    } else {
                        String destinoPopular = empresaTVDE.destinoPopular(dataInicio, dataFim);
                        System.out.println("O destino mais poupular é: " + destinoPopular);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Erro no formato da data" + e.getMessage());
                }
            }

            //Apresentar lista de clientes em viagens a distância esteja dentro do indicado pelo utilizador
            private void verListaDeClientes (Scanner ler){
                try {
                    System.out.println("Indique o contribuinte do Cliente:");
                    int contribuinte = Integer.parseInt(ler.nextLine());
                    System.out.println("Indique a distância mínima: ");
                    double distanciaMinima = Double.parseDouble(ler.nextLine());
                    System.out.println("Indique a disância máxima: ");
                    double distanciaMaxima = Double.parseDouble(ler.nextLine());

                    ArrayList<Cliente> listaClientes = empresaTVDE.clientesPorDistancia(distanciaMinima, distanciaMaxima);
                    if (listaClientes.isEmpty()) {
                        System.out.println("Nenhum cliente encontrado!");
                    } else {
                        for (Cliente cliente : listaClientes) {
                            System.out.println("Cliente: " + cliente.getNome() + "com o contribuinte: " + cliente.getContribuinte());
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro! Digite números validos!" + e.getMessage());
                }
            }
        }