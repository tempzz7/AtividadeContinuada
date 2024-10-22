package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;


public class TelaExcluir {

    public TelaExcluir(){

        initialize();
    }

    private void initialize (){
        JFrame frame = new JFrame();
        frame.setTitle("Excluir Entidade Operação");
        frame.setSize(600, 500);
        frame.setLayout(null);

    
        JLabel lblId = new JLabel("Id: ");
        lblId.setBounds(10, 20, 80, 25);
        frame.add(lblId);
        JTextField textFieldId = new JTextField();
        textFieldId.setBounds(150, 20, 165, 25);
        frame.add(textFieldId);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(75, 200, 100, 25);
        frame.add(btnExcluir);

       
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 200, 100, 25);
        frame.add(btnVoltar);

     
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(325, 200, 100, 25);
        frame.add(btnLimpar);

        frame.setVisible(true);
    }

}