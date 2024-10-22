package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTransacao {
    private static final String FILE_NAME = "Transacao.txt";

    public boolean incluir(Transacao transacao) {
        List<Transacao> transacoes = lerArquivo();
        transacoes.add(transacao);
        boolean sucesso = gravarArquivo(transacoes);
        if (sucesso) {
            transacao.realizarTransacao();
            atualizarEntidades(transacao.getEntidadeCredora(), transacao.getEntidadeDevedora());
        }
        return sucesso;
    }

    private void atualizarEntidades(EntidadeOperadora credora, EntidadeOperadora devedora) {
        List<EntidadeOperadora> entidades = lerArquivoEntidades();
        for (EntidadeOperadora entidade : entidades) {
            if (entidade.getIdentificador() == credora.getIdentificador()) {
                entidade.setSaldoTituloDivida(credora.getSaldoTituloDivida());
            } else if (entidade.getIdentificador() == devedora.getIdentificador()) {
                entidade.setSaldoTituloDivida(devedora.getSaldoTituloDivida());
            }
        }
        gravarArquivoEntidades(entidades);
    }

    private List<Transacao> lerArquivo() {
        List<Transacao> transacoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                // Implementação de leitura conforme necessário
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transacoes;
    }

    private boolean gravarArquivo(List<Transacao> transacoes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Transacao transacao : transacoes) {
                bw.write(transacao.getEntidadeCredora().getIdentificador() + ";" +
                        transacao.getEntidadeDevedora().getIdentificador() + ";" +
                        transacao.getValorOperacao() + ";" +
                        transacao.getDataHoraOperacao() + "\n");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<EntidadeOperadora> lerArquivoEntidades() {
        
        return new ArrayList<>();
    }

    private boolean gravarArquivoEntidades(List<EntidadeOperadora> entidades) {
      
        return true;
    }
}
