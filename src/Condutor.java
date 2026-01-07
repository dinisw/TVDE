//Condutores (ex: nome, n.º de identificação, carta de condução, n.º de segurança social, n.º de
//identificação fiscal, telemóvel, morada, …);

import java.util.ArrayList;

/**
 * Representa um condutor TVDE.
 */
public class Condutor extends Pessoa{
    /** O número da carta de condução do condutor*/
    private String cartaDeConducao;
    /** A avaliação da/o condutor/a.*/
    private double avaliacao;
    /** Número total de viagens realizadas pelo condutor. */
    private int totalViagens;
    /**
     * Construtor da classe Condutor.
     *
     * @param nome Nome do condutor
     * @param idade Idade do condutor
     * @param sexo Sexo do condutor
     * @param email Email do condutor
     * @param telefone Número de telefone
     * @param morada Morada do condutor
     * @param cartaoDeCidadao Número do cartão de cidadão
     * @param contribuinte Número de contribuinte
     */
    public Condutor(String nome, int idade, String sexo, String email, int telefone, String morada, int cartaoDeCidadao, int contribuinte) {
        super(nome, idade, sexo, email, telefone, morada, cartaoDeCidadao, contribuinte);
        this.cartaDeConducao = "";
        this.avaliacao = 0;
        this.totalViagens = 0;
    }
    /**
     * Obtém o número da carta de condução.
     * @return O código da carta.
     */
    public String getCartaDeConducao() {
        return cartaDeConducao;
    }

    /**
     * Define ou altera o número da carta de condução.
     * @param cartaDeConducao O novo número da carta de condução.
     */
    public void setCartaDeConducao(String cartaDeConducao) {
        this.cartaDeConducao = cartaDeConducao;
    }

    /**
     * Obtém a nota de avaliação do/a condutor/a.
     * @return Um valor numérico.
     */
    public double getAvaliacao() {
        return avaliacao;
    }

    /**
     * Define uma nota de avaliação do/a condutor/a.
     * @param avaliacao A nova avaliação por atribuir.
     */
    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    /**
     * Obtém o número total de viagens realizadas.
     *
     * @return Total de viagens
     */
    public int getTotalViagens() {
        return totalViagens;
    }

    /**
     * Define o número total de viagens realizadas.
     *
     * @param totalViagens Número de viagens
     */
    public void setTotalViagens(int totalViagens) {
        this.totalViagens = totalViagens;
    }

    /**
     * Incrementa o número total de viagens do condutor em uma unidade.
     */
    public void addViagem(){
        this.totalViagens ++;
    }

    /**
     * Método destinado a adicionar este condutor a uma lista de condutores.
     *
     * @param condutores Lista de condutores
     */
    public void add(ArrayList<Condutor> condutores){
    }

    /**
     * Devolve uma representação textual do condutor.
     *
     * @return String com os dados do condutor
     */
    @Override
    public String toString() {
        return "Condutor{" +
                "Carta de condução='" + cartaDeConducao + '\'' +
                ", avaliação=" + avaliacao + '\'' +
                ", totalViagens=" + totalViagens + '\'' +
                '}';
    }
}
