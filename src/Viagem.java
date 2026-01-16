import java.time.LocalDate;
import java.time.LocalDateTime;
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
    /** Atribuição da data e hora do início viagem.*/
    private LocalDateTime datanIcio;
    /** Atribuição da data e hora do fim da viagem. */
    private LocalDateTime dataFim;
    /** Atribuição da morada da origem.*/
    private String moradaOrigem;
    /** Atribuição da morada do destino.*/
    private String moradaDestino;
    /** Indica se a viagem foi concluída. */
    private boolean concluida;
    /** Atributo do custo da viagem.*/
    private double custoViagem;
    /** Atribuição para a distância.*/
    private double distancia;

    public Viagem(String moradaOrigem, String moradaDestino, LocalDate dataInicio, LocalTime horaInicio, String cliente, String viatura, String condutor) {
        this.cliente = new Cliente();
        this.condutor = new Condutor();
        this.viatura = new Viatura();
        datanIcio = LocalDateTime.now();
        dataFim = LocalDateTime.now();
        this.moradaOrigem = "";
        this.moradaDestino = "";
        concluida = false;
        custoViagem = 0;
        distancia = 0;
    }
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
    public Viagem(Cliente cliente, Condutor condutor, Viatura viatura, LocalDateTime dataViagem, LocalTime hora, boolean concluida, String moradaOrigem, String moradaDestino, double custoViagem) {
        this.cliente = cliente;
        this.condutor = condutor;
        this.viatura = viatura;
        this.datanIcio = datanIcio;
        this.dataFim = dataFim;
        this.concluida = concluida;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.custoViagem = custoViagem;
        this.distancia = distancia;
    }

    /**
     * O metodo expande os principais atributos do objeto separados por ponto e vírgula (`;`),
     * criando uma representação adequada para armazenamento ou exportação para ficheiro.
     * @return uma String contendo os dados do objeto separados por `;`, pronta para escrita em ficheiro
     */
    public String paraFicheiro() {
        return cliente.getContribuinte() + ";" +
                condutor.getContribuinte() + ";" +
                viatura.getMatricula() + ";" +
                getInicio() + ";" +
                getFim() + ";" +
                isConcluida() + ";" +
                getMoradaOrigem() + ";" +
                getMoradaDestino() + ";" +
                getCustoViagem() + ";" +
                getDistancia();
    }

    @Override
    public String toString() {
        String status = "";
        if(this.concluida)
            status = "Concluído";
        else
            status = "Não concluído";
        return "VIAGEM\n\n" +
                "Início:  " + getInicio() + '\n' +
                "Fim:  " + getFim() + '\n' +
                "Status:  " + status + '\n' +
                "Origem:  " + getMoradaOrigem() + '\n' +
                "Destino:  " + getMoradaDestino() + '\n' +
                "Distância:  " + getDistancia() + '\n' +
                "Custo da viagem:  " + getCustoViagem() + '\n' +
                "Nome do cliente:  " + cliente.getNome() + '\n' +
                "Nome do Condutor:  " + condutor.getNome() + '\n' +
                "Viatura: " + viatura.getMatricula() + " " + viatura.getCor()+ " " + viatura.getMarca() + " " + viatura.getModelo();
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
     * Obtém a data e hora da viagem.
     * @return A data e hora da viagem.
     */
    public LocalDateTime getDatanIcio() {
        return datanIcio;
    }
    /**
     * Define a data e hora da viagem.
     * @param datanIcio A nova data e hora da viagem.
     */
    public void setDatanIcio(LocalDateTime datanIcio) {
        this.datanIcio = datanIcio;
    }
    /**
     * Obtém a data e hora da viagem.
     * @return A data e hora da viagem.
     */
    public LocalDateTime getDataFim() {
        return dataFim;
    }
    /**
     * Define a data e hora da viagem.
     * @param dataFim A nova data hora da viagem.
     */
    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
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
     * Obtém a distância da viagem.
     * @return A double da distância da viagem.
     */
    public double getDistancia() {
        return distancia;
    }
    /**
     * Define a distância da viagem.
     * @param distancia A nova distância da viagem.
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
}