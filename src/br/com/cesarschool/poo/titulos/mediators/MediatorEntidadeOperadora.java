package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

public class MediatorEntidadeOperadora {
    // Atributo do repositório
    private final RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();
    
    // Instância única para o padrão Singleton
    private static MediatorEntidadeOperadora instancia = new MediatorEntidadeOperadora();

    // Construtor privado para o padrão Singleton
    private MediatorEntidadeOperadora() {}

    // Método para obter a instância única
    public static MediatorEntidadeOperadora getInstance() {
        return instancia;
    }

    // Método para validar a EntidadeOperadora
    private String validar(EntidadeOperadora entidade) {
        if (entidade.getIdentificador() <= 100 || entidade.getIdentificador() >= 1000000) {
            return "Identificador deve estar entre 100 e 999999.";
        }
        if (entidade.getNome() == null || entidade.getNome().trim().isEmpty()) {
            return "Nome deve ser preenchido.";
        }
        if (entidade.getNome().length() < 10 || entidade.getNome().length() > 100) {
            return "Nome deve ter entre 10 e 100 caracteres.";
        }
        return null;
    }

    // Método para incluir uma EntidadeOperadora
    public String incluir(EntidadeOperadora entidade) {
        String mensagemValidacao = validar(entidade);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        boolean incluido = repositorioEntidadeOperadora.incluir(entidade);
        if (incluido) {
            return null;
        } else {
            return "Entidade já existente";
        }
    }

    // Método para alterar uma EntidadeOperadora
    public String alterar(EntidadeOperadora entidade) {
        String mensagemValidacao = validar(entidade);
        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        boolean alterado = repositorioEntidadeOperadora.alterar(entidade);
        if (alterado) {
            return null;
        } else {
            return "Entidade inexistente";
        }
    }

    // Método para excluir uma EntidadeOperadora
    public String excluir(long identificador) {
        if (identificador <= 100 || identificador >= 1000000) {
            return "Entidade inexistente";
        }
        boolean excluido = repositorioEntidadeOperadora.excluir(identificador);
        if (excluido) {
            return null;
        } else {
            return "Entidade inexistente";
        }
    }

    // Método para buscar uma EntidadeOperadora
    public EntidadeOperadora buscar(long identificador) {
        if (identificador <= 100 || identificador >= 1000000) {
            return null;
        }
        return repositorioEntidadeOperadora.buscar(identificador);
    }
}