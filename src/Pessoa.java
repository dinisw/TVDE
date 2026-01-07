public abstract class Pessoa {
    protected String nome;
    protected int idade;
    protected String sexo;
    protected String email;
    protected String telefone;
    protected String morada;
    protected int cartaoDeCidadao;

    public Pessoa(String nome, int idade, String sexo, String email, String telefone, String morada, int cartaoDeCidadao) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.morada = morada;
        this.cartaoDeCidadao = cartaoDeCidadao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getCartaoDeCidadao() {
        return cartaoDeCidadao;
    }

    public void setCartaoDeCidadao(int cartaoDeCidadao) {
        this.cartaoDeCidadao = cartaoDeCidadao;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", sexo='" + sexo + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", morada='" + morada + '\'' +
                ", cartaoDeCidadao=" + cartaoDeCidadao +
                '}';
    }
}
