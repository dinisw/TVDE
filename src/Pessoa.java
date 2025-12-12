/**
 * Declaração da classe que representa uma pessoa no sistema.
 */
public class Pessoa {
    /** Atribuição do cartão de cidadão da pessoa.*/
    private String cc;
    /** Atribuição do nome completo da pessoa.*/
    private String nome;
    /** Atribuição do número de identificação da segurança social.*/
    private int idSegSocial;
    /** Número de identificação fiscal / Contribuinte. */
    private int idFinancas;
    /** Morada de residência da pessoa. */
    private String morada;
    /** Número de telemóvel da pessoa. */
    private int telemovel;

    /**
     * Construtor vazio.
     * Inicia todos os atributos com valores por defeito.
     */
    public Pessoa() {
        cc = "";
        nome = "";
        idSegSocial = 0;
        idFinancas = 0;
        morada = "";
        telemovel = 0;
    }

    /**
     * Construtor com parâmetros.
     * Permite criar uma pessoa com todos os seus dados definidos.
     * @param cc O cartão de cidadão.
     * @param nome O nome completo.
     * @param idSegSocial O número da segurança social.
     * @param idFinancas O número de identificação fiscal / Contribuinte.
     * @param morada A morada de residência.
     * @param telemovel O número de telefone.
     */
    public Pessoa(String cc, String nome, int idSegSocial, int idFinancas, String morada, int telemovel) {
        this.cc = cc;
        this.nome = nome;
        this.idSegSocial = idSegSocial;
        this.idFinancas = idFinancas;
        this.morada = morada;
        this.telemovel = telemovel;
    }

    /**
     * Devolve o número do cartão de cidadão.
     * @return número do cartão de cidadão
     */
    public String getCc() {
        return cc;
    }

    /**
     * Define o número do cartão de cidadão.
     * @param cc O novo cartão de cidadão.
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * Devolve o nome da pessoa.
     * @return O nome da pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa
     * @param nome O novo nome completo.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Devolve o número de identificação da Segurança Social.
     * @return O número da Segurança Social
     */
    public int getIdSegSocial() {
        return idSegSocial;
    }

    /**
     * Define o número de identificação da Segurança Social.
     * @param idSegSocial O número da segurança social.
     */
    public void setIdSegSocial(int idSegSocial) {
        this.idSegSocial = idSegSocial;
    }

    /**
     * Devolve o número de identificação fiscal.
     * @return O número de identificação fiscal
     */
    public int getIdFinancas() {
        return idFinancas;
    }

    /**
     * Define o número de identificação fiscal.
     * @param idFinancas O novo número de identificação fiscal / Contribuinte.
     */
    public void setIdFinancas(int idFinancas) {
        this.idFinancas = idFinancas;
    }

    /**
     * Devolve a morada da pessoa.
     * @return A morada da pessoa.
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Define a morada da pessoa.
     * @param morada A nova morada de residência.
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Devolve o número de telemóvel da pessoa.
     * @return número de telemóvel da pessoa.
     */
    public int getTelemovel() {
        return telemovel;
    }

    /**
     * Define o número de telemóvel da pessoa.
     * @param telemovel O novo número de telefone.
     */
    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }
}
