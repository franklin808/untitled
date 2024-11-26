package ui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import controles.FileConnection;
import modelos.Aluno;
import modelos.Boletim;
import modelos.Disciplina;

public class AdicionaDisciplinaAluno extends javax.swing.JFrame {

    final private FileConnection fileConnection;
    final private Aluno aluno;

    public AdicionaDisciplinaAluno(FileConnection fileConnection, Aluno aluno) {
        
        this.fileConnection = fileConnection;
        this.aluno = aluno;

        setTitle("Adicionar Disciplina");
        setSize(400, 300);
        setLayout(new GridLayout(0,1));
        setLocationRelativeTo(null);

        List<Disciplina> disciplinasCadastradas = fileConnection.listaDisciplinas();

        for (Disciplina disciplina : disciplinasCadastradas) {
            JButton button = new JButton(disciplina.getNome());
            button.addActionListener((ActionEvent e) -> {
                adicionarDisciplinaAoAluno(disciplina);
            });
            add(button);
        }

        JButton btnCriarNova = new JButton("Criar Nova Disciplina");
        btnCriarNova.addActionListener((ActionEvent e) -> {
            new AdicionarDisciplina(fileConnection, aluno);
            dispose();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener((ActionEvent e) -> {
            new ConsultaAluno(fileConnection, aluno);
            dispose();
        });

        add(btnCriarNova);
        add(btnCancelar);

        setVisible(true);
    }
        
    private void adicionarDisciplinaAoAluno(Disciplina disciplina) {
    
        double av1 = Double.parseDouble(JOptionPane.showInputDialog("Informe a nota AV1:"));
        double av2 = Double.parseDouble(JOptionPane.showInputDialog("Informe a nota AV2:"));

        Boletim boletim = new Boletim(av1, av2, disciplina.getIdDisciplina());
        aluno.adicionaBoletim(boletim);

        fileConnection.salvaAluno(aluno);
        JOptionPane.showMessageDialog(null, "Disciplina adicionada com sucesso!");
        
        new ConsultaAluno(fileConnection, aluno);
        dispose();
        
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
