package br.mackenzie.sistema_matriculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class SistemaMatriculasApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaMatriculasApplication.class, args);

        System.out.println("\n--- INICIANDO SISTEMA DE MATRÍCULAS (MACKENZIE) ---");

        AlunoDAO alunoDao = new AlunoDAO();
        MatriculaDAO matriculaDao = new MatriculaDAO();

        alunoDao.salvar(new Aluno("Ana Julia - Sistemas de Informação"));
        alunoDao.salvar(new Aluno("Joao Pedro - Engenharia"));
        alunoDao.salvar(new Aluno("Maria Oliveira - Direito"));

        List<Aluno> listaTodos = alunoDao.listarTodos();
        
        if (listaTodos.size() >= 3) {
            for (int i = listaTodos.size() - 3; i < listaTodos.size(); i++) {
                Aluno aluno = listaTodos.get(i);
                matriculaDao.salvar(new Matricula(aluno.getId(), "2024.1"));
                matriculaDao.salvar(new Matricula(aluno.getId(), "2024.2"));
            }
        }

        System.out.println("\n===========================================");
        System.out.println("      RELATÓRIO DE ALUNOS E MATRÍCULAS     ");
        System.out.println("===========================================");
        
        List<Aluno> todosNoBanco = alunoDao.listarTodos();
        
        for (Aluno a : todosNoBanco) {
            System.out.println("\nALUNO: [" + a.getId() + "] " + a.getNome());
            List<Matricula> suasMatriculas = matriculaDao.listarPorAluno(a.getId());
            
            if (suasMatriculas.isEmpty()) {
                System.out.println("  - Sem matrículas registradas.");
            } else {
                for (Matricula m : suasMatriculas) {
                    System.out.println("  -> Matrícula ID: " + m.getId() + " | Semestre: " + m.getSemestre());
                }
            }
        }
    }
}