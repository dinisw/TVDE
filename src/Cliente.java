import java.util.ArrayList;

/**
 * Representa um cliente do serviço TVDE.
 *
 * Um cliente é uma pessoa registada no sistema que pode realizar viagens,
 * mantendo o registo do número total de viagens efetuadas e do valor total gasto.
 */
public class Cliente extends Pessoa {
    /** Número total de viagens realizadas pelo cliente. */
    private int totalViagens;
    /** Valor total gasto pelo cliente em viagens. */
    private double totalGasto;

    /**
     * Construtor da classe Cliente.
     *
     * @param nome Nome do cliente
     * @param idade Idade do cliente
     * @param sexo Sexo do cliente
     * @param email Email do cliente
     * @param telefone Número de telefone
     * @param morada Morada do cliente
     * @param cartaoDeCidadao Número do cartão de cidadão
     * @param contribuinte Número de contribuinte
     */
    public Cliente(String nome, int idade, String sexo, String email, int telefone, String morada, int cartaoDeCidadao, int contribuinte) {
        super(nome, idade, sexo, email, telefone, morada, cartaoDeCidadao, contribuinte);
        this.totalViagens = 0;
        this.totalGasto = 0;
    }

    /**
     * Obtém o número total de viagens realizadas pelo cliente.
     *
     * @return Total de viagens
     */
    public int getTotalViagens() {
        return totalViagens;
    }

    /**
     * Define o número total de viagens realizadas pelo cliente.
     *
     * @param totalViagens Número de viagens
     */
    public void setTotalViagens(int totalViagens) {
        this.totalViagens = totalViagens;
    }

    /**
     * Obtém o valor total gasto pelo cliente.
     *
     * @return Valor total gasto
     */
    public double getTotalGasto() {
        return totalGasto;
    }

    /**
     * Define o valor total gasto pelo cliente.
     *
     * @param totalGasto Valor total gasto
     */
    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }

    /**
     * Regista uma nova viagem realizada pelo cliente.
     *
     * Incrementa o número total de viagens e adiciona o valor da viagem
     * ao total gasto.
     *
     * @param valor Custo da viagem
     */
    public void addViagem(double valor){
        this.totalViagens++;
        this.totalGasto += valor;
    }

    /**
     * Método destinado a adicionar este cliente a uma lista de clientes.
     *
     * @param clientes Lista de clientes
     */
    public void add(ArrayList<Cliente> clientes){}

    /**
     * Devolve uma representação textual do cliente.
     *
     * @return String com os dados do cliente
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "totalViagens=" + totalViagens +
                ", totalGasto=" + totalGasto +
                '}';
    }
}