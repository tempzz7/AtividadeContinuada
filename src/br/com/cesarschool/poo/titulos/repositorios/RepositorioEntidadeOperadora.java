package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEntidadeOperadora {
    private static final String FILE_NAME = "EntidadeOperadora.txt";

    public boolean incluir(EntidadeOperadora entidadeOperadora) {
        List<EntidadeOperadora> entidades = lerArquivo();
        for (EntidadeOperadora e : entidades) {
            if (e.getIdentificador() == entidadeOperadora.getIdentificador()) {
                return false; 
            }
        }
        entidades.add(entidadeOperadora);
        return gravarArquivo(entidades);
    }

    public boolean alterar(EntidadeOperadora entidadeOperadora) {
        List<EntidadeOperadora> entidades = lerArquivo();
        boolean encontrado = false;
        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i).getIdentificador() == entidadeOperadora.getIdentificador()) {
                entidades.set(i, entidadeOperadora);
                encontrado = true;
                break;
            }
        }
        return encontrado && gravarArquivo(entidades);
    }

    public boolean excluir(int identificador) {
        List<EntidadeOperadora> entidades = lerArquivo();
        boolean encontrado = entidades.removeIf(e -> e.getIdentificador() == identificador);
        return encontrado && gravarArquivo(entidades);
    }

    public EntidadeOperadora buscar(long identificador) {
        List<EntidadeOperadora> entidades = lerArquivo();
        for (EntidadeOperadora entidade : entidades) {
            if (entidade.getIdentificador() == identificador) {
                return entidade;
            }
        }
        return null;
    }

    private List<EntidadeOperadora> lerArquivo() {
        List<EntidadeOperadora> entidades = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile(); 
            } catch (IOException e) {
                e.printStackTrace();
                return entidades;
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                EntidadeOperadora entidade = new EntidadeOperadora(
                    Long.parseLong(dados[0]),
                    dados[1],
                    Boolean.parseBoolean(dados[2])
                );
                entidade.setSaldoAcao(Double.parseDouble(dados[3]));
                entidade.setSaldoTituloDivida(Double.parseDouble(dados[4]));
                entidades.add(entidade);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entidades;
    }

    private boolean gravarArquivo(List<EntidadeOperadora> entidades) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (EntidadeOperadora entidade : entidades) {
                bw.write(entidade.getIdentificador() + ";" + entidade.getNome() + ";" + entidade.getAutorizadoAcao() + ";" + entidade.getSaldoAcao() + ";" + entidade.getSaldoTituloDivida());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}