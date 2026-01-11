import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class EmpresaTVDE {
    private String nomeEmpresa;
    private ArrayList<Cliente> clientes;
    private ArrayList<Viagem> viagens;
    private ArrayList<Condutor> condutores;
    private ArrayList<Reserva> reservas;
    private ArrayList<Viatura> viaturas;
    private final String CAMINHO_FICHEIRO_VIATURAS = "viaturas.txt";
    private final String CAMINHO_FICHEIRO_CLIENTES = "clientes.txt";
    private final String CAMINHO_FICHEIRO_CONDUTORES = "condutores.txt";
    private final String CAMINHO_FICHEIRO_LOGS_VIATURAS = "logsViaturas.txt";
    private final String CAMINHO_FICHEIRO_LOGS_CLIENTE = "logsClientes.txt";
    private final String CAMINHO_FICHEIRO_LOGS_CONDUTOR = "logsCondutor.txt";

    Scanner ler = new Scanner(System.in);

    public EmpresaTVDE() {
        clientes = new ArrayList<>();
        viagens = new ArrayList<>();
        condutores = new ArrayList<>();
        reservas = new ArrayList<>();
        viaturas = new ArrayList<>();
        nomeEmpresa = "";
    }

    public EmpresaTVDE(String nomeEmpresa, ArrayList<Cliente> clientes, ArrayList<Viagem> viagens, ArrayList<Condutor> condutores, ArrayList<Reserva> reservas, ArrayList<ArrayList<Viatura>> viaturas) {
        this.nomeEmpresa = nomeEmpresa;
        this.clientes = clientes;
        this.viagens = viagens;
        this.condutores = condutores;
        this.reservas = reservas;
        this.viaturas = new ArrayList<>();
    }


    /*CRUD LOGS*/
    public boolean adicionarLogsDeErros(String caminho, String erro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) {
            writer.write(LocalDateTime.now() + "->" + erro);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao gravar log: " + e.getMessage());
            return false;
        }
    }
    // O CRUD DE VIATURAS

    //CREATE
    public boolean adicionarViatura(Viatura viatura) {
        if (viatura.getMatricula() == null || viatura.getMatricula().isEmpty()) {
            return false;
        }
        boolean adicionou = viaturas.add(viatura);

        if (adicionou) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIATURAS, true))) {
                writer.write(viatura.paraFicheiro());
                writer.newLine();
                return true;
            } catch (IOException e) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_LOGS_VIATURAS, true))) {
                    writer.write(e.getMessage());
                    writer.newLine();
                    return true;
                } catch (IOException ex) {
                    System.out.println("Ocorreu um erro durante a inserção da viatura, tente novamente....");
                    return false;
                }
            }
        }
        return false;
    }

    //READ:
    public Viatura procurarViatura(String matricula) {
        for (Viatura viatura : viaturas) {
            if (viatura.getMatricula().equals(matricula)) {
                return viatura;
            }
        }
        return null;
    }

    //READ:
    public void listarViaturas() {
        if(viaturas.isEmpty()) {
            System.out.println("Não existe nenhuma viatura registada!");
        }
        for (Viatura viatura : viaturas) {
            System.out.println(viatura.toString());
        }
    }

    //UPDATE:
    public boolean atualizarViatura(String matricula, String marca, String modelo, String cor, int anoDeFabrico) {
        Viatura viatura = procurarViatura(matricula);
        if (viatura != null) {
            viatura.setMarca(marca);
            viatura.setModelo(modelo);
            viatura.setCor(cor);
            viatura.setAnoDeFabrico(anoDeFabrico);
        }
        return false;
    }

    //DELETE:
    public boolean removerViatura(Viatura viatura) {
        if (viatura.getMatricula() == null) {
            return false;
        }
        for (Viagem viagem : viagens) {
            if (viagem.getViatura().getMatricula().equals(viatura.getMatricula())) {
                System.out.println("Já existe uma viatura associada a esta viagem");
                return false;
            }

        }
        for (Reserva reserva : reservas) {
            if (reserva.getViatura().getMatricula().equalsIgnoreCase(viatura.getMatricula())) {
                System.out.println("Já existe uma reserva associada a esta viagem");
                return false;
            }
        }
        return viaturas.remove(viatura);
    }

    public ArrayList<Viatura> getViaturas() {
        return viaturas;
    }

    // CRUD de Cliente
    //CREATE
    public boolean adicionarCliente(Cliente cliente) {
        if (cliente == null || procurarCliente(cliente.getContribuinte()) != null) {
            return false;
        }

        boolean adicionou = clientes.add(cliente);

        if (adicionou) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CLIENTES, true))) {
                writer.write(cliente.paraFicheiro());
                writer.newLine();
                return true;
            } catch (IOException e) {
                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTE, "Erro gravação ficheiro: " + e.getMessage())
                return true;
            }
        }
        return false;
    }

    //READ
    public Cliente procurarCliente(int nif) {
        for (Cliente cliente : clientes) {
            if (cliente.getContribuinte() == nif)
                return cliente;
        }
        return null;
    }

    //READ
    public void listarClientes() {
        if (clientes == null){
            System.out.println("Não existe nenhum cliente registado");
        }else{
            for (Cliente cliente : clientes) {
                System.out.println(cliente.toString());
            }
        }
    }

    //UPDATE
    public boolean atualizarCliente(int nifOriginal, String novoNome, int novaIdade, String novoSexo, String novoEmail, int novoTelefone, String novaMorada, int novoCC) {
        Cliente cliente = procurarCliente(nifOriginal);
        if (cliente != null) {
            cliente.setNome(novoNome);
            cliente.setIdade(novaIdade);
            cliente.setSexo(novoSexo);
            cliente.setEmail(novoEmail);
            cliente.setTelefone(novoTelefone);
            cliente.setMorada(novaMorada);
            cliente.setCartaoDeCidadao(novoCC);
            return true;
        }
        return false;
    }

    //DELETE
    public boolean removerCliente(int nif) {
        Cliente cliente = procurarCliente(nif);
        if (cliente != null) {
            for (Viagem viagem : viagens)
                if (viagem.getCliente().getContribuinte() == nif)
                    return false;
            for (Reserva reserva : reservas)
                if (reserva.getCliente().getContribuinte() == nif)
                    return false;

            return clientes.remove(cliente);
        }
        return false;
    }


    // CRUD PARA CONDUTOR
    // CREATE

    public boolean adicionarCondutor(Condutor condutor) {
        if (condutor.getNome() == null || procurarCondutor(condutor.getContribuinte()) != null) {
            return false;
        }
        boolean adicionou = condutores.add(condutor);

        if (adicionou) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CONDUTORES))){
                writer.write((condutor.paraFicheiro());
                writer.newLine();
                return true;
            } catch (IOException e) {
                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTOR, "Erro gravação ficheiro: " + e.getMessage())
                return true;
            }
        }
        return false;
    }
    //READ
    public Condutor procurarCondutor(int nif) {
        for (Condutor condutor : condutores) {
            if (condutor.getContribuinte() == nif)
                return condutor;
        }
        return null;
    }

    public  ArrayList<Condutor> getCondutores() {
        return condutores;
    }

    //READ
    public void listarCondutores(){
        if (condutores == null) {
            System.out.println("Não existe nenhum condutor cadastrado!");
        }
        for (Condutor condutor : condutores) {
            System.out.println(condutor.toString());
        }
    }

    //UPDATE
    public boolean atualizarCondutor(int nifOriginal, String novoNome, int novaIdade, String novoSexo, String novoEmail, int novoTelefone, String novaMorada, String novaCarta) {
        Condutor condutor = procurarCondutor(nifOriginal);
        if (condutor != null) {
            condutor.setNome(novoNome);
            condutor.setIdade(novaIdade);
            condutor.setSexo(novoSexo);
            condutor.setEmail(novoEmail);
            condutor.setTelefone(novoTelefone);
            condutor.setMorada(novaMorada);
            condutor.setCartaDeConducao(novaCarta);
            return true;
        }
        return false;
    }

    //DELETE
    public boolean removerCondutor(int nif) {
        Condutor condutor = procurarCondutor(nif);
        if (condutor == null) {
            for (Viagem viagem : viagens)
                if (viagem.getCondutor().getContribuinte() == nif)
                    return false;
            return condutores.remove(condutor);
        }
        return false;
    }

    //Faturação total do condutor
    public double calcularFaturacaoTotal(int contribuinte, LocalDateTime inicio, LocalDateTime fim) {
        double total = 0;
        for (Viagem viagem : viagens) {
            if (viagem.getCondutor().getContribuinte() == contribuinte) {
                if (!viagem.getInicio().isBefore(inicio) && !viagem.getInicio().isBefore(fim)) {
                    total += viagem.getCustoViagem();
                }
            }
        }
        return total;
    }

    // CRUD DA RESERVA
    //CREATE
    public boolean adicionarReserva(Reserva reserva) {
        if (viaturaDisponivel(reserva.getViatura(), reserva.getDataHoraInicio())) {
            return reservas.add(reserva);
        }
        System.out.println("Impossível criar uma reserva!");
        return false;
    }

    //READ
    public boolean viaturaDisponivel(Viatura viatura, LocalDateTime data) {
        for (Reserva reserva : reservas) {
            if (reserva.getViatura() == viatura && reserva.getDataHoraInicio() == data) {
                return false;
            }
            for (Viagem viagem : viagens) {
                if (viagem.getViatura() == viatura && viagem.getInicio() == data) {
                    return false;
                }
            }
        }
        return true;
    }

    //READ
    public void listarReservas() {
        if(reservas == null) {
            System.out.println("Não existe nenhum reserva cadastrada!");
        }
        for (Reserva reserva : reservas) {
            System.out.println(reserva.toString());
        }
    }

    //UPDATE
    public boolean atualizarReserva(Cliente cliente, LocalDateTime dataAntiga, LocalDateTime dataAtualizado, String novoDestino) {
        for (Reserva reserva : reservas) {
            if (reserva.getCliente() == cliente && reserva.getDataHoraInicio() == dataAntiga) {
                if (viaturaDisponivel(reserva.getViatura(), dataAtualizado)) {
                    reserva.setDataHoraInicio(dataAtualizado);
                    reserva.setMoradaDestino(novoDestino);
                    return true;
                } else {
                    System.out.println("Viatura ocupada!");
                    return false;
                }
            }
        }
        return false;
    }

    //DELETE
    public boolean eliminarReserva(int contribuinte, LocalDateTime dataInicio) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if (reserva.getCliente().getContribuinte() == contribuinte && reserva.getDataHoraInicio() == dataInicio) {
                reservas.remove(i);
                System.out.println("Reserva removida com sucesso!");
                return true;
            }
        }
        System.out.println("Reserva impossível de ser encontrada!");
        return false;
    }


}
