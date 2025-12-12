import java.util.Date;

public class Reserva {
    private Date dataHora;
    private String moradaOrigem;
    private String moradaDestino;
    private double kms;

    public Reserva() {
        dataHora = new Date();
        moradaOrigem = "";
        moradaDestino = "";
        kms = 0;
    }

    public Reserva(double kms, String moradaDestino, String moradaOrigem, Date dataHora) {
        this.kms = kms;
        this.moradaDestino = moradaDestino;
        this.moradaOrigem = moradaOrigem;
        this.dataHora = dataHora;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
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

    public double getkms() {
        return kms;
    }

    public void setkms(double kms) {
        this.kms = kms;
    }
}
