//Condutores (ex: nome, n.º de identificação, carta de condução, n.º de segurança social, n.º de
//identificação fiscal, telemóvel, morada, …);

public class Condutor extends Pessoa{
    private int idCondutor;
    private int cartaDeConducao;
    private double avaliacao;

    public Condutor() {
        idCondutor = 0;
        cartaDeConducao = 0;
        avaliacao = 0;
    }

    public Condutor(int cc, String nome, int idSegSocial, int idFinancas, String morada, int telemovel, int idCondutor, int cartaDeConducao, double avaliacao) {
        super(cc, nome, idSegSocial, idFinancas, morada, telemovel);
        this.idCondutor = idCondutor;
        this.cartaDeConducao = cartaDeConducao;
        this.avaliacao = avaliacao;
    }

    public int getIdCondutor() {
        return idCondutor;
    }

    public void setIdCondutor(int idCondutor) {
        this.idCondutor = idCondutor;
    }

    public int getCartaDeConducao() {
        return cartaDeConducao;
    }

    public void setCartaDeConducao(int cartaDeConducao) {
        this.cartaDeConducao = cartaDeConducao;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
