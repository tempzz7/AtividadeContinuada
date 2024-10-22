package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;

public class TelaAlterar {
    public TelaAlterar(){

        initialize();
    }

    public static void main(String[] args) {

        TelaIncluir tela4 = new TelaIncluir();
    }

    private void initialize (){
        JFrame frame = new JFrame();
        frame.setTitle("Alterar Entidade Operação");
        frame.setSize(600, 500);
        frame.setLayout(null);

        //id
        JLabel lblId = new JLabel("Id: ");
        lblId.setBounds(10, 20, 80, 25);
        frame.add(lblId);
        JTextField textFieldId = new JTextField();
        textFieldId.setBounds(150, 20, 165, 25);
        frame.add(textFieldId);

        //nome
        JLabel lblNome = new JLabel("Nome: ");
        lblNome.setBounds(10, 50, 80, 25);
        frame.add(lblNome);
        JTextField textFieldNome = new JTextField();
        textFieldNome.setBounds(150, 50, 165, 25);
        frame.add(textFieldNome);

        //Autorizadoação
        JLabel lblAutorizadoAcao = new JLabel("Autorizado Ação: ");
        lblAutorizadoAcao.setBounds(10, 80, 80, 25);
        frame.add(lblAutorizadoAcao);
        String[] tipos = { "True", "False"};
        JComboBox<String> comboBoxTipo = new JComboBox<>(tipos);
        comboBoxTipo.setBounds(150, 80, 80, 25);
        frame.add(comboBoxTipo);

        //saldoacao
        JLabel lblsaldoAcao = new JLabel("Saldo Ação: ");
        lblsaldoAcao.setBounds(10, 110, 80, 25);
        frame.add(lblsaldoAcao);
        JTextField textFieldsaldoAcao = new JTextField();
        textFieldsaldoAcao.setBounds(150, 110, 165, 25);
        frame.add(textFieldsaldoAcao);

        //saldoTituloDivida
        JLabel lblsaldoTituloDivida = new JLabel("Saldo Título Dívida: ");
        lblsaldoTituloDivida.setBounds(10, 140, 80, 25);
        frame.add(lblsaldoTituloDivida);
        JTextField textFieldsaldoTituloDivida = new JTextField();
        textFieldsaldoTituloDivida.setBounds(150, 140, 165, 25);
        frame.add(textFieldsaldoTituloDivida);

        //btnAlterar
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(75, 200, 100, 25);
        frame.add(btnAlterar);

        //btnVoltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 200, 100, 25);
        frame.add(btnVoltar);

        //btnLimpar
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(325, 200, 100, 25);
        frame.add(btnLimpar);

        frame.setVisible(true);
    }
}