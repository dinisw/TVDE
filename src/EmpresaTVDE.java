import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
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
    private final String CAMINHO_FICHEIRO_RESERVAS = "reservas.txt";
    private final String CAMINHO_FICHEIRO_VIAGENS = "viagens.txt";

    private final String CAMINHO_FICHEIRO_LOGS_VIATURAS = "logsViaturas.txt";
    private final String CAMINHO_FICHEIRO_LOGS_CLIENTE = "logsClientes.txt";
    private final String CAMINHO_FICHEIRO_LOGS_CONDUTOR = "logsCondutor.txt";
    private final String CAMINHO_FICHEIRO_LOGS_RESERVAS = "logsReservas.txt";
    private final String CAMINHO_FICHEIRO_LOGS_VIAGENS = "logsViagens.txt";

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

        try {
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

    private void guardarAlteracoes() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIATURAS, false))) {

            for (Viatura v : viaturas) {
                writer.write(v.paraFicheiro());
                writer.newLine();
            }
        }
    }
    //endregion

    // CRUD de Cliente
    //CREATE
    public boolean adicionarCliente(Cliente cliente) {
        if (cliente == null || procurarNifCliente(cliente.getContribuinte()) != null)
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

    public ArrayList<Cliente> carregarClientes() {
        clientes.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_CLIENTES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
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
                        if (dados.length >= 10) {
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
    public Cliente procurarNifCliente(int nif) {
        for (Cliente cliente : clientes) {
            if (cliente.getContribuinte() == nif)
                return cliente;
        }
        return null;
    }

    public Cliente procurarCartaoDeCidadaoCliente(int cc) {
        for (Cliente cliente : clientes) {
            if (cliente.getCartaoDeCidadao() == cc)
                return cliente;
        }
        return null;
    }

    //READ
    public void listarClientes() {
        if (clientes == null) {
            System.out.println("Não existe nenhum cliente registado");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente.toString());
            }
        }
    }


    //UPDATE
    public boolean atualizarCliente(String novoNome, int novaIdade, String novoSexo, String novoEmail, int novoTelefone, String novaMorada, int novoCC,int nifOriginal) {
        Cliente cliente = procurarNifCliente(nifOriginal);
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
        Cliente cliente = procurarNifCliente(nif);
        if (cliente == null)
            return false;
        if (clientes.remove(cliente)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CLIENTES))) {
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
    private void guardarAlteracoesClientes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CLIENTES))) {
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
        if (condutor == null || procurarNifCondutor(condutor.getContribuinte()) != null) return false;

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
                        String sexo = dados[2];
                        String email = dados[3];
                        int tel = Integer.parseInt(dados[4]);
                        String morada = dados[5];
                        int cc = Integer.parseInt(dados[6]);
                        int nif = Integer.parseInt(dados[7]);
                        String carta = dados [8];



                        Condutor condutor = new Condutor(nome, idade, sexo, email, tel, morada, cc, carta, nif);
                        condutor.setCartaDeConducao(carta);
                        condutores.add(condutor);
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
    public Condutor procurarNifCondutor(int nif) {
        for (Condutor condutor : condutores) {
            if (condutor.getContribuinte() == nif)
                return condutor;
        }
        return null;
    }

    public Condutor procurarCartaoDeCidadaoCondutor(int cc) {
        for (Condutor condutor : condutores) {
            if (condutor.getCartaoDeCidadao() == cc)
                return condutor;
        }
        return null;
    }

    public Condutor procurarCartaDeConducaoCondutor(String carta) {
        for (Condutor condutor : condutores) {
            if (Objects.equals(condutor.getCartaDeConducao(), carta))
                return condutor;
        }
        return null;
    }

    //READ
    public void listarCondutores() {
        if (condutores == null) {
            System.out.println("Não existe nenhum condutor cadastrado!");
        }
        for (Condutor condutor : condutores) {
            System.out.println(condutor.toString());
        }
    }

    //UPDATE
    public boolean atualizarCondutor(String novoNome, int novaIdade, String novoSexo, String novoEmail, int novoTelefone, String novaMorada, String novaCarta,int novoCartaoDeCidadao,int nifOriginal) {
        Condutor condutor = procurarNifCondutor(nifOriginal);
        if (condutor != null) {
            condutor.setNome(novoNome);
            condutor.setIdade(novaIdade);
            condutor.setSexo(novoSexo);
            condutor.setEmail(novoEmail);
            condutor.setTelefone(novoTelefone);
            condutor.setMorada(novaMorada);
            condutor.setCartaDeConducao(novaCarta);
            condutor.setCartaoDeCidadao(novoCartaoDeCidadao);

            guardarAlteracoesCondutores();
            return true;
        }
        return false;
    }

    //DELETE
    public boolean removerCondutor(int nif) {
        Condutor condutor = procurarNifCondutor(nif);
        if (condutor == null) return false;

        if (condutores.remove(condutor)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CONDUTORES, false))) {
                for (Condutor condutor2 : condutores) {
                    writer.write(condutor2.paraFicheiro());
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CONDUTORES, false))) {
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
        if (reserva.getViatura().getMatricula() == null && reserva.getDataHoraInicio() == null) {
            return false;
        }
        boolean adicionar = reservas.add(reserva);
        if (adicionar) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_RESERVAS, true))) {
                writer.write(reserva.paraFicheiro());
                writer.newLine();
                System.out.println("Reserva adicionado com sucesso!");
                return true;
            } catch (IOException e) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_LOGS_RESERVAS))) {
                    writer.write("Erro ao adicionar reservas: " + e.getMessage());
                    writer.newLine();
                    System.out.println("Erro ao inserir reservas!");
                } catch (IOException ex) {
                    return false;
                }
            }
        }
        return false;
    }

    //READ
    public ArrayList<Reserva> reservas() {
        reservas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_RESERVAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 6) {
                    int contribuinte = Integer.parseInt(dados[0]);
                    String matricula = dados[1];
                    LocalDateTime data = LocalDateTime.parse(dados[2]);
                    String origem = dados[3];
                    String destino = dados[4];
                    double kms = Double.parseDouble(dados[5]);

                    Cliente cliente = procurarNifCliente(contribuinte);
                    Viatura viatura = procurarViatura(matricula);

                    if(cliente != null && viatura != null) {
                        Reserva reserva = new Reserva();
                        reservas.add(reserva);
                    }
                    }
                }
            return reservas;
        } catch (IOException e) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_LOGS_RESERVAS, true))) {
                writer.write("Erro ao ler as reservas: " + e.getMessage());
                writer.newLine();
                return null;
            } catch (IOException ex) {
                System.out.println("Erro crítico: Falha ao ler ficheiro e falha ao gravar log.");
            }
        }
        return null;
    }

    public boolean eliminarReservas(int contribuinte) {
        Reserva reservaEncontrada = null;
        reservaEncontrada = procurarReserva(contribuinte);
        if (reservaEncontrada == null) {
            return false;
        }
        try {
            reservas.remove(reservaEncontrada);
            return true;
        } catch (Exception e) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_LOGS_RESERVAS, true))) {
                writer.write("Erro remover reserva: " + e.getMessage());
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

    public Reserva procurarReserva(int contribuinte) {
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().getContribuinte() == contribuinte) {
                return reserva;
            }
        }
        return null;
    }public void guardarReservas() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_RESERVAS, false))) {
            for (Reserva r : reservas) {
                writer.write(r.paraFicheiro());
                writer.newLine();
            }
        }
    }

    public boolean adicionarViagem(Viagem viagem) {
        for(Viagem v : viagens) {}
        if(viagem.getViatura().getMatricula().equals(viagem.getViatura().getMatricula()) && viagem.getInicio().equals(viagem.getInicio())) {
            return false;
        }
        boolean adicionar = viagens.add(viagem);
        if (adicionar) {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIAGENS))){
                writer.write(viagem.paraFicheiro());
                writer.newLine();
                System.out.println("Viagem adicionado com sucesso!");
                return true;
            }catch (IOException e) {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter (CAMINHO_FICHEIRO_LOGS_VIAGENS))){
                    writer.write("Erro ao adicionar uma viagem" + e.getMessage());
                    writer.newLine();
                    System.out.println("Ocorreu um erro ao adicionar uma viagem, tente novamente.");
                }catch (IOException ex){
                    return false;
                }
            }
        }
        return false;
    }
    public ArrayList<Viagem> procurarViagens(int contribuinte) {
        viagens.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_VIAGENS))){
            String linha;

            while ((linha = reader.readLine()) != null){
                String[] dados = linha.split(",");
                if(dados.length >= 6) {
                    Condutor condutor = procurarNifCondutor(Integer.parseInt(dados[0]));
                    Cliente cliente = procurarNifCliente(Integer.parseInt(dados[1]));
                    Viatura viatura = procurarViatura(dados[2]);
                    if(condutor != null && cliente != null && viatura != null) {
                        LocalDateTime inicio = LocalDateTime.parse(dados[3]);
                        LocalDateTime fim = LocalDateTime.parse(dados[4]);
                        String moradaOrigem = dados[5];
                        String moradaDestino = dados[6];
                        double custoViagem = Double.parseDouble(dados[7]);
                        Viagem viagem = new Viagem();
                        viagens.add(viagem);
                    }
                }
                return viagens;
            }
        } catch (IOException e) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_LOGS_VIAGENS))){
                writer.write("Erro ao ler viagens: " + e.getMessage());
                writer.newLine();
                return null;
        } catch (IOException ex) {
                System.out.println("Erro crítico: Falha ao ler o ficheiro viagens.txt");
            }
        }
        return null;
    }

    public boolean eliminarViagem(int contribuinte, LocalDateTime inicio) {
        ArrayList<Viagem> viagemEncontrada = null;
        viagemEncontrada = procurarViagens(contribuinte);

        if(viagemEncontrada == null) {
            return false;
        }

        try {
            viagens.remove(viagemEncontrada);
            return true;
        } catch (Exception e) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIAGENS))){
                writer.write("Erro ao remover a viagem " + e.getMessage());
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

    //getter gerais
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Condutor> getCondutores() {
        return condutores;
    }

    public ArrayList<Viatura> getViaturas() {
        return viaturas;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public ArrayList<Viagem> getViagens() {
        return viagens;
    }

}
