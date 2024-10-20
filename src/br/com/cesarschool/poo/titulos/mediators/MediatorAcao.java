package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;
import java.time.LocalDate;

public class MediatorAcao {
    // Atributo do repositório
    private final RepositorioAcao repositorioAcao = new RepositorioAcao();
    
    // Instância única para o padrão Singleton
    private static MediatorAcao instancia = new MediatorAcao();

    // Construtor privado para o padrão Singleton
    private MediatorAcao() {}

    // Método para obter a instância única
    public static MediatorAcao getInstance() {
        return instancia;
    }

    // Método para validar a Acao
    private String validar(Acao acao) {
        if (acao.getIdentificador() <= 0 || acao.getIdentificador() >= 100000) {
            return "Identificador deve estar entre 1 e 99999.";
        }
        if (acao.getNome() == null || acao.getNome().trim().isEmpty()) {
            return "Nome deve ser preenchido.";
        }
        if (acao.getNome().length() < 10 || acao.getNome().length() > 100) {
            return "Nome deve ter entre 10 e 100 caracteres.";
        }
        if (acao.getDataValidade().isBefore(LocalDate.now().plusDays(30))) {
            return "Data de validade deve ter pelo menos 30 dias na frente da data atual.";
        }
        if (acao.getValorUnitario() <= 0) {
            return "Valor unitário deve ser maior que zero.";
        }
        return null;
    }

    // Método para incluir uma Acao
    public String incluir(Acao acao) {
        String mensagemValidacao = validar(acao);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        boolean incluido = repositorioAcao.incluir(acao);
        if (incluido) {
            return null;
        } else {
            return "Ação já existente";
        }
    }

    // Método para alterar uma Acao
    public String alterar(Acao acao) {
        String mensagemValidacao = validar(acao);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        boolean alterado = repositorioAcao.alterar(acao);
        if (alterado) {
            return null;
        } else {
            return "Ação inexistente";
        }
    }

    // Método para excluir uma Acao
    public String excluir(int identificador) {
        if (identificador <= 0 || identificador >= 100000) {
            return "Ação inexistente";
        }
        boolean excluido = repositorioAcao.excluir(identificador);
        if (excluido) {
            return null;
        } else {
            return "Ação inexistente";
        }
    }

    // Método para buscar uma Acao
    public Acao buscar(int identificador) {
        if (identificador <= 0 || identificador >= 100000) {
            return null;
        }
        return repositorioAcao.buscar(identificador);
    }
}