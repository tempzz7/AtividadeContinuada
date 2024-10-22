package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;
import java.awt.event.ActionEvent;


import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

public class TelaIncluir {

    JFrame frame = new JFrame();

    JLabel lblId = new JLabel("Id: ");
    JTextField textFieldId = new JTextField();

    JLabel lblNome = new JLabel("Nome: ");
    JTextField textFieldNome = new JTextField();

    JLabel lblAutorizadoAcao = new JLabel("Autorizado Ação: ");
    String[] tipos = { "True", "False"};
    JComboBox<String> comboBoxTipo = new JComboBox<>(tipos);

    JLabel lblSaldoAcao = new JLabel("Saldo Ação: ");
    JTextField textFieldsaldoAcao = new JTextField();

    JLabel lblSaldoTituloDivida = new JLabel("Saldo Título Dívida: ");
    JTextField textFieldsaldoTituloDivida = new JTextField();

    JButton btnIncluir = new JButton("Incluir");
    JButton btnVoltar = new JButton("Voltar");
    JButton btnLimpar = new JButton("Limpar");

    public TelaIncluir(){

        initialize();
    }

    private void initialize (){
        frame.setTitle("Incluir Entidade Operação");
        frame.setSize(600, 500);
        frame.setLayout(null);

        lblId.setBounds(10, 20, 80, 25);
        frame.add(lblId);
        textFieldId.setBounds(150, 20, 165, 25);
        frame.add(textFieldId);

        lblNome.setBounds(10, 50, 80, 25);
        frame.add(lblNome);
        textFieldNome.setBounds(150, 50, 165, 25);
        frame.add(textFieldNome);

        lblAutorizadoAcao.setBounds(10, 80, 80, 25);
        frame.add(lblAutorizadoAcao);
        comboBoxTipo.setBounds(150, 80, 80, 25);
        frame.add(comboBoxTipo);

        lblSaldoAcao.setBounds(10, 110, 80, 25);
        frame.add(lblSaldoAcao);
        textFieldsaldoAcao.setBounds(150, 110, 165, 25);
        frame.add(textFieldsaldoAcao);


        lblSaldoTituloDivida.setBounds(10, 140, 80, 25);
        frame.add(lblSaldoTituloDivida);
        textFieldsaldoTituloDivida.setBounds(150, 140, 165, 25);
        frame.add(textFieldsaldoTituloDivida);


        btnIncluir.setBounds(75, 200, 100, 25);
        frame.add(btnIncluir);
        btnIncluir.addActionListener(e -> incluir(e));

        btnVoltar.setBounds(200, 200, 100, 25);
        frame.add(btnVoltar);
        btnVoltar.addActionListener(e -> voltar(e));

        btnLimpar.setBounds(325, 200, 100, 25);
        frame.add(btnLimpar);
        btnLimpar.addActionListener(e -> limpar(e));

        frame.setVisible(true);
    }

    private void incluir(ActionEvent actionEvent){
        try{
            String id = textFieldId.getText();
            String nome = textFieldNome.getText();
            String autorizadoAcao = textFieldsaldoAcao.getText();

            EntidadeOperadora entidadeOperadora = new EntidadeOperadora(Long.parseLong(id), nome, Boolean.parseBoolean(autorizadoAcao));
            RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();

            if (repositorioEntidadeOperadora.incluir(entidadeOperadora)){
                JOptionPane.showMessageDialog(frame, "Entidade Operação Incluida com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(frame, "Erro ao incluir entidade operação: ", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            

        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Erro ao converter valores numéricos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void limpar(ActionEvent actionEvent){
        textFieldId.setText("");
        textFieldNome.setText("");
        textFieldsaldoAcao.setText("");
        textFieldsaldoTituloDivida.setText("");
    }
    private void voltar(ActionEvent actionEvent){
        
    }
}