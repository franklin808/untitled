package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import modelos.Professor;
import controles.FileConnection;

public class AdicionaProfessor extends javax.swing.JFrame {

    public AdicionaProfessor(FileConnection fileConnection, Professor professor) {

        setTitle("Inserir Novo Aluno");
        setSize(500, 400);
        setLayout(new GridLayout(0, 1));
        setLocationRelativeTo(null);

        JLabel lblCpf = new JLabel("CPF do Professor:");
        JTextField txtCpf = new JTextField(professor.getCpf());
        JLabel lblNome = new JLabel("Nome do Professor:");
        JTextField txtNome = new JTextField(professor.getNome());
        JLabel lblSalario = new JLabel("Salario do Professor:");
        JTextField txtSalario = new JTextField(professor.getSalario());
        
        JButton btnSalvar = new JButton("Salvar Professor");
        JButton btnExcuir = new JButton("Excluir Professor");
        JButton btnCancelar = new JButton("Cancelar");

        add(lblCpf);
        add(txtCpf);
        add(lblNome);
        add(txtNome);
        add(lblSalario);
        add(txtSalario);
        add(btnSalvar);
        if (professor != null) {
            add(btnExcuir);
        }
        add(btnCancelar);

        // Botão para salvar o professor
        btnSalvar.addActionListener((ActionEvent e) -> {
            String cpf = txtCpf.getText();
            String nome = txtNome.getText();
            String salario = txtSalario.getText();
            // Cria o novo professor
            int idProfessor = (int) (Math.random() * 1000);
            Professor professor1 = new Professor(nome, cpf, salario, idProfessor);
            // Salva o professor no arquivo
            fileConnection.salvaProfessor(professor1);
            JOptionPane.showMessageDialog(null, "Professor inserido com sucesso!");
            new ListaProfessores(fileConnection);
            dispose();
        });
        
        btnExcuir.addActionListener((ActionEvent e) -> {
            fileConnection.deletaProfessor(professor);
            JOptionPane.showMessageDialog(null, "Professor excluído (se encontrado).");
            new ListaProfessores(fileConnection);
            dispose();
        });

        // Botão para cancelar a operação
        btnCancelar.addActionListener((ActionEvent e) -> {
            new ListaProfessores(fileConnection);
            dispose();
        });

        setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
