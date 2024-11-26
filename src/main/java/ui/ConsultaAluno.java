package ui;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controles.FileConnection;
import modelos.Aluno;
import modelos.Boletim;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class ConsultaAluno extends javax.swing.JFrame {

    public ConsultaAluno(FileConnection fileConnection, Aluno aluno) {

        setTitle("Detalhes do Aluno: " + aluno.getNome());
        setSize(500, 500);
        setLayout(new GridLayout(0,1));
        setLocationRelativeTo(null);

        JPanel alunoPanel = new JPanel(new GridLayout(0, 1));
        alunoPanel.add(new JLabel("ID: " + aluno.getIdAluno()));
        alunoPanel.add(new JLabel("CPF: " + aluno.getCpf()));
        alunoPanel.add(new JLabel("Nome: " + aluno.getNome()));

        JPanel disciplinasPanel = new JPanel();
        disciplinasPanel.setLayout(new BoxLayout(disciplinasPanel, BoxLayout.Y_AXIS));

        for (Boletim boletim : aluno.boletins) {
            String disciplinaInfo = "Disciplina: " + boletim.getDisciplina(fileConnection).getNome() +
                    ", Professor: " + boletim.getDisciplina(fileConnection).getProfessor() +
                    ", AV1: " + boletim.av1 +
                    ", AV2: " + boletim.av2 +
                    ", Média: " + boletim.calcularMedia();
            disciplinasPanel.add(new JLabel(disciplinaInfo));
        }
        
        JButton btnAdicionarDisciplina = new JButton("Adicionar Disciplina");
        btnAdicionarDisciplina.addActionListener((ActionEvent e) -> {
            new AdicionaDisciplinaAluno(fileConnection, aluno);
            dispose();
        });

        JButton btnEditarAluno = new JButton("Editar Aluno");
        btnEditarAluno.addActionListener((ActionEvent e) -> {
            new AdicionaAluno(fileConnection, aluno, null);
            dispose();
        });

        JButton btnExcluirAluno = new JButton("Excluir Aluno");
        btnExcluirAluno.addActionListener((ActionEvent e) -> {
            fileConnection.deletaAluno(aluno.getCpf());
            JOptionPane.showMessageDialog(null, "Aluno excluído (se encontrado).");
            new ListaAlunos(fileConnection);
            dispose();
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener((ActionEvent e) -> {
            new ListaAlunos(fileConnection);
            dispose();
        });

        add(alunoPanel);
        add(disciplinasPanel);
        add(btnAdicionarDisciplina);
        add(btnEditarAluno);
        add(btnExcluirAluno);
        add(btnVoltar);

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
