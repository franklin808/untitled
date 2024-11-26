package modelos;
import java.util.List;

public class Professor extends Pessoa{
    
    final private int idProfessor;  // Novo campo para ID do professor
    private String salario;

    public Professor(String nome, String cpf, String salario, int idProfessor) {
        super(nome, cpf); // Chama o construtor de Pessoa para inicializar nome e cpf
        this.salario = salario;
        this.idProfessor = idProfessor;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public int getId() {
        return idProfessor;
    }
    
    public static boolean cpfExists(List<Professor> professores, String cpf) {
        for (Professor professor : professores) {
            if (professor.getCpf().equals(cpf)) {
                    return true;
            }
        }
        return false;
    }
    
}
