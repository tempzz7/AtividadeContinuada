package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTituloDivida {
    private static final String FILE_NAME = "TituloDivida.txt";

    public boolean incluir(TituloDivida tituloDivida) {
        List<TituloDivida> titulos = lerArquivo();
        for (TituloDivida t : titulos) {
            if (t.getIdentificador() == tituloDivida.getIdentificador()) {
                return false; // Identificador repetido
            }
        }
        titulos.add(tituloDivida);
        return gravarArquivo(titulos);
    }

    public boolean alterar(TituloDivida tituloDivida) {
        List<TituloDivida> titulos = lerArquivo();
        boolean encontrado = false;
        for (int i = 0; i < titulos.size(); i++) {
            if (titulos.get(i).getIdentificador() == tituloDivida.getIdentificador()) {
                titulos.set(i, tituloDivida);
                encontrado = true;
                break;
            }
        }
        return encontrado && gravarArquivo(titulos);
    }

    public boolean excluir(int identificador) {
        List<TituloDivida> titulos = lerArquivo();
        boolean encontrado = titulos.removeIf(t -> t.getIdentificador() == identificador);
        return encontrado && gravarArquivo(titulos);
    }

    public TituloDivida buscar(int identificador) {
        List<TituloDivida> titulos = lerArquivo();
        for (TituloDivida titulo : titulos) {
            if (titulo.getIdentificador() == identificador) {
                return titulo;
            }
        }
        return null;
    }

    private List<TituloDivida> lerArquivo() {
        List<TituloDivida> titulos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                TituloDivida titulo = new TituloDivida(
                    Integer.parseInt(dados[0]),
                    dados[1],
                    LocalDate.parse(dados[2]),
                    Double.parseDouble(dados[3])
                );
                titulos.add(titulo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return titulos;
    }

    private boolean gravarArquivo(List<TituloDivida> titulos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (TituloDivida titulo : titulos) {
                bw.write(titulo.getIdentificador() + ";" + titulo.getNome() + ";" + titulo.getDataValidade() + ";" + titulo.getTaxaJuros());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
