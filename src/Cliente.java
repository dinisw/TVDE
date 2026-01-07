public class Cliente extends Pessoa {
    private int totalViagens;
    private double totalGasto;

    public Cliente(String nome, int idade, String sexo, String email, String telefone, String morada, int cartaoDeCidadao) {
        super(nome, idade, sexo, email, telefone, morada, cartaoDeCidadao);
        this.totalViagens = 0;
        this.totalGasto = 0;
    }

    public int getTotalViagens() {
        return totalViagens;
    }

    public void setTotalViagens(int totalViagens) {
        this.totalViagens = totalViagens;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }

    public void addViagem(double valor){
        this.totalViagens++;
        this.totalGasto += valor;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "totalViagens=" + totalViagens +
                ", totalGasto=" + totalGasto +
                '}';
    }
}