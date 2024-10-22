package br.com.cesarschool.poo.titulos.mediator;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MediatorEntidadeOperadora {

    private final Path caminhoArquivo = Paths.get("EntidadeOperadora.txt");

    public boolean adicionar(EntidadeOperadora entidade) {
        if (existeIdentificador(entidade.getIdentificador())) {
            return false;
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo.toFile(), true))) {
            escritor.write(formatarLinha(entidade));
            escritor.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean atualizar(EntidadeOperadora entidade) {
        List<String> linhasModificadas = new ArrayList<>();
        boolean alterado = false;

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo.toFile()))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(String.valueOf(entidade.getIdentificador()))) {
                    linhasModificadas.add(formatarLinha(entidade));
                    alterado = true;
                } else {
                    linhasModificadas.add(linha);
                }
            }
        } catch (IOException e) {
            return false;
        }

        if (alterado) {
            escreverNovasLinhas(linhasModificadas);
            return true;
        }

        return false;
    }

    public boolean remover(int identificador) {
        List<String> linhasModificadas = new ArrayList<>();
        boolean removido = false;

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo.toFile()))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
                if (!dados[0].equals(String.valueOf(identificador))) {
                    linhasModificadas.add(linha);
                } else {
                    removido = true;
                }
            }
        } catch (IOException e) {
            return false;
        }

        if (removido) {
            escreverNovasLinhas(linhasModificadas);
            return true;
        }

        return false;
    }

    public EntidadeOperadora procurar(int identificador) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo.toFile()))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(String.valueOf(identificador))) {
                    return new EntidadeOperadora(
                            Integer.parseInt(dados[0]),
                            dados[1],
                            Boolean.parseBoolean(dados[2])
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean existeIdentificador(int identificador) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo.toFile()))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(String.valueOf(identificador))) {
                    return true; 
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    private void escreverNovasLinhas(List<String> linhasModificadas) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo.toFile()))) {
            for (String linha : linhasModificadas) {
                escritor.write(linha);
                escritor.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatarLinha(EntidadeOperadora entidade) {
        return entidade.getIdentificador() + ";" + entidade.getNome() + ";" + entidade.getAutorizadoAcao();
    }
}