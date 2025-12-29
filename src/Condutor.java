//Condutores (ex: nome, n.º de identificação, carta de condução, n.º de segurança social, n.º de
//identificação fiscal, telemóvel, morada, …);

/**
 * Representa um condutor TVDE.
 */
public class Condutor{
    /** O número da carta de condução do condutor*/
    private String cartaDeConducao;
    /** A avaliação da/o condutor/a.*/
    private double avaliacao;
    /** Atribuição do nome completo da/o condutor/a. */
    private String nomeCondutor;
    /** Atribuição do cartão de cidadão do/a condutor/a. */
    private int cartaoCidadao;
    /** Número de Contribuinte do/a condutor/a. */
    private int idContribuinte;
    /** Morada de residência do/a condutor/a. */
    private String morada;
    /** Número de telemóvel do/a condutor/a. */
    private int telemovel;
    /**
     * Construtor vazio.
     * Cria um condutor sem os dados preenchidos.
     */
    public Condutor() {
        cartaDeConducao = "";
        avaliacao = 0;
        nomeCondutor = "";
        cartaoCidadao = 0;
        idContribuinte = 0;
        morada = "";
        telemovel = 0;
    }

    /**
     * Construtor preenchido.
     * Cria um construtor que cria todos os dados pessoais e profissionais.
     * @param cartaoCidadao O cartão de cidadão.
     * @param nomeCondutor O nome completo.
     * @param idContribuinte O número de Contribuinte.
     * @param morada A morada de residência.
     * @param telemovel O número de telefone
     * @param cartaDeConducao O número da carta de condução.
     * @param avaliacao A nota inicial do condutor.
     */
    public Condutor(String cartaDeConducao, double avaliacao, String nomeCondutor, int cartaoCidadao, int idContribuinte, String morada, int telemovel) {
        this.cartaDeConducao = cartaDeConducao;
        this.avaliacao = avaliacao;
        this.nomeCondutor = nomeCondutor;
        this.cartaoCidadao = cartaoCidadao;
        this.idContribuinte = idContribuinte;
        this.morada = morada;
        this.telemovel = telemovel;
    }

    /**
     * Define o nome do/a condutor/a.
     * @return nomeCondutor O nome do/a condutor/a.
     */
    public String getNomeCondutor() {
        return nomeCondutor;
    }

    /**
     * Define o nome do/a condutor/a
     * @param nomeCondutor O novo nome completo.
     */
    public void setNomeCondutor(String nomeCondutor) {
        this.nomeCondutor = nomeCondutor;
    }
    /**
     * Devolve o número do cartão de cidadão.
     * @return número do cartão de cidadão
     */
    public int getCartaoCidadao() {
        return cartaoCidadao;
    }
    /**
     * Define o número do cartão de cidadão.
     * @param cartaoCidadao O novo cartão de cidadão.
     */
    public void setCartaoCidadao(int cartaoCidadao) {
        this.cartaoCidadao = cartaoCidadao;
    }
    /**
     * Devolve o número de contribuinte.
     * @return O número de contribuinte.
     */
    public int getIdContribuinte() {
        return idContribuinte;
    }
    /**
     * Define o número de identificação fiscal.
     * @param idContribuinte O novo número de identificação fiscal / Contribuinte.
     */
    public void setIdContribuinte(int idContribuinte) {
        this.idContribuinte = idContribuinte;
    }
    /**
     * Devolve a morada do/a condutor/a.
     * @return A morada do/a condutor/a.
     */
    public String getMorada() {
        return morada;
    }
    /**
     * Devolve a morada do/a condutor/a.
     * @param morada A nova morada do/a condutor/a.
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }
    /**
     * Devolve o número de telemóvel do/a condutor/a.
     * @return número de telemóvel do/a condutor/a.
     */
    public int getTelemovel() {
        return telemovel;
    }
    /**
     * Define o número de telemóvel do/a condutor/a.
     * @param telemovel O novo número de telefone.
     */
    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
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
}
