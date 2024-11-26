package ui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import controles.FileConnection;
import modelos.Aluno;
import modelos.Boletim;
import modelos.Disciplina;
import modelos.Professor;

public class AdicionarDisciplina extends javax.swing.JFrame {

    public AdicionarDisciplina(FileConnection fileConnection, Aluno aluno) {

        setTitle("Criar Nova Disciplina");
        setSize(400, 300);
        setLayout(new GridLayout(0,1));
        setLocationRelativeTo(null);

        JTextField txtNomeDisciplina = new JTextField();
        JComboBox<Professor> comboBoxProfessores = new JComboBox<>();

        List<Professor> professores = fileConnection.listaProfessores();
        for (Professor professor : professores) {
            comboBoxProfessores.addItem(professor);
        }

        add(new JLabel("Nome da Disciplina:"));
        add(txtNomeDisciplina);
        add(new JLabel("Escolha um Professor:"));
        add(comboBoxProfessores);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener((ActionEvent e) -> {
            
            String nomeDisciplina = txtNomeDisciplina.getText();
            Professor professorSelecionado = (Professor) comboBoxProfessores.getSelectedItem();
            
            if (nomeDisciplina.isEmpty() || professorSelecionado == null) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                return;
            }
            
            int idDisciplina = (int) (Math.random() * 1000);
            Disciplina novaDisciplina = new Disciplina(idDisciplina, nomeDisciplina, professorSelecionado.getNome());
            fileConnection.salvaDisciplina(novaDisciplina);
            
            double av1 = Double.parseDouble(JOptionPane.showInputDialog("Informe a nota AV1:"));
            double av2 = Double.parseDouble(JOptionPane.showInputDialog("Informe a nota AV2:"));
            
            Boletim boletim = new Boletim(av1, av2, novaDisciplina.getIdDisciplina());
            aluno.boletins.add(boletim);
            
            fileConnection.salvaAluno(aluno);
            JOptionPane.showMessageDialog(null, "Disciplina criada e adicionada com sucesso!");
            
            new ConsultaAluno(fileConnection, aluno);
            dispose();
            
        });
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener((ActionEvent e) -> {
            new AdicionaDisciplinaAluno(fileConnection, aluno);
            dispose();
        });

        add(btnSalvar);
        add(btnCancelar);

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
