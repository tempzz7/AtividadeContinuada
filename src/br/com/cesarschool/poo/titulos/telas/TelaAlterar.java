package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

public class TelaAlterar {
    private JFrame frame;
    private JTextField textFieldId;
    private JTextField textFieldNome;
    private JComboBox<String> comboBoxTipo;
    private JTextField textFieldsaldoAcao;
    private JTextField textFieldsaldoTituloDivida;

    public TelaAlterar(){
        initialize();
    }

    private void initialize (){
        frame = new JFrame("Alterar Entidade Operadora");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblId = new JLabel("Id: ");
        textFieldId = new JTextField();

        JLabel lblNome = new JLabel("Nome: ");
        textFieldNome = new JTextField();

        JLabel lblAutorizadoAcao = new JLabel("Autorizado Ação: ");
        String[] tipos = { "True", "False"};
        comboBoxTipo = new JComboBox<>(tipos);

        JLabel lblSaldoAcao = new JLabel("Saldo Ação: ");
        textFieldsaldoAcao = new JTextField();

        JLabel lblSaldoTituloDivida = new JLabel("Saldo Título Dívida: ");
        textFieldsaldoTituloDivida = new JTextField();

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.addActionListener(this::alterar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(this::voltar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(this::limpar);

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblId)
                .addComponent(lblNome)
                .addComponent(lblAutorizadoAcao)
                .addComponent(lblSaldoAcao)
                .addComponent(lblSaldoTituloDivida))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(textFieldId)
                .addComponent(textFieldNome)
                .addComponent(comboBoxTipo)
                .addComponent(textFieldsaldoAcao)
                .addComponent(textFieldsaldoTituloDivida)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnAlterar)
                    .addComponent(btnVoltar)
                    .addComponent(btnLimpar)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblId)
                .addComponent(textFieldId))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblNome)
                .addComponent(textFieldNome))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblAutorizadoAcao)
                .addComponent(comboBoxTipo))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblSaldoAcao)
                .addComponent(textFieldsaldoAcao))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblSaldoTituloDivida)
                .addComponent(textFieldsaldoTituloDivida))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnAlterar)
                .addComponent(btnVoltar)
                .addComponent(btnLimpar))
        );

        frame.pack();
        frame.setVisible(true);
    }

    private void alterar(ActionEvent actionEvent){
        try{
            long id = Long.parseLong(textFieldId.getText());
            String nome = textFieldNome.getText();
            boolean autorizadoAcao = Boolean.parseBoolean((String) comboBoxTipo.getSelectedItem());
            double saldoAcao = Double.parseDouble(textFieldsaldoAcao.getText());
            double saldoTituloDivida = Double.parseDouble(textFieldsaldoTituloDivida.getText());

            EntidadeOperadora entidadeOperadora = new EntidadeOperadora(id, nome, autorizadoAcao);
            entidadeOperadora.creditarSaldoAcao(saldoAcao);
            entidadeOperadora.creditarSaldoTituloDivida(saldoTituloDivida);

            RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();

            if (repositorioEntidadeOperadora.alterar(entidadeOperadora)){
                JOptionPane.showMessageDialog(frame, "Entidade Operadora Alterada com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Erro ao alterar entidade operadora: ", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Erro ao converter valores numéricos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar(ActionEvent actionEvent){
        textFieldId.setText("");
        textFieldNome.setText("");
        comboBoxTipo.setSelectedIndex(0);
        textFieldsaldoAcao.setText("");
        textFieldsaldoTituloDivida.setText("");
    }

    private void voltar(ActionEvent actionEvent){
        frame.dispose();
    }

    public static void main(String[] args) {
        new TelaAlterar();
    }
}