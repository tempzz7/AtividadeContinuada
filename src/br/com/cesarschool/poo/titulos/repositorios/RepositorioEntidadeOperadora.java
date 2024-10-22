package br.com.cesarschool.poo.titulos.repositorios;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEntidadeOperadora {

    static Path text = Paths.get("EntidadeOperadora.txt");

    public boolean incluir(EntidadeOperadora entidadeOperadora) {
        try (BufferedReader reader = new BufferedReader(new FileReader(text.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributos = line.split(";");
                if (atributos[0].equals(String.valueOf(entidadeOperadora.getIdentificador()))) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(text.toFile(), true))) {
            String newLine = entidadeOperadora.getIdentificador() + " " + entidadeOperadora.getNome() + " " + entidadeOperadora.getAutorizadoAcao();
            writer.write(newLine);
            writer.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean alterar(EntidadeOperadora entidadeOperadora) {
        List<String> lines = new ArrayList<>();
        boolean trocou = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(text.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributos = line.split(";");
                if (atributos[0].equals(String.valueOf(entidadeOperadora.getIdentificador()))) {
                    lines.add(entidadeOperadora.getIdentificador() + " " + entidadeOperadora.getNome() + " " + entidadeOperadora.getAutorizadoAcao());
                    trocou = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            return false;
        }

        if (trocou) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(text.toFile(), false))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean excluir(int identificador) {
        List<String> lines = new ArrayList<>();
        boolean excluido = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(text.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributos = line.split(";");
                if (Integer.parseInt(atributos[0]) != identificador) {
                    lines.add(line);
                } else {
                    excluido = true;
                }
            }
        } catch (IOException e) {
            return false;
        }

        if (excluido) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(text.toFile(), false))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    static public EntidadeOperadora buscar(int identificador) {
        try (BufferedReader reader = new BufferedReader(new FileReader(text.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributos = line.split(";");
                if (Integer.parseInt(atributos[0]) == identificador) {
                    return new EntidadeOperadora(Integer.parseInt(atributos[0]), atributos[1], Double.parseDouble(atributos[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
