package modelos;

public class Disciplina {
    
    public int idDisciplina;
    public String nome;
    public String professor;

    public Disciplina(int idDisciplina, String nome, String professor) {
        this.idDisciplina = idDisciplina;
        this.nome = nome;
        this.professor = professor;
    }
    
    public int getIdDisciplina() {
        return idDisciplina;
    }
    
    public String getNome() {
        return nome;
    }
    // Método getter para o nome do professor
    public String getProfessor() {
        return professor;
    }

    // Método setter para o nome da disciplina
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método setter para o professor da disciplina
    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
