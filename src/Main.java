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

    /** Caminho do ficheiro de logs de erros relacionados com viaturas. */
    private final String CAMINHO_FICHEIRO_LOGS_ERROS_VIATURAS = "logsErrosViaturas.txt";
    /** Caminho do ficheiro de logs de erros relacionados com Clientes. */
    private final String CAMINHO_FICHEIRO_LOGS_ERROS_CLIENTE = "logsErrosClientes.txt";
    /** Caminho do ficheiro de logs de erros relacionados com condutores. */
    private final String CAMINHO_FICHEIRO_LOGS_ERROS_CONDUTOR = "logsErrosCondutor.txt";
    /** Caminho do ficheiro de logs de erros relacionados com reservas. */
    private final String CAMINHO_FICHEIRO_LOGS_ERROS_RESERVA = "logsErrosReserva.txt";
    /** Caminho do ficheiro de logs de erros relacionados com viagens. */
    private final String CAMINHO_FICHEIRO_LOGS_ERROS_VIAGEM = "logsErrosViagem.txt";

    /** Instância principal da empresa TVDE responsável pela gestão dos dados. */
    EmpresaTVDE empresaTVDE = new EmpresaTVDE();
    /** Lista de clientes carregados a partir do sistema. */
    ArrayList<Cliente> clientes = empresaTVDE.carregarClientes();
    /** Lista de condutores carregados a partir do sistema. */
    ArrayList<Condutor> condutores = empresaTVDE.carregarCondutores();
    /** Lista de viaturass carregados a partir do sistema. */
    ArrayList<Viatura> viaturas = empresaTVDE.carregarViaturas();
    /** Lista de reservas carregados a partir do sistema. */
    ArrayList<Reserva> reservas = new ArrayList<>();
    /** Lista de viagens carregados a partir do sistema. */
    ArrayList<Viagem> viagens = new ArrayList<>();

    //region Design
    /** Código ANSI utilizado para repor a formatação padrão da consola. */
    public static final String RESET = "\u001B[0m";

    //Cores
    /** Código ANSI utilizado para apresentar texto a vermelho na consola. */
    public static final String VERMELHO = "\u001B[31m";
    /** Código ANSI utilizado para apresentar texto a verde na consola. */
    public static final String VERDE = "\u001B[32m";
    /** Código ANSI utilizado para apresentar texto a amarelo na consola. */
    public static final String AMARELO = "\u001B[33m";
    /** Código ANSI utilizado para apresentar texto a azul na consola. */
    public static final String AZUL = "\u001B[34m";
    /** Código ANSI utilizado para apresentar texto a roxo na consola. */
    public static final String ROXO = "\u001B[35m";
    /** Código ANSI utilizado para apresentar texto a ciano na consola. */
    public static final String CIANO = "\u001B[36m";

    /*Cores Brilhantes*/
    /** Código ANSI utilizado para apresentar texto a vermelho brilhante na consola. */
    public static final String VERMELHO_BRILHANTE = "\u001B[91m";
    /** Código ANSI utilizado para apresentar texto a verde brilhante na consola. */
    public static final String VERDE_BRILHANTE = "\u001B[92m";
    /** Código ANSI utilizado para apresentar texto a amarelo brilhante na consola. */
    public static final String AMARELO_BRILHANTE = "\u001B[93m";
    /** Código ANSI utilizado para apresentar texto a azul brilhante na consola. */
    public static final String AZUL_BRILHANTE = "\u001B[94m";
    /** Código ANSI utilizado para apresentar texto a roxo brilhante na consola. */
    public static final String ROXO_BRILHANTE = "\u001B[95m";
    /** Código ANSI utilizado para apresentar texto a ciano brilhante na consola. */
    public static final String CIANO_BRILHANTE = "\u001B[96m";

    //Negrito
    /** Código ANSI utilizado para apresentar texto a negrito na consola. */
    public static final String NEGRITO = "\u001B[1m";

    /*função centralizar texto*/
    /**
     * Imprime um texto centrado na consola.
     *
     * O texto é centrado com base numa largura fixa de 80 caracteres.
     * Caso o texto seja maior do que a largura definida, é impresso
     * normalmente sem centralização.
     *
     * @param texto texto a ser apresentado de forma centralizada na consola
     */
    public static void printCentralizado(String texto) {
        int largura = 80;
        int espacos = (largura - texto.length()) / 2;

        if (espacos > 0) {
            System.out.print(" ".repeat(espacos));
        }
        System.out.println(texto);
    }

    /*Função titulo principal*/
    /**
     * Imprime o título principal do sistema TVDE centralizado na consola.
     *
     * O título é formatado com cores e estilo em negrito, utilizando
     * códigos ANSI, e é centralizado na largura da consola.
     */
    public static void printTituloPrincipal() {
        System.out.println();
        printCentralizado(NEGRITO + AZUL + "========= Sistema de Viagens TVDE ===========" + RESET);
        System.out.println();
    }

    /*Função titulo secundario */
    /**
     * Imprime um título secundário centralizado na consola.
     *
     * O texto é apresentado na cor ciano, utilizando códigos ANSI,
     * e é centralizado com base na largura da consola.
     *
     * @param texto texto do título secundário a ser impresso
     */
    public static void printTituloSecundario(String texto) {
        printCentralizado(CIANO + texto + RESET);
        System.out.println();
    }
    //endregion

    void main() {

        /** Carrega todos os condutores registados no sistema. */
        clientes = empresaTVDE.carregarClientes();
        /** Carrega todos os condutores registados no sistema. */
        condutores = empresaTVDE.carregarCondutores();

        /**
         * switch case das opções do menu principal do sistema TVDE.
         * Se o utilizador inserir uma opção inválida, é apresentada uma mensagem
         * e o menu é exibido novamente. O loop continua até que a opção 0 seja
         * selecionada.
         *
         *   @param ler Scanner utilizado para ler as opções do utilizador
         */
        int opcao;
        Scanner ler = new Scanner(System.in);
        do {
            opcao = menu(ler);
            switch (opcao) {
                /* Opção que leva ao menu de clientes. */
                case 1:
                    Clientes(ler);
                    break;
                /* Opção que leva ao menu de condutores. */
                case 2:
                    Condutores(ler);
                    break;
                /* Opção que leva ao menu de viaturas. */
                case 3:
                    Viaturas(ler);
                    break;
                /* Opção que leva ao menu de reservas. */
                case 4:
                    Reservas(ler);
                    break;
                /* Opção que leva ao menu de viagens. */
                case 5:
                    Viagens(ler);
                    break;
                /* Opção que leva ao menu de informações. */
                case 6:
                    informacoes(ler);
                    break;
                /* Opção para sair do programa. */
                case 0:
                    System.out.print("Obrigado por utilizar a App da TVDE!!");
                    break;
                default:
                    System.out.println("Opção Inválida! Tente novamente!");
                    break;
            }
        } while (opcao != 0);
    }

    /**
     * Apresenta o menu principal do sistema TVDE e lê a opção escolhida pelo utilizador.
     *
     * O utilizador deve inserir um número correspondente à opção desejada.
     * A entrada é lida como String e convertida para inteiro.
     *
     * @param ler Scanner utilizado para ler a opção do utilizador
     * @return número inteiro correspondente à opção escolhida
     * @throws NumberFormatException se a entrada não for um número válido
     */
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
    /**
     * Metodo que verifica se uma matricula é válida.
     * O metodo so acita exatamente 6 caracteres alfanuméricos (A-Z, 0-9).
     * @param matricula matrícula a ser validada
     * @return "true" se a matricula for valida, "false" se for invalida.
     */
    public boolean isMatriculaValida(String matricula) {
        if (matricula == null) return false;
        String regex = "^[A-Z0-9]{6}$";

        return matricula.trim().toUpperCase().matches(regex);
    }

    /**
     * Verifica se a marca de uma viatura é valida.
     * @param marca marca de uma viatura a ser validada
     * @return "true" se a marca for valida, "false" se for invalida.
     */
    public boolean isMarcaValida(String marca) {
        if (marca == null) return false;
        return marca.trim().length() >=2;
    }

    /**
     * Verifica se o modelo de uma viatura é valida.
     * @param modelo modelo de uma viatura a ser validada
     * @return "true" se o modelo for valido, "false" se for invalido.
     */
    public boolean isModeloValido (String modelo) {
        if (modelo == null) return false;

        return !modelo.trim().isEmpty();
    }

    /**
     * Verifica se o ano de fabrico de uma viatura é valida
     * Ometodo so aceita se não for nulo, estiver no intervalo
     * de 2001 a 2026
     * For representado exatamente como 4 dígitos numéricos.
     * @param anoDeFabrico ano de fabrico a ser validado
     * @return "true" se o ano de frabrico for valido, "false" se for invalido.
     */
    public boolean isAnoDeFabricoValido(String anoDeFabrico) {
        if (anoDeFabrico == null) return false;
        String regex = "^(200[1-9]|201[0-9]|202[0-6])$";

        return anoDeFabrico.trim().matches(regex);
    }

    /**
     * Verifica se a cor de uma viatura é válida.
     *
     * O metodo considera válida uma cor se:
     * - Não for nula
     * - Conter apenas letras (maiúsculas ou minúsculas), acentos comuns em português e espaços
     *
     * @param cor cor a ser validada
     * @return "true" se a cor for valida, "false" se for invalida.
     */
    public boolean isCorValida (String cor) {
        if (cor == null) return false;

        return cor.trim().matches("^[a-zA-ZãõáéíóúçÃÕÁÉÍÓÚÇ ]+$");
    }

    /**
     * Verifica se o status de uma viatura é válido.
     *
     * O metodo considera válido um status se:
     * - Não for nulo
     * - For representado pelos valores "1" ou "2" (ignorando maiúsculas e minúsculas)
     *
     * Normalmente, esses valores podem representar, por exemplo:
     * - "1" = Disponível
     * - "2" = Indisponível
     *
     * @param status status a ser validado
     * @return "true" se o status for valido, "false" se for invalido.
     */
    public boolean isStatusValido (String status) {
        if (status == null) return false;
        return status.equalsIgnoreCase("1") || status.equalsIgnoreCase("2");
    }

    /**
     * Verifica se um NIF (Número de Identificação Fiscal) é válido.
     *
     * O metodo considera válido um NIF se:
     * - Não for nulo
     * - Conter exatamente 9 dígitos numéricos
     *
     * @param nif nif a ser validado
     * @return "true" se o nif for valida, "false" se for invalido.
     */
    public boolean isNifValido(String nif) {
        if (nif == null) return false;
        String regex = "^\\d{9}$";

        return nif.trim().matches(regex);
    }

    /**
     * Verifica se o nome de um condutor ou viatura é válido.
     *
     * O metodo considera válido um nome se:
     * - Não for nulo
     * - Conter apenas letras (maiúsculas ou minúsculas), incluindo acentos comuns em português, e espaços
     * - Possuir pelo menos 2 caracteres
     *
     * @param nome o nome a ser validado
     * @return "true" se o nome for valida, "false" se for invalido.
     */
    public boolean isNomeValido(String nome) {
        if (nome== null) return false;

        return nome.trim().matches("^[a-zA-ZãõáéíóúçÃÕÁÉÍÓÚÇ ]{2,}$");
    }

    /**
     * Verifica se o Cartão de Cidadão (CC) é válido.
     *
     * O metodo considera válido um CC se:
     * - Não for nulo
     * - Conter exatamente 8 dígitos numéricos
     *
     * @param cc o Cartão de Cidadão a ser validado
     * @return "true" se o CC for válido, "false" se for inválido
     */
    public boolean isCcValido(String cc) {
        if (cc == null) return false;
        String regex = "^\\d{8}$";

        return cc.trim().matches(regex);
    }

    /**
     * Verifica se a carta de condução é válida.
     *
     * O metodo considera válida uma carta de condução se:
     * - Não for nula
     * - Seguir o formato: uma letra maiúscula, seguida de um hífen e 7 dígitos (ex.: "B-1234567")
     *
     * @param carta a carta de condução a ser validada
     * @return "true" se a carta for válida, "false" se for inválida
     */
    public boolean isCartaValido(String carta) {
        if (carta == null) return false;
        String regex = "^[A-Z]-\\d{7}$";

        return carta.trim().matches(regex);
    }

    /**
     * Verifica se a idade de um condutor é válida.
     *
     * O metodo considera válida uma idade se:
     * - Não for nula
     * - For um número inteiro entre 1 e 129 (inclusive)
     *
     * @param idade a idade a ser validada
     * @return "true" se a idade for válida, "false" se for inválida
     */
    public boolean isIdadeValido(String idade) {
        if (idade == null) return false;
        String regex = "^(?:[1-9][0-9]?|1[0-2][0-9])$";

        return idade.trim().matches(regex);
    }

    /**
     * Verifica se o sexo de um condutor é válido.
     *
     * O metodo considera válido um sexo se:
     * - Não for nulo
     * - For representado pelos valores "1", "2" ou "3" (ignorando maiúsculas/minúsculas)
     *
     * Normalmente, esses valores podem representar, por exemplo:
     * - "1" = Masculino
     * - "2" = Feminino
     * - "3" = Outro
     *
     * @param sexo o sexo a ser validado
     * @return "true" se o sexo for válido, "false" se for inválido
     */
    public boolean isSexoValido(String sexo) {
        if (sexo == null) return false;
        return sexo.equalsIgnoreCase("1") || sexo.equalsIgnoreCase("2") || sexo.equalsIgnoreCase("3");
    }


    /**
     * Verifica se o email de um condutor é válido.
     *
     * O metodo considera válido um email se:
     * - Não for nulo
     * - Seguir o formato padrão de email: local@domínio.extensão
     *   - Parte local pode conter letras, números e caracteres especiais como . _ % + -
     *   - Domínio pode conter letras, números e hífens
     *   - Extensão deve ter pelo menos 2 letras
     *
     * @param email o email a ser validado
     * @return "true" se o email for válido, "false" se for inválido
     */
    public boolean isEmailValido(String email) {
        if (email == null) return false;
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        return email.trim().toLowerCase().matches(regex);
    }

    /**
     * Verifica se o telefone de um condutor é válido.
     *
     * O metodo considera válido um telefone se:
     * - Não for nulo
     * - Contiver exatamente 9 dígitos numéricos
     *
     * @param telefone o número de telefone a ser validado
     * @return "true" se o telefone for válido, "false" se for inválido
     */
    public boolean isTelefoneValido(String telefone) {
        if (telefone == null) return false;
        String regex = "^\\d{9}$";

        return telefone.trim().matches(regex);
    }

    /**
     * Verifica se a morada de um condutor é válida.
     *
     * O metodo considera válida uma morada se:
     * - Não for nula
     * - Seguir o formato padrão de endereço com código postal português:
     *   - Qualquer texto (nome da rua, número, etc.) seguido de espaço
     *   - Código postal no formato "NNNN-NNN" (4 dígitos, hífen, 3 dígitos)
     *
     * @param morada a morada a ser validada
     * @return "true" se a morada for válida, "false" se for inválida
     */
    public boolean isMoradaValido(String morada) {
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

    /**
     * Verifica se o texto fornecido corresponde à opção de sair.
     *
     * O metodo considera válido o texto se:
     * - Não for nulo
     * - For exatamente "sair", ignorando maiúsculas e minúsculas
     *
     * @param texto o texto a ser verificado
     * @return "true" se o texto for "sair", "false" caso contrário
     */
    public boolean opcaoSair(String texto) {
        if (texto == null) return false;
        return texto.equalsIgnoreCase("sair");
    }

    /**
     * Converte um texto para formato capitalizado.
     *
     * O metodo transforma cada palavra do texto de modo que:
     * - A primeira letra fique em maiúscula
     * - As demais letras fiquem em minúscula
     *
     * Palavras são definidas como sequências de caracteres separadas por espaços.
     * Se o texto for nulo ou vazio, ele é retornado inalterado.
     *
     * @param texto o texto a ser capitalizado
     * @return o texto capitalizado, ou o texto original se for nulo ou vazio
     */
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
    /** Metodo utilizado para apresentar o menu do Cliente. */
    void Clientes(Scanner ler) {
        int opcao;
        do {
            opcao = subMenuClientes(ler);
            /**  Opção para registar um cliente. */
            if (opcao == 1) {
                registarCliente(ler);
            /**  Opção para pesquisar por um cliente. */
            } else if (!clientes.isEmpty() && opcao == 2) {
                pesquisarCliente(ler);
            /**  Opção para atualizar dados do cliente. */
            } else if (!clientes.isEmpty() && opcao == 3) {
                atualizarCliente(ler);
            /**  Opção para remover um cliente. */
            } else if (!clientes.isEmpty() && opcao == 4) {
                removerCliente(ler);
            /**  Opção para voltar ao menu anterior. */
            } else if (opcao == 0) {
                break;
            /**  Opção para quando a opção do cliente é invalida. */
            } else {
                System.out.println("Opção Inválida!");
            }
        } while (opcao != 0);
    }

    /** Metodo utilizado para apresentar o submenu cliente.
     * As opções de pesquisa, atualização e remoção só estão disponíveis quando existem reservas registadas.
     *
     * O menu é apresentado repetidamente até o utilizador escolhera opção 0 (voltar/sair).
     * */
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
    }

    /**
     * Regista um novo cliente no sistema.
     * Para cada campo, o metodo:
     * - Valida a entrada utilizando os métodos de validação correspondentes
     * - Garante que valores duplicados (NIF ou CC já registados) não sejam aceitos
     * - Permite ao utilizador digitar "sair" a qualquer momento para cancelar o registo
     * - Informa o utilizador sobre erros de formato e solicita nova entrada
     *
     * Em caso de exceção durante o processo, a mensagem de erro é registada
     * num ficheiro de logs de erros e o metodo imprime "Dados inválidos.".
     *
     * @param ler ler Scanner usado para ler a entrada do utilizador
     */
    private void registarCliente(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Novo Registo de Cliente (Escreva 'sair' para cancelar) ---\n\n" + RESET);

            String ccStr, sexo, email, morada;
            int cc, idade, telefone, nif;

            // Validação e solicitação do NIF
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

            // Validação e solicitação do Cartão de Cidadão
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

            // Validação e solicitação do Nome
            System.out.print("Indique o seu nome: ");
            String nome = ler.nextLine();
            if (opcaoSair(nome)) return;

            // Validação e solicitação da Idade
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

            // Validação e solicitação do Sexo
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

            // Validação e solicitação do Email
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

            // Validação e solicitação Numero de Telefone
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

            // Validação e solicitação da Morada
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


        } catch (Exception e) {
            System.out.println("Dados inválidos.");
            empresaTVDE.adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_ERROS_CLIENTE, e.getMessage());
        }
    }

    /**
     * Permite pesquisar um cliente no sistema pelo NIF de forma interativa via console.
     *
     * O metodo realiza os seguintes passos:
     * - Solicita ao utilizador que indique o NIF do cliente
     * - Permite digitar "sair" para cancelar a pesquisa
     * - Valida o NIF utilizando o metodo isNifValido
     * - Se o NIF for válido, procura o cliente no sistema
     * - Se o cliente for encontrado, imprime os detalhes do cliente
     * - Se não for encontrado, informa que o cliente não existe
     * - Permite ao utilizador pressionar Enter para continuar após visualizar os detalhes
     *
     * @param ler Scanner usado para ler a entrada do utilizador
     */
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
    }

    /**
    * Permite atualizar os dados de um cliente existente de forma interativa via console.
    *
    * O metodo realiza os seguintes passos:
    * - Solicita ao utilizador o NIF do cliente
    * - Permite digitar "sair" a qualquer momento para cancelar a atualização
    * - Valida o NIF e procura o cliente no sistema
    * - Se o cliente existir, apresenta um menu com as opções de atualização
    * - Para cada campo, realiza validação específica (usando os métodos de validação)
    * - Impede duplicação de dados críticos (Cartão de Cidadão)
    * - Salva imediatamente as alterações utilizando empresaTVDE.guardarAlteracoesClientes()
    * - Em caso de erro, registra a exceção em logs e informa o utilizador
    *
    * @param ler Scanner usado para ler a entrada do utilizador
    */
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
                            // Opção para alterar o nome
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
                            // Opção para alterar a idade
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
                            // Opção para alterar o sexo
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
                            // Opção para alterar o email
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
                            // Opção para alterar o numeor de telefone
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
                            // Opção para alterar a morada
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
                            // Opção para alterar o cartão de cidadão
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
                            // Opção para sair
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
    }

    /**
     * Permite remover um cliente do sistema de forma interativa via console.
     *
     * O metodo realiza os seguintes passos:
     * - Solicita ao utilizador o NIF do cliente a ser removido
     * - Permite digitar "sair" a qualquer momento para cancelar a operação
     * - Valida o NIF utilizando o metodo isNifValido
     * - Se o NIF for válido, procura o cliente no sistema
     * - Se o cliente for encontrado, solicita confirmação do utilizador [S/N]
     * - Se confirmado, remove o cliente usando empresaTVDE.removerCliente()
     * - Atualiza a lista local de clientes chamando empresaTVDE.carregarClientes()
     * - Informa o utilizador se a remoção foi bem-sucedida ou se o cliente não pôde ser removido
     * - Em caso de erro de input ou exceção, informa o utilizador
     *
     * @param ler Scanner usado para ler a entrada do utilizador
     */
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
    }
    //endregion

    //region Condutores
    /** Metodo utilizado para apresentar o menu do Condutor*/
    void Condutores(Scanner ler) {
        int opcao;
        do {
            opcao = subMenuCondutores(ler);
            /**  Opção para registar um condutor. */
            if (opcao == 1) {
                registarCondutor(ler);
            /**  Opção para pesquisar por um condutor. */
            } else if (!condutores.isEmpty() && opcao == 2) {
                pesquisarCondutor(ler);
            /**  Opção para atualizar dados do condutor. */
            } else if (!condutores.isEmpty() && opcao == 3) {
                atualizarCondutor(ler);
            /**  Opção para remover um condutor. */
            } else if (!condutores.isEmpty() && opcao == 4) {
                removerCondutor(ler);
            /**  Opção para voltar ao menu anterior. */
            } else if (opcao == 0) {
                break;
            /**  Opção para quando a opção do cliente é invalida. */
            } else {
                System.out.println("Opção Inválida!");
            }
        } while (opcao != 0);
    }

    /** Metodo utilizado para apresentar o submenu condutor.
     * As opções de pesquisa, atualização e remoção só estão disponíveis quando existem reservas registadas.
     *
     * O menu é apresentado repetidamente até o utilizador escolhera opção 0 (voltar/sair).
     * */
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
    }

    /**
     * Regista um novo condutor no sistema.
     * Para cada campo, o metodo:
     * - Valida a entrada utilizando os métodos de validação correspondentes
     * - Garante que valores duplicados (NIF ou CC já registados) não sejam aceitos
     * - Permite ao utilizador digitar "sair" a qualquer momento para cancelar o registo
     * - Informa o utilizador sobre erros de formato e solicita nova entrada
     *
     * Em caso de exceção durante o processo, a mensagem de erro é registada
     * num ficheiro de logs de erros e o metodo imprime "Dados inválidos.".
     *
     * @param ler ler Scanner usado para ler a entrada do utilizador
     */
    void registarCondutor(Scanner ler) {
        try {
            System.out.print(ROXO + "\n\n--- Novo Condutor (Escreva 'sair' para cancelar) ---\n\n" + RESET);

            String ccStr, sexo, email, morada, cartaStr;
            int cc, idade, telefone, nif;
            // Validação e solicitação do NIF
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

            //Validação e solicitação do Cartão de Cidadão
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
            // Validação e Solicitação da Carta de Condução
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

            // Validação e solicitação do Nome
            System.out.println("Indique o seu nome: ");
            String nome = ler.nextLine();
            if (opcaoSair(nome)) return;

            // Validação e solicitação da Idade
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

            // Validação e solicitação do Sex
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

            // Validação e solicitação do Email
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

            // Validação e solicitação da Morada
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

            // Validação e solicitação Numero de Telefone
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
    }

    /**
     * Permite pesquisar um condutor no sistema pelo NIF de forma interativa via console.
     *
     * O metodo realiza os seguintes passos:
     * - Solicita ao utilizador que indique o NIF do condutor
     * - Permite digitar "sair" para cancelar a pesquisa
     * - Valida o NIF utilizando o metodo isNifValido
     * - Se o NIF for válido, procura o condutor no sistema
     * - Se o condutor for encontrado, imprime os detalhes do condutor
     * - Se não for encontrado, informa que o condutor não existe
     * - Permite ao utilizador pressionar Enter para continuar após visualizar os detalhes
     *
     * @param ler Scanner usado para ler a entrada do utilizador
     */
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
    }

    /**
     * Permite atualizar os dados de um condutor existente no sistema de forma interativa via console.
     *
     * O método realiza os seguintes passos:
     * - Solicita ao utilizador o NIF do condutor que deseja atualizar
     * - Permite digitar "sair" a qualquer momento para cancelar a operação
     * - Valida o NIF informado usando o método isNifValido
     * - Se o condutor for encontrado, exibe um menu de opções para atualizar:
     *   1 - Nome
     *   2 - Idade
     *   3 - Género
     *   4 - Email
     *   5 - Telefone
     *   6 - Morada
     *   7 - Cartão de Cidadão
     *   8 - Carta de Condução
     *   0 - Sair
     * - Para cada opção, valida os dados de entrada antes de atualizar o condutor
     * - Salva automaticamente as alterações após cada atualização
     * - Garante que não haja duplicação de Cartão de Cidadão ou Carta de Condução entre condutores
     *
     * @param ler Scanner usado para ler a entrada do utilizador
     */
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
                            // Opção para alterar o nome
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
                        // Opção para alterar a idade
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
                        // Opção para alterar o sexo
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
                        // Opção para alterar o email
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
                        // Opção para alterar o numeor de telefone
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
                        // Opção para alterar a morada
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
                        // Opção para alterar o cartão de cidadão
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
                            // Opção para alterar a carta de condução
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
                        // Opção para sair
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
}

    /**
     * Permite remover um condutor existente do sistema de forma interativa via console.
     *
     * O metodo realiza os seguintes passos:
     * - Solicita ao utilizador o NIF do condutor que deseja remover
     * - Permite digitar "sair" a qualquer momento para cancelar a operação
     * - Valida o NIF informado usando o metodo isNifValido
     * - Se o condutor for encontrado, pede confirmação antes da remoção
     *   (S para confirmar, outro valor cancela a operação)
     * - Remove o condutor do sistema usando empresaTVDE.removerCondutor
     * - Atualiza a lista interna de condutores após remoção
     * - Exibe mensagens de erro caso o NIF não seja encontrado ou a remoção falhe
     *
     * @param ler Scanner usado para ler a entrada do utilizador
     */
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
