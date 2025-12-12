//Viaturas (ex: matrícula, marca, modelo, ano de fabrico, …);

/**
 * Declaração da classe Viatura.
 */
public class Viatura {
    /** atributo da matrícula.*/
    private String matricula;
    /** atributo da marca.*/
    private String marca;
    /** atributo do modelo.*/
    private String modelo;
    /** atributo do ano de fabrico.*/
    private int anoDeFabrico;

    /**
     * Construtor vazio.
     * Inicia todos os atributos com valores por defeito.
     */
    public Viatura() {
        anoDeFabrico = 0;
        modelo = "";
        marca = "";
        matricula = "";
    }

    /**
     * Construtor com parâmetros da classe Viatura.
     * Permite criar uma viatura com todos os seus atributos defenidos.
     * @param anoDeFabrico O ano do fabrico da viatura.
     * @param modelo O modelo da viatura.
     * @param marca A marca da viatura.
     * @param matricula A matrícula da viatura.
     */
    public Viatura(int anoDeFabrico, String modelo, String marca, String matricula) {
        this.anoDeFabrico = anoDeFabrico;
        this.modelo = modelo;
        this.marca = marca;
        this.matricula = matricula;
    }

    /**
     * Obtém a matrícula da viatura.
     * @return matrícula da viatura
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Define a matricula da viatura.
     * @param matricula A nova matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Obtém a marca da viatura.
     * @return marca da viatura.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca da viatura.
     * @param marca A nova marca da viatura.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtém o modelo da viatura.
     * @return modelo da viatura
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo da viatura.
     * @param modelo O novo modelo da viatura.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtém o ano de fabrico da viatura.
     * @return ano de fabrico
     */
    public int getAnoDeFabrico() {
        return anoDeFabrico;
    }

    /**
     * Define o ano de fabrico da viatura.
     * O ano só é aceite se estiver entre 1901 e 2025.
     */
    public void setAnoDeFabrico(int anoDeFabrico) {
        if (anoDeFabrico > 1900 && anoDeFabrico <= 2025) {
            this.anoDeFabrico = anoDeFabrico;
        } else {
            System.out.println("Erro");
        }
    }
}