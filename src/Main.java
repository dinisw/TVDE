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

    ArrayList<Cliente>clientes = new ArrayList<>();
    ArrayList<Condutor> condutores = new ArrayList<>();
    ArrayList<Viatura> viaturas = new ArrayList<>();
    ArrayList<Viagem> viagens = new ArrayList<>();
    ArrayList<Reserva>reservas = new ArrayList<>();
    //Antes de qualuqer coisa temos que carregar os itens da memória pra ca e guardar em um arraylist e depois irmos consultando

    void main() {
        int opcao;
        Scanner ler = new Scanner(System.in);
        do {
            opcao = menu(ler);
            switch (opcao) {
                case 1:
                    cliente(ler);
                    break;
                case 2:
                    condutor(ler);
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
    int menu(Scanner ler) {
        System.out.println("========= Sistema de Viagens TVDE ===========");
        System.out.println("            MENU            ");
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
        System.out.println("Indique o seu nome:");
        String nome = ler.nextLine();
        System.out.println("Indique a sua idade:");
        int idade = Integer.parseInt(ler.nextLine());
        System.out.println("Indique o seu genero:");
        String sexo = ler.nextLine();
        System.out.println("Indique o seu email:");
        String email = ler.nextLine();
        System.out.println("Indique o seu número de telemóvel:");
        int telefone = Integer.parseInt(ler.nextLine());
        System.out.println("Indique a sua morada:");
        String morada = ler.nextLine();
        System.out.println("Indique o seu número de cartão de cidadão (sem os últimos 4 dígitos):");
        int cartaoDeCidadao = Integer.parseInt(ler.nextLine());
        System.out.println("Indique o seu número de contribuinte.");
        boolean existe = false;
        int contribuinte = Integer.parseInt(ler.nextLine());
        for (Cliente cliente : clientes) {
            if (cliente.getContribuinte() == contribuinte) {
                existe = true;
                break;
            }
        }
        if (existe) {
            System.out.println("Já existe um cliente cadastrado com sucesso!");
        } else {
            Cliente cliente = new Cliente(nome, idade, sexo, email, telefone, morada, cartaoDeCidadao, contribuinte);
            cliente.add(clientes);
            System.out.println("Cliente cadastrado com sucesso!");
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
                        return;
                    }else {
                        System.out.println("Formato incorreto, tente novamente com o formato [XX-XX-XX]");
                    }
                }
                pesquisarViaturaPelaMatricula(ler, matricula);
            }
            case 2:
                    break;
                case 3:
                    verListadeClientes(ler);
                    break;
                case 4:
                    matricula = "";
                    removerViatura(ler, matricula);
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

                if (existe) {
                    System.out.println("Erro: Essa matrícula já existe no sistema.");
                } else {
                    break;
                }
            }

            Viatura novaViatura = new Viatura(Integer.parseInt(anoDeFabrico), modelo, marca, matricula);
            viaturas.add(novaViatura);
            System.out.println("Viatura registada com sucesso!");

        } catch (Exception e) {
             //Guardar em arquivo como log e.getMessage()
        }
    }

    public boolean isMatriculaValida(String matricula) {
        if (matricula == null) return false;
        String regex = "^[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}$";

        return matricula.trim().toUpperCase().matches(regex);
    }

    private void verListaDeClientes(Scanner ler, String matricula) {
    }
    //Feito na classe Empresa.
    void removerViatura(Scanner ler) {
    }
    //Feito na classe Empresa.
    void pesquisarViaturaPelaMatricula(Scanner ler, String matricula) {
        for (var viatura : viaturas){
            viatura.toString();
            System.out.println("Aperte qualquer tecla para continuar ...");
        }
    }
    //Feito na classe Empresa.
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
    }

    private void consultarReservas(Scanner ler) {
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
        /*Ler dados que estão guardados*/
        Viagem viagem = new Viagem(cliente, condutor, viatura, dataViagem, hora, moradaDestino, moradaOrigem, custoViagem, distancia, concluida);
        viagem.add(viagens);
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

    void removerViagem(Scanner ler) {);
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

    void Viaturas(Scanner ler) {
        /*
         * Criar um Submenu para as opções
         * */
        String matricula = ""; /*pegar a matricula*/

        registarViatura(ler);
        pesquisarViaturaPelaMatricula(ler);
        verListaDeClientes(ler, matricula);
        removerViatura(ler);

    }

    private void verListaDeClientes(Scanner ler, String matricula) {
    }
    //Feito na classe Empresa.
    void removerViatura(Scanner ler) {
    }
    //Feito na classe Empresa.
    void pesquisarViaturaPelaMatricula(Scanner ler) {
    }
    //Feito na classe Empresa.
    void registarViatura(Scanner ler) {
        ArrayList<Viatura> viaturas = new ArrayList<>();
        System.out.println("Indique a marca da viatura.");
        String marca = ler.nextLine();
        System.out.println("Indique a modelo da viatura.");
        String modelo = ler.nextLine();
        System.out.println("Indique a cor da viatura.");
        String cor = ler.nextLine();
        System.out.println("Indique a matrícula.");
        System.out.println("Indique o ano de fabrico da viatura.");
        int anoDeFabrico = Integer.parseInt(ler.nextLine());
        do {
            System.out.println("Indique a matrícula:");
            String matricula = ler.nextLine();
            boolean disponivel = false;
            for (Viatura viatura : viaturas) {
                if (viatura.getMatricula().equals(matricula)) {
                    disponivel = true;
                    break;
                }
            }
            if (disponivel) {
                System.out.println("Já existe uma viatura com a mesma matricula.");
            } else {
                Viatura viatura = new Viatura(matricula, marca, modelo, anoDeFabrico, cor, disponivel);
                viatura.add(viaturas);
            }
        } while (true);
    }
    //Feito na classe Empresa.
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
        /*Ler dados que estão guardados*/
        if (reservas.isEmpty()){
            System.out.println("Não existe nenhuma reserva para transformar em viagem.");
            return;
        }
        System.out.println("=== Escolha a Reserva  a Transformar ===");
        for (int i = 0; i < reservas.size(); i++) {
            System.out.println((i + 1) + ". " + reservas.get(i).toString());
        }
        System.out.println("Introduza o número da reserva: ");
        int numeroreserva = 

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

    void removerViagem(Scanner ler) {);
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