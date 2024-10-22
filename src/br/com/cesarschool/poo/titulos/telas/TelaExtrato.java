package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelaExtrato {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public TelaExtrato() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Extrato de Transações");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"ID Credora", "ID Devedora", "Valor", "Data/Hora"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(this::voltar);

        frame.getContentPane().add(btnVoltar, "South");

        carregarTransacoes();

        frame.setVisible(true);
    }

    private void carregarTransacoes() {
        List<String[]> transacoes = lerArquivoTransacoes();
        for (String[] transacao : transacoes) {
            tableModel.addRow(transacao);
        }
    }

    private List<String[]> lerArquivoTransacoes() {
        List<String[]> transacoes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Transacao.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    String[] transacao = new String[4];
                    transacao[0] = dados[0]; 
                    transacao[1] = dados[1]; 
                    transacao[2] = dados[2]; 
                    transacao[3] = dados[3]; 
                    transacoes.add(transacao);
                } else {
                    System.err.println("Linha inválida: " + linha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transacoes;
    }

    private void voltar(ActionEvent actionEvent) {
        frame.dispose();
    }

    public static void main(String[] args) {
        new TelaExtrato();
    }
}