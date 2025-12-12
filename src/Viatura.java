//Viaturas (ex: matrícula, marca, modelo, ano de fabrico, …);

/**
 * Declaração de classes e atributos
 */
public class Viatura {
    private String matricula;
    private String marca;
    private String modelo;
    private int anoDeFabrico;

    /**
     * Construtor padrão (sem argumentos)
     */
    public Viatura() {
        anoDeFabrico = 0;
        modelo = "";
        marca = "";
        matricula = "";
    }

    /**
     * Construtor com parâmetros
     */
    public Viatura(int anoDeFabrico, String modelo, String marca, String matricula) {
        this.anoDeFabrico = anoDeFabrico;
        this.modelo = modelo;
        this.marca = marca;
        this.matricula = matricula;
    }

    /**
     * Métodos getters e setters
     */
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoDeFabrico() {
        return anoDeFabrico;
    }

    /**
     * Validação no setter do ano
     */
    public void setAnoDeFabrico(int anoDeFabrico) {
        if (anoDeFabrico > 1900 && anoDeFabrico <= 2025) {
            this.anoDeFabrico = anoDeFabrico;
        } else {
            System.out.println("Erro");
        }
    }
}