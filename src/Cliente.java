
//Clientes (ex: nome, n.º de identificação fiscal, telemóvel, morada, …);

/**
 * Declaração da classe Cliente representa um cliente do sistema.
 */
public class Cliente extends Pessoa{
    /** Identificador único do cliente */
    private int idCliente;

    /**
     * Construtor da classe que recebe apenas o identificador do cliente.
     * @param idCliente
     */
    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Construtor vazio da classe cliente.
     * Permite criar um cliente com todos os seus atributos definidos.
     * @param cc O cartão de cidadão.
     * @param nome O nome completo.
     * @param idSegSocial O número da segurança social.
     * @param idFinancas O número de identificação fiscal / Contribuinte.
     * @param morada  A morada de residência.
     * @param telemovel  O número de telefone.
     * @param idCliente  O número da carta de condução.
     */
    public Cliente(String cc, String nome, int idSegSocial, int idFinancas, String morada, int telemovel, int idCliente) {
        super(cc, nome, idSegSocial, idFinancas, morada, telemovel);
        this.idCliente = idCliente;
    }

    /**
     * Devolve o número de identificação do cliente.
     * @return IdCliente O número identificação do cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Define o número de identificação do cliente.
     * @param idCliente O novo número de identificação do cliente
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
