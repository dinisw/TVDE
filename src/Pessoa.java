/**
 * Representa uma pessoa no sistema.
 *
 * Classe abstrata que serve de base para os diferentes tipos de utilizadores,
 * como clientes e condutores. Contém informação pessoal comum a todos.
 */
public abstract class Pessoa {
    /** Nome da pessoa. */
    private String nome;
    /** Idade da pessoa. */
    private int idade;
    /** Sexo da pessoa. */
    private String sexo;
    /** Endereço de email da pessoa. */
    private String email;
    /** Número de telefone da pessoa. */
    private int telefone;
    /** Morada da pessoa. */
    private String morada;
    /** Número do cartão de cidadão da pessoa. */
    private int cartaoDeCidadao;
    /** Número de contribuinte da pessoa. */
    private int contribuinte;

    /**
     * Construtor da classe Pessoa.
     *
     * @param nome Nome da pessoa
     * @param idade Idade da pessoa
     * @param sexo Sexo da pessoa
     * @param email Endereço de email
     * @param telefone Número de telefone
     * @param morada Morada
     * @param cartaoDeCidadao Número do cartão de cidadão
     * @param contribuinte Número de contribuinte
     */
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

    /**
     * Obtém o número de contribuinte.
     *
     * @return Número de contribuinte
     */
    public int getContribuinte() {
        return contribuinte;
    }

    /**
     * Define o número de contribuinte.
     *
     * @param contribuinte Número de contribuinte
     */
    public void setContribuinte(int contribuinte) {
        this.contribuinte = contribuinte;
    }

    /**
     * Obtém o nome da pessoa.
     *
     * @return Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome Nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a idade da pessoa.
     *
     * @return Idade
     */
    public int getIdade() {
        return idade;
    }

    /**
     * Define a idade da pessoa.
     *
     * @param idade Idade
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * Obtém o sexo da pessoa.
     *
     * @return Sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Define o sexo da pessoa.
     *
     * @param sexo Sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    /**
     * Obtém o email da pessoa.
     *
     * @return Endereço de email
     */
    public String getEmail() {
        return email;
    }


    /**
     * Define o email da pessoa.
     *
     * @param email Endereço de email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o número de telefone.
     *
     * @return Número de telefone
     */
    public int getTelefone() {
        return telefone;
    }

    /**
     * Define o número de telefone.
     *
     * @param telefone Número de telefone
     */
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém a morada da pessoa.
     *
     * @return Morada
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Define a morada da pessoa.
     *
     * @param morada Morada
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Obtém o número do cartão de cidadão.
     *
     * @return Número do cartão de cidadão
     */
    public int getCartaoDeCidadao() {
        return cartaoDeCidadao;
    }

    /**
     * Define o número do cartão de cidadão.
     *
     * @param cartaoDeCidadao Número do cartão de cidadão
     */
    public void setCartaoDeCidadao(int cartaoDeCidadao) {
        this.cartaoDeCidadao = cartaoDeCidadao;
    }

    /**
     * Devolve uma representação textual da pessoa.
     *
     * @return String com os dados da pessoa
     */
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
