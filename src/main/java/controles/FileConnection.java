package controles;
import modelos.Disciplina;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import modelos.Professor;
import modelos.Aluno;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileConnection {
    
    private static final String ALUNO_FILE_NAME = "EscolaDB.json";
    private static final String PROFESSOR_FILE_NAME = "ProfessoresDB.json";
    private static final String DISCIPLINA_FILE_NAME = "DisciplinasDB.json"; // Arquivo para as disciplinas
    
    private final Gson gson;

    public FileConnection() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void salvaAluno (Aluno aluno) {
        
        List<Aluno> alunos = listaAlunos(ALUNO_FILE_NAME);
        boolean alunoExistente = false;

        for (Aluno a : alunos) {
            if (a.getCpf().equals(aluno.getCpf())) {
                a.setCpf(aluno.getCpf()); 
                a.setNome(aluno.getNome()); 
                a.setBoletins(aluno.getBoletins());
                alunoExistente = true;
                break;
            }
        }

        if (!alunoExistente) {
            alunos.add(aluno);
        }

        try (Writer writer = new FileWriter(ALUNO_FILE_NAME)) {
            gson.toJson(alunos, writer);
            System.out.println("Dados do aluno atualizados no arquivo JSON.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo JSON: " + e.getMessage());
        }
        
    }

    public List<Aluno> listaAlunos(String fileName) {
        try (Reader reader = new FileReader(fileName)) {
            Type listType = new TypeToken<List<Aluno>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo JSON não encontrado. Retornando lista vazia.");
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erro ao ler dados do arquivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void deletaAluno(String cpf) {
        List<Aluno> alunos = listaAlunos(ALUNO_FILE_NAME);
        boolean alunoRemovido = alunos.removeIf(aluno -> aluno.getCpf().equals(cpf));

        try (Writer writer = new FileWriter(ALUNO_FILE_NAME)) {
            gson.toJson(alunos, writer);
            if (alunoRemovido) {
                System.out.println("Aluno com CPF " + cpf + " foi removido.");
            } else {
                System.out.println("Aluno com CPF " + cpf + " não encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar o arquivo JSON: " + e.getMessage());
        }
    }
    
    public void salvaDisciplina(Disciplina disciplina) {
        List<Disciplina> disciplinas = listaDisciplinas();
        disciplinas.add(disciplina);

        try (Writer writer = new FileWriter(DISCIPLINA_FILE_NAME)) {
            gson.toJson(disciplinas, writer);
            System.out.println("Dados da disciplina salvos no arquivo JSON.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo de disciplinas JSON: " + e.getMessage());
        }
    }
    
    public List<Disciplina> listaDisciplinas() {
        try (Reader reader = new FileReader(DISCIPLINA_FILE_NAME)) {
            Type listType = new TypeToken<List<Disciplina>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de disciplinas não encontrado. Retornando lista vazia.");
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erro ao ler dados do arquivo de disciplinas JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void salvaProfessor(Professor professor) {
        List<Professor> professores = listaProfessores();
        boolean professorExistente = false;

        for (Professor a : professores) {
            if (a.getCpf().equals(professor.getCpf())) {
                a.setCpf(professor.getCpf()); 
                a.setNome(professor.getNome()); 
                a.setSalario(professor.getSalario()); 
                professorExistente = true;
                break;
            }
        }

        if (!professorExistente) {
            if (Professor.cpfExists(professores, professor.getCpf())) {
                JOptionPane.showMessageDialog(null, "CPF já cadastrado. Professor não será inserido.");
                return;
            }
            professores.add(professor);
        }

        try (Writer writer = new FileWriter(PROFESSOR_FILE_NAME)) {
            gson.toJson(professores, writer);
            System.out.println("Dados do professor atualizados no arquivo JSON.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo JSON: " + e.getMessage());
        }
    }
    
    public void deletaProfessor(Professor professor) {
        List<Professor> professores = listaProfessores();
        boolean professorRemovido = professores.removeIf(professorAtual -> professorAtual.getCpf().equals(professor.getCpf()));

        try (Writer writer = new FileWriter(PROFESSOR_FILE_NAME)) {
            gson.toJson(professores, writer);
            if (professorRemovido) {
                System.out.println("Professor " + professor.getNome() + "foi removido.");
            } else {
                System.out.println("Professor não encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar o arquivo JSON: " + e.getMessage());
        }
    }

    public List<Professor> listaProfessores() {
        try (Reader reader = new FileReader(PROFESSOR_FILE_NAME)) {
            Type listType = new TypeToken<List<Professor>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de professores não encontrado. Retornando lista vazia.");
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erro ao ler dados dos professores do arquivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
}
