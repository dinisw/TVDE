import java.util.ArrayList;

public class EmpresaTVDE {
    private String nomeEmpresa;
    private ArrayList<Cliente> clientes;
    private ArrayList<Viagem> viagens;
    private ArrayList<Condutor> condutores;
    private ArrayList<Reserva> reservas;
    private ArrayList<Viatura> viaturas;

    public EmpresaTVDE(String nomeEmpresa, ArrayList<Cliente> clientes, ArrayList<Viagem> viagens, ArrayList<Condutor> condutores, ArrayList<Reserva> reservas, ArrayList<ArrayList<Viatura>> viaturas) {
        this.nomeEmpresa = nomeEmpresa;
        this.clientes = clientes;
        this.viagens = viagens;
        this.condutores = condutores;
        this.reservas = reservas;
        this.viaturas = new ArrayList<>();
    }

    // O CRUD DE VIATURAS

    //CREATE
    public boolean adicionarViatura(Viatura viatura){
        if(viatura.getMatricula() == null){
            return viaturas.add(viatura);
        }
        return false; // Pois já existe uma matrícula igual.
    }

    //READ:
    public Viatura procurarViatura(String matricula){
        for(Viatura viatura : viaturas){
            if(viatura.getMatricula().equals(matricula)){
                return viatura;
            }
            }
        return null;
    }

    //READ:

    public ArrayList<Viatura> consultarViaturas(String matricula){
        return viaturas;
    }

    //UPDATE:
    public boolean atualizarViatura(String matricula, String marca, String modelo, String cor, int anoDeFabrico){
        Viatura viatura = procurarViatura(matricula);
        if(viatura != null){
            viatura.setMarca(marca);
            viatura.setModelo(modelo);
            viatura.setCor(cor);
            viatura.setAnoDeFabrico(anoDeFabrico);
        }
        return false;
    }

    //DELETE:
    public boolean removerViatura(Viatura viatura){
        if(viatura.getMatricula() == null){
            return false;
        }
        for (Viagem viagem : viagens) {
            if(viagem.getViatura().getMatricula().equals(viatura.getMatricula())){
                System.out.println("Já existe uma viatura associada a esta viagem");
                return false;
            }

        }
        for (Reserva reserva : reservas) {
            if(reserva.getViatura().getMatricula().equalsIgnoreCase(viatura.getMatricula())){
                System.out.println("Já existe uma reserva associada a esta viagem");
                return false;
            }
        }
        return viaturas.remove(viatura);
    }
    public ArrayList<Viatura> getViaturas() {
        return viaturas;
    }


}
