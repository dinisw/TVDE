//Condutores (ex: nome, n.º de identificação, carta de condução, n.º de segurança social, n.º de
//identificação fiscal, telemóvel, morada, …);

/**
 * Representa um condutor TVDE.
 * Classe herdada de {@link Pessoa}, o que significa que o Condutor
 * Tem o nome, morada, etc.
 * Além disso, têm carta de condução e uma avaliação de desempenho.
 */
public class Condutor extends Pessoa{
    /** O número da carta de condução do condutor*/
    private String cartaDeConducao;

    /** A avaliação do condutor*/
    private double avaliacao;

    /**
     * Construtor vazio.
     * Cria um condutor sem os dados preenchidos.
     * Inicia uma carta vazia e a avaliação a zero.
     */
    public Condutor() {
        cartaDeConducao = "";
        avaliacao = 0;
    }

    /**
     * Construtor preenchido.
     * Cria um construtor que cria todos os dados pessoais e profissionais.
     * @param cc O cartão de cidadão.
     * @param nome O nome completo.
     * @param idSegSocial O número da segurança social.
     * @param idFinancas O número de identificação fiscal / Contribuinte.
     * @param morada A morada de residência.
     * @param telemovel O número de telefone
     * @param cartaDeConducao O número da carta de condução.
     * @param avaliacao A nota inicial do condutor.
     */
    public Condutor(String cc, String nome, int idSegSocial, int idFinancas, String morada, int telemovel, String cartaDeConducao, double avaliacao) {
        super(cc, nome, idSegSocial, idFinancas, morada, telemovel);
        this.cartaDeConducao = cartaDeConducao;
        this.avaliacao = avaliacao;
    }

    /**
     * Construtor parcial.
     * A Avaliação começa automaticamente em 0.
     * @param cc O cartão de cidadão.
     * @param nome O nome completo.
     * @param idSegSocial O número da segurança social.
     * @param idFinancas O número de identificação fiscal / Contribuinte.
     * @param morada A morada de residência.
     * @param telemovel O número de telefone
     * @param cartaDeConducao O número da carta de condução.
     */
    public Condutor(String cc, String nome, int idSegSocial, int idFinancas, String morada, int telemovel, String cartaDeConducao) {
        super(cc, nome, idSegSocial, idFinancas, morada, telemovel);
        this.cartaDeConducao = cartaDeConducao;
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
     * Obtém a nota de avaliação do condutor.
     * @return Um valor numérico.
     */
    public double getAvaliacao() {
        return avaliacao;
    }

    /**
     * Define uma nota de avaliação do condutor.
     * @param avaliacao A nova avaliação por atribuir.
     */
    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    /**
     * Devolve o nome do condutor.
     * @return O nome herdado da class Pessoa.
     */
    public String toString(){
        return getNome();
    }
}
