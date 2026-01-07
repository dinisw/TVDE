public abstract class Pessoa {
    private String nome;
    private int idade;
    private String sexo;
    private String email;
    private int telefone;
    private String morada;
    private int cartaoDeCidadao;
    private int contribuinte;

    public Pessoa(String nome, int idade, String sexo, String email, int telefone, String morada, int cartaoDeCidadao, int contribuinte) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.morada = morada;
        this.cartaoDeCidadao = cartaoDeCidadao;
        this.contribuinte = contribuinte;
    }

    public int getContribuinte() {
        return contribuinte;
    }

    public void setContribuinte(int contribuinte) {
        this.contribuinte = contribuinte;
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

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
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
