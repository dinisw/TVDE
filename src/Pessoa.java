
public class Pessoa {
    private int cc;
    private String nome;
    private int idSegSocial;
    private int idFinancas;
    private String morada;
    private int telemovel;

    public Pessoa() {
        cc = 0;
        nome = "";
        idSegSocial = 0;
        idFinancas = 0;
        morada = "";
        telemovel = 0;
    }

    /**
     *
     * @param cc
     * @param nome
     * @param idSegSocial
     * @param idFinancas
     * @param morada
     * @param telemovel
     */
    public Pessoa(int cc, String nome, int idSegSocial, int idFinancas, String morada, int telemovel) {
        this.cc = cc;
        this.nome = nome;
        this.idSegSocial = idSegSocial;
        this.idFinancas = idFinancas;
        this.morada = morada;
        this.telemovel = telemovel;
    }

    public int getCc() {
        return cc;
    }

    /**
     * 
     * @param cc
     */
    public void setCc(int cc) {
        this.cc = cc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdSegSocial() {
        return idSegSocial;
    }

    public void setIdSegSocial(int idSegSocial) {
        this.idSegSocial = idSegSocial;
    }

    public int getIdFinancas() {
        return idFinancas;
    }

    public void setIdFinancas(int idFinancas) {
        this.idFinancas = idFinancas;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }
}
