//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    ArrayList<Cliente>clientes = new ArrayList<>();
    ArrayList<Condutor> condutores = new ArrayList<>();
    ArrayList<Viatura> viaturas = new ArrayList<>();
    ArrayList<Viagem> viagens = new ArrayList<>();
    ArrayList<Reserva>reservas = new ArrayList<>();
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