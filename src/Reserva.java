import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Representa um ficheiro Reserva.
 * Serve para guardar os dados de uma reserva.
 */
public class Reserva {
    private Cliente cliente;
    private LocalDate data;
    private LocalTime hora;
    private String moradaOrigem;
    private String moradaDestino;
    private boolean confirmacao;

    /**
     * Construtor preenchido.
     * Cria uma reserva preenchendo todos os dados.
     *
     * @param cliente       O cliente que fez a reserva.
     * @param moradaDestino A morada para onde o cliente quer ir.
     * @param moradaOrigem  A morada onde vamos buscar o cliente.
     * @param data          A data em que a viagem vai acontecer.
     * @param hora          A hora em que a viagem vai acontecer.
     * @param confirmacao   A confirmação da viagem.
     */
    public Reserva(Cliente cliente, LocalDate data, LocalTime hora, String moradaOrigem, String moradaDestino, boolean confirmacao) {
        this.cliente = cliente;
        this.data = data;
        this.hora = hora;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.confirmacao = confirmacao;
    }

    /**
     * Obtém a data da reserva.
     *
     * @return A data agendada da viagem.
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Define uma nova data e hora para a reserva da viagem.
     *
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
     *
     * @return O local de onde começa a viagem.
     */
    public String getMoradaOrigem() {
        return moradaOrigem;
    }

    /**
     * Define uma nova morada de origem.
     *
     * @param moradaOrigem O novo endereço em que a viagem vai começar.
     */
    public void setMoradaOrigem(String moradaOrigem) {
        this.moradaOrigem = moradaOrigem;
    }

    /**
     * Obtém a morada do destino.
     *
     * @return O local onde termina a viagem.
     */
    public String getMoradaDestino() {
        return moradaDestino;
    }

    /**
     * Define uma nova morada de destino.
     *
     * @param moradaDestino O novo endereço em que a viagem vai terminar.
     */
    public void setMoradaDestino(String moradaDestino) {
        this.moradaDestino = moradaDestino;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(boolean confirmacao) {
        this.confirmacao = confirmacao;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "cliente=" + cliente +
                ", data=" + data +
                ", hora=" + hora +
                ", moradaOrigem='" + moradaOrigem + '\'' +
                ", moradaDestino='" + moradaDestino + '\'' +
                ", confirmacao=" + confirmacao +
                '}';
    }
}
