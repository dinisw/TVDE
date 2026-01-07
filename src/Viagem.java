import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Declaração da classe Viagem.
 */
public class Viagem {
    /** o cliente associado à viagem. */
    private Cliente cliente;
    /** O condutor responsável pela viagem. */
    private Condutor condutor;
    /** A viatura utilizada na viagem. */
    private Viatura viatura;
    /** Atribuição da data da viagem.*/
    private LocalDate dataViagem;
    /** Atribuição da hora da viagem. */
    private LocalTime hora;
    /** Atribuição da morada da origem.*/
    private String moradaOrigem;
    /** Atribuição da morada do destino.*/
    private String moradaDestino;
    /** Indica se a viagem foi concluída. */
    private boolean concluida;
    /** Atributo do custo da viagem.*/
    private double custoViagem;
    /**
     * Construtor com parâmetros da classe Viagem.
     * Permite criar uma viagem com todos os seus atributos definidos.
     * @param cliente O cliente que solicitou a viagem.
     * @param condutor O condutor atribuído à viagem.
     * @param viatura A viatura utilizada na viagem.
     * @param dataViagem A data da viagem.
     * @param hora A hora da viagem.
     * @param concluida Indica se a viagem já foi concluída.
     * @param moradaOrigem A morada de origem.
     * @param moradaDestino A morada do destino da viagem.
     * @param custoViagem O custo total da viagem.
     */
    public Viagem(Cliente cliente, Condutor condutor, Viatura viatura, LocalDate dataViagem, LocalTime hora, boolean concluida, String moradaOrigem, String moradaDestino, double custoViagem) {
        this.cliente = cliente;
        this.condutor = condutor;
        this.viatura = viatura;
        this.dataViagem = dataViagem;
        this.hora = hora;
        this.concluida = concluida;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.custoViagem = custoViagem;
    }
    /**
     * Obtém o cliente da viagem.
     * @return O objeto Cliente.
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Define o cliente da viagem.
     * @param cliente O novo cliente a associar.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Obtém o condutor da viagem.
     * @return O objeto Condutor.
     */
    public Condutor getCondutor() {
        return condutor;
    }
    /**
     * Define o condutor da viagem.
     * @param condutor O novo condutor a associar.
     */
    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }
    /**
     * Obtém a viatura da viagem.
     * @return O objeto Viatura.
     */
    public Viatura getViatura() {
        return viatura;
    }
    /**
     * Define a viatura da viagem.
     * @param viatura A nova viatura a associar.
     */
    public void setViatura(Viatura viatura) {
        this.viatura = viatura;
    }
    /**
     * Obtém a data da viagem.
     * @return A data da viagem.
     */
    public LocalDate getDataViagem() {
        return dataViagem;
    }
    /**
     * Define a data da viagem.
     * @param dataViagem A nova data da viagem.
     */
    public void setDataViagem(LocalDate dataViagem) {
        this.dataViagem = dataViagem;
    }
    /**
     * Obtém a hora da viagem.
     * @return A hora da viagem.
     */
    public LocalTime getHora() {
        return hora;
    }
    /**
     * Define a hora da viagem.
     * @param hora A nova hora da viagem.
     */
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    /**
     * Verifica se a viagem está concluída.
     * @return true se concluída, false caso contrário.
     */
    public boolean isConcluida() {
        return concluida;
    }
    /**
     * Define o estado de conclusão da viagem.
     * @param concluida O novo estado (true para concluída).
     */
    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
    /**
     * Obtém a morada de origem.
     * @return A string da morada de origem.
     */
    public String getMoradaOrigem() {
        return moradaOrigem;
    }
    /**
     * Define a morada de origem.
     * @param moradaOrigem A nova morada de origem.
     */
    public void setMoradaOrigem(String moradaOrigem) {
        this.moradaOrigem = moradaOrigem;
    }
    /**
     * Obtém a morada de destino.
     * @return A string da morada de destino.
     */
    public String getMoradaDestino() {
        return moradaDestino;
    }
    /**
     * Define a morada de destino.
     * @param moradaDestino A nova morada de destino.
     */
    public void setMoradaDestino(String moradaDestino) {
        this.moradaDestino = moradaDestino;
    }
    /**
     * Obtém o custo da viagem.
     * @return O valor do custo.
     */
    public double getCustoViagem() {
        return custoViagem;
    }
    /**
     * Define o custo da viagem.
     * @param custoViagem O novo valor do custo.
     */
    public void setCustoViagem(double custoViagem) {
        this.custoViagem = custoViagem;
    }
    /**
     * Adiciona ou processa uma lista de viagens.
     * @param viagens A lista (ArrayList) de viagens a ser adicionada.
     */
    public void add(ArrayList<Viagem> viagens){}
}