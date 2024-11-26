package modelos;
import controles.FileConnection;
import java.util.List;

public class Boletim {
    public double av1;
    public double av2;
    public int idDisciplina;

    public Boletim(double av1, double av2, int idDisciplina) {
        this.av1 = av1;
        this.av2 = av2;
        this.idDisciplina = idDisciplina;
    }

    public double calcularMedia() {
        return (av1 + av2) / 2;
    }
    
    public Disciplina getDisciplina(FileConnection fileConnection) {
        Disciplina boletimDisciplina = new Disciplina(0, "", "");
        List<Disciplina> disciplinasCadastradas = fileConnection.listaDisciplinas();
        for (Disciplina disciplina : disciplinasCadastradas) {
            if (disciplina.getIdDisciplina() == idDisciplina) {
                boletimDisciplina = disciplina;
            }
        }        
        return boletimDisciplina;
    }

    public int getIdDisciplina() {
        return this.idDisciplina;
    }

    public void setDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }
}