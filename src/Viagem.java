import java.time.LocalDate;
import java.util.Date;

/**
 * Declaração da classe Viagem.
 */
public class Viagem {
    /** Atributo da data de inicio.*/
    private Date dataInicio;
    /** Atributo da data do fim.*/
    private Date dataFim;
    /** Atributo da morada da origem*/
    private String moradaOrigem;
    /** Atributo da morada do destino*/
    private String moradaDestino;
    /** Atributo de kms*/
    private double kms;
    /** Atributo do custo da viagem*/
    private double custoViagem;

    /**
     * Construtor padrão (sem argumentos).
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
     * construtor com parametros da classe viagem.
     * Permite criar uma viagem com todos os seus atributos defenidos.
     * @param dataInicio
     * @param dataFim
     * @param moradaOrigem
     * @param moradaDestino
     * @param kms
     * @param custoViagem
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
     * devolve a data de inicio da viagem.
     * @return data de inicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * define a data de inicio da viagem.
     * @param dataInicio
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * devolve a data do fim da viagem.
     * @return data do fim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * define a data de fin da viagem.
     * @param dataFim
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * define a morada da origem da viagem.
     * @return morada da origem
     */
    public String getMoradaOrigem() {
        return moradaOrigem;
    }

    /**
     * define a morada da origem da viagem.
     * @param moradaOrigem
     */
    public void setMoradaOrigem(String moradaOrigem) {
        this.moradaOrigem = moradaOrigem;
    }

    /**
     * devolve a morada do destino da viagem.
     * @return morada do destino
     */
    public String getMoradaDestino() {
        return moradaDestino;
    }

    /**
     * define a morada do destino da viagem.
     * @param moradaDestino
     */
    public void setMoradaDestino(String moradaDestino) {
        this.moradaDestino = moradaDestino;
    }

    /**
     * devolve número de quilómetros percorridos na viagem.
     * @return ksm
     */
    public double getKms() {
        return kms;
    }

    /**
     * define número de quilómetros percorridos na viagem.
     * @param kms
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
     * @param custoViagem
     */
    public void setCustoViagem(double custoViagem) {
        this.custoViagem = custoViagem;
    }
}
