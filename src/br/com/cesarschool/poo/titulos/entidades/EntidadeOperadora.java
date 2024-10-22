package br.com.cesarschool.poo.titulos.entidades;

public class EntidadeOperadora {
    private final long identificador;
    private String nome;
    private double autorizadoAcao;
    private double saldoAcao;
    private double saldoTituloDivida;

    public EntidadeOperadora(long identificador, String nome, double autorizadoAcao) {
        this.identificador = identificador;
        this.nome = nome;
        this.autorizadoAcao = autorizadoAcao;
        this.saldoAcao = 0;
        this.saldoTituloDivida = 0;
    }

    public long getIdentificador() {
        return identificador;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getAutorizadoAcao() {
        return autorizadoAcao;
    }
    public void setAutorizadoAcao(double autorizadoAcao) {
        this.autorizadoAcao = autorizadoAcao;
    }

    public double getSaldoAcao() {
        return saldoAcao;
    }
    public double getSaldoTituloDivida() {
        return saldoTituloDivida;
    }
    void creditarSaldoAcao(double valor){
        saldoAcao += valor;
    }
    void creditarSaldoTituloDivida(double valor){
        saldoTituloDivida += valor;
    }
    void debitarSaldoAcao(double valor){
        if (saldoAcao > valor){
            saldoAcao -= valor;
        }
    }
    void debitarSaldoTituloDivida(double valor){
        if (saldoTituloDivida > valor){
            saldoTituloDivida -= valor;
        }
    }
}