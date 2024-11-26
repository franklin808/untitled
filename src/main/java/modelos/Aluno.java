package modelos;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
    
    public int idAluno;
    public List<Boletim> boletins;

    public Aluno(String nome, String cpf, int idAluno) {
        super(nome, cpf); // Chama o construtor de Pessoa para inicializar nome e cpf
        this.idAluno = idAluno;
        this.boletins = new ArrayList<>();
    }

    public int getIdAluno() {
        return idAluno;
    }

    // Método setter para os boletins do aluno
    public void setBoletins(List<Boletim> boletins) {
        this.boletins = boletins;
    }
    
    // Método getter para os boletins do aluno
    public List<Boletim> getBoletins() {
        return boletins;
    }
    
    public void adicionaBoletim(Boletim boletim) {
        for (Boletim boletimAtual : boletins) {
            if (boletim.getIdDisciplina() == boletimAtual.getIdDisciplina()) {
                boletins.remove(boletimAtual);
                boletins.add(boletim);
            }
        }
    }
    
    public static boolean cpfExists(List<Aluno> alunos, String cpf) {
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                    return true;
            }
        }
        return false;
    }
    
}
