import java.io.*;
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

    //region CRUD VIATURAS

    //region CREATE
    public String adicionarViatura(Viatura viatura) {
        if (viatura.getMatricula() == null || viatura.getMatricula().isEmpty()) {
            return "Para inserir uma viatura é obrigatório a inserção da matrícula";
        }
        boolean adicionou = viaturas.add(viatura);

        if (adicionou) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIATURAS, true))) {
                writer.write(viatura.paraFicheiro());
                writer.newLine();
                return "Viatura inserida com Sucesso!";
            } catch (IOException e) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_LOGS_VIATURAS, true))) {
                    writer.write("Erro ao adicionar viaturas: " + e.getMessage());
                    writer.newLine();
                    return "Ocorreu um erro durante a inserção da viatura, tente novamente....";
                } catch (IOException ex) {
                    return "";
                }
            }
        }
        return "";
    }
    //endregion

    //READ
    public ArrayList<Viatura> carregarViaturas() {
        viaturas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_VIATURAS))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 6) {
                    String matricula = dados[0];
                    String marca = dados[1];
                    String modelo = dados[2];
                    String cor = dados[4];
                    boolean disponivel = false;
                    try {
                        disponivel = Boolean.parseBoolean(dados[5]);
                    } catch (IllegalArgumentException e) {
                        disponivel = false;
                    }

                    int ano = 0;
                    try {
                        ano = Integer.parseInt(dados[3]);
                    } catch (NumberFormatException e) {
                        ano = 0;
                    }
                    Viatura viatura = new Viatura(matricula, marca, modelo, ano, cor, disponivel);
                    viaturas.add(viatura);
                }
            }
            return viaturas;
        } catch (IOException e) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_LOGS_VIATURAS, true))) {
                writer.write("Erro ao ler viaturas: " + e.getMessage());
                writer.newLine();
                return null;
            } catch (IOException ex) {
                //System.out.println("Erro crítico: Falha ao ler ficheiro e falha ao gravar log.");
            }
        }
        return null;
    }

    //DELETE
    public Boolean deletarViaturas(String matricula) {
        Viatura viaturaEncontrada = null;
        viaturaEncontrada = procurarViatura(matricula);

        if (viaturaEncontrada == null) {
            return false;
        }

        try{
            viaturas.remove(viaturaEncontrada);
            return true;
        } catch (Exception e) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_LOGS_VIATURAS, true))) {
                writer.write("Erro remover viatura: " + e.getMessage());
                writer.newLine();
            } catch (IOException ex) {
                return false;
            }
        }
        try {
            guardarAlteracoes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


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

    private void guardarAlteracoes() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIATURAS, false))) {

            for (Viatura v : viaturas) {
                writer.write(v.paraFicheiro());
                writer.newLine();
            }
        }
    }
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

    //endregion

    // CRUD de Cliente
    //CREATE
    public boolean adicionarCliente(Cliente cliente) {
        if (cliente == null || procurarCliente(cliente.getContribuinte()) != null)
            return false;

        boolean adicionou = clientes.add(cliente);
        if (adicionou) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CLIENTES, true))) {
                writer.write(cliente.paraFicheiro());
                writer.newLine();
                return true;
            } catch (IOException e) {
                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTE, e.getMessage());
                return true;
            }
        }
        return false;
    }

    public ArrayList<Cliente> carregarClientes(){
        clientes.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_CLIENTES))){
            String linha;
            while ((linha = reader.readLine()) != null)     {
                String[] dados = linha.split(";");
                if (dados.length >= 8) {
                    try {
                        String nome = dados[0];
                        int idade = Integer.parseInt(dados[1]);
                        String sexo = dados[2];
                        String email = dados[3];
                        int tel = Integer.parseInt(dados[4]);
                        String morada = dados[5];
                        int cc = Integer.parseInt(dados[6]);
                        int nif = Integer.parseInt(dados[7]);

                        Cliente cliente = new Cliente(nome, idade, sexo, email, tel, morada, cc, nif);
                        // Se tiver totalViagens e totalGasto, ler também:
                        if(dados.length >= 10) {
                            cliente.setTotalViagens(Integer.parseInt(dados[8]));
                            cliente.setTotalGasto(Double.parseDouble(dados[9]));
                        }
                        clientes.add(cliente);
                    } catch (NumberFormatException e) {
                        adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTE, "Erro linha: " + linha);
                    }
                }
            }
            return clientes;
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTE, "Erro leitura ficheiro: " + e.getMessage());
            return new ArrayList<>();
        }
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

            guardarAlteracoesClientes();
            return true;
        }
        return false;
    }

    //DELETE
    public boolean removerCliente(int nif) {
        Cliente cliente = procurarCliente(nif);
        if (cliente != null)
            return false;
        if (clientes.remove(cliente)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CLIENTES))){
                for (Cliente cliente2 : clientes) {
                    writer.write(cliente2.paraFicheiro());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTE, "Erro ao atualizar ficheiros" + e.getMessage());
            }
        }
       return false;
    }

    //método para reescrever os ficheiros
    private void guardarAlteracoesClientes () {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CLIENTES))){
            for (Cliente cliente : clientes) {
                writer.write(cliente.paraFicheiro());
                writer.newLine();
            }
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTE, "Erro ao reescrever clientes: " + e.getMessage());
        }
    }


    // CRUD PARA CONDUTOR
    // CREATE

    public boolean adicionarCondutor(Condutor condutor) {
        if (condutor == null || procurarCondutor(condutor.getContribuinte()) != null) return false;

        if (condutores.add(condutor)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CONDUTORES, true))) {
                writer.write(condutor.paraFicheiro());
                writer.newLine();
                return true;
            } catch (IOException e) {
                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTOR, e.getMessage());
                return true;
            }
        }
        return false;
    }

    public ArrayList<Condutor> carregarCondutores() {
        condutores.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_CONDUTORES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 9) {
                    try {
                        String nome = dados[0];
                        int idade = Integer.parseInt(dados[1]);
                        // ... ler restantes campos ...
                        int nif = Integer.parseInt(dados[7]);
                        String carta = dados[8];

                        Condutor c = new Condutor(nome, idade, dados[2], dados[3], Integer.parseInt(dados[4]), dados[5], Integer.parseInt(dados[6]), nif);
                        c.setCartaDeConducao(carta);
                        condutores.add(c);
                    } catch (Exception e) {
                        adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTOR, "Erro linha: " + linha);
                    }
                }
            }
            return condutores;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }


    //READ
    public Condutor procurarCondutor(int nif) {
        for (Condutor condutor : condutores) {
            if (condutor.getContribuinte() == nif)
                return condutor;
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

            guardarAlteracoesCondutores();
            return true;
        }
        return false;
    }

    //DELETE
    public boolean removerCondutor(int nif) {
        Condutor condutor = procurarCondutor(nif);
        if (condutor == null) return false;

        if (condutores.remove(condutor)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CONDUTORES, false))) {
                for (Condutor cond : condutores) {
                    writer.write(cond.paraFicheiro());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTOR, e.getMessage());
            }
        }
        return false;
    }

    //método para reescrever o ficheiro de condutores
    private void guardarAlteracoesCondutores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CONDUTORES, false))){
            for (Condutor condutor : condutores) {
                writer.write(condutor.paraFicheiro());
                writer.newLine();
            }
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTOR, "Erro ao reescrever condutores: " + e.getMessage());
        }
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

    //getter gerais
    public ArrayList<Cliente> getClientes() { return clientes; }
    public ArrayList<Condutor> getCondutores() { return condutores; }

}
