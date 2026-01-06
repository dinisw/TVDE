//Viaturas (ex: matrícula, marca, modelo, ano de fabrico, …);

import java.util.ArrayList;

/**
 * Declaração da classe Viatura.
 */
public class Viatura {
    /** Atribuição da matrícula.*/
    private String matricula;
    /** Atribuição da marca.*/
    private String marca;
    /** Atribuição do modelo.*/
    private String modelo;
    /** Atribuição do ano de fabrico.*/
    private int anoDeFabrico;
    /** Atribuição do número de portas. */
    private int nPortas;
    /** Atribuição da cor do Veículo. */
    private String cor;

    /**
     * Construtor vazio.
     * Inicia todos os atributos com valores por defeito.
     */
    public Viatura() {
        anoDeFabrico = 0;
        modelo = "";
        marca = "";
        matricula = "";
        cor = "";
        nPortas = 0;
    }

    /**
     * Construtor com parâmetros da classe Viatura.
     * Permite criar uma viatura com todos os seus atributos definidos.
     * @param anoDeFabrico O ano do fabrico da viatura.
     * @param modelo O modelo da viatura.
     * @param marca A marca da viatura.
     * @param matricula A matrícula da viatura.
     * @param nPortas Número de portas da viatura.
     * @param cor A cor da viatura.
     */
    public Viatura(String matricula, String marca, String modelo, int anoDeFabrico, int nPortas, String cor) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anoDeFabrico = anoDeFabrico;
        this.nPortas = nPortas;
        this.cor = cor;
    }

    /**
     * Obtém a matrícula da viatura.
     * @return matrícula da viatura.
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Define a matrícula da viatura.
     * @param matricula A nova matrícula.
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
     * @return modelo da viatura.
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
     * @return ano de fabrico.
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

    /**
     * Devolve o número de portas da viatura.
     * @return O número de portas da viatura.
     */
    public int getNPortas() {
        return nPortas;
    }

    /**
     * Define o número de portas da viatura.
     * @param nPortas O novo número de portas da viatura.
     */
    public void setNPortas(int nPortas) {
        this.nPortas = nPortas;
    }

    /**
     * Devolve a cor da viatura.
     * @return A cor da viatura.
     */
    public String getCor() {
        return cor;
    }

    /**
     * Define a cor da viatura.
     * @param cor A nova cor da viatura.
     */
    public void setCor(String cor) {
        this.cor = cor;
    }
    public void add(ArrayList<Viatura> viaturas){
    }
}