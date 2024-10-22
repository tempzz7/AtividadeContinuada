package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAcao {
    private static final String FILE_NAME = "Acao.txt";

    public boolean incluir(Acao acao) {
        List<Acao> acoes = lerArquivo();
        for (Acao a : acoes) {
            if (a.getIdentificador() == acao.getIdentificador()) {
                return false; // Identificador repetido
            }
        }
        acoes.add(acao);
        return gravarArquivo(acoes);
    }

    public boolean alterar(Acao acao) {
        List<Acao> acoes = lerArquivo();
        boolean encontrado = false;
        for (int i = 0; i < acoes.size(); i++) {
            if (acoes.get(i).getIdentificador() == acao.getIdentificador()) {
                acoes.set(i, acao);
                encontrado = true;
                break;
            }
        }
        return encontrado && gravarArquivo(acoes);
    }

    public boolean excluir(int identificador) {
        List<Acao> acoes = lerArquivo();
        boolean encontrado = acoes.removeIf(a -> a.getIdentificador() == identificador);
        return encontrado && gravarArquivo(acoes);
    }

    public Acao buscar(int identificador) {
        List<Acao> acoes = lerArquivo();
        for (Acao acao : acoes) {
            if (acao.getIdentificador() == identificador) {
                return acao;
            }
        }
        return null;
    }

    private List<Acao> lerArquivo() {
        List<Acao> acoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Acao acao = new Acao(
                    Integer.parseInt(dados[0]),
                    dados[1],
                    LocalDate.parse(dados[2]),
                    Double.parseDouble(dados[3])
                );
                acoes.add(acao);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return acoes;
    }

    private boolean gravarArquivo(List<Acao> acoes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Acao acao : acoes) {
                bw.write(acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataDeValidade() + ";" + acao.getValorUnitario());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}