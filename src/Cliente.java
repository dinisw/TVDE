
//Clientes (ex: nome, n.º de identificação fiscal, telemóvel, morada, …);

/**
 * Declaração da classe Cliente representa um cliente do sistema.
 */
public class Cliente extends Pessoa{
    /** Identificador único do cliente */
    private int idCliente;

    /**
     * construtor da classe que recebe apenas o identificador do cliente.
     * @param idCliente
     */
    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * construtor completo da classe cliente.
     * permite criar um cliente com todos os seus atributos defenidos.
     * @param cc
     * @param nome
     * @param idSegSocial
     * @param idFinancas
     * @param morada
     * @param telemovel
     * @param idCliente
     */
    public Cliente(String cc, String nome, int idSegSocial, int idFinancas, String morada, int telemovel, int idCliente) {
        super(cc, nome, idSegSocial, idFinancas, morada, telemovel);
        this.idCliente = idCliente;
    }

    /**
     * devolve o identificador do cliente.
     * @return identificador do cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * define o identificador do cliente.
     * @param idCliente
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
