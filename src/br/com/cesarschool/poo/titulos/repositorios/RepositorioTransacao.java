package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTransacao {
    private static final String FILE_NAME = "Transacao.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public boolean incluir(Transacao transacao) {
        List<Transacao> transacoes = lerArquivo();
        transacoes.add(transacao);
        return gravarArquivo(transacoes);
    }

    public List<Transacao> buscarTodas() {
        return lerArquivo();
    }

    public List<Transacao> buscarPorEntidadeCredora(int identificadorEntidadeCredora) {
        List<Transacao> transacoes = lerArquivo();
        List<Transacao> resultado = new ArrayList<>();
        for (Transacao transacao : transacoes) {
            if (transacao.getEntidadeCredora().getIdentificador() == identificadorEntidadeCredora) {
                resultado.add(transacao);
            }
        }
        return resultado;
    }

    public List<Transacao> buscarPorEntidadeDevedora(int identificadorEntidadeDevedora) {
        List<Transacao> transacoes = lerArquivo();
        List<Transacao> resultado = new ArrayList<>();
        for (Transacao transacao : transacoes) {
            if (transacao.getEntidadeDevedora().getIdentificador() == identificadorEntidadeDevedora) {
                resultado.add(transacao);
            }
        }
        return resultado;
    }

    private List<Transacao> lerArquivo() {
        List<Transacao> transacoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                long id = Long.parseLong(dados[0]);
                LocalDateTime dataHoraOperacao = LocalDateTime.parse(dados[1], DATE_FORMATTER);
                double valorOperacao = Double.parseDouble(dados[2]);
                EntidadeOperadora entidadeCredora = new EntidadeOperadora(Long.parseLong(dados[3]), dados[4], Boolean.parseBoolean(dados[5]));
                EntidadeOperadora entidadeDevedora = new EntidadeOperadora(Long.parseLong(dados[6]), dados[7], Boolean.parseBoolean(dados[8]));
                Acao acao = dados[9].equals("null") ? null : new Acao(Integer.parseInt(dados[9]), dados[10], LocalDate.parse(dados[11], DATE_FORMATTER), Double.parseDouble(dados[12]));
                TituloDivida tituloDivida = dados[13].equals("null") ? null : new TituloDivida(Integer.parseInt(dados[13]), dados[14], LocalDate.parse(dados[15], DATE_FORMATTER), Double.parseDouble(dados[16]));
                Transacao transacao = new Transacao(entidadeCredora, entidadeDevedora, acao, tituloDivida, valorOperacao, dataHoraOperacao);
                transacoes.add(transacao);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transacoes;
    }

    private boolean gravarArquivo(List<Transacao> transacoes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Transacao transacao : transacoes) {
                String linha = String.format("%d;%s;%.2f;%d;%s;%b;%d;%s;%b;%s;%s;%s;%.2f;%s;%s;%s;%.2f",
                        transacao.getId(),
                        transacao.getDataHoraOperacao().format(DATE_FORMATTER),
                        transacao.getValorOperacao(),
                        transacao.getEntidadeCredora().getIdentificador(),
                        transacao.getEntidadeCredora().getNome(),
                        transacao.getEntidadeCredora().getAutorizadoAcao(),
                        transacao.getEntidadeDevedora().getIdentificador(),
                        transacao.getEntidadeDevedora().getNome(),
                        transacao.getEntidadeDevedora().getAutorizadoAcao(),
                        transacao.getAcao() == null ? "null" : transacao.getAcao().getIdentificador(),
                        transacao.getAcao() == null ? "null" : transacao.getAcao().getNome(),
                        transacao.getAcao() == null ? "null" : transacao.getAcao().getDataDeValidade().format(DATE_FORMATTER),
                        transacao.getAcao() == null ? 0 : transacao.getAcao().getValorUnitario(),
                        transacao.getTituloDivida() == null ? "null" : transacao.getTituloDivida().getIdentificador(),
                        transacao.getTituloDivida() == null ? "null" : transacao.getTituloDivida().getNome(),
                        transacao.getTituloDivida() == null ? "null" : transacao.getTituloDivida().getDataDeValidade().format(DATE_FORMATTER),
                        transacao.getTituloDivida() == null ? 0 : transacao.getTituloDivida().getTaxaJuros());
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}