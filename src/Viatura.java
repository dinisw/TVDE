//Viaturas (ex: matrícula, marca, modelo, ano de fabrico, …);

import java.util.ArrayList;

/**
 * Declaração da classe Viatura.
 */
public class Viatura {
    /**
     * Atribuição da matrícula.
     */
    private final String matricula;
    /**
     * Atribuição da marca.
     */
    private String marca;
    /**
     * Atribuição do modelo.
     */
    private String modelo;
    /**
     * Atribuição do ano de fabrico.
     */
    private int anoDeFabrico;
    /**
     * Atribuição da cor do Veículo.
     */
    private String cor;
    /**
     * Indica se a viatura está disponível para utilização.
     */
    private boolean disponivel;

    public Viatura(){
        matricula = "";
        marca = "";
        modelo = "";
        anoDeFabrico = 0;
        cor = "";
        disponivel = false;
    }

    /**
     * Construtor com parâmetros da classe Viatura.
     * Permite criar uma viatura com todos os seus atributos definidos.
     *
     * @param anoDeFabrico O ano do fabrico da viatura.
     * @param modelo       O modelo da viatura.
     * @param marca        A marca da viatura.
     * @param matricula    A matrícula da viatura.
     * @param cor          A cor da viatura.
     * @param disponivel   Se a viatura está disponível.
     */
    public Viatura(String matricula, String marca, String modelo, int anoDeFabrico, String cor, boolean disponivel) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anoDeFabrico = anoDeFabrico;
        this.cor = cor;
        this.disponivel = disponivel;
    }

    public String paraFicheiro() {
        return this.matricula + ";" + this.marca + ";" + this.modelo + ";" + this.anoDeFabrico + ";" + this.cor + ";" + this.disponivel;
    }

    @Override
    public String toString() {
        String status = "";
        if(this.disponivel)
            status = "Disponível";
        else
            status = "Indisponível";
        return "Viatura:\n" +
                "Matricula = " + matricula + '\n' +
                "Marca = " + marca + '\n' +
                "Modelo = " + modelo + '\n' +
                "Ano de Fabrico = " + anoDeFabrico + '\n' +
                "Cor = " + cor + '\n' +
                "Status = " + status + '\n';
    }

    public ArrayList<Viatura> getViaturasDisponiveis(ArrayList<Viatura> viaturas){
        ArrayList<Viatura> viaturasDisponiveis = new ArrayList<Viatura>();
        for(var viatura : viaturas){
            if(viatura.disponivel){
                viaturasDisponiveis.add(viatura);
            }
        }
        return viaturasDisponiveis;
    }




    /**
     * Obtém a matrícula da viatura.
     *
     * @return A matrícula da viatura.
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Obtém a marca da viatura.
     *
     * @return marca da viatura.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca da viatura.
     *
     * @param marca A nova marca da viatura.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtém o modelo da viatura.
     *
     * @return modelo da viatura.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo da viatura.
     *
     * @param modelo O novo modelo da viatura.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtém o ano de fabrico da viatura.
     *
     * @return ano de fabrico.
     */
    public int getAnoDeFabrico() {
        return anoDeFabrico;
    }

    /**
     * Define o ano de fabrico da viatura.
     * O ano só é aceite se estiver entre 2001 e 2025.
     *
     * @param anoDeFabrico O novo ano de fabrico a ser definido.
     */
    public void setAnoDeFabrico(int anoDeFabrico) {
        if (anoDeFabrico > 2000 && anoDeFabrico <= 2025) {
            this.anoDeFabrico = anoDeFabrico;
        } else {
            System.out.println("Erro");
        }
    }

    /**
     * Devolve a cor da viatura.
     *
     * @return A cor da viatura.
     */
    public String getCor() {
        return cor;
    }

    /**
     * Define a cor da viatura.
     *
     * @param cor A nova cor da viatura.
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * Verifica se a viatura está disponível.
     *
     * @return true se a viatura estiver disponível, false caso contrário.
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Define o estado de disponibilidade da viatura.
     *
     * @param disponivel O novo estado de disponibilidade (true para disponível).
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Adiciona ou processa uma lista de viaturas.
     *
     * @param viaturas A lista (ArrayList) de viaturas a ser adicionada.
     */
    public void add(ArrayList<Viatura> viaturas) {
    }
}