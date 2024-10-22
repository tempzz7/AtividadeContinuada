package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Locale;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

public class TelaTransacao {
    private JFrame frame;
    private JTextField textFieldIdCredora;
    private JTextField textFieldIdDevedora;
    private JTextField textFieldValor;
    private RepositorioEntidadeOperadora repositorioEntidadeOperadora;

    public TelaTransacao() {
        repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Realizar Transação");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblIdCredora = new JLabel("Id Credora: ");
        textFieldIdCredora = new JTextField();

        JLabel lblIdDevedora = new JLabel("Id Devedora: ");
        textFieldIdDevedora = new JTextField();

        JLabel lblValor = new JLabel("Valor: ");
        textFieldValor = new JTextField();

        JButton btnTransacao = new JButton("Realizar Transação");
        btnTransacao.addActionListener(this::realizarTransacao);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(this::voltar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(this::limparCampos);

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblIdCredora)
                .addComponent(lblIdDevedora)
                .addComponent(lblValor))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(textFieldIdCredora)
                .addComponent(textFieldIdDevedora)
                .addComponent(textFieldValor)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnTransacao)
                    .addComponent(btnVoltar)
                    .addComponent(btnLimpar)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblIdCredora)
                .addComponent(textFieldIdCredora))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblIdDevedora)
                .addComponent(textFieldIdDevedora))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblValor)
                .addComponent(textFieldValor))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnTransacao)
                .addComponent(btnVoltar)
                .addComponent(btnLimpar))
        );

        frame.pack();
        frame.setVisible(true);
    }

    private void realizarTransacao(ActionEvent actionEvent) {
        String idCredora = textFieldIdCredora.getText();
        String idDevedora = textFieldIdDevedora.getText();
        String valorStr = textFieldValor.getText();

        EntidadeOperadora credora = repositorioEntidadeOperadora.buscar(Long.parseLong(idCredora));
        EntidadeOperadora devedora = repositorioEntidadeOperadora.buscar(Long.parseLong(idDevedora));

        if (credora != null && devedora != null) {
            try {
                NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));
                double valor = format.parse(valorStr).doubleValue();

       
                credora.creditarSaldoAcao(valor);
                devedora.debitarSaldoAcao(valor);

   
                repositorioEntidadeOperadora.alterar(credora);
                repositorioEntidadeOperadora.alterar(devedora);

              
                Transacao transacao = new Transacao(credora, devedora, null, null, valor, LocalDateTime.now());
                RepositorioTransacao repoTransacao = new RepositorioTransacao();
                repoTransacao.incluir(transacao);

                JOptionPane.showMessageDialog(frame, "Transação realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao converter valor: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Entidades não encontradas!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos(ActionEvent actionEvent) {
        textFieldIdCredora.setText("");
        textFieldIdDevedora.setText("");
        textFieldValor.setText("");
    }

    private void voltar(ActionEvent actionEvent) {
        frame.dispose();
    }

    public static void main(String[] args) {
        new TelaTransacao();
    }
}