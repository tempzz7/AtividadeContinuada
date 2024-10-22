package br.com.cesarschool.poo.titulos.mediator;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;
import java.time.LocalDate;

public class MediatorAcao {

    private static MediatorAcao instancia;

    private final RepositorioAcao repositorioAcao = new RepositorioAcao();

    private MediatorAcao() {}

    public static MediatorAcao obterInstancia() {
        if (instancia == null) {
            instancia = new MediatorAcao();
        }
        return instancia;
    }

    private String checarValidade(Acao acao) {
        if (acao.getIdentificador() <= 0 || acao.getIdentificador() >= 100000) {
            return "Identificador deve ser entre 1 e 99999.";
        }

        if (acao.getNome() == null || acao.getNome().trim().isEmpty()) {
            return "O nome não pode ser vazio.";
        }

        if (acao.getNome().length() < 10 || acao.getNome().length() > 100) {
            return "O nome deve conter entre 10 e 100 caracteres.";
        }

        if (acao.getDataDeValidade().isBefore(LocalDate.now().plusDays(180))) {
            return "A data de validade deve estar a pelo menos 180 dias à frente da data atual.";
        }

        if (acao.getValorUnitario() <= 0) {
            return "O valor unitário deve ser maior que zero.";
        }

        return null;
    }

    public String incluir(Acao acao) {
        String erroValidacao = checarValidade(acao);

        if (erroValidacao != null) {
            return erroValidacao;
        }

        boolean incluidaComSucesso = repositorioAcao.incluir(acao);

        return incluidaComSucesso ? null : "Ação já existente.";
    }

    public String atualizar(Acao acao) {
        String erroValidacao = checarValidade(acao);

        if (erroValidacao != null) {
            return erroValidacao;
        }

        boolean alteradaComSucesso = repositorioAcao.alterar(acao);

        return alteradaComSucesso ? null : "Ação não encontrada.";
    }

    public String remover(int identificador) {
        if (identificador <= 0 || identificador >= 100000) {
            return "Identificador deve ser entre 1 e 99999.";
        }

        boolean removidaComSucesso = repositorioAcao.excluir(identificador);

        return removidaComSucesso ? null : "Ação não encontrada.";
    }

    public Acao localizar(int identificador) {
        if (identificador <= 0 || identificador >= 100000) {
            return null;
        }
        return repositorioAcao.buscar(identificador);
    }
}