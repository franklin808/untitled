package ui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelos.Boletim;
import controles.FileConnection;
import modelos.Aluno;

public class AdicionaAluno extends javax.swing.JFrame {

    private List<Boletim> boletins;
    
    public AdicionaAluno(FileConnection fileConnection, Aluno aluno, JPanel disciplinasPanel) {
        
        this.boletins = new ArrayList<>();

        setTitle("Inserir Novo Aluno");
        setSize(500, 400);
        setLayout(new GridLayout(8, 2));
        setLocationRelativeTo(null);

        JLabel lblCpf = new JLabel("CPF do Aluno:");
        JTextField txtCpf = new JTextField(aluno.getCpf());
        JLabel lblNome = new JLabel("Nome do Aluno:");
        JTextField txtNome = new JTextField(aluno.getNome());

        JButton btnSalvar = new JButton("Salvar Aluno");
        btnSalvar.addActionListener((ActionEvent e) -> {
            
            String cpf = txtCpf.getText();
            String nome = txtNome.getText();
            
            List<Aluno> alunos = fileConnection.listaAlunos("EscolaDB.json");
            if (Aluno.cpfExists(alunos, cpf) && !cpf.equals(aluno.getCpf())) {
                JOptionPane.showMessageDialog(null, "CPF já cadastrado. Aluno não será inserido.");
                return;
            }
        
            int idAluno = (int) (Math.random() * 1000);
            Aluno aluno1 = new Aluno(nome, cpf, idAluno);
            aluno1.boletins.addAll(boletins);
            
            fileConnection.salvaAluno(aluno1);
            JOptionPane.showMessageDialog(null, "Aluno inserido com sucesso!");
            
            new ConsultaAluno(fileConnection, aluno1);
            dispose();
            
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener((ActionEvent e) -> {
            if (aluno.getIdAluno() != 0) {
                new ConsultaAluno(fileConnection, aluno);
                dispose();
            } else {
                new ListaAlunos(fileConnection);
                dispose();
            }
        });

        add(lblCpf);
        add(txtCpf);
        add(lblNome);
        add(txtNome);
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
