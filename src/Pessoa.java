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
     * Construtor vazio da classe pessoa.
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
     * @param cc O cartão de cidadão.
     * @param nome O nome completo.
     * @param idSegSocial O número da segurança social.
     * @param idFinancas O número de identificação fiscal / Contribuinte.
     * @param morada A morada de residência.
     * @param telemovel O número de telefone
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
     * devolve o número do cartão de cidadão.
     * @return número do cartão de cidadão
     */
    public String getCc() {
        return cc;
    }

    /**
     * define o número do cartão de cidadão.
     * @param cc O novo cartão de cidadão.
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
     * @param nome O novo nome completo.
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
     * @param idSegSocial O número da segurança social.
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
     * @param idFinancas O novo número de identificação fiscal / Contribuinte.
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
     * @param morada A nova morada de residência.
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Devolve o número de telemóvel da pessoa.
     * @return número de telemóvel da pessoa
     */
    public int getTelemovel() {
        return telemovel;
    }

    /**
     * define o número de telemóvel da pessoa.
     * @param telemovel O novo número de telefone
     */
    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }
}
