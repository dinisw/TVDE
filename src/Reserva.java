import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Representa um ficheiro Reserva.
 * Serve para guardar os dados de uma reserva.
 */
public class Reserva {
    private LocalDate data;
    private LocalTime hora;
    private String moradaOrigem;
    private String moradaDestino;
    private double kms;

    /**
     * Construtor vazio.
     * Cria uma reserva vazia com a data atual e kms 0.
     */
    public Reserva() {
        data =  LocalDate.now();
        hora = LocalTime.now();
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
     * @param data A data em que a viagem vai acontecer.
     * @param hora A hora em que a viagem vai acontecer.
     */
    public Reserva(double kms, String moradaDestino, String moradaOrigem, LocalDate data, LocalTime hora) {
        this.kms = kms;
        this.moradaDestino = moradaDestino;
        this.moradaOrigem = moradaOrigem;
        this.data = data;
        this.hora = hora;
    }

    /**
     * Obtém a data da reserva.
     * @return A data agendada da viagem.
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Define uma nova data e hora para a reserva da viagem.
     * @param data A nova data e hora a registar.
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
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
    public double getKms() {
        return kms;
    }

    /**
     * Define a distância da Viagem.
     * @param kms A nova distância em quilómetros.
     */
    public void setKms(double kms) {
        this.kms = kms;
    }
    public void add(ArrayList<Reserva> reservas) {
    }
}
