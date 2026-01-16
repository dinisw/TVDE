import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Representa um ficheiro Reserva.
 * Serve para guardar os dados de uma reserva.
 */
public class Reserva {
    /** O cliente que efetuou a reserva. */
    private Cliente cliente;
    /** A viatura associa a reserva. */
    private Viatura viatura;
    /** A data e hora agendada para a viagem. */
    private LocalDateTime dataHoraInicio;
    /** A morada de onde partirá a viagem. */
    private String moradaOrigem;
    /** A morada de destino da viagem. */
    private String moradaDestino;
    /** A distância estimada ou calculada da viagem. */
    private double distancia;

    public Reserva() {
        cliente = new Cliente();
        viatura = new Viatura();
        dataHoraInicio = LocalDateTime.now();
        moradaOrigem = "";
        moradaDestino = "";
        distancia = 0;
    }

    /**
     * Construtor preenchido da classe Reserva.
     * Cria uma reserva preenchendo todos os dados necessários.
     *
     * @param cliente O cliente que fez a reserva.
     * @param viatura A viatura associada a reserva.
     * @param dataHoraInicio A data e hora em que a viagem vai acontecer.
     * @param moradaOrigem A morada onde vamos buscar o cliente.
     * @param moradaDestino A morada para onde o cliente quer ir.
     * @param distancia A distância da viagem.
     */
    public Reserva(Cliente cliente, Viatura viatura, LocalDateTime dataHoraInicio, String moradaOrigem, String moradaDestino, double distancia) {
        this.cliente = cliente;
        this.viatura = viatura;
        this.dataHoraInicio = dataHoraInicio;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.distancia = distancia;
    }

    public String paraFicheiro() {
        return cliente.getContribuinte() + ";" +
                viatura.getMatricula() + ";" +
                getDataHoraInicio() + ";" +
                getMoradaOrigem() + ";" +
                getMoradaDestino() + ";" +
                getDistancia();
    }

    @Override
    public String toString() {
        return "RESERVAS\n\n" +
                "Início:  " + getDataHoraInicio() + '\n' +
                "Origem:  " + getMoradaOrigem() + '\n' +
                "Destino:  " + getMoradaDestino() + '\n' +
                "Nome do cliente:  " + cliente.getNome() + '\n' +
                "Viatura: " + viatura.getMatricula() + " " + viatura.getCor()+ " " + viatura.getMarca() + " " + viatura.getModelo();
    }

    /**
     * Obtém a data e hora da reserva.
     * @return A data e hora agendada da viagem.
     */
    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    /**
     * Define uma nova data e hora para a reserva da viagem.
     * @param dataHoraInicio A nova data e hora a registar.
     */
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
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
     * Obtém a viatura registada a reserva
     * @return A viatura.
     */
    public Viatura getViatura() {
        return viatura;
    }

    /**
     * Define a viatura registada à viagem.
     * @param viatura A nova viatura.
     */

    public void setViatura(Viatura viatura) {
        this.viatura = viatura;
    }

    /**
     * Devolve uma representação em texto da reserva.
     * @return Uma string com os detalhes da reserva.
     */
}
