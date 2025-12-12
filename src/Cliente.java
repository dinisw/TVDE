
//Clientes (ex: nome, n.º de identificação fiscal, telemóvel, morada, …);
public class Cliente extends Pessoa{
    private int idCliente;

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(String cc, String nome, int idSegSocial, int idFinancas, String morada, int telemovel, int idCliente) {
        super(cc, nome, idSegSocial, idFinancas, morada, telemovel);
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
