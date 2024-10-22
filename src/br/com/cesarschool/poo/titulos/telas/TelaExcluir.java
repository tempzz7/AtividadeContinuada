package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

public class TelaExcluir {
    private JFrame frame;
    private JTextField textFieldId;

    public TelaExcluir(){
        initialize();
    }

    private void initialize (){
        frame = new JFrame("Excluir Entidade Operadora");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblId = new JLabel("Id: ");
        textFieldId = new JTextField();

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(this::excluir);

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
                    .addComponent(btnExcluir)
                    .addComponent(btnVoltar)
                    .addComponent(btnLimpar)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblId)
                .addComponent(textFieldId))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnExcluir)
                .addComponent(btnVoltar)
                .addComponent(btnLimpar))
        );

        frame.pack();
        frame.setVisible(true);
    }

    private void excluir(ActionEvent actionEvent){
        try{
            int id = Integer.parseInt(textFieldId.getText());
            RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();

            if (repositorioEntidadeOperadora.excluir(id)){
                JOptionPane.showMessageDialog(frame, "Entidade Operadora Excluída com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Erro ao excluir entidade operadora: ", "Erro", JOptionPane.ERROR_MESSAGE);
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
        new TelaExcluir();
    }
}