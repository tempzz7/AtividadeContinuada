package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDateTime;

public class Transacao {
    private EntidadeOperadora entidadeCredora;
    private EntidadeOperadora entidadeDevedora;
    private double valorOperacao;
    private LocalDateTime dataHoraOperacao;

    public Transacao(EntidadeOperadora credora, EntidadeOperadora devedora, String descricao, String status, double valor, LocalDateTime dataHora) {
        this.entidadeCredora = credora;
        this.entidadeDevedora = devedora;
        this.valorOperacao = valor;
        this.dataHoraOperacao = dataHora;
    }

    public void realizarTransacao() {
        entidadeCredora.creditarSaldoTituloDivida(valorOperacao);
        entidadeDevedora.debitarSaldoTituloDivida(valorOperacao);
    }

    public EntidadeOperadora getEntidadeCredora() {
        return entidadeCredora;
    }

    public EntidadeOperadora getEntidadeDevedora() {
        return entidadeDevedora;
    }

    public double getValorOperacao() {
        return valorOperacao;
    }

    public LocalDateTime getDataHoraOperacao() {
        return dataHoraOperacao;
    }
}
