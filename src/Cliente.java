
//Clientes (ex: nome, n.º de identificação fiscal, telemóvel, morada, …);

/**
 * Declaração da classe Cliente representa um cliente do sistema.
 */
public class Cliente {
    /**
     * Atribuição do cartão de cidadão do/a cliente.
     */
    private int cartaoCidadao;
    /**
     * Atribuição do nome completo do/a cliente.
     */
    private String nomeCliente;
    /**
     * Número de Contribuinte.
     */
    private int idContribuinte;
    /**
     * Morada de residência do/a cliente.
     */
    private String morada;
    /**
     * Número de telemóvel do/a cliente.
     */
    private int telemovel;

    /**
     * Construtor vazio.
     * Inicia todos os atributos com valores por defeito.
     */
    public Cliente() {
        cartaoCidadao = 0;
        nomeCliente = "";
        idContribuinte = 0;
        morada = "";
        telemovel = 0;
    }

    /**
     * Construtor preenchido.
     * Cria um construtor que cria todos os dados pessoais.
     *
     * @param cartaoCidadao  O cartão de cidadão.
     * @param nomeCliente    O nome completo.
     * @param idContribuinte O número de Contribuinte.
     * @param morada         A morada de residência.
     * @param telemovel      O número de telefone
     */
    public Cliente(int cartaoCidadao, String nomeCliente, int idContribuinte, String morada, int telemovel) {
        this.cartaoCidadao = cartaoCidadao;
        this.nomeCliente = nomeCliente;
        this.idContribuinte = idContribuinte;
        this.morada = morada;
        this.telemovel = telemovel;
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
     * Devolve o nome do/a cliente.
     * @return O nome do/a cliente.
     */
    public String getNomeCliente() {
        return nomeCliente;
    }
    /**
     * Define o nome do/a cliente
     * @param nomeCliente O novo nome completo.
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    /**
     * Devolve o número de identificação fiscal.
     * @return O número de identificação fiscal
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
     * Devolve a morada do/a cliente.
     * @return A morada do/a cliente.
     */
    public String getMorada() {
        return morada;
    }
    /**
     * Define a morada do/a cliente.
     * @param morada A nova morada de residência.
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }
    /**
     * Devolve o número de telemóvel do/a cliente.
     * @return número de telemóvel do/a cliente.
     */
    public int getTelemovel() {
        return telemovel;
    }
    /**
     * Define o número de telemóvel do/a cliente.
     * @param telemovel O novo número de telefone.
     */
    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }
}