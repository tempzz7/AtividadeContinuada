package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.*;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MediatorOperacao {
    // Instâncias de mediators e repositório
    private static MediatorOperacao instancia = new MediatorOperacao();
    private final MediatorAcao mediatorAcao = MediatorAcao.getInstance();
    private final MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getInstance();
    private final MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstance();
    private final RepositorioTransacao repositorioTransacao = new RepositorioTransacao();

    // Construtor privado para o padrão Singleton
    private MediatorOperacao() {}

    // Padrão Singleton
    public static MediatorOperacao getInstance() {
        return instancia;
    }

    // Método para realizar a operação de transação
    public String realizarOperacao(boolean ehAcao, int entidadeCreditoId, int entidadeDebitoId, int idAcaoOuTitulo, double valor) {
        // Passo 1: Validar o valor
        if (valor <= 0) {
            return "Valor inválido";
        }

        // Passo 2: Buscar entidade de crédito
        EntidadeOperadora entidadeCredito = mediatorEntidadeOperadora.buscar(entidadeCreditoId);
        if (entidadeCredito == null) {
            return "Entidade crédito inexistente";
        }

        // Passo 3: Buscar entidade de débito
        EntidadeOperadora entidadeDebito = mediatorEntidadeOperadora.buscar(entidadeDebitoId);
        if (entidadeDebito == null) {
            return "Entidade débito inexistente";
        }

        // Passo 4: Verificar autorização de crédito para ação (se ehAcao)
        if (ehAcao && entidadeCredito.getAutorizadoAcao() <= 0) {
            return "Entidade de crédito não autorizada para ação";
        }

        // Passo 5: Verificar autorização de débito para ação (se ehAcao)
        if (ehAcao && entidadeDebito.getAutorizadoAcao() <= 0) {
            return "Entidade de débito não autorizada para ação";
        }

        double valorOperacao;
        Acao acao = null;
        TituloDivida tituloDivida = null;

        // Passo 6: Buscar ação ou título de dívida
        if (ehAcao) {
            acao = mediatorAcao.buscar(idAcaoOuTitulo);
            if (acao == null) {
                return "Ação inexistente";
            }
        } else {
            tituloDivida = mediatorTituloDivida.buscar(idAcaoOuTitulo);
            if (tituloDivida == null) {
                return "Título de dívida inexistente";
            }
        }

        // Passo 7: Validar saldo da entidade de débito
        if (ehAcao) {
            if (entidadeDebito.getSaldoAcao() < valor) {
                return "Saldo da entidade débito insuficiente";
            }
        } else {
            if (entidadeDebito.getSaldoTituloDivida() < valor) {
                return "Saldo da entidade débito insuficiente";
            }
        }

        // Passo 8: Verificar se valor da ação é menor que o valor unitário
        if (ehAcao && acao.getValorUnitario() > valor) {
            return "Valor da operação é menor do que o valor unitário da ação";
        }

        // Passo 9: Calcular valor da operação
        if (ehAcao) {
            valorOperacao = valor;
        } else {
            valorOperacao = tituloDivida.calcularPrecoTransacao(valor);
        }

        // Passo 10: Creditar saldo na entidade de crédito
        if (ehAcao) {
            entidadeCredito.creditarSaldoAcao(valorOperacao);
        } else {
            entidadeCredito.creditarSaldoTituloDivida(valorOperacao);
        }

        // Passo 11: Debitar saldo na entidade de débito
        if (ehAcao) {
            entidadeDebito.debitarSaldoAcao(valorOperacao);
        } else {
            entidadeDebito.debitarSaldoTituloDivida(valorOperacao);
        }

        // Passo 12: Alterar entidade de crédito no mediator
        String mensagemCredito = mediatorEntidadeOperadora.alterar(entidadeCredito);
        if (mensagemCredito != null) {
            return mensagemCredito;
        }

        // Passo 13: Alterar entidade de débito no mediator
        String mensagemDebito = mediatorEntidadeOperadora.alterar(entidadeDebito);
        if (mensagemDebito != null) {
            return mensagemDebito;
        }

        // Passo 14: Criar transação
        Transacao transacao = new Transacao(
            entidadeCredito,
            entidadeDebito,
            ehAcao ? acao : null,
            ehAcao ? null : tituloDivida,
            valorOperacao,
            LocalDateTime.now()
        );

        // Passo 15: Incluir a transação no repositório
        repositorioTransacao.incluir(transacao);

        return "Operação realizada com sucesso";
    }

    // Método para gerar extrato de uma entidade
    public Transacao[] gerarExtrato(int entidadeId) {
        List<Transacao> transacoesCredora = repositorioTransacao.buscarPorEntidadeCredora(entidadeId);
        List<Transacao> transacoesDevedora = repositorioTransacao.buscarPorEntidadeDevedora(entidadeId);

        List<Transacao> todasTransacoes = new ArrayList<>(transacoesCredora);
        todasTransacoes.addAll(transacoesDevedora);

        todasTransacoes.sort((t1, t2) -> t2.getDataHoraOperacao().compareTo(t1.getDataHoraOperacao()));

        return todasTransacoes.toArray(new Transacao[0]);
    }
}