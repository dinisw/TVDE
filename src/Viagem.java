import java.time.LocalDate;
import java.util.Date;

public class Viagem {
    private Date dataInicio;
    private Date dataFim;
    private String moradaOrigem;
    private String moradaDestino;
    private double kms;
    private double custoViagem;

    public Viagem() {
        dataInicio = new Date();
        dataFim = new Date();
        moradaOrigem = "";
        moradaDestino = "";
        kms = 0;
        custoViagem = 0;

    }

    public Viagem(Date dataInicio, Date dataFim, String moradaOrigem, String moradaDestino, double kms, double custoViagem) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.moradaOrigem = moradaOrigem;
        this.moradaDestino = moradaDestino;
        this.kms = kms;
        this.custoViagem = custoViagem;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
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

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    public double getCustoViagem() {
        return custoViagem;
    }

    public void setCustoViagem(double custoViagem) {
        this.custoViagem = custoViagem;
    }
}
