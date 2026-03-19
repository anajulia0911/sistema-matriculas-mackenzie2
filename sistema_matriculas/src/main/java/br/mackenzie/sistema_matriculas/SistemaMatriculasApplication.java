package br.mackenzie.sistema_matriculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class SistemaMatriculasApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaMatriculasApplication.class, args);

        System.out.println("\n--- INICIANDO CONEXÃO COM O NOVO BANCO (SA-EAST-1) ---");

        AlunoDAO dao = new AlunoDAO();

    
        System.out.println("LOG: Salvando registros...");
        
        Aluno ana = new Aluno("Ana Julia Mackenzie");
        dao.salvar(ana);

        Aluno carlos = new Aluno("Carlos Silva - Ciencias Contabeis");
        dao.salvar(carlos);

        
        System.out.println("\n--- LISTAGEM FINAL PARA O TRABALHO ---");
        List<Aluno> todos = dao.listarTodos();
        
        if (todos.isEmpty()) {
            System.out.println("Aviso: O banco ainda está vazio.");
        } else {
            for (Aluno a : todos) {
                System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome());
            }
        }

        System.out.println("\n--- TUDO PRONTO! PODE TIRAR O PRINT AGORA ---");
    }
}