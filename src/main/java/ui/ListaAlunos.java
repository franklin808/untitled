package ui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import controles.FileConnection;
import modelos.Aluno;

public class ListaAlunos extends javax.swing.JFrame {

    
    public ListaAlunos(FileConnection fileConnection) {

        setTitle("Consultar Alunos");
        setSize(400, 400);
        setLayout(new GridLayout(0, 1));
        setLocationRelativeTo(null);

        List<Aluno> alunos = fileConnection.listaAlunos("EscolaDB.json");

        for (Aluno aluno : alunos) {
            JButton alunoButton = new JButton(aluno.getIdAluno() + " - " + aluno.getNome());
            alunoButton.addActionListener((ActionEvent e) -> {
                new ConsultaAluno(fileConnection, aluno);
                dispose();
            });
            add(alunoButton);
        }

        JButton btnInserirAluno = new JButton("Adicionar Aluno");
        btnInserirAluno.addActionListener((ActionEvent e) -> {
            new AdicionaAluno(fileConnection, new Aluno("", "", 0), null);
            dispose();
        });
        add(btnInserirAluno);
        
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
