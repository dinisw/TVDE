import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Declaração da classe Viagem.
 */
public class Viagem {
    /** Atribuição da data da viagem.*/
    private LocalDate dataViagem;
    private LocalTime horaInicial;
    private LocalTime horaFinal;
    /** Atribuição da morada da origem.*/
    private String moradaOrigem;
    /** Atribuição da morada do destino.*/
    private String moradaDestino;
    /** Atribuição de quilómetros.*/
    private double kms;
    /** Atributo do custo da viagem.*/
    private double custoViagem;

    /**
     * Construtor vazio.
     * Inicia todos os atributos com valores por defeito.
     */
    public Viagem() {
        dataViagem = LocalDate.now();
        horaInicial = LocalTime.now();
        horaFinal = LocalTime.now();
        moradaOrigem = "";
        moradaDestino = "";
        kms = 0;
        custoViagem = 0;

    }

    /**
     * Construtor com parametros da classe Viagem.
     * Permite criar uma viagem com todos os seus atributos definidos.
     * @param dataViagem A Data da viagem.
     * @param dataInicial A Hora de inicio da viagem.
     * @param dataFinal A Hora de fim da viagem.
     * @param moradaOrigem A morada de origem.
     * @param moradaDestino A morada do destino da viagem.
     * @param kms O valor de quilómetros da viagem.
     * @param custoViagem O custo total da viagem
     */
    public Viagem(LocalDate dataViagem, LocalTime dataInicial, LocalTime dataFinal, String moradaOrigem, String moradaDestino, double kms, double custoViagem) {
        this.dataViagem = dataViagem;
        this.horaInicial = dataInicial;
        this.horaFinal = dataFinal;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.kms = kms;
        this.custoViagem = custoViagem;
    }

    /**
     * Devolve a data de início da viagem.
     * @return A data de inicio
     */
    public LocalDate getDataViagem() {
        return dataViagem;
    }

    /**
     * Define a data da viagem.
     * @param dataViagem A nova data da viagem.
     */
    public void setDataViagem(LocalDate dataViagem) {
        this.dataViagem = dataViagem;}

    public LocalTime getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(LocalTime horaInicial) {
        this.horaInicial = horaInicial;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * Define a morada da origem da viagem.
     * @return A morada da origem.
     */
    public String getMoradaDeOrigem() {
        return moradaOrigem;
    }

    /**
     * Define a morada da origem da viagem.
     * @param moradaOrigem A nova morada de origem da viagem.
     */
    public void setMoradaDeOrigem(String moradaOrigem) {
        this.moradaOrigem = moradaOrigem;
    }

    /**
     * Devolve a morada do destino da viagem.
     * @return A morada do destino.
     */
    public String getMoradaDeDestino() {
        return moradaDestino;
    }

    /**
     * Define a morada do destino da viagem.
     * @param moradaDestino A nova morada do destino da viagem.
     */
    public void setMoradaDeDestino(String moradaDestino) {
        this.moradaDestino = moradaDestino;
    }

    /**
     * Devolve número de quilómetros percorridos na viagem.
     * @return O número de quilómetros da viagem.
     */
    public double getKMS() {
        return kms;
    }

    /**
     * define número de quilómetros percorridos na viagem.
     * @param kms A nova distância em quilómetros.
     */
    public void setKMS(double kms) {
        this.kms = kms;
    }

    /**
     * devolve o custo da viagem.
     * @return custo da viagem
     */
    public double getCustoViagem() {
        return custoViagem;
    }

    /**
     * define o custo total da viagem.
     * @param custoViagem O novo custo total da viagem.
     */
    public void setCustoViagem(double custoViagem) {
        this.custoViagem = custoViagem;
    }

    public void add(ArrayList<Viagem> viagens) {}
}
