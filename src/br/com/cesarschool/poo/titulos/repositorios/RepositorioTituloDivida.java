package br.com.cesarschool.poo.titulos.repositorios;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTituloDivida {
	static Path text = Paths.get("TituloDivida.txt");
	public boolean incluir(TituloDivida tituloDivida) {
		try(BufferedReader reader = new BufferedReader(new FileReader(text.toFile()))) {
			String line;
			while((line=reader.readLine())!=null){
				String[] lines = line.split(";");
				if(lines[0].equals(String.valueOf(tituloDivida.getIdentificador()))){
					return false;
				}
			}
		}catch (IOException e){
			e.fillInStackTrace();
			return false;
		}
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(text.toFile(),true))){
			String newLine = tituloDivida.getIdentificador() + " " + tituloDivida.getNome() + " " + tituloDivida.getDataDeValidade() + tituloDivida.getTaxaJuros();
			writer.write(newLine);
			writer.newLine();
			return true;

		}catch (IOException e){
			return false;
		}
	}

	public boolean alterar(TituloDivida tituloDivida) {
		List<String> lines = new ArrayList<>();
		boolean trocou = false;
		try(BufferedReader reader = new BufferedReader(new FileReader(text.toFile()))) {
			String line;
			while((line=reader.readLine())!=null){
				String[] atributos = line.split(";");
				if(atributos[0].equals(String.valueOf(tituloDivida.getIdentificador()))){
					lines.add(tituloDivida.getIdentificador() + " " + tituloDivida.getNome() + " " + tituloDivida.getDataDeValidade() + " " + tituloDivida.getTaxaJuros());
					trocou=true;
				}else{
					lines.add(line);
				}
			}
		}catch (IOException e){
			return false;
		}
		if(trocou){
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(text.toFile(),true))){
				for(String line:lines){
					writer.write(line);
					writer.newLine();
					return true;
				}
			}catch (IOException e){
				return false;
			}
		}
		else {
			return false;
		}
		return false;
	}

	public boolean excluir(int identificador) {
		List<String> lines = new ArrayList<>();
		boolean excluido = false;
		try(BufferedReader reader = new BufferedReader(new FileReader(text.toFile()))) {
			String line;
			while((line=reader.readLine())!=null){
				String[] atributos = line.split(";");
				if(Integer.parseInt(atributos[0])!=identificador){
					lines.add(line);
				}else{
					excluido = true;
				}
			}
		}catch (IOException e){
			return false;
		}
		if(excluido){
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(text.toFile(),true))){
				for(String line:lines){
					writer.write(line);
					writer.newLine();
					return true;
				}
			}catch (IOException e){
				return false;
			}
		}else{
			return false;
		}
		return false;
	}

	static public TituloDivida buscar(int identificador) {
		try(BufferedReader reader = new BufferedReader(new FileReader(text.toFile()))) {
			String line;
			while((line=reader.readLine())!=null){
				String[] lines = line.split(";");
				if(Integer.parseInt(lines[0])==identificador){
					TituloDivida tituloVida = new TituloDivida(Integer.parseInt(lines[0]),lines[1],LocalDate.parse(lines[2]), Double.parseDouble(lines[3]));
					return tituloVida;
				}
			}
		}catch (IOException e){
			e.fillInStackTrace();
		}
		return null;
	}
}