package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDateTime;

public class Transacao {
    private long id;
    private LocalDateTime dataHoraOperacao;
    private double valorOperacao;
    private EntidadeOperadora entidadeCredora;
    private EntidadeOperadora entidadeDevedora;
    private Acao acao;
    private TituloDivida tituloDivida;

    public Transacao(EntidadeOperadora entidadeCredora, EntidadeOperadora entidadeDevedora, Acao acao, TituloDivida tituloDivida, double valorOperacao, LocalDateTime dataHoraOperacao) {
        this.entidadeCredora = entidadeCredora;
        this.entidadeDevedora = entidadeDevedora;
        this.acao = acao;
        this.tituloDivida = tituloDivida;
        this.valorOperacao = valorOperacao;
        this.dataHoraOperacao = dataHoraOperacao;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDataHoraOperacao() {
        return dataHoraOperacao;
    }

    public double getValorOperacao() {
        return valorOperacao;
    }

    public EntidadeOperadora getEntidadeCredora() {
        return entidadeCredora;
    }

    public EntidadeOperadora getEntidadeDevedora() {
        return entidadeDevedora;
    }

    public Acao getAcao() {
        return acao;
    }

    public TituloDivida getTituloDivida() {
        return tituloDivida;
    }
}