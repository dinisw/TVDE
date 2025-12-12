//Condutores (ex: nome, n.º de identificação, carta de condução, n.º de segurança social, n.º de
//identificação fiscal, telemóvel, morada, …);

public class Condutor extends Pessoa{
    private String cartaDeConducao;
    private double avaliacao;

    public Condutor() {
        cartaDeConducao = "";
        avaliacao = 0;
    }

    public Condutor(String cc, String nome, int idSegSocial, int idFinancas, String morada, int telemovel, String cartaDeConducao, double avaliacao) {
        super(cc, nome, idSegSocial, idFinancas, morada, telemovel);
        this.cartaDeConducao = cartaDeConducao;
        this.avaliacao = avaliacao;
    }

    public Condutor(String cc, String nome, int idSegSocial, int idFinancas, String morada, int telemovel, String cartaDeConducao) {
        super(cc, nome, idSegSocial, idFinancas, morada, telemovel);
        this.cartaDeConducao = cartaDeConducao;
    }

    public String getCartaDeConducao() {
        return cartaDeConducao;
    }

    public void setCartaDeConducao(String cartaDeConducao) {
        this.cartaDeConducao = cartaDeConducao;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String toString(){
        return getNome();
    }
}
