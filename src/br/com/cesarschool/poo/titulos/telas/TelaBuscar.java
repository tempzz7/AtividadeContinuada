package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;


public class TelaBuscar {

    public TelaBuscar(){

        initialize();
    }

    private void initialize (){
        JFrame frame = new JFrame();
        frame.setTitle("Buscar Entidade Operação");
        frame.setSize(600, 500);
        frame.setLayout(null);

        //id
        JLabel lblId = new JLabel("Id: ");
        lblId.setBounds(10, 20, 80, 25);
        frame.add(lblId);
        JTextField textFieldId = new JTextField();
        textFieldId.setBounds(150, 20, 165, 25);
        frame.add(textFieldId);

     
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(75, 200, 100, 25);
        frame.add(btnBuscar);

      
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 200, 100, 25);
        frame.add(btnVoltar);

       
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(325, 200, 100, 25);
        frame.add(btnLimpar);
        frame.setVisible(true);
    }
}