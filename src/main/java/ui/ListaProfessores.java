package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import modelos.Professor;
import controles.FileConnection;

public class ListaProfessores extends javax.swing.JFrame {

    
    public ListaProfessores(FileConnection fileConnection) {
       
        setTitle("Lista Professores");
        setSize(400, 400);
        setLayout(new GridLayout(0, 1)); // Exibe os alunos como uma lista de botões
        setLocationRelativeTo(null);

        List<Professor> professores = fileConnection.listaProfessores();
        
        for (Professor professor : professores) {
            
            JButton professorButton = new JButton(professor.getId() + " - " + professor.getNome());
            professorButton.addActionListener((ActionEvent e) -> {
                new AdicionaProfessor(fileConnection, professor);
                dispose();
            });
            add(professorButton);
            
        }
        
        JButton adicionarButton = new JButton("Adicionar Professor");
        adicionarButton.addActionListener((ActionEvent e) -> {
            new AdicionaProfessor(fileConnection, new Professor("", "", "", 0));
            dispose();
        });
        
        add(adicionarButton);

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
