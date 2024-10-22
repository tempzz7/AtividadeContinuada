package br.com.cesarschool.poo.titulos.mediator;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

public class MediatorEntidadeOperadora {
    private RepositorioEntidadeOperadora repositorio;

    public MediatorEntidadeOperadora() {
        repositorio = new RepositorioEntidadeOperadora();
    }

    public EntidadeOperadora procurar(int id) {
        return repositorio.buscar(id);
    }
}
