package br.com.cesarschool.poo.titulos.repositorios;
import br.com.cesarschool.poo.titulos.entidades.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class RepositorioTransacao {
	private static final Path text = Paths.get("Transacao.txt");
	public boolean incluir(Transacao transacao) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(text.toFile(), true))) {
			writer.write(transacao.getEntidadeCredito().getIdentificador() + ";"
					+ transacao.getEntidadeCredito().getNome() + ";"
					+ transacao.getEntidadeCredito().getAutorizadoAcao() + ";"
					+ transacao.getEntidadeCredito().getSaldoAcao() + ";"
					+ transacao.getEntidadeCredito().getSaldoTituloDivida() + ";"
					+ transacao.getEntidadeDebito().getIdentificador() + ";"
					+ transacao.getEntidadeDebito().getNome() + ";"
					+ transacao.getEntidadeDebito().getAutorizadoAcao() + ";"
					+ transacao.getEntidadeDebito().getSaldoAcao() + ";"
					+ transacao.getEntidadeDebito().getSaldoTituloDivida() + ";"
					+ (transacao.getAcao() != null ? transacao.getAcao().getIdentificador() + ";"
					+ transacao.getAcao().getNome() + ";"
					+ transacao.getAcao().getDataDeValidade() + ";"
					+ transacao.getAcao().getValorUnitario() + ";"
					: "null;null;null;null;")
					+ (transacao.getTituloDivida() != null ? transacao.getTituloDivida().getIdentificador() + ";"
					+ transacao.getTituloDivida().getNome() + ";"
					+ transacao.getTituloDivida().getDataDeValidade() + ";"
					+ transacao.getTituloDivida().getTaxaJuros()
					: "null;null;null;null;")
					+ ";" + transacao.getValorOperacao() + ";"
					+ transacao.getDataHoraOperacao());
			writer.newLine();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Transacao> buscarPorEntidade(int identificador, boolean isCredito) {
		List<Transacao> transacoes = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(text.toFile()))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				String[] dados = linha.split(";");

				Acao acao = null;
				if (!"null".equals(dados[10])) {
					acao = RepositorioAcao.buscar(Integer.parseInt(dados[10]));
				}

				TituloDivida tituloDivida = null;
				if (!"null".equals(dados[14])) {
					tituloDivida = RepositorioTituloDivida.buscar(Integer.parseInt(dados[14]));
				}
				EntidadeOperadora entidadeDebito = RepositorioEntidadeOperadora.buscar(Integer.parseInt(dados[5]));
				EntidadeOperadora entidadeCredito = RepositorioEntidadeOperadora.buscar(Integer.parseInt(dados[0]));
				Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, Double.parseDouble(dados[18]), LocalDateTime.parse(dados[19]));
				if ((isCredito && transacao.getEntidadeCredito().getIdentificador() == identificador) ||
						(!isCredito && transacao.getEntidadeDebito().getIdentificador() == identificador)) {
					transacoes.add(transacao);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return transacoes;
	}

	public List<Transacao> buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
		return buscarPorEntidade(identificadorEntidadeCredito, true);
	}

	public List<Transacao> buscarPorEntidadeDevedora(int identificadorEntidadeDebito) {
		return buscarPorEntidade(identificadorEntidadeDebito, false);
	}
}