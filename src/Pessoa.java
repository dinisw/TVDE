/**
 * Declaração da classe que representa uma pessoa no sistema.
 */
public class Pessoa {
    /** Atributo do cartão de cidadão da pessoa.*/
    private String cc;
    /** Atributo do nome completo da pessoa.*/
    private String nome;
    /** Atributo do numero de identificação da segurança social.*/
    private int idSegSocial;
    /** Número de identificação fiscal (Finanças) */
    private int idFinancas;
    /** Morada da pessoa */
    private String morada;
    /** Número de telemóvel da pessoa */
    private int telemovel;

    /**
     * Construtor padrão da classe pessoa.
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
     * Construtor com parâmetros da classe Pessoa.
     * Permite criar uma pessoa com todos os seus dados definidos.
     * @param cc
     * @param nome
     * @param idSegSocial
     * @param idFinancas
     * @param morada
     * @param telemovel
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
     * devolve o numero do cartão de cidadão.
     * @return numero do cartão de cidadão
     */
    public String getCc() {
        return cc;
    }

    /**
     * define o numero do cartão de cidadão.
     * @param cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * devolve o nome da pessoa.
     * @return nome da pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * define o nome da pessoa
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * devolve o número de identificação da Segurança Social.
     * @return número da Segurança Social
     */
    public int getIdSegSocial() {
        return idSegSocial;
    }

    /**
     * define o número de identificação da Segurança Social.
     * @param idSegSocial
     */
    public void setIdSegSocial(int idSegSocial) {
        this.idSegSocial = idSegSocial;
    }

    /**
     * devolve o número de identificação fiscal.
     * @return  número de identificação fiscal
     */
    public int getIdFinancas() {
        return idFinancas;
    }

    /**
     * define o número de identificação fiscal.
     * @param idFinancas
     */
    public void setIdFinancas(int idFinancas) {
        this.idFinancas = idFinancas;
    }

    /**
     * devolve a morada da pessoa.
     * @return morada da pessoa
     */
    public String getMorada() {
        return morada;
    }

    /**
     * define a morada da pessoa
     * @param morada
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Devolve o número de telemóvel da pessoa.
     * @return numeor de telemovel da pessoa
     */
    public int getTelemovel() {
        return telemovel;
    }

    /**
     * define o número de telemóvel da pessoa.
     * @param telemovel
     */
    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }
}
