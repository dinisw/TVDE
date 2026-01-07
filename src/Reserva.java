import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Representa um ficheiro Reserva.
 * Serve para guardar os dados de uma reserva.
 */
public class Reserva {
    /** O cliente que efetuou a reserva. */
    private Cliente cliente;
    /** A data agendada para a viagem. */
    private LocalDate data;
    /** A hora agendada para a viagem. */
    private LocalTime hora;
    /** A morada de onde partirá a viagem. */
    private String moradaOrigem;
    /** A morada de destino da viagem. */
    private String moradaDestino;
    /** A distância estimada ou calculada da viagem. */
    private double distancia;

    /**
     * Construtor preenchido da classe Reserva.
     * Cria uma reserva preenchendo todos os dados necessários.
     *
     * @param cliente O cliente que fez a reserva.
     * @param data A data em que a viagem vai acontecer.
     * @param hora A hora em que a viagem vai acontecer.
     * @param moradaOrigem A morada onde vamos buscar o cliente.
     * @param moradaDestino A morada para onde o cliente quer ir.
     * @param distancia A distância da viagem.
     */
    public Reserva(Cliente cliente, LocalDate data, LocalTime hora, String moradaOrigem, String moradaDestino, double distancia) {
        this.cliente = cliente;
        this.data = data;
        this.hora = hora;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.distancia = distancia;
    }

    /**
     * Obtém a data da reserva.
     * @return A data agendada da viagem.
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Define uma nova data para a reserva da viagem.
     * @param data A nova data a registar.
     */
    public void setData(LocalDate data) {
        this.data = data;
    }
    /**
     * Obtém a hora da reserva.
     * @return A hora agendada para a viagem.
     */
    public LocalTime getHora() {
        return hora;
    }
    /**
     * Define a hora da reserva.
     * @param hora A nova hora a ser definida.
     */
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
     * Obtém o cliente associado à reserva.
     * @return O objeto Cliente.
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Define o cliente da reserva.
     * @param cliente O novo cliente a associar.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Obtém a distância da viagem.
     * @return A distância da viagem.
     */
    public double getDistancia() {
        return distancia;
    }
    /**
     * Define a distância da viagem.
     * @param distancia A nova distância a ser registada.
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    /**
     * Adiciona ou processa uma lista de reservas.
     * @param reservas A lista (ArrayList) de reservas a ser adicionada.
     */
    public void add(ArrayList<Reserva> reservas){}
    /**
     * Devolve uma representação em texto da reserva.
     * @return Uma string com os detalhes da reserva.
     */
    @Override
    public String toString() {
        return "Reserva{" +
                "cliente=" + cliente +
                ", data=" + data +
                ", hora=" + hora +
                ", moradaOrigem='" + moradaOrigem + '\'' +
                ", moradaDestino='" + moradaDestino + '\'' +
                ", distancia=" + distancia +
                '}';
    }
}
