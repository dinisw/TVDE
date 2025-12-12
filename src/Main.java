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
        System.out.println("========= Sistema de Viagens TVDE ===========");
        System.out.println("            MENU            ");
        System.out.println("1\tCadastrar Condutor");
        System.out.print("Digite a opção que deseja realizar: ");

        int opcao = Integer.parseInt(ler.nextLine());

        switch (opcao){
            case 1:
                String nome, morada, cartaoCidadao, cartaDeConducao;
                int segurancaSocial, finacas, telemovel;

                System.out.print("Digite o nome do condutor: ");
                nome = ler.nextLine();
                System.out.print("Digite o número da Carta de Condução: ");
                cartaDeConducao = ler.nextLine();
                System.out.print("Digite o número do Cartão de Cidadão: ");
                cartaoCidadao = ler.nextLine();
                System.out.print("Digite o número da Segurança Social: ");
                segurancaSocial = Integer.parseInt(ler.nextLine());
                System.out.print("Digite o número das Finanças: ");
                finacas = Integer.parseInt(ler.nextLine());
                System.out.print("Digite a morada: ");
                morada = ler.nextLine();
                System.out.print("Digite o número do telemóvel(apenas números): ");
                telemovel = Integer.parseInt(ler.nextLine());

                Condutor condutor = new Condutor(cartaoCidadao, nome, segurancaSocial, finacas, morada, telemovel, cartaDeConducao);

                ArrayList<Condutor> condutores = new ArrayList<>();
                condutores.add(condutor);
                
                System.out.println(condutor.toString());
                break;

        }
        break;
    }
}
