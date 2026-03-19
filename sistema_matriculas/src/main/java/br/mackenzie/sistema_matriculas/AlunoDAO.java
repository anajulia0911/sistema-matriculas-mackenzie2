package br.mackenzie.sistema_matriculas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    
    private String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres"
                       + "?user=postgres.ypoqwjllfwshtcoswtqg"
                       + "&password=mackenziebdsistema" 
                       + "&sslmode=disable"
                       + "&prepareThreshold=0";

    public void salvar(Aluno aluno) {
       
        String createSql = "CREATE TABLE IF NOT EXISTS aluno (id SERIAL PRIMARY KEY, nome TEXT)";
        String insertSql = "INSERT INTO aluno (nome) VALUES (?)";

        try (Connection con = DriverManager.getConnection(url)) {
        
            Statement st = con.createStatement();
            st.execute(createSql);

            // Salva o aluno
            try (PreparedStatement stmt = con.prepareStatement(insertSql)) {
                stmt.setString(1, aluno.getNome());
                stmt.executeUpdate();
                System.out.println("DAO: " + aluno.getNome() + " salvo com sucesso!");
            }
        } catch (SQLException e) {
            System.err.println("Erro no DAO ao salvar: " + e.getMessage());
        }
    }

    public List<Aluno> listarTodos() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (Connection con = DriverManager.getConnection(url);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Erro no DAO ao listar: " + e.getMessage());
        }
        return lista;
    }
}