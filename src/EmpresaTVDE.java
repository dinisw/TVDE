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
    private final String CAMINHO_FICHEIRO_LOGS_VIATURAS = "logsViaturas.txt";

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
            writer.write(erro);
            writer.newLine();
            return true;
        } catch (IOException e) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(e.getMessage(), true))) {
                writer.write(erro);
                writer.newLine();
                return true;
            } catch (IOException ex) {
                return false;
            }
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
        System.out.println("Indique o seu nome:");
        String nome = ler.nextLine();
        System.out.println("Indique a sua idade:");
        int idade = Integer.parseInt(ler.nextLine());
        System.out.println("Indique o seu genero:");
        String sexo = ler.nextLine();
        System.out.println("Indique o seu email:");
        String email = ler.nextLine();
        System.out.println("Indique o seu número de telemóvel:");
        int telefone = Integer.parseInt(ler.nextLine());
        System.out.println("Indique a sua morada:");
        String morada = ler.nextLine();
        System.out.println("Indique o seu número de cartão de cidadão (sem os últimos 4 dígitos):");
        int cartaoDeCidadao = Integer.parseInt(ler.nextLine());
        System.out.println("Indique o seu número de contribuinte.");
        int contribuinte = Integer.parseInt(ler.nextLine());
        if (procurarCliente(cliente.getContribuinte()) == null) {
            return clientes.add(cliente);
        }
        return false;
    }

    //READ
    public Cliente procurarCliente(int contribuinte) {
        for (Cliente cliente : clientes) {
            if (cliente.getContribuinte() == contribuinte) {
                return cliente;
            }
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
    public boolean atualizarCliente(String nome, int idade, String sexo, String email, int telefone, String morada, int cartaoCidadao, int contribuinte) {
        Cliente cliente = procurarCliente(contribuinte);
        {
            if (cliente != null) {
                cliente.setNome(nome);
                cliente.setIdade(idade);
                cliente.setSexo(sexo);
                cliente.setEmail(email);
                cliente.setTelefone(telefone);
                cliente.setMorada(morada);
                cliente.setCartaoDeCidadao(cartaoCidadao);
                cliente.setContribuinte(contribuinte);
                return true;
            }
        }
        return false;
    }

    //DELETE
    public boolean removerCliente(int contribuinte) {
        Cliente cliente = procurarCliente(contribuinte);
        if (cliente != null) {
            for (Viagem viagem : viagens) {
                if (viagem.getCliente().getContribuinte() == contribuinte) {
                    System.out.println("O cliente pelo qual quer remover encontra-se com uma viagem registada.");
                    return false;
                }
            }
            for (Reserva reserva : reservas) {
                if (reserva.getCliente().getContribuinte() == contribuinte) {
                    System.out.println("O Cliente pelo qual quer remover encontra-se com uma reserva registada.");
                    return false;
                }
            }
        }
        return clientes.remove(cliente);
    }

    // CRUD PARA CONDUTOR
    // CREATE

    public boolean adicionarCondutor(Condutor condutor) {
        if (condutor.getNome() == null) {
            System.out.println("Não existe nenhum condutor cadastrado");
            return false;
        }
        return condutores.add(condutor);
    }

    public Condutor procurarCondutor(int contribuinte) {
        for (Condutor condutor : condutores) {
            if (condutor.getContribuinte() == contribuinte) {
                return condutor;
            }
        }
        return null;
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
    public boolean atualizarCondutor(String nome, int idade, String sexo, String email, int telefone, String morada, int cartaoCidadao, int contribuinte, String cartaDeConducao) {
        Condutor condutor = procurarCondutor(contribuinte);
        if (condutor != null) {
            condutor.setNome(nome);
            condutor.setIdade(idade);
            condutor.setSexo(sexo);
            condutor.setEmail(email);
            condutor.setTelefone(telefone);
            condutor.setMorada(morada);
            condutor.setCartaoDeCidadao(cartaoCidadao);
            condutor.setContribuinte(contribuinte);
            condutor.setCartaDeConducao(cartaDeConducao);
            return true;
        }
        return false;
    }

    //DELETE
    public boolean removerCondutor(int contribuinte) {
        Condutor condutor = procurarCondutor(contribuinte);
        if (condutor == null) {
            return false;
        }
        for (Viagem viagem : viagens) {
            if (viagem.getCondutor().getContribuinte() == contribuinte) {
                System.out.println("Já existe um condutor associada a esta viagem");
                return false;
            }
        }
        return condutores.remove(condutor);
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
