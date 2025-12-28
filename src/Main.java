//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/**
 * Classe principal da aplicação TVDE.
 * Apresenta um menu de opções do sistema de viagens TVDE
 * e permite ao utilizador realizar operações através da
 * leitura de dados introduzidos pelo teclado.
 *
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
                String nomeCliente, morada, cartaoCidadao;
                int telemovel, idFinancas;
                System.out.print("Indique o nome do cliente: ");
                nomeCliente = ler.nextLine();
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(nomeCliente);
                ArrayList<Pessoa> pessoas = new ArrayList<>();
                System.out.print("Digite o número do Cartão de Cidadão (sem os últimos 4 dígitos): ");
                cartaoCidadao = ler.nextLine();
                pessoa.setCartaoCidadao(cartaoCidadao);
                ArrayList<Pessoa> pessoas1 = new ArrayList<>();
                System.out.println("Indique o número de contribuinte:");
                idFinancas = ler.nextInt();
                pessoa.setIdFinancas(idFinancas);
                ArrayList<Pessoa> pessoas2 = new ArrayList<>();
                System.out.print("Digite a morada: ");
                morada = ler.nextLine();
                pessoa.setMorada(morada);
                ArrayList<Pessoa> pessoas3 = new ArrayList<>();
                System.out.print("Digite o número do telemóvel(sem o indicativo do país): ");
                telemovel = ler.nextInt();
                pessoa.setTelemovel(telemovel);
                ArrayList<Pessoa> pessoas4 = new ArrayList<>();
                break;
            case 2:

                break;

        }
        break;
    }
}
