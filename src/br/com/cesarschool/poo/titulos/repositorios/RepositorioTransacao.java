package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTransacao {
    private static final String FILE_NAME = "Transacao.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private RepositorioEntidadeOperadora repoEntidade = new RepositorioEntidadeOperadora();
    private RepositorioAcao repoAcao = new RepositorioAcao();
    private RepositorioTituloDivida repoTitulo = new RepositorioTituloDivida();

    // Método para incluir uma transação no repositório
    public boolean incluir(Transacao transacao) {
        List<Transacao> transacoes = lerArquivo();
        transacoes.add(transacao);
        return gravarArquivo(transacoes);
    }

    // Método para buscar todas as transações
    public List<Transacao> buscarTodas() {
        return lerArquivo();
    }

    // Método para buscar transações onde a entidade é credora
    public List<Transacao> buscarPorEntidadeCredora(int identificadorEntidadeCredora) {
        List<Transacao> transacoes = lerArquivo();
        List<Transacao> transacoesCredoras = new ArrayList<>();
        for (Transacao transacao : transacoes) {
            if (transacao.getEntidadeCredito().getIdentificador() == identificadorEntidadeCredora) {
                transacoesCredoras.add(transacao);
            }
        }
        return transacoesCredoras;
    }

    // Método para buscar transações onde a entidade é devedora
    public List<Transacao> buscarPorEntidadeDevedora(int identificadorEntidadeDevedora) {
        List<Transacao> transacoes = lerArquivo();
        List<Transacao> transacoesDevedoras = new ArrayList<>();
        for (Transacao transacao : transacoes) {
            if (transacao.getEntidadeDebito().getIdentificador() == identificadorEntidadeDevedora) {
                transacoesDevedoras.add(transacao);
            }
        }
        return transacoesDevedoras;
    }

    // Método privado para ler o arquivo de transações
    private List<Transacao> lerArquivo() {
        List<Transacao> transacoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                // Buscando entidades relacionadas pelos identificadores
                EntidadeOperadora entidadeCredito = repoEntidade.buscar(Long.parseLong(dados[0]));
                EntidadeOperadora entidadeDebito = repoEntidade.buscar(Long.parseLong(dados[1]));
                Acao acao = repoAcao.buscar(Integer.parseInt(dados[2]));
                TituloDivida tituloDivida = repoTitulo.buscar(Integer.parseInt(dados[3]));

                // Reconstruindo a data e o valor da operação
                double valorOperacao = Double.parseDouble(dados[4]);
                LocalDateTime dataHoraOperacao = LocalDateTime.parse(dados[5], DATE_TIME_FORMATTER);

                // Criando a transação com os dados reconstruídos
                Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
                transacoes.add(transacao);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transacoes;
    }

    // Método privado para gravar as transações no arquivo
    private boolean gravarArquivo(List<Transacao> transacoes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Transacao transacao : transacoes) {
                // Gravando os dados da transação no formato:
                // idEntidadeCredito;idEntidadeDebito;idAcao;idTituloDivida;valorOperacao;dataHoraOperacao
                bw.write(transacao.getEntidadeCredito().getIdentificador() + ";"
                        + transacao.getEntidadeDebito().getIdentificador() + ";"
                        + (transacao.getAcao() != null ? transacao.getAcao().getIdentificador() : "null") + ";"
                        + (transacao.getTituloDivida() != null ? transacao.getTituloDivida().getIdentificador() : "null") + ";"
                        + transacao.getValorOperacao() + ";"
                        + transacao.getDataHoraOperacao().format(DATE_TIME_FORMATTER));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
