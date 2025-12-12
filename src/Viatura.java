//Viaturas (ex: matrícula, marca, modelo, ano de fabrico, …);

public class Viatura {
    private String matricula;
    private String marca;
    private String modelo;
    private int anoDeFabrico;

    public Viatura() {
        anoDeFabrico = 0;
        modelo = "";
        marca = "";
        matricula = "";
    }

    public Viatura(int anoDeFabrico, String modelo, String marca, String matricula) {
        this.anoDeFabrico = anoDeFabrico;
        this.modelo = modelo;
        this.marca = marca;
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoDeFabrico() {
        return anoDeFabrico;
    }

    public void setAnoDeFabrico(int anoDeFabrico) {
        if (anoDeFabrico > 1900 && anoDeFabrico <= 2025) {
            this.anoDeFabrico = anoDeFabrico;
        } else {
            System.out.println("Erro");
        }
    }
}