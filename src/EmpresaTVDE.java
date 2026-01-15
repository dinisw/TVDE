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
        if (viaturas.add(viatura)) {
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
                    try {
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
                    } catch (NumberFormatException e) {
                        adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIATURAS, "Erro linha: " + linha);
                        return new ArrayList<>();
                    }
                }
            }
            if(!viaturas.isEmpty()) return viaturas;
            else return new ArrayList<>();
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIATURAS, "Erro leitura ficheiro: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean atualizarViatura(String matricula, String novaMarca, String novoModelo, int novoAnoDeFabrico, String novaCor, boolean novoStatus) {
        Viatura viatura = procurarViatura(matricula);
        if (viatura != null) {
            viatura.setMarca(novaMarca);
            viatura.setModelo(novoModelo);
            viatura.setAnoDeFabrico(novoAnoDeFabrico);
            viatura.setCor(novaCor);
            //viatura.setStatus(novoStatus);

            guardarAlteracoesViaturas();
            return true;
        }
        return false;
    }

    //DELETE
    public Boolean removerViaturas(String matricula) {
        Viatura viatura = procurarViatura(matricula);
        if (viatura == null)
            return false;
        if (viaturas.remove(viatura)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIATURAS))) {
                for (Viatura viatura2 : viaturas) {
                    writer.write(viatura2.paraFicheiro());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIATURAS, "Erro ao atualizar ficheiros" + e.getMessage());
            }
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

    private void guardarAlteracoesViaturas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIATURAS))) {
            for (Viatura viatura : viaturas) {
                writer.write(viatura.paraFicheiro());
                writer.newLine();
            }
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIATURAS, "Erro ao reescrever viaturas: " + e.getMessage());
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
                        return new ArrayList<>();
                    }
                }
            }
            if(!clientes.isEmpty()) return clientes;
            else return new ArrayList<>();
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
    public boolean atualizarCliente(String novoNome, int novaIdade, String novoSexo, String novoEmail, int novoTelefone, String novaMorada, int novoCC, int nifOriginal) {
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
                        String carta = dados[8];


                        Condutor condutor = new Condutor(nome, idade, sexo, email, tel, morada, cc, carta, nif);
                        condutor.setCartaDeConducao(carta);
                        condutores.add(condutor);
                    } catch (Exception e) {
                        adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTOR, "Erro linha: " + linha);
                        return new ArrayList<>();
                    }
                }
            }
            if(!condutores.isEmpty()) return condutores;
            else return new ArrayList<>();
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTOR, "Erro leitura ficheiro: " + e.getMessage());
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
    public boolean atualizarCondutor(String novoNome, int novaIdade, String novoSexo, String novoEmail, int novoTelefone, String novaMorada, String novaCarta, int novoCartaoDeCidadao, int nifOriginal) {
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
        try(BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_CONDUTORES))) {
            String linha;
            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(",");

                if(dados.length >=8){
                    int contribuinteLido = Integer.parseInt(dados[0]);
                    LocalDateTime data = LocalDateTime.parse(dados[3]);
                    if (contribuinteLido == contribuinte && data.isBefore(inicio) && data.isAfter(fim)) {
                        total += Double.parseDouble(dados[7]);
                    }
                }
            }
        }catch(IOException e){
            System.out.println("Erro ao calcular faturação total: " + e.getMessage());
        }
        return total;
    }

    // CRUD DA RESERVA
    //CREATE
    /*public boolean adicionarReserva(Reserva reserva) {
        if (reserva == null || (procurarNifReserva(reserva.getCliente().getContribuinte()) != null && !reserva.getViatura().isStatus()))
            return false;
        if (reservas.add(reserva)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_RESERVAS, true))) {
                writer.write(reserva.paraFicheiro());
                writer.newLine();
                return true;
            } catch (IOException e) {
               adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIATURAS, e.getMessage());
               return true;
            }
        }
        return false;
    } */

    //READ
    public ArrayList<Reserva> carregarReservas() {
        reservas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_RESERVAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 6) {
                    int contribuinte = Integer.parseInt(dados[0]);
                    String matricula = dados[1];
                    LocalDateTime datahora = LocalDateTime.parse(dados[2]);
                    String origem = dados[3];
                    String destino = dados[4];
                    double kms = Double.parseDouble(dados[5]);

                    Cliente cliente = procurarNifCliente(contribuinte);
                    Viatura viatura = procurarViatura(matricula);

                    if (cliente != null && viatura != null) {
                        Reserva reserva = new Reserva(cliente,viatura,datahora,origem,destino,kms);
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

    public Boolean removerReservas(int contribuinte) {
        Reserva reserva = procurarNifReserva(contribuinte);
        if (reserva == null)
            return false;
        if (reservas.remove(reserva)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_RESERVAS))) {
                for (Reserva reserva2 : reservas) {
                    writer.write(reserva2.paraFicheiro());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIATURAS, "Erro ao atualizar ficheiros" + e.getMessage());
            }
        }
        return false;
    }

    public Reserva procurarNifReserva(int contribuinte) {
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().getContribuinte() == contribuinte) {
                return reserva;
            }
        }
        return null;
    }

    public Reserva procurarMatriculaReserva(String matricula) {
        for (Reserva reserva : reservas) {
            if (reserva.getViatura().getMatricula() == matricula) {
                return reserva;
            }
        }
        return null;
    }

    private void guardarAlteracoesReservas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_RESERVAS))) {
            for (Reserva reserva : reservas) {
                writer.write(reserva.paraFicheiro());
                writer.newLine();
            }
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_RESERVAS, "Erro ao reescrever reservas: " + e.getMessage());
        }
    }

    public boolean adicionarViagem(Viagem viagem) {
        for (Viagem viagem1 : viagens) {
            if (viagem.getViatura().getMatricula().equals(viagem1.getViatura().getMatricula()) && viagem.getInicio().equals(viagem1.getInicio())) {
                return false;
            }
        }
        boolean adicionar = viagens.add(viagem);
        if (adicionar) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIAGENS,true))) {
                writer.write(viagem.paraFicheiro());
                writer.newLine();
                System.out.println("Viagem adicionado com sucesso!");
                return true;
            } catch (IOException e) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_LOGS_VIAGENS))) {
                    writer.write("Erro ao adicionar uma viagem" + e.getMessage());
                    writer.newLine();
                    System.out.println("Ocorreu um erro ao adicionar uma viagem, tente novamente.");
                } catch (IOException ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public Viagem procurarViagens(int contribuinte) {
        for (Viagem viagem : viagens) {
            if (viagem.getCliente().getContribuinte() == contribuinte) {
                return viagem;
            }
        }
        return null;
    }

    private void guardarAlteracoesViagens() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIAGENS))) {
            for (Viagem viagem : viagens) {
                writer.write(viagem.paraFicheiro());
                writer.newLine();
            }
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIAGENS, "Erro ao reescrever viagens: " + e.getMessage());
        }
    }

    public boolean removerViagem(int contribuinte, LocalDateTime inicio) {
        Viagem viagem = procurarViagens(contribuinte);
        if (viagem == null)
            return false;
        if (viagens.remove(viagem)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIAGENS))) {
                for (Viagem viagem2 : viagens) {
                    writer.write(viagem.paraFicheiro());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIATURAS, "Erro ao atualizar ficheiros" + e.getMessage());
            }
        }
        return false;
    }
    //Pesquisar viagens de um cliente num intervalo de data dada pelo cliente
    public ArrayList<Viagem> pesquisarViagemClienteData(int contribuinte, LocalDateTime inicio, LocalDateTime fim) {
        ArrayList<Viagem> viagemEncontrada = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_VIAGENS))){
            String linha;

            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                if(dados.length >= 6) {
                    int contribuinteLido =  Integer.parseInt(dados[0]);
                    if(contribuinteLido == contribuinte) {
                        LocalDateTime dataViagem = LocalDateTime.parse(dados[1]);
                        if(dataViagem.isBefore(inicio) && dataViagem.isAfter(fim)) {
                            Cliente cliente = procurarNifCliente(contribuinte);
                            Viatura viatura = procurarViatura(dados[3]);
                            Condutor condutor = procurarNifCondutor(Integer.parseInt(dados[2]));
                            if(cliente != null && viatura != null && condutor != null) {
                                Viagem viagem = new Viagem();
                                viagem.setCliente(cliente);
                                viagem.setCondutor(condutor);
                                viagem.setViatura(viatura);
                                viagem.setFim(fim);
                                viagem.setInicio(inicio);
                                viagem.setMoradaOrigem(dados[5]);
                                viagem.setMoradaDestino(dados[4]);
                                viagem.setCustoViagem(Double.parseDouble(dados[7]));
                                viagemEncontrada.add(viagem);
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Erro ao ler ficheiro: " + ex.getMessage());
        }
        return viagemEncontrada;
    }

    public double calculaDistanciaMedia(LocalDateTime inicio, LocalDateTime fim) {
        double media = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_VIAGENS))){
            double kMS=0;
            int quantidadeViagens = 0;
            String linha;
            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                if(dados.length >= 6) {
                    LocalDateTime dataViagem = LocalDateTime.parse(dados[0]);

                    if(!dataViagem.isBefore(inicio) && !dataViagem.isAfter(fim)) {
                        kMS = Double.parseDouble(dados[1]);
                        quantidadeViagens++;
                    }
                }
                media = kMS/quantidadeViagens;
            }
        } catch (IOException ex) {
            System.out.println("Erro ao ler ficheiro: " + ex.getMessage());
        }
        return media;
    }

    public String destinoPopular(LocalDateTime inicio, LocalDateTime fim) {
        ArrayList<String> destinos = new ArrayList<>();

        try (BufferedReader viagem = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_VIAGENS))){
            String linha;
            while ((linha = viagem.readLine()) != null){
                String[] dados = linha.split(";");
                if(dados.length >= 6) {
                    LocalDateTime dataViagem = LocalDateTime.parse(dados[1]);
                    if(dataViagem.isBefore(inicio) && !dataViagem.isAfter(fim)) {
                        destinos.add(dados[6]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler ficheiro da viagem: " + e.getMessage());
        }

        try(BufferedReader reserva = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_RESERVAS))){
            String linha;
            while ((linha = reserva.readLine()) != null){
                String[] dados = linha.split(";");
                if(dados.length >= 6) {
                    LocalDateTime dataReserva = LocalDateTime.parse(dados[3]);
                    if(dataReserva.isBefore(inicio) && !dataReserva.isAfter(fim)) {
                        destinos.add(dados[6]);
                    }
                }
            }
        }catch (IOException ex) {
            System.out.println("Erro ao ler ficheiro da reserva: " + ex.getMessage());
        }
        if(destinos.isEmpty()) {
            return "Não existe reservas nem viagens entre as datas inseridas!";
        }
        String destino = "";
        int pedidos = 0;
        int maxPedidos = 0;
        for(String destino1 : destinos){
            for(String destino2 : destinos){
                if(destino1.equalsIgnoreCase(destino2)){
                    pedidos++;
                }
            }
            if (pedidos > maxPedidos){
                maxPedidos = pedidos;
                destino = destino1;
            }
        }
        return "O destino mais popular é :" + destino + " pedido " + pedidos + "vezes.";
    }

    public ArrayList<Cliente> clientesPorDistancia(double distanciaMinima, double distanciaMaxima){
        ArrayList<Cliente> clientesEncontrados = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_CLIENTES))) {
            String linha;
            while ((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                if(dados.length >= 6) {
                    double distancia =  Double.parseDouble(dados[1]);

                    if(distancia >= distanciaMinima && distancia <= distanciaMaxima){
                        int nifCliente = Integer.parseInt(dados[2]);
                        Cliente cliente = procurarNifCliente(nifCliente);
                        if(cliente != null){
                            clientesEncontrados.add(cliente);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clientesEncontrados;
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
