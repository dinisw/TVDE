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
    while (true){
        Scanner ler = new Scanner(System.in);
        int opcao;
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
        opcao = ler.nextInt();
        switch (opcao){
            case 1:
                String nomeCliente, morada;
                int telemovel, cartaoCidadao, idContribuinte;
                System.out.print("Indique o nome do cliente: ");
                nomeCliente = ler.nextLine();
                Cliente cliente = new Cliente();
                cliente.setNomeCliente(nomeCliente);
                ArrayList<Cliente> clientes = new ArrayList<>();
                System.out.print("Digite o número do Cartão de Cidadão (sem os últimos 4 dígitos): ");
                cartaoCidadao = ler.nextInt();
                cliente.setCartaoCidadao(cartaoCidadao);
                ArrayList<Cliente> clientes1 = new ArrayList<>();
                System.out.println("Indique o número de contribuinte:");
                idContribuinte = ler.nextInt();
                cliente.setIdContribuinte(idContribuinte);
                ArrayList<Cliente> clientes2 = new ArrayList<>();
                System.out.print("Digite a morada: ");
                morada = ler.nextLine();
                cliente.setMorada(morada);
                ArrayList<Cliente> clientes3 = new ArrayList<>();
                System.out.print("Digite o número do telemóvel(sem o indicativo do país): ");
                telemovel = ler.nextInt();
                cliente.setTelemovel(telemovel);
                ArrayList<Cliente> clientes4 = new ArrayList<>();
                break;
            case 2:
                String nomeCondutor, moradaCondutor, cartaDeConducao;
                int cartaoDeCidadao, Contribuinte, telemovelCondutor;
                double avaliacao;
                Condutor condutor = new Condutor();
                System.out.println("Indique o nome do/a condutor/a");
                nomeCondutor = ler.nextLine();
                condutor.setNomeCondutor(nomeCondutor);
                ArrayList<Condutor> condutores = new ArrayList<>();
                System.out.println("Indique o numero da carta de condução:");
                cartaDeConducao = ler.nextLine();
                condutor.setCartaDeConducao(cartaDeConducao);
                ArrayList<Condutor> condutores1 = new ArrayList<>();
                System.out.println("Indique o numero de cartão de cidadão sem os últimos 4 dígitos:");
                cartaoDeCidadao = ler.nextInt();
                condutor.setCartaoCidadao(cartaoDeCidadao);
                ArrayList<Condutor> condutores2 = new ArrayList<>();
                System.out.println("Indique o seu número de contribuinte:");
                Contribuinte = ler.nextInt();
                condutor.setIdContribuinte(Contribuinte);
                ArrayList<Condutor> condutores3 = new ArrayList<>();
                System.out.println("Indique a sua morada:");
                moradaCondutor = ler.nextLine();
                condutor.setMorada(moradaCondutor);
                ArrayList<Condutor> condutores4 = new ArrayList<>();
                System.out.println("Indique o seu número de telemóvel:");
                telemovelCondutor = ler.nextInt();
                condutor.setTelemovel(telemovelCondutor);
                ArrayList<Condutor> condutores5 = new ArrayList<>();
                break;
                case 3:

                    break;
        }
        break;
    }
}
