package br.com.cesarschool.poo.titulos.mediator;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;

import java.time.LocalDate;

public class MediatorTituloDivida {
    private RepositorioTituloDivida repositorio;

    public MediatorTituloDivida() {
        this.repositorio = new RepositorioTituloDivida();  
    }

   
    public boolean incluirTituloDivida(int identificador, String nome, LocalDate dataDeValidade, double taxaJuros) {
        TituloDivida tituloDivida = new TituloDivida(identificador, nome, dataDeValidade, taxaJuros);
        return repositorio.incluir(tituloDivida);
    }

 
    public boolean alterarTituloDivida(int identificador, String nome, LocalDate dataDeValidade, double taxaJuros) {
        TituloDivida tituloDivida = new TituloDivida(identificador, nome, dataDeValidade, taxaJuros);
        return repositorio.alterar(tituloDivida);
    }

    
    public boolean excluirTituloDivida(int identificador) {
        return repositorio.excluir(identificador);
    }

 
    public TituloDivida buscarTituloDivida(int identificador) {
        return repositorio.buscar(identificador);
    }
}
