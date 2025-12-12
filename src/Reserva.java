import java.util.Date;

/**
 * Representa um ficheiro Reserva.
 * Serve para guardar os dados de uma reserva.
 */
public class Reserva {
    private Date dataHora;
    private String moradaOrigem;
    private String moradaDestino;
    private double kms;

    /**
     * Construtor vazio.
     * Cria uma reserva vazia com a data atual e kms 0.
     */
    public Reserva() {
        dataHora = new Date();
        moradaOrigem = "";
        moradaDestino = "";
        kms = 0;
    }

    /**
     * Construtor preenchido.
     * Cria uma reserva preenchendo todos os dados.
     * @param kms A Distância da viagem em quilómetros.
     * @param moradaDestino A morada para onde o cliente quer ir.
     * @param moradaOrigem A morada onde vamos buscar o cliente.
     * @param dataHora A data e hora em que a viagem vai acontecer.
     */
    public Reserva(double kms, String moradaDestino, String moradaOrigem, Date dataHora) {
        this.kms = kms;
        this.moradaDestino = moradaDestino;
        this.moradaOrigem = moradaOrigem;
        this.dataHora = dataHora;
    }

    /**
     * Obtém a data e hora da reserva.
     * @return A data agendada da viagem.
     */
    public Date getDataHora() {
        return dataHora;
    }

    /**
     * Define uma nova data e hora para a reserva da viagem.
     * @param dataHora A nova data e hora a registar.
     */
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * Obtém a morada origem.
     * @return O local de onde começa a viagem.
     */
    public String getMoradaOrigem() {
        return moradaOrigem;
    }

    /**
     * Define uma nova morada de origem.
     * @param moradaOrigem O novo endereço em que a viagem vai começar.
     */
    public void setMoradaOrigem(String moradaOrigem) {
        this.moradaOrigem = moradaOrigem;
    }

    /**
     * Obtém a morada do destino.
     * @return O local onde termina a viagem.
     */
    public String getMoradaDestino() {
        return moradaDestino;
    }

    /**
     * Define uma nova morada de destino.
     * @param moradaDestino O novo endereço em que a viagem vai terminar.
     */
    public void setMoradaDestino(String moradaDestino) {
        this.moradaDestino = moradaDestino;
    }

    /**
     * Obtém a distância da viagem.
     * @return O número de quilómetros da reserva da viagem.
     */
    public double getkms() {
        return kms;
    }

    /**
     * Define a distância da Viagem.
     * @param kms A nova distância em quilómetros.
     */
    public void setkms(double kms) {
        this.kms = kms;
    }
}
