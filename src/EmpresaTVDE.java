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
    private final String CAMINHO_FICHEIRO_LOGS_CLIENTES = "logsClientes.txt";
    private final String CAMINHO_FICHEIRO_LOGS_CONDUTORES = "logsCondutor.txt";
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

    //CREATE
    public boolean adicionarViatura(Viatura viatura) {
        if (viatura == null || procurarViatura(viatura.getMatricula()) != null)
            return false;

        boolean adicionou = viaturas.add(viatura);
        if (adicionou) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIATURAS, true))) {
                writer.write(viatura.paraFicheiro());
                writer.newLine();
                return true;
            } catch (IOException e) {
                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIATURAS, e.getMessage());
                return true;
            }
        }
        return false;
    } // Completo
    //READ
    public ArrayList<Viatura> carregarViaturas() {
        viaturas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_VIATURAS))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 6) {
                    try {
                        String matricula = dados[0].trim();
                        String marca = dados[1].trim();
                        String modelo = dados[2].trim();
                        int ano = 0;
                        try {
                            ano = Integer.parseInt(dados[3].trim());
                        } catch (NumberFormatException e) {
                            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIATURAS, "Ano inválido na linha: " + linha);
                            ano = 0;
                        }
                        String cor = dados[4].trim();
                        boolean disponivel = Boolean.parseBoolean(dados[5].trim());

                        Viatura viatura = new Viatura(matricula, marca, modelo, ano, cor, disponivel);
                        viaturas.add(viatura);
                    } catch (Exception e) {
                        adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIATURAS, "Erro linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIATURAS, "Erro leitura ficheiro: " + e.getMessage());
        }
        return viaturas;
    } //Completo Dinis
    //UPDATE na main!

    //DELETE
    public boolean removerViaturas(String matricula) {
        Viatura viatura = procurarViatura(matricula);
        if (viatura != null && viaturas.remove(viatura)) {
            guardarAlteracoesViaturas();
            return true;
        }
        return false;
    } //Completo Dinis

    public Viatura procurarViatura(String matricula) {
        for (Viatura viatura : viaturas) {
            if (viatura.getMatricula() != null && viatura.getMatricula().equals(matricula)) {
                return viatura;
            }
        }
        return null;
    } //Completo Dinis

    public void guardarAlteracoesViaturas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIATURAS))) {
            for (Viatura viatura : viaturas) {
                writer.write(viatura.paraFicheiro());
                writer.newLine();
            }
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIATURAS, "Erro ao reescrever viaturas: " + e.getMessage());
        }
    } //Completo Dinis
    //endregion

    //region CRUD CLIENTES

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
                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTES, e.getMessage());
                return true;
            }
        }
        return false;
    } //Completo
    //READ
    public ArrayList<Cliente> carregarClientes() {
        clientes.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_CLIENTES))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 8) {
                    try {
                        String nome = dados[0].trim();
                        int idade = 0;
                        try {
                            idade = Integer.parseInt(dados[1].trim());
                        } catch (NumberFormatException e) {
                            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTES, "Idade inválido na linha: " + linha);
                            idade = 0;
                        }
                        String sexo = dados[2].trim();
                        String email = dados[3].trim();
                        int tel = 0;
                        try {
                            tel = Integer.parseInt(dados[4].trim());
                        } catch (NumberFormatException e) {
                            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTES, "Número de telemóvel inválido na linha: " + linha);
                            tel = 0;
                        }
                        String morada = dados[5].trim();
                        int cc = 0;
                        try {
                            cc = Integer.parseInt(dados[6].trim());
                        } catch (NumberFormatException e) {
                            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTES, "Cartão de cidadão inválido na linha: " + linha);
                            cc = 0;
                        }
                        int nif = 0;
                        try {
                            nif = Integer.parseInt(dados[7].trim());
                        } catch (NumberFormatException e) {
                            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTES, "NIF inválido na linha: " + linha);
                            nif = 0;
                        }
                        Cliente cliente = new Cliente(nome, idade, sexo, email, tel, morada, cc, nif);
                        if (dados.length >= 10) {
                            try {
                                cliente.setTotalViagens(Integer.parseInt(dados[8].trim()));
                                cliente.setTotalGasto(Double.parseDouble(dados[9].trim()));
                            } catch (NumberFormatException e) {
                                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTES, "Erro ao ler histórico financeiro do cliente: " + nome);
                            }
                        }
                        clientes.add(cliente);
                    } catch (NumberFormatException e) {
                        adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTES, "Erro linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTES, "Erro leitura ficheiro: " + e.getMessage());
        }
        return clientes;
    } //Completo Dinis
    //UPDATE na main!

    //DELETE
    public boolean removerCliente(int nif) {
        Cliente cliente = procurarNifCliente(nif);
        if (cliente != null && clientes.remove(cliente)) {
            guardarAlteracoesClientes();
            return true;
        }
        return false;
    } //Completo Dinis

    public Cliente procurarNifCliente(int nif) {
        for (Cliente cliente : clientes) {
            if (cliente.getContribuinte() == nif)
                return cliente;
        }
        return null;
    } //Completo Dinis

    public Cliente procurarCartaoDeCidadaoCliente(int cc) {
        for (Cliente cliente : clientes) {
            if (cliente.getCartaoDeCidadao() == cc)
                return cliente;
        }
        return null;
    } //Completo Dinis

    public void guardarAlteracoesClientes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CLIENTES))) {
            for (Cliente cliente : clientes) {
                writer.write(cliente.paraFicheiro());
                writer.newLine();
            }
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CLIENTES, "Erro ao reescrever clientes: " + e.getMessage());
        }
    }  //Completo
//endregion

    //region CRUD  CONDUTOR

    // CREATE
    public boolean adicionarCondutor(Condutor condutor) {
        if (condutor == null || procurarNifCondutor(condutor.getContribuinte()) != null)
            return false;

        boolean adicionou = condutores.add(condutor);
        if (adicionou) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CONDUTORES, true))) {
                writer.write(condutor.paraFicheiro());
                writer.newLine();
                return true;
            } catch (IOException e) {
                adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTORES, e.getMessage());
                return true;
            }
        }
        return false;
    } //Completo Dinis
    //READ
    public ArrayList<Condutor> carregarCondutores() {
        condutores.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_CONDUTORES))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 9) {
                    try {
                        String nome = dados[0].trim();

                        int idade = 0;
                        try {
                            idade = Integer.parseInt(dados[1].trim());
                        } catch (NumberFormatException e) {
                            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTORES, "Idade inválido na linha: " + linha);
                            idade = 0;
                        }
                        String sexo = dados[2].trim();
                        String email = dados[3].trim();
                        int tel = 0;
                        try {
                            tel = Integer.parseInt(dados[4].trim());
                        } catch (NumberFormatException e) {
                            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTORES, "Número de telemóvel inválido na linha: " + linha);
                            tel = 0;
                        }
                        String morada = dados[5].trim();
                        int cc = 0;
                        try {
                            cc = Integer.parseInt(dados[6].trim());
                        } catch (NumberFormatException e) {
                            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTORES, "Cartão de cidadão inválido na linha: " + linha);
                            cc = 0;
                        }
                        int nif = 0;
                        try {
                            nif = Integer.parseInt(dados[7].trim());
                        } catch (NumberFormatException e) {
                            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTORES, "NIF inválido na linha: " + linha);
                            nif = 0;
                        }
                        String carta = dados[8].trim();

                        Condutor condutor = new Condutor(nome, idade, sexo, email, tel, morada, cc, carta, nif);
                        condutores.add(condutor);
                    } catch (NumberFormatException e) {
                        adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTORES, "Erro linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTORES, "Erro leitura ficheiro: " + e.getMessage());
        }
        return condutores;
    } //Completo dinis :)
    //UPDATE na main!

    //DELETE
    public boolean removerCondutor(int nif) {
        Condutor condutor = procurarNifCondutor(nif);
        if (condutor != null && condutores.remove(condutor)) {
            guardarAlteracoesCondutores();
            return true;
        }
        return false;
    } //Completo Dinis :)

    public Condutor procurarNifCondutor(int nif) {
        for (Condutor condutor : condutores) {
            if (condutor.getContribuinte() == nif)
                return condutor;
        }
        return null;
    } //Completo Dinis

    public Condutor procurarCartaoDeCidadaoCondutor(int cc) {
        for (Condutor condutor : condutores) {
            if (condutor.getCartaoDeCidadao() == cc)
                return condutor;
        }
        return null;
    } //Completo Dinis

    public Condutor procurarCartaDeConducaoCondutor(String carta) {
        for (Condutor condutor : condutores) {
            if (condutor.getCartaDeConducao() != null && condutor.getCartaDeConducao().equalsIgnoreCase(carta)) {
                return condutor;
            }
        }
        return null;
    } //Completo Dinis

    public void guardarAlteracoesCondutores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_CONDUTORES))) {
            for (Condutor condutor : condutores) {
                writer.write(condutor.paraFicheiro());
                writer.newLine();
            }
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_CONDUTORES, "Erro ao reescrever condutores: " + e.getMessage());
        }
    } //Completo Dinis :)

    //Faturação total do condutor
    public double calcularFaturacaoTotal(int contribuinte, LocalDateTime inicio, LocalDateTime fim) {
        double total = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_VIAGENS))) {
            String linha;
            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                if(dados.length >=8){
                    try{
                    int contribuinteLido = Integer.parseInt(dados[1]);
                    LocalDateTime data = LocalDateTime.parse(dados[3]);
                        if (contribuinteLido == contribuinte && (data.isAfter(inicio) || data.isEqual(inicio)) && (data.isBefore(fim) || data.isEqual(fim))) {
                            total += Double.parseDouble(dados[8]);
                        }
                    }catch (Exception e) { continue; }
                }
            }
        }catch(IOException e){
            System.out.println("Erro ao calcular faturação total: " + e.getMessage());
        }
        return total;
    }
//endregion
    //region CRUD RESERVA
    //CREATE
public boolean adicionarReserva(Reserva reserva) {
    if (reserva == null)
        return false;

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_RESERVAS, true))) {
        writer.write(reserva.paraFicheiro());
        writer.newLine();
        return true;
    } catch (IOException e) {
        adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_RESERVAS, e.getMessage());
        return true;
    }
}

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
                        Reserva reserva = new Reserva(cliente, viatura, datahora, origem, destino, kms);
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

    public void guardarAlteracoesReservas() {
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
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_FICHEIRO_VIAGENS, true))) {
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

    public ArrayList<Viagem> carregarViagem() {
        viagens.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_VIAGENS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 9) {
                    try {
                        int nifCliente = 0;
                        try{
                            nifCliente = Integer.parseInt(dados[0].trim());
                        } catch (NumberFormatException e) {
                            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIAGENS, "NIF inválido na linha: " +linha);
                            nifCliente = 0;
                        }
                        int nifCondutor = 0;
                        try{
                            nifCondutor = Integer.parseInt(dados[1].trim());
                        } catch (NumberFormatException e) {
                            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIAGENS, "NIF inválido na linha: " +linha);
                            nifCondutor = 0;
                        }
                        String matricula = dados[2].trim();
                        LocalDateTime inicio = LocalDateTime.parse(dados[3].trim());
                        LocalDateTime fim = LocalDateTime.parse(dados[4].trim());
                        boolean concluida = Boolean.parseBoolean(dados[5].trim());
                        String origem = dados[6].trim();
                        String destino = dados[7].trim();
                        double custo = Double.parseDouble(dados[8].trim());

                        Cliente cliente = procurarNifCliente(nifCliente);
                        Condutor condutor = procurarNifCondutor(nifCondutor);
                        Viatura viatura = procurarViatura(matricula);

                        if (cliente != null && condutor != null && viatura != null) {
                            Viagem viagem = new Viagem(cliente, condutor, viatura, inicio, null, concluida, origem, destino, custo);
                            viagem.setFim(fim);
                            viagens.add(viagem);
                        }
                    } catch (Exception e) {
                        adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIAGENS, "Erro dados linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            adicionarLogsDeErros(CAMINHO_FICHEIRO_LOGS_VIAGENS, "Erro ficheiro: " + e.getMessage());
        }
        return viagens;
    }


    public Viagem procurarViagens(int contribuinte, LocalDateTime dataInicio) {
        for (Viagem viagem : viagens) {
            if (viagem.getCliente().getContribuinte() == contribuinte && viagem.getInicio().equals(dataInicio)) {
                return viagem;
            }
        }
        return null;
    }

    public void guardarAlteracoesViagens() {
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
        Viagem viagem = procurarViagens(contribuinte, inicio);
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
        try(BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_FICHEIRO_RESERVAS))) {
            String linha;
            while ((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                if(dados.length >= 6) {
                    double distancia =  Double.parseDouble(dados[5]);

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

    boolean alterarDataReserva(LocalDateTime dataAntiga, int dia, int mes, int ano){
        for (Reserva reserva : reservas){
            LocalDateTime dataNova = reserva.getDataHoraInicio().withDayOfMonth(dia).withMonth(mes).withYear(ano);
            reserva.setDataHoraInicio(dataNova);
            return true;
        }
        return false;
    }
    boolean alterarHoraReserva(LocalDateTime horaAntiga, int hora, int minuto){
        for (Reserva reserva : reservas){
            LocalDateTime horaNova = reserva.getDataHoraInicio().withHour(hora).withMinute(minuto);
            reserva.setDataHoraInicio(horaNova);
            return true;
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
