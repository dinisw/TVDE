//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

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
void main() {
    int opcao;
    Scanner ler = new Scanner(System.in);
    do {
        opcao = menu(ler);
        switch (opcao) {
            case 1:
                opcao1(ler);
                break;
            case 2:
                opcao2(ler);
                break;
            case 3:
                opcao3(ler);
                break;
            case 4:
                opcao4(ler);
                break;
            case 5:
                opcao5(ler);
                break;
            case 6:
                opcao6(ler);
                break;
            default:
                System.out.println("Opção Inválida! Tente novamente!");
                break;
        }
        break;
    } while (opcao < 0 || opcao > 6);
}
int menu(Scanner ler){
    System.out.println("========= Sistema de Viagens TVDE ===========");
    System.out.println("            MENU            ");
    System.out.println("1. Registar o/a Cliente");
    System.out.println("2. Registar o/a Condutor");
    System.out.println("3. Registar a Viatura");
    System.out.println("4. Criar Reserva");
    System.out.println("5. Registar Viagem");
    System.out.println("6. Informações");
    System.out.println("0. Sair");
    System.out.print("Indique a opção que queira realizar utilizando os números de 0 a 6.");
    int opcao = Integer.parseInt(ler.nextLine());
    return opcao;
}

void opcao1(Scanner ler){
    String nomeCliente, morada;
    int telemovel, cartaoCidadao, idContribuinte;
    Cliente cliente = new Cliente();
    ArrayList<Cliente> clientes = new ArrayList<>();
    System.out.print("Indique o nome do cliente: ");
    nomeCliente = ler.nextLine();
    cliente.setNomeCliente(nomeCliente);
    System.out.print("Digite o número do Cartão de Cidadão (sem os últimos 4 dígitos): ");
    cartaoCidadao = Integer.parseInt(ler.nextLine());
    cliente.setCartaoCidadao(cartaoCidadao);
    System.out.println("Indique o número de contribuinte:");
    idContribuinte = Integer.parseInt(ler.nextLine());
    cliente.setIdContribuinte(idContribuinte);
    System.out.print("Digite a morada: ");
    morada = ler.nextLine();
    cliente.setMorada(morada);
    System.out.print("Digite o número do telemóvel(sem o indicativo do país): ");
    telemovel = Integer.parseInt(ler.nextLine());
    cliente.setTelemovel(telemovel);
    cliente.add(clientes);
}
void opcao2(Scanner ler){
    String nomeCondutor, moradaCondutor, cartaDeConducao;
    int cartaoDeCidadao, Contribuinte, telemovelCondutor;
    double avaliacao;
    Condutor condutor = new Condutor();
    ArrayList<Condutor> condutores = new ArrayList<>();
    System.out.println("Indique o nome do/a condutor/a");
    nomeCondutor = ler.nextLine();
    condutor.setNomeCondutor(nomeCondutor);
    System.out.println("Indique o numero da carta de condução:");
    cartaDeConducao = ler.nextLine();
    condutor.setCartaDeConducao(cartaDeConducao);
    System.out.println("Indique o numero de cartão de cidadão sem os últimos 4 dígitos:");
    cartaoDeCidadao = Integer.parseInt(ler.nextLine());
    condutor.setCartaoCidadao(cartaoDeCidadao);
    System.out.println("Indique o seu número de contribuinte:");
    Contribuinte = Integer.parseInt(ler.nextLine());
    condutor.setIdContribuinte(Contribuinte);
    System.out.println("Indique a sua morada:");
    moradaCondutor = ler.nextLine();
    condutor.setMorada(moradaCondutor);
    System.out.println("Indique o seu número de telemóvel:");
    telemovelCondutor = Integer.parseInt(ler.nextLine());
    condutor.setTelemovel(telemovelCondutor);
    condutor.add(condutores);
}
void opcao3(Scanner ler){
    String matricula, marca, modelo, cor;
    int anoDeFabrico, nPortas;
    Viatura viatura = new Viatura();
    ArrayList<Viatura> viaturas = new ArrayList<>();
    System.out.println("Indique a marca da viatura.");
    marca = ler.nextLine();
    viatura.setMarca(marca);
    System.out.println("Indique a modelo da viatura.");
    modelo = ler.nextLine();
    viatura.setModelo(modelo);
    System.out.println("Indique a cor da viatura.");
    cor = ler.nextLine();
    viatura.setCor(cor);
    System.out.println("Indique a matrícula.");
    matricula = ler.nextLine();
    viatura.setMatricula(matricula);
    System.out.println("Indique o número de portas da viatura.");
    nPortas = Integer.parseInt(ler.nextLine());
    viatura.setNPortas(nPortas);
    System.out.println("Indique o ano de fabrico da viatura.");
    anoDeFabrico = Integer.parseInt(ler.nextLine());
    viatura.setAnoDeFabrico(anoDeFabrico);
    viatura.add(viaturas);
}
void opcao4(Scanner ler){
    LocalDate data;
    LocalTime hora;
    String moradaOrigem, moradaDestino;
    double kms;
    Reserva reserva = new Reserva();
    ArrayList<Reserva> reservas = new ArrayList<>();
    System.out.println("Indique a data que pretenda reservar (em formato de dd/MM/yyyy):");
    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    data = LocalDate.parse(ler.nextLine(), formatoData);
    reserva.setData(data);
    System.out.println("Indique a hora que pretenda reservar (em formato de HH:mm):");
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
    hora = LocalTime.parse(ler.nextLine(), formatoHora);
    reserva.setHora(hora);
    System.out.println("Indique a sua atual morada:");
    moradaOrigem = ler.nextLine();
    reserva.setMoradaOrigem(moradaOrigem);
    System.out.println("Indique o destino:");
    moradaDestino = ler.nextLine();
    reserva.setMoradaDestino(moradaDestino);
    System.out.println("Indique a distância:");
    kms = ler.nextDouble();
    reserva.setKms(kms);
    reserva.add(reservas);
}
void  opcao5(Scanner ler){
    LocalTime horaInicial, horaFinal;
    LocalDate dataViagem;
    String moradaDeOrigem, moradaDeDestino;
    double kMS, custoViagem;
    Viagem viagem = new Viagem();
    ArrayList<Viagem> viagens = new ArrayList<>();
    System.out.println("Indique a hora de inicio:");
    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    horaInicial = LocalTime.parse(ler.nextLine(), formatter);
    viagem.setHoraInicial(horaInicial);
    System.out.println("Indique a hora final:");
    horaFinal = LocalTime.parse(ler.nextLine(), formatter);
    viagem.setHoraFinal(horaFinal);
    System.out.println("Indique a data");
    dataViagem = LocalDate.parse(ler.nextLine(), formatterData);
    viagem.setDataViagem(dataViagem);
    System.out.println("Indique a morada de origem:");
    moradaDeOrigem = ler.nextLine();
    viagem.setMoradaDeOrigem(moradaDeOrigem);
    System.out.println("Indique a morada de destino:");
    moradaDeDestino = ler.nextLine();
    viagem.setMoradaDeDestino(moradaDeDestino);
    System.out.println("Indique a custo da viagem:");
    custoViagem = ler.nextDouble();
    viagem.setCustoViagem(custoViagem);
    System.out.println("Indique a distancia percorrida:");
    kMS = ler.nextDouble();
    viagem.setKMS(kMS);
    viagem.add(viagens);
}
void opcao6(Scanner ler){
}