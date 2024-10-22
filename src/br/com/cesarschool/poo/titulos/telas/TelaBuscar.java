package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

public class TelaBuscar {
    private JFrame frame;
    private JTextField textFieldId;

    public TelaBuscar(){
        initialize();
    }

    private void initialize (){
        frame = new JFrame("Buscar Entidade Operadora");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblId = new JLabel("Id: ");
        textFieldId = new JTextField();

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this::buscar);

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
                .addComponent(lblId))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(textFieldId)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnBuscar)
                    .addComponent(btnVoltar)
                    .addComponent(btnLimpar)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblId)
                .addComponent(textFieldId))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnBuscar)
                .addComponent(btnVoltar)
                .addComponent(btnLimpar))
        );

        frame.pack();
        frame.setVisible(true);
    }

    private void buscar(ActionEvent actionEvent){
        try{
            int id = Integer.parseInt(textFieldId.getText());
            RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();
            EntidadeOperadora entidadeOperadora = repositorioEntidadeOperadora.buscar(id);

            if (entidadeOperadora != null){
                JOptionPane.showMessageDialog(frame, "Entidade Encontrada: \n" +
                        "Nome: " + entidadeOperadora.getNome() + "\n" +
                        "Autorizado Ação: " + entidadeOperadora.getAutorizadoAcao() + "\n" +
                        "Saldo Ação: " + entidadeOperadora.getSaldoAcao() + "\n" +
                        "Saldo Título Dívida: " + entidadeOperadora.getSaldoTituloDivida(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Entidade não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Erro ao converter valores numéricos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch(Exception ex){
            JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar(ActionEvent actionEvent){
        textFieldId.setText("");
    }

    private void voltar(ActionEvent actionEvent){
        frame.dispose();
    }

    public static void main(String[] args) {
        new TelaBuscar();
    }
}