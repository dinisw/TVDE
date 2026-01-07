import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Declaração da classe Viagem.
 */
public class Viagem {
    private Cliente cliente;
    private Condutor condutor;
    private Viatura viatura;
    /** Atribuição da data da viagem.*/
    private LocalDate dataViagem;
    private LocalTime hora;
    /** Atribuição da morada da origem.*/
    private String moradaOrigem;
    /** Atribuição da morada do destino.*/
    private String moradaDestino;
    private boolean concluida;
    /** Atributo do custo da viagem.*/
    private double custoViagem;
    /**
     * Construtor com parametros da classe Viagem.
     * Permite criar uma viagem com todos os seus atributos definidos.
     * @param dataViagem A Data da viagem.
     * @param cliente O cliente que
     * @param moradaOrigem A morada de origem.
     * @param moradaDestino A morada do destino da viagem.
     * @param custoViagem O custo total da viagem
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Viatura getViatura() {
        return viatura;
    }

    public void setViatura(Viatura viatura) {
        this.viatura = viatura;
    }

    public LocalDate getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(LocalDate dataViagem) {
        this.dataViagem = dataViagem;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public String getMoradaOrigem() {
        return moradaOrigem;
    }

    public void setMoradaOrigem(String moradaOrigem) {
        this.moradaOrigem = moradaOrigem;
    }

    public String getMoradaDestino() {
        return moradaDestino;
    }

    public void setMoradaDestino(String moradaDestino) {
        this.moradaDestino = moradaDestino;
    }

    public double getCustoViagem() {
        return custoViagem;
    }

    public void setCustoViagem(double custoViagem) {
        this.custoViagem = custoViagem;
    }
    public void add(ArrayList<Viagem> viagens){}
}