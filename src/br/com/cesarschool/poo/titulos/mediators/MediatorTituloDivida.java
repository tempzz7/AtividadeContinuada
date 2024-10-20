package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;
import java.time.LocalDate;

public class MediatorTituloDivida {
    // Atributo do repositório
    private final RepositorioTituloDivida repositorioTituloDivida = new RepositorioTituloDivida();
    
    // Instância única para o padrão Singleton
    private static MediatorTituloDivida instancia = new MediatorTituloDivida();

    // Construtor privado para o padrão Singleton
    private MediatorTituloDivida() {}

    // Método para obter a instância única
    public static MediatorTituloDivida getInstance() {
        return instancia;
    }

    // Método para validar o TituloDivida
    private String validar(TituloDivida titulo) {
        if (titulo.getIdentificador() <= 0 || titulo.getIdentificador() >= 100000) {
            return "Identificador deve estar entre 1 e 99999.";
        }
        if (titulo.getNome() == null || titulo.getNome().trim().isEmpty()) {
            return "Nome deve ser preenchido.";
        }
        if (titulo.getNome().length() < 10 || titulo.getNome().length() > 100) {
            return "Nome deve ter entre 10 e 100 caracteres.";
        }
        if (titulo.getDataValidade().isBefore(LocalDate.now().plusDays(180))) {
            return "Data de validade deve ter pelo menos 180 dias na frente da data atual.";
        }
        if (titulo.getTaxaJuros() < 0) {
            return "Taxa de juros deve ser maior ou igual a zero.";
        }
        return null;
    }

    // Método para incluir um TituloDivida
    public String incluir(TituloDivida titulo) {
        String mensagemValidacao = validar(titulo);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        boolean incluido = repositorioTituloDivida.incluir(titulo);
        if (incluido) {
            return null;
        } else {
            return "Título já existente";
        }
    }

    // Método para alterar um TituloDivida
    public String alterar(TituloDivida titulo) {
        String mensagemValidacao = validar(titulo);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        boolean alterado = repositorioTituloDivida.alterar(titulo);
        if (alterado) {
            return null;
        } else {
            return "Título inexistente";
        }
    }

    // Método para excluir um TituloDivida
    public String excluir(int identificador) {
        if (identificador <= 0 || identificador >= 100000) {
            return "Título inexistente";
        }
        boolean excluido = repositorioTituloDivida.excluir(identificador);
        if (excluido) {
            return null;
        } else {
            return "Título inexistente";
        }
    }

    // Método para buscar um TituloDivida
    public TituloDivida buscar(int identificador) {
        if (identificador <= 0 || identificador >= 100000) {
            return null;
        }
        return repositorioTituloDivida.buscar(identificador);
    }
}