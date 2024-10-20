package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEntidadeOperadora {
    private static final String FILE_NAME = "EntidadeOperadora.txt";

    public boolean incluir(EntidadeOperadora entidade) {
        List<EntidadeOperadora> entidades = lerArquivo();
        for (EntidadeOperadora e : entidades) {
            if (e.getIdentificador() == entidade.getIdentificador()) {
                return false; // Identificador repetido
            }
        }
        entidades.add(entidade);
        return gravarArquivo(entidades);
    }

    public boolean alterar(EntidadeOperadora entidade) {
        List<EntidadeOperadora> entidades = lerArquivo();
        boolean encontrado = false;
        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i).getIdentificador() == entidade.getIdentificador()) {
                entidades.set(i, entidade);
                encontrado = true;
                break;
            }
        }
        return encontrado && gravarArquivo(entidades);
    }

    public boolean excluir(long identificador) {
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
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                EntidadeOperadora entidade = new EntidadeOperadora(
                    Long.parseLong(dados[0]),
                    dados[1],
                    Double.parseDouble(dados[2])
                );
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
                bw.write(entidade.getIdentificador() + ";" + entidade.getNome() + ";" + entidade.getAutorizadoAcao());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
