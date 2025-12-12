import java.time.LocalDate;
import java.util.Date;

/**
 * Declaração da classe Viagem.
 */
public class Viagem {
    /** Atribuição da data de início.*/
    private Date dataInicio;
    /** Atribuição da data do fim.*/
    private Date dataFim;
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
        dataInicio = new Date();
        dataFim = new Date();
        moradaOrigem = "";
        moradaDestino = "";
        kms = 0;
        custoViagem = 0;

    }

    /**
     * Construtor com parametros da classe Viagem.
     * Permite criar uma viagem com todos os seus atributos definidos.
     * @param dataInicio A Data de início da viagem.
     * @param dataFim A Data do fim da viagem.
     * @param moradaOrigem A morada de origem.
     * @param moradaDestino A morada do destino da viagem.
     * @param kms O valor de quilómetros da viagem.
     * @param custoViagem O custo total da viagem
     */
    public Viagem(Date dataInicio, Date dataFim, String moradaOrigem, String moradaDestino, double kms, double custoViagem) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.kms = kms;
        this.custoViagem = custoViagem;
    }

    /**
     * Devolve a data de início da viagem.
     * @return A data de inicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * Define a data de inicio da viagem.
     * @param dataInicio A nova data de início da viagem.
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Devolve a data do fim da viagem.
     * @return A data do fim.
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * Define a data de fin da viagem.
     * @param dataFim A nova data de fim da viagem.
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Define a morada da origem da viagem.
     * @return A morada da origem.
     */
    public String getMoradaOrigem() {
        return moradaOrigem;
    }

    /**
     * Define a morada da origem da viagem.
     * @param moradaOrigem A nova morada de origem da viagem.
     */
    public void setMoradaOrigem(String moradaOrigem) {
        this.moradaOrigem = moradaOrigem;
    }

    /**
     * Devolve a morada do destino da viagem.
     * @return A morada do destino.
     */
    public String getMoradaDestino() {
        return moradaDestino;
    }

    /**
     * Define a morada do destino da viagem.
     * @param moradaDestino A nova morada do destino da viagem.
     */
    public void setMoradaDestino(String moradaDestino) {
        this.moradaDestino = moradaDestino;
    }

    /**
     * Devolve número de quilómetros percorridos na viagem.
     * @return O número de quilómetros da viagem.
     */
    public double getKms() {
        return kms;
    }

    /**
     * define número de quilómetros percorridos na viagem.
     * @param kms A nova distância em quilómetros.
     */
    public void setKms(double kms) {
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
}
